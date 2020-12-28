package All_Customer;

import java.sql.Date;

public class personal_product_history {
	String History_code;
	String Bname;
	int Used_point;
	int Payment;
	int Point;
	int Usable_point;
	Date Bdate;
	int Saled_num;
	
	// Object 배열로 반환
	public Object[] toArray() {
		Object product_history_info[] = {History_code, Bname, 
				Used_point, Payment, Point, Usable_point, Bdate, Saled_num};
		return product_history_info;
	}
}
