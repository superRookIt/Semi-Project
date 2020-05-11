package com.javalec.ex.BDao;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.javalec.ex.BDto.BDto2;
import com.javalec.ex.BDto.BDto3;
import com.javalec.ex.BDto.BDto3_h;


public class BDao3 {
	
	
	// 객체선언

		Connection con = null;
		Context context = null;
		DataSource ds = null;

		// 객체선언
		ArrayList<BDto3> list = new ArrayList<BDto3>();
		BDto3 mdto = new BDto3();
		
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "";
		
		
		BDto3 dto3=null;
		
		
		String name, id, pw, email, address, phone, gender, news, sms, day;
		
		
		
		// 생성자 - 커넥션풀 연결(객체선언)
		public BDao3() {

			try {

				context = new InitialContext();
				ds = (DataSource) context.lookup("java:comp/env/jdbc/oracle11g");

			} catch (Exception e) {
				e.printStackTrace();
			}

		}// 생성자 - 커넥션풀 연결(객체선언)



		
		
		// 아이디 중복 체크
		public int confirmId(String id) {
			
			int check = 0;
			
			sql="select id from mvc_mem where id=?";
			
			try {
				
				con=ds.getConnection();
				ps=con.prepareStatement(sql);
				ps.setString(1, id);
				rs=ps.executeQuery();
				
				if(rs.next()) {
					
					//중복 아이디가 존재
					check=1;
					
					
				}else {
					//중복 아이디가 없음
					
					check=0;
					
				}
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				try {
					
					if(rs!=null) rs.close();
					if(con!=null) con.close();
					if(ps!=null) ps.close();
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}		
			

			return check;
			
		}

	
		
		
		
		//회원 가입 입력 메소드
		public int insertMember(BDto3 mdto) {
			
			int check =0;
			
			sql ="insert into mvc_mem values (?,?,?,?,?,?,?,?,?,?)";
			
			
			try {
				
				con=ds.getConnection();
				
				ps=con.prepareStatement(sql);
				
				ps.setString(1, mdto.getName());
				ps.setString(2, mdto.getId());
				ps.setString(3, mdto.getPw());
				ps.setString(4, mdto.getEmail());
				ps.setString(5, mdto.getAddress());
				ps.setString(6, mdto.getGender());
				ps.setString(7, mdto.getNews());
				ps.setString(8, mdto.getSms());
				ps.setString(9, mdto.getPhone());
				ps.setString(10, mdto.getDay());
			
				check = ps.executeUpdate();
				
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {

					if (con != null) con.close();
					if (ps != null) ps.close();

				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			
			
			return check;
		}
		
		
		
		    //회원 가입 입력 메소드_취미만 분리 ㅡㅡ...
		
				public int hobbyMember(String id, String hobby) {
					
					
					int check =0;
					
					sql ="insert into hobby values (?,?)";
					
					
					try {
						
						con=ds.getConnection();
						
						ps=con.prepareStatement(sql);
						
						ps.setString(1, id);
						ps.setString(2, hobby);
					
						check = ps.executeUpdate();
						
						
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						try {

							if (con != null) con.close();
							if (ps != null) ps.close();

						} catch (Exception e2) {
							e2.printStackTrace();
						}
					}
					
					
					return check;
				}
		
		
		
		
		
		
		
	
		// 멤버 1개
		public BDto3 getMember(String id) {
			
			sql = "select * from mvc_mem where id=?";

			try {

				con = ds.getConnection();
				ps = con.prepareStatement(sql);
				ps.setString(1, id);
				rs = ps.executeQuery();

				if (rs.next()) {

					// if(rs !=null) -> rs null 리턴값을 돌려줍니다.

					mdto = new BDto3();

					mdto.setId(rs.getString("id"));
					mdto.setPw(rs.getString("pw"));
					mdto.setName(rs.getString("name"));

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

			return mdto;
		}
		
		
		
		
		
		
		// 아이디,패스워드 체크 메소드
		public int userCheck(String id, String pw) {

			int check = 0;// db에서 결과 리턴값

			
			sql = "select id,pw from mvc_mem where id=?";

			try {

				String ch_pw;

				con = ds.getConnection();
				
				ps = con.prepareStatement(sql);
				ps.setString(1, id);
				rs = ps.executeQuery();

				if (rs.next()) {

					ch_pw = rs.getString("pw");

					if (ch_pw.equals(pw)) {

						check = 1; // 패스워드 일치

					} else {

						check = 0; // 패스워드 불일치

					}

				} else {
					// 데이터가 없을 경우
					check = -1; // 아이디가 존재하지 않음

				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {

					if (rs != null) rs.close();
					if (con != null) con.close();
					if (ps != null) ps.close();

				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}

			return check; 
		}// userCheck
		
		
		
		
		
		
		public BDto3 m_modify(String id) {

			sql = "select * from mvc_mem where id=?";

			try {

				con = ds.getConnection();
				ps = con.prepareStatement(sql);
				ps.setString(1, id);
				rs = ps.executeQuery();

				// 1개는 if

				if (rs.next()) {

					name = rs.getString("name");

					id = rs.getString("id");

					pw = rs.getString("pw");

					email = rs.getString("email");

					address = rs.getString("address");
					
					phone = rs.getString("phone");
					
					gender = rs.getString("gender");
					
					news = rs.getString("news");
					
					sms = rs.getString("sms");
					
					day = rs.getString("day");
					
					dto3 = new BDto3(name, id, pw, email, address, phone, gender, news, sms, day);

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

			return dto3;

		}
		
		
		
		
		
		
		
		public BDto3_h m_modify_h(String id) {
			
			BDto3_h bh= new BDto3_h();
			
			String hobby;
			
			sql = "select * from hobby where id=?";

			try {

				con = ds.getConnection();
				ps = con.prepareStatement(sql);
				ps.setString(1, id);
				rs = ps.executeQuery();

				// 1개는 if

				if (rs.next()) {

					id = rs.getString("id");
					
					hobby = rs.getString("hobby");
					
					bh = new BDto3_h(id, hobby);
					
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

			return bh;

		}
		
		
		
		
		
		
		
		//회원 정보수정
		public int updateMember(BDto3 mdto) {
					
			int check =0;
					
		     sql ="update mvc_mem set email=?, address=?, gender=?, news=?, sms=?, phone=?, day=? where id=?";
					
					
			try {
						
						con=ds.getConnection();
						
						ps=con.prepareStatement(sql);
						
						ps.setString(1, mdto.getEmail());
						ps.setString(2, mdto.getAddress());
						ps.setString(3, mdto.getGender());
						ps.setString(4, mdto.getNews());
						ps.setString(5, mdto.getSms());
						ps.setString(6, mdto.getPhone());
						ps.setString(7, mdto.getDay());
						ps.setString(8, mdto.getId());
					
						check = ps.executeUpdate();
						
						
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						try {

							if (con != null) con.close();
							if (ps != null) ps.close();

						} catch (Exception e2) {
							e2.printStackTrace();
						}
					}
					
					
					return check;
				}
		
		
		
		
		
		
		
		
		
		
		public int updateMember_h(String id, String hobby) {
			
			int check =0;
			
					
		     sql ="update hobby set hobby=? where id=?";
					
					
			try {
						
						con=ds.getConnection();
						
						ps=con.prepareStatement(sql);
						
						ps.setString(1, hobby);
						ps.setString(2, id);
					
					
						check = ps.executeUpdate();
						
						
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						try {

							if (con != null) con.close();
							if (ps != null) ps.close();

						} catch (Exception e2) {
							e2.printStackTrace();
						}
					}
					
					
					return check;
				}
		
		
		
		
		
		
		
		
		
		
		
		
		
	

}
