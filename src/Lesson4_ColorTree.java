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


import java.awt.*;

public class Lesson4_ColorTree {

    Node root;


    private class Node {
        int value;
        Color color;
        Node left;
        Node right;
    }

    public boolean add(int value) {
        if (root != null) {
            boolean result = addNode(root, value);
            root = rebalance(root);
            root.color = Color.BLACK;
            return result;

        } else {
            root = new Node();
            root.color = Color.BLACK;
            root.value = value;
            return true;
        }

    }

    public boolean addNode(Node cur, int value) {

        // начинаем обход дерева


        if (value == cur.value) { // точка выхода если значение повторяется
            System.out.println("значение дубль, добавление прервано");
            return false;
        } else {
            if (value < cur.value) {
                if (cur.left == null) { // нашли место в левом потомке
                    cur.left = new Node();
                    cur.left.color = Color.RED;
                    cur.left.value = value;
                    return true;
                } else {
                    boolean result = addNode(cur.left, value); // если место занято, углубляемся
                    cur.left = rebalance(cur.left); // пока от корня попытаюсь вызвать
                    return result;
                }

            } else {
                if (cur.right == null) {
                    cur.right = new Node();
                    cur.right.color = Color.RED;
                    cur.right.value = value;
                    return true;
                } else {
                    boolean result = addNode(cur.right, value); // если место занято, углубляемся
                    cur.right = rebalance(cur.right);
                    return result;
                }
            }
        }
    }


    private Node rebalance(Node node) {

        // проверяем наличие детей (просто для удобства вызова)
        Node leftChild = node.left;
        Node rightChild = node.right;
        Node result = node;
        boolean needRebalance;

        do {
            needRebalance = false;


            if (result.right != null && result.right.color == Color.RED &&
                    (result.left == null || result.left.color == Color.BLACK)) { // если красная правая, делаем правосторонний поворот с валидным выходом (после фуллсвап не выполнится)
                needRebalance = true;
                result = rightSwap(result);
            }

            if (result.left != null &&
                    result.left.color == Color.RED &&
                    result.left.left != null &&
                    result.left.left.color == Color.RED) { // • У красной ноды все дети черного цвета
                // если красная левая, это не валид так как родитель тут красный. Выполняем левый поворрот
                // после фуллсвап не выполнится
                // после правостороннего функция завершится выходом
                needRebalance = true; // выходим с запросом на повторный ребаланс
                result = leftSwap(result);

            }

            if (result.left != null && result.left.color == Color.RED && result.right != null && result.right.color == Color.RED) { // У чёрной ноды не может быть 2 красных потомка
                // случай если оба потомка красные делаем свап
                fullSwap(result);
                // если выполнен фуллсвап, правила выше могут быть нарушены, возвращаем вверх по рекурсии флаг
                needRebalance = true;

            }
        }
        while (needRebalance); // если с нижней рекурсии пришёл true выполняем проверку ещё раз

        return result;
    }


    private Node leftSwap(Node rootNode) {
        Node temp = rootNode;
        Node leftChild = rootNode.left;
        Node betweenChild = leftChild.right;

//        rootNode = temp.left; // в рут теперь левый
//        rootNode.right = temp; // справа от левого теперь старый рут
//        rootNode.right.left = betweenChild; // Промежуточный слева от старого корня
//        rootNode.color = temp.color; // новый рут получает цвет старого
//        rootNode.right.color = Color.RED; // справа от рута обязательно красный
//        return rootNode;

        leftChild.right = rootNode;
        rootNode.left = betweenChild;
        leftChild.color = rootNode.color;
        rootNode.color = Color.RED;

        return leftChild;
    }

    private Node rightSwap(Node rootNode) {
        Node temp = rootNode;
        Node rightChild = rootNode.right;
        Node betweenChild = rightChild.left;

//        rootNode = temp.right; // в рут теперь правый
//        rootNode.left = temp; // слева от рута теперь старый рут
//        rootNode.left.right = betweenChild; // меняем промежуточного
//        rootNode.color = temp.color; // новый рут получает цвет старого
//        rootNode.left.color = Color.RED; // слева от рута обязательно красный
//
//        return rootNode;

        rightChild.left = rootNode;
        rootNode.right = betweenChild;
        rightChild.color = rootNode.color;
        rootNode.color = Color.RED;

        return rightChild;
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


    class Trunk {
        Trunk prev;
        String str;

        Trunk(Trunk prev, String str) {
            this.prev = prev;
            this.str = str;
        }
    }

    ;


    public static void showTrunks(Trunk p) {
        if (p == null) {
            return;
        }

        showTrunks(p.prev);
        System.out.print(p.str);
    }


    public void printTree(Node root, Trunk prev, boolean isLeft) {
        if (root == null) {
            return;
        }

        String prev_str = "    ";
        Trunk trunk = new Trunk(prev, prev_str);

        printTree(root.right, trunk, true);

        if (prev == null) {
            trunk.str = "———";
        } else if (isLeft) {
            trunk.str = ".———";
            prev_str = "   |";
        } else {
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

