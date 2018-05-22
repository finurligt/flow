package mainPackage;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class railroad {

    public static void main(String[] args) {
        Parser p = new Parser();
        Parser.parse("namn");
        Algorithm a = new Algorithm();
        a.solve();
        System.out.println("the right answer");
    }

    static class Algorithm {
        public static void solve() {
            //TODO
        }
    }

    // note to fred.
    // 852 eller 872 Sveriges ingenjörer.
    static class Graph {
        Set<Node> nodeSet;
        Set<Edge> edgeSet;

        public Graph() {
            this.nodeSet = new HashSet<>();
            this.edgeSet = new HashSet<>();
        }

        private class Edge {
            Node start;
            Node end;
            Edge(Node start, Node end) {
                this.start = start;
                this.end = end;
            }
        }

        private class Node {
            String label;

            Node(String label) {
                this.label = label;
            }
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
