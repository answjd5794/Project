package bookStore;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class IDaoImpl implements IDao {
	static final String url = "jdbc:oracle:thin:@192.168.45.45:1521/XE";
	static final String id = "SEO";
	static final String pass = "java";
	
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
	public String login(Map<String, String> params) {
		// TODO Auto-generated method stub
		return null;
	}

	// 주문목록 조회
	@Override
	public List<OrdersVO> orderList() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		List<OrdersVO> list = new ArrayList<>();
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url,id,pass);
			stmt = conn.createStatement();
			
			String sql = "SELECT * "
					+ "FROM ORDERS ";
			
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				OrdersVO ovo = new OrdersVO();
				ovo.setOrder_id(rs.getString("ORDER_ID"));
				ovo.setOrder_date(rs.getString("ORDER_DATE"));
				ovo.setOrder_qty(rs.getInt("ORDER_QTY"));
				ovo.setCart_id(rs.getString("CART_ID"));
				ovo.setMem_id(rs.getString("MEM_ID"));
				
				//담아주기
				list.add(ovo);
			}
			
		}catch(ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
		}catch(SQLException e){
			System.out.println("로딩 실패");
		}finally {
			try{
				if(stmt != null){
					stmt.close();
				}
				if(conn != null){
					conn.close();
				}
			}catch(SQLException e){
				System.out.println("자원 반환 실패");
			}
		}
		return list;
	}

	// 환불조회
	@Override
	public List<RefundVO> refundList() {
		System.out.println();
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		List<RefundVO> list = new ArrayList<>();
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url,id,pass);
			stmt = conn.createStatement();
			
			String sql = "SELECT * "
					+ "FROM REFUND ";
			
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				RefundVO rvo = new RefundVO();
				rvo.setOrder_id(rs.getString("ORDER_ID"));
				rvo.setRefund_date(rs.getString("REFUND_DATE"));
				
				list.add(rvo);
				
				//담아주기
				list.add(rvo);
			}
			
		}catch(ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
		}catch(SQLException e){
			System.out.println("로딩 실패");
		}finally {
			try{
				if(stmt != null){
					stmt.close();
				}
				if(conn != null){
					conn.close();
				}
			}catch(SQLException e){
				System.out.println("자원 반환 실패");
			}
		}
		return list;
	}
	
	
	//서적삭제
	@Override
	public int bookDelete(BooksVO bvo) {
		String input = bvo.getBook_name();

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		int result = 0;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, id, pass);
			stmt = conn.createStatement();

			String sql = "DELETE BOOKS "  
					   + "WHERE BOOK_NAME = '" + input +"' ";
					
			stmt.executeUpdate(sql);
//			 System.out.println(result);

		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
		} catch (SQLException e) {
			System.out.println("로딩 실패");
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("자원 반환 실패");
			}
		}
		return result;
	}

	@Override
	public String bookCreate(BooksVO bvo) {
		String input1 = bvo.getBook_name();
		String input2 = bvo.getBook_pub_name();
		String input3 = bvo.getBook_writer();
		String input4 = bvo.getBook_pub_date();
		String input5 = bvo.getBook_category();
		int input6 = bvo.getBook_price();
		
		Scanner scan = new Scanner(System.in);
		
		Connection conn = null;
		Statement stmt = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection(url, id, pass);
			stmt = conn.createStatement();
			
			String sql = "INSERT INTO BOOKS(BOOK_ID, BOOK_NAME, BOOK_PUB_NAME, BOOK_WRITER, BOOK_PUB_DATE, BOOK_PRICE, BOOK_CATEGORY) "
					+    "VALUES(BOOKS_SEQ.NEXTVAL,'" + input1 +"','" + input2 +"','" + input3 + "','" + input4 + "','" + input6 +"','" + input5+"')";
			int result = stmt.executeUpdate(sql);
			
			if(result != 0){
				input1 = bvo.getBook_name();
				input2 = bvo.getBook_pub_name();
				input3 = bvo.getBook_writer();
				input4 = bvo.getBook_pub_date();
			}
			
		
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
		
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("로딩 실패");
		
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("자원 반환 실패");
			}
		}
		return null;
			
	}

	@Override
	public String bookEdit(BooksVO bvo) {
		String input1 = bvo.getBook_name();
		String input2 = bvo.getBook_pub_name();
		String input3 = bvo.getBook_writer();
		String input4 = bvo.getBook_pub_date();
		String input5 = bvo.getBook_category();
		int input6 = bvo.getBook_price();
		String input7 = bvo.getBook_name();
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		int result = 0;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		
			conn = DriverManager.getConnection(url, id, pass);
			stmt = conn.createStatement();
			
			
			String sql = "UPDATE BOOKS "
					+    "SET BOOK_ID = BOOKS_SEQ.NEXTVAL, " 
					+	    " BOOK_NAME = '" + input1 +"',"
					+	    " BOOK_PUB_NAME = '" + input2 +"',"
					+       " BOOK_WRITER = '" + input3 +"',"
					+       " BOOK_PUB_DATE = '" + input4 +"',"
					+		" BOOK_CATEGORY = '" + input5 +"',"
					+		" BOOK_PRICE = '" + input6 +"'"
					+    "WHERE BOOK_NAME = '" + input7 +"'";
			stmt.executeUpdate(sql);
		
			sql = "SELECT BOOKS "
					+    "SET BOOK_ID = BOOKS_SEQ.NEXTVAL,' " 
					+	    " BOOK_PUB_NAME = '" + input1 +"'"
					+       " BOOK_WRITER = '" + input2 +"'"
					+       " BOOK_PUB_DATE = '" + input3 +"'"
					+		" BOOK_CATEGORY = '" + input4 +"'"
					+		" BOOK_PRICE = '" + input5 +"'";
			rs = stmt.executeQuery(sql);
			
//			while(rs.next()){
//				OrdersVO ovo = new OrdersVO();
//				ovo.setOrder_id(rs.getString("ORDER_ID"));
//				ovo.setOrder_date(rs.getString("ORDER_DATE"));
//				ovo.setOrder_qty(rs.getInt("ORDER_QTY"));
//				ovo.setCart_id(rs.getString("CART_ID"));
//				ovo.setMem_id(rs.getString("MEM_ID"));
//				
//				//담아주기
//				list.add(ovo);
//			}

		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
		
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("로딩 실패");
		
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("자원 반환 실패");
			}
		}
		return null;
	}


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
				bvo.setBook_id(rset.getInt("book_id"));
				bvo.setBook_name(rset.getString("book_name"));
//				bvo.setBook_date(rset.getString("book_date"));
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


	@Override
	public List<BooksVO> booKList() {
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
                bvo.setBook_id(rs.getInt("BOOK_ID"));
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

    
    /**
     * 서적 주문을 했을 때 장바구니에 담기는 메서드
     */
	@Override
	public List<CartVO> bucketInput() {
		  Connection conn = null;
          Statement stmt = null;
          ResultSet rs = null;

          List<CartVO> bookList = new ArrayList<CartVO>();

          try {
             Class.forName("oracle.jdbc.driver.OracleDriver");
             conn = DriverManager.getConnection(url, id, pass);
             stmt = conn.createStatement();

             String sql = "SELECT * " + 
            		 "     FROM  CART ";

             rs = stmt.executeQuery(sql);

             while (rs.next()) {
                CartVO cvo = new CartVO();
                cvo.setCart_id(rs.getString("cart_id"));
                // 담아주기
                bookList.add(cvo);
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
