
public class LongestCommonSubsequence {

	public String[][] lcsSoln;
	
	public LongestCommonSubsequence() {
		// TODO Auto-generated constructor stub
	}

	public void getLCSWithDP(String x, String y) {
		lcsSoln = new String[x.length()+1][y.length()+1];
		
		for(int i = 1; i <= x.length(); i++) {
			for(int j = 1; j <= y.length(); j++) {
				if(y.substring(1,j).indexOf(x.charAt(i)) != -1) {
					lcsSoln[i][j] = ""+x.charAt(i);
				}
			}
		}
	}
	
	public static void main(String[] args) {
		LongestCommonSubsequence lcs = new LongestCommonSubsequence();
//		char[] x = {0,'a','b','c','d','e','f','g','h','i','j'}, y = {0,'b','d','u','h','i','m'};
//		String z = lcs.getLCS(x, y);
//		
//		for(int i = 0; i < z.length(); i++) {
//			System.out.print(z.charAt(i)+" ");
//		}

		String x = "0abcdefghij", y = "0bdfhi";
		lcs.getLCSWithDP(x, y);
	}

}


//public String getLCS(char[] x, char[] y) {
//String z = null;
//z = get(x, 1, y, 1);
//return z;
//}
//
//public String get(char[] x, int xPos, char[] y, int yPos) {
//
//if(x[xPos] == y[yPos]) {
//	return x[xPos] + ((x.length>xPos+1 && y.length>yPos+1)?get(x, xPos+1, y, yPos+1):"");
//} else {
//	String s1 = (y.length>yPos+1?get(x, xPos, y, yPos+1):"");
//	String s2 = (x.length>xPos+1?get(x, xPos+1, y, yPos):"");
//	if(s1.length() >= s2.length()) {
//		return s1;
//	} else {
//		return s2;
//	}
//}
//}

