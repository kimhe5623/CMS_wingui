/* 고객 정보를 담는 클래스 */
package All_Customer;

import java.sql.Date;

public class Customer_info {
	String Cid;
	String Cname;
	String Csex;
	String Cphone;
	Date Cbirth;
	Date Cdate;
	String Cgrade;
	int Usable_point;
	int Cumul_amount;
	//
	public Object[] toArray() {
		Object C_info[] = {Cid, Cname, Csex, Cphone, Cbirth, Cdate,
							Cgrade, Usable_point, Cumul_amount};
		return C_info;
	}
}
