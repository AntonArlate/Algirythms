import java.util.Random;

public class Lesson4_test {
    public static void main(String[] args) {
        Lesson4_ColorTree colorTree = new Lesson4_ColorTree();

        Random random = new Random();

        for (int i = 0; i < 30; i++) {
            int val = random.nextInt(100);
            System.out.println(i + " val = " + val);
            colorTree.add(colorTree.root, val);
//            colorTree.add(colorTree.root, 99);
//            colorTree.add(colorTree.root,13);
//            colorTree.add(colorTree.root,5);
//            colorTree.add(colorTree.root,2);
//            colorTree.add(colorTree.root,81);

            colorTree.printTree(colorTree.root, null, false);
            System.out.println();

        }



    }
}
