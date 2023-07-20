package com.kh.emp.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sh.app.common.AbstractController;
import com.sh.app.emp.service.EmpService;
import com.sh.app.emp.service.EmpServiceImpl;

public class EmpPagedListController extends AbstractController {

	private EmpService empService = new EmpServiceImpl();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//1.파라미터핸들링
		int numPerPage = 5;//한페이지당 게시물수
		int cPage;//요청페이지
		try {
			cPage = Integer.parseInt(request.getParameter("cPage"));
		}catch(NumberFormatException e) {
			cPage = 1;
		}
		
		//2.업무로직
		List<Map<String,String>> list = empService.selectEmpList(cPage,numPerPage);
		System.out.println("list@controller="+list);
		
		//페이지바 : 전체컨텐츠수 구하기
		int totalContents = empService.selectTotalContents();
		
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
		pageBar += "}";
		pageBar += "</script>";
		/****** 페이지바 끝 ******/
		
		//3.view단 포워딩
		request.setAttribute("list", list);
		request.setAttribute("pageBar", pageBar);
		request.setAttribute("totalContens", totalContents);
		setView("/WEB-INF/views/emp/empPagedList.jsp");
	}

}
