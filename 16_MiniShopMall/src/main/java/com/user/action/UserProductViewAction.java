package com.user.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.controller.Action;
import com.shop.controller.ActionForward;
import com.shop.model.ProductDAO;
import com.shop.model.ProductDTO;

public class UserProductViewAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// get 방식으로 넘어온 제품 번호에 해당하는 제품의 상세정보를 조회하여 view 페이지로 이동시키는 비지니스 로직
		
		int no = Integer.parseInt(request.getParameter("pnum"));
		
		ProductDAO dao  = ProductDAO.getInstance();
		
		ProductDTO cont = dao.getProductContent(no);
		
		request.setAttribute("ProductCont", cont);
		
		return null;
	}

}
