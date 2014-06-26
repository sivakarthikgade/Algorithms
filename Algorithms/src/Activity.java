
/**
 * @author Siva Karthik Gade
 */
public class Activity {

	int s;
	int f;
	int p;
	boolean isShort;
	
	public Activity(int s, int f, int p) {
		this.s = s;
		this.f = f;
		this.p = p;
		if(this.f - this.s <= 4)
			this.isShort = true;
		else
			this.isShort = false;
	}

}
