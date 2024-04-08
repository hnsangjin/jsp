package com.admin.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shop.controller.Action;
import com.shop.controller.ActionForward;

public class AdminLogoutAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 상단에 위치한 로그아웃을 클릭하면 현재의 모든 정보(session)를 종료시켜주는 비지니스 로직
		HttpSession session = request.getSession();

		// invalidate() : 현재 사용중인 모든 세션을 종료시키는 메서드.
		session.invalidate();

		ActionForward forward = new ActionForward();

		forward.setRedirect(false);
		forward.setPath("main.jsp");

		return forward;
	}
}