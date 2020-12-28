package Birth_customer;

import java.sql.Date;

public class Birth_Cinfo {
	String Cid;
	String Cname;
	String Csex;
	Date Cbirth;
	String Cphone;
	
	//
	public Object[] toArray() {
		Object C_info[] = {Cid, Cname, Csex, Cphone, Cbirth};
		
		return C_info;
	}
}
