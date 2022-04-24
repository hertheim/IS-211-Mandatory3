package Dijkstra;
import java.util.*;

public class Dijkstra {
    public static Dictionary[] dijkstra (Graph g, Vertex startingVertex){
        Dictionary<String, Integer> distances = new Hashtable<>();
        Dictionary<String, Vertex> previous = new Hashtable<>();
        PriorityQueue<QueueObject> queue = new PriorityQueue<>();

        queue.add(new QueueObject(startingVertex, 0));

        for (Vertex v: g.getVertices()) {
            if(v != startingVertex){
                distances.put(v.getData(), Integer.MAX_VALUE);
            }
            previous.put(v.getData(), new Vertex("Null"));
        }

        distances.put(startingVertex.getData(), 0);

        while(queue.size() != 0){
            Vertex current = queue.poll().vertex;
            for (Edge e: current.getEdges()) {
                Integer alternate = distances.get(current.getData()) + e.getWeight();
                String neighborValue = e.getEnd().getData();
                if (alternate < distances.get(neighborValue)){
                    distances.put(neighborValue, alternate);
                    previous.put(neighborValue, current);
                    queue.add(new QueueObject(e.getEnd(), distances.get(neighborValue)));
                }
            }
        }

        return new Dictionary[]{distances, previous};
    }

    public static void shortestPathBetween(Graph g, Vertex startingVertex, Vertex targetVertex){
        Dictionary[] dijkstraDicts = dijkstra(g, startingVertex);
        Dictionary distances = dijkstraDicts[0];
        Dictionary previous = dijkstraDicts[1];
        Integer distance = (Integer) distances.get(targetVertex.getData());

        ArrayList<Vertex> path = new ArrayList<>();
        Vertex v = targetVertex;
        while(v.getData() != "Null"){
            path.add(0,v);
            v = (Vertex) previous.get(v.getData());
        }
        StringBuilder shortestPath = new StringBuilder();
        for (Vertex pathVertex: path){
             shortestPath.append(pathVertex.getData());
        }
        System.out.println("Shortest Path between " + startingVertex.getData() + " and " + targetVertex.getData());
        System.out.println("Path: " + "[" + shortestPath + "] " + "Distance: " + distance);
    }

    public static void dijkstraResultPrinter(Dictionary[] d){
        /* Shows the shortest distance between the inputted vertex and all other vertices */
        System.out.println("Distances:");
        for (Enumeration keys = d[0].keys(); keys.hasMoreElements();){
            String nextKey = keys.nextElement().toString();
            System.out.println(nextKey + ": " + d[0].get(nextKey));
        }
        /* Shows the previous visited vertex in the shortest path to all other vertices */
        System.out.println("\nPrevious:");
        for (Enumeration keys = d[1].keys(); keys.hasMoreElements();) {
            String nextKey = keys.nextElement().toString();
            Vertex nextVertex = (Vertex) d[1].get(nextKey);
            System.out.println(nextKey + ": " + nextVertex.getData());
        }
        System.out.println(" ");
    }

    public static void main(String[] args){
        Graph network = new Graph(true, false);
        Vertex a = network.addVertex("A");
        Vertex b = network.addVertex("B");
        Vertex c = network.addVertex("C");
        Vertex d = network.addVertex("D");
        Vertex e = network.addVertex("E");
        Vertex f = network.addVertex("F");

        ArrayList<Vertex> vertices = new ArrayList<>();
        vertices.add(a);
        vertices.add(b);
        vertices.add(c);
        vertices.add(d);
        vertices.add(e);
        vertices.add(f);

        network.addEdge(a, b, 10);
        network.addEdge(a, c, 5);
        network.addEdge(a, e, 3);
        network.addEdge(a, f, 12);
        network.addEdge(b, c, 17);
        network.addEdge(b, d, 9);
        network.addEdge(b, e, 17);
        network.addEdge(b, f, 12);
        network.addEdge(c, d, 35);
        network.addEdge(c, e, 3);
        network.addEdge(c, f, 12);
        network.addEdge(d, f, 12);
        network.addEdge(e, f, 12);

        for (Vertex startingVertex : vertices ) {
            for (Vertex endVertex : network.getVertices()) {
                shortestPathBetween(network, startingVertex, endVertex);
                System.out.println(" ");
            }
            System.out.println("Summary for vertex " + startingVertex.getData() + ":\n");
            dijkstraResultPrinter(dijkstra(network, startingVertex));
        }
    }
}

