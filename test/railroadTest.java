import mainPackage.railroad;
import org.junit.jupiter.api.Test;

import static mainPackage.railroad.*;

public class railroadTest {

    @Test
    public void shouldFindPath() {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);

        n1.adjacent.add(new Tuple(n2, 3));
        n2.adjacent.add(new Tuple(n3, 9));
        n2.adjacent.add(new Tuple(n4, 7));

        Graph g = new Graph(null);

    }
}
