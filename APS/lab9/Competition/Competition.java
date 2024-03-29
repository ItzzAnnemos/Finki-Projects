package mk.ukim.finki.lab9.Competition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Competition {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bf.readLine());

        BinarySearchTree<Integer> tree = new BinarySearchTree<>();

        for (int i = 0;i < n;i++) {
            tree.insert(Integer.parseInt(bf.readLine()));
        }

        int target = Integer.parseInt(bf.readLine());

        int rank = tree.findR(target);
        System.out.println(rank);
    }
}
