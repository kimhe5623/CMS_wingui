package Designer;

import java.sql.Date;

public class Designer_info {
	String Did;
	String Dname;
	String Dphone;
	String Dpos;
	//
	public Object[] toArray() {
		Object D_info[] = {Did, Dname, Dphone, Dpos};
		return D_info;
	}
}
