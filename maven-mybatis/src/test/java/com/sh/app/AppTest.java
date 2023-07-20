package com.sh.app;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.sh.app.common.SqlSessionTemplate;

import lombok.extern.log4j.Log4j2;


@Log4j2
public class AppTest {
	
	@BeforeAll // JUnit 4의 @BeforeClass
    static void setUpBeforeClass() {
        log.debug("@BeforeAll");
    }
    @BeforeEach // JUnit 4의 @Before
    void setUp1() {
        log.debug("@BeforeEach");
    }
    @AfterEach // JUnit 4의 @After
    void tearDown() {
        log.debug("@AfterEach");
    }

    @AfterAll // JUnit 4의 @AfterClass
    static void tearDownAfterClass() {
        log.debug("@AfterAll");
    }

    @Test
    @Disabled // 테스트 비활성화
    public void nothing() {
    	fail("Not yet implemented");
    }
    
    @DisplayName("SqlSession 생성")
    @Test
    void testSqlSessionIsNotNull () throws Exception {
    	SqlSession session = SqlSessionTemplate.getSqlSession();
    	assertNotNull(session);
    	org.assertj.core.api.Assertions.assertThat(session).isNotNull();
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
