package com.user.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.controller.Action;
import com.shop.controller.ActionForward;
import com.shop.model.CartDAO;

public class UserCartDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// get 방식으로 넘어온 장바구니 번호에 해당하는 장바구니 내역을 shop_cart 테이블에서 삭제하는 비지니스 로직
		int num = Integer.parseInt(request.getParameter("num").trim());
		
		CartDAO dao = CartDAO.getInstance();
		
		int check =	 dao.deleteCart(num);
		
		PrintWriter out = response.getWriter();
		
		if(check > 0) {
			
			dao.updateSequence(num);
			
			out.println("<script>");
			out.println("alert('제품이 장바구니에서 제외되었습니다.')");
			out.println("location.href='user_cart_list.go'");
			out.println("</script>");
		}else {
			
			out.println("<script>");
			out.println("alert('삭제 실패.')");
			out.println("history.back()");
			out.println("</script>");
		}
		
		return null;
	}

}
