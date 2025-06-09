/**
 * Package Name : com.pcwk.ehr.admin.vo <br/>
 * 파일명: AdminVO.java <br/>
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
package com.pcwk.ehr.admin.vo;

import java.util.Objects;

import com.pcwk.ehr.cmn.DTO;

/**
 * 
 */
public class AdminVO extends DTO{
	
	private int No;//번호
	private String name;//메뉴 이름
	private int price;//가격
	
	
	
	

	public AdminVO() {
		super();
		
	}



	public AdminVO(int no, String name, int price) {
		super();
		this.No = no;
		this.name = name;
		this.price = price;
	}



	public int getNo() {
		return No;
	}


	public void setNo(int no) {
		No = no;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}


	@Override
	public String toString() {
		return "AdminVO [No=" + No + ", name=" + name + ", price=" + price + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	
	
	
	
	
	

}
