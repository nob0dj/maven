package com.sh.app.student.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.sh.app.student.entity.Student;

public class StudentRepositoryImpl implements StudentRepository {

	@Override
	public int insertStudent(SqlSession session, Student student) {
		return session.insert("student.insertStudent", student);
	}

	@Override
	public int insertStudentMap(SqlSession session, Map<String, Object> studentMap) {
		return session.insert("student.insertStudentMap", studentMap);
	}

	@Override
	public int selectTotalContent(SqlSession session) {
		return session.selectOne("student.selectTotalContent");
	}

	@Override
	public Student selectOneStudent(SqlSession session, int no) {
		return session.selectOne("student.selectOneStudent", no);
	}
	
	@Override
	public int updateStudent(SqlSession session, Student student) {
		return session.update("student.updateStudent", student);
	}

	@Override
	public int updateStudent(SqlSession session, Map<String, Object> map) {
		return session.update("student.updateStudent", map);
	}

	@Override
	public int deleteStudent(SqlSession session, int no) {
		return session.delete("student.deleteStudent", no);
	}

	@Override
	public Map<String, Object> selectOneStudentMap(SqlSession session, int no) {
		return session.selectOne("student.selectStudentMapOne", no);
	}

	@Override
	public List<Student> selectStudentList(SqlSession session) {
		return session.selectList("student.selectStudentList");
	}

	@Override
	public List<Map<String, Object>> selectStudentMapList(SqlSession session) {
		return session.selectList("student.selectStudentMapList");
	}

	@Override
	public Map<Integer, Object> selectStudentMapKey(SqlSession session) {
		//두번째 인자는 key로 사용할 property명 지정
		return session.selectMap("student.selectStudentMapKey", "no");
	}

	
	
	
	

}
