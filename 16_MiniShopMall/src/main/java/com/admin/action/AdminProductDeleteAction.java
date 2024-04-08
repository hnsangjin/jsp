package com.admin.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.controller.Action;
import com.shop.controller.ActionForward;
import com.shop.model.ProductDAO;

public class AdminProductDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		int no = Integer.parseInt(request.getParameter("num").trim());
		
		ProductDAO dao = ProductDAO.getInstance();
		
		int check = dao.deleteProduct(no);
		
		PrintWriter out = response.getWriter();
		
		if (check > 0) {
			
			dao.updateSequence(no);

			out.println("<script>");
			out.println("alert('상품 삭제 완료')");
			out.println("location.href='admin_product_list.go'");
			out.println("</script>");

		} else {

			out.println("<script>");
			out.println("alert('상품 삭제 실패')");
			out.println("history.back()");
			out.println("</script>");

		}
		
		return null;
	}

}
