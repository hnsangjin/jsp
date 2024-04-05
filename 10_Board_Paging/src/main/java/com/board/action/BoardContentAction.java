package com.board.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;
import com.board.model.BoardDTO;

public class BoardContentAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

		int no = Integer.parseInt(request.getParameter("no"));
		int page = Integer.parseInt(request.getParameter("page"));
		
		BoardDAO dao = BoardDAO.getInstance();
		
		dao.countBoard(no);
		BoardDTO content = dao.getBoardContent(no);
		
		request.setAttribute("cont", content);
		request.setAttribute("page", page);
		
		ActionForward forward = new ActionForward();
		
		// jsp 페이지로 이동하는 경우 false 값을 줌.
		forward.setRedirect(false);
		
		// 이동할 페이지 주소
		forward.setPath("view/board_content.jsp");
		
		
		return forward;
		
	}

}
