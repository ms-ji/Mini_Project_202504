/**
 * Package Name : com.pcwk.ehr.menu <br/>
 * 파일명: adminBoard.java <br/>
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
import com.pcwk.ehr.cmn.CafeDiv;
import com.pcwk.ehr.cmn.Main;

public class AdminBoard {

	public static void board() {
		Scanner scanner = new Scanner(System.in);

		while (true) {;
			System.out.println("====================================================");
			System.out.println(" ① 음료 조회  ② 음료 등록  ③ 음료 수정  ④ 음료 삭제  ⑤ 프로그램 종료  ");
			System.out.println("====================================================");
			System.out.print("어떤 프로그램을 할 지 선택하세요 >");
			int search = scanner.nextInt();
			AdminDao i = new AdminDao();

			MainBoard main = new MainBoard();

			AdminVO vo = new AdminVO();
			switch (search) {
			case 1://메뉴 조회
				List<AdminVO> menuList = i.doRetrieve(null);
				break;


			case 2:
				while (true) {
					System.out.println("====== 메뉴 등록 ======");
					int result = i.doSave(vo);

					if (result == 1) {
						System.out.println("🎉 메뉴가 성공적으로 등록되었습니다.");
						
					} else {
						System.out.println("⚠️ 메뉴 등록에 실패했습니다.");
					}
					break;
				}
				break;

			case 3:
				while (true) {
					System.out.println("====== 가격 수정 ======");
					int result = i.doUpdate(vo);

					if (result == 1) {
						System.out.println("🎉 가격이 성공적으로 수정되었습니다.");
						
					} else {
						System.out.println("⚠️ 가격 수정에 실패했습니다.");
					}
					break;
				}
				break;
			case 4://메뉴 삭제
				System.out.print("📝 삭제할 번호를 입력하세요 > ");
				int noToDelete = scanner.nextInt();
				//스캐너를 사용해 삭제할 번호 입력
				AdminVO deleteTarget = new AdminVO(noToDelete,"",0);//deleteTarget으로 변수 설정
				int result = i.doDelete(deleteTarget);
				
				if(result == 1) {
					System.out.println("🎉 성공적으로 삭제되었습니다.");
				}else {
					System.out.println("⚠️ 해당 번호는 없는 번호입니다.");
				}
				break;
			case 5://프로그램 종료(메인으로 이동)
				System.out.println("🔚 메인화면으로 돌아갑니다.");
				main.Board();
				break;
			default://앞에 있는 값이 아닐시 다시 입력 받기
				System.out.println("✅ 다시 입력해 주세요.");

			}
		}
	}
}
