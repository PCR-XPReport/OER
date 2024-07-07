/*
 * Project: Assignment 1 Datastructures and Algorithms Stack
 * Author:  Anonymous
 * Last Change:01.01.2024
 */

package com.da.datastructures;

import com.da.main.LinkedlistNode;

/**
 * Class that implements the data structure stack via a singly linked list.
 * Implement at least the methods push and pop.
 */
public class MyStack {


    /** Creates a new "empty" top element aka the base of the stack, which
     we identify with the char 'N'.
     */
    LinkedlistNode top=new LinkedlistNode(null,null);

    /**
     * Pushes a character onto the stack, making it the new top
     * element.
     * @param value char to be new top element
     */
    public void push(Double value){
        LinkedlistNode tmp=this.top;
        this.top = new LinkedlistNode(value, top);
    }

    /**
     * Removes the top element from the stack and
     * return it.
     * @return previous top stack element
     */
    public Double pop(){
        Double toReturn = top.getValue();
        top = top.getSuccessor();
        return toReturn;
    }

    /**
     * Generates a String representation of the stack, such that
     * the top of the stack is the leftmost element of the
     * returned string.
     * @return String representing the stack
     */
    public String toString(){

        //TODO

        String return_str= new String();
        LinkedlistNode node = top;
        //NULL ist letztes Element
        while(node.getValue()!=null){
            return_str = return_str+node.getValue()+" ";
            node = node.getSuccessor();
        }
        return_str = return_str+ "\n";
        return return_str.toString();
    }
}
