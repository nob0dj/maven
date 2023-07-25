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
	public List<Map<String, Object>> search1(SqlSession session, Map<String, Object> params) {
		return session.selectList("emp.search1", params);
	}

	@Override
	public List<Map<String, Object>> search2(SqlSession session, Map<String, Object> params) {
		return session.selectList("emp.search2", params);
	}

	@Override
	public List<Map<String, Object>> search3(SqlSession session, Map<String, Object> params) {
		return session.selectList("emp.search3", params);
	}

	@Override
	public int search3Count(SqlSession session, Map<String, Object> params) {
		return session.selectOne("emp.search3Count", params);
	}

	@Override
	public List<Map<String, Object>> selectEmpList(SqlSession session, int cPage, int numPerPage) {

		// org.apache.ibatis.session.RowBounds.RowBounds(int offset, int limit)
		RowBounds rowBounds = new RowBounds((cPage - 1) * numPerPage, numPerPage);
		return session.selectList("emp.empList", null, rowBounds);
	}

	@Override
	public int selectTotalCount(SqlSession session) {
		return session.selectOne("emp.selectTotalCount");
	}

	@Override
	public List<Map<String, Object>> search1Paging(SqlSession session, Map<String, Object> params, int cPage,
			int numPerPage) {
		// org.apache.ibatis.session.RowBounds.RowBounds(int offset, int limit)
		RowBounds rowBounds = new RowBounds((cPage - 1) * numPerPage, numPerPage);
		return session.selectList("emp.search1Paging", params, rowBounds);
	}

	@Override
	public int search1TotalContents(SqlSession session, Map<String, Object> params) {
		return session.selectOne("emp.search1TotalContents", params);
	}

	@Override
	public Map<String, Object> selectOneEmp(SqlSession session, String empId) {
		return session.selectOne("emp.selectOneEmp", empId);
	}


	@Override
	public int updateEmp(SqlSession session, Map<String, Object> param) {
		return session.update("emp.updateEmp", param);
	}

	@Override
	public List<Map<String, Object>> selectJobList(SqlSession session) {
		return session.selectList("emp.selectJobList");
	}

	@Override
	public List<Map<String, Object>> selectDeptList(SqlSession session) {
		return session.selectList("emp.selectDeptList");
	}
}
