
public class PartitionProcedure {

	public PartitionProcedure() {
		// TODO Auto-generated constructor stub
	}

	public int[] partition(int[] input) {
		int q = 0, t = 0, j = 0;
		int[] retVal = new int[2];
		int pivot = input[input.length - 1];

		t = -1;
		for(j = 0; j < input.length - 1; j++) {
			if(input[j] <= pivot) {
				t++;
				swap(input, t, j);
			}
		}
		
		t = t+1;
		swap(input, t, input.length-1);
		
		printArray(input);
		
		q = t;
		for(j = 0; j < q; j++) {
			if(input[j] == input[q]) {
				while(--q >= 0 && input[q] == input[t]){}
				if(q>=0)
					swap(input, j, q);
			}
		}
		
		retVal[0] = q;
		retVal[1] = t;
		
		return retVal;
	}
	
	private void swap(int[] input, int i, int j) {
		int temp = 0;
		temp = input[i];
		input[i] = input[j];
		input[j] = temp;
	}
	
	private void printArray(int[] input) {
		for(int i = 0; i < input.length; i++) {
			//System.out.print(" " + i + (i==1?"st: ":(i==2?"nd: ":(i==3?"rd: ":"th: "))) + input[i] + ";");
			System.out.print(input[i]+",");
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		PartitionProcedure proc = new PartitionProcedure();
		
		int[] input = {17,17,18,17,17,17,17,23,543,17,73,17,21,17,17,17};
		proc.printArray(input);
		
		int[] pivot = proc.partition(input);
		proc.printArray(input);
		System.out.println(pivot[0] + "," + pivot[1]);
	}

}
