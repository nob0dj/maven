package com.sh.app.student.service;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.sh.app.student.entity.Student;
import com.sh.app.student.repository.StudentRepositoryImpl;

import lombok.extern.log4j.Log4j2;


@Log4j2
@TestMethodOrder(OrderAnnotation.class) // org.junit.jupiter.api.MethodOrderer.OrderAnnotation.OrderAnnotation
class StudentServiceTest {

	private StudentService studentService;

    @BeforeEach // JUnit 4의 @Before
    void setUp() {
        this.studentService = new StudentServiceImpl(new StudentRepositoryImpl());
    }
	
    @DisplayName("StudentService 의존객체 주입")
	@Test
	void studentSeriveIsNotNull() throws Exception {
		assertNotNull(this.studentService); // jupiter-api 제공
		assertThat(this.studentService).isNotNull(); // assertj-core 제공
		// import static org.assertj.core.api.Assertions.assertThat;
	}
	
	@DisplayName("학생 전체 조회")
	@Test
	void testSelectStudentList() throws Exception {
		List<Student> students = studentService.selectStudentList();
		assertThat(students).isNotEmpty();
		// log.debug("students = {}", students);
		
		students.forEach(
			(student) -> {
				// log.debug("student = {}", student);
				assertThat(student.getId()).isNotEqualTo(0);
				assertThat(student.getName()).isNotEmpty();
				assertThat(student.getTel()).isNotEmpty();
				assertThat(student.getCreatedAt()).isNotNull();
		});
	}
	
	@DisplayName("존재하는 학생 1명 조회")
	@Test
	void test2() throws Exception {
		int no = 5; // 존재하는 임의의 학생번호
		Student student = studentService.selectOneStudent(no);
		assertThat(student).isNotNull();
		assertThat(student.getId()).isNotEqualTo(0);
		assertThat(student.getName()).isNotEmpty();
		assertThat(student.getTel()).isNotEmpty();
		assertThat(student.getCreatedAt()).isNotNull();
	}
	
	@DisplayName("존재하지 않는 학생 1명 조회")
	@Test
	void test3() throws Exception {
		int no = Integer.MAX_VALUE;
		Student student = studentService.selectOneStudent(no);
		assertThat(student).isNull();
	}
	
	
	@DisplayName("학생 등록")
	@Test
	@Order(1)
	void test4() throws Exception {
		Student student = Student.builder()
				.name("세종대왕")
				.tel("01099999999")
				.build();
		assertThat(student.getId()).isEqualTo(0);
		int result = studentService.insertStudent(student);
		int no = student.getId();
		assertThat(no).isNotEqualTo(0);
		assertThat(result).isGreaterThan(0);
		
		student = studentService.selectOneStudent(no);
		assertThat(student).isNotNull();
		assertThat(student.getId()).isNotEqualTo(0).isEqualTo(no);
		assertThat(student.getName()).isNotEmpty();
		assertThat(student.getTel()).isNotEmpty();
		assertThat(student.getCreatedAt()).isNotNull();
	}

	@DisplayName("학생 수정")
	@ParameterizedTest
	@MethodSource("studentProvider")
	@Order(2)
	void test5(Student student) throws Exception {
		log.debug("student = {}", student);
		
		int no = student.getId();
		String newName = "마동석";
		String newTel = "01055556666";
		LocalDateTime createdAt = student.getCreatedAt();
		
		student.setName(newName);
		student.setTel(newTel);
		
		int result = studentService.updateStudent(student);
		assertThat(result).isGreaterThan(0);
		
		student = studentService.selectOneStudent(no);
		assertThat(student).isNotNull();
		assertThat(student.getId()).isEqualTo(no);
		assertThat(student.getName()).isEqualTo(newName);
		assertThat(student.getTel()).isEqualTo(newTel);
		assertThat(student.getCreatedAt()).isEqualTo(createdAt);
		
	}
	
	@DisplayName("학생 삭제")
	@ParameterizedTest
	@MethodSource("studentProvider")
	@Order(3)
	void test6(Student student) throws Exception {
		log.debug("student = {}", student);
		
		int no = student.getId();
		
		int result = studentService.deleteStudent(no);
		assertThat(result).isGreaterThan(0);
		
		student = studentService.selectOneStudent(no);
		assertThat(student).isNull();
	}
	
	
	@DisplayName("SqlSession#selectMap - Integer(pk)=Student")
	@Test
	void test7() throws Exception {
		Map<Integer, Object> studentMap = studentService.selectStudentMapKey();
		log.debug("studentMap = {}", studentMap); 
		// 	{
		// 		6={no=6, createdAt=2023-07-20T15:03:12, name=이순신, tel=01033334444}, 
		//		5={no=5, createdAt=2023-07-20T15:03:12, name=신사임당, tel=01022223333}, 
		// 		20={no=20, createdAt=2023-07-20T16:37:16, name=홍길동, tel=01012341234}
		// 	}

		assertThat(studentMap).isNotNull().isNotEmpty();
	}
	
	/**
	 * Arguments.arguments(Object... arguments)
	 * 
	 * 제공된 Arguments객체안의 인수를 가지고 테스트메소드를 실행한다. 자동형변환처리됨.
	 * Arguments객체의 개수만큼 테스트메소드 실행
	 *  
	 * @return
	 */
	public static Stream<Arguments> studentProvider() {
		StudentService studentService = new StudentServiceImpl(new StudentRepositoryImpl());
		List<Student> students = studentService.selectStudentList();
		return Stream.of(
				Arguments.arguments(students.get(0))
			);
	}
	
	
	
}
