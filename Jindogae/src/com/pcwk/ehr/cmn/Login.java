/**
 * Package Name : com.pcwk.ehr.cmn <br/>
 * 파일명: Login.java <br/>
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
package com.pcwk.ehr.cmn;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.pcwk.ehr.menu.AdminBoard;

/**
 * 
 */
public class Login {


	public static void Login() {
		String id = ""; // 사용자 ID
		String password = ""; // 사용자 비밀번호

		// ID/비번을 HashMap으로 저장, ID/비번은 Scanner로 입력.
		Map<String, String> userInfo = new HashMap<>();
		userInfo.put("admin", "1234");

		// ID존재 확인: ID를 확인 하세요.
		Scanner scanner = new Scanner(System.in);

		// 카페 로그인 화면 출력
		printCafeLoginScreen();

		// ID 입력 Null Check
		while (true) {
			System.out.printf("👤 아이디를 입력하세요 > ");
			// id를 입력
			id = scanner.nextLine().trim();

			// 1. null continue;
			if (id.length() <= 0) {
				printMessage("⚠️ 아이디를 입력하지 않았습니다.", "☕️ 다시 입력해 주세요.");
				continue;
				// 2. !userInfo.containsKey(id) continue;
			} else if (!userInfo.containsKey(id)) {
				printMessage(String.format("❌ %s는 존재 하지 않습니다.", id), "☕️ 다시 입력해 주세요.");
				continue;
			}

			System.out.printf("입력 ID: [%s]%n", id);
			break;
		} // while id

		// 비밀번호 입력 Null Check
		while (true) {
			System.out.printf("🔑 비밀번호를 입력하세요 > ");
			// trim() : 앞뒤 공백 제거
			password = scanner.nextLine().trim();
			System.out.printf("입력 비밀번호: [%s]%n", password);

			if (password.length() != 0) {
				String passAnswer = userInfo.get(id);

				if (!passAnswer.equals(password)) {
					System.out.println("⚠️ 비밀번호를 확인해 주세요.\n"+"☕️ 다시 입력해 주세요.");
				} else {
					System.out.println("🎉 로그인 성공! 관리자로 접속합니다. ☕️");
					AdminBoard move = new AdminBoard();
					move.board();
					break;
				}
			} else {
				System.out.println("⚠️ 비밀번호를 입력하지 않았습니다.\n"+"☕️ 다시 입력해 주세요.");
			}
		} // while password

		// 리소스 해제
		scanner.close();
	}

	// 로그인 화면 메시지 출력 함수
	private static void printCafeLoginScreen() {
		System.out.println("☕️☕️☕️ 카페 관리자 로그인 시스템 ☕️☕️☕️");
		System.out.println("========================================");
		System.out.println("😊 환영합니다! 카페 관리자 로그인 페이지입니다.");
		System.out.println("아이디와 비밀번호를 입력해주세요. 😊");
		System.out.println("========================================");
		
	}

	// 메시지 출력 함수
	private static void printMessage(String line1, String line2) {
		System.out.println("───────────────────────────────────");
		System.out.println("  " + line1); // line1 값 출력
		System.out.println("  " + line2); // line2 값 출력
		System.out.println("───────────────────────────────────");

	}

}