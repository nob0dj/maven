package com.sh.app.student.service;

import java.util.List;
import java.util.Map;

import com.sh.app.student.entity.Student;

public interface StudentService {

	int insertStudent(Student student);

	int insertStudentMap(Map<String, Object> studentMap);

	int updateStudent(Student student);
	
	int updateStudent(Map<String, Object> map);

	int deleteStudent(int id);

	Map<String, Object> selectOneStudentMap(int id);

	Student selectOneStudent(int id);

	List<Student> selectStudentList();

	List<Map<String, Object>> selectStudentMapList();

	Map<Integer, Object> selectStudentMapKey();

	int getTotalCount();

	List<Student> selectStudentList(Map<String, Object> params);

	

}
