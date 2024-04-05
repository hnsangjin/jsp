package com.board.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;
import com.board.model.BoardDTO;

public class BoardModifyOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

		int no = Integer.parseInt(request.getParameter("no"));
		String writer = request.getParameter("writer");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String pwd = request.getParameter("pwd");
		String board_pwd = request.getParameter("bd_pwd");
		int page = Integer.parseInt(request.getParameter("page"));
		
		BoardDTO dto = new BoardDTO();
		
		dto.setBoard_no(no);
		dto.setBoard_writer(writer);
		dto.setBoard_title(title);
		dto.setBoard_cont(content);
		
		BoardDAO dao = BoardDAO.getInstance();
		
		PrintWriter out = response.getWriter();
		
		if(board_pwd.equals(pwd)) {
			
			int check = dao.modifyContent(dto);
			
			if(check > 0) {
				
				out.println("<script>");
				out.println("alert('수정이 완료되었습니다.')");
				out.println("location.href='content.go?no=" + dto.getBoard_no() + "&page=" + page+"'");
				out.println("</script>");
				
			}else {
				
				out.println("<script>");
				out.println("alert('수정이 실패했습니다.')");
				out.println("history.back()");
				out.println("</script>");
				
			}
		}else {
			out.println("<script>");
			out.println("alert('비밀번호가 틀렸습니다.')");
			out.println("history.back()");
			out.println("</script>");
		}
		
		
		return null;
	}

}
