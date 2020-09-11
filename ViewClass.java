
package bookStore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ViewClass {

	private IService service = new IServiceImpl();
	BooksVO bvo = new BooksVO();
	CartVO cavo = new CartVO();
	ManagerVO mavo = new ManagerVO();
	MemberVO mvo = new MemberVO();
	OrdersVO ovo = new OrdersVO();
	RefundVO rvo = new RefundVO();
	// 카테고리
	final String IT = "IT";
	final String HISTORY = "History";
	final String SPORTS = "Sports";

	/**
	 * 서점 관리 프로그램 초기 화면
	 * 
	 * @author 이선엽
	 * @since 2020.09.07
	 */

	public void startMethod() {
		while (true) {
			System.out.println("┌───────────────────────────────┐");
			System.out.println("│    강선철 서점에 오신것을 환영합니다\t│");
			System.out.println("│───────────────────────────────│");
			System.out.println("│\t     메뉴를 고르세요\t\t│");
			System.out.println("└───────────────────────────────┘");
			System.out.print("    1.회원가입  ");
			System.out.print("2.로그인  ");
			System.out.println("0.종료");

			int input = 0;
			try {
				Scanner scan = new Scanner(System.in);
				input = scan.nextInt();
			} catch (InputMismatchException e) {
				System.out.println();
				System.out.println("입력이 올바르지 않습니다. 메뉴에 해당하는 숫자를 입력해주세요.");
			}

			switch (input) {
			case 1:
				// 회원가입
				createMember();
				break;

			case 2:
				// 로그인
				logIn();
				break;

			case 3:
				// 프로그램 종료
				System.exit(0);
				break;
			default:
				System.out.println("번호를 다시 입력하세요");
				break;
			}
		}
	}

	/**
	 * 회원가입 메서드
	 * 
	 * @method createMemberss
	 * @return void
	 * @author 이선엽
	 * @since 2020. 9. 9.오후 8:11:58
	 */

	// sql
	// 반환타입, 메서드명, 매개변수
	// int createMember(MemberVO mv);

	private void createMember() {
		System.out.println("┌───────────────────────────────┐");
		System.out.println("│         회원 가입 정보 입력\t\t│");
		System.out.println("└───────────────────────────────┘");

		String mem_id = inputId();
		String mem_pass = inputPass();
		String mem_name = inputName();
		String mem_add1 = inputAdd1();
		String mem_add2 = inputAdd2();
		String mem_phone = inputPhone();

		int result = service.createMember(mvo);
		if (result == 1) {
			System.out.println("회원가입 성공");
		} else {
			System.out.println("회원가입 실패");
		}

		while (true) {
			System.out.println("┌───────────────────────────────┐");
			System.out.println("│      회원가입이 완료되었습니다.\t│");
			System.out.println("│───────────────────────────────│");
			System.out.println("│\t   로그인 하시겠습니까?   \t│");
			System.out.println("└───────────────────────────────┘");
			System.out.println("1. 예");
			System.out.println("0. 아니오. 프로그램을 종료하겠습니다.");

			int input = 0;
			Scanner scan = new Scanner(System.in);
			try {
				input = scan.nextInt();
			} catch (InputMismatchException e) {
				System.out.println();
				System.out.println("입력이 올바르지 않습니다. 0번 혹은 1번을 입력해주세요.");
			}
			switch (input) {
			case 1:
				logIn();
				break;
			case 2:
				System.out.println("프로그램을 종료합니다.");
				System.exit(0);
			}
		}
	}

	/**
	 * 회원가입 기입항목 1.고객의 아이디 저장 (정규식 확인 + 중복 검사 필요) 메서드
	 * 
	 * @return 가입이 완료된 고객의 아이디 반환
	 * @author 이선엽
	 */

	private String inputId() {
		while (true) {
			Scanner scan = new Scanner(System.in);
			try {
				// 1.id 입력
				System.out.println("영문,숫자 로만 이루어진 5 ~ 12자 이하로 생성해주세요.");
				System.out.print("아이디 : ");
				String mem_id = scan.next();
				mvo.setMem_id(mem_id);

				// 2. id 정규식 확인
				// 시작은 영문으로만, '_'를 제외한 특수문자 안되며 영문, 숫자, '_'으로만 이루어진 5 ~ 12자 이하
				Pattern p = Pattern.compile("^[a-zA-Z]{1}[a-zA-Z0-9]{4,11}$");
				Matcher m = p.matcher(mem_id);

				if (m.matches() == true) {
					System.out.println("사용가능한 아이디입니다.");
					return mem_id;
				} else {
					System.out.println("사용불가능한 아이디입니다.");
				}

				// 3. 중복검사
				int check = 0;
				check = service.dupleId(mem_id);

				if (check == 1) {
					System.out.println("이미 등록 된 아이디입니다.");
				} else {
					System.out.println("사용가능한 아이디입니다.");
					return mem_id;
				}

			} catch (InputMismatchException e) {
				System.out.println("올바르지 않은 아이디 입니다.");
			}

		}
	}

	/**
	 * 회원가입 기입항목 2.고객의 비밀번호(정규식 검사) 메서드
	 * 
	 * @method inputPass
	 * @return String
	 * @author 이선엽
	 * @since 2020. 9. 9.오후 8:25:18
	 */
	private String inputPass() {
		// 1. 비밀번호 입력 받기
		while (true) {
			try {
				Scanner scan = new Scanner(System.in);
				System.out.println("영문,숫자,특수문자 _*로만 이루어진 5 ~ 12자 이하로 생성해주세요.");
				System.out.print("비밀 번호 : ");
				String mem_pass = scan.next();
				mvo.setMem_pass(mem_pass);

				Pattern p = Pattern.compile("^[a-zA-Z]{1}[a-zA-Z0-9_*]{4,11}$");
				Matcher m = p.matcher(mem_pass);
				if (m.matches() == true) {
					System.out.println("사용 가능한 비밀번호 입니다.");
					return mem_pass;
				} else {
					System.out.println("올바르지 않은 비밀번호 입니다.");
				}
			} catch (InputMismatchException e) {
				System.out.println("올바르지 않은 비밀번호 입니다.");
			}
		}
	}

	/**
	 * 
	 * 회원가입 기입항목 3.고객의 이름 저장 메서드
	 * 
	 * @return 가입이 완료된 고객의 이름 반환
	 * @author 이선엽
	 */

	private String inputName() {
		while (true) {
			try {
				// 1. 이름 입력 받기
				Scanner scan = new Scanner(System.in);
				System.out.print("이름 : ");
				String mem_name = scan.next();
				mvo.setMem_name(mem_name);

				// 2. 이름 정규식 확인
				Pattern p = Pattern.compile("^[가-힣]+$");
				Matcher m = p.matcher(mem_name);

				if (m.matches() == true) {
					System.out.println("사용 가능한 이름입니다.");
					return mem_name;
				} else {
					System.out.println("한글 이름만 가능합니다.");
				}

			} catch (InputMismatchException e) {
				System.out.println("올바르지 않은 형식 입니다.");

			}
		}
	}

	/**
	 * 회원가입 기입항목 4.고객의 주소 저장 메서드 sql
	 * 
	 * @return 가입이 완료된 고객의 주소를 반환
	 * @author 이선엽
	 */

	private String inputAdd1() {
		while (true) {
			try {
				Scanner scan = new Scanner(System.in);
				String mem_add1 = null;
				System.out.println("주소 입력 화면입니다.");
				// System.out.println("XX시 XX구 XX동의 형식으로 적어주세요. (시, 구, 동 필수 표기 필수)");
				// 1. 주소 입력 받기
				mem_add1 = scan.nextLine();
				mvo.setMem_add1(mem_add1);
				// 2. 주소 정규식 확인
				Pattern p = Pattern
						.compile("^[가-힣]+[(도||광역시)]\\s[가-힣]+[(시||군||구)]\\s[가-힣]+[(읍||면||동||군||구)]"
								+ "(\\s[가-힣]+[(읍||면||동||리)])?+(\\s[가-힣]+[(읍||면||동||리)])?$");
				Matcher m = p.matcher(mem_add1);
				if (m.matches() == true) {
					System.out.println();
					System.out
							.println("정상적으로 입력되었습니다. 상세 주소 입력 창으로 이동해서 상세 주소를 입력해주세요.");
					System.out.println();
					return mem_add1;
				} else {
					System.out.println("입력이 올바르지 않습니다. 확인 후 다시 입력해주세요.");
				}

			} catch (InputMismatchException e) {
				System.out.println("올바르지 않은 형식의 주소입니다. 확인하고 다시 입력해주세요.");
			}
		}
	}

	/**
	 * 회원가입 기입항목 5.고객의 상세주소 저장 메서드
	 * 
	 * @return 가입이 완료된 고객의 상세 주소를 반환
	 * @author 이선엽
	 */

	private String inputAdd2() {
		Scanner scan = new Scanner(System.in);
		String mem_add2 = null;
		System.out.println("상세 주소 입력 화면입니다. XX동 이하의 세부 주소를 적어주세요.");
		System.out
				.println("상세 주소를 잘못 입력할 시 배달이 정상적으로 진행되지 않을 수 있으며, 이 경우 발생하는 모든 문제는 고객의 책임이 됩니다.");
		System.out.println();
		mem_add2 = scan.nextLine();
		mvo.setMem_add2(mem_add2);

		return mem_add2;
	}

	/**
	 * 회원가입 기입항목 6.고객의 휴대폰 번호 저장 메서드
	 * 
	 * @return 고객의 휴대폰 번호를 반환
	 * @author 이선엽
	 */

	private String inputPhone() {
		while (true) {
			try {
				Scanner scan = new Scanner(System.in);
				System.out.println("휴대폰 번호 입력 화면입니다.");
				System.out
						.println("휴대폰 번호를 XXX-XXXX-XXXX 형식에 맞추어 입력해주세요. (숫자만 입력 가능, '-' 없이 입력 가능");
				// 1. 휴대폰 번호 입력 받기
				String mem_phone = scan.next();
				mvo.setMem_phone(mem_phone);

				// 2. 휴대폰 정규식 확인
				Pattern p = Pattern
						.compile("^01(?:0|1|[6-9])[.-]?([1-9]{1}\\d{2}|[1-9]{1}\\d{3})[.-]?(\\d{4})$");
				Matcher m = p.matcher(mem_phone);
				if (m.matches() == true) {
					return mem_phone;
				} else {
					System.out.println("형식이 일치하지 않습니다. 확인하고 다시 입력해주세요.");
				}
			} catch (InputMismatchException e) {
				System.out.println("올바르지 않은 입력입니다. 확인하고 다시 입력해주세요.");
			}
		}
	}

	/**
	 * 회원 혹은 관리자 로그인 하는 메서드
	 * 
	 * @author 이선엽
	 * @since 2020.09.07
	 */

	private void logIn() {
		while (true) {
			System.out.println("┌───────────────────────────────┐");
			System.out.println("│          메뉴를 고르세요\t\t│");
			System.out.println("└───────────────────────────────┘");
			System.out.print(" 1. 회원 로그인   ");
			System.out.print("2. 관리자 로그인  ");
			System.out.println("0. 뒤로 가기 ");

			int input = 0;
			try {
				Scanner scan = new Scanner(System.in);
				input = scan.nextInt();
				switch (input) {
				case 1:
					// 회원로그인
					memberLogin();
					break;

				case 2:
					// 관리자 로그인
					managerLogin();
					break;

				case 0:
					// 뒤로 가기
					return;
				}
			} catch (InputMismatchException e) {
				System.out.println();
				System.out.println("입력이 올바르지 않습니다.");
			}
		}
	}

	/**
	 * 회원의 로그인 메서드 sql
	 * 
	 * @author 이선엽
	 * @since 2020.09.07
	 */

	private void memberLogin() {

		Scanner scan = new Scanner(System.in);
		String logIn_id = null;
		System.out.println("┌───────────────────────────────┐");
		System.out.println("│    아이디와 비밀번호를 입력하세요\t│");
		System.out.println("└───────────────────────────────┘");

		String mem_id = inputLogId();
		String mem_pass = inputLogPs();

		Map<String, String> params = new HashMap<>();
		params.put("mem_id", mem_id);
		params.put("mem_pass", mem_pass);

		logIn_id = service.logIn(params);

		if (logIn_id == null) {
			System.out.println("아이디와 비밀번호가 일치하지 않습니다.");
		} else {
			System.out.println(logIn_id + "님 어서오세요.");
			afterLogin();
		}
	}

	/**
	 * 로그인용 ID 기입 메서드
	 * 
	 * @return 가입이 완료된 고객의 아이디 반환
	 * @author 서대철
	 * @since 2020.09.10
	 */
	private String inputLogId() {
		// sql
		// SELETE FROM MEMBER WHERE MEM_ID = '';
		Scanner scan = new Scanner(System.in);
		System.out.print("아이디 : ");
		String mem_id = scan.next();

		while (true) {
			if (service.dupleId(mem_id) == 1) {
				return mem_id;
			} else {
				System.out.println("등록된 아이디가 없습니다.");
			}
		}

	}

	/**
	 * 로그인용 비밀번호 기입 메서드
	 * 
	 * @method inputLogPs
	 * @return DB에 있는 아이디와 비교해서 같은 아이디가 있으면 비밀번호를 반환
	 * @author 서대철
	 * @since 2020. 9. 9.오후 8:22:11
	 */

	private String inputLogPs() {
		// sql
		// SELECT mem_pass FROM MEMBER
		// WHERE MEM_ID = mem_id;
		Scanner scan = new Scanner(System.in);
		System.out.print("비밀번호 : ");
		String mem_pass = scan.next();

		return mem_pass;

	}

	/**
	 * 회원 로그인 후 첫 화면 메서드
	 * 
	 * @author 이선엽
	 * @since 2020.09.07
	 */

	private void afterLogin() {
		while (true) {
			System.out
					.println("   ┌────────────────────────────────────────────┐");
			System.out.println("   │\t\t               회원\t\t\t│");
			System.out
					.println("   └────────────────────────────────────────────┘");
			System.out.print("1. 서적 주문  ");
			System.out.print(" 2. 장바구니 조회  ");
			System.out.print(" 3. 내 주문 내역 관리  ");
			System.out.println(" 4. 개인정보 변경 ");
			System.out.print("         5. 내 포인트 관리   ");
			System.out.println("           0. 로그아웃");

			int input = 0;
			// 입력값 변수타입의 범위를 초과했을 때 예외처리
			try {
				Scanner scan = new Scanner(System.in);
				input = scan.nextInt();
				switch (input) {
				case 1:
					// 서적 주문 메서드 호출
					System.out.println("서적 주문 메서드");
					bookOrder();
					break;

				case 2:
					// 장바구니 조회 메서드 호출
					bucketInput();
					break;

				case 3:
					// 내 주문 내역 관리 메서드 호출
					orderInfo();
					break;

				case 4:
					// 개인정보 변경 메서드 호출
					memUpdate();
					break;

				case 5:
					// 내 포인트 관리 메서드
					memPoint();
					break;

				case 0:
					// 회원 로그아웃
					memLogOut();
					return;
				}

			} catch (InputMismatchException e) {
				System.out.println("입력이 올바르지 않습니다.");
			}
		}
	}

	/**
	 * 회원 로그아웃을 위한 메서드
	 * 
	 * @method memLogOut
	 * @return void
	 * @author 강문정
	 * @since 2020. 9. 9.오후 12:30:37
	 */

	private void memLogOut() {
		// sql
		// 반환타입, 메서드명, 매개변수
		// int memberLogOut(String mem_id);
	}

	/**
	 * 회원이 포인트를 관리하는 메서드
	 * 
	 * @method memPoint
	 * @return void
	 * @author 강문정
	 * @since 2020. 9. 9.오후 12:52:18
	 */

	private void memPoint() {
		while (true) {
			System.out.println("┌───────────────────────────┐");
			System.out.println("│\t      내 포인트 관리\t    │");
			System.out.println("└───────────────────────────┘");
			System.out.print("1. 포인트 충전 ");
			System.out.print(" 2. 포인트 조회 ");
			System.out.println(" 0. 뒤로 가기");

			int input = 0;
			try {
				Scanner scan = new Scanner(System.in);
				input = scan.nextInt();
				switch (input) {
				case 1:
					// 포인트 충전 메서드
					// sql
					// int pointCharge(MemberVO mvo);
					pointCharge();
					break;

				case 2:
					// 포인트 조회 메서드
					// sql
					// List<String> pointSelect(memberVO mvo);
					pointSelect();
					break;

				case 0:
					return;
				}

			} catch (InputMismatchException e) {
				System.out.println("입력이 올바르지 않습니다.");
			}
		}
	}

	/**
	 * 포인트 충전 메서드
	 * 
	 * @method pointSelect
	 * @return void
	 * @author 강문정
	 * @since 2020. 9. 9.오후 1:39:21
	 */

	private void pointCharge() {
		while (true) {
			Scanner scan = new Scanner(System.in);
			service.pointCharge(mvo);
			System.out.println("┌───────────────────────────┐");
			System.out.println("│\t      내 포인트 충전\t    │");
			System.out.println("└───────────────────────────┘");
			System.out.println();
			System.out.println("포인트 충전 화면입니다.");
			System.out.println("충전을 원하는 포인트를 기입해주세요.");
			System.out.println();
			System.out.println("1. 5000포인트");
			System.out.println("2. 10000포인트");
			System.out.println("3. 3000포인트");
			System.out.println("4. 50000포인트");
			System.out.println("5. 100000포인트");
			System.out.println("0. 뒤로 가기");
			int input = 0;
			try {
				input = scan.nextInt();
				switch (input) {
				case 1:
					pointChargeInput();
					break;

				case 2:
					pointChargeInput();
					break;

				case 3:
					pointChargeInput();
					break;

				case 4:
					pointChargeInput();
					break;

				case 5:
					pointChargeInput();
					break;

				case 0:
					return;
				}
			} catch (InputMismatchException e) {
				System.out.println("올바른 숫자를 입력해주세요.");
			}
		}
	}

	/**
	 * 포인트 조회 메서드
	 * 
	 * @method pointSelect
	 * @return void
	 * @author 강문정
	 * @since 2020. 9. 9.오후 1:39:21
	 */

	private List<String> pointSelect() {
		List<String> list = new ArrayList<>();
		list = service.pointSelect(mvo);
		System.out.println("┌───────────────────────────┐");
		System.out.println("│\t      내 포인트 조회\t    │");
		System.out.println("└───────────────────────────┘");

		return list;
	}

	/**
	 * 포인트를 충전하여 저장하는 메서드
	 * 
	 * @method pointChargeInput
	 * @return void
	 * @author 이선엽
	 * @since 2020. 9. 9.오후 9:11:19
	 */
	private void pointChargeInput() {
		Scanner scan = new Scanner(System.in);
		System.out.println("충전할 포인트를 입력해 주세요.");
		int input =  scan.nextInt();
		
	}

	/**
	 * 회원 화면의 1.서적 주문 구현 메서드
	 * 
	 * @author 이선엽
	 * @since 2020.09.07
	 */
	private void bookOrder() {
		
		
		while (true) {
			System.out.println("┌───────────────────────────┐");
			System.out.println("│\t      서적주문\t    │");
			System.out.println("└───────────────────────────┘");
			System.out.println("1. IT");
			System.out.println("2. History");
			System.out.println("3. Sports");
			System.out.println("0. 뒤로 가기");

			int input = 0;
			Scanner scan = new Scanner(System.in);
			try {
				input = scan.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("다시 입력해주세요.");
			}
			switch (input) {
			case 1:
				showIT();
				break;

			case 2:
				showHistory();
				break;

			case 3:
				showSports();
				break;

			case 4:
				return;
			}
		}
	}

	/**
	 * 서적을 조회할 수 있는 메서드
	 * 
	 * @method showIT
	 * @return void
	 * @author 강문정
	 * @since 2020. 9. 9.오후 4:05:29
	 */

	private void showIT() {
		System.out.println("┌───────────────────────────┐");
		System.out.println("│\t      IT서적\t    │");
		System.out.println("└───────────────────────────┘");
		
		
		List<BooksVO> showIT = service.showIT();
		System.out.println("┌───────────────────────────────────────┐");
		System.out.println("│번호\t이름\t출판사\t가격\t저자          │");
		System.out.println("└───────────────────────────────────────┘");

		if (showIT.isEmpty() == true) {
			System.out.println("\t it 서적이 존재하지 않습니다.");
		} else {
			for (BooksVO bvo : showIT) {
				System.out.print(bvo.getBook_id() + " ");
				System.out.print(bvo.getBook_name() + " ");
				System.out.print(bvo.getBook_pub_name() + " ");
				System.out.print(bvo.getBook_price() + " ");
				System.out.print(bvo.getBook_writer());
				System.out.println();
			}
		} bookOrder();
	}

	/**
	 * 역사 서적을 조회할 수 있는 메서드
	 * 
	 * @method showIT
	 * @return void
	 * @author 강문정
	 * @since 2020. 9. 9.오후 4:05:29
	 */

	private List<BooksVO> showHistory() {
		System.out.println("┌───────────────────────────┐");
		System.out.println("│\t      역사서적\t    │");
		System.out.println("└───────────────────────────┘");
		// sql
		// SELECT book_name
		// FROM BOOKS
		// WHERE book_category = 'History';
		// 반환타입, 매개변수, 메서드명
		// BookVO에서 카테고리 컬럼이 History인것만 조회
		// List<String> History(book_category);

		List<BooksVO> list = new ArrayList<>();
		list = service.HistoryList(bvo);

		return list;
	}

	/**
	 * 스포츠 서적을 조회할 수 있는 메서드
	 * 
	 * @method showIT
	 * @return void
	 * @author 강문정
	 * @since 2020. 9. 9.오후 4:05:29
	 */

	private List<BooksVO> showSports() {
		System.out.println("┌───────────────────────────┐");
		System.out.println("│\t      스포츠서적\t    │");
		System.out.println("└───────────────────────────┘");
		// sql
		// SELECT book_name
		// FROM BOOKS
		// WHERE book_category = 'Sprots';
		// 반환타입, 매개변수, 메서드명
		// BookVO에서 카테고리 컬럼이 History인것만 조회
		// List<Strin> list =
		List<BooksVO> list = new ArrayList<>();
		list = service.SportsList(bvo);
		return list;
	}

	/**
	 * 회원 화면의 서적 선택 후 메뉴 구현 메서드
	 * 
	 * @author 이선엽
	 * @since 2020.09.07
	 */

	private void bookQuestion() {
		while (true) {
			System.out.println();
			System.out.println("1. 장바구니에 담기");
			System.out.println("2. 뒤로 가기");

			int input = 0;
			try {
				Scanner scan = new Scanner(System.in);
				input = scan.nextInt();
				switch (input) {
				case 1:
					// 주문->장바구니추가됨->장바구니목록띄어주고 주문할지 안할지 선택
					System.out.println("장바구니에 서적이 추가되었습니다.");
					System.out.println("장바구니 페이지로 넘어갑니다.");
					bucketInput();
					return;
				case 2:
					System.out.println("이전 화면으로 이동합니다.");
					return;
				}
			} catch (InputMismatchException e) {
				System.out.println();
				System.out.println("다시 입력해주세요.");
			}
		}
	}

	/**
	 * 장바구니에 주문할 서적을 담는 메소드
	 * 
	 * @method bucket
	 * @return void
	 * @author 강문정
	 * @since 2020. 9. 9.오후 1:57:34
	 */

	private List<CartVO> bucketInput() {
		while (true) {
			// sql
			// INSERT INTO CART
			// VALUES (bvo);
			// 장바구니 선택하면 장바구니에 담기는 메서드
			// int bucketInput(bvo);
			System.out.println("┌───────────────────────────┐");
			System.out.println("│\t      장바구니 내역\t    │");
			System.out.println("└───────────────────────────┘");
			// sql
			List<CartVO> list = new ArrayList<>();
			list = service.bucketInput();
			for (CartVO cvo : list) {
				System.out.println(cvo.getCart_id());
			}

			return list;
		}
	}

	private List<String> cartSelect() {

		System.out.println("1. 장바구니 내역 일괄 취소");
		System.out.println("2. 장바구니 내역 선택 취소");
		System.out.println("3. 장바구니 내역 주문 ");
		System.out.println("4. 계속 쇼핑하기 ");

		int input = 0;
		try {
			Scanner scan = new Scanner(System.in);
			input = scan.nextInt();
			switch (input) {
			case 1:
				// 모두 장바구니 내역을 가져와야 함
				deleteAll();
				break;
			case 2:
				// 모두 장바구니 내역을 가져와야 함
				deleteChoice();
				break;

			case 3:
				// 모두 장바구니 내역을 가져와야 함
				cartOrder();
				return null;
			}
		} catch (InputMismatchException e) {
			System.out.println();
			System.out.println("다시 입력해주세요.");
		}
		return null;
	}

	/**
	 * 장바구니의 모든 내역을 일괄 주문하는 메서드
	 * 
	 * @method cartOrder
	 * @return void
	 * @author 강문정
	 * @since 2020. 9. 9.오후 12:47:39
	 */

	private void cartOrder() {
		// sql
		// 반환타입, 메서드명, 매개변수
		// int cartOrder(CartVO cavo);
	}

	/**
	 * 서적 주문/장바구니에 담기
	 * 
	 * @method bucketInput
	 * @return void
	 * @author 강문정
	 * @since 2020. 9. 9.오후 1:54:56
	 */

	/**
	 * 장바구니 내역에서 일괄 삭제하는 메서드
	 * 
	 * @method deleteAll
	 * @return void
	 * @author 강문정
	 * @since 2020. 9. 9.오후 12:42:30
	 */

	private int deleteAll() {
		// sql
		// DELETE CART
		// WHERE MEM_ID = ''
		// 반환타입, 메서드명, 매개변수
		// Map<String, String> m = new HashMap<>();
		// m.put("book_id", book_id);
		// m.put("cart_id", cart_id);
		String member = mvo.getMem_id();

		int deleteCart = service.deleteCart(member);

		return deleteCart;
	}

	/**
	 * 장바구니 내역에서 선택해서 삭제하는 메서드
	 * 
	 * @method deleteChoice
	 * @return void
	 * @author 강문정
	 * @since 2020. 9. 9.오후 12:44:36
	 */

	private void deleteChoice() {
		// sql
		// DELETE CART
		// WHERE cart_id = "cart_id"
		// 반환타입, 메서드명, 매개변수
		// List<String> deleteChoice(CartVO cavo);
		while (true) {
			Scanner scan = new Scanner(System.in);
			int cart_id = scan.nextInt();
			int result = service.deleteChoice(cart_id);
			if (result > 0) {
				System.out.println("삭제되었습니다");
				break;
			} else {
				System.out.println("다시입력해주세요");
				continue;
			}
		}

	}

	/**
	 * 회원 화면의 3.내 주문 내역 조회 구현 메서드
	 * 
	 * @author 이선엽
	 * @since 2020.09.07
	 */

	private void orderInfo() {
		while (true) {
			System.out.println("1. 주문 내역 조회");
			System.out.println("2. 반품 신청하기");
			System.out.println("3. 뒤로 가기");

			int input = 0;
			try {
				Scanner scan = new Scanner(System.in);
				input = scan.nextInt();
				switch (input) {
				case 1:
					// 주문 내역 조회를 위한 메서드
					buyList();
					break;

				case 2:
					// 일괄 반품 신청을 위한 메서드
					sellList();
					return;

				case 3:
					System.out.println("이전 화면으로 이동합니다.");
					System.out.println();
					return;
				}
			} catch (InputMismatchException e) {
				System.out.println();
				System.out.println("다시 입력해주세요.");
			}
		}
	}

	/**
	 * 주문 내역 조회를 위한 메서드
	 * 
	 * @method buyList
	 * @return void
	 * @author 강문정
	 * @since 2020. 9. 9.오후 2:07:25
	 */

	private void buyList() {
		System.out.println("┌───────────────────────────┐");
		System.out.println("│\t      주문   내역\t    │");
		System.out.println("└───────────────────────────┘");

		// sql
		// 주문 내역 조회를 위한 메서드
		// List<String> buyList(OrdersVO ovo);

		while (true) {
			System.out.println("0. 뒤로 가기");
			int input = 0;
			try {
				Scanner scan = new Scanner(System.in);
				input = scan.nextInt();
				switch (input) {
				case 0:
					System.out.println();
					System.out.println("이전 화면으로 이동합니다.");
					return;
				}
			} catch (InputMismatchException e) {
				System.out.println();
				System.out.println("다시 입력해주세요.");
			}
		}
	}

	/**
	 * 반품 신청을 하는 메서드 반품 신청하면 일괄적으로 주문했던 모든 내역이 반품처리가 되고 포인트/서적 재고/주문 내역이 모두
	 * 환불되어야함
	 * 
	 * @method sellList
	 * @return void
	 * @author 강문정
	 * @since 2020. 9. 9.오후 2:21:47
	 */

	private void sellList() {
		while (true) {
			System.out.println();
			System.out.println("반품 신청 화면입니다.");
			System.out.println("원하시는 항목을 선택해주세요.");

			// sql
			// Map<String, String> m = new HashMap<>();
			// m.put("book_id", book_id);
			// m.put("refund_id", refund_id);

			int input = 0;
			try {
				Scanner scan = new Scanner(System.in);
				input = scan.nextInt();
				switch (input) {
				case 1:
					// 반품 신청하려면 주문내역을 불러와야하는데 없어서 일단 보류
					System.out.println("반품이 완료되었습니다.");
					System.out.println("이전 화면으로 이동합니다.");
					return;
				case 2:
					System.out.println("이전 화면으로 이동합니다.");
					return;
				}
			} catch (InputMismatchException e) {
				System.out.println();
				System.out.println("다시 입력해주세요.");
			}
		}
	}

	/**
	 * 개인정보 변경을 위한 메서드 회원의 ID빼고 모두 변경이 가능함
	 * 
	 * @method memUpdate
	 * @return void
	 * @author 강문정
	 * @since 2020. 9. 9.오후 2:25:54
	 */

	private void memUpdate() {
		// sql
		// memverVO에 있는 개인정보를 ID빼고 변경
		// int memUpdate(MemberVO mv);
		System.out.println("┌────────────────────────────────────────────┐");
		System.out.println("│\t\t      개인 정보 변경  \t\t     │");
		System.out.println("└────────────────────────────────────────────┘");

		System.out.print("1. 비밀번호 변경 ");
		System.out.print("2. 이름 변경 ");
		System.out.print("3. 주소 변경 ");
		System.out.print("4. 상세주소 변경 ");
		System.out.println("5. 전화번호 변경 ");
		System.out.println("6. 뒤로가기"); // 수정할 부분

		int input = 0;
		try {
			Scanner scan = new Scanner(System.in);
			input = scan.nextInt();
		} catch (InputMismatchException e) {
			System.out.println();
			System.out.println("범위 초과");
		}

		switch (input) {
		case 1:
			passUpdate();
			break;

		case 2:
			nameUpdate();
			break;

		case 3:
			add1Update();
			break;

		case 4:
			add2Update();
			break;

		case 5:
			// 5.전화번호변경
			phoneUpdate();
			break;

		case 6:
			return;
		}

		try {
			Scanner scan = new Scanner(System.in);
			input = scan.nextInt();
		} catch (InputMismatchException e) {
			System.out.println("범위 초과");
		}
	}

	/**
	 * 회원 화면의 4.개인 정보 변경 -1 비밀번호 변경 구현 메서드
	 * 
	 * @author 서대철
	 * @since 2020.09.07
	 */

	private int passUpdate() {
		Scanner scan = new Scanner(System.in);
		System.out.print("아이디 입력 : ");
		String mem_id = scan.next();
		System.out.print("변경할 비밀번호 : ");
		String mem_pass = scan.next();
		Map<String, String> params = new HashMap<>();
		params.put("mem_id", mem_id);
		params.put("mem_pass", mem_pass);

		int result = service.passUpdate(params);

		if (result != 0) {
			System.out.println("비밀번호 변경이 성공하였습니다.");
		} else {
			System.out.println("비밀번호 변경이 실패했습니다.");
		}

		return result;
	}

	/**
	 * 회원 화면의 4.개인 정보 변경 -2 이름 변경 구현 메서드
	 * 
	 * @author 서대철
	 * @since 2020.09.07
	 */

	private int nameUpdate() {
		Scanner scan = new Scanner(System.in);
		System.out.print("아이디 입력 : ");
		String mem_id = scan.next();
		System.out.print("변경할 이름 : ");
		String mem_name = scan.next();

		Map<String, String> params = new HashMap<>();
		params.put("mem_id", mem_id);
		params.put("mem_name", mem_name);

		int result = service.nameUpdate(params);

		if (result != 0) {
			System.out.println("이름 변경이 성공하였습니다.");
		} else {
			System.out.println("이름 변경이 실패했습니다.");
		}

		return result;
	}

	/**
	 * 회원 화면의 4.개인 정보 변경 -3 주소 변경 구현 메서드
	 * 
	 * @author 서대철
	 * @since 2020.09.07
	 */

	private int add1Update() {
		Scanner scan = new Scanner(System.in);
		System.out.print("아이디 입력 : ");
		String mem_id = scan.next();
		System.out.print("변경할 주소 : ");
		String mem_add1 = scan.nextLine();

		Map<String, String> params = new HashMap<>();
		params.put("mem_id", mem_id);
		params.put("mem_add1", mem_add1);

		int result = service.add1Update(params);

		if (result != 0) {
			System.out.println("주소 변경이 성공하였습니다.");
		} else {
			System.out.println("주소 변경이 실패했습니다.");
		}

		return result;
	}

	/**
	 * 회원 화면의 4.개인 정보 변경 -4 상세 주소 변경 구현 메서드
	 * 
	 * @author 서대철
	 * @since 2020.09.07
	 */

	private int add2Update() {
		Scanner scan = new Scanner(System.in);
		System.out.print("아이디 입력 : ");
		String mem_id = scan.next();
		System.out.print("변경할 상세주소 : ");
		String mem_add2 = scan.next();

		Map<String, String> params = new HashMap<>();
		params.put("mem_id", mem_id);
		params.put("mem_add2", mem_add2);

		int result = service.add2Update(params);

		if (result != 0) {
			System.out.println("상세주소 변경이 성공하였습니다.");
		} else {
			System.out.println("상세주소 변경이 실패했습니다.");
		}

		return result;
	}

	/**
	 * 회원 화면의 4.개인 정보 변경 -5 휴대폰 번호 변경 구현 메서드
	 * 
	 * @author 서대철
	 * @since 2020.09.07
	 */

	private int phoneUpdate() {
		Scanner scan = new Scanner(System.in);
		System.out.print("아이디 입력 : ");
		String mem_id = scan.next();
		System.out.print("변경할 전화번호 : ");
		String mem_phone = scan.next();

		Map<String, String> params = new HashMap<>();
		params.put("mem_id", mem_id);
		params.put("mem_pass", mem_phone);

		int result = service.phoneUpdate(params);

		if (result != 0) {
			System.out.println("전화번호 변경이 성공하였습니다.");
		} else {
			System.out.println("전화번호 변경이 실패했습니다.");
		}

		return result;
	}

	// 로그인 성공이라는 메서드를 완성했다고 가정하고 1번 누르면 밑에 출력문이 실행된다.
	// 관리자로그인 메서드
	// sql
	// String managerLogin(Map<String, String> params);

	private void managerLogin() {
		Scanner scan = new Scanner(System.in);
		System.out.println("┌───────────────────────────────┐");
		System.out.println("│    아이디와 비밀번호를 입력하세요\t│");
		System.out.println("└───────────────────────────────┘");
		System.out.print("아이디 : ");
		String manager_id = scan.next();
		System.out.print("비밀번호 : ");
		String manager_pass = scan.next();
		managerAfterLogin();

		String managerLogin_id = null;

		Map<String, String> params = new HashMap<>();
		params.put("manager_id", manager_id);
		params.put("manager_pass", manager_pass);

		managerLogin_id = service.managerLogin(params);

		if (managerLogin_id != null) {
			System.out.println("관리자 로그인에 성공했습니다.");
		} else {
			System.out.println("유효하지 않는 계정입니다. 아이디와 비밀번호를 확인 후 다시 입력해주세요.");
		}
		// 로그인이 성공했으면 로그인 메뉴로 넘어간다.
		// 로그인이 실패했으면 다시 입력받도록 한다.

	}

	/**
	 * 관리자 로그인 ID 입력 메서드
	 * 
	 * @author 강문정
	 * @return
	 */

	private String managerLoginID() {
		Scanner scan = new Scanner(System.in);
		System.out.print("아이디 : ");
		String manager_id = scan.next();

		return manager_id;
	}

	/**
	 * 관리자 로그인 PASS 입력 메서드
	 * 
	 * @author 강문정
	 * @return
	 */

	private String managerLoginPass() {
		Scanner scan = new Scanner(System.in);
		System.out.print("비밀번호 : ");
		String manager_pass = scan.next();

		return manager_pass;
	}

	private void managerAfterLogin() {
		while (true) {
			System.out.println("┌───────────────────────────────┐");
			System.out.println("│            관리자 모드         \t│");
			System.out.println("│───────────────────────────────│");
			System.out.println("│\t       메뉴를 고르세요\t\t│");
			System.out.println("└───────────────────────────────┘");
			System.out.println("1. 주문 관리");
			System.out.println("2. 서적 관리");
			System.out.println("3. 회원 관리");
			System.out.println("4. 뒤로 가기");

			int input = 0;
			// 입력값 변수타입의 범위를 초과했을 때 예외처리
			try {
				Scanner scan = new Scanner(System.in);
				input = scan.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("입력이 올바르지 않습니다.");
				continue;
			}
			switch (input) {
			case 1:
				// 관리자 주문 관리 메서드
				orderManage();
				break;

			case 2:
				// 관리자 서적 관리 메서드
				bookManage();
				break;

			case 3:
				// 관리자 회원 관리 메서드
				memberManage();
				break;

			case 0:
				// 뒤로가기
				return;
			}
		}
	}

	// 관리자모드 회원 정보 관리 메소드

	private void memberManage() {
		while (true) {
			try {
				System.out.println("┌───────────────────────────────┐");
				System.out.println("│    관리할 항목을 고르세요\t        │");
				System.out.println("└───────────────────────────────┘");
				System.out.println("1. 회원 정보 조회");
				System.out.println("2. 탈퇴 회원 관리");

				int input = 0;
				try {
					Scanner scan = new Scanner(System.in);
					input = scan.nextInt();
				} catch (InputMismatchException e) {
					e.printStackTrace();
					System.out.println("입력이 올바르지 않습니다.");
					continue;
				}
				switch (input) {
				case 1:
					// 회원 정보 조회 메서드
					memList();
					break;

				case 2:
					// 탈퇴 회원 비활성화 시키는 메서드
					memDrop();
					break;
				}
			} catch (InputMismatchException e) {
				System.out.println("다시 입력해주세요.");
			}
		}
	}

	// 회원의 정보를 모두 조회하는 메서드

	private void memList() {
		List<MemberVO> memList = service.memList();
		System.out
				.println("┌──────────────────────────────────────────────────┐");
		System.out
				.println("│아이디   \t이름 \t 주소   \t상세주소   \t핸드폰    \t포인트             │");
		System.out
				.println("└──────────────────────────────────────────────────┘");

		if (memList.isEmpty() == true) {
			System.out.println("\t\t회원이 존재하지 않습니다.");
		} else {
			for (MemberVO mvo : memList) {
				System.out.print(mvo.getMem_id() + " ");
				System.out.print(mvo.getMem_name() + " ");
				System.out.print(mvo.getMem_add1() + " ");
				System.out.print(mvo.getMem_add2() + " ");
				System.out.print(mvo.getMem_phone() + " ");
				System.out.print(mvo.getMem_point() + " ");
				System.out.println();
			}
		}

	}

	// 탈퇴 회원 비활성화 시키는 메서드
	private String memDrop() {
		return null;
		// sql
		// 반환타입, 메서드명, 매개변수
		// int MemDrop(MemberVO mvo)
	}

	// 관리자모드 서적관리 메소드
	private void bookManage() {
		while (true) {
			System.out.println();
			System.out.println("관리자 모드 서적 관리 화면입니다.");
			System.out.println();
			System.out.println("┌───────────────────────────────┐");
			System.out.println("│    번호를 입력하세요\t        │");
			System.out.println("└───────────────────────────────┘");
			System.out.println("1. 서적 등록");
			System.out.println("2. 서적 수정");
			System.out.println("3. 한줄평 조회");
			System.out.println("0. 뒤로 가기");

			int input = 0;
			try {
				Scanner scan = new Scanner(System.in);
				input = scan.nextInt();
			} catch (InputMismatchException e) {
				System.out.println();
				System.out.println("입력이 올바르지 않습니다.");
				continue;
			}
			switch (input) {
			case 1:
				// 관리자 서적 등록
				bookRegit();
				break;

			case 2:
				// 관리자 서적 조회
				bookList();
				break;

			case 3:
				// 관리자 한줄평 전체 조회
				reviewList();
				break;

			case 0:
				return;
			}
		}
	}

	/**
	 * 서적 등록을 위한 메서드
	 * 
	 * @method bookRegit
	 * @return void
	 * @author 강문정
	 * @since 2020. 9. 9.오후 4:16:47
	 */

	private void bookRegit() {
		while (true) {
			System.out.println("┌───────────────────────────────┐");
			System.out.println("│    카테고리를 선택하세요\t        │");
			System.out.println("└───────────────────────────────┘");
			System.out.println("1. IT");
			System.out.println("2. History");
			System.out.println("3. Sports");
			System.out.println("0. 뒤로 가기");

			int input = 0;
			try {
				Scanner scan = new Scanner(System.in);
				input = scan.nextInt();
				switch (input) {
				case 1:
					// IT서적 등록
					itRegit();
					break;

				case 2:
					// 역사 서적 등록
					historyRegit();
					break;

				case 3:
					// 스포츠 서적 등록
					sportsRegit();
					break;

				case 0:
					return;

				}
			} catch (InputMismatchException e) {
				System.out.println();
				System.out.println("입력이 올바르지 않습니다.");
				continue;
			}
		}
	}

	/**
	 * 서적 등록을 위한 메서드
	 * 
	 * @method bookCreate
	 * @return void
	 * @author 강문정
	 * @since 2020. 9. 9.오후 5:47:47
	 */

	private void bookCreate() {
		while (true) {
			// sql
			// int bookCreate(BooksVO bvo);
			codeinp();
			nameinp();
			pubnameinp();
			writterinp();
			pubdateinp();
			// service.itRegit(bvo);<< 서비스로 가게 해주는 것
		}
	}

	private void codeinp() {
		Scanner sc = new Scanner(System.in);
		System.out.println(" 서적코드 입력:");
		String code = sc.next();
		bvo.setBook_id(code);
	}

	private void nameinp() {
		Scanner sc = new Scanner(System.in);
		System.out.println(" 서적 이름 입력:");
		String name = sc.next();
		bvo.setBook_id(name);
	}

	private void pubnameinp() {
		Scanner sc = new Scanner(System.in);
		System.out.println(" 출판사 명 입력:");
		String pubname = sc.next();
		bvo.setBook_id(pubname);
	}

	private void writterinp() {
		Scanner sc = new Scanner(System.in);
		System.out.println(" 저자 입력:");
		String writter = sc.next();
		bvo.setBook_id(writter);
	}

	private void pubdateinp() {
		Scanner sc = new Scanner(System.in);
		System.out.println(" 출판일 입력:");
		String pubdate = sc.next();
		bvo.setBook_id(pubdate);
	}

	/**
	 * 서적 등록 / 수정 담당 메서드 복합체
	 */
	private void inputBook() {
		nameinp();
		pubnameinp();
		writterinp();
		pubdateinp();
	}

	/**
	 * 역사 서적 등록 메서드 P1001
	 * 
	 * @method historyRegit
	 * @return void
	 * @author 이선엽
	 * @since 2020. 9. 9.오후 6:29:42
	 */
	private void historyRegit() {
		inputBook();
	}

	/**
	 * 스포츠 서적 등록 메서드 P2001
	 * 
	 * @method historyRegit
	 * @return void
	 * @author 이선엽
	 * @since 2020. 9. 9.오후 6:29:42
	 */
	private void sportsRegit() {
		inputBook();
	}

	/**
	 * IT 서적 등록 메서드 P3001
	 * 
	 * @method historyRegit
	 * @return void
	 * @author 이선엽
	 * @since 2020. 9. 9.오후 6:29:42
	 */
	private void itRegit() {
		inputBook();
	}

	private void bookList() {
		List<BooksVO> bookList = service.bookList();
		System.out.println("┌───────────────────────────────────────┐");
		System.out.println("│번호\t이름\t출판사\t가격\t저자          │");
		System.out.println("└───────────────────────────────────────┘");

		if (bookList.isEmpty() == true) {
			System.out.println("\t서적이 존재하지 않습니다.");
		} else {
			for (BooksVO bvo : bookList) {
				System.out.print(bvo.getBook_id() + " ");
				System.out.print(bvo.getBook_name() + " ");
				System.out.print(bvo.getBook_pub_name() + " ");
				System.out.print(bvo.getBook_price() + " ");
				System.out.print(bvo.getBook_writer());
				System.out.println();
			}
		}
		
		// sql
		// 책 조회할때 책의 모든 VO를 가지고 와서 목록에 조회함
		// List<String> bookList(BookVO bvo);
		while (true) {
			System.out.println("┌───────────────────────────────┐");
			System.out.println("│    항목을 선택하세요\t        │");
			System.out.println("└───────────────────────────────┘");
			System.out.println("1. 서적 수정");
			System.out.println("2. 서적 삭제");
			System.out.println("0. 뒤로 가기");

			int input = 0;
			try {
				Scanner scan = new Scanner(System.in);
				input = scan.nextInt();
			} catch (InputMismatchException e) {
				System.out.println();
				System.out.println("입력이 올바르지 않습니다.");
				continue;
			}

			switch (input) {
			case 1:
				// 서적 수정
				bookUpdate();
				break;
			case 2:
				// 서적 삭제
				bookDelete();
				break;
			case 0:
				return;
			}
		}
	}

	/**
	 * 서적을 카테고리별로 수정할 수 있는
	 * 
	 * @method bookUpdate
	 * @return void
	 * @author 강문정
	 * @since 2020. 9. 9.오후 4:47:41
	 */

	private void bookUpdate() {
		while (true) {
			System.out.println("┌───────────────────────────────┐");
			System.out.println("│    카테고리를 선택하세요\t        │");
			System.out.println("└───────────────────────────────┘");
			System.out.println("1. IT");
			System.out.println("2. History");
			System.out.println("3. Sports");
			System.out.println("0. 뒤로 가기");

			int input = 0;
			try {
				Scanner scan = new Scanner(System.in);
				input = scan.nextInt();
			} catch (InputMismatchException e) {
				System.out.println();
				System.out.println("입력이 올바르지 않습니다.");
				continue;
			}
			switch (input) {
			case 1:
				// IT서적 수정
				itUpdate();
				break;

			case 2:
				// 역사 서적 수정
				historyUpdate();
				break;

			case 3:
				// 스포츠 서적 수정
				sportsUpdate();
				break;

			case 0:
				return;
			}
		}
	}

	/**
	 * IT서적 수정 메서드 P10001
	 * 
	 * @method itUpdate
	 * @return void
	 * @author 강문정
	 * @since 2020. 9. 9.오후 4:50:47
	 */

	private void itUpdate() {
		inputBook();
	}

	/**
	 * 역사 서적 수정 메서드 P20001
	 * 
	 * @method itUpdate
	 * @return void
	 * @author 강문정
	 * @since 2020. 9. 9.오후 4:50:47
	 */

	private void historyUpdate() {
		inputBook();
	}

	/**
	 * 스포츠 서적 등록 메서드
	 * 
	 * @method itUpdate
	 * @return void
	 * @author 강문정
	 * @since 2020. 9. 9.오후 4:50:47
	 */

	private void sportsUpdate() {
		inputBook();
	}

	/**
	 * 서적 삭제 메서드
	 * 
	 * @method bookDelete
	 * @return void
	 * @author 이선엽
	 * @since 2020. 9. 9.오후 6:39:36
	 */

	private void bookDelete() {

	}

	// 관리자모드 서적관리 메소드 - 한줄평 조회
	private void reviewList() {
		System.out.println();
		// sql
		// 반환타입, 메서드명, 매개변수
		// List<String> reviewList()
		System.out.println("카테고리별 한줄평 조회 화면입니다.");
	}

	// 관리자모드 주문관리 메소드
	private void orderManage() {
		while (true) {
			System.out.println();
			System.out.println("관리자 모드 주문 관리 화면입니다.");
			System.out.println();
			System.out.println("┌───────────────────────────────┐");
			System.out.println("│    번호를 입력하세요\t        │");
			System.out.println("└───────────────────────────────┘");
			System.out.println("1. 주문 목록 조회");
			System.out.println("2. 반품 목록 조회");
			System.out.println("3. 뒤로 가기");

			int input = 0;
			try {
				Scanner scan = new Scanner(System.in);
				input = scan.nextInt();
			} catch (InputMismatchException e) {
				e.printStackTrace();
				System.out.println();
				System.out.println("입력이 올바르지 않습니다.");
				continue;
			}
			switch (input) {
			case 1:
				// 주문 목록 조회 메서드
				orderList();
				break;
			case 2:
				// 반품 목록 조회 메서드
				refundList();
				break;
			case 3:
				return;
			}
		}
	}

	// 관리자모드 주문관리 - 반품 목록 조회
	private void refundList() {
		System.out.println();
		System.out.println("관리자 모드 반품 목록 조회 화면입니다.");

		// sql
		// 회원의 주문 목록을 조회
		// List<String> refundList(RefundVO rvo);
	}

	/**
	 * 관리자가 회원의 주문목록을 전부 조회할 수 있는 메서드
	 * 
	 * @method orderList
	 * @return void
	 * @author 강문정
	 * @since 2020. 9. 9.오후 2:31:57
	 */

	// 관리자모드 주문관리 - 주문 목록 조회

	private void orderList() {
		// sql
		// 회원의 주문 목록을 조회
		// List<String> orderList(OrdersVO ovo);
		System.out.println();
		System.out.println("관리자 모드 주문 목록 조회 화면입니다.");
	}
}
