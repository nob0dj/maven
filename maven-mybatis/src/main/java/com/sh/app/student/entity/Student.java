package com.sh.app.student.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Student {

	private int id;
	private String name;
	private String tel;
	private LocalDateTime createdAt;
	
}
