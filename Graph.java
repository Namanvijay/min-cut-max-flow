import java.util.*;

public class Graph {
    private Map<Integer, List<Edge>> node_to_edges;
    private int delta;

    public Graph(int V) {
        this.node_to_edges = new HashMap<>();
        
        node_to_edges.put(V-1, new ArrayList<>()); // Adding sink to the network
    }

    public void addEdge(Edge edge){
        node_to_edges.putIfAbsent(edge.getFrom(), new ArrayList<>());
        node_to_edges.get(edge.getFrom()).add(edge);// to get the other vertices that have common edge
    }

    public int numberOfVertices() {
        return node_to_edges.keySet().size();
    }

    
    public List<Integer> getAdjacentVertices(int v, int delta) {
        List<Integer> adjacent = new ArrayList<>();
        for (Edge edge : node_to_edges.get(v)) {// get all the edges which are connected

            if(edge.getResidualCapacity() > 0 && edge.getResidualCapacity() >= delta)// look only for that vertices which have 
                                                                                    // an edge with  residual capacity so that we
                                                                                    // can return back and store the capacity
             adjacent.add(edge.getTo());
        }
        return adjacent;
    }

    public Edge getEdge(int v, int w) {
        for(Edge edge : node_to_edges.get(v)) {
            if(edge.getTo() == w) return edge;
        }
        return null;
    }

   

    public void setDelta(int delta){
        this.delta = delta;
    }

    public int getDelta(){
        return delta;
    }
}
//This module emphasis basically on to get all the edges from a given vertex v and put them in the adjacentency for those
// who has the residual capacity .
// then find the edge between given adjencent vertices and hence we r able to get edge
