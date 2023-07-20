package com.sh.app.common;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlSessionTemplate {

	public static SqlSession getSqlSession() {
		SqlSession session = null;
//		String resource = "/mybatis-config.xml"; 
		String resource = "mybatis-config.xml"; // JUnit 단독테스트시  
		
		try {
			InputStream is = Resources.getResourceAsStream(resource);
			
			SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
			SqlSessionFactory factory = builder.build(is);
			session = factory.openSession(false);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return session;
	}

	
}
