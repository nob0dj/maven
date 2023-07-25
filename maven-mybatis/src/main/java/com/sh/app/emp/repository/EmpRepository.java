package com.sh.app.emp.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

public interface EmpRepository {

	List<Map<String, Object>> selectEmpList(SqlSession session);

	List<Map<String, Object>> search1(SqlSession session, Map<String, Object> params);

	List<Map<String, Object>> search2(SqlSession session, Map<String, Object> params);

	List<Map<String, Object>> search3(SqlSession session, Map<String, Object> params);

	List<Map<String, Object>> selectEmpList(SqlSession session, int cPage, int numPerPage);
	
	Map<String, Object> selectOneEmp(SqlSession session, String empId);

	int updateEmp(SqlSession session, Map<String, Object> param);

	List<Map<String, Object>> selectJobList(SqlSession session);

	List<Map<String, Object>> selectDeptList(SqlSession session);
	
	int selectTotalCount(SqlSession session);

	int search3Count(SqlSession session, Map<String, Object> params);

	List<Map<String, Object>> search1Paging(SqlSession session, Map<String, Object> params, int cPage, int numPerPage);

	int search1TotalContents(SqlSession session, Map<String, Object> params);

	

}
