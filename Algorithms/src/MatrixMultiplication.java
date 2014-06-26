
public class MatrixMultiplication {

	public MatrixMultiplication() {
		// TODO Auto-generated constructor stub
	}

	public void findOptimalWayToMultiply(int[] p) {
		int n = p.length-1;
		int[][] m = new int[n+1][n+1];
		int[][] s = new int[n+1][n+1];
		
//		This step is not required as java automatically initializes int elements to 0
//		for(int i = 1; i <= n; i++) {
//			m[i][i] = 0;
//		}
		
		for(int j = 2; j <= n; j++) {
			for(int i = j-1; i >= 1; i--) {
				int q = Integer.MAX_VALUE;
				for(int k = i; k <= j-1; k++) {
					if(q > m[i][k] + m[k+1][j] + p[i-1]*p[k]*p[j]) {
						q = m[i][k] + m[k+1][j] + p[i-1]*p[k]*p[j];
						m[i][j] = q;
						s[i][j] = k;
					}
				}
			}
		}
		
		System.out.println("m[1][n]: "+m[1][n]);
		System.out.println("s[1][n]: "+s[1][n]);
		
		printOptimalSolution(s, 1, n);
	}
	
	public void printOptimalSolution(int[][] s, int i, int j) {
		if(i == j) {
			System.out.print("A"+i);
		} else {
			System.out.print("(");
			printOptimalSolution(s, i, s[i][j]);
			printOptimalSolution(s, s[i][j]+1, j);
			System.out.print(")");
		}
	}
	
	public static void main(String[] args) {
		MatrixMultiplication mm = new MatrixMultiplication();
//		int[] p = {25,10,100,5,17,23,6,50,2,7,19};
//		int[] p = {10,100,5,50};
		int[] p = {5,10,3,12,5,50,6};
		mm.findOptimalWayToMultiply(p);
	}

}
