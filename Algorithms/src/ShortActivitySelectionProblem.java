
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Jemish Patel
 * @author Siva Karthik Gade
 */
public class ShortActivitySelectionProblem {

	List<Activity> activityList;
	Activity[] A;
	Map<Integer,Map<Integer,Solution>> ASP;
	
	public ShortActivitySelectionProblem() {
		activityList = new ArrayList<Activity>();
	}
	
	public void orderActivitiesByFinishTime() {
		mergeSortByFinishTime(this.activityList, 0, this.activityList.size()-1);
		A = new Activity[activityList.size()+1];
		
		for(int i = 1; i <= activityList.size(); i++) {
			A[i] = this.activityList.get(i-1);
//			log(A[i].s+" , "+A[i].f+" , "+A[i].p);
		}
	}
	
	public void calculateMaxProfit() {
		for(int i = 1; i < A.length; i++) {

			if(ASP.get(i-1) != null) {				//Setting solutions at i-1 into i. Sort of defaulting values.
				for(int j: ASP.get(i-1).keySet()) {
					if(ASP.get(i) == null) {
						ASP.put(i, new HashMap<Integer,Solution>());
					}
					ASP.get(i).put(j,new Solution(ASP.get(i-1).get(j)));
					if(ASP.get(i).get(j).isUsed) {
						ASP.get(i).get(j).isUsed = false;
						ASP.get(i).get(j).activity = null;
						int[] prevItem = {i-1,j};
						ASP.get(i).get(j).setPrevItem(prevItem);
					}
				}
			}
			
			int a = i-1;
			while(a > 0 && A[a].f > A[i].s) {
				a--;
			}
			if(a > 0) {
				int isShort = A[i].isShort ? 1 : -1;
				for(int j: ASP.get(a).keySet()) {
					if(ASP.get(i) == null) {
						ASP.put(i, new HashMap<Integer,Solution>());
					}
					if(ASP.get(i).get(j+isShort) == null) {
						ASP.get(i).put(j+isShort, new Solution());
					}
					if(ASP.get(i).get(j+isShort).profit < A[i].p + ASP.get(a).get(j).profit) {
						ASP.get(i).get(j+isShort).activity = A[i];
						ASP.get(i).get(j+isShort).profit = A[i].p + ASP.get(a).get(j).profit;
						ASP.get(i).get(j+isShort).tac = 1 + ASP.get(a).get(j).tac;
						ASP.get(i).get(j+isShort).isUsed = true;
						if(ASP.get(a).get(j).isUsed) {
							int[] prevItem = {a,j};
							ASP.get(i).get(j+isShort).setPrevItem(prevItem);
						} else {
							int[] prevItem = {ASP.get(a).get(j).prevItem[0],ASP.get(a).get(j).prevItem[1]};
							ASP.get(i).get(j+isShort).setPrevItem(prevItem);
						}
					}
				}
			}
			int j = A[i].isShort ? 1 : -1;
			if(ASP.get(i) != null && ASP.get(i).get(j) != null) {
				if(A[i].p > ASP.get(i).get(j).profit) {
					ASP.get(i).get(j).activity = A[i];
					ASP.get(i).get(j).profit = A[i].p;
					ASP.get(i).get(j).tac = 1;
					ASP.get(i).get(j).isUsed = true;
					ASP.get(i).get(j).setPrevItem(null);
				}
			} else {
				if(ASP.get(i) == null) {
					ASP.put(i, new HashMap<Integer,Solution>());
				}
				ASP.get(i).put(j,new Solution(A[i],A[i].p, 1, true, null));
			}
		}
	}
	
	public void printMaxProfitSolution() {
		int n = activityList.size();
		int maxVal = 0, excess = -1;
		for(int j: ASP.get(n).keySet()) {
			if(j > 0 && (maxVal < ASP.get(n).get(j).profit)) {
				maxVal = ASP.get(n).get(j).profit;
				excess = j;
			}
		}
		if(excess == -1) {
			log("No satisfying solution available");
		} else {
			log("Profit = "+maxVal);
			log("Excess small = "+excess);
			print(ASP.get(n).get(excess));
		}
	}
	
	private void print(Solution s) {
		if(s.isUsed)
			log("["+(s.activity.isShort?"S":"B")+"] "+s.activity.s+" "+s.activity.f+" "+s.activity.p);
		if(s.prevItem[0] > 0)
			print(ASP.get(s.prevItem[0]).get(s.prevItem[1]));
	}
	
	public void mergeSortByFinishTime(List<Activity> input, int p, int r) {
		
		if(p == r) {
			return;
		}
		
		int q = (p+r)/2;

		mergeSortByFinishTime(input, p, q);
		mergeSortByFinishTime(input, q+1, r);
		
		mergeByFinishTime(input, p, q, r);
	}

	public void mergeByFinishTime(List<Activity> input, int p, int q, int r) {
		int lCnt = 0, rCnt = 0;
		
		List<Activity> lList = new ArrayList<Activity>(input.subList(p, q+1));
		List<Activity> rList = new ArrayList<Activity>(input.subList(q+1, r+1));
		
		lList.add(new Activity(-1,Integer.MAX_VALUE,-1));
		rList.add(new Activity(-1,Integer.MAX_VALUE,-1));
		
		for(int i = p; i <= r; i++) {
			if(lList.get(lCnt).f <= rList.get(rCnt).f) {
				input.set(i, lList.get(lCnt++));
			} else {
				input.set(i, rList.get(rCnt++));
			}
		}
	}
	
	public static void main(String[] args) {
		ShortActivitySelectionProblem prob;
		for(int i = 0; i < args.length; i++) {
			prob = new ShortActivitySelectionProblem();
			int activityCnt = Integer.parseInt(args[i]);
			for(int j = 1; j <= activityCnt; j++) {
				int s = Integer.parseInt(args[++i]);
				int f = Integer.parseInt(args[++i]);
				int p = Integer.parseInt(args[++i]);
				if(s == 0 && f == 0 && p == 0) {	//Ignoring dummy activity in test input.
					continue;
				}
				prob.activityList.add(new Activity(s, f, p));
			}
			prob.ASP = new HashMap<Integer,Map<Integer,Solution>>(prob.activityList.size()+1);
			prob.orderActivitiesByFinishTime();
			prob.calculateMaxProfit();
			prob.printMaxProfitSolution();
		}
	}
	
	private void log(String str) {
		System.out.println(str);
	}

}
