package com.sh.app.emp.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

public class EmpRepositoryImpl implements EmpRepository {

	@Override
	public List<Map<String, Object>> selectEmpList(SqlSession session) {

		return session.selectList("emp.selectEmpList");
	}

	@Override
	public List<Map<String, Object>> search1(SqlSession session, Map<String, Object> map) {
		return session.selectList("emp.search1", map);
	}

	@Override
	public List<Map<String, Object>> search2(SqlSession session, Map<String, Object> map) {
		return session.selectList("emp.search2", map);
	}

	@Override
	public List<Map<String, Object>> search3(SqlSession session, Map<String, Object> map) {
		return session.selectList("emp.search3", map);
	}

	@Override
	public int search3Count(SqlSession session, Map<String, Object> map) {
		return session.selectOne("emp.search3Count", map);
	}

	@Override
	public List<Map<String, Object>> selectEmpList(SqlSession session, int cPage, int numPerPage) {

		// org.apache.ibatis.session.RowBounds.RowBounds(int offset, int limit)
		RowBounds rowBounds = new RowBounds((cPage - 1) * numPerPage, numPerPage);
		return session.selectList("emp.empList", null, rowBounds);
	}

	@Override
	public int selectTotalContents(SqlSession session) {
		return session.selectOne("emp.selectTotalContents");
	}

	@Override
	public List<Map<String, Object>> search1Paging(SqlSession session, Map<String, Object> map, int cPage,
			int numPerPage) {
		// org.apache.ibatis.session.RowBounds.RowBounds(int offset, int limit)
		RowBounds rowBounds = new RowBounds((cPage - 1) * numPerPage, numPerPage);
		return session.selectList("emp.search1Paging", map, rowBounds);
	}

	@Override
	public int search1TotalContents(SqlSession session, Map<String, Object> map) {
		return session.selectOne("emp.search1TotalContents", map);
	}

	@Override
	public Map<String, Object> selectOneEmp(SqlSession session, String empId) {
		return session.selectOne("emp.selectOneEmp", empId);
	}

	@Override
	public List<String> selectJobCodeList(SqlSession session) {
		return session.selectList("emp.selectJobCodeList");
	}

	@Override
	public List<String> selectSalLevelList(SqlSession session) {
		return session.selectList("emp.selectSalLevelList");
	}

	@Override
	public List<String> selectEmpIdList(SqlSession session) {
		return session.selectList("emp.selectEmpIdList");
	}

	@Override
	public List<String> selectDeptCodeList(SqlSession session) {
		return session.selectList("emp.selectDeptCodeList");
	}

	@Override
	public int updateEmp(SqlSession session, Map<String, Object> param) {
		return session.update("emp.updateEmp", param);
	}

}
