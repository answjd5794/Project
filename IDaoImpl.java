package bookStore;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class IDaoImpl implements IDao {
	static final String url = "jdbc:oracle:thin:@localhost:1521/XE";
	static final String id = "SEO";
	static final String pass = "java";

	/**
	 * 회원가입
	 * @author 서대철
	 */
	@Override
	public int createMember(MemberVO mvo) {
		Connection con = null;
		Statement state = null;
		int result = 0;

		// 1. 드라이버 로딩
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. 접속

			con = DriverManager.getConnection(url, id, pass);

			String mem_id = mvo.getMem_id();
			String mem_pass = mvo.getMem_pass();
			String mem_name = mvo.getMem_name();
			String mem_add1 = mvo.getMem_add1();
			String mem_add2 = mvo.getMem_add2();
			String mem_phone = mvo.getMem_phone();
			int mem_point = mvo.getMem_point();

			// 3. 질의
			state = con.createStatement();
			String sql = "INSERT INTO MEMBER (MEM_ID, MEM_PASS, MEM_NAME, MEM_ADD1, MEM_ADD2, MEM_PHONE)"
						+	"VALUES('" + mem_id + "', '"
									   + mem_pass + "', '"
									   + mem_name + "', '"
									   + mem_add1 + "', '"
									   + mem_add2 + "', '"
									   + mem_phone + "')";
			
			result = state.executeUpdate(sql);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("드라이버 로딩 실패");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("접속 실패");
		} finally {
			try{
				if(state != null){
					state.close();
				}
				if(con != null){
					con.close();
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
		}

		return result;
	}
	
	/**
	 * 중복검사
	 */
	@Override
	public int dupleId(String mem_id) {
		Connection con = null;
		Statement state = null;
		ResultSet rset = null;
		int result = 0;
		
		//1. 드라이버 로딩
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 2. 접속
			con = DriverManager.getConnection(url, id, pass);
			
			// 3. 질의
			state = con.createStatement();
			String sql = "SELECT COUNT(MEM_ID) "
						 + "FROM MEMBER "
						+ "WHERE MEM_ID = '" + mem_id + "'";
			
			rset = state.executeQuery(sql);
			
			while(rset.next()){
				result++;
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch(SQLException e){
			e.printStackTrace();
		} finally {
			try{
				if(rset != null){
					rset.close();
				}
				if(state != null){
					state.close();
				}
				if(con != null){
					con.close();
				}
			} catch(SQLException e){
				e.printStackTrace();
			}
		}
		
		
		return result;
	}

	@Override
	public String logIn(Map<String, String> params) {
		String mem_id = params.get("mem_id");
		String mem_pass = params.get("mem_pass");
		
		Connection con = null;
		Statement state = null;
		ResultSet rset = null;
		String logIn_id = null;
		
		// 1. 드라이버 로딩
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
		// 2. 접속
			con = DriverManager.getConnection(url, id, pass);
			
		// 질의
			state = con.createStatement();
			String sql = "SELECT * "
						+  "FROM MEMBER "
						+ "WHERE MEM_ID ='" + mem_id + "'"
						  + "AND MEM_PASS = '" + mem_pass + "'";
			
			rset = state.executeQuery(sql); // 질의 및 결과
			while(rset.next()){
				logIn_id = rset.getString("MEM_ID");
			}
			
		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("드라이버 로드 실패");
		} catch (SQLException e){
			e.printStackTrace();
			System.out.println("접속 실패");
		} finally {
			
			try {
				if(rset != null){
					rset.close();
				}
				if(state != null){
					state.close();
				}
				if(con != null){
					con.close();
				}
				
			} catch (SQLException e){
				e.printStackTrace();
				System.out.println("반환 실패");
			}
		}
		
		return logIn_id;
	}
	
	
	@Override
	public int passUpdate(Map<String, String> params) {
		Connection con = null;
		Statement state = null;
		int result = 0;
		
		String mem_id = params.get("mem_id");
		String mem_pass = params.get("mem_pass");
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			con = DriverManager.getConnection(url, id, pass);
			
			state = con.createStatement();
			String sql = "UPDATE MEMBER "
						  + "SET MEM_PASS = '" + mem_pass + "'"
						+ "WHERE MEM_ID = '" + mem_id + "'";
			
			result = state.executeUpdate(sql);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("드라이버 로딩 실패");
		} catch (SQLException e){
			e.printStackTrace();
			System.out.println("접속 실패");
		} finally {
			try{
				if(state != null){
					state.close();
				}
				if(con != null){
					con.close();
				}
			} catch (SQLException e){
				e.printStackTrace();
			}
		}
		
		return result;
	}

	@Override
	public int nameUpdate(Map<String, String> params) {
		Connection con = null;
		Statement state = null;
		int result = 0;
		
		String mem_id = params.get("mem_id");
		String mem_name = params.get("mem_name");
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			con = DriverManager.getConnection(url, id, pass);
			
			state = con.createStatement();
			String sql = "UPDATE MEMBER "
						  + "SET MEM_NAME = '" + mem_name + "'"
						+ "WHERE MEM_ID = '" + mem_id + "'";
			
			result = state.executeUpdate(sql);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("드라이버 로딩 실패");
		} catch (SQLException e){
			e.printStackTrace();
			System.out.println("접속 실패");
		} finally {
			try{
				if(state != null){
					state.close();
				}
				if(con != null){
					con.close();
				}
			} catch (SQLException e){
				e.printStackTrace();
			}
		}
		
		return result;
	}

	@Override
	public int add1Update(Map<String, String> params) {
		Connection con = null;
		Statement state = null;
		int result = 0;
		
		String mem_id = params.get("mem_id");
		String mem_add1 = params.get("mem_add1");
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			con = DriverManager.getConnection(url, id, pass);
			
			state = con.createStatement();
			String sql = "UPDATE MEMBER "
						  + "SET MEM_ADD1 = '" + mem_add1 + "'"
						+ "WHERE MEM_ID = '" + mem_id + "'";
			result = state.executeUpdate(sql);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("드라이버 로딩 실패");
		} catch (SQLException e){
			e.printStackTrace();
			System.out.println("접속 실패");
		} finally {
			try{
				if(state != null){
					state.close();
				}
				if(con != null){
					con.close();
				}
			} catch (SQLException e){
				e.printStackTrace();
			}
		}
		
		return result;
	}

	@Override
	public int add2Update(Map<String, String> params) {
		Connection con = null;
		Statement state = null;
		int result = 0;
		
		String mem_id = params.get("mem_id");
		String mem_add2 = params.get("mem_add2");
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			con = DriverManager.getConnection(url, id, pass);
			
			state = con.createStatement();
			String sql = "UPDATE MEMBER "
						  + "SET MEM_ADD2 = '" + mem_add2 + "'"
						+ "WHERE MEM_ID = '" + mem_id + "'";
			result = state.executeUpdate(sql);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("드라이버 로딩 실패");
		} catch (SQLException e){
			e.printStackTrace();
			System.out.println("접속 실패");
		} finally {
			try{
				if(state != null){
					state.close();
				}
				if(con != null){
					con.close();
				}
			} catch (SQLException e){
				e.printStackTrace();
			}
		}
		
		return result;
	}

	@Override
	public int phoneUpdate(Map<String, String> params) {
		Connection con = null;
		Statement state = null;
		int result = 0;
		
		String mem_id = params.get("mem_id");
		String mem_phone = params.get("mem_phone");
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			con = DriverManager.getConnection(url, id, pass);
			
			state = con.createStatement();
			String sql = "UPDATE MEMBER "
						  + "SET MEM_PHONE = '" + mem_phone + "'"
						+ "WHERE MEM_ID = '" + mem_id + "'";
			
			result = state.executeUpdate(sql);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("드라이버 로딩 실패");
		} catch (SQLException e){
			e.printStackTrace();
			System.out.println("접속 실패");
		} finally {
			try{
				if(state != null){
					state.close();
				}
				if(con != null){
					con.close();
				}
			} catch (SQLException e){
				e.printStackTrace();
			}
		}
		
		return result;
	}
	

	@Override
	   public String managerlogIn(Map<String, String> params) {
	      String manager_id = params.get("manager_id");
	      String manager_pass = params.get("manager_pass");
	      
	      Connection conn = null;
	      Statement stmt = null;
	      ResultSet rs = null;
	      
	      String logIn_ID = null;
	      
	      try {
	         Class.forName("oracle.jdbc.driver.OracleDriver");
	         conn = DriverManager.getConnection(url, id, pass);
	         stmt = conn.createStatement();
	         
	         String sql = "SELECT MANAGER_ID "
	                      + "FROM MANAGER "
	                     + "WHERE MANAGER_ID = '" + manager_id + "'"
	                      + " AND MANAGER_PASS = '" + manager_pass + "' ";
	         rs = stmt.executeQuery(sql);
	         while(rs.next()){
	            logIn_ID = rs.getString("MANAGER_ID");
	         }
	         
	      } catch (ClassNotFoundException e) {
	         System.out.println("드라이버 로딩에 실패했습니다.");

	      } catch (SQLException e) {
	         e.printStackTrace();
	         System.out.println("로딩에 실패했습니다.");
	      
	      } finally {
	         try{
	            if(rs != null){
	               rs.close();
	            }
	            if(stmt != null){
	               stmt.close();
	            }
	            if(conn != null){
	               conn.close();
	            }
	         }catch(SQLException e){
	            System.out.println("반환 실패");
	         }
	      }
	      
	      return logIn_ID;
	   }

	@Override
	public List<BooksVO> itList(BooksVO bvo) {
		Connection con = null;
		Statement state = null;
		ResultSet rset = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			con = DriverManager.getConnection(url, id, pass);
			
			List<BooksVO> itList = new ArrayList<>();
			
			state = con.createStatement();
			String sql = "SELECT * "
						+  "FROM BOOKS "
						+ "WHERE BOOK_CATEGORY = '" + bvo.getBook_category() + "'";

			rset = state.executeQuery(sql);
			
			while(rset.next()){
				bvo.setBook_id(rset.getString("book_id"));
				bvo.setBook_name(rset.getString("book_name"));
				bvo.setBook_date(rset.getString("book_date"));
				bvo.setBook_price(rset.getShort("book_price"));
				itList.add(bvo);
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e){
			e.printStackTrace();
		} finally {
			try {
				if(rset != null){
					rset.close();
				}
				if(state != null){
					state.close();
				}
				if(con != null){
					con.close();
				}
			} catch (SQLException e){
				e.printStackTrace();
			}
		}
		
		
		return null;
	}

	@Override
	public List<BooksVO> historyList(BooksVO bvo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BooksVO> SportsList(BooksVO bvo) {
		// TODO Auto-generated method stub
		return null;
	}

	

	/**
	    * 관리자 회원목록 조회
	    * 
	    * @author 강문정
	    */
	   @Override
	   public List<MemberVO> memList() {
	      Connection conn = null;
	      Statement stmt = null;
	      ResultSet rs = null;

	      List<MemberVO> memList = new ArrayList<MemberVO>();

	      try {
	         Class.forName("oracle.jdbc.driver.OracleDriver");
	         conn = DriverManager.getConnection(url, id, pass);
	         stmt = conn.createStatement();

	         String sql = "SELECT * " + "     FROM  MEMBER";

	         rs = stmt.executeQuery(sql);
	         // 아이디\t이름\t주소\t상세주소\t핸드폰\t포인트
	         while (rs.next()) {
	            MemberVO mvo = new MemberVO();
	            mvo.setMem_id(rs.getString("MEM_ID"));
	            mvo.setMem_name(rs.getString("MEM_NAME"));
	            mvo.setMem_add1(rs.getString("MEM_ADD1"));
	            mvo.setMem_add2(rs.getString("MEM_ADD2"));
	            mvo.setMem_phone(rs.getString("MEM_PHONE"));
	            mvo.setMem_point(rs.getInt("MEM_POINT"));

	            // 담아주기
	            memList.add(mvo);
	         }

	      } catch (ClassNotFoundException e) {
	         e.printStackTrace();
	         System.out.println("드라이버 로딩 실패");
	      } catch (SQLException e) {
	         e.printStackTrace();
	         System.out.println("로딩실패");
	      } finally {
	         try {
	            if (stmt != null) {
	               stmt.close();
	            }
	            if (conn != null) {
	               conn.close();
	            }
	         } catch (SQLException e) {
	            e.printStackTrace();
	            System.out.println("반환실패");
	         }
	      }
	      return memList;
	   }


	   /**
	    * 관리자 서적 조회를 위한 메서드 주문목록 빼고
	    */
	   @Override
	   public List<BooksVO> bookList() {

	      Connection conn = null;
	      Statement stmt = null;
	      ResultSet rs = null;

	      List<BooksVO> bookList = new ArrayList<BooksVO>();

	      try {
	         Class.forName("oracle.jdbc.driver.OracleDriver");
	         conn = DriverManager.getConnection(url, id, pass);
	         stmt = conn.createStatement();

	         String sql = "SELECT * " + "     FROM  BOOKS";

	         rs = stmt.executeQuery(sql);

	         while (rs.next()) {
	            BooksVO bvo = new BooksVO();
	            bvo.setBook_id(rs.getString("BOOK_ID"));
	            bvo.setBook_name(rs.getString("BOOK_NAME"));
	            bvo.setBook_pub_name(rs.getString("BOOK_PUB_NAME"));
	            bvo.setBook_price(rs.getInt("BOOK_PRICE"));
	            bvo.setBook_writer(rs.getString("BOOK_WRITER"));
	            bvo.setBook_pub_date(rs.getString("BOOK_PUB_DATE"));

	            // 담아주기
	            bookList.add(bvo);
	         }

	      } catch (ClassNotFoundException e) {
	         e.printStackTrace();
	         System.out.println("드라이버 로딩 실패");
	      } catch (SQLException e) {
	         e.printStackTrace();
	         System.out.println("로딩실패");
	      } finally {
	         try {
	            if (stmt != null) {
	               stmt.close();
	            }
	            if (conn != null) {
	               conn.close();
	            }
	         } catch (SQLException e) {
	            e.printStackTrace();
	            System.out.println("반환실패");
	         }
	      }
	      return bookList;
	   }

	@Override
	public List<BooksVO> showIT() {

		
	      Connection conn = null;
	      Statement stmt = null;
	      ResultSet rs = null;

	      List<BooksVO> bookList = new ArrayList<BooksVO>();

	      try {
	         Class.forName("oracle.jdbc.driver.OracleDriver");
	         conn = DriverManager.getConnection(url, id, pass);
	         stmt = conn.createStatement();

	         String sql = "SELECT * " + "     FROM  BOOKS "
	         			 + "WHERE BOOK_CATEGORY = 'IT'";

	         rs = stmt.executeQuery(sql);

	         while (rs.next()) {
	            BooksVO bvo = new BooksVO();
	            bvo.setBook_id(rs.getString("BOOK_ID"));
	            bvo.setBook_name(rs.getString("BOOK_NAME"));
	            bvo.setBook_pub_name(rs.getString("BOOK_PUB_NAME"));
	            bvo.setBook_price(rs.getInt("BOOK_PRICE"));
	            bvo.setBook_writer(rs.getString("BOOK_WRITER"));
	            bvo.setBook_pub_date(rs.getString("BOOK_PUB_DATE"));

	            // 담아주기
	            bookList.add(bvo);
	         }

	      } catch (ClassNotFoundException e) {
	         e.printStackTrace();
	         System.out.println("드라이버 로딩 실패");
	      } catch (SQLException e) {
	         e.printStackTrace();
	         System.out.println("로딩실패");
	      } finally {
	         try {
	            if (stmt != null) {
	               stmt.close();
	            }
	            if (conn != null) {
	               conn.close();
	            }
	         } catch (SQLException e) {
	            e.printStackTrace();
	            System.out.println("반환실패");
	         }
	      }
	      return bookList;
		
	}

	
	

	

	

}