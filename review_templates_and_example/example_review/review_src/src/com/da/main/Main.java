/*
 * Project: Assignment 1 Datastructures and Algorithms Stack
 * Author:  Anonymous
 * Last Change:01.01.2024
 */

package com.da.main;

import com.da.datastructures.MyStack;
import com.da.util.IOHandler;
import javax.accessibility.AccessibleRelation;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class that tests the <code>MyStack</code> implementation on its basic methods, i.e. <code>push</code>,
 * <code>pop</code> and <code>print</code>, or translates a given infix expression to postfix.
 */
public class Main {

    /**
     * Entry point into the implementation that either calls to test the <code>MyStack</code> implementation or to
     * translates a given infix expression to postfix.
     * @param args the path to the input file as args[0]
     */
    public static void main(String[] args) {

        //check if necessary test case input file is provided
        if (args.length != 1) {
            System.err.println("Error: Please specify an input file");
            System.exit(-1);
        }

        // read input
        IOHandler ioHandler = new IOHandler();
        List<String> input = ioHandler.readInputFile(args[0]);
        System.out.println("Input File: " + args[0]);

        // create stack instance and result
        MyStack myStack = new MyStack();
        StringBuilder result = new StringBuilder();

        try {
            parseTestcaseContent(input, myStack, result);
        } catch (IllegalArgumentException | ArithmeticException runtimeException) {
            runtimeException.printStackTrace();
            result.append(runtimeException.getMessage());
        } finally {
            System.out.println("Result: " + result);
            ioHandler.printResultToOutputFile(result.toString(), args[0]);
        }
    }

    /**
     * Parses the test case to determine whether to test the stack or to evaluate a postfix expression.
     * @param input the test case as a list with each element representing one line of the input test file
     * @param myStack the instance of the stack class
     * @param result the result of the test case execution to be preformed
     */
    private static void parseTestcaseContent(List<String> input, MyStack myStack, StringBuilder result) {
        if (input.get(0).equals("stack")) {
            //stack testcase
            parseStackTestCase(input, myStack, result);
        } else {
            //evaluate postfix expression testcase
            Double postfixResult = evaluatePostfix(input.get(0), myStack);
            result.append(postfixResult);
        }
    }

    /** Parses the stack test case line by line, calls the corresponding <code>MyStack</code> method and handles the
     * output.
     * @param input the stack test case as a list with each element representing one line of the input test file
     * @param myStack the instance of the stack class
     * @param result the result of the test case execution
     */
    private static void parseStackTestCase(List<String> input, MyStack myStack, StringBuilder result) {
        for (int i = 1; i < input.size(); i++) {
            //get character between parentheses
            String line = input.get(i);
            String strWithinBrackets = "";
            Matcher characterMatcher = Pattern.compile("\\((-?\\d*)\\)").matcher(line);
            while (characterMatcher.find()) {
                strWithinBrackets = characterMatcher.group(1);
            }

            //parse method name and call corresponding MyStack method
            if (line.contains("push") && !strWithinBrackets.isEmpty()) {
                myStack.push(Double.parseDouble(strWithinBrackets));
            } else if (line.contains("pop")) {
                myStack.pop();
            } else if (line.contains("print")) {
                result.append(myStack.toString());
            } else {
                throw new IllegalArgumentException("Unknown stack method " + line + ".");
            }
        }
    }


    /**
     * Evaluates a given postfix expression passed as a String using a stack instance of <code>MyStack</code>.
     *
     * @param input the postfix expression
     * @param stack the stack instance
     * @return the result of the evaluation
     */
    public static Double evaluatePostfix(String input, MyStack stack) {
        //iterate over characters of line
        for (int i = 0; i < input.length(); i++) {



            
            char x = input.charAt(i);
            if (x >= 48 && x <= 57) {
                //push digit value onto stack
                stack.push((double) Character.getNumericValue(x));
            } else {
                    Double firstOp = stack.pop();
                    Double secondOp = stack.pop();
                    switch (x) {
                        case '+':
                            stack.push(firstOp + secondOp);
                            break;
                        case '-':
                            stack.push(secondOp - firstOp);
                            break;
                        case '/':
                            if (secondOp == 0)
                                System.exit(0);
                            stack.push(firstOp / secondOp);
                            break;
                        case '*':
                            stack.push(firstOp * secondOp);
                            break;
                    }
            }
        }
        return stack.pop();
    }
}
