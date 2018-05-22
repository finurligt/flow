package mainPackage;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Parser p = new Parser();
        p.parse("namn");
        Algorithm a = new Algorithm();
        a.solve();
        System.out.println("the right answer");
    }

    static class Algorithm {
        public static void solve() {
            //TODO
        }
    }

    static class Parser {
        static void parse(String fileName) {
            try(Scanner sc = new Scanner(new FileReader(fileName))) {
                int noNodes;
                noNodes = Integer.parseInt(sc.nextLine());
                for (int i = 0; i<noNodes;i++) {
                    readNode();
                }

                //TODO: parse rest of the stuff


            } catch (FileNotFoundException e) {
                System.err.println("Could not find input-file), exiting...");
                System.exit(1);
            }
        }
        static void readNode() {
            //TODO
        }
    }
}
