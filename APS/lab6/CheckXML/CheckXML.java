package mk.ukim.finki.lab6.CheckXML;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;
import java.util.Stack;

public class CheckXML {
    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int n = Integer.parseInt(s);
        String [] redovi = new String[n];

        for(int i=0;i<n;i++)
            redovi[i] = br.readLine();

        int valid = 1;
        Stack<String> stack = new Stack<>();

        for (int i = 0;i < n;i++) {
            if (!redovi[i].contains("/") && redovi[i].contains("[") && redovi[i].contains("]")) {
                stack.push(redovi[i]);
            } else if (redovi[i].contains("/") && redovi[i].contains("[") && redovi[i].contains("]")){
                String tmp = stack.pop();
                if (!(tmp.substring(1,tmp.length() - 1).equals(redovi[i].substring(2, redovi[i].length() - 1)))) {
                    valid = 0;
                    break;
                }
            }
        }

        System.out.println(valid);

        br.close();
    }
}
