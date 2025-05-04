package org.example; 
import java.util.Map;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Comparator;

//implementation of dijsktra's algorithm using the "geeks for geeks" implementation as reference


public class Dijkstra
{
    public static int shortestPath(Map<Integer, Map<Integer, Integer>> graph, int start, int end)
    {
        PriorityQueue<int[]> que = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));

        Map<Integer, Integer> distance = new HashMap<>();

        for(Integer node : graph.keySet()){
            distance.put(node, Integer.MAX_VALUE);
        }

        distance.put(start, 0);
        que.offer(new int[]{0, start});

        while(!que.isEmpty()){
            int[] current = que.poll();
            int currDist = current[0];
            int currNode = current[1];

            if(currNode == end){
                return currDist;
            }

            if(currDist > distance.get(currNode)) continue;

            Map<Integer, Integer> neighbors = graph.getOrDefault(currNode, new HashMap<>());
            for(Map.Entry<Integer, Integer> entry : neighbors.entrySet()){
                int neighbor = entry.getKey();
                int weight = entry.getValue();

                int newDist = currDist + weight;

                if(newDist < distance.getOrDefault(neighbor, Integer.MAX_VALUE)){
                    distance.put(neighbor, newDist);
                    que.offer(new int[]{newDist, neighbor});
                }
            }
        }
        return -1;
    }
}