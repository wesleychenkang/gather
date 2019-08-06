package com.zhongdao.gather;

public class Node {
    private Object data;
    private Node leftChild;
    private Node rightChild;

    private void display(){

        System.out.println(data);
    }
    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Node getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(Node leftChild) {
        this.leftChild = leftChild;
    }

    public Node getRightChild() {
        return rightChild;
    }

    public void setRightChild(Node rightChild) {
        this.rightChild = rightChild;
    }


    public Node finde(int key){

        return  null;
    }

}
