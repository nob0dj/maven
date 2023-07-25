package com.sh.app.emp.service;

import static com.sh.app.common.SqlSessionTemplate.getSqlSession;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.sh.app.common.SqlSessionTemplate;
import com.sh.app.emp.repository.EmpRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EmpServiceImpl implements EmpService {
	private final EmpRepository empRepository;

	@Override
	public List<Map<String, Object>> selectEmpList() {
		SqlSession session = getSqlSession();
		List<Map<String, Object>> list = empRepository.selectEmpList(session);
		session.close();
		return list;
	}

	@Override
	public List<Map<String, Object>> search1(Map<String, Object> paramss) {
		SqlSession session = getSqlSession();
		List<Map<String, Object>> list = empRepository.search1(session, paramss);
		session.close();
		return list;
	}

	@Override
	public List<Map<String, Object>> search2(Map<String, Object> paramss) {
		SqlSession session = getSqlSession();
		List<Map<String, Object>> list = empRepository.search2(session, paramss);
		session.close();
		return list;
	}

	@Override
	public List<Map<String, Object>> search3(Map<String, Object> paramss) {
		SqlSession session = getSqlSession();
		List<Map<String, Object>> list = empRepository.search3(session, paramss);
		session.close();
		return list;
	}

	@Override
	public int search3Count(Map<String, Object> paramss) {
		SqlSession session = SqlSessionTemplate.getSqlSession();
		int result = empRepository.search3Count(session, paramss);
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
	public int selectTotalCount() {
		SqlSession session = getSqlSession();
		int totalContents = empRepository.selectTotalCount(session);
		session.close();
		return totalContents;
	}

	@Override
	public List<Map<String, Object>> search1Paging(Map<String, Object> paramss, int cPage, int numPerPage) {
		SqlSession session = getSqlSession();
		List<Map<String, Object>> list = empRepository.search1Paging(session, paramss, cPage, numPerPage);
		session.close();
		return list;
	}

	@Override
	public int search1TotalContents(Map<String, Object> paramss) {
		SqlSession session = getSqlSession();
		int totalContents = empRepository.search1TotalContents(session, paramss);
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
	public int updateEmp(Map<String, Object> params) {
		SqlSession session = getSqlSession();
		int result = 0;
		try {
			result = empRepository.updateEmp(session, params);
			session.commit();
		} catch(Exception e) {
			session.rollback();
			throw e;
		} finally {			
			session.close();
		}
		return result;
	}

	@Override
	public List<Map<String, Object>> selectJobList() {
		SqlSession session = getSqlSession();
		List<Map<String, Object>> jobList = empRepository.selectJobList(session);
		session.close();
		return jobList;
	}

	@Override
	public List<Map<String, Object>> selectDeptList() {
		SqlSession session = getSqlSession();
		List<Map<String, Object>> deptList = empRepository.selectDeptList(session);
		session.close();
		return deptList;
	}
}
