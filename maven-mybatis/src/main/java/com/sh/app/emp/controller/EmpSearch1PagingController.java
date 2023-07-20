package com.kh.emp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.common.AbstractController;
import com.kh.emp.model.service.EmpService;
import com.kh.emp.model.service.EmpServiceImpl;

public class EmpSearch1PagingController extends AbstractController {

	private EmpService empService = new EmpServiceImpl();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//1.파라미터핸들링
		String searchType = request.getParameter("searchType");
		String searchKeyword = request.getParameter("searchKeyword");
		//SqlSession의 메소드 selectType에 두번째인자 Object타입으로 전달할 Map객체생성
		Map<String, String> map = new HashMap<>();
		map.put("searchType", searchType);
		map.put("searchKeyword", searchKeyword);
		System.out.println("map="+map);
		
		int numPerPage = 5;//한페이지당 게시물수
		int cPage;//요청페이지
		try {
			cPage = Integer.parseInt(request.getParameter("cPage"));
		}catch(NumberFormatException e) {
			cPage = 1;
		}
		
		
		//2.업무로직
		List<Map<String,String>> list = empService.search1Paging(map,cPage,numPerPage);
		System.out.println("list@EmpSearch1PagingController="+list);
		
		//페이지바 : 전체컨텐츠수 구하기
		int totalContents = empService.search1TotalContents(map);
		System.out.println("totalContents@EmpSearch1PagingController="+totalContents);
		
		/****** 페이지바 시작 ******/
		int pageBarSize = 5;
		String pageBar = "";
		int totalPage = (int)Math.ceil((double)totalContents/numPerPage);
		
		//1.pageBar작성
		//pageBar순회용변수 
		int pageNo = ((cPage - 1)/pageBarSize) * pageBarSize +1;
		//종료페이지 번호 세팅
		int pageEnd = pageNo+pageBarSize-1;
		//System.out.println("pageStart["+pageNo+"] ~ pageEnd["+pageEnd+"]");
		
		pageBar += "<ul class='pagination justify-content-center pagination-sm'>";
		//[이전]section
		if(pageNo == 1 ){
			pageBar += "<li class='page-item disabled'>";
			pageBar += "<a class='page-link' href='#' tabindex='-1'>이전</a>";
			pageBar += "</li>";
		}
		else {
			pageBar += "<li class='page-item'>";
			pageBar += "<a class='page-link' href='javascript:fn_paging("+(pageNo-1)+")'>이전</a>";
			pageBar += "</li>";
		}
		
		// pageNo section
		while(!(pageNo>pageEnd || pageNo > totalPage)){
			if(cPage == pageNo ){
				pageBar += "<li class='page-item active'>";
				pageBar += "<a class='page-link'>"+pageNo+"</a>";
				pageBar += "</li>";
			} 
			else {
				pageBar += "<li class='page-item'>";
				pageBar += "<a class='page-link' href='javascript:fn_paging("+pageNo+")'>"+pageNo+"</a>";
				pageBar += "</li>";
			}
			pageNo++;
		}
		
		//[다음] section
		if(pageNo > totalPage){
			pageBar += "<li class='page-item disabled'>";
			pageBar += "<a class='page-link' href='#'>다음</a>";
			pageBar += "</li>";
			
		} else {
			pageBar += "<li class='page-item'>";
			pageBar += "<a class='page-link' href='javascript:fn_paging("+pageNo+")'>다음</a> ";
			pageBar += "</li>";
		}
		
		pageBar += "</ul>";
		
		//2.스크립트 태그 작성
		//fn_paging함수
		pageBar += "<script>";
		pageBar += "function fn_paging(cPage){";
		pageBar += "location.href='"+request.getRequestURI()+"?cPage='+cPage";
		if(searchType!=null && searchKeyword!=null){
			pageBar +="+'&searchType="+searchType+"&searchKeyword="+searchKeyword+"'";
		}
		pageBar += "}";
		pageBar += "</script>";
		/****** 페이지바 끝 ******/
		
		
		System.out.println("pageBar@EmpSearch1PageController="+pageBar);
		//3.view단 포워딩
		request.setAttribute("list", list);
		request.setAttribute("pageBar", pageBar);
		request.setAttribute("totalContens", totalContents);
		setView("/WEB-INF/views/emp/search1Paging.jsp");
	}

}
