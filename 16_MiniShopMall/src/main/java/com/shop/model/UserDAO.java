package com.shop.model;

import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class UserDAO {

	// DB와 연결하는 객체.
	Connection con = null;

	PreparedStatement pstmt = null;

	ResultSet rs = null;

	String sql = null;

	// UserDAO 객체를 싱글턴 방식으로 만들어보자.
	// 1단계 : UserDAO 객체를 정적 멤버로 선언해주자.
	private static UserDAO instance = null;

	// 2단계 : 싱글턴 방식으로 객체를 만들기 위해서는 우선적으로 기본생성자의 접근지정자를 public이 아닌 private으로 변경해 주어야
	// 함.
	// 즉, 외부에서 직접적으로 기본생성자에 접근하여 호출하지 못하도록 하는 방법이다.
	private UserDAO() {

	}

	// 기본 생성자 대신에 싱글턴 객체를 return 해주는 getInstance() 메서드를 만들어서 해당 getInstance() 메서드를
	// 외부에서 접근가능하도록 해줘야 함.
	public static UserDAO getInstance() {

		if (instance == null) {

			instance = new UserDAO();

		}
		return instance;
	}

	// DB 연동을 작업을 하는 메서드
	// JDBC 방식이 아닌 DBCP 방식으로 DB 연동 진행
	public void openConn() {

		try {
			// 1단계 : JNDI 서버 객체 생성
			Context initCtx = new InitialContext();

			// 2단계 : Context 객체를 얻어와야 함.
			Context ctx = (Context) initCtx.lookup("java:comp/env");

			// 3단계 : lookup() 메서드를 이용하여 매칭되는 커넥션을 찾아옴.
			DataSource ds = (DataSource) ctx.lookup("jdbc/myoracle");

			// 4단계 : DataSource 객체를 이용하여 커넥션을 하나 가져오면 됨.
			con = ds.getConnection();

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

	public int userCheck(String id, String pwd) {

		int result = 0;

		try {
			openConn();

			sql = "select * from member where memid = ?";

			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, id);

			rs = pstmt.executeQuery();

			if (rs.next()) {

				if (pwd.equals(rs.getString("mempwd"))) {
					result = 1;

				} else {

					result = -1;
				}

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return result;

	}
	
	public UserDTO getMember(String id) {

		UserDTO dto = null;

		try {
			openConn();

			sql = "select * from member where memid = ?";

			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, id);

			rs = pstmt.executeQuery();

			if (rs.next()) {

				dto = new UserDTO();

				dto.setNum(rs.getInt("memno"));
				dto.setMemid(rs.getString("memid"));
				dto.setMemname(rs.getString("memname"));
				dto.setPwd(rs.getString("mempwd"));
				dto.setAge(rs.getInt("age"));
				dto.setMileage(rs.getInt("mileage"));
				dto.setJob(rs.getString("job"));
				dto.setAddr(rs.getString("addr"));
				dto.setRegdate(rs.getString("regdate"));

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return dto;

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
