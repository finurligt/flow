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

    public static List<Node> findPath(Graph graph, int start, int end) {
        List<Node> path = new LinkedList<>();
        path.add(graph.nodes.get(start));

        HashSet<Node> visited = new HashSet<>(graph.nodes.values());
        visited.remove(graph.nodes.get(start));

        Queue<Node> q = new LinkedList<>();
        q.addAll(visited);

        while (!q.isEmpty()) {
            Node current = q.poll();

            for (Tuple tuple : current.adjacent) {
                Node neighbour = tuple.n;

                if (!visited.contains(neighbour)) {
                    visited.remove(neighbour);
                    q.offer(neighbour);
                    //path.add(neighbour); nog inte right.
                    if (neighbour == graph.nodes.get(end)) {
                        System.out.println("We found a fucking path");
                        return null;
                    }
                }
            }
        }
        return null;
    }

    // note to fred.
    // 852 eller 872 Sveriges ingenjörer.
    public static class Graph {
        Map<Integer, Node> nodes;

        public Graph(Map<Integer, Node> mappen) {
            this.nodes = mappen;
        }
    }

    public static class Node {
        public int label;
        public List<Tuple> adjacent;

        public Node(int label) {
            this.label = label;
            this.adjacent = new LinkedList<>();
        }
    }

    public static class Tuple {
        Node n;
        int cost;

        public Tuple(Node n, int cost) {
            this.n = n;
            this.cost = cost;
        }
    }

    static class Parser {
        static Graph parse(String fileName) {
            Map<Integer, Node> nodeMap = new HashMap<Integer, Node>();
            try (Scanner sc = new Scanner(new FileReader(fileName))) {
                int noNodes;
                noNodes = Integer.parseInt(sc.nextLine());
                for (int i = 0; i < noNodes; i++) {
                    nodeMap.put(i, new Node(i));
                    sc.nextLine();
                }

                int noEdges = Integer.parseInt(sc.nextLine());

                for (int i = 0; i < noEdges; i++) {
                    String[] split = sc.nextLine().split(" ");
                    nodeMap.get(Integer.parseInt(split[0])).adjacent.add(new Tuple(nodeMap.get(Integer.parseInt(split[1])), Integer.parseInt(split[2]))); //one-liner of doom
                }


            } catch (FileNotFoundException e) {
                System.err.println("Could not find input-file), exiting...");
                System.exit(1);
            }
            return new Graph(nodeMap);
        }

    }
}
