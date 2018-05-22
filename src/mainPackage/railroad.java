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
        path.add( graph.nodes.get(start) );

        HashSet<Node> visited = new HashSet<>( graph.nodes.values() );
        visited.remove( graph.nodes.get(start) );

        Queue<Node> q = new LinkedList<>();
        q.addAll(visited);

        while(!q.isEmpty()) {
            Node current = q.poll();

            for (Node neighbour : current.adjacent) {
                if(!visited.contains(neighbour)) {
                    visited.remove(neighbour);
                    q.offer(neighbour);
                    //path.add(neighbour); nog inte right.
                    if(neighbour == graph.nodes.get(end)) {
                        return path;
                    }
                }
            }
        }
        return new Graph(new HashMap<>());
    }

    // note to fred.
    // 852 eller 872 Sveriges ingenjörer.
    static class Graph {
        Map<Integer, Node> nodes;

        public Graph(Map<Integer,Node> mappen) {
            this.nodes = mappen;
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
            return new Graph(nodeMap);
        }

    }
}
