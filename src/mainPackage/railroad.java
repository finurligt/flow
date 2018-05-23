package mainPackage;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class railroad {

    final static boolean DEBUG_PRINT = true;

    public static void main(String[] args) {
        Graph g = Parser.parse(args[0]);

        System.out.println(fordFulkerson(g, 0, 54));
    }

    public static int fordFulkerson(Graph graph, int s, int t) {
        List<Node> path;
        do {
            path = findPath(graph, s, t);


            if(DEBUG_PRINT) {
                // graph print
                graph.nodes.values().forEach(System.err::println);

                // print the path
                System.err.println("path: ");
                path.forEach(node -> System.err.print("-> " + node.label));
                System.err.println();
            }


            List<Edgelet> edges = edgesInPath(path);

            OptionalInt minDelta = edges.stream().mapToInt(Edgelet::delta).min();

            if(minDelta.isPresent()) {
                if(DEBUG_PRINT) System.err.println("minDelta " + minDelta.getAsInt());
                for (Edgelet e : edges) {
                    e.flow += minDelta.getAsInt();
                }
            }
        } while(!path.isEmpty());

        return graph.nodes.get(s).adjacent.stream().mapToInt(e -> e.flow).sum();
    }

    public static List<Edgelet> edgesInPath(List<Node> path) {
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
        q.offer(graph.nodes.get(start));

        while (q.peek() != null) {
            Node current = q.poll();

            for (Edgelet edgelet : current.adjacent) {
                Node neighbour = edgelet.n;

                if (!visited.contains(neighbour) && (edgelet.capacity == -1 || edgelet.delta() > 0 )) {
                    visited.add(neighbour);
                    q.offer(neighbour);
                    preceding.put(neighbour, current);
                    if (neighbour == graph.nodes.get(end)) {

                        while(neighbour != null) {
                            path.addFirst(neighbour);
                            neighbour = preceding.get(neighbour);
                        }
                        return path;
                    }
                }
            }
        }
        return path;
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
            StringBuilder bob = new StringBuilder();
            adjacent.forEach(s -> bob.append("\t"+s.toString()));

            return String.valueOf(label) + "||" + bob.toString();
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
            if (capacity == -1) {
                return Integer.MAX_VALUE;
            } else {
                return capacity - flow;
            }
        }

        @Override
        public String toString() {
            return "->" + n.label + ", " + flow + "/" + capacity;
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
                System.err.println("Could not find input-file), exiting...");
                System.exit(1);
            }
            return new Graph(nodeMap);
        }

    }
}
