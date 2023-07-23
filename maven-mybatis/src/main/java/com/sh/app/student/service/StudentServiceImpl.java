package com.sh.app.student.service;

import static com.sh.app.common.SqlSessionTemplate.getSqlSession;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.sh.app.student.entity.Student;
import com.sh.app.student.repository.StudentRepository;
import com.sh.app.student.repository.StudentRepositoryImpl;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

	private final StudentRepository studentRepository;

	@Override
	public int insertStudent(Student student) {
		SqlSession session = getSqlSession();
		int result = 0;
		try {
			result = studentRepository.insertStudent(session, student);
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
			result = studentRepository.insertStudentMap(session, studentMap);
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
	public Student selectOneStudent(int id) {
		SqlSession session = getSqlSession();
		Student student = studentRepository.selectOneStudent(session, id);
		session.close();
		return student;
	}
	
	@Override
	public int updateStudent(Student student) {
		SqlSession session = getSqlSession();
		int result = 0;
		try {
			result = studentRepository.updateStudent(session, student);
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
			result = studentRepository.updateStudent(session, map);
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
	public int deleteStudent(int id) {
		SqlSession session = getSqlSession();
		int result = 0;
		try {
			//dml실행구문
			result = studentRepository.deleteStudent(session, id);
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
	public Map<String, Object> selectOneStudentMap(int id) {
		SqlSession session = getSqlSession();
		Map<String, Object> student = studentRepository.selectOneStudentMap(session, id);
		session.close();
		return student;
	}

	@Override
	public List<Student> selectStudentList() {
		SqlSession session = getSqlSession();
		List<Student> list = studentRepository.selectStudentList(session);
		session.close();
		return list;
	}

	@Override
	public List<Map<String, Object>> selectStudentMapList() {
		SqlSession session = getSqlSession();
		List<Map<String, Object>> mapList = studentRepository.selectStudentMapList(session);
		session.close();
		return mapList;
	}

	@Override
	public Map<Integer, Object> selectStudentMapKey() {
		SqlSession session = getSqlSession();
		Map<Integer, Object> map = studentRepository.selectStudentMapKey(session);
		session.close();
		return map;
	}

	@Override
	public int getTotalCount() {
		SqlSession session = getSqlSession();
		int totalCount = studentRepository.getTotalCount(session);
		session.close();
		return totalCount;
	}
	
	@Override
	public List<Student> selectStudentList(Map<String, Object> params) {
		SqlSession session= getSqlSession();
		List<Student> students = studentRepository.selectStudentList(session, params);
		session.close();
		return students;
	}
	
}
