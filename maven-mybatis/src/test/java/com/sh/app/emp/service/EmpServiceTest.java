package com.sh.app.emp.service;


import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import com.sh.app.emp.repository.EmpRepositoryImpl;

class EmpServiceTest {
	
	private EmpService empService;

	@BeforeEach
	void setUp() throws Exception {
		this.empService = new EmpServiceImpl(new EmpRepositoryImpl());
	}

	@DisplayName("EmpService 의존객체")
	@Test
	void empServiceIsNotNull() {
		assertThat(empService).isNotNull();
	}
	
	@DisplayName("emp 전체조회")
	@Test
	@Tag("slow")
	void test1() {
		List<Map<String, Object>> emps = empService.selectEmpList();
		assertThat(emps).isNotNull().isNotEmpty();
		
		assertThat(emps).allSatisfy((emp) -> {
			assertThat(emp).isNotNull()
//				.containsKey(key:String)
//				.containsValue(value:Object)
//				.containsEntry(key:String, value:Object)
				// 중복/순서 상관없이 제시한 키값을 모두 가지고 있는 경우
				.containsOnlyKeys("empId", "empName", "empNo", "email", "phone", "deptCode", "jobCode", "salLevel", "salary", "bonus", "managerId", "hireDate", "quitDate", "quitYn");
			
			assertThat(emp.get("empId"))
//				.asInstanceOf(InstanceOfAssertFactories.INTEGER).isGreaterThan(0); // type casting이 아닌 down casting만 가능하다.
				.isInstanceOfSatisfying(String.class, (strEmpId) -> assertThat(Integer.parseInt(strEmpId)).isGreaterThan(0));
			assertThat(emp.get("empName"))
				.asInstanceOf(InstanceOfAssertFactories.STRING).isNotBlank();
			assertThat(emp.get("empNo"))
				.asInstanceOf(InstanceOfAssertFactories.STRING).containsPattern(Pattern.compile("^\\d{6}-\\d{7}$"));
		});
	}
	
	@DisplayName("emp 검색1 - 사번 | 사원명 | 이메일 | 전화번호 (like연산)")
	@ParameterizedTest
	@CsvSource(value = {
		"emp_id, 202",
		"emp_name, 동",
		"email,a",
		"phone,010"
	})
	void test2(String searchType, String searchKeyword) {
		Map<String, Object> params = Map.of("searchType", searchType, "searchKeyword", searchKeyword);
		List<Map<String, Object>> emps = empService.search1(params);
		assertThat(emps).isNotNull().isNotEmpty();
		
		assertThat(emps).allSatisfy((emp) -> {
			String key = toCamelCase(searchType);
//			System.out.println(searchKeyword + " " + emp.get(key));
			assertThat(emp.get(key))
				.asInstanceOf(InstanceOfAssertFactories.STRING).contains(searchKeyword);
		});
	}
	
	@DisplayName("emp 검색2 - 성별 | 급여 (조건값이 없을때 전체 사원이 조회된다)")
	@ParameterizedTest
	@CsvSource(value = {
			"null, null, null"
		}, nullValues = "null")
	void test3a(String gender, Integer salary, String salaryCompare) {
		Map<String, Object> params = new HashMap<>();
		params.put("gender", gender);
		params.put("salary", salary);
		params.put("salaryCompare", salaryCompare);
		
		List<Map<String, Object>> emps = empService.search2(params);
		assertThat(emps).isNotNull().isNotEmpty();
		
		int expected = empService.selectTotalCount();
		assertThat(emps).size().isEqualTo(expected);
		
	}
	@DisplayName("emp 검색2 - 성별 | 급여 (검색 조건이 있는 경우)")
	@ParameterizedTest
	@CsvSource(value = {
		"null, 2_000_000, ge",
		"남, null, null",
		"여, 3_000_000, le",
		"남, 5_000_000, ge"
	}, nullValues = "null")
	void test3b(String gender, Integer salary, String salaryCompare) {
		// Map.of는 null value를 허용하지 않는다.
		Map<String, Object> params = new HashMap<>();
		params.put("gender", gender);
		params.put("salary", salary);
		params.put("salaryCompare", salaryCompare);
		
		List<Map<String, Object>> emps = empService.search2(params);
		assertThat(emps).isNotNull().isNotEmpty();
		
		assertThat(emps).allSatisfy((emp) -> {
			if(salary != null) {
				assertThat(emp.get("salary"))
				.isInstanceOfSatisfying(BigDecimal.class, (sal) -> {
					switch(salaryCompare) {
					case "le": 
						assertThat(sal.intValue()).isLessThanOrEqualTo(salary);
						break;
					case "ge": 
						assertThat(sal.intValue()).isGreaterThanOrEqualTo(salary);
						break;
					}
				});;
			}
		});
	}
	

