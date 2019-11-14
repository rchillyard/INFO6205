package Quiz2019;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class Quiz9 {
	
	  static class MySystem{
		private  Directory root =new Directory("root");
		
		private HashMap<String,File> map = new HashMap<>();
		
		MySystem(){
			map.put("root", root);
		}
		
		protected boolean isValid(String path) {
			return map.containsKey(path);
		}
		
		protected void creat(String path, String type) throws Exception{
			
			int index = path.length();
			while(index>0) {
				if(path.charAt(--index)=='/')
					break;
			}
			String s = path.substring(0, index);
			
			//Implemente
			if(this.isValid(s)) {
				Directory d = (Directory) map.get(s);
				if(type.equals("dir")) {
					File f = new Directory(path);
					d.list.add(f);
					map.put(path, f);
				}
				else {
					File f = new File(path);
					d.list.add(f);
					map.put(path, f);
				}
			}
			
		}
	  
		protected void delete(String path) throws Exception{
			//Implemente       
			if(map.containsKey(path)) {
				int index = path.length();
				while(index>0) {
					if(path.charAt(--index)=='/')
						break;
				}
				String s = path.substring(0, index);
				((Directory)map.get(s)).list.remove(path);
			}
		}
	
		protected void print(){
			//Implemente
			Queue<File> queue = new LinkedList<>();
			queue.add(root);
			Queue<File> tmp = new LinkedList<>();
			while(!queue.isEmpty()) {
				if(queue.peek() instanceof Directory) {
					System.out.print(queue.peek());
					tmp.addAll(((Directory)queue.poll()).list);
				}else
					System.out.print(queue.poll());
				if(!queue.isEmpty())
					System.out.print(",");
				else {
					System.out.println();
					queue=tmp;
					tmp = new LinkedList<File>();
				}	
			}
		} 
	}
		    
	  //Class Directory and class File
	static class Directory extends File {
		Directory(String s){
			super(s);
		}
		
		PriorityQueue list = new PriorityQueue<File>();
		
		
	}
		    
	static class File implements Comparable{
        String path;
        File(String p){
        	this.path=p;
        }
        @Override
        public String toString() {
        	return path;
        }
        @Override
        public int compareTo(Object o) {
            File f =(File)o;
            return this.path.compareTo(f.path);
        }
    }
	
		    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MySystem ms = new MySystem();
		Scanner sc = new Scanner(System.in);
		while(sc.hasNextLine()) {
			String line = sc.nextLine();
			String[] arr = line.split(",");
			try {
				if(arr[0].equals("creation")) {
					ms.creat(arr[1],arr[2]);
				}else 
					ms.delete(arr[1]);
			}
			catch (Exception e) {
				break;
			}
		}
		ms.print();
	}

}
