
/**
 * @author Siva Karthik Gade
 */
public class Node extends Object {

	public int id;
	public Node l, r;
	public int min;
	public int max;
	public boolean isBST;
	public int treeSize;
	public int maxBSTSize;
	public String iTreeBST;
	
	public Node(int id) {
		this.id = id;
		this.l = null;
		this.r = null;
		this.min = id;
		this.max = id;
		this.isBST = true;
		this.treeSize = 1;
		this.maxBSTSize = 1;
		this.iTreeBST = id+",";
	}
	
    public boolean equals(Node node) {
    	return this.id==node.id?true:false;
    }

}
