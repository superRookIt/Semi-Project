package com.javalec.ex.BDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.javalec.ex.BDto.BDto;
import com.javalec.ex.BDto.BDto2;

public class BDao2 {

	Context context = null;
	DataSource ds = null;

	Connection con = null;

	PreparedStatement ps = null;
	ResultSet rs = null;

	ArrayList<BDto2> list = new ArrayList<BDto2>();
	
	BDto2 dto2 = null;
	BDto2 dto = null;

	int bId;

	String bTitle, bContent, bFile, bFile2;

	int bHit;

	Timestamp bDate, bDate2;

	String sql = null;

	
	
	// 생성자
	public BDao2() {

		try {

			context = new InitialContext();
			ds = (DataSource) context.lookup("java:comp/env/jdbc/oracle11g");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// requeset.getSession().getServletContext().getRealPath("/");

	
	
	
	public BDto2 getMember(int bId) {

		sql = "select * from mvc_board_2 where bid=? order by bid asc";

		try {

			con = ds.getConnection();

			ps = con.prepareStatement(sql);
			ps.setInt(1, bId);
			rs = ps.executeQuery();

			if (rs.next()) {

				dto2 = new BDto2();

				dto2.setbId(rs.getInt("bid"));
				dto2.setbTitle(rs.getString("btitle"));
				dto2.setbFile(rs.getString("bfile"));
				dto2.setbFile2(rs.getString("bfile2"));
				dto2.setbContent(rs.getString("bcontent"));
				dto2.setbDate(rs.getTimestamp("bdate"));
				dto2.setbDate2(rs.getTimestamp("bdate2"));

				// } while

			} else {

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {

				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (con != null)
					con.close();

			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return dto2;
	}

	
	
	
	
	public BDto2 pregetMember(int bId) {

		sql = "select * from mvc_board_2 where bid=(select max(bid) from mvc_board_2 where bid<?) order by bid asc";

		try {

			con = ds.getConnection();

			ps = con.prepareStatement(sql);
			ps.setInt(1, bId);
			
			rs = ps.executeQuery();

			if (rs.next()) {

				dto2 = new BDto2();

				dto2.setbId(rs.getInt("bid"));
				dto2.setbTitle(rs.getString("btitle"));
				dto2.setbFile(rs.getString("bfile"));
				dto2.setbFile2(rs.getString("bfile2"));
				dto2.setbContent(rs.getString("bcontent"));
				dto2.setbDate(rs.getTimestamp("bdate"));
				dto2.setbDate2(rs.getTimestamp("bdate2"));

				// } while

			} else {

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {

				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (con != null)
					con.close();

			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return dto2;
	}

	
	
	
	
	
	
	public BDto2 nextgetMember(int bId) {

		sql = "select * from mvc_board_2 where bid=(select min(bid) from mvc_board_2 where bid>?) order by bid asc ";

		try {

			con = ds.getConnection();

			ps = con.prepareStatement(sql);
			ps.setInt(1, bId);
			
			rs = ps.executeQuery();

			if (rs.next()) {

				dto2 = new BDto2();

				dto2.setbId(rs.getInt("bid"));
				dto2.setbTitle(rs.getString("btitle"));
				dto2.setbFile(rs.getString("bfile"));
				dto2.setbFile2(rs.getString("bfile2"));
				dto2.setbContent(rs.getString("bcontent"));
				dto2.setbDate(rs.getTimestamp("bdate"));
				dto2.setbDate2(rs.getTimestamp("bdate2"));

				// } while

			} else {

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {

				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (con != null)
					con.close();

			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return dto2;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// member 여러개
	public ArrayList<BDto2> getmembers() {

		ArrayList<BDto2> list = new ArrayList<BDto2>();

		sql = "select * from mvc_board_2";

		try {

			con = ds.getConnection();

			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {

				BDto2 bt2 = new BDto2();

				bt2.setbId(rs.getInt("bid"));
				bt2.setbTitle(rs.getString("btitle"));
				bt2.setbFile(rs.getString("bfile"));
				bt2.setbFile2(rs.getString("bfile2"));
				bt2.setbContent(rs.getString("bcontent"));
				bt2.setbDate(rs.getTimestamp("bdate"));
				bt2.setbDate2(rs.getTimestamp("bdate2"));

				list.add(bt2);

			}

		}

		catch (

		Exception e) {
			e.printStackTrace();
		} finally {
			try {

				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (con != null)
					con.close();

			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return list;

	}// members

	
	
	
	
	// 전체 select
	public ArrayList<BDto2> list(int page, int limit) {

		int startrow = (page - 1) * 10 + 1;
		int endrow = startrow + limit - 1;
		
		//1~10, 11~20 ...

		sql = "select * from (select rownum rnum, bid, btitle, bcontent, bdate, bdate2, bfile, bfile2 from (select * from mvc_board_2 )) where rnum>=? and rnum<=?";

		try {

			con = ds.getConnection();
			ps = con.prepareStatement(sql);

			ps.setInt(1, startrow);
			ps.setInt(2, endrow);

			rs = ps.executeQuery();

			while (rs.next()) {

				bId = rs.getInt("bId");

				bTitle = rs.getString("bTitle");
				bContent = rs.getString("bContent");

				bDate = rs.getTimestamp("bdate");
				bDate2 = rs.getTimestamp("bdate2");
				
				bFile = rs.getString("bfile");
				bFile2 = rs.getString("bfile2");

				dto = new BDto2(bId, bTitle, bContent, bDate, bDate2,  bFile, bFile2);

				list.add(dto);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {

			try {

				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (con != null)
					con.close();

			} catch (Exception e2) {

				e2.printStackTrace();
			}

		}

		return list;

	} // 전체 select
	
	
	
	
	

	public int getlistCount() {

		int count = 0;

		sql = "select count(*) as count from mvc_board_2";
	

		try {

			con = ds.getConnection();
			ps = con.prepareStatement(sql);

			rs = ps.executeQuery();

			if (rs.next()) {

				count = rs.getInt("count");
				// as 별칭에 있는 걸로 넘어옴

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {

				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (con != null)
					con.close();

			} catch (Exception e2) {
				e2.printStackTrace();
			}

		}

		return count;

	}


	
	
	
	// 게시글 추가 write -insert
	public int e_write(String bTitle, String bContent, String bFile, String bFile2) {

		int check = 0;

		sql = "insert into mvc_board_2 values(mvc_board_sequence.nextval,?,?,sysdate, sysdate,?,?)";
		// currval 현재번호

		try {

			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, bTitle);
			ps.setString(2, bContent);
			ps.setString(3, bFile);
			ps.setString(4, bFile2);
			check = ps.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();

		} finally {
			try {

				if (ps != null)
					ps.close();
				if (con != null)
					con.close();

			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return check;

	}// 게시글 추가 write -insert

	
	
	
	
	// 게시글 삭제 delete
	public int delete(int bId) {

		int check = 0;

		sql = "delete mvc_board_2 where bid=?";

		try {

			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, bId);
			check = ps.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();

		} finally {
			try {

				if (ps != null)
					ps.close();
				if (con != null)
					con.close();

			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return check;

	}// 게시글 삭제 delete

	
	
	
	
	public BDto2 modify(int bId) {

		sql = "select * from mvc_board_2 where bId=?";

		try {

			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, bId);
			rs = ps.executeQuery();

			// 1개는 if

			if (rs.next()) {

				bId = rs.getInt("bId");

				bTitle = rs.getString("bTitle");
				bContent = rs.getString("bContent");

				bDate = rs.getTimestamp("bdate");
				bDate2 = rs.getTimestamp("bdate2");
				
				bFile = rs.getString("bfile");
				bFile2 = rs.getString("bfile2");

				dto = new BDto2(bId, bTitle, bContent, bDate, bDate2,  bFile, bFile2);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {

				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (con != null)
					con.close();

			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return dto;

	}

	
	
	
	public int modify_ok(int bId, String bTitle, String bContent, String bFile, String bFile2) {

		int check = 0;

		sql = "update mvc_board_2 set bTitle=? , bContent=? , bFile=?, bFile2=? where bId=?";

		try {

			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, bTitle);
			ps.setString(2, bContent);
			ps.setString(3, bFile);
			ps.setString(4, bFile2);
			ps.setInt(5, bId);

			check = ps.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			try {

				if (ps != null)
					ps.close();
				if (con != null)
					con.close();

			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return check;

	}
	
	
	
	
	
	
	

}
