
public class RodCuttingWithVarCuttingCost {

	public RodCuttingWithVarCuttingCost() {
	}
	
	public void cutRod(int[] p, int n, double[] c) {
		double[][] pc = new double[n][n+1];
		
		for(int i = 0; i < n; i++) {
			for(int j = 1; j <= n; j++) {
				if(i >= j) {
					pc[i][j] = -1;
				} else if(i == 0) {
					pc[i][j] = p[j];
				} else {
					pc[i][j] = getMax(pc, i, j);
				}
			}
		}
		
		printSolution(pc, 8, c);
	}
	
	private double getMax(double[][] pc, int i, int j) {
		double max = -1;
		for(int cnt = j-1; cnt > 0 && pc[i-1][cnt] != -1; cnt--) {
			if(pc[0][j-cnt] + pc[i-1][cnt] > max) {
				max = pc[0][j-cnt] + pc[i-1][cnt];
			}
		}
		return max;
	}
	
	public void printSolution(double[][] pc, int k, double[] c) {
		double max = -1;
		int x=0,y=0;
		for(int i = 0; i < pc.length; i++) {
			if(pc[i][k] - c[i] > max) {
				max = pc[i][k] - c[i];
				x = i;
				y = k;
			}
		}
		System.out.println("max revenue for k: "+k+" is: "+max+". ("+x+","+y+")");
	}
	
	public static void main(String[] args) {
		RodCuttingWithVarCuttingCost ct = new RodCuttingWithVarCuttingCost();
		int[] p = {0,3,6,6,11,11,12,16,16,18,30};
		int n = 10;
		double[] c = new double[n];
		for(int i = 1; i < n; i++) {
			c[i] = c[i-1] + ((double)2/i);
		}
		ct.cutRod(p, n, c);
	}

}






















//public void cutRod(int[] p, int n, int c_j) {
//	double[] r = new double[p.length];
//	int[] s = new int[p.length], cc = new int[p.length];
//	
//	r[0] = 0;
//	
//	for(int j = 0; j < cc.length; j++) {
//		cc[j] = 0;
//	}
//	
//	for(int j = 1; j <= n; j++) {
//		double q = p[j];
//		s[j] = j;
//		for(int i = 1; i <= j-1; i++) {
//			if(p[i] + r[j-i] - ((double)c_j/(cc[j-i]+1)) > q) {
//				q = p[i] + r[j-i] - ((double)c_j/(cc[j-i]+1));
//				cc[j] = cc[j-1] + 1;
//				s[j] = i;
//			}
//		}
//		r[j] = q;
//	}
//	
//	printSolution(p, r, s, 8);
//}



//public void cutRod(int[] p, int n, int c_j) {
//	double[] r = new double[p.length];
//	int[] s = new int[p.length], cc = new int[p.length];
//	
//	r[0] = 0;
//	
//	for(int j = 0; j < cc.length; j++) {
//		cc[j] = 0;
//	}
//	
//	for(int j = 1; j <= n; j++) {
//		double q = p[j];
//		s[j] = j;
//		for(int i = 1; i <= j-1; i++) {
//			if(p[i] + r[j-i] - getCostForCuts(c_j, cc[j-1]+1) > q) {
//				q = p[i] + r[j-i];
//				cc[j] = cc[j-1] + 1;
//				s[j] = i;
//			}
//		}
//		r[j] = q;
//	}
//	
//	for(int j = 1; j <= n; j++) {
//		r[j] = r[j] - getCostForCuts(c_j, cc[j]);
//	}
//	
//	printSolution(p, r, s, 8);
//}
//
//private double getCostForCuts(int c_j, int cc) {
//	double rv = 0;
//	for(int i = 1; i <= cc; i++) {
//		rv = rv + ((double)c_j/i);
//	}
//	return rv;
//}
//



//public void cutRod(int[] p, int n, int c_j) {
//	double[] r = new double[p.length];
//	int[] s = new int[p.length], cc = new int[p.length];
//	
//	r[0] = 0;
//	
//	for(int j = 0; j < cc.length; j++) {
//		cc[j] = 0;
//	}
//	
//	for(int j = 1; j <= n; j++) {
//		double q = p[j];
//		s[j] = j;
//		for(int i = 1; i <= j-1; i++) {
//			System.out.println("Outside: "+j+","+i+","+(j-i)+","+p[i]+","+r[j-i]+","+c_j+","+cc[j-i]+","+q);
//			if(p[i] + r[j-i] - ((double)c_j/(cc[j-i]+1)) > q) {
//				q = p[i] + r[j-i] - ((double)c_j/(cc[j-i]+1));
//				cc[j] = cc[j-i] + 1;
//				s[j] = i;
//				System.out.println("Inner: "+j+","+i+","+(j-i)+","+p[i]+","+r[j-i]+","+c_j+","+cc[j-i]+","+q);
//			}
//		}
//		r[j] = q;
//	}
//	
//	printSolution(p, r, s, cc, 8);
//}
