import java.util.*;
import java.io.*;

public class FileReader {
    public static Graph createNetwork(String filename) {
        try {

            Scanner sc = new Scanner(new File(filename));//Reading a txt file in java by scanner class
                                                         // sc is the object
            int V = sc.nextInt();

            List <Integer> cap= new ArrayList<>();

            
            String empty = sc.nextLine();
            for(int i = 0; i <= V-1; i++) {
                String line = sc.nextLine();
            }

            int E = Integer.parseInt(sc.nextLine());

            Graph network = new Graph(V);// We r basically adding the sink to the network

            int delta = 0;

            // add edges to network
            for(int j = 0; j < E; j++) {
                String[] edge = sc.nextLine().split(" ");
                int v = Integer.parseInt(edge[0]);
                int w = Integer.parseInt(edge[1]);
                int c = Integer.parseInt(edge[2]);
                if(c == -1) c = Integer.MAX_VALUE;   // There is no capacity for source i.e it signifies the source 

                if (v == 0) cap.add(w);
                if (cap.contains(v) && c > delta) delta = c;// Updating the delta value everytime if the vertex is 
                                                                      // already present the list 

                Edge forwardEdge  = new Edge(v, w, c, false);
                Edge backwardEdge = new Edge(w, v, c, true);

                forwardEdge.addPartnerEdge(backwardEdge);// for residual graph we require partner edge to ensure wheather there exist a 
                                                         // residual graph or not
                backwardEdge.addPartnerEdge(forwardEdge);
                network.addEdge(forwardEdge);
                network.addEdge(backwardEdge);
            }

            network.setDelta(delta);
            return network;

        } catch(IOException e) {
            System.out.println("File not found.");
        }

        return null;
    }
}