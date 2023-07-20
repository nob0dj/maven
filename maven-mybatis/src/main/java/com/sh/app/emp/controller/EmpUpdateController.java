package com.kh.emp.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.common.AbstractController;
import com.kh.emp.model.service.EmpService;
import com.kh.emp.model.service.EmpServiceImpl;

public class EmpUpdateController extends AbstractController {
	
	private EmpService empService = new EmpServiceImpl();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//파라미터값 가져오기
		String empId = request.getParameter("empId");
		Map<String, String> emp = null;
		
		//EMP_ID 목록 가져오기
		List<String> empIdList = empService.selectEmpIdList();
		request.setAttribute("empIdList", empIdList);

		//조회한 empId가 있는 경우, 수정폼을 위한 목록준비
		if(empId!=null){
			emp = empService.selectOneEmp(empId);
			
			//KH.EMPLOYEE.SAL_LEVEL 목록 가져오기
			List<String> salLevelList = empService.selectSalLevelList();
			//KH.JOB.JOB_CODE 목록 가져오기
			List<String> jobCodeList = empService.selectJobCodeList();
			//KH.DEPARTMENT.DEPT_ID 목록 가져오기
			List<String> deptCodeList = empService.selectDeptCodeList();
			
			request.setAttribute("emp", emp);
			request.setAttribute("salLevelList", salLevelList);
			request.setAttribute("jobCodeList", jobCodeList);
			request.setAttribute("deptCodeList", deptCodeList);
		}
			
		setView("/WEB-INF/views/emp/empUpdate.jsp");
	}

}
