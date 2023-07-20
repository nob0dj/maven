package com.sh.app.student.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.sh.app.common.AbstractController;
import com.sh.app.student.service.StudentService;
import com.sh.app.student.service.StudentServiceImpl;

public class StudentMapSelectOneController extends AbstractController {

	private StudentService studentService = new StudentServiceImpl();

	@Override
	public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1.사용자입력값 no
		int no = Integer.parseInt(request.getParameter("no"));
		// 2.업무로직 Map
		Map<String, Object> student = studentService.selectOneStudentMap(no);
		System.out.println("student@controller = " + student);

		// 3.응답처리 : gson을 이용해서 json작성
		response.setContentType("application/json; charset=utf-8");
		new Gson().toJson(student, response.getWriter());

		return null;
	}

}
