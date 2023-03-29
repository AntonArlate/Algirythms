import java.util.Random;

public class Lesson4_test {
    public static void main(String[] args) {
        Lesson4_ColorTree colorTree = new Lesson4_ColorTree();

        Random random = new Random();

        for (int i = 0; i < 30; i++) {
            int val = random.nextInt(100);
//            colorTree.add(val);
            colorTree.add(43);
            colorTree.add(11);
            colorTree.add(75);
            colorTree.add(2);
            colorTree.add(81);
            System.out.println(i + " val = " + val);
            colorTree.printTree(colorTree.root, null, false);
            System.out.println();

        }



    }
}
