public class Lesson3_list_rev {
    public static void main(String[] args) {
        int[] test_data = {1, 2, 3, 4, 5};
        My_List myList = new My_List();

        for (int v : test_data) {
            myList.add(v);
        }

        myList.print();
        System.out.println();

        myList.reverse();
        myList.print();

    }


}


class My_List {

    Node head;

    public void add(Integer value) {
        Node next = head;
        head = new Node(value);
        head.next = next;
    }

    public void reverse() {
        if (head != null && head.next != null){
            reverse(head, null);
        }
    }

    private void reverse(Node current, Node newNext) {

        if (current.next != null) {
            reverse(current.next, current);
        } else head = current;
        current.next = newNext;
    }

    public class Node {
        Integer value;
        Node next;

        public Node(Integer value) {
            this.value = value;
        }
    }

    public void print() {
        Node current = head;
        while (current != null) {
            System.out.println(current.value);
            current = current.next;
        }
    }

}