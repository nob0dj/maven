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
import com.sh.app.student.service.StudentServiceImpl;

public class StudentSelectListController extends AbstractController {

	private StudentService studentService = new StudentServiceImpl();

	@Override
	public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1.업무로직
		List<Student> list = studentService.selectStudentList();
		System.out.println("list@controller = " + list);

		List<Map<String, Object>> mapList = studentService.selectStudentMapList();
		System.out.println("mapList@controller = " + mapList);

		Map<Integer, Object> map = studentService.selectStudentMapKey();
		System.out.println("map@controller = " + map);

		// 2.jsp위임
		request.setAttribute("list", list);
		request.setAttribute("mapList", mapList);
		request.setAttribute("map", map);
		request.setAttribute("mapKeySet", new TreeSet<Integer>(map.keySet()));
		return "student/selectList";
	}

}
