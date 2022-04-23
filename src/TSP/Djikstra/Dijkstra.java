package TSP.Djikstra;
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
        System.out.println("Shortest Distance between " + startingVertex.getData() + " and " + targetVertex.getData());
        System.out.println(distance);

        ArrayList<Vertex> path = new ArrayList<>();
        Vertex v = targetVertex;
        while(v.getData() != "Null"){
            path.add(0,v);
            v = (Vertex) previous.get(v.getData());
        }
        System.out.println("Shortest Path");
        for (Vertex pathVertex: path){
            System.out.println(pathVertex.getData());
        }
    }

    public static void dijkstraResultPrinter(Dictionary[] d){
        System.out.println("Distances:\n");
        for (Enumeration keys = d[0].keys(); keys.hasMoreElements();){
            String nextKey = keys.nextElement().toString();
            System.out.println(nextKey + ": " + d[0].get(nextKey));
        }
        System.out.println("\nPrevious:\n");
        for (Enumeration keys = d[1].keys(); keys.hasMoreElements();) {
            String nextKey = keys.nextElement().toString();
            Vertex nextVertex = (Vertex) d[1].get(nextKey);
            System.out.println(nextKey + ": " + nextVertex.getData());
        }
    }

    public static void main(String[] args){
        Graph testGraph = new Graph(true, false);
        Vertex Oradea = testGraph.addVertex("Oradea");
        Vertex Zerind = testGraph.addVertex("Zerind");
        Vertex Arad = testGraph.addVertex("Arad");
        Vertex Timisoara = testGraph.addVertex("Timisoara");
        Vertex Lugoj = testGraph.addVertex("Lugoj");
        Vertex Mehadia = testGraph.addVertex("Mehadia");
        Vertex Drobeta = testGraph.addVertex("Drobeta");
        Vertex Craiova = testGraph.addVertex("Craiova");
        Vertex RimnicuVilcea = testGraph.addVertex("Rimnicu Vilcea");
        Vertex Sibiu = testGraph.addVertex("Sibiu");
        Vertex Fagaras = testGraph.addVertex("Fagaras");
        Vertex Pitesti = testGraph.addVertex("Pitesti");
        Vertex Bucharest = testGraph.addVertex("Bucharest");
        Vertex Giurgiu = testGraph.addVertex("Giurgiu");
        Vertex Urziceni = testGraph.addVertex("Urziceni");
        Vertex Hirsova = testGraph.addVertex("Hirsova");
        Vertex Eforie = testGraph.addVertex("Eforie");
        Vertex Vaslui = testGraph.addVertex("Vaslui");
        Vertex Iasi = testGraph.addVertex("Iasi");
        Vertex Neamt = testGraph.addVertex("Neamt");

        testGraph.addEdge(Oradea, Zerind, 71);


        dijkstraResultPrinter(dijkstra(testGraph, Oradea));
        shortestPathBetween(testGraph, Zerind, Oradea);
    }
}

