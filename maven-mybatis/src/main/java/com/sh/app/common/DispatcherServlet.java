package com.sh.app.common;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sh.app.common.exception.ControllerNotFoundException;
import com.sh.app.common.exception.MethodNotAllowedException;
import com.sh.app.emp.repository.EmpRepositoryImpl;
import com.sh.app.emp.service.EmpService;
import com.sh.app.emp.service.EmpServiceImpl;
import com.sh.app.student.repository.StudentRepositoryImpl;
import com.sh.app.student.service.StudentService;
import com.sh.app.student.service.StudentServiceImpl;

/**
 * .do로 끝나는 모든 요청을 처리할 대표 Servlet
 * 사용자요청을 최초 받아서 controller객체의 메소드를 대신 호출해준다.
 * controller는 AbstractController(규격)를 상속해 doGet/doPost를 override하도록 한다.
 * 
 */
@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Properties prop = new Properties();
	Map<String, AbstractController> commandMap = new HashMap<>();
	
	
	/**
	 * 1. url.properties를 읽어서 prop에 저장
	 * 2. prop내용을 가지고 urlMap에 요소를 추가
	 */
	public void init(ServletConfig config) throws ServletException {
		System.out.println("---------------- DispatcherServlet.init start ---------------");
		// 1. command-map.properties -> prop
        Properties prop = new Properties();
        String filepath = DispatcherServlet.class.getResource("/command-map.properties").getPath();
        try {
			prop.load(new FileReader(filepath));
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        // 2. prop -> commandMap (reflection api)
        StudentService studentService = new StudentServiceImpl(new StudentRepositoryImpl());
        EmpService empService = new EmpServiceImpl(new EmpRepositoryImpl());
        
        Set<String> propNames = prop.stringPropertyNames();
        try {
	        for(String url : propNames) {
	        	String className = prop.getProperty(url);
	        	Class<?> clz = Class.forName(className);
	        	AbstractController controller = null;
	        	if(url.startsWith("/student")) {
	        		Constructor<?> constructor = clz.getDeclaredConstructor(StudentService.class); // 생성자객체
		        	controller = (AbstractController) constructor.newInstance(studentService); // new XXXController()
	        	}
	        	else if(url.startsWith("/emp")) {
	        		Constructor<?> constructor = clz.getDeclaredConstructor(EmpService.class); // 생성자객체
	        		controller = (AbstractController) constructor.newInstance(empService); // new XXXController()
	        	}
	 
	        	commandMap.put(url, controller);
	        }
        } catch (Exception e) {
        	e.printStackTrace();
        }
        
        System.out.println("commandMap : " + commandMap);
		System.out.println("--------------- DispatcherServlet.init end ---------------");
	
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 사용자 요청주소 
		String uri = request.getRequestURI(); // /mybatis/student/studentEnroll.do
		int beginIndex = request.getContextPath().length();
		String url = uri.substring(beginIndex);
		
		//2. controller 호출
		AbstractController controller = commandMap.get(url);
		if(controller == null)
			throw new ControllerNotFoundException(url + "에 해당하는 controller가 없습니다.");
		
		String viewName = null;
		String method = request.getMethod();
		if("GET".equals(method)) 
			viewName = controller.doGet(request, response);
		else if("POST".equals(method))
			viewName = controller.doPost(request, response);
		else 
			throw new MethodNotAllowedException(method);
	
		//3. 응답처리 : forwarding | redirect | pass
		if(viewName != null) {
			//redirect
			if(viewName.startsWith("redirect:")) {
				String location = request.getContextPath()
								+ viewName.replace("redirect:", "");
				response.sendRedirect(location);
			}
			//forwarding
			else {
				String prefix = "/WEB-INF/views/";
				String suffix = ".jsp";
				viewName = prefix + viewName + suffix;
				request.getRequestDispatcher(viewName)
					   .forward(request, response);
			}
			
		}
		else {
			//비동기 json응답을 직접 처리한 경우는 아무것도 하지 않는다.
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
