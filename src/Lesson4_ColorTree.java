/*
Условия дерева:
• Каждая нода имеет цвет (красный или черный)
• Корень дерева всегда черный
• Новая нода всегда красная
• Красные ноды могут быть только левым ребенком
• У краной ноды все дети черного цвета

Подсказка к выполнению:
Соответственно, чтобы данные условия выполнялись, после добавления элемента в дерево необходимо произвести балансировку,
благодаря которой все критерии выше станут валидными.
Для балансировки существует 3 операции – левый малый поворот, правый малый поворот и смена цвета.
 */




public class Lesson4_ColorTree {

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
        node.color = Color.RED;


        if (root == null) {
            root = node;
            // добавляем цвет корню
            root.color = Color.BLACK;
        } else {
            // начинаем обход дерева
            Node cur = root;
            while (cur != null) {

                if (value == cur.value) { // точка выхода если значение повторяется
                    System.out.println("значение дубль, добавление прервано");
                    break;
                }

                if (value < cur.value) {
                    if (cur.left == null) { // нашли место в левом потомке
                        cur.left = node;
                        rebalance(cur);
                        break;
                    }
                    cur = cur.left; // углубляемся если есть куда
                } else {
                    if (cur.right == null) { // нашли место в правом потомке
                        cur.right = node;
                        rebalance(cur);
                        break;
                    }
                    cur = cur.right; // углубляемся если есть куда
                }
            }
        }
    }


    private boolean rebalance(Node node) {

        // проверяем наличие детей (просто для удобства вызова)
        Node leftChild = node.left;
        Node rightChild = node.right;
        boolean needRebalance;

        do {
            needRebalance = false;

            if (node.color == Color.BLACK && leftChild != null && rightChild != null) { // У чёрной ноды не может быть 2 красных потомка
                if (leftChild.color == Color.RED && rightChild.color == Color.RED) { // случай если оба потомка красные делаем свап
                    fullSwap(node);
                    // если выполнен фуллсвап, правила выше могут быть нарушены, возвращаем вверх по рекурсии флаг
                    return true;
                }
            }

            if (rightChild != null && node.right.color == Color.RED) { // если красная правая, делаем правосторонний поворот с валидным выходом (после фуллсвап не выполнится)
                needRebalance = rebalance(rightSwap(node).left);
            }

            if (node.color == Color.RED) { // • У красной ноды все дети черного цвета
                if (leftChild != null && leftChild.color == Color.RED) { // если красная левая, это не валид так как родитель тут красный. Выполняем левый поворрот
                    // после фуллсвап не выполнится
                    // после правостороннего функция завершится выходом
                    rebalance(leftSwap(node).right);
                    return true; // выходим с запросом на повторный ребаланс
                }
            }
        }
        while (needRebalance); // если с нижней рекурсии пришёл true выполняем проверку ещё раз

        return false;
    }


    private Node leftSwap(Node rootNode) {
        Node temp = rootNode;
        Node leftChild = rootNode.left;
        Node betweenChild = leftChild.right;

        rootNode = temp.left; // в рут теперь левый
        rootNode.right = temp; // справа от рута теперь старый рут
        rootNode.right.color = Color.RED; // справа от рута обязательно красный
        rootNode.right.left = betweenChild; // меняем промежуточного
        rootNode.color = temp.color; // новый рут получает цвет старого

        return rootNode;
    }

    private Node rightSwap(Node rootNode) {
        Node temp = rootNode;
        Node rightChild = rootNode.right;
        Node betweenChild = rightChild.left;

        rootNode = temp.right; // в рут теперь правый
        rootNode.left = temp; // слева от рута теперь старый рут
        rootNode.left.color = Color.RED; // слева от рута обязательно красный
        rootNode.left.right = betweenChild; // меняем промежуточного
        rootNode.color = temp.color; // новый рут получает цвет старого

        return rootNode;
    }

    private void fullSwap(Node rootNode) {
        rootNode.color = Color.RED;
        rootNode.left.color = Color.BLACK;
        rootNode.right.color = Color.BLACK;
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



    class Trunk
    {
        Trunk prev;
        String str;

        Trunk(Trunk prev, String str)
        {
            this.prev = prev;
            this.str = str;
        }
    };


    public static void showTrunks(Trunk p)
    {
        if (p == null) {
            return;
        }

        showTrunks(p.prev);
        System.out.print(p.str);
    }



    public void printTree(Node root, Trunk prev, boolean isLeft)
    {
        if (root == null) {
            return;
        }

        String prev_str = "    ";
        Trunk trunk = new Trunk(prev, prev_str);

        printTree(root.right, trunk, true);

        if (prev == null) {
            trunk.str = "———";
        }
        else if (isLeft) {
            trunk.str = ".———";
            prev_str = "   |";
        }
        else {
            trunk.str = "`———";
            prev.str = prev_str;
        }

        showTrunks(trunk);
        System.out.println(" " + root.value + " " + root.color);

        if (prev != null) {
            prev.str = prev_str;
        }
        trunk.str = "   |";

        printTree(root.left, trunk, false);
    }
}

