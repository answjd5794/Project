package bookStore;

import java.util.*;

public interface IService {

	/**
	 * 회원가입을 위한 메서드
	 * 
	 * @method createMember
	 * @param mv
	 * @return
	 * @return int
	 * @author 강문정
	 * @since 2020. 9. 9.오후 12:11:04
	 */
	int createMember(MemberVO mvo);

	/**
	 * 회원 로그인을 위한 메서드
	 * 
	 * @method memberLogin
	 * @param params
	 * @return
	 * @return String
	 * @author 강문정
	 * @since 2020. 9. 9.오후 12:12:52
	 */
	String logIn(Map<String, String> params);

	/**
	 * 관리자 로그인을 위한 메서드
	 * 
	 * @method managerLogin
	 * @param params
	 * @return
	 * @return String
	 * @author 강문정
	 * @since 2020. 9. 9.오후 12:14:05
	 */
	String managerLogin(Map<String, String> params);

	/**
	 * 
	 * @param bvo
	 */
	void itUpdate(BooksVO bvo);

	/**
	 * 
	 * @method HistoryList
	 * @return
	 * @return List<String>
	 * @author 강문정
	 * @since 2020. 9. 9.오후 7:58:47
	 */
	List<String> HistoryList();

	/**
	 * 
	 * @method SportsList
	 * @return
	 * @return List<String>
	 * @author 강문정
	 * @since 2020. 9. 9.오후 8:00:20
	 */
	List<String> SportsList();

	/**
	 * 
	 * @method bucketInput
	 * @param bvo
	 * @return
	 * @return List<String>
	 * @author 강문정
	 * @since 2020. 9. 9.오후 8:22:25
	 */
	List<String> bucketInput(BooksVO bvo);

	/**
	 * 
	 * @method deleteCart
	 * @param member
	 * @return
	 * @return int
	 * @author 강문정
	 * @since 2020. 9. 9.오후 8:40:53
	 */
	int deleteCart(String member);

	/**
	 * Cart에서 선택해서 삭제
	 * 
	 * @param cart_id
	 * @return
	 */
	int deleteChoice(int cart_id);

	/**
	 * id중복검사를 위한 메서드
	 * 
	 * @param mem_id
	 *            회원의 아이디
	 * @return id가 일치하는 회원이 있을때 1을 일치하는 회원이 없을떄 0을 반환
	 * @author 서대철
	 * @since 2020.09.08
	 */
	int dupleId(String mem_id);

	/**
	 * 포인트충전
	 * 
	 * @method pointCharge
	 * @param mvo
	 * @return void
	 * @author 강문정
	 * @since 2020. 9. 9.오후 9:40:31
	 */
	void pointCharge(MemberVO mvo);

	/**
	 * 포인트 조회
	 * 
	 * @method pointSelect
	 * @param mvo
	 * @return
	 * @return List<String>
	 * @author 강문정
	 * @since 2020. 9. 9.오후 9:40:39
	 */

	List<String> pointSelect(MemberVO mvo);

	/**
	 * 장바구니
	 * 
	 * @method bucketInput
	 * @return
	 * @return List<CartVO>
	 * @author 강문정
	 * @since 2020. 9. 9.오후 11:11:19
	 */
	List<CartVO> bucketInput();

	/**
	 * 주문내역 조회를 위한 메서드
	 * 
	 * @method buyList
	 * @return
	 * @return List<String>
	 * @author 강문정
	 * @since 2020. 9. 9.오후 11:40:58
	 */
	List<String> buyList();

	/**
	 * @method sellList
	 * @param order
	 * @return
	 * @return int
	 * @author 강문정
	 * @since 2020. 9. 10.오전 12:22:44
	 */
	int sellList(String order);

	/**
	 * 회원 개인정보 변경전 비밀번호 확인받는 메서드
	 * 
	 * @method memUpdatePass
	 * @param uppass
	 * @return
	 * @return int
	 * @author 강문정
	 * @since 2020. 9. 10.오전 12:32:48
	 */
	int memUpdatePass(String uppass);

	/**
	 * 회원정보 변경 비밀번호 변경 메서드
	 * 
	 * @method inputPass
	 * @param mem_pass
	 * @return
	 * @return int
	 * @author 강문정
	 * @since 2020. 9. 10.오전 12:38:22
	 */
	int inputPass(String mempass);

