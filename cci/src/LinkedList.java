import java.util.HashSet;

public class LinkedList {

    // print linked list
    public static void printList(Node input){
        while(input != null){
            System.out.print(input.data + " -> ");
            input = input.next;
        }
        System.out.println("end");
    }


    /*
    Return Kth to Last:
    Implement an algorithm to find the kth to last element of a singly linked list.
    Yeah.. great I completely misunderstood the dump question....

    Another algorithm: use two pointer to create a gap of k, and then loop them at the same pace
    til hit the end of the linked list
     */
    public static Node MoveKToLast(Node input, int k) {
        Node n = input;
        Node tmp = null;

        // special cases: k=1
        if (k ==1){
            int d = input.data;
            while (n.next != null)
                n = n.next;
            n.appendToTail(d);
            return input.next;
        }

        int i = 1;
        while(n.next != null) {
            if (i == k - 1) {
                tmp = n.next;
                if (n.next.next != null)
                    n.next = n.next.next;
                else
                    break;
            }

            n = n.next;
            i++;
        }

        // special case k = last
        if (tmp != null)
            tmp.next = null;
            n.next = tmp;
        return input;
    }

    // runtime should be O(N)
    public static Node ReturnKToLast(Node input, int k){
        if (input == null)
            return input;

        // find the length
        Node cur = input;
        int i = 0;
        while (cur != null){
            i++;
            cur = cur.next;
        }

        // loop again
        Node head = input;
        int compl = i - k;
        while (compl >0){
            head = head.next;
            compl--;
        }
        return head;
    }


    /*
    Delete Middle Node:
    Implement an algorithm to delete a node in the middle
    (i.e., any node but the first and last node, not necessarily the exact middle) of a singly linked list, given only access to that node.

    EXAMPLE
    lnput:the node c from the linked list a->b->c->d->e->f
    Result: nothing is returned, but the new linked list looks like a->b->d->e->f
    */

    public static Node deleteMidNode(Node input, int target){

        if(input == null)
            return input;

        Node head = input;
        while(head.next != null){
            if (head.next.data == target){
                head.next = head.next.next;
                break;
            }
            head = head.next;
        }
        return input;
    }

    // do the deletion only base on the given node
    public static boolean deleteMidNode(Node targetNode){
        if (targetNode == null || targetNode.next == null)
            return false;

        Node next = targetNode.next;
        targetNode.data = next.data;
        targetNode.next = next.next;
        return true;
    }

    /*
    Partition:
    Write code to partition a linked list around a value x, such that all nodes less than x come before all nodes greater than
    or equal to x. If x is contained within the list the values of x only need to be after the elements less than x (see below).
    The partition element x can appear anywhere in the "right partition"; it does not need to appear between the left and right partitions.

    EXAMPLE
    Input: 3 -> 5 -> 8 -> 5 -> 10 -> 2 -> 1     [partition=5]
    Output: 3 -> 1 -> 2 -> 10 -> 5 -> 5 -> 8
    */
    public static Node Partition(Node input, int x){
        if(input == null)
            return input;

        Node head = input;
        Node right = new Node(0);
        Node left = new Node(0);
        Node lhead = left;
        Node rhead = right;
        while(head != null){
            if (head.data < x){
                left.next = head;
                left = left.next;
            }else{
                right.next = head;
                right = right.next;
            }
            head = head.next;
        }
        right.next = null;
        if (rhead.next != null)
            left.next = rhead.next;

        return lhead.next;
    }



    /*
    Sum Lists:
    You have two numbers represented by a linked list,where each node contains a single digit.
    The digits are stored in reverse order,such that the 1's digit is at the head of the list.
    Write a function that adds the two numbers and returns the sum as a linked list.

    EXAMPLE
    Input: (7-> 1 -> 6) + (5 -> 9 -> 2).That is, 617 + 295. Output:2 -> 1 -> 9.That is, 912.
    FOLLOW UP
    Suppose the digits are stored in forward order. Repeat the above problem.
    Input: (6 -> 1 -> 7) + (2 -> 9 -> 5).That is, 617 + 295. Output:9 ->1 ->2. That is, 912.


    Another method: use recursion, take digit of n1, digit of n2 and carry as arguments.
    */
    public static Node SumList(Node num1, Node num2){
        Node retval = new Node(0);

        int i = 0;
        double n1 = 0;
        while(num1 != null){
            n1 += num1.data * Math.pow(10, i);
            i++;
            num1 = num1.next;
        }

        i = 0;
        double n2 = 0;
        while(num2 != null){
            n2 += num2.data * Math.pow(10, i);
            i++;
            num2 = num2.next;
        }

        int result = (int)(n1 + n2);
        i = Integer.toString(result).length() -1;
        System.out.println("num1 is " + n1 + " num2 is " + n2 + " result is " + result + " i is " + i);
        while(i>=0){
            int d = (int)(result/(Math.pow(10, i)));
            retval.appendToTail(d);
            result -= d* (Math.pow(10, i));
            i--;
        }
        return retval.next;
    }



