package mk.ukim.finki.lab7.RoutingHashJava;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class RoutingHashJava {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        CBHT<String, List<String>> routers = new CBHT<>(N * 2);

        for (int i = 1; i <= N; i++) {
            String source = br.readLine();
            String destination = br.readLine();

            String[] parts = destination.split(",");

            List<String> list = new ArrayList<>();

            for (String part : parts) {
                String[] tmp = part.split("\\.");

                list.add(String.format("%s.%s.%s", tmp[0], tmp[1], tmp[2]));
            }

            routers.insert(source, list);
        }

        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            String source = br.readLine();
            String destination = br.readLine();

            String[] parts = destination.split("\\.");

            destination = String.format("%s.%s.%s", parts[0], parts[1], parts[2]);

            boolean flag = false;

            if (routers.search(source) != null) {
                List<String> tmp = routers.search(source).element.value;
                for (String s : tmp) {
                    if (s.equals(destination)) {
                        System.out.println("postoi");
                        flag = true;
                    }
                }

                if (!flag)
                    System.out.println("ne postoi");

            } else {
                System.out.println("ne postoi");
            }
        }
    }
}
