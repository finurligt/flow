import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static mainPackage.railroad.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class railroadTest {

    Graph g;
    @BeforeEach
    void setUp() {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);

        n1.adjacent.add(new Edgelet(n2, 3));
        n2.adjacent.add(new Edgelet(n3, 9));
        n2.adjacent.add(new Edgelet(n4, 7));

        Map<Integer, Node> mappen = new HashMap<>();
        mappen.put(1, n1);
        mappen.put(2, n2);
        mappen.put(3, n3);
        mappen.put(4, n4);

        g = new Graph(mappen);
    }

    @Test
    void shouldFindPath() {
        List<Node> path = findPath(g,1,3);
        assertEquals(1,path.get(0).label);
        assertEquals(2,path.get(1).label);
        assertEquals(3,path.get(2).label);
    }

    @Test
    void shouldBeCorrectEdge() {
        List<Edgelet> edges = edgesInPath(findPath(g, 1, 3));
        edges.forEach(System.err::println);
    }
}
