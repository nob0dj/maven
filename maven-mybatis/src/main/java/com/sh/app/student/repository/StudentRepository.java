package com.sh.app.student.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.sh.app.student.entity.Student;

public interface StudentRepository {

	int insertStudent(SqlSession session, Student student);

	int insertStudentMap(SqlSession session, Map<String, Object> studentMap);

	int selectTotalContent(SqlSession session);

	Student selectOneStudent(SqlSession session, int no);

	int updateStudent(SqlSession session, Map<String, Object> map);

	int deleteStudent(SqlSession session, int no);

	Map<String, Object> selectOneStudentMap(SqlSession session, int no);

	List<Student> selectStudentList(SqlSession session);

	List<Map<String, Object>> selectStudentMapList(SqlSession session);

	Map<Integer, Object> selectStudentMapKey(SqlSession session);

	int updateStudent(SqlSession session, Student student);

}
