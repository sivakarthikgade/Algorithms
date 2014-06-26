
/**
 * @author Jemish Patel
 * @author Siva Karthik Gade
 */
public class Solution {

	public Activity activity;
	public int profit;
	public int tac;
	public boolean isUsed;
	public int[] prevItem;
	
	public Solution() {
		this.prevItem = new int[2];
		this.prevItem[0] = -1;
		this.prevItem[1] = -1;
	}

	public Solution(Activity activity, int val, int tac, boolean isUsed, int[] prevItem) {
		this.activity = activity;
		this.profit = val;
		this.tac = tac;
		this.isUsed = isUsed;
		this.prevItem = new int[2];
		if(prevItem == null) {
			this.prevItem[0] = -1;
			this.prevItem[1] = -1;
		} else {
			this.prevItem[0] = prevItem[0];
			this.prevItem[1] = prevItem[1];
		}
	}

	public Solution(Solution old) {
		this.activity = old.activity;
		this.profit = old.profit;
		this.tac = old.tac;
		this.isUsed = old.isUsed;
		this.prevItem = new int[2];
		this.prevItem[0] = old.prevItem[0];
		this.prevItem[1] = old.prevItem[1];
	}
	
	public void setPrevItem(int[] prevItem) {
		if(prevItem == null) {
			this.prevItem[0] = -1;
			this.prevItem[1] = -1;
		} else {
			this.prevItem[0] = prevItem[0];
			this.prevItem[1] = prevItem[1];
		}
	}

}
