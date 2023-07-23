package com.sh.app.student.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sh.app.common.AbstractController;
import com.sh.app.student.entity.Student;
import com.sh.app.student.service.StudentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor
@Log4j2
public class StudentListController extends AbstractController {

	private final StudentService studentService;

	@Override
	public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자입력값 처리
		int page = 1;
		try {
			page = Integer.parseInt(request.getParameter("page"));
		} catch (NumberFormatException e) {
			// TODO: handle exception
		}
		int limit = 10;
		Map<String, Object> params = Map.of("page", page, "limit", limit);
		
		// 2. 업무로직
//		List<Student> students = studentService.selectStudentList();
		List<Student> students = studentService.selectStudentList(params);
		List<Map<String, Object>> studentMaps = studentService.selectStudentMapList();
		Map<Integer, Object> studentKeyMap = studentService.selectStudentMapKey();
		log.debug("students = {}", students);
		log.debug("studentMaps = {}", studentMaps);
		log.debug("studentKeyMap = {}", students);
		
		// 2.jsp위임
		request.setAttribute("students", students);
		request.setAttribute("studentMaps", studentMaps);
		request.setAttribute("studentKeyMap", studentKeyMap);
		request.setAttribute("studentKeySet", new TreeSet<Integer>(studentKeyMap.keySet()));
		return "student/studentList";
	}

}
