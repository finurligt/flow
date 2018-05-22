package mainPackage;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class railroad {

    public static void main(String[] args) {
        Parser p = new Parser();

        Graph g = p.parse("testfiles/rail.txt");
        System.out.println("the right answer");
    }

    public static Graph findPath(Graph graph, Node start, Node end) {

        return new Graph();
    }

    // note to fred.
    // 852 eller 872 Sveriges ingenjörer.
    static class Graph {
        Map<String, Node> nodes;

        public Graph() {
            this.nodes = new HashMap<>();
        }
    }

    static class Node {
        int label;
        List<Node> adjacent;

        Node(int label) {
            this.label = label;
            this.adjacent = new LinkedList<>();
        }
    }

    static class Parser {
        static Graph parse(String fileName) {
            Map<Integer,Node> nodeMap = new HashMap<Integer,Node>();
            try(Scanner sc = new Scanner(new FileReader(fileName))) {
                int noNodes;
                noNodes = Integer.parseInt(sc.nextLine());
                for (int i = 0; i<noNodes;i++) {
                    nodeMap.put(i,new Node(i));
                    sc.nextLine();
                }

                //TODO: parse rest of the stuff


            } catch (FileNotFoundException e) {
                System.err.println("Could not find input-file), exiting...");
                System.exit(1);
            }
            return new Graph();
        }

    }
}
