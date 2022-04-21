package com.example.graphs;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * This class represents a directed graph using adjacency list representation
 * Java program to print DFS traversal from a given graph
 */
public class Graph {
    private int V;  // Number of vertices

    private LinkedList<Integer> adj[]; // Array of lists for Adjacency List Representation

    /* Constructor */
    Graph(int v) {
        V = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; ++i) {
            adj[i] = new LinkedList<>();
        }
    }

    /* Function to add an edge into the graph */
    void addEdge(int v, int w) {
        adj[v].add(w); // add w to v's list.
    }

    /* A function used by DFS */
    void DFSUtil(int v, boolean visited[], List<Integer> results) {
        visited[v] = true; // Mark the current node as visited and print it
        results.add(v);

        Iterator<Integer> i = adj[v].listIterator(); // Recur for all the vertices to this vertex
        while (i.hasNext()) {
            int n = i.next();
            if (! visited[n]) {
                DFSUtil(n, visited, results);
            }
        }
    }

    /* The function to do DFS traversal. It uses recursive DFSUtil function */
    List<Integer> DFS(int v) {
        List<Integer> results = new LinkedList<>();

        // Mark all the vertices as not visited (set as false by default in Java)
        boolean[] visited = new boolean[V];

        // Call the recursive helper function to print DFS traversal
        DFSUtil(v, visited, results);
        return results;
    }

    List<Integer> BFS(int s) {
        List<Integer> results = new LinkedList<>();

        // Mark all the vertices as not visited (set as false by default in Java)
        boolean[] visited = new boolean[V];

        // Create a queue for BFS
        LinkedList<Integer> queue = new LinkedList<>();

        // Mark the current node as visited and enqueue it
        visited[s] = true;
        queue.add(s);

        while (queue.size() != 0) {
            // Dequeue a vertex from queue and print it
            s = queue.poll();
            results.add(s);

            // Get all adjacent vertices of the dequeued vertex 's'
            // If an adjacent has not been visited, then mark it
            // visited and enqueue it
            Iterator<Integer> i = adj[s].listIterator();
            while (i.hasNext()) {
                int n = i.next();
                if (! visited[n]) {
                    visited[n] = true;
                    queue.add(n);
                }
            }
        }
        return results;
    }
}