    /*
    Palindrome:
    Implement a function to check if a linked list is a palindrome.

    method 1 (iterative): two pointers method, one 2x faster than the other. Find the midpoint and then check
    method 2: reverse the linked list and check from the head
    method 3 (recurrsive): as below
     */
    public static boolean isPalindrome(Node input){
        if (input == null || input.next == null)
            return true;

        int len = getLen(input);
        boolean retval = recurse(input, len).result;
        if (retval == false)
            System.out.println("This is not a parlindrome!");
        else
            System.out.println("This is a parlindrome!");
        return retval;
    }

    public static PalindromeNode recurse(Node input, int length){

        PalindromeNode retval = new PalindromeNode(input, true);

        if (length == 0)
            return retval;
        if (length == 1){
            retval.n = input.next;
            return retval;
        }
        PalindromeNode cur = recurse(input.next, length-2);
        retval.n = cur.n.next;

        if (cur.result == false || cur.n.data != input.data)
            retval.result = false;
        return retval;
    }

    public static int getLen(Node input){
        int len = 0;

        while(input != null){
            input = input.next;
            len++;
        }
        System.out.println("Length: " + len);
        return len;
    }

    static class PalindromeNode{
        Node n;
        boolean result;

        PalindromeNode(Node node, boolean res){
            n = node;
            result = res;
        }
    }



    /*
    Intersection:
    Given two (singly) linked lists, determine if the two lists intersect.
    Return the intersecting node. Note that the intersection is defined based on reference, not value.
    That is, if the kth node of the first linked list is the exact same node (by reference) as the jth
    node of the second linked list, then they are intersecting.

    first thought: double loop, check each node if they point to the same node. Runtime should be O(MN)
    second thought: find the length first, then use two pointers method.
    */
    public static Node Intersection(Node list1, Node list2){

        if (list1 == null || list2 == null)
            return null;

        // special case: heads join
        if (list1 == list2) {
            System.out.println("lists intersect at " + list1.data);
            return list1;
        }

        int len1 = getLen(list1);
        int len2 = getLen(list2);
        int diff = Math.abs(len1 - len2);

        Node headL = (len1>len2)? list2: list1;
        Node headS = (len1>len2)? list1: list2;

        while(diff > 0){
            headS = headS.next;
            diff--;
        }

        while(headL != null && headS != null){
            if (headL == headS) {
                System.out.println("lists intersect at " + headL.data);
                return headL;
            }
            headL = headL.next;
            headS = headS.next;
        }

        return null;
    }


    /*
    Loop Detection:
    Given a circular linked list, implement an algorithm that returns the node at the beginning of the loop.
    DEFINITION
    Circular linked list: A (corrupt) linked list in which a node's next pointer points to an earlier node,
    so as to make a loop in the linked list.
    EXAMPLE
    Input: A -> B -> C -> D -> E -> C [the same C as earlier]
    Output: C

    first thought: build a hashset, record all nodes.

    Second method: two pointer method. One moves 2x faster than the other. they will eventually meet each other.
    then loop from the head and find the start of the loop.
     */
    public static Node isLoop(Node input){
        if(input == null)
            return null;

        HashSet<Node> map = new HashSet<>();

        Node head = input;

        while(head != null){
            System.out.print(head.data + " ");
            if (map.contains(head)) {
                System.out.println("The start of loop is " + head.data);
                return head;
            }
            else
                map.add(head);
            head = head.next;
        }
        return null;
    }


    public static void main(String args[]) {

        Node input = new Node(1);
        input.appendToTail(3);
        input.appendToTail(8);
        input.appendToTail(9);
        input.appendToTail(2);
        input.appendToTail(3);
        input.appendToTail(4);
        input.appendToTail(5);
        input.appendToTail(6);

        Node num1 = new Node(1);
        num1.appendToTail(2);
        //num1.appendToTail(3);
        num1.appendToTail(2);
        num1.appendToTail(1);

        Node num2 = new Node(1);


        // TEST: MoveKToLast
        //printList(input);
        //printList(MoveKToLast(input, 5));
        //printList(ReturnKToLast(input, 6));

        // TEST: deleteMidNode
        // printList(deleteMidNode(input, 5));

        // TEST: Partition
        //Partition(input, 7);
        //printList(Partition(input, 9));

        // TEST: SumList
        //printList(SumList(num1, num2));

        // TEST: palindrome
        //printList(num1);
        //isPalindrome(num1);


        // TEST: intersection

        Node a = new Node(1);
        a.appendToTail(2);
        a.appendToTail(3);
        a.appendToTail(4);
        a.appendToTail(5);


        Node b = new Node(11);
        b.appendToTail(22);
        b.appendToTail(33);
        b.appendToTail(44);
        b.appendToTail(55);

        a.next.next.next.next.next = a;
        //printList(a);
        //printList(b);
        //Intersection(a, b);


        // TEST: isLoop
        isLoop(a);

    }





    // Node class
    static class Node {
        int data;
        Node next;

        public Node(int d) {
            data = d;
        }

        void appendToTail(int d) {
            Node end = new Node(d);
            Node n = this;
            while (n.next != null) {
                n = n.next;
            }
            n.next = end;
        }
    }
}
