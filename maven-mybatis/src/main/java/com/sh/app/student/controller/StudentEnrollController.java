package com.sh.app.student.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sh.app.common.AbstractController;
import com.sh.app.student.entity.Student;
import com.sh.app.student.service.StudentService;
import com.sh.app.student.service.StudentServiceImpl;

public class StudentEnrollController extends AbstractController {

	private StudentService studentService = new StudentServiceImpl();

	/**
	 * 학생 등록페이지 요청
	 */
	@Override
	public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// jsp포워딩용
		return "student/studentForm";
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
		System.out.println("student@controller = " + stdt);

		// 2. 업무로직
		int result = studentService.insertStudent(stdt);
		String msg = (result > 0) ? "학생 등록 성공!" : "학생 등록 실패!";
		System.out.println(msg);

		// 3. 리다이렉트 및 사용자피드백
		request.getSession().setAttribute("msg", msg);

		return "redirect:/student/studentEnroll.do";
	}

}
