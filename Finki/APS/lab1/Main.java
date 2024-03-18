package mk.ukim.finki.lab1;

import java.util.Scanner;

class QuarterlySales {

    private int numOfSales;
    private int[] revenues;
    private int quarterNo;

    public QuarterlySales(int numOfSales, int[] revenues, int quarterNo) {
        this.numOfSales = numOfSales;
        this.revenues = revenues;
        this.quarterNo = quarterNo;
    }
    /*@Override
    /*public String toString() {

    }*/

    public int getRevenue() {
        int sum = 0;
        for (int i = 0; i < numOfSales; i++) {
            sum += revenues[i];
        }
        return sum;
    }
}

class SalesPerson {
    private String name;
    private QuarterlySales[] quarters;

    public SalesPerson(String name, QuarterlySales[] quarters) {
        this.name = name;
        this.quarters = quarters;
    }

    /*@Override
    public String toString() {

    }*/

    public void setName(String name) {
        this.name = name;
    }

    public void setQuarters(int index, QuarterlySales quarter) {
        this.quarters[index] = quarter;
    }

    public String getName() {
        return name;
    }

    int sumSales(SalesPerson sp) {
        int sum = 0;
        for (int i = 0; i < 4; i++) {
            sum += sp.quarters[i].getRevenue();
        }
        return sum;
    }

    int sumQuarter(int index) {
        return quarters[index].getRevenue();
    }
}

public class Main {

    public static SalesPerson salesChampion(SalesPerson[] arr) {
        int max = 0;
        int current = 0;
        int index = 0;
        int i;
        for (i = 0; i < arr.length; i++) {
            current = arr[i].sumSales(arr[i]);
            if (current > max) {
                max = current;
                index = i;
            }
        }

        return arr[index];
    }

    public static void table(SalesPerson[] arr) {
        System.out.println("SP   1   2   3   4   Total");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i].getName() + "   ");
            for (int j = 0; j < 4; j++) {
                System.out.print(arr[i].sumQuarter(j) + "   ");
            }
            int total = arr[i].sumSales(arr[i]);
            System.out.println(total);
            if (i == arr.length - 1) {
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        int num = 1;
        int n;
        Scanner input = new Scanner(System.in);
        n = input.nextInt();
        SalesPerson[] arr = new SalesPerson[n];
        for (int i = 0; i < n; i++) {
            String name = input.next();
            arr[i] = new SalesPerson(name, new QuarterlySales[4]);
            QuarterlySales[] temp = new QuarterlySales[4];
            for (int j = 0; j < 4; j++) {
                int m = input.nextInt();
                int[] array = new int[m];
                for (int l = 0; l < m; l++) {
                    array[l] = input.nextInt();
                }
                QuarterlySales tmp = new QuarterlySales(m, array, num++);
                arr[i].setQuarters(j, tmp);
            }
        }

        table(arr);
        System.out.println("SALES CHAMPION: " + salesChampion(arr).getName());

    }
}