package Quiz2019;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class Quiz8 {
	
	
	 static String[] schedule(ArrayList<Activity> activities) {
	        String[] res = new String[activities.size()];
	        PriorityQueue<Activity> pq = new PriorityQueue<>();
	        Collections.sort(activities, new Comparator() {

	            @Override
	            public int compare(Object o1, Object o2) {
	                Activity a = (Activity)o1;
	                Activity b = (Activity)o2;
	                return a.starttime-b.starttime ==0? b.priority-a.priority:a.starttime-b.starttime;
	            }
	        });
	        Queue<Activity> queue = new LinkedList();
	        for(Activity a : activities)
	            queue.add(a);
	        
	        int time=0;
	        int index=0;
	        res[index++] = queue.peek().getName();
	        time = time< queue.peek().getStarttime()? queue.peek().getStarttime(): time;
	        time += queue.poll().getDuration();
	        while(!queue.isEmpty()) {
	            time = pq.isEmpty()&&time< queue.peek().getStarttime()? queue.peek().getStarttime(): time;
	            while(!queue.isEmpty()&&queue.peek().getStarttime()<=time)
	                pq.add(queue.poll());
	            res[index++]= pq.peek().getName();
	            time+=pq.poll().getDuration();
	        }
	        while(!pq.isEmpty())
	            res[index++]= pq.poll().getName();
	        return res;
	    }
	    
	    
	      static final class Activity implements Comparable{
	        String name;
	        int priority;
	        int starttime;
	        int duration;
	        Activity(String n,int p, int st, int d){
	            this.name =n;
	            this.priority=p;
	            this.starttime=st;
	            this.duration=d;
	        }
	        protected int getPriority() {
	            return priority;
	        }
	        
	        protected int getStarttime() {
	            return starttime;
	        }
	        
	        protected int getDuration() {
	            return duration;
	        }
	        
	        protected String getName() {
	            return name;
	        }
	        @Override
	        public int compareTo(Object o) {
	            Activity a = (Activity)o;
	            return a.priority-this.priority==0? this.starttime-a.starttime:a.priority-this.priority;
	        }
	        
	    }
	
		
	 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		ArrayList<Activity> list = new ArrayList<>();
		while(sc.hasNextLine()) {
			String[] values = sc.nextLine().split(",");
			list.add(new Activity(values[0], Integer.parseInt(values[1]),Integer.parseInt(values[2]),Integer.parseInt(values[3])));
		}
		String[] res = Quiz8.schedule(list);
		for(String s: res)
			System.out.println(s);
	}

}
