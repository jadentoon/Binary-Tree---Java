import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        binarySearchTree binarySearchTree = new binarySearchTree();
        int choice = scanner.nextInt();
        while (choice != 0 && choice < 9){
            if (choice == 1){
                int elementToBeAdded = scanner.nextInt();
                binarySearchTree.insert(elementToBeAdded);
                choice = scanner.nextInt();
            }
            if (choice == 2){
                int elementToBeSearched = scanner.nextInt();
                node element = binarySearchTree.search(elementToBeSearched);
                if (element != null){
                    System.out.print(element.getData()+ "(" +element.getCount()+ ") ");
                }
                else {
                    System.out.print(elementToBeSearched+ "(0) ");
                }
                choice = scanner.nextInt();
            }
            if (choice == 3){
                binarySearchTree.printMaxElement();
                choice = scanner.nextInt();
            }
            if (choice == 4){
                binarySearchTree.printMinElement();
                choice = scanner.nextInt();
            }
            if (choice == 5){
                binarySearchTree.preOrderTraversal();
                choice = scanner.nextInt();
            }
            if (choice == 6){
                binarySearchTree.postOrderTraversal();
                choice = scanner.nextInt();
            }
            if (choice == 7){
                binarySearchTree.inOrderTraversal();
                choice = scanner.nextInt();
            }
            if (choice == 8){
                int elementToBeDeleted = scanner.nextInt();
                binarySearchTree.delete(elementToBeDeleted);
                binarySearchTree.preOrderTraversal();
                System.out.println();
                choice = scanner.nextInt();
            }
        }
    }
}

class binarySearchTree {

    private node root;

    public binarySearchTree(){
        root = null;
    }

    public void insert (int data){
        root = insertRecursive(root, data);
    }

    private node insertRecursive (node current, int data){
        if (current == null){
            return new node(data);
        }
        if (data < current.getData()) {
            current.setLeft(insertRecursive(current.getLeft(), data));
        }
        else if (data > current.getData()){
            current.setRight(insertRecursive(current.getRight(), data));
        }
        else{
            current.addCount();
        }
        return current;
    }

    public node search (int data){
        return searchRecursive(root, data);
    }

    private node searchRecursive(node current, int data){
        if (current == null || current.getData() == data){
            return current;
        }
        if (data < current.getData()){
            return searchRecursive(current.getLeft(), data);
        }
        return searchRecursive(current.getRight(), data);
    }

    public void printMaxElement (){
        if (root == null){
            System.out.print("0(0)");
        }
        else{
            node maxElement = maxElement(root);
            System.out.print(maxElement.getData() + "(" + maxElement.getCount() + ") ");
        }
    }

    private node maxElement (node current) {
        while (current.getRight() != null){
            current = current.getRight();
        }
        return current;
    }

    public void printMinElement (){
        if (root == null){
            System.out.print("0(0)");
        }
        else{
            node minElement = minElement(root);
            System.out.print(minElement.getData() + "(" + minElement.getCount() + ") ");
        }
    }

    private node minElement (node current) {
        while (current.getLeft() != null){
            current = current.getLeft();
        }
        return current;
    }

    public void preOrderTraversal (){
        printPreOrder(root);
    }

    public void printPreOrder(node current){
        if (current == null){
            return;
        }
        System.out.print(current.getData() + "(" + current.getCount() + ") ");

        printPreOrder(current.getLeft());

        printPreOrder(current.getRight());
    }

    public void inOrderTraversal(){
        printInOrder(root);
    }

    public void printInOrder (node current){
        if (current == null){
            return;
        }
        printInOrder(current.getLeft());

        System.out.print(current.getData()+ "(" + current.getCount() + ") ");

        printInOrder(current.getRight());
    }

    public void postOrderTraversal(){
        printPostOrder(root);
    }

    public void printPostOrder (node current){
        if (current == null){
            return;
        }
        printPostOrder(current.getLeft());

        printPostOrder(current.getRight());

        System.out.print(current.getData()+ "(" + current.getCount() + ") ");
    }

    public void delete(int data){
        root = deleteRecursive(root, data);
    }

    private node deleteRecursive(node current, int data){
        if (current == null){
            return null;
        }
        if (data < current.getData()){
            current.setLeft(deleteRecursive(current.getLeft(), data));
        }
        else if (data > current.getData()){
            current.setRight(deleteRecursive(current.getRight(), data));
        }
        else{
            if (current.getCount() > 1){
                current.takeCount();
            }
            else{
                if (current.getLeft() == null){
                    return current.getRight();
                }
                else if (current.getRight() == null){
                    return current.getLeft();
                }else {
                    current.setData(minElement(current.getLeft()).getData());

                    current.setLeft(deleteRecursive(current.getLeft(), current.getData()));
                }
            }
        }
        return current;
    }

}

class node {
    private int data, count;
    private node left, right;

    public node (int data){
        this.data = data;
        count = 1;
        left = right = null;
    }

    public void setLeft (node l){
        left = l;
    }

    public int getCount(){
        return count;
    }

    public void addCount(){
        count++;
    }

    public void takeCount(){
        count--;
    }

    public node getLeft(){
        return left;
    }

    public void setRight (node r){
        right = r;
    }

    public node getRight(){
        return right;
    }

    public int getData(){
        return data;
    }

    public void setData (int data) {
        this.data = data;
    }
}