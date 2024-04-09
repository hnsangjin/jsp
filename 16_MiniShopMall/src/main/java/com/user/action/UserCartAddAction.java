package com.user.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.controller.Action;
import com.shop.controller.ActionForward;
import com.shop.model.CartDAO;
import com.shop.model.CartDTO;

public class UserCartAddAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 제품 상세 내역에서 장바구니 버튼을 클릭하면 폼 태그안에 있는 데이터들을 shop_cart 테이블에 저장하는 비지니스 로직
		String name = request.getParameter("p_name").trim();
		int price = Integer.parseInt(request.getParameter("p_price").trim());
		int qty = Integer.parseInt(request.getParameter("p_qty").trim());
		int num = Integer.parseInt(request.getParameter("p_num"));
		String spec = request.getParameter("p_spec");
		String image = request.getParameter("p_image");
		String id = request.getParameter("userId");

		CartDTO dto = new CartDTO();

		dto.setCart_pnum(num);
		dto.setCart_userid(id);
		dto.setCart_pname(name);
		dto.setCart_pqty(qty);
		dto.setCart_price(price);
		dto.setCart_pspec(spec);
		dto.setCart_pimage(image);
		
		CartDAO dao = CartDAO.getInstance();
		
		int check = dao.insertCart(dto);
		
		PrintWriter out = response.getWriter();
		
		if(check > 0) {
			
			out.println("<script>");
			out.println("alert('장바구니에 상품이 추가되었습니다.')");
			out.println("location.href='user_cart_list.go'");
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
