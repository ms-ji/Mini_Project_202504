/**
 * Package Name : com.pcwk.ehr.member.dao <br/>
 * 파일명: MemberDao.java <br/>
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
package com.pcwk.ehr.member.dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.pcwk.ehr.admin.vo.AdminVO;
import com.pcwk.ehr.cmn.CafeDiv;
import com.pcwk.ehr.cmn.PLog;
import com.pcwk.ehr.member.vo.MemberVO;

public class MemberDao implements CafeDiv<MemberVO>,PLog {

	private List<MemberVO> cart = new ArrayList<>();
	private int currentNo = 1;
	Scanner sc = new Scanner(System.in);
	public static final String CAFE_DATA = ".\\data\\cafe.csv";// csv 경로 저장
	private List<AdminVO> admin = new ArrayList<AdminVO>();// 리스트 만들기

	public MemberDao() {
		getAdminReadFile(CAFE_DATA);// 파일 읽기
	}

	private List<AdminVO> getAdminReadFile(String path) {
		try (BufferedReader reader = new BufferedReader(new FileReader(path));) {
			String line;
			while ((line = reader.readLine()) != null) {
				//System.out.println(line);
		

				String[] dataArr = line.split(",");// , 기준으로 나누기
				int no = Integer.parseInt(dataArr[0]);// 첫번째를 no로 설정
				String name = dataArr[1];// 두번째를 메뉴명으로 설정
				int price = Integer.parseInt(dataArr[2]);// 세번째를 가격으로 설정

				AdminVO adminVO = new AdminVO(no, name, price);
				admin.add(adminVO);//화면 출력
				System.out.printf("📋 No.%d 제품 정보 -[%s] %,d원%n", no, name, price);
			}
//			System.out.println("추가 확인");
//			for (AdminVO vo : admin) {
//				System.out.println(vo);
//			}
		} catch (FileNotFoundException e) {
			LOG.debug("⚠️ 파일 저장 실패: " + e.getMessage());
		} catch (IOException e) {
			LOG.debug("⚠️ 입출력 오류 발생: " + e.getMessage());
		} catch (Exception e) {
			LOG.debug("⚠️ 알 수 없는 오류 발생: " + e.getMessage());
		}

		return admin;// csv파일 읽기

	}


	public int doSave(MemberVO dto) {
		String filePath = ".\\data\\cafe.csv";
		// 1. 메뉴 선택하기.
		// 메뉴에 없는 제품 구매 불가.
		// 선택 반복할 수 있게.
		int result = 0;
		boolean addMore = true;

		while (addMore) {

			System.out.print("🔍 선택할 제품 번호를 입력해주세요 > ");
			String inputcart = sc.nextLine();

			if (inputcart.isEmpty()) {
				System.out.println("⚠️ 다시 입력해 주세요.");
				continue;
			}
			// 메뉴가 존재하는지 확인
			boolean flag = false;
			String cartMenuName = null;
			try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
				String line;

				while ((line = reader.readLine()) != null) {
					String[] parts = line.split(",");

					// 메뉴 이름이 일치하면 가격 수정
					if (parts.length == 3) {
						String menuNumber = parts[0].trim();
						if (menuNumber.equals(inputcart.trim())) {
							cartMenuName = parts[1].trim();
							flag = true;
							break;
						}
					}

				} // while end
				if (!flag) {
					System.out.println("📝 해당 번호의 메뉴는 존재하지 않습니다.");
					continue; // 다시 메뉴 선택으로 돌아가기
				}
			} catch (FileNotFoundException e) {
				LOG.debug("⚠️ 파일을 찾을 수 없습니다: " + e.getMessage());
				return 0;
			} catch (IOException e1) {
				LOG.debug("⚠️ 파일 읽기 오류: " + e1.getMessage());
				return 0;
			}

			// 2. 개수 선택하기
			int input = 0;
			while (true) {
				System.out.print("📝 개수를 선택해 주세요> ");
				String countcart = sc.nextLine();
				try {
					input = Integer.parseInt(countcart);
					if (input <= 0) {
						System.out.println("⚠️ 가격은 0보다 커야 합니다.");
						continue;
					}
					break; // 정상 입력이면 루프 탈출
				} catch (NumberFormatException e) {
					LOG.debug("⚠️ 가격에는 숫자만 넣으세요.");
				}
			} // 개수 while end
			MemberVO newDto = new MemberVO();
			newDto.setName(cartMenuName);
			newDto.setQuantity(input);
			newDto.setNO(currentNo++);
			cart.add(newDto);
			System.out.println("🧺 "+cartMenuName + " x " + input + " 장바구니에 담았습니다.🧺");
			result = 1;

			// 다음 메뉴 등록 여부 확인
			System.out.print("다른 메뉴를 추가하시겠습니까? (Y/N): ");
			String answer = sc.nextLine();
			if (!answer.equalsIgnoreCase("Y")) {
				System.out.println("🎉 메뉴 등록을 종료합니다.");
				addMore = false;
			}
		} // while 1 end
		return result;
	}// doSave end

	@Override
	public List<MemberVO> doRetrieve(MemberVO dto) {
		boolean flag = true;
		if (cart.isEmpty()) {
			System.out.println("⚠️ 장바구니가 비어 있습니다.");
			flag = false;
		}

		System.out.println("\n[주문 내역]");
		for (MemberVO item : cart) {
			System.out.printf("✅ 번호: %d번, 이름: [%s], 수량: %d개%n", item.getNO(), item.getName(), item.getQuantity());

		}
		return cart;
	}

	@Override
	public int doUpdate(MemberVO dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int doDelete(MemberVO dto) {
		if (cart.isEmpty()) {
			System.out.println("⚠️ 장바구니가 비어 있습니다.");
			return 0;
		}

		System.out.println("\n[주문 내역]");
		for (MemberVO item : cart) {
			System.out.printf("✅ 번호: %d번, 제품명: [%s], 수량: %d개%n", item.getNO(), item.getName(), item.getQuantity());

		}
		System.out.println("⚠️ 삭제할 번호를 입력하세요:");
		int input;
		try {
			input = Integer.parseInt(sc.nextLine());
		} catch (NumberFormatException e) {
			LOG.debug("⚠️ 번호는 숫자만 입력 가능합니다.");
			return 0;
		}

		// 장바구니에서 해당 번호의 아이템을 찾음
		for (int i = 0; i < cart.size(); i++) {
			MemberVO item = cart.get(i);

			// 입력된 번호와 장바구니 번호가 일치하는지 확인
			if (item.getNO() == input) {
				int currentQuantity = item.getQuantity();
				System.out.println("📝 삭제할 수량을 입력하세요:");
				int deleteQuantity = sc.nextInt(); // 삭제할 수량 입력

				if (deleteQuantity <= 0) {
					System.out.println("⚠️ 수량은 1개 이상이어야 합니다.");
					return 0;
				}

				if (deleteQuantity > currentQuantity) {
					System.out.println("⚠️ 삭제할 수량이 장바구니 수량보다 많습니다.");
					return 0;
				} else if (deleteQuantity == currentQuantity) {
					cart.remove(i); // 장바구니에서 아이템을 제거
					System.out.println("✅ 상품이 삭제되었습니다.");
				} else {
					item.setQuantity(currentQuantity - deleteQuantity); // 일부 수량만 남기고 수정
					System.out.println("✅ 상품 일부 수량이 삭제되었습니다.");
				}
				return 1; // 성공적으로 삭제됨
			}
		}

		System.out.println("❌ 해당 번호의 상품이 장바구니에 없습니다.");
		return 0; // 해당 번호가 장바구니에 없는 경우

	}
}