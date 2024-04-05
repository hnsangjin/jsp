package com.board.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;

public class BoardDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

		int no = Integer.parseInt(request.getParameter("num"));
		
		BoardDAO dao = BoardDAO.getInstance();
		
		int check = dao.deleteContent(no);
		
		PrintWriter out = response.getWriter();
		
		if(check > 0) {
			
			dao.updateBoard_no(no);
			
			out.println("<script>");
			out.println("alert('삭제되었습니다.')");
			out.println("location.href='select.go'");
			out.println("</script>");
			
		}else {
			
			out.println("<script>");
			out.println("alert('다시 시도해주세요.')");
			out.println("history.back()");
			out.println("</script>");
		}
		
		return null;
	}

}
