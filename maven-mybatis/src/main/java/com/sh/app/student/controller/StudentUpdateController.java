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

public class StudentUpdateController extends AbstractController {

	private StudentService studentService = new StudentServiceImpl();

	@Override
	public String doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1.사용자입력값 처리
		int no = Integer.parseInt(request.getParameter("no"));
		String name = request.getParameter("name");
		String tel = request.getParameter("tel");

		Map<String, Object> map = new HashMap<>();
		map.put("no", no);
		map.put("name", name);
		map.put("tel", tel);
		System.out.println("map@controller = " + map);

		// 2.업무로직
		int result = studentService.updateStudent(map);
		String msg = result > 0 ? "학생 정보 수정 성공!" : "학생 정보 수정 실패!";

		// 3.사용자피드백 & 리다이렉트
		request.getSession().setAttribute("msg", msg);

		return "redirect:/student/selectStudent.do?no=" + no;
	}

}
