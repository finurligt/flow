package mainPackage;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class railroad {

    public static void main(String[] args) {
        Graph g = Parser.parse("testfiles/rail.txt");
        fordFulkerson(g, 1, 0);
        System.out.println("the right answer");
    }

    public static void fordFulkerson(Graph graph, int s, int t) {
        List<Node> path;
        do {
            path = findPath(graph, s, t);
            List<Edgelet> edges = edgesInPath(path, graph);
            int minDelta = edges.stream().mapToInt(Edgelet::delta).sum();

            for (Edgelet e : edges) {
                e.capacity += minDelta;
            }
        } while(!path.isEmpty());
    }

    public static List<Edgelet> edgesInPath(List<Node> path, Graph g) {
        List<Edgelet> edgesInPath = new LinkedList<>();
        ArrayList<Node> arrayPath = new ArrayList<>(path);

        for(int i = 0; i < arrayPath.size()-1; i++) {
            for( Edgelet t : arrayPath.get(i).adjacent ) {
                if( t.n == arrayPath.get(i+1) ) {
                    edgesInPath.add(t);
                }
            }
        }
        return edgesInPath;
    }

    public static List<Node> findPath(Graph graph, int start, int end) {
        LinkedList<Node> path = new LinkedList<>();
        Map<Node, Node> preceding = new HashMap<>();

        HashSet<Node> visited = new HashSet<>();
        visited.add(graph.nodes.get(start));

        Queue<Node> q = new LinkedList<>();
        q.addAll(graph.nodes.values());

        while (q.peek() != null) {
            Node current = q.poll();

            for (Edgelet edgelet : current.adjacent) {
                Node neighbour = edgelet.n;

                if (!visited.contains(neighbour) && edgelet.capacity >= 0) {
                    visited.add(neighbour);
                    q.offer(neighbour);
                    preceding.put(neighbour, current);
                    if (neighbour == graph.nodes.get(end)) {
                        System.err.println("We found a fucking path");

                        while(neighbour != null) {
                            path.addFirst(neighbour);
                            neighbour = preceding.get(neighbour);
                        }
                        return path;
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
        public List<Edgelet> adjacent;

        public Node(int label) {
            this.label = label;
            this.adjacent = new LinkedList<>();
        }

        @Override
        public String toString() {
            return String.valueOf(label);
        }

    }

    public static class Edgelet {
        Node n;
        int capacity;
        int flow;

        public Edgelet(Node n, int capacity) {
            this.n = n;
            this.capacity = capacity;
            this.flow = 0;
        }

        public int delta() {
            return capacity - flow;
        }

        @Override
        public String toString() {
            return "->" + n.toString() + ", " + flow + "/" + capacity;
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
                    nodeMap.get(Integer.parseInt(split[0])).adjacent.add(new Edgelet(nodeMap.get(Integer.parseInt(split[1])), Integer.parseInt(split[2]))); //one-liner of doom
                }


            } catch (FileNotFoundException e) {
                System.err.println("Could not find input-file, exiting...");
                System.exit(1);
            }
            return new Graph(nodeMap);
        }

    }
}
