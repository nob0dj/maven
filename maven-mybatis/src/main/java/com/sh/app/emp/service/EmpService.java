package com.sh.app.emp.service;

import java.util.List;
import java.util.Map;

public interface EmpService {

	List<Map<String, Object>> selectEmpList();

	List<Map<String, Object>> search1(Map<String, Object> params);

	List<Map<String, Object>> search2(Map<String, Object> params);

	List<Map<String, Object>> search3(Map<String, Object> params);

	List<Map<String, Object>> selectEmpList(int cPage, int numPerPage);

	int selectTotalCount();

	List<Map<String, Object>> selectJobList();

	List<Map<String, Object>> selectDeptList();

	int search3Count(Map<String, Object> params);

	Map<String, Object> selectOneEmp(String empId);
	
	int updateEmp(Map<String, Object> params);

	List<Map<String, Object>> search1Paging(Map<String, Object> params, int offset, int limit);

	int search1TotalContents(Map<String, Object> params);

}
