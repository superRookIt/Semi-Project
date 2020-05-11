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

public class BDao {

	Context context = null;
	DataSource ds = null;

	Connection con = null;

	PreparedStatement ps = null;
	ResultSet rs = null;

	ArrayList<BDto> list = new ArrayList<BDto>();
	BDto dto = null;

	int bId;

	String bName, bTitle, bContent;

	int bHit, bGroup, bIndent, bStep;

	Timestamp bDate;

	String sql = null;

	
	
	
	
	
	
	// 생성자
	public BDao() {

		try {

			context = new InitialContext();
			ds = (DataSource) context.lookup("java:comp/env/jdbc/oracle11g");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	
	
	
	
	// 전체 select
	public ArrayList<BDto> list(int page, int limit, String opt, String search) {

		int startrow = (page - 1) * 10 + 1;
		int endrow = startrow + limit - 1;

		if (opt == null) {

			sql = "select * from (select rownum rnum, bid, bname, btitle, bcontent, bdate, bhit, bgroup, bstep, bindent from (select * from mvc_board order by bgroup desc, bid asc)) where rnum>=? and rnum<=?";
		}

		else {

			switch (opt) {

			case "":
				sql = "select * from (select rownum rnum, bid, bname, btitle, bcontent, bdate, bhit, bgroup, bstep, bindent from (select * from mvc_board order by bgroup desc, bid asc)) where rnum>=? and rnum<=?";
				break;

			case "all":
				sql = "select * from (select rownum rnum, bid, bname, btitle, bcontent, bdate, bhit, bgroup, bstep, bindent from (select * from mvc_board where btitle like ? or bcontent like ? order by bgroup desc, bid asc)) where rnum>=? and rnum<=?";
				break;

			case "title":
				sql = "select * from (select rownum rnum, bid, bname, btitle, bcontent, bdate, bhit, bgroup, bstep, bindent from (select * from mvc_board where btitle like ? order by bgroup desc, bid asc)) where rnum>=? and rnum<=?";
				break;

			case "con":
				sql = "select * from (select rownum rnum, bid, bname, btitle, bcontent, bdate, bhit, bgroup, bstep, bindent from (select * from mvc_board where bcontent like ? order by bgroup desc, bid asc)) where rnum>=? and rnum<=?";
				break;

			}

			// sql = "select * from (select rownum rnum, bid, bname, btitle, bcontent, bdate, bhit, bgroup, bstep, bindent from (select * from mvc_board order by bgroup desc, bid asc)) where rnum>=? and rnum<=?";
			
			
			//   ps.setString(1, "%"+search+"%");


		}

		try {

			con = ds.getConnection();
			ps = con.prepareStatement(sql);

			if (opt == null) {
				
				ps.setInt(1, startrow);
				ps.setInt(2, endrow);
			}

			else {

				switch (opt) {

				case "":
					ps.setInt(1, startrow);
					ps.setInt(2, endrow);
					break;

				case "all":
					ps.setString(1, "%"+search+"%");
					ps.setString(2, "%"+search+"%");
					ps.setInt(3, startrow);
					ps.setInt(4, endrow);
					break;

				case "title":
					ps.setString(1, "%"+search+"%");
					ps.setInt(2, startrow);
					ps.setInt(3, endrow);
					break;

				case "con":
					ps.setString(1, "%"+search+"%");
					ps.setInt(2, startrow);
					ps.setInt(3, endrow);
					break;

				}
			}

			// ps.setInt(2, startrow);
			// ps.setInt(3, endrow);
			// ps.setString(1, "%"+search+"%");

			rs = ps.executeQuery();

			while (rs.next()) {

				bId = rs.getInt("bId");

				bName = rs.getString("bName");
				bTitle = rs.getString("bTitle");
				bContent = rs.getString("bContent");

				bHit = rs.getInt("bhit");
				bGroup = rs.getInt("bgroup");
				bIndent = rs.getInt("bindent");
				bStep = rs.getInt("bstep");

				bDate = rs.getTimestamp("bdate");

				dto = new BDto(bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent);

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

	
	
	
	
	
	public int getlistCount(String opt, String search) {

		int count = 0;

		if (opt == null) {

			sql = "select count(*) as count from mvc_board";
		}

		else {

			switch (opt) {

			case "":
				sql = "select count(*) as count from mvc_board";
				break;

			case "all":
				sql = "select count(*) as count from mvc_board where bcontent like ? or btitle like ?";
				break;

			case "title":
				sql = "select count(*) as count from mvc_board where btitle like ?";
				break;

			case "con":
				sql = "select count(*) as count from mvc_board where bcontent like ?";
				break;

			}

		}

		try {

			con = ds.getConnection();
			ps = con.prepareStatement(sql);

			if (opt == null) {

			}

			else {

				switch (opt) {

				case "all":
					ps.setString(1, "%"+search+"%");
					ps.setString(2, "%"+search+"%");
					break;

				case "title":
					ps.setString(1, "%"+search+"%");
					break;

				case "con":
					ps.setString(1, "%"+search+"%");
					break;

				}

			}

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

	
	
	
	
	
	
	
	
	
	// 1개 select - contentView
	public BDto contentView(int bId) {

		upHit(bId);

		sql = "select * from mvc_board where bId=?";

		try {

			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, bId);
			rs = ps.executeQuery();

			// 1개는 if

			if (rs.next()) {

				bId = rs.getInt("bId");
				bName = rs.getString("bName");
				bTitle = rs.getString("bTitle");
				bContent = rs.getString("bContent");
				bDate = rs.getTimestamp("bDate");
				bHit = rs.getInt("bHit");
				bGroup = rs.getInt("bGroup");
				bStep = rs.getInt("bStep");
				bIndent = rs.getInt("bIndent");

				dto = new BDto(bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent);

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

			}

			catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return dto;

	}// 1개 select - contentView

	
	
	
	
	public BDto precontentView(int bId) {

		upHit(bId);

		sql = "select * from mvc_board where bid=(select max(bid) from mvc_board where bid<?)";

		try {

			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, bId);
			rs = ps.executeQuery();

			// 1개는 if

			if (rs.next()) {

				bId = rs.getInt("bId");
				bName = rs.getString("bName");
				bTitle = rs.getString("bTitle");
				bContent = rs.getString("bContent");
				bDate = rs.getTimestamp("bDate");
				bHit = rs.getInt("bHit");
				bGroup = rs.getInt("bGroup");
				bStep = rs.getInt("bStep");
				bIndent = rs.getInt("bIndent");

				dto = new BDto(bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent);

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

			}

			catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return dto;

	}// 1개 select - precontentView
	
	
	
	
	
	//main용
	public String maincontentView() {
		
		String bt  =null;

		sql = "select * from mvc_board where bid=(select max(bid) from mvc_board)";

		try {

			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			// 1개는 if

			if (rs.next()) {

				bTitle = rs.getString("bTitle");
				
				bt = bTitle;

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

			}

			catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return bt;

	}//main 
	
	
	
	
	
	
	
	
	public BDto nextcontentView(int bId) {

		upHit(bId);
		

		sql = "select * from mvc_board where bid=(select min(bid) from mvc_board where bid>?)";

		
		try {

			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, bId);
			rs = ps.executeQuery();

			// 1개는 if

			if (rs.next()) {

				bId = rs.getInt("bId");
				bName = rs.getString("bName");
				bTitle = rs.getString("bTitle");
				bContent = rs.getString("bContent");
				bDate = rs.getTimestamp("bDate");
				bHit = rs.getInt("bHit");
				bGroup = rs.getInt("bGroup");
				bStep = rs.getInt("bStep");
				bIndent = rs.getInt("bIndent");

				dto = new BDto(bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent);

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

			}

			catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return dto;

	}// 1개 select - nextcontentView
	
	
	
	
	
	
	
	
	
	
	// 조회수 1 증가 - update
	public int upHit(int bId) {

		// 조회수 1증가
		// update mvc_board set bHit=? where bId=?

		int check = 0;

		sql = "update mvc_board set bHit=bHit+1 where bId=?";

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
	}// 조회수 1증가 - update

	
	
	
	
	
	
	
	
	// 게시글 추가 write -insert
	public int write(String bName, String bTitle, String bContent) {

		int check = 0;

		sql = "insert into mvc_board values(mvc_board_sequence.nextval,?,?,?,sysdate,0,mvc_board_sequence.currval,0,0)";
		// currval 현재번호

		try {

			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, bName);
			ps.setString(2, bTitle);
			ps.setString(3, bContent);
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

		sql = "delete mvc_board where bid=?";

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

	
	
	
	
	
	
	
	
	public BDto reply(int bId) {

		int check = 0;

		sql = "select * from mvc_board where bId=?";

		try {

			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, bId);
			rs = ps.executeQuery();

			// 1개는 if

			if (rs.next()) {

				bId = rs.getInt("bId");
				bName = rs.getString("bName");
				bTitle = rs.getString("bTitle");
				bContent = rs.getString("bContent");
				bDate = rs.getTimestamp("bDate");
				bHit = rs.getInt("bHit");
				bGroup = rs.getInt("bGroup");
				bStep = rs.getInt("bStep");
				bIndent = rs.getInt("bIndent");

				dto = new BDto(bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent);

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

			}

			catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return dto;

	}

	
	
	
	
	
	
	
	
	// 답글달기 (reply)
	public int reply_ok(int bId, String bName, String bTitle, String bContent, int bGroup, int bStep, int bIndent) {

		int check = 0;

		// 답글달려 있는 리스트 step 1씩 증가
		replyshape(bGroup, bStep);

		sql = "insert into mvc_board values(mvc_board_sequence.nextval,?,?,?,sysdate,0,?,?,?)";

		try {

			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, bName);
			ps.setString(2, bTitle);
			ps.setString(3, bContent);
			ps.setInt(4, bGroup);
			ps.setInt(5, bStep + 1);
			ps.setInt(6, bIndent + 1);

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

	}// 답글달기 (reply)

	
	
	
	
	
	
	
	// 답글 step 1씩 증가
	public void replyshape(int bGroup, int bStep) {

		sql = "update mvc_board set bStep=bStep+1 where bGroup=? and bStep>?";

		try {

			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, bGroup);
			ps.setInt(2, bStep);
			ps.executeUpdate();

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

	}// 답글 step 1씩 증가

	
	
	
	
	
	
	public BDto modify(int bId) {

		sql = "select * from mvc_board where bId=?";

		try {

			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, bId);
			rs = ps.executeQuery();

			// 1개는 if

			if (rs.next()) {

				bId = rs.getInt("bId");
				bName = rs.getString("bName");
				bTitle = rs.getString("bTitle");
				bContent = rs.getString("bContent");
				bDate = rs.getTimestamp("bDate");
				bHit = rs.getInt("bHit");
				bGroup = rs.getInt("bGroup");
				bStep = rs.getInt("bStep");
				bIndent = rs.getInt("bIndent");

				dto = new BDto(bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent);

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

	
	
	
	
	
	
	
	public int modify_ok(int bId, String bTitle, String bContent) {

		int check = 0;

		sql = "update mvc_board set bTitle=? , bContent=? where bId=?";

		try {

			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, bTitle);
			ps.setString(2, bContent);
			ps.setInt(3, bId);

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
