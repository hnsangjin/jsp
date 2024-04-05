package com.board.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;
import com.board.model.BoardDTO;

public class BoardListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		// 요청 : 게시물 전체 목록을 보여달라고 요청.
		// 응답 : board 테이블의 전체 목록을 조회하여 view 페이지로 이동시키는 비지니스 로직.
		//		 이 때 비지니스 로직 작업 시 페이징 처리 작업도 같이 진행.
		
		// 페이징 작업 진행
		
		// 한 페이지당 보여줄 게시물의 수
		int rowsize = 3;	
		
		// 아래에 보여질 페이지의 최대 블럭 수
		int block = 3;		
		
		// board 테이블 상의 게시물의 전체 수
		int totalRecord = 0;
		
		// 전체 페이지 수
		int allpage = 0;
		
		// 현재 페이지 변수
		int page = 0;
		
		if(request.getParameter("page") != null) {
			
			page = Integer.parseInt(request.getParameter("page").trim());
		
			
		
		}else {
			// 처음으로 메인페이지에서 "게시물 전체 목록" a 태그를 클릭한 경우
			page = 1;
		}
		
		// 해당 페이지에서 시작 게시물 번호
		int startNo = (page * rowsize) - (rowsize - 1);
		
		// 해당 페이지에서 끝 게시물 번호
		int endNo = (page * rowsize);
		
		// 해당 페이지에서 시작 블럭
		int startBlock = (((page - 1)/ block) * block) + 1;
		
		// 해당 페이지에서 끝 블럭
		int endBlock = (((page - 1)/ block) * block) + block;
		
		// 전체 게시물 수를 확인하는 메서드 호출
		BoardDAO dao = BoardDAO.getInstance();
		
		totalRecord = dao.getBoardCount();
		
		// 전체 게시물 수를 한 페이지당 보여질 게시물의 수로 나누어주면 전체 페이지 수가 나옴.
		allpage = (int)Math.ceil(totalRecord / (double)rowsize);
		
		if(endBlock > allpage) {
				
			endBlock = allpage;
		}
		
		// 현재 페이지에 해당하는 게시물을 가져오는 메서드 호출.
		List<BoardDTO> boardList = dao.getBoardList(page, rowsize);
		
		// 페이징 작업 후에는 지금까지 페이징 처리 시 작업했던 모든 정보들을 view 페이지로 바인딩 시켜주어야 함.
		request.setAttribute("page", page);
		request.setAttribute("rowsize", rowsize);
		request.setAttribute("block", block);
		request.setAttribute("totalRecord", totalRecord);
		request.setAttribute("allPage", allpage);
		request.setAttribute("startNo", startNo);
		request.setAttribute("endNo", endNo);
		request.setAttribute("startBlock", startBlock);
		request.setAttribute("endBlock", endBlock);
		request.setAttribute("List", boardList);
		
		ActionForward forward = new ActionForward();
		
		// jsp 페이지로 이동하는 경우 false 값을 줌.
		forward.setRedirect(false);
		
		// 이동할 페이지 주소
		forward.setPath("view/board_list.jsp");
		
		
		return forward;
	}

}
















