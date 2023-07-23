package com.sh.app.student.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.sh.app.student.entity.Student;

public interface StudentRepository {

	int insertStudent(SqlSession session, Student student);

	int insertStudentMap(SqlSession session, Map<String, Object> studentMap);

	int updateStudent(SqlSession session, Student student);
	
	int updateStudent(SqlSession session, Map<String, Object> map);

	int deleteStudent(SqlSession session, int id);

	Student selectOneStudent(SqlSession session, int id);
	
	Map<String, Object> selectOneStudentMap(SqlSession session, int id);

	List<Student> selectStudentList(SqlSession session);

	List<Map<String, Object>> selectStudentMapList(SqlSession session);

	Map<Integer, Object> selectStudentMapKey(SqlSession session);

	int getTotalCount(SqlSession session);

	List<Student> selectStudentList(SqlSession session, Map<String, Object> params);

}
