package com.example;

import java.util.*;

public class DepthFirstSearch {
    //this is the array you need to store your visited vertex in order, the unit test will test this
    private ArrayList<Integer> result;
    //Do not edit this part
    public ArrayList<Integer> getResult(){
        return result;
    }
    //Write down your DFS code below
    public DepthFirstSearch(Graph G, int s) {
        //some initialization
        result = new ArrayList<Integer>();
        //call the dfs method, s is the startpoint of your dfs method
        dfs(G, s);
    }
    
    private void dfs(Graph G, int v){
        //TODO
        
        result.add(v);
        for(int x : G.adj(v)){
            
            if(!result.contains(x)){   
            dfs(G,x);
            }            
        }

        
    }
    
}
