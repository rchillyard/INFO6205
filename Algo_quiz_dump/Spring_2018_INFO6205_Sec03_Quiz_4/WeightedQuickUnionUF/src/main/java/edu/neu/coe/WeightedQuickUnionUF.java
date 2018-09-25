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
		// TODO determine if p and q are connected
		// TODO if p and q are not connected perform a weighted union and update the size array and component count
        
        int i = find(p);
        int j = find(q);
        if(i == j) return;
        if(size[i]<size[j]){
            id[i] = j;
            size[j] += size[i];
        }else{
            id[j] = i;
            size[i] += size[j];
        }
        count--;
	}
	public int find(int p) {
		// TODO find the root of p in the id[] array and update the return statement to return that value
        
        while(p != id[p]){
            id[p]=id[id[p]];
            p = id[p];
        }
		return p;
	}

	private int[] id;
	private int[] size; // size of component for root
	private int count; // number of components
}
