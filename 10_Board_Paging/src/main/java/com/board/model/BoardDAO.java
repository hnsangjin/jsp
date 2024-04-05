package com.board.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class BoardDAO {
	
	//DB와 연결하는 객체.
	Connection con = null;
	
	PreparedStatement pstmt = null;
	
	ResultSet rs = null;
	
	String sql = null;
	
	// BoardDAO 객체를 싱글턴 방식으로 만들어보자.
	// 1단계 : BoardDAO 객체를 정적 멤버로 선언해주자.
	private static BoardDAO instance = null;
	
	// 2단계 : 싱글턴 방식으로 객체를 만들기 위해서는 우선적으로 기본생성자의 접근지정자를 public이 아닌 private으로 변경해 주어야 함.
	//        즉, 외부에서 직접적으로 기본생성자에 접근하여 호출하지 못하도록 하는 방법이다.
	private BoardDAO() {
		
	
		
	}
	
	// 기본 생성자 대신에 싱글턴 객체를 return 해주는 getInstance() 메서드를 만들어서 해당 getInstance() 메서드를 외부에서 접근가능하도록 해줘야 함.
	public static BoardDAO getInstance() {
		
		if(instance == null) {
			
			instance = new BoardDAO();
			
			
			
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
			 
			 if(rs != null) {rs.close();}
			 if(pstmt != null) {pstmt.close();}
			 if(con != null) {con.close();}
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	 public void closeConn(PreparedStatement pstmt, Connection con) {
		 
		 try {
			 
			 
			 if(pstmt != null) {pstmt.close();}
			 if(con != null) {con.close();}
			 
		 } catch (SQLException e) {
			 // TODO Auto-generated catch block
				 e.printStackTrace();
		 }
		 
		 }
	 
	 // board 테이블의 전체 게시물 수를 확인하는 메서드.
	 public int getBoardCount() {
	
		 int count = 0;
		 
		
		 
		 try {
			 
			 openConn();
			 
			 sql = "select count(*) from board";
			 
			 pstmt = con.prepareStatement(sql);
			 
			 rs = pstmt.executeQuery();
			 
			 if(rs.next()) {
				 
				 count = rs.getInt(1);
				 
			 }
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
			closeConn(rs, pstmt, con);
		}return count;
		 
	 }
	 
	 // board 테이블에서 현재 페이지에 해당하는 게시물을 조회하는 메서드.
	public List<BoardDTO> getBoardList(int page, int rowsize) {
		 
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		 
		int startNo = (page * rowsize) - (rowsize - 1);
			
		// 해당 페이지에서 끝 게시물 번호
		int endNo = (page * rowsize);
		
		
		try {
			
			openConn();
			
			sql = "select * from"
					+ "(select row_number() over(order by board_no desc) as rnum, b.* from board b)"
					+ "where rnum between ? and ?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, startNo);
			pstmt.setInt(2, endNo);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				BoardDTO dto = new BoardDTO();
				
				dto.setBoard_no(rs.getInt("board_no"));
				dto.setBoard_writer(rs.getString("board_writer"));
				dto.setBoard_title(rs.getString("board_title"));
				dto.setBoard_cont(rs.getString("board_cont"));
				dto.setBoard_pwd(rs.getString("board_pwd"));
				dto.setBoard_hit(rs.getInt("board_hit"));
				dto.setBoard_date(rs.getString("board_date"));
				dto.setBoard_update(rs.getString("board_update"));
				
				list.add(dto);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}return list;
		 
	 }
	 
	 
	 public int insertBoard(BoardDTO dto) {
		 
		 int result = 0;
		 
		 int count = 0;

		 
		 try {
			 
			 
			 openConn();
			 
			 sql = "select max(board_no) from board";
			 
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				count = rs.getInt(1);
				
			}
			
			sql = "insert into board values(?, ?, ?, ?, ?, default, sysdate, '')";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, count + 1);
			pstmt.setString(2, dto.getBoard_writer());
			pstmt.setString(3, dto.getBoard_title());
			pstmt.setString(4, dto.getBoard_cont());
			pstmt.setString(5, dto.getBoard_pwd());
			
			result = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
			closeConn(rs, pstmt, con);
		}
		 return result;
		 
	 }
	 
	 public void countBoard(int no) {
		 

		 
		 try {
			 openConn();
			 
			 sql = "update board set board_hit = board_hit + 1 where board_no =?";
			 
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, no);
			
			pstmt.executeQuery();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(pstmt, con);
		}
		 
		 
		 
	 }
	 
	 
	 public BoardDTO getBoardContent(int no) {
		 
		 BoardDTO dto = null;
		
		 try {
			openConn();
			 
			sql = "select * from board where board_no = ?";
					 
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, no);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				dto = new BoardDTO();
				
				dto.setBoard_no(rs.getInt("board_no"));
				dto.setBoard_writer(rs.getString("board_writer"));
				dto.setBoard_title(rs.getString("board_title"));
				dto.setBoard_cont(rs.getString("board_cont"));
				dto.setBoard_pwd(rs.getString("board_pwd"));
				dto.setBoard_hit(rs.getInt("board_hit"));
				dto.setBoard_date(rs.getString("board_date"));
				dto.setBoard_update(rs.getString("board_update"));
						
				
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
			closeConn(rs, pstmt, con);
		}return dto;
		 
	 }
	 
	public int deleteContent(int no) {
		

		
		int result = 0;
		
		try {
			
			openConn();
			
			sql = "delete from board where board_no = ?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, no);
			
			result = pstmt.executeUpdate();
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(pstmt, con);
		}
		
		return result;
		
	}
	
	public void updateBoard_no(int no) {

		try {
			
			openConn();
			
			sql = "update board set board_no = board_no - 1 where board_no > ?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, no);
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(pstmt, con);
		}
		
	}
	 
	public int modifyContent(BoardDTO dto) {
		
		int result = 0;
		
		try {
			openConn();
			
			sql = "update board set board_writer = ?, board_title = ?, board_cont = ?, board_pwd = ? where board_no = ?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, dto.getBoard_writer());
			pstmt.setString(2, dto.getBoard_title());
			pstmt.setString(3, dto.getBoard_cont());
			pstmt.setString(4, dto.getBoard_pwd());
			pstmt.setInt(5, dto.getBoard_no());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
			closeConn(pstmt, con);
		}return result;
		
		
	}
	 
	public List<BoardDTO> selectBoard(String field, String keyword, int page, int rowsize){
		
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		
		int startNo = (page * rowsize) - (rowsize - 1);
		int endNo = (page * rowsize);
		
		
		try {
			openConn();
			
			sql = "select * from (select row_number() over (order by board_no desc) as rnum, b.* from board b ";
			
			if(field.equals("title")) {
				
				sql += "where board_title like ?)";

			}else if(field.equals("content")) {
				
				sql += "where board_cont like ?)";
				
			}else if(field.equals("title_content")) {
				
				sql += "where board_title LIKE ? OR board_cont LIKE ?)";
				
			}else {
				
				sql += "where board_writer like ?)";
				
			}
			
			sql += " where rnum >= ? and rnum <= ?";
			
			pstmt = con.prepareStatement(sql);
			
			
			if(field.equals("title_content")) {
				pstmt.setString(1, "%" + keyword + "%");
				pstmt.setString(2, "%" + keyword + "%");
				pstmt.setInt(3, startNo);
				pstmt.setInt(4, endNo);
			}else {
				pstmt.setString(1, "%" + keyword + "%");
				pstmt.setInt(2, startNo);
				pstmt.setInt(3, endNo);
			}
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				BoardDTO dto = new BoardDTO();
				
				dto.setBoard_no(rs.getInt("board_no"));
				dto.setBoard_writer(rs.getString("board_writer"));
				dto.setBoard_title(rs.getString("board_title"));
				dto.setBoard_cont(rs.getString("board_cont"));
				dto.setBoard_pwd(rs.getString("board_pwd"));
				dto.setBoard_hit(rs.getInt("board_hit"));
				dto.setBoard_date(rs.getString("board_date"));
				dto.setBoard_update(rs.getString("board_update"));
				
				
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
	 
	public int searchListCount(String field, String keyword) {
		
		int count = 0;
		
		try {
			openConn();
			
			sql = "select count(*) from board ";
			
			if(field.equals("title")) {
				
				sql += "where board_title like ?";

			}else if(field.equals("content")) {
				
				sql += "where board_cont like ?";
				
			}else if(field.equals("title_content")) {
				
				sql += "where board_title LIKE ? OR board_cont LIKE ?";
				
			}else {
				
				sql += "where board_writer like ?";
				
			}
			
			sql += "order by board_no desc";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, "%" + keyword + "%");
			if(field.equals("title_content")) pstmt.setString(2, "%" + keyword + "%");
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				count = rs.getInt(1);
			}
			

			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}return count;
		
	}
	 
	 
	 
	 
	 
	 
	 
	 
}
