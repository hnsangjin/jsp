package com.admin.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.controller.Action;
import com.shop.controller.ActionForward;
import com.shop.model.CategoryDAO;
import com.shop.model.CategoryDTO;

public class AdminCategoryListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// shop_category 테이블에 있는 전체 카테고리 리스트를 조회하여 view 페이지로 이동시키는 비지니스 로직
		
		CategoryDAO dao = CategoryDAO.getInstance();
		
		List<CategoryDTO> list = dao.getCategoryList();
		
		request.setAttribute("categoryList", list);
		
		ActionForward forward = new ActionForward();
		
		forward.setRedirect(false);
		forward.setPath("admin/admin_category_list.jsp");
		
		return forward;
	}

}
