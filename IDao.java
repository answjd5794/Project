package bookStore;

import java.util.List;
import java.util.Map;

public interface IDao {
	
	int createMember(MemberVO mvo);


	int dupleId(String mem_id);


	String logIn(Map<String, String> params);


	int passUpdate(Map<String, String> params);


	int nameUpdate(Map<String, String> params);


	int add1Update(Map<String, String> params);


	int add2Update(Map<String, String> params);


	int phoneUpdate(Map<String, String> params);

	List<BooksVO> itList(BooksVO bvo);


	List<BooksVO> historyList(BooksVO bvo);


	List<BooksVO> SportsList(BooksVO bvo);


	List<BooksVO> booKList();


	List<MemberVO> memList();

	String login(Map<String, String> params);

	String managerlogIn(Map<String, String> params);

	List<OrdersVO> orderList();

	List<RefundVO> refundList();

	int bookDelete(BooksVO bvo);

	String bookCreate(BooksVO bvo);

	String bookEdit(BooksVO bvo);


	
	

	
	
}
