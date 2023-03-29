/*
”слови€ дерева:
Х  ажда€ нода имеет цвет (красный или черный)
Х  орень дерева всегда черный
Х Ќова€ нода всегда красна€
Х  расные ноды могут быть только левым ребенком
Х ” краной ноды все дети черного цвета

ѕодсказка к выполнению:
—оответственно, чтобы данные услови€ выполн€лись, после добавлени€ элемента в дерево необходимо произвести балансировку,
благодар€ которой все критерии выше станут валидными.
ƒл€ балансировки существует 3 операции Ц левый малый поворот, правый малый поворот и смена цвета.
 */


public class Lesson4_ColorTree {

    public static void main(String[] args) {

    }

    Node root;

    private class Node {
        int value;
        Color color;
        Node left;
        Node right;
    }

    public void add(int value) {
        Node node = new Node();
        node.value = value;

        if (root == null) {
            root = node;
        } else {
            Node cur = root;
            while (cur != null) {
                if (value < cur.value) {
                    if (cur.left == null) {
                        cur.left = node;
                        break;
                    }
                    cur = cur.left;
                } else {
                    if (cur.right == null) {
                        cur.right = node;
                        break;
                    }
                    cur = cur.right;
                }
            }
        }
    }

    public boolean find(int value) {
        if (root != null) {
            Node cur = root;
            while (cur != null) {
                if (cur.value == value)
                    return true;
                if (value < cur.value) {
                    cur = cur.left;
                } else {
                    cur = cur.right;
                }
            }
        }
        return false;
    }

    private enum Color {
        RED, BLACK
    }
}

