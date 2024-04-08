package com.admin.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.shop.controller.Action;
import com.shop.controller.ActionForward;
import com.shop.model.ProductDAO;
import com.shop.model.ProductDTO;

public class AdminProductModifyOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

		// 상품 등록 폼 페이지에서 넘어오는 데이터들을 shop_products 테이블에 저장하는 비지니스 로직
		// 첨부 파일이 저장될 경로를 설정
		String saveFolder = "C:\\NCS\\Workspace(jsp)\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\16_MiniShopMall\\upload\\";

		// 첨부 파일 용량 크기 제한 - 파일 업로드 최대 크기
		int fileSize = 10 * 1024 * 1024;

		// 파일 업로드를 위한 객체 생성 객체
		MultipartRequest multi = new MultipartRequest(request, saveFolder, fileSize, "UTF-8",
				new DefaultFileRenamePolicy());

		// 상품 등록 폼 페이지에서 넘어온 데이터들을 받아주자.
		String name = multi.getParameter("p_name");
		String category = multi.getParameter("p_category");
		String company = multi.getParameter("p_company");
		int pqty = Integer.parseInt(multi.getParameter("p_qty"));
		int price = Integer.parseInt(multi.getParameter("p_price"));
		String pspec = multi.getParameter("p_spec");
		String content = multi.getParameter("p_content");
		int point = Integer.parseInt(multi.getParameter("p_point"));

		// getFilesystemName() ==> 업로드될 파일의 이름을 문자열로 반환해 주는 메서드
		String image = multi.getFilesystemName("p_image_new");

		if (image == null) {
			image = multi.getParameter("p_image_old");

		}
		
		int no = Integer.parseInt(multi.getParameter("p_num").trim());
		
		ProductDTO dto = new ProductDTO();

		dto.setPnum(no);
		dto.setPname(name);
		dto.setPcategory_fk(category);
		dto.setPcompany(company);
		dto.setPqty(pqty);
		dto.setPrice(price);
		dto.setPspec(pspec);
		dto.setPimage(image);
		dto.setPcontents(content);
		dto.setPoint(point);

		ProductDAO dao = ProductDAO.getInstance();

		int check = dao.updateProduct(dto);

		PrintWriter out = response.getWriter();

		if (check > 0) {

			out.println("<script>");
			out.println("alert('상품 수정 완료')");
			out.println("location.href='admin_product_list.go'");
			out.println("</script>");

		} else {

			out.println("<script>");
			out.println("alert('상품 수정 실패')");
			out.println("history.back()");
			out.println("</script>");

		}

		return null;
	}

}
