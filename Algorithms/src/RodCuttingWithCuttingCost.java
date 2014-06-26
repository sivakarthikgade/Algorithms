
public class RodCuttingWithCuttingCost {

	public RodCuttingWithCuttingCost() {
	}
	
	public void cutRod(int[] p, int n, int c) {
		int[] r = new int[p.length], s = new int[p.length];
		
		r[0] = 0;
		for(int j = 1; j <= n; j++) {
			int q = p[j];
			s[j] = j;
			for(int i = 1; i <= j-1; i++) {
				if(p[i] + r[j-i] - c > q) {
					q = p[i] + r[j-i] - c;
					s[j] = i;
				}
			}
			r[j] = q;
		}
		
		printSolution(p, r, s, 9);
	}
	
	public void printSolution(int[] p, int[] r, int[] s, int n) {
		int i = n;
		System.out.println();
		System.out.print("Total Revenue: "+r[n]+" [");
		while(i > 0) {
			System.out.print(s[i]+",");
			i = i - s[i];
		}
		System.out.println("]");

		for(int j = 0; j < p.length; j++) {
			System.out.print(j+",\t");
		}
		System.out.println("");
		for(int j = 0; j < p.length; j++) {
			System.out.print(p[j]+",\t");
		}
		System.out.println("");
		for(int j = 0; j < r.length; j++) {
			System.out.print(r[j]+",\t");
		}
		System.out.println("");
		for(int j = 0; j < s.length; j++) {
			System.out.print(s[j]+",\t");
		}
	}

	public static void main(String[] args) {
		RodCuttingWithCuttingCost ct = new RodCuttingWithCuttingCost();
		int[] p = {0,1,5,8,10,13,17,17,20,24,30};
		int n = 10;
		int c = 0;
		ct.cutRod(p, n, c);
	}

}
