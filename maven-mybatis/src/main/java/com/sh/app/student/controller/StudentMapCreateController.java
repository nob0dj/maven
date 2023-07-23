package com.sh.app.student.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sh.app.common.AbstractController;
import com.sh.app.student.service.StudentService;
import com.sh.app.student.service.StudentServiceImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor
@Log4j2
public class StudentMapCreateController extends AbstractController {
	private final StudentService studentService;

	@Override
	public String doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. 사용자입력값
		String name = request.getParameter("name");
		String tel = request.getParameter("tel");

		Map<String, Object> studentMap = new HashMap<>();
		studentMap.put("name", name);
		studentMap.put("tel", tel);
		log.debug("studentMap = {}", studentMap);

		// 2. 업무로직
		int result = studentService.insertStudentMap(studentMap);
		String msg = "학생 등록 성공!";

		// 3. 리다이렉트 & 사용자피드백
		request.getSession().setAttribute("msg", msg);

		return "redirect:/student/studentList.do";
	}

}
