package mainPackage;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class railroad {

    public static void main(String[] args) {
        Parser p = new Parser();

        Graph g = p.parse("testfiles/rail.txt");
        System.out.println("the right answer");
    }

    public static Graph findPath(Graph graph, Node start, Node end) throws Exception {
        if(graph.nodeSet.stream().anyMatch(node -> node.label.equals(start) || node.label.equals(end))) {
            throw new Exception("Could not find start or end node in the graph.");
        }

    }

    // note to fred.
    // 852 eller 872 Sveriges ingenjörer.
    static class Graph {
        Set<Node> nodeSet;

        public Graph() {
            this.nodeSet = new HashSet<>();
        }
    }

    private class Node {
        String label;
        Set<Node> adjacent;

        Node(String label) {
            this.label = label;
            this.adjacent = new HashSet<>();
        }
    }

    static class Parser {
        static Graph parse(String fileName) {
            Set<Node> nodeSet = new HashSet<Node>();
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
