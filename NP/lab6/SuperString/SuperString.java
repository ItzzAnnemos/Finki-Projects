package mk.ukim.finki.NP.lab6.SuperString;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Stack;

public class SuperString {
    private LinkedList<String> list;
    private Stack<String> stack;

    public SuperString() {
        this.list = new LinkedList<>();
        this.stack = new Stack<>();
    }

    public void append(String s) {
        list.addLast(s);
        stack.push(s);
    }

    public void insert(String s) {
        list.addFirst(s);
        stack.push(s);
    }

    public boolean contains(String s) {
        String full = "";
        for (String string : list) {
            full += string;
        }

        return full.contains(s);
    }

    public void reverse() {
        for (int i = 0;i < list.size();i++) {
            StringBuilder string = new StringBuilder(list.get(i));
            list.set(i, string.reverse().toString());
        }

        Stack<String> tempStack = new Stack<>();

        while (!stack.isEmpty()) {
            String str = stack.pop();
            StringBuilder reversedString = new StringBuilder(str).reverse();
            tempStack.push(reversedString.toString());
        }

        while (!tempStack.isEmpty()) {
            stack.push(tempStack.pop());
        }

        Collections.reverse(list);
    }

    @Override
    public String toString() {
        String ret = "";
        for (int i = 0;i < list.size();i++) {
            ret += list.get(i);
        }

        return ret;
    }

    public void removeLast(int k) {
        if (k < list.size()) {
            for (int i = 0;i < k;i++) {
                String s = stack.pop();
                list.removeFirstOccurrence(s);
            }
        }
    }
}
