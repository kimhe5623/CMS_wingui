package All_Customer;

import java.sql.Date;

public class personal_Service_history {
	String History_code;
	String Dname;
	String Service_name;
	int Used_point;
	int Payment;
	int Point;
	int Usable_point;
	Date Cdate;
	
	// Object 배열로 반환
	public Object[] toArray() {
		Object service_history_info[] = {History_code, Dname, Service_name
				,Used_point, Payment, Point, Usable_point, Cdate};
		return service_history_info;
	}
}
