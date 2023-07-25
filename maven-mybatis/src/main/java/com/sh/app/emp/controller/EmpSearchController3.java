package com.sh.app.emp.controller;

import java.io.IOException;
import java.util.Arrays;
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
public class EmpSearchController3 extends AbstractController {

	private final EmpService empService;

	@Override
	public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1.사용자입력값 처리
		String[] jobCodes = request.getParameterValues("jobCode"); // String[]로 처리
		String[] deptCodes = request.getParameterValues("deptCode");
		List<String> deptList = deptCodes != null ? Arrays.asList(deptCodes) : null; // List<T>로 처리

		Map<String, Object> params = new HashMap<>();
		params.put("jobCodes", jobCodes);
		params.put("deptList", deptList);

		// 2. 업무로직
		List<Map<String, Object>> list = empService.search3(params);
		log.debug("list = ", list);

		// 폼에 나열할 직급목록 조회
		List<Map<String, Object>> jobCodeList = empService.selectJobList();
		List<Map<String, Object>> deptCodeList = empService.selectDeptList();
		log.debug("jobList = ", jobCodeList);
		log.debug("deptCodeList = ", deptCodeList);

		request.setAttribute("jobCodeList", jobCodeList);
		request.setAttribute("deptCodeList", deptCodeList);
		request.setAttribute("list", list);

		return "emp/search3";
	}
}