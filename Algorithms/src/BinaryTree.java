
import java.util.ArrayList;
import java.util.List;

/**
 * @author Siva Karthik Gade
 */
public class BinaryTree {

	int maxBSTSize;
	String maxBSTITree;
	
	public BinaryTree() {
		this.maxBSTSize = 0;
		this.maxBSTITree = "";
	}
	
	public Node calculateMaxBSTSize(int n, List<Integer> pTree, List<Integer> iTree) {
		if(pTree == null || iTree == null || pTree.size() == 0 || iTree.size() == 0)
			return null;

		Node a = new Node(pTree.get(n-1));
		int aPos = iTree.indexOf(a.id);
		a.l = calculateMaxBSTSize(aPos, pTree.subList(0, aPos), iTree.subList(0, aPos));
		a.r = calculateMaxBSTSize(n-aPos-1, pTree.subList(aPos, n-1), iTree.subList(aPos+1, n));

		a.treeSize = 1 + (a.l!=null?a.l.treeSize:0) + (a.r!=null?a.r.treeSize:0);

		if(a.l == null && a.r == null) {
		} else if(a.l == null && a.r != null) {
			a.min = a.id > a.r.min ? a.r.min : a.id;
			a.max = a.id < a.r.max ? a.r.max : a.id;
		} else if(a.l != null && a.r == null) {
			a.min = a.id > a.l.min ? a.l.min: a.id;
			a.max = a.id < a.l.max ? a.l.max: a.id;
		} else {
			if(!(a.min < a.l.min && a.min < a.r.min))
				a.min = a.l.min < a.r.min ? a.l.min : a.r.min;
			if(!(a.max > a.l.max && a.max > a.r.max))
				a.max = a.l.max > a.r.max ? a.l.max : a.r.max;
		}

		if((a.l == null || (a.l.isBST && a.l.max<a.id))
				&& (a.r == null || (a.r.isBST && a.id<a.r.min))) {
			a.isBST = true;
			a.maxBSTSize = a.treeSize;
			if(a.l != null)
				a.iTreeBST = a.l.iTreeBST + a.iTreeBST;
			if(a.r != null)
				a.iTreeBST = a.iTreeBST + a.r.iTreeBST;
		} else {
			a.isBST = false;
			
			if(a.l == null || (a.l.isBST && a.l.max<a.id)) {
				if(a.l != null) {
					a.maxBSTSize += a.l.treeSize;
					a.iTreeBST = a.l.iTreeBST + a.iTreeBST;
				}
			} else {
				ReturnValue val = getLocalMaxBSTSize(a.l,Integer.MIN_VALUE,a.id);
				a.maxBSTSize += val.size;
				a.iTreeBST = val.iTree + a.iTreeBST;
			}
			
			if(a.r == null || (a.r.isBST && a.id<a.r.min)) {
				if(a.r != null) {
					a.maxBSTSize += a.r.treeSize;
					a.iTreeBST = a.iTreeBST + a.r.iTreeBST;
				}
			} else {
				ReturnValue val = getLocalMaxBSTSize(a.r,a.id,Integer.MAX_VALUE);
				a.maxBSTSize += val.size;
				a.iTreeBST = a.iTreeBST + val.iTree;
			}
		}

		if(this.maxBSTSize < a.maxBSTSize) {
//			log("id of node setting global max: "+a.id+", max size: "+a.maxBSTSize+", iTree: "+a.iTreeBST);
			this.maxBSTSize = a.maxBSTSize;
			this.maxBSTITree = a.iTreeBST;
		} else {
//			log("id of node NOT setting global max: "+a.id+", max size: "+a.maxBSTSize+", iTree: "+a.iTreeBST);
		}

		return a;
	}
	
	public ReturnValue getLocalMaxBSTSize(Node a, int min, int max) {
		ReturnValue val = new ReturnValue(), temp;
		if(a != null && (min < a.id && a.id < max)) {
			val.size = 1;
			val.iTree = a.id+",";
			temp = getLocalMaxBSTSize(a.l, min, a.id);
			val.size = val.size + temp.size;
			val.iTree = temp.iTree + val.iTree;
			temp = getLocalMaxBSTSize(a.r, a.id, max);
			val.size = val.size + temp.size;
			val.iTree = val.iTree + temp.iTree;
		}
		return val;
	}
	
	public static void main(String args[]) {
		BinaryTree tree;
		
		int argsCnt = 0;
		int dataSetsCnt = Integer.parseInt(args[argsCnt++]);
		for(int i = 1; i <= dataSetsCnt; i++) {
			int nodeCnt = Integer.parseInt(args[argsCnt++]);
//			log("Input tree size: "+nodeCnt);
			List<Integer> pTree = new ArrayList<Integer>();
			List<Integer> iTree = new ArrayList<Integer>();
			for(int j = 1; j <= nodeCnt; j++) {
				pTree.add(Integer.parseInt(args[argsCnt++]));
			}
			for(int j = 1; j <= nodeCnt; j++) {
				iTree.add(Integer.parseInt(args[argsCnt++]));
			}
			tree = new BinaryTree();
			tree.calculateMaxBSTSize(nodeCnt, pTree, iTree);
			log("Input instance:"+i+" -> Size of max BST: "+tree.maxBSTSize+", InOrder Tree Representation of Solution: "+tree.maxBSTITree);
		}
	}
	
	static void log(String str) {
		System.out.println(str);
	}
}
