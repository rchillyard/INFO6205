package edu.neu.coe;

public class WeightedQuickUnionUF {
	public WeightedQuickUnionUF(int N) {
		count = N;
		id = new int[count];
		for(int i = 0; i < count; i++) id[i] = i;
		size = new int[count];
		for(int i = 0; i < count; i++) size[i] = 1;
	}
	
	public int count() { return count; }
	public boolean connected(int p, int q) { return find(p) == find(q); }
	
	public void union(int p, int q) {
		// TODO hint:perform a weighted union and update the size array and component count
        
        int rootP = id[p];
        int rootQ = id[q];
        if(rootP == rootQ){
            return;
        }
        
        if(size[rootP] < size[rootQ]){
            
            id[rootP] = rootQ;
            size[rootQ] += size[rootP];
        }else{
            
            id[rootQ] = rootP;
            size[rootP] += size[rootQ];
        }
        
        count--;
       
	}
	public int find(int p) {
		// TODO hint:find the root of p in the id[] array and update the return statement to return that value
        
        while(p != id[p]){
            p=id[p];
        }
        
        
        return p;
	}

	private int[] id; // id_of_parent
	private int[] size; // size of component for root
	private int count; // number of components
}
