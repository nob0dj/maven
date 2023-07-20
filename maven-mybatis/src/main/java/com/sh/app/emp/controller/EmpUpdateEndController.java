package com.kh.emp.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.common.AbstractController;
import com.kh.emp.model.service.EmpService;
import com.kh.emp.model.service.EmpServiceImpl;

public class EmpUpdateEndController extends AbstractController {
	
	private EmpService empService = new EmpServiceImpl();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//파라미터값 가져오기
		String empId = request.getParameter("empId");
		String salLevel = request.getParameter("salLevel");
		String jobCode = request.getParameter("jobCode");
		String deptCode = request.getParameter("deptCode");
		
		Map<String, String> param = new HashMap<>();
		param.put("empId", empId);
		param.put("salLevel", salLevel);
		param.put("jobCode", jobCode);
		param.put("deptCode", deptCode);
		
		System.out.println("param="+param);
		
		int result = empService.updateEmp(param);
		
		if(result == 0) throw new IllegalStateException("사원정보 수정오류");
		
		setView("/emp/empUpdate.do?empId="+empId);
		setRedirect(true);
	}

}
