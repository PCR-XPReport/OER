/*
 * Project: Assignment 1 Datastructures and Algorithms Stack
 * Author:  Anonymous
 * Last Change:01.01.2024
 */

package com.da.main;

public class LinkedlistNode {

    public Double value;
    public LinkedlistNode successor;


    public LinkedlistNode(Double value, LinkedlistNode successor) {
        this.value = value;
        this.successor = successor;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        value = value;
    }
    public LinkedlistNode getSuccessor() {
        return this.successor;
    }

    public void setSuccessor(LinkedlistNode successor) {
        this.successor = successor;
    }

    /*public boolean isEmpty(){
        //if (value==0){
        //
        //}
        return false;
    }*/
}
