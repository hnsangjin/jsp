package com.admin.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shop.controller.Action;
import com.shop.controller.ActionForward;
import com.shop.model.AdminDAO;
import com.shop.model.AdminDTO;

public class AdminLoginOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

		// 관리자 로그인 페이지에서 입력한 아이디와 비밀번호가 shop_admin 테이블 상의 아이디와 비밀번호가 일치하는지 여부를 확인하여
		// view page(관리자 메인 페이지)로 이동시키는 비지니스 로직

		String id = request.getParameter("admin_id").trim();
		String pwd = request.getParameter("admin_pwd").trim();

		AdminDAO dao = AdminDAO.getInstance();

		int check = dao.adminCheck(id, pwd);



		ActionForward forward = new ActionForward();

		PrintWriter out = response.getWriter();
		
		/*
		 * - 쿠키보다 세션을 더 많이 사용하는 이유는 세션이 쿠키보다 보안이 앞서기 때문임.
		 *   쿠키의 이름이나 데이터는 네트워크를 통해 전달이 되기 때문에 HTTP 프로토콜을 사용하는 경우
		 *   중간에 누군가 쿠키의 값을 읽어갈 수 있음. 하지만, 세션의 값은 오직 서버에만 저장되기 때문에
		 *   중요한 데이터를 저장하기에 알맞은 방법이 됨.
		 *   
		 * - 세션을 설정하는 방법
		 *   request.getSession()
		 *   ==> HttpSession이 존재하면 현재 HttpSession을 반환해주고, 존재하지 않으면 세션을 생성하는 메서드.
		 */
		HttpSession session = request.getSession();
		
		if (check > 0) {

			// 아이디와 비밀번호가 일치하는 관리자
			// 관리자인 경우 관리자의 정보를 받아와보자.
			AdminDTO cont = dao.getAdmin(id);

			// 관리자의 정보를 세션에 저장하여 사용함.
			session.setAttribute("adminId", cont.getAdmin_id());
			session.setAttribute("adminName", cont.getAdmin_name());

			// 세션에 저장된 정보를 가지고 view 페이지로 이동
			forward.setRedirect(false);
			forward.setPath("admin/admin_main.jsp");
			
		} else if (check == -1) {
			
			// 아이디는 일치하지만 비밀번호가 틀린 경우
			out.println("<script>");
			out.println("alert('비밀번호가 틀렸습니다.')");
			out.println("history.back()");
			out.println("</script>");

		} else {

			out.println("<script>");
			out.println("alert('관리자 정보가 존재하지 않습니다.')");
			out.println("history.back()");
			out.println("</script>");
		}

		return forward;
	}

}













