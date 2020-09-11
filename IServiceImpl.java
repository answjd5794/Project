package bookStore;

import java.util.List;
import java.util.Map;

public class IServiceImpl implements IService {
	IDao dao = new IDaoImpl();

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

	/**
	 * 회원로그인
	 * 
	 * @param params
	 * @return
	 * @author 서대철
	 * @since 2020.09.10
	 */
	@Override
	public String logIn(Map<String, String> params) {
		String mem_id = dao.logIn(params);
		return mem_id;
	}

	/**
	 * 관리자 로그인
	 * 
	 * @param params
	 * @return
	 * @author 이선엽
	 * @since 2020.09.10
	 */

	@Override
	public String managerLogin(Map<String, String> params) {
		String manager_id = dao.managerlogIn(params);
		return manager_id;
	}

	@Override
	public void itUpdate(BooksVO bvo) {

	}

	@Override
	public List<String> HistoryList() {
		return null;
	}

	@Override
	public List<String> SportsList() {
		return null;
	}

	@Override
	public List<String> bucketInput(BooksVO bvo) {
		return null;
	}

	@Override
	public int deleteCart(String member) {
		return 0;
	}

	@Override
	public int deleteChoice(int cart_id) {
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

	}

	@Override
	public List<String> pointSelect(MemberVO mvo) {
		return null;
	}

	@Override
	public List<CartVO> bucketInput() {
		return null;
	}

	@Override
	public List<String> buyList() {
		return null;
	}

	@Override
	public int sellList(String order) {
		return 0;
	}

	@Override
	public int memUpdatePass(String uppass) {
		return 0;
	}

	@Override
	public int inputPass(String mempass) {
		return 0;
	}

	@Override
	public int inputName(String memname) {
		return 0;
	}

	@Override
	public int inputAdd1(String memadd1) {
		return 0;
	}

	@Override
	public int inputAdd2(String memadd2) {
		return 0;
	}

	@Override
	public int inputPhone(String memphone) {
		return 0;
	}

	@Override
	public int memLogOut(String mem) {
		return 0;
	}

	@Override
	public int pointCharge(int mem) {
		return 0;
	}

	@Override
	public int cartOrder(String cart) {
		return 0;
	}

	@Override
	public int memUpdatePass(int mem) {
		return 0;
	}

//	@Override
//	public List<String> bookList(BooksVO bvo) {
//		return null;
//	}

	@Override
	public int passUpdate(Map<String, String> params) {
		int result = dao.passUpdate(params);
		return result;
	}

	@Override
	public int nameUpdate(Map<String, String> params) {
		int result = dao.nameUpdate(params);
		return result;
	}

	@Override
	public int add1Update(Map<String, String> params) {
		int result = dao.add1Update(params);
		return result;
	}

	@Override
	public int add2Update(Map<String, String> params) {
		int result = dao.add2Update(params);
		return result;
	}

	@Override
	public int phoneUpdate(Map<String, String> params) {
		int result = dao.phoneUpdate(params);
		return result;
	}

	@Override
	public List<BooksVO> itList(BooksVO bvo) {
		List<BooksVO> list = dao.itList(bvo);
		return list;
	}

	@Override
	public List<BooksVO> HistoryList(BooksVO bvo) {
		List<BooksVO> list = dao.historyList(bvo);
		return list;
	}

	@Override
	public List<BooksVO> SportsList(BooksVO bvo) {
		List<BooksVO> list = dao.SportsList(bvo);
		return list;
	}

	@Override
	public List<MemberVO> memList() {
		return dao.memList();
	}

	@Override
	public List<BooksVO> bookList() {
		List<BooksVO> list = dao.bookList();
		return list;
	}

	@Override
	public List<BooksVO> showIT() {
		List<BooksVO> list = dao.showIT();
		return list;
	}



}