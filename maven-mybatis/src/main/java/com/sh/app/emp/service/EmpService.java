package com.sh.app.emp.service;

import java.util.List;
import java.util.Map;

public interface EmpService {

	List<Map<String, Object>> selectEmpList();

	List<Map<String, Object>> search1(Map<String, Object> map);

	List<Map<String, Object>> search2(Map<String, Object> map);

	List<Map<String, Object>> search3(Map<String, Object> map);

	List<Map<String, Object>> selectEmpList(int cPage, int numPerPage);

	int selectTotalContents();

	int search3Count(Map<String, Object> map);

	List<Map<String, Object>> search1Paging(Map<String, Object> map, int cPage, int numPerPage);

	int search1TotalContents(Map<String, Object> map);

	Map<String, Object> selectOneEmp(String empId);

	
	//동적쿼리 set, where, trim테스트
	List<String> selectEmpIdList();

	List<String> selectSalLevelList();

	List<String> selectJobCodeList();

	List<String> selectDeptCodeList();

	int updateEmp(Map<String, Object> param);

}
