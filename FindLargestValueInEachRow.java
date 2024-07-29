import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//https://leetcode.com/problems/find-largest-value-in-each-tree-row
public class FindLargestValueInEachRow {
    public static List<Integer> largestValuesBFS(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if(root == null) return result;

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        while(!q.isEmpty()) { //O(n) T.C, O(n) S.C
            int size = q.size(); //take size for level order traversal
            int max = Integer.MIN_VALUE; //reset max each time for new level
            for(int i=0; i < size; i++) { //iterate over current level
                TreeNode current = q.poll();
                assert current != null;
                max = Math.max(max, current.val); //find max
                if(current.left != null) q.add(current.left);
                if(current.right != null) q.add(current.right);
            }
            result.add(max);
        }
        return result;
    }

    static List<Integer> result;
    public static List<Integer> largestValuesDFS(TreeNode root) {
        result = new ArrayList<>();
        dfs(root, 0);
        return result;
    }

    static void dfs(TreeNode node, int level) { //O(n) T.C, O(1) S.C
        if(node == null) return;

        if(level == result.size()) result.add(node.val); //if the current level reaches current result size
        else result.set(level, Math.max(result.get(level), node.val)); //else, reset the value at current level

        dfs(node.left, level+1);
        dfs(node.right, level+1);
    }

    public static void main(String[] args) {
        // Test Case 1: Example Tree
        TreeNode root1 = new TreeNode(1,
                new TreeNode(3,
                        new TreeNode(5),
                        new TreeNode(3)),
                new TreeNode(2,
                        null,
                        new TreeNode(9))
        );

        // Test Case 2: Another Tree
        TreeNode root2 = new TreeNode(10,
                new TreeNode(20,
                        new TreeNode(40),
                        new TreeNode(60)),
                new TreeNode(30,
                        null,
                        new TreeNode(70))
        );

        // Test Case 3: Single Node Tree
        TreeNode root3 = new TreeNode(7);

        // Test the BFS and DFS methods
        System.out.println("Test Case 1:");
        printResults(largestValuesBFS(root1));

        System.out.println("Test Case 2:");
        printResults(largestValuesDFS(root2));

        System.out.println("Test Case 3:");
        printResults(largestValuesBFS(root3));

        System.out.println("Test Case 4:");
        printResults(largestValuesDFS(null));
    }

    private static void printResults(List<Integer> results) {
        System.out.println("Largest values in each row: " + results);
    }
}