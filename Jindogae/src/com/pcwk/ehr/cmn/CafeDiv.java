/**
 * Package Name : com.pcwk.ehr.cmn <br/>
 * 파일명: CafeDiv.java <br/>
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

import java.util.List;

/**
 * 
 */
public interface CafeDiv<T> {
	

	int doSave(T dto); 
	
	List<T> doRetrieve(T dto);
	
	int doUpdate(T dto);
	
	int doDelete(T dto);

}
