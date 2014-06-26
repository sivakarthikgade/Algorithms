
public class BinarySearchForRange {

	public BinarySearchForRange() {
	}

//	public int find(int[] input, int key, int start, int end) {
//		int pos = -1;
//		int mid = (start + end)/2;
//		if(start <= end && mid < input.length - 1) {
//			if(input[mid] <= key) {
//				System.out.println("in 1:"+start+","+end);
//				if(input[mid+1] > key) {
//					pos = mid;
//				} else {
//					pos = find(input, key, mid + 1, end);
//				}
//			} else if(input[mid] > key) {
//				System.out.println("in 2:"+start+","+end);
//				if(mid-1 >= 0 && input[mid-1] <= key) {
//					pos = mid - 1;
//				} else {
//					pos = find(input, key, start, mid - 1);
//				}
//			}
//		}
//		System.out.println("in 3:"+start+","+end);
//		return pos;
//	}

	public int find(int[] input, int key, int start, int end) {
		int pos = -1;
		int mid = (start + end)/2;
		if(start <= end && mid < input.length - 1) {
			if(input[mid] <= key && key < input[mid+1]) {
				pos = mid;
			} else if(input[mid] > key) {
				pos = find(input, key, start, mid - 1);
			} else if(input[mid+1] <= key) {
				pos = find(input, key, mid + 1, end);
			}
		}
		return pos;
	}

	public static void main(String[] args) {
		BinarySearchForRange s = new BinarySearchForRange();
		int[] input = {0,1,4,9,16,25,36,49,64,81,100,121,144,169,196,225,256,289,324,361,400,99999};
		int key = 169;
		int pos = s.find(input, key, 0, input.length - 1);
		
		if(pos == -1) {
			System.out.println("Element doesn't belong any data range found in the array");
		} else {
			System.out.println("Element belongs to the range " + pos + ", " + (pos+1) + " slots. "
					+ "Data bucket range: " + input[pos] + ", " + input[pos+1]+".");
		}
	}

}
