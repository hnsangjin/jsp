package com.admin.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.controller.Action;
import com.shop.controller.ActionForward;
import com.shop.model.CategoryDAO;
import com.shop.model.ProductDAO;

public class AdminCategoryDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		int no = Integer.parseInt(request.getParameter("num"));
		
		CategoryDAO dao = CategoryDAO.getInstance();
		
		int check = dao.deleteCategory(no);
		
		PrintWriter out = response.getWriter();
		
		if(check > 0) {
			
			dao.updateSequence(no);
			
			out.println("<script>");
			out.println("alert('삭제되었습니다')");
			out.println("location.href='admin_category_list.go'");
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