	static String toCamelCase(String s){
	   String[] parts = s.split("_");
	   String camelCaseString = parts[0];
	   for (int i = 1; i < parts.length; i++){
		  camelCaseString = camelCaseString + toProperCase(parts[i]);
	   }
	   return camelCaseString;
	}

	static String toProperCase(String s) {
		return s.substring(0, 1).toUpperCase() +
				   s.substring(1).toLowerCase();
	}	
	
	@DisplayName("emp 검색3 - 직급 n개")
	@ParameterizedTest
	@MethodSource("generateJobCodes")
	void test4a(List<String> jobCodes) {
		Map<String, Object> params = Map.of("jobCodes", jobCodes);
		List<Map<String, Object>> emps = empService.search3(params);
		assertThat(emps).isNotNull().isNotEmpty();
		
		assertThat(emps).allSatisfy((emp) -> {
			assertThat(emp.get("jobCode"))
				.isInstanceOfSatisfying(String.class, (jobCode) -> {
					assertThat(jobCodes).contains(jobCode);
				});;
		});
	}
	static Stream<Arguments> generateJobCodes() {
	    return Stream.of(
	        Arguments.of(Arrays.asList("J1", "J2", "J3")),
	        Arguments.of(Arrays.asList("J5", "J6")),
	        Arguments.of(Arrays.asList("J3"))
	    );
	}
	
	@DisplayName("emp 검색3 - 부서 n개")
	@ParameterizedTest
	@MethodSource("generateDeptCodes")
	void test4b(List<String> deptCodes) {
		Map<String, Object> params = Map.of("deptCodes", deptCodes);
		List<Map<String, Object>> emps = empService.search3(params);
		assertThat(emps).isNotNull().isNotEmpty();
		
		assertThat(emps).allSatisfy((emp) -> {
			assertThat(emp.get("deptCode"))
			.isInstanceOfSatisfying(String.class, (deptCode) -> {
				assertThat(deptCodes).contains(deptCode);
			});;
		});
	}
	static Stream<Arguments> generateDeptCodes() {
		return Stream.of(
				Arguments.of(Arrays.asList("D1", "D2", "D9")),
				Arguments.of(Arrays.asList("D5", "D6")),
				Arguments.of(Arrays.asList("D8"))
				);
	}
	
	@DisplayName("emp 검색3 - 부서 n개 && 직급 n개")
	@ParameterizedTest
	@MethodSource("generateDeptCodesAndJobCodes")
	void test4c(List<String> deptCodes, List<String> jobCodes) {
		Map<String, Object> params = Map.of("deptCodes", deptCodes, "jobCodes", jobCodes);
		List<Map<String, Object>> emps = empService.search3(params);
		assertThat(emps).isNotNull().isNotEmpty();
		
		assertThat(emps).allSatisfy((emp) -> {
			assertThat(emp.get("deptCode"))
			.isInstanceOfSatisfying(String.class, (deptCode) -> {
				assertThat(deptCodes).contains(deptCode);
			});;
			assertThat(emp.get("jobCode"))
			.isInstanceOfSatisfying(String.class, (jobCode) -> {
				assertThat(jobCodes).contains(jobCode);
			});;
		});
	}
	static Stream<Arguments> generateDeptCodesAndJobCodes() {
		return Stream.of(
				Arguments.of(Arrays.asList("D1", "D2", "D9"), Arrays.asList("J1", "J2", "J3")),
				Arguments.of(Arrays.asList("D5", "D6"), Arrays.asList("J5", "J6")),
				Arguments.of(Arrays.asList("D5"), Arrays.asList("J7"))
				);
	}
	
	
	

}