	/**
	 * 회원정보 변경 이름 변경 메서드
	 * 
	 * @method inputName
	 * @param memname
	 * @return
	 * @return int
	 * @author 강문정
	 * @since 2020. 9. 10.오전 12:39:54
	 */
	int inputName(String memname);

	/**
	 * 회원정보 변경 주소 변경 메서드
	 * 
	 * @method inputAdd1
	 * @param memadd1
	 * @return
	 * @return int
	 * @author 강문정
	 * @since 2020. 9. 10.오전 12:41:55
	 */
	int inputAdd1(String memadd1);

	/**
	 * 회원정보 상세주소 변경 메서드
	 * 
	 * @method inputAdd2
	 * @param memadd2
	 * @return
	 * @return int
	 * @author 강문정
	 * @since 2020. 9. 10.오전 12:43:44
	 */
	int inputAdd2(String memadd2);

	/**
	 * 회원정보 핸드폰
	 * 
	 * @method inputPh2one
	 * @param memphone
	 * @return
	 * @return int
	 * @author 강문정
	 * @since 2020. 9. 10.오전 12:46:05
	 */
	int inputPhone(String memphone);

	/**
	 * 회원 로그아웃 메서드
	 * 
	 * @method memLogOut
	 * @param mem
	 * @return
	 * @return int
	 * @author 강문정
	 * @since 2020. 9. 10.오전 12:52:21
	 */
	int memLogOut(String mem);

	/**
	 * 포인트 충전 메서드
	 * 
	 * @method pointCharge
	 * @param mem
	 * @return
	 * @return int
	 * @author 강문정
	 * @since 2020. 9. 10.오전 12:54:24
	 */
	int pointCharge(int mem);

	/**
	 * 카트 주문
	 * 
	 * @method cartOrder
	 * @param cart
	 * @return
	 * @return int
	 * @author 강문정
	 * @since 2020. 9. 10.오전 1:15:01
	 */
	int cartOrder(String cart);

	/**
	 * 멤버 리스트 조회
	 * 
	 * @method memList
	 * @return
	 * @return List<String>
	 * @author 강문정
	 * @since 2020. 9. 10.오전 1:17:34
	 */
	List<MemberVO> memList();
	
	
	/**
	 * 멤버 비밀번호 수정
	 * 
	 * @method memUpdatePass
	 * @param mem
	 * @return
	 * @return int
	 * @author 강문정
	 * @since 2020. 9. 10.오전 1:21:52
	 */
	int memUpdatePass(int mem);

	/**
	 * 서적 조회 메서드
	 * 
	 * @method bookList
	 * @param bvo
	 * @return
	 * @return List<String>
	 * @author 강문정
	 * @since 2020. 9. 10.오전 1:32:32
	 */
//	List<String> bookList(BooksVO bvo);

	/**
	 * 회원의 비밀번호를 변경하는 메서드
	 * 
	 * @param params
	 * @return
	 */
	int passUpdate(Map<String, String> params);

	/**
	 * 회원의 이름을 변경하는 메서드
	 * 
	 * @param params
	 * @return
	 */
	int nameUpdate(Map<String, String> params);

	/**
	 * 회원의 주소를 변경하는 메서드
	 * 
	 * @param params
	 * @return
	 */
	int add1Update(Map<String, String> params);

	/**
	 * 회원의 상세주소를 변경하는 메서드
	 * 
	 * @param params
	 * @return
	 */
	int add2Update(Map<String, String> params);

	/**
	 * 회원의 휴대폰번호를 변경하는 메서드
	 * 
	 * @param params
	 * @return
	 */
	int phoneUpdate(Map<String, String> params);

	/**
	 * IT서적 조회 메서드
	 * 
	 * @param bvo
	 * @return
	 */
	List<BooksVO> itList(BooksVO bvo);

	/**
	 * 역사서적 조회 메서드
	 * 
	 * @param bvo
	 * @return
	 */
	List<BooksVO> HistoryList(BooksVO bvo);

	/**
	 * 스포츠서적 조회 메서드
	 * 
	 * @param bvo
	 * @return
	 */
	List<BooksVO> SportsList(BooksVO bvo);

	List<BooksVO> bookList();

	List<BooksVO> showIT();


}