package com.sh.app.emp.service;

import static com.sh.app.common.SqlSessionTemplate.getSqlSession;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.sh.app.common.SqlSessionTemplate;
import com.sh.app.emp.repository.EmpRepository;
import com.sh.app.emp.repository.EmpRepositoryImpl;

public class EmpServiceImpl implements EmpService {
	private EmpRepository empRepository = new EmpRepositoryImpl();

	@Override
	public List<Map<String, Object>> selectEmpList() {
		SqlSession session = getSqlSession();
		List<Map<String, Object>> list = empRepository.selectEmpList(session);
		session.close();
		return list;
	}

	@Override
	public List<Map<String, Object>> search1(Map<String, Object> map) {
		SqlSession session = getSqlSession();
		List<Map<String, Object>> list = empRepository.search1(session, map);
		session.close();
		return list;
	}

	@Override
	public List<Map<String, Object>> search2(Map<String, Object> map) {
		SqlSession session = getSqlSession();
		List<Map<String, Object>> list = empRepository.search2(session, map);
		session.close();
		return list;
	}

	@Override
	public List<Map<String, Object>> search3(Map<String, Object> map) {
		SqlSession session = getSqlSession();
		List<Map<String, Object>> list = empRepository.search3(session, map);
		session.close();
		return list;
	}

	@Override
	public int search3Count(Map<String, Object> map) {
		SqlSession session = SqlSessionTemplate.getSqlSession();
		int result = empRepository.search3Count(session, map);
		session.close();
		return result;
	}

	@Override
	public List<Map<String, Object>> selectEmpList(int cPage, int numPerPage) {
		SqlSession session = getSqlSession();
		List<Map<String, Object>> list = empRepository.selectEmpList(session, cPage, numPerPage);
		session.close();
		return list;
	}

	@Override
	public int selectTotalContents() {
		SqlSession session = getSqlSession();
		int totalContents = empRepository.selectTotalContents(session);
		session.close();
		return totalContents;
	}

	@Override
	public List<Map<String, Object>> search1Paging(Map<String, Object> map, int cPage, int numPerPage) {
		SqlSession session = getSqlSession();
		List<Map<String, Object>> list = empRepository.search1Paging(session, map, cPage, numPerPage);
		session.close();
		return list;
	}

	@Override
	public int search1TotalContents(Map<String, Object> map) {
		SqlSession session = getSqlSession();
		int totalContents = empRepository.search1TotalContents(session, map);
		session.close();
		return totalContents;
	}

	@Override
	public Map<String, Object> selectOneEmp(String empId) {
		SqlSession session = getSqlSession();
		Map<String, Object> emp = empRepository.selectOneEmp(session, empId);
		session.close();
		return emp;
	}

	@Override
	public List<String> selectEmpIdList() {
		SqlSession session = getSqlSession();
		List<String> list = empRepository.selectEmpIdList(session);
		session.close();
		return list;
	}

	@Override
	public List<String> selectSalLevelList() {
		SqlSession session = getSqlSession();
		List<String> list = empRepository.selectSalLevelList(session);
		session.close();
		return list;
	}

	@Override
	public List<String> selectJobCodeList() {
		SqlSession session = getSqlSession();
		List<String> list = empRepository.selectJobCodeList(session);
		session.close();
		return list;
	}

	@Override
	public List<String> selectDeptCodeList() {
		SqlSession session = getSqlSession();
		List<String> list = empRepository.selectDeptCodeList(session);
		session.close();
		return list;
	}

	@Override
	public int updateEmp(Map<String, Object> param) {
		SqlSession session = getSqlSession();
		int result = empRepository.updateEmp(session, param);
		if (result > 0)
			session.commit();
		else
			session.rollback();
		session.close();
		return result;
	}
}
