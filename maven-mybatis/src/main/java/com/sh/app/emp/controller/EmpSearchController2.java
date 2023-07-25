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
public class EmpSearchController2 extends AbstractController {

	private final EmpService empService;
	
	@Override
	public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자 입력값처리
		String gender = request.getParameter("gender"); // "남" null
		int salary = 0;
		try {
			salary = Integer.parseInt(request.getParameter("salary")); // "3000000" "" null			
		} catch(NumberFormatException e) {}
		
		String salaryCompare = request.getParameter("salaryCompare"); // "ge" "le" null
		
		Map<String, Object> params = new HashMap<>();
		params.put("gender", gender);
		params.put("salary", salary);
		params.put("salaryCompare", salaryCompare);
		
		log.debug("params = {}", params);
		
		// 2. 검색
		List<Map<String, Object>> list = empService.search2(params);
		log.debug("list = {}", list);
		request.setAttribute("list", list);
		return "emp/search2";
	}
}