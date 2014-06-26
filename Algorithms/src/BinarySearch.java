
public class BinarySearch {

	public BinarySearch() {
		// TODO Auto-generated constructor stub
	}

	public int find(int[] input, int key, int start, int end) {
		int pos = -1;
		int mid = (start + end)/2;
		if(start > end) {
			System.out.println("in 1:"+start+","+end+".");
			return pos;
		} else if(input[mid] == key) {
			System.out.println("in 2:"+start+","+end+".");
			pos = mid;
		} else if(input[mid] > key) {
			System.out.println("in 3:"+start+","+end+".");
			pos = find(input, key, start, mid - 1);
		} else {
			System.out.println("in 4:"+start+","+end+".");
			pos = find(input, key, mid + 1, end);
		}
		System.out.println("in 5:"+start+","+end+".");
		return pos;
	}
	
	public static void main(String[] args) {
		BinarySearch s = new BinarySearch();
		
		int[] input = {0,2,4,6,8,10,12,14,16,18,20,21,22,24,26,28,30,32,34,36,38,40,42,44,46,48,50};
		int key = 36;
		int pos = s.find(input, key, 0, input.length - 1);
		if(pos == -1) {
			System.out.println("element not found in array");
		} else {
			System.out.println("element found at " + pos + "th position in the array");
		}
	}

}
