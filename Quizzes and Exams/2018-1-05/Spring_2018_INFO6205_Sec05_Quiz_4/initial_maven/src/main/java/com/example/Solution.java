package com.example;

public class Solution {
    //you only need to implement the UnionFind class, if you are interested in this question, you can modify the below validTree method
    class UnionFind {
        //this is the array to store the parents' index
        int[] parents;
        public UnionFind(int n) {
            //initialize
            //please implement this part
            
           
                parents= new int[n];
            
            
        }
        //basic union operations, please implement these, you can use quick union/find or weighted union find to implement these
        public void union(int n1, int n2) {
            //please implement this part
          int n1root =find(n1);
          int n2root =find(n2);
         
                if(n1root ==n2root){
                    return;
                }else{
                    parents[n1root]=n2root;
                }
               
            }
            
        
        public int find(int node) {
            //please implement this part
            while(node!=parents[node]){
                node=parents[node];
            }
            return node;

        }
        public boolean isConnected(int n1, int n2) {
            //please implement this part
            return find(n1)==find(n2);

        }
    }

    //Use union find to check if the given edges will build a valid tree
    //already implemented to fit most cases, however you can modify this part if you think it will help you pass the tests
    UnionFind union;
    public boolean validTree(int n, int[][] edges) {
        union = new UnionFind(n);
        Boolean valid = true;
        for (int i = 0; i < n; i++)
            union.parents[i] = i;
        if (edges.length != n-1)
            valid = false;
        for (int i = 0; i < edges.length; i++) {
            if (union.find(edges[i][0]) == union.find(edges[i][1]))
                valid = false;
            else
                union.union(edges[i][0], edges[i][1]);
        }
        return valid;
    }
}

