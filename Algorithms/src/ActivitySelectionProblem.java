
import java.util.ArrayList;
import java.util.List;

public class ActivitySelectionProblem {

	List<Activity> activityList;
	Activity[] A;
	int[] ASP;
	
	public ActivitySelectionProblem() {
		activityList = new ArrayList<Activity>();
	}
	
	public void calculateMaxProfit() {

		mergeSortByFinishTime(this.activityList, 0, this.activityList.size()-1);
		A = new Activity[activityList.size()];
		for(int i = 0; i < A.length; i++) {
			A[i] = this.activityList.get(i);
		}
		
		ASP[0] = A[0].p;
		for(int i = 1; i < ASP.length; i++) {
			ASP[i] = ASP[i-1] < A[i].p ? A[i].p : ASP[i-1];
			int j = i-1;
			while(j >= 0 && A[j].f > A[i].s) {
				j--;
			}
			while(j >= 0) {
				if(ASP[i] < A[i].p + ASP[j]) {
					ASP[i] = A[i].p + ASP[j];
				}
				j--;
			}
		}
		
		int i = 0;
		for(Activity a: activityList) {
			log(a.s + " " + a.f + " " + a.p + " " + ASP[i++]);
		}
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
			if(lList.get(lCnt).f < rList.get(rCnt).f) {
				input.set(i, lList.get(lCnt++));
			} else {
				input.set(i, rList.get(rCnt++));
			}
		}
	}
	
	public static void main(String[] args) {
		ActivitySelectionProblem prob;
		int instanceNum = 0;
		for(int i = 0; i < args.length; i++) {
			instanceNum++;
			prob = new ActivitySelectionProblem();
			int activityCnt = Integer.parseInt(args[i]);
			for(int j = 1; j <= activityCnt; j++) {
				int s = Integer.parseInt(args[++i]);
				int f = Integer.parseInt(args[++i]);
				int p = Integer.parseInt(args[++i]);
				prob.activityList.add(new Activity(s, f, p));
			}
			prob.ASP = new int[prob.activityList.size()];
			prob.calculateMaxProfit();
			prob.log(instanceNum+" "+prob.ASP[prob.ASP.length-1]);
		}
	}
	
	private void log(String str) {
		System.out.println(str);
	}

}
