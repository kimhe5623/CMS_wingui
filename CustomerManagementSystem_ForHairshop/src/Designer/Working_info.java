package Designer;

public class Working_info {
// D.Did, D.Dname, D.Dphone, D.Dpos, W.Dtime
	String Did;
	String Dname;
	String Dphone;
	String Dpos;
	String Dtime;
	//
	public Object[] toArray() {
		Object W_info[] = {Did, Dname, Dphone, Dpos, Dtime};
		return W_info;
	}
}
