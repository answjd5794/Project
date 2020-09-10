package bookStore;

import java.util.List;
import java.util.Map;

public class IServiceImpl implements IService {

	private IDao dao = new IDaoImpl();

	/**
	 * 관리자 서적 조회 메서드
	 * 
	 * @method bookList
	 * @return int
	 * @author 강문정
	 * @since 2020. 9. 10.오전 2:08:47
	 */
	@Override
	public List<BooksVO> bookList() {
		List<BooksVO> bookList = dao.booKList();
		return bookList;
	}

	@Override
	public List<MemberVO> memList() {
		return dao.memList();
	}

	@Override
	public List<ReviewVO> reviewList() {
		return dao.reviewContent();
	}

	/**
	 * 회원가입
	 * 
	 * @param mvo
	 * @return
	 * @author 서대철
	 * @since 2020.09.10
	 */
	@Override
	public int createMember(MemberVO mvo) {
		return dao.createMember(mvo);
	}

	@Override
	public int orderIT(BooksVO bvo) {
		return 0;
	}
	
	@Override
	public List<BooksVO> showIT() {
		List<BooksVO> showIT = dao.showIT();
		return showIT;
	}

	
	@Override
	public String memberLogin(Map<String, String> params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> bucketInput(BooksVO bvo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteCart(String member) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteChoice(int cart_id) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * 중복검사
	 * 
	 * @param mem_id
	 * @return 반환되는 값이 1이면 중복, 0이면 사용가능
	 * @author 서대철
	 * @since 2020.09.10
	 */
	@Override
	public int dupleId(String mem_id) {

		return dao.dupleId(mem_id);
	}

	@Override
	public void pointCharge(MemberVO mvo) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<String> pointSelect(MemberVO mvo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CartVO> bucketInput() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> buyList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int sellList(String order) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int memUpdatePass(String uppass) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int inputPass(String mempass) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int inputName(String memname) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int inputAdd1(String memadd1) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int inputAdd2(String memadd2) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int inputPhone(String memphone) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int memLogOut(String mem) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int pointCharge(int mem) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int cartOrder(String cart) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int memUpdatePass(int mem) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<String> orderList(OrdersVO ovo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> refundList(RefundVO rvo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> reviewList(OrdersVO ovo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int bookDelete(String book) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int createBook(BooksVO bvo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int memDrop(MemberVO mvo) {
		int result = dao.memDrop();
		return result;
	}

//	@Override
//	public String managerLogin(Map<String, String> params) {
//		return dao.managerlogIn(params);
//	}


}