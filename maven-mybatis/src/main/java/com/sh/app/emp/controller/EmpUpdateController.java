package com.sh.app.emp.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sh.app.common.AbstractController;
import com.sh.app.emp.service.EmpService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor
@Log4j2
public class EmpUpdateController extends AbstractController {

	private final EmpService empService;
	
	@Override
	public String doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. 사용자입력값처리
		String empId = request.getParameter("empId");
		String jobCode = request.getParameter("jobCode");
		String deptCode = request.getParameter("deptCode");
		
		Map<String, Object> param = new HashMap<>();
		param.put("empId", empId);
		param.put("jobCode", jobCode);
		param.put("deptCode", deptCode);
		log.debug("param = {}", param);
		
		// 2. 업무로직
		int result = empService.updateEmp(param);
		
		return "redirect:/emp/updateEmp.do?empId=" + empId;
	}
	
	@Override
	public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자 입력값 처리
		String empId = request.getParameter("empId");
		log.debug("empId = {}", empId);
		
		// 2. 업무로직
		Map<String, Object> emp = empService.selectOneEmp(empId);
		log.debug("emp = {}", emp);
		
		List<Map<String, Object>> jobList = empService.selectJobList();
		List<Map<String, Object>> deptList = empService.selectDeptList();
		
		// 3. jsp데이터 전달
		request.setAttribute("emp", emp);
		request.setAttribute("jobList", jobList);
		request.setAttribute("deptList", deptList);
		
		return "emp/updateEmp";
	}
}