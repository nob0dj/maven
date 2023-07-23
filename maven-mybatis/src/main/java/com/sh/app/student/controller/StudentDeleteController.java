package com.sh.app.student.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sh.app.common.AbstractController;
import com.sh.app.student.service.StudentService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class StudentDeleteController extends AbstractController {
	private final StudentService studentService;

	@Override
	public String doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1.사용자입력값 처리
		int id = Integer.parseInt(request.getParameter("id"));

		// 2.업무로직
		int result = studentService.deleteStudent(id);
		String msg = "학생정보 삭제 성공!";

		// 3.사용자피드백 & 리다이렉트
		request.getSession().setAttribute("msg", msg);

		return "redirect:/student/studentList.do";
	}
}
