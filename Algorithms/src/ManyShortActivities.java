
public class ManyShortActivities {

	public ManyShortActivities() {
		// TODO Auto-generated constructor stub
	}
	
	public void getMaxProfitSolution(Activity[] a) {
		int n = a.length;
		int[][][] c = new int[n+1][n+2][6];
		
		for(int i = 1; i <= n; i++) {
			a[i].isShort = a[i].f-a[i].s > 4 ? false : true;
		}
		
		for(int l = 2; l <= n+2; l++) {
			for(int i = 0; i <= n+2-l; i++) {
				int j = i+l-1;
				for(int k = i+1; k <= j-1; k++) {
					if(a[i].f <= a[k].s && a[k].f <= a[j].s) {
								
						if(c[i][j][0] < c[i][k][0] + a[k].p + c[k][j][0]) {
							c[i][j][0] = c[i][k][0] + a[k].p + c[k][j][0];
							c[i][j][1] = c[i][k][1] + 1 + c[k][j][1];
							c[i][j][2] = c[i][k][2] + (a[k].isShort?1:0) + c[k][j][2];
						}
					}
				}
				if(c[i][j][2] >= c[i][j][1] - c[i][j][2]) {
					c[i][j][3] = c[i][j][0];
					c[i][j][4] = c[i][j][1];
					c[i][j][5] = c[i][j][2];
				}
			}
		}
	}
	
	public static void main(String[] args) {
		
	}

}
