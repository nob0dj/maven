package com.sh.app.student.controller;

import java.io.IOException;

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
public class StudentCreateController extends AbstractController {

	private final StudentService studentService;

	/**
	 * 학생 등록페이지 요청
	 */
	@Override
	public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// jsp포워딩용
		return "student/studentCreate";
	}

	/**
	 * 학생정보 DB 등록
	 */
	@Override
	public String doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. 사용자 입력
		String name = request.getParameter("name");
		String tel = request.getParameter("tel");

		Student stdt = new Student();
		stdt.setName(name);
		stdt.setTel(tel);
		log.debug("student = {}", stdt);

		// 2. 업무로직
		int result = studentService.insertStudent(stdt);
		String msg = "학생 등록 성공!";

		// 3. 리다이렉트 및 사용자피드백
		request.getSession().setAttribute("msg", msg);

		return "redirect:/student/studentCreate.do";
	}

}
