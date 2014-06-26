
public class RodCuttingMainClass {

	public RodCuttingMainClass() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
//		int[] p = {0,10,5,8,10,13,17,17,20,24,30};
		int[] p = {0,3,6,6,11,11,12,16,16,18,30};
		int n = 10;
		int c = 2;

		RodCutting ct1 = new RodCutting();
		ct1.cutRod(p, n);

		RodCuttingWithCuttingCost ct2 = new RodCuttingWithCuttingCost();
		ct2.cutRod(p, n, c);

//		RodCuttingWithVarCuttingCost ct3 = new RodCuttingWithVarCuttingCost();
//		ct3.cutRod(p, n, c);
	}

}
