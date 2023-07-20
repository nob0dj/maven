package com.sh.app.student.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sh.app.common.AbstractController;
import com.sh.app.student.entity.Student;
import com.sh.app.student.service.StudentService;
import com.sh.app.student.service.StudentServiceImpl;

public class StudentSelectOneController extends AbstractController {
	private StudentService studentService = new StudentServiceImpl();

	@Override
	public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1.사용자입력값
		int no = 0;
		try {
			no = Integer.parseInt(request.getParameter("no"));
		} catch (NumberFormatException ignored) {

		}

		// 2.업무로직
		// a.전체학생수 조회
		int totalStudents = studentService.selectTotalContent();
		System.out.println("totalStudents@controller = " + totalStudents);

		// b.학생 1명 조회 VO
		if (no != 0) {
			Student student = studentService.selectOneStudent(no);
			System.out.println("student@controller = " + student);
			request.setAttribute("student", student);
		}

		// 3.jsp에 html작성 위임.
		request.setAttribute("count", totalStudents);

		return "student/selectOne";
	}

}
