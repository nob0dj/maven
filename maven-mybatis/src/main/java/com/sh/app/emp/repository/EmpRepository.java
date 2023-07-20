package com.sh.app.emp.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

public interface EmpRepository {

	List<Map<String, Object>> selectEmpList(SqlSession session);

	List<Map<String, Object>> search1(SqlSession session, Map<String, Object> map);

	List<Map<String, Object>> search2(SqlSession session, Map<String, Object> map);

	List<Map<String, Object>> search3(SqlSession session, Map<String, Object> map);

	List<Map<String, Object>> selectEmpList(SqlSession session, int cPage, int numPerPage);

	int selectTotalContents(SqlSession session);

	int search3Count(SqlSession session, Map<String, Object> map);

	List<Map<String, Object>> search1Paging(SqlSession session, Map<String, Object> map, int cPage, int numPerPage);

	int search1TotalContents(SqlSession session, Map<String, Object> map);

	List<String> selectJobCodeList(SqlSession session);

	List<String> selectSalLevelList(SqlSession session);

	List<String> selectEmpIdList(SqlSession session);

	Map<String, Object> selectOneEmp(SqlSession session, String empId);

	List<String> selectDeptCodeList(SqlSession session);

	int updateEmp(SqlSession session, Map<String, Object> param);

}
