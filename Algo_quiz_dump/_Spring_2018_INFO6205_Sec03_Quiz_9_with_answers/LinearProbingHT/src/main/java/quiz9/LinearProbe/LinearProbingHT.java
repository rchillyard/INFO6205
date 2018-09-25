package quiz9.LinearProbe;

public class LinearProbingHT<Key, Value> 
{
	  private int N;
	  private int M = 16;
	  private Key[] keys;
	  private Value[] vals;
	  
	  @SuppressWarnings("unchecked")
	  public LinearProbingHT() {
		  keys = (Key[]) new Object[M];
		  vals = (Value[]) new Object[M];
	  }
	  
	  @SuppressWarnings("unchecked")
	  public LinearProbingHT(int m) {
		  keys = (Key[]) new Object[M];
		  vals = (Value[]) new Object[M];
		  M = m;
	  }
	  
	  private int hash(Key key) {
		  return (key.hashCode() & 0x7fffffff) % M;
	  }
	  
	  private void resize(int cap) {
		  LinearProbingHT<Key, Value> t;
		  t = new LinearProbingHT<Key, Value>(cap);
		  for (int i = 0; i < M; i++) 
			  if(keys[i] !=null)
				  t.put(keys[i], vals[i]);
		  keys = t.keys;
		  vals = t.vals;
		  M = t.M;
		  
	  }
	  
	  public void put(Key key, Value val){
		  // TODO implement the put method for a linear probing hash table
		 int i;
         for(i=hash(key); keys[i]!= null; i = (i+1)%M){
            if(keys[i].equals(key))
                break;
         }
         keys[i] = key;
         vals[i] = val;
	  }
	  
	  public Value get(Key key) {
		  // TODO implement the get method for a linear probing hash table
		  for(int i = hash(key); keys[i] != null; i = (i + 1) % M){
                if(key.equals(keys[i])) return vals[i];
          }
            
		  return null;
	  }
	  
	  /*
	   * The below method is for unit test validation that the put method functions correctly
	   */
	  public Value checkValue(int i) {
		  return vals[i];
	  }
	  
}
