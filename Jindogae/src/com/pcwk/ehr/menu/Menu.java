/**
 * Package Name : com.pcwk.ehr.menu <br/>
 * 파일명: Menu.java <br/>
 * Description:  <br/>
 * Modification imformation : <br/> 
 * ------------------------------------------<br/>
 * 최초 생성일 : 2025-04-22<br/>
 *
 * ------------------------------------------<br/>
 * @author :user
 * @since  :2024-09-09
 * @version: 0.5
 */
package com.pcwk.ehr.menu;

import java.util.List;
import java.util.Scanner;

import com.pcwk.ehr.admin.dao.AdminDao;
import com.pcwk.ehr.admin.vo.AdminVO;
import com.pcwk.ehr.member.dao.MemberDao;
import com.pcwk.ehr.member.vo.MemberVO;

public class Menu {

	public static void MenuBoard() {
		AdminDao dao = new AdminDao();
		MemberDao i = new MemberDao();

		System.out.println("고객 모드에 오신걸 환영합니다!");
		System.out.println("🦮🐾 진돗개 카페에 오신걸 환영합니다! 🐾🦮");
		System.out.println("🍽️ 메 뉴 판 🍽️");

		System.out.println("☕ 커피 ☕");
		System.out.println("  - 아메리카노 3000원");
		System.out.println("  - 카푸치노 4500원");
		System.out.println("  - 모카 4500원");

		System.out.println("🥤 음료 🥤");
		System.out.println("  - 아이스티 2500원");
		System.out.println("  - 레모네이드 3000원");
		System.out.println("  - 스무디 4000원");

		System.out.println("🍰 디저트 🍰");
		System.out.println("  - 쿠키 3500원");
		System.out.println("  - 치즈케이크 4000원");
		System.out.println("  - 티라미수 4500원");

		System.out.println("🍨 아이스크림 🍨");
		System.out.println("  - 바닐라 2500원");
		System.out.println("  - 초코 2500원");
		System.out.println("  - 딸기 2500원");

		System.out.println("🐾 진돗개 카페에 와주셔서 감사합니다! 🐾");

		Scanner scanner = new Scanner(System.in);
		MainBoard main = new MainBoard();
		MemberVO vo = new MemberVO();

		while (true) {
			System.out.println("====================================================");
			System.out.println(" ① 주문하기  ② 주문 확인  ③ 주문 정보 삭제  ④ 프로그램 종료	         ");
			System.out.println("====================================================");
			System.out.print("어떤 프로그램을 할 지 선택하세요 >");
			int select = scanner.nextInt();
			MemberDao da = new MemberDao();
			switch (select) {

			case 1: // 메뉴선택
				while (true) {
					System.out.println("====== 주문을 시작합니다 ======");
					System.out.println("원하시는 메뉴를 선택해주세요 :)");

					int result = i.doSave(vo);

					if (result == 1) {
						System.out.println("✅ 주문이 정상적으로 완료되었습니다. 감사합니다!");
					} else {
						System.out.println("❌ 주문 처리 중 문제가 발생했습니다. 다시 시도해주세요.");
					}
					break;
				}
				break;
			case 2:
				List<MemberVO> result = i.doRetrieve(vo);
				break;
			case 3://삭제 코드
				while (true) {
					System.out.println("===== 주문 내역 삭제하기 =====");

					int result2 = i.doDelete(vo);

					if (result2 == 1) {
						System.out.println("✅ 주문이 취소 되었습니다.");
					} else {
						System.out.println("❌ 주문 삭제에 실패했습니다. 다시 한 번 확인해주세요.");
					}
					break;
				}
				break;
			case 4:
				System.out.println("👋 이용해주셔서 감사합니다!");
				System.out.println("메인 화면으로 돌아갑니다. 좋은 하루 되세요 ☕️");
				main.Board();
				break;
			default:
				System.out.println("⚠️ 다시 선택해주세요.");

			}
		}
	}
}
