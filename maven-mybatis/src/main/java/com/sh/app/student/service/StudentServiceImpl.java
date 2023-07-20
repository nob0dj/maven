package com.sh.app.student.service;

import static com.sh.app.common.SqlSessionTemplate.getSqlSession;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.sh.app.student.entity.Student;
import com.sh.app.student.repository.StudentRepository;
import com.sh.app.student.repository.StudentRepositoryImpl;

public class StudentServiceImpl implements StudentService {

	private StudentRepository studentDao = new StudentRepositoryImpl();

	@Override
	public int insertStudent(Student student) {
		SqlSession session = getSqlSession();
		int result = 0;
		try {
			result = studentDao.insertStudent(session, student);
			session.commit();
		} catch (Exception e) {
			session.rollback();
			throw e;
		} finally {
			session.close();
		}
		return result;
	}

	
	@Override
	public int insertStudentMap(Map<String, Object> studentMap) {
		SqlSession session = getSqlSession();
		int result = 0;
		try {
			result = studentDao.insertStudentMap(session, studentMap);
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
	public int selectTotalContent() {
		SqlSession session = getSqlSession();
		int totalContent = studentDao.selectTotalContent(session);
		session.close();
		return totalContent;
	}

	@Override
	public Student selectOneStudent(int no) {
		SqlSession session = getSqlSession();
		Student student = studentDao.selectOneStudent(session, no);
		session.close();
		return student;
	}
	
	@Override
	public int updateStudent(Student student) {
		SqlSession session = getSqlSession();
		int result = 0;
		try {
			result = studentDao.updateStudent(session, student);
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
	public int updateStudent(Map<String, Object> map) {
		SqlSession session = getSqlSession();
		int result = 0;
		try {
			//dml실행구문
			result = studentDao.updateStudent(session, map);
			session.commit();
		} catch (Exception e) {
			session.rollback();
			throw e;
		} finally {
			session.close();
		}
		return result;
	}

	@Override
	public int deleteStudent(int no) {
		SqlSession session = getSqlSession();
		int result = 0;
		try {
			//dml실행구문
			result = studentDao.deleteStudent(session, no);
			session.commit();
		} catch (Exception e) {
			session.rollback();
			throw e;
		} finally {
			session.close();
		}
		return result;
	}

	@Override
	public Map<String, Object> selectOneStudentMap(int no) {
		SqlSession session = getSqlSession();
		Map<String, Object> student = studentDao.selectOneStudentMap(session, no);
		session.close();
		return student;
	}

	@Override
	public List<Student> selectStudentList() {
		SqlSession session = getSqlSession();
		List<Student> list = studentDao.selectStudentList(session);
		session.close();
		return list;
	}

	@Override
	public List<Map<String, Object>> selectStudentMapList() {
		SqlSession session = getSqlSession();
		List<Map<String, Object>> mapList = studentDao.selectStudentMapList(session);
		session.close();
		return mapList;
	}

	@Override
	public Map<Integer, Object> selectStudentMapKey() {
		SqlSession session = getSqlSession();
		Map<Integer, Object> map = studentDao.selectStudentMapKey(session);
		session.close();
		return map;
	}

	
	
}
