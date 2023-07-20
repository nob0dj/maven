package com.sh.app.student.service;

import java.util.List;
import java.util.Map;

import com.sh.app.student.entity.Student;

public interface StudentService {

	int insertStudent(Student student);

	int insertStudentMap(Map<String, Object> studentMap);

	int selectTotalContent();

	int updateStudent(Student student);
	
	int updateStudent(Map<String, Object> map);

	int deleteStudent(int no);

	Map<String, Object> selectOneStudentMap(int no);

	Student selectOneStudent(int no);

	List<Student> selectStudentList();

	List<Map<String, Object>> selectStudentMapList();

	Map<Integer, Object> selectStudentMapKey();

	

}
