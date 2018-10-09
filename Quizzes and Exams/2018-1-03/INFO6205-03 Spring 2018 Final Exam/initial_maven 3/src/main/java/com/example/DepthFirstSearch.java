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
        //TODO
        result = new ArrayList<>();
        
        //call the dfs method, s is the startpoint of your dfs method
        dfs(G, s);
    }
    
    private void dfs(Graph G, int v){
        //TODO
        result.add(v);
        
        for(int w: G.adj(v)){
            
            if(!result.contains(w)) {
                
                dfs(G, w);
                
            }
        }
       
    }
    
    //implement other functions you might need
    //TODO
}
