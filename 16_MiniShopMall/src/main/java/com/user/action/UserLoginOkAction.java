package com.user.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shop.controller.Action;
import com.shop.controller.ActionForward;
import com.shop.model.UserDAO;
import com.shop.model.UserDTO;

public class UserLoginOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String id = request.getParameter("user_id");
		String pwd = request.getParameter("user_pwd");

		UserDAO dao = UserDAO.getInstance();

		int check = dao.userCheck(id, pwd);

		PrintWriter out = response.getWriter();

		ActionForward forward = new ActionForward();

		if (check > 0) {

			UserDTO cont = dao.getMember(id);

			HttpSession session = request.getSession();

			session.setAttribute("UserId", cont.getMemid());
			session.setAttribute("UserName", cont.getMemname());

			forward.setRedirect(true);
			forward.setPath("user_main.go");

		} else if (check == -1) {

			out.println("<script>");
			out.println("alert('비밀번호가 틀렸습니다.')");
			out.println("history.back()");
			out.println("</script>");

		} else {

			out.println("<script>");
			out.println("alert('등록된 회원정보가 없습니다.')");
			out.println("history.back()");
			out.println("</script>");
		}
		return forward;
	}

}
