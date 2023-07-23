package com.sh.app.student.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
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
	public Student selectOneStudent(SqlSession session, int id) {
		return session.selectOne("student.selectOneStudent", id);
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
	public int deleteStudent(SqlSession session, int id) {
		return session.delete("student.deleteStudent", id);
	}

	@Override
	public Map<String, Object> selectOneStudentMap(SqlSession session, int id) {
		return session.selectOne("student.selectOneStudentMap", id);
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
		return session.selectMap("student.selectStudentMapKey", "id");
	}

	@Override
	public int getTotalCount(SqlSession session) {
		return session.selectOne("student.getTotalCount");
	}
	
	@Override
	public List<Student> selectStudentList(SqlSession session, Map<String, Object> params) {
		int page = (int) params.get("page");
		int limit = (int) params.get("limit");
		
		int offset = (page - 1) * limit; //  page=1 -> offset=0, page=2 -> offset=10, page=3 -> offset=20, ...
		RowBounds rowBounds = new RowBounds(offset, limit);
		return session.selectList("student.selectStudentList", null, rowBounds);
	}
	
	
	

}
