import java.util.ArrayList;
import java.util.LinkedList;

public class Tree {
    public TreeNode root;

    static class TreeNode {
        int data;
        TreeNode left;
        TreeNode right;

        TreeNode(int d){
            data = d;
        }
    }

    public static void PreOrderPrint(TreeNode rootNode){
        if(rootNode != null){
            System.out.println(rootNode.data);
            PreOrderPrint(rootNode.left);
            PreOrderPrint(rootNode.right);
        }

    }

    public static void InOrderPrint(TreeNode rootNode){
        if(rootNode != null){
            InOrderPrint(rootNode.left);
            System.out.println(rootNode.data);
            InOrderPrint(rootNode.right);
        }

    }

    /*
    4.2
    Minimal Tree:
    Given a sorted (increasing order) array with unique integer elements, write an algo­
    rithm to create a binary search tree with minimal height.

    BST with minimal height -> then its a balanced BST = =
    known the lenth, sorted array
    */
    public static TreeNode MinimalTree(int[] inputs){
        if (inputs.length < 1)
            return null;

        return MinimalTreeRecurse(inputs, 0, inputs.length-1);
    }

    public static TreeNode MinimalTreeRecurse(int[] inputs, int start, int end){
        int pos = (start + end)/2;

        if (start >= end)
            return null;

        TreeNode curNode = new TreeNode(inputs[pos]);
        curNode.left = MinimalTreeRecurse(inputs, start, pos);
        curNode.right = MinimalTreeRecurse(inputs, pos+1, end);
        return curNode;
    }


    /*
    List of Depths:
    Given a binary tree, design an algorithm which creates a linked list of all the nodes
    at each depth (e.g., if you have a tree with depth D, you'll have D linked lists).

    so link the nodes at the same level

     */

    public static ArrayList<LinkedList<TreeNode>> ListOfDepths(TreeNode input){
        ArrayList<LinkedList<TreeNode>> retval = new ArrayList<LinkedList<TreeNode>>();

        ListOfDepthRecurse(input, 0, retval);
        return retval;
    }

    // this one use depth first search
    public static void ListOfDepthRecurse(TreeNode cur, int level, ArrayList<LinkedList<TreeNode>> retval){
        if (cur == null)
            return;

        LinkedList<TreeNode> curLevel = null;

        if (retval.size() == level){
            curLevel = new LinkedList<TreeNode>();
            retval.add(curLevel);
        }else
            curLevel = retval.get(level);

        curLevel.add(cur);
        System.out.println("Add " + cur.data + " to level " + level);

        ListOfDepthRecurse(cur.left, level+1, retval);
        ListOfDepthRecurse(cur.right, level+1, retval);
    }

    // ALTERNATIVE: breadth-first search, iterate each tree level
    public static ArrayList<LinkedList<TreeNode>> BFSLinkedList(TreeNode input){
        ArrayList<LinkedList<TreeNode>> retval = new ArrayList<LinkedList<TreeNode>>();
        if (input == null)
            return null;

        LinkedList<TreeNode> cur = new LinkedList<TreeNode>();
        cur.add(input);

        while(cur != null){
            retval.add(cur);
            LinkedList<TreeNode> parents = cur;
            cur = new LinkedList<TreeNode>();
            for (TreeNode i : parents){
                if (i.left != null)
                    cur.add(i.left);
                if (i.right != null)
                    cur.add(i.right);
            }
        }
        return retval;
    }


    public static void main(String args[]){

        // 4.2 TEST
        //int[] inputArray = {1,2,3,4,5,6,7,8,9};
        //PreOrderPrint(MinimalTree(inputArray));
        //InOrderPrint(MinimalTree(inputArray));


        // 4.3 TEST
        int[] inputArray = {1,2,3,4,5,6,7,8,9};
        TreeNode input = MinimalTree(inputArray);
        ListOfDepths(input);
    }

}
