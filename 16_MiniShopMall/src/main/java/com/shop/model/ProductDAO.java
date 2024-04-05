package com.shop.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

	// DB와 연결하는 객체.
	Connection con = null;

	PreparedStatement pstmt = null;

	ResultSet rs, rs1 = null;

	String sql = null;

	// ProductDAO 객체를 싱글턴 방식으로 만들어보자.
	// 1단계 : ProductDAO 객체를 정적 멤버로 선언해주자.
	private static ProductDAO instance = null;

	// 2단계 : 싱글턴 방식으로 객체를 만들기 위해서는 우선적으로 기본생성자의 접근지정자를 public이 아닌 private으로 변경해 주어야
	// 함.
	// 즉, 외부에서 직접적으로 기본생성자에 접근하여 호출하지 못하도록 하는 방법이다.
	private ProductDAO() {

	}

	// 기본 생성자 대신에 싱글턴 객체를 return 해주는 getInstance() 메서드를 만들어서 해당 getInstance() 메서드를
	// 외부에서 접근가능하도록 해줘야 함.
	public static ProductDAO getInstance() {

		if (instance == null) {

			instance = new ProductDAO();

		}
		return instance;
	}

	// DB 연동을 작업을 하는 메서드
	public void openConn() {

		String driver = "oracle.jdbc.driver.OracleDriver";

		String url = "jdbc:oracle:thin:@localhost:1521:xe";

		String user = "goott";

		String password = "99229922";

		try {
			Class.forName(driver);

			con = DriverManager.getConnection(url, user, password);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// DB에 연결되어 있던 자원을 종료하는 메서드
	public void closeConn(ResultSet rs, PreparedStatement pstmt, Connection con) {

		try {

			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void closeConn(PreparedStatement pstmt, Connection con) {

		try {

			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	public int insertProduct(ProductDTO dto) {

		int result = 0, count = 0;

		try {
			openConn();

			sql = "select max(pnum) from shop_products";

			pstmt = con.prepareStatement(sql);

			rs = pstmt.executeQuery();

			if (rs.next()) {

				count = rs.getInt(1) + 1;
			}

			sql = "insert into shop_products values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, sysdate)";

			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, count);
			pstmt.setString(2, dto.getPname());
			pstmt.setString(3, dto.getPcategory_fk());
			pstmt.setString(4, dto.getPcompany());
			pstmt.setString(5, dto.getPimage());
			pstmt.setInt(6, dto.getPqty());
			pstmt.setInt(7, dto.getPrice());
			pstmt.setString(8, dto.getPspec());
			pstmt.setString(9, dto.getPcontents());
			pstmt.setInt(10, dto.getPoint());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return result;

	}

	public List<ProductDTO> getProductList() {

		List<ProductDTO> list = new ArrayList<ProductDTO>();

		try {
			openConn();

			sql = "select * from shop_products order by pnum desc";

			pstmt = con.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				ProductDTO dto = new ProductDTO();

				dto.setPnum(rs.getInt("pnum"));
				dto.setPname(rs.getString("pname"));
				dto.setPcategory_fk(rs.getString("pcategory_fk"));
				dto.setPcompany(rs.getString("pcompany"));
				dto.setPimage(rs.getString("pimage"));
				dto.setPqty(rs.getInt("pqty"));
				dto.setPrice(rs.getInt("price"));
				dto.setPspec(rs.getString("pspec"));
				dto.setPcontents(rs.getString("pcontents"));
				dto.setPoint(rs.getInt("point"));
				dto.setPinputdate(rs.getString("pinputdate"));

				list.add(dto);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return list;

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
