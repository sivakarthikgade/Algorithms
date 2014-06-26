
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
 
class AllPaths {
 
 static int dim = 5, size = 0; // dim is number of nodes in graph
 // size had been used to keep record of index to remove node from Arraylist
    static boolean[] color = new boolean[dim + 1];      // to remember visit
    static int[][] graph = new int[10][10];
    static ArrayList<Integer> al = new ArrayList<Integer>();
 
    public static void main(String[] args) throws IOException {
        for (int I = 1; I <= dim; I++) {
            String[] s = args[I-1].split(",");
            int len = s.length;
            for (int J = 1; J <= len; J++) {
                graph[I][J] = Integer.parseInt(s[J - 1]);
            }
        }
        Arrays.fill(color, false);      // initially all are unvisited
 
        int src = Integer.parseInt(args[5]);      //Source node
        int dst = Integer.parseInt(args[6]);      //Destination node
 
        dfs(src, dst);  // backtracking
    }
 
    static void dfs(int src, int dst) {
        al.add(src);
        size++;
        color[src] = true;
        if (src == dst) {       // tests for base condition to stop
            for (Integer i : al) {
                //     Prints the path
                System.out.print(i + "  ");
            }
            System.out.println();
            return;
        }
        for (int I = 1; I <= dim; I++) {
            if (graph[src][I] == 1) {
                if (color[I] == false) {
                    dfs(I, dst);        // These lines do
                    color[I] = false;   // main job of backtracking
                    size--;
                    al.remove(size);
                }
            }
        }
    }
}