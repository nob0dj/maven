package com.sh.app.student.service;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.sh.app.student.entity.Student;
import com.sh.app.student.repository.StudentRepositoryImpl;

class StudentServicePagingTest {
	static private StudentService studentService;
	
	@BeforeAll 
    static void setUpBeforeClass() {
        studentService = new StudentServiceImpl(new StudentRepositoryImpl());
    }
	@BeforeEach
	void setUp() throws Exception {
//		this.studentService = new StudentServiceImpl(new StudentRepositoryImpl());
	}

	@DisplayName("전체 학생수 조회")
	@Test
	void testGetTotalCount() {
		// given
		
		// when
		int totalCount = studentService.getTotalCount();
		
		// then
		List<Student> students = studentService.selectStudentList();
		assertThat(totalCount).isEqualTo(students.size());
	}
	
	@DisplayName("마지막 페이지 조회")
	@Test
	void testGetLastPage() throws Exception {
		// given
		final int limit = 10;
		// when
		int totalCount = studentService.getTotalCount();
		int lastPage = (int) Math.ceil((double) totalCount / limit);
		// then 
		// 마지막 페이지의 마지막 Student객체와 전체조회 마지막 Student객체가 동일한지 비교
		List<Student> allStudents = studentService.selectStudentList();
		Student s1 = allStudents.get(allStudents.size() - 1);
		List<Student> lastPageStudents = studentService.selectStudentList(Map.of("page", lastPage, "limit", limit));
		Student s2 = lastPageStudents.get(lastPageStudents.size() - 1);
		assertThat(s1).isEqualTo(s2);
	}
	
	@DisplayName("학생 페이징 조회")
	@ParameterizedTest
//	@ValueSource(ints = {1,2,3,4,5,6,7,8,9,10}) // 단일 자료형에만 사용
	@MethodSource("pageProvider")
	void testSelectStudentListWithPage(int page) throws Exception {
		// given
		final int limit = 10;
		// when
		Map<String, Object> params = new HashMap<>();
		params.put("page", page);
		params.put("limit", limit);
		List<Student> students = studentService.selectStudentList(params);
		System.out.println(students);
		// then
		assertThat(students).size().isLessThanOrEqualTo(limit);
	}

	/**
	 * 1 ~ 마지막 페이지까지만 제공하는 @MethodSource
	 * @return
	 */
	static Stream<Arguments> pageProvider() {
		final int limit = 10;
		// when
		int totalCount = studentService.getTotalCount();
		int lastPage = (int) Math.ceil((double) totalCount / limit);
		List<Integer> pages = new ArrayList<>();
		for(int i = 1; i <= lastPage; i++)
			pages.add(i);
		return pages.stream().map((page) -> Arguments.of(page));
	}
	
}