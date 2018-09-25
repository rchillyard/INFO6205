package com.example;

//Remember not to change anything. Just implement the TODOs
public class DijkstraStack<Item>{
	
	public DijkstraStack(int size) {
		 grow((Item[]) new Object[0],size);
	 }
	    
	 public void push(Item element) {
		 //TODO:: Push an element into the stack. you will have to take care and grow the stack when its full.
         if (this.isFull()) {
             this.grow(this.arr, size * 2);
             this.size *= 2;    
         }
         this.arr[index++] = element;
	 }

	 public Item pop(){
		 //TODO:: Pop an element from the stack and return it. Pop always happens from the top. Remember LIFO.
         if (this.isEmpty()) {
             return null;
         }
         return this.arr[--index];
	 }
	 
	 public Item peek(){
		//TODO:: Peek allows you to "peek" at the top element of the stack. Peek will not return the element (that is, it will not remove it from the stack) but merely allows you to see whats on top.
         if (this.isEmpty()) {
             return null;
         }
         return this.arr[index - 1];
	 }

    //Checks whether the stack is empty
	 public boolean isEmpty() {
		 return index == 0;
	 }
    
     //Checks whether the stack is full.
	 public boolean isFull() {
		 return index == size;
	 }

	//Returns the size of the stack. Remember, size of the stack doesn't mean the number of elements in it. 
    public int size() {
		 return size;
	 }
	 
     //The method which evaluates the expression.
	 public static void evaluate(DijkstraStack<String> operators,DijkstraStack<Integer> values,String expr){
		 String[] tokens = expr.split(" ");
				 
		 for(String token : tokens){
			 switch(token){
			 case "(": break;
			 case "+":
			 case "-":
			 case "*":
			 case "/":
				 operators.push(token);
				 break;
			 case ")":
				 String operator = operators.pop();
				 Integer val = values.pop();
							
				 switch(operator){
				 case "+": val = values.pop() + val;break;
				 case "-": val = values.pop() - val;break;
				 case "*": val = values.pop() * val;break;
				 case "/": val = values.pop() / val;break;
				 }
				 values.push(val);
				 break;
			 default:
				 Integer num = Integer.parseInt(token);
				 values.push(num);
				 break;	
			 }
		 }
	 }
	 
     //The below two functions Grow the stack when its full.
	 private void grow(Item[] source, int size){
		 this.size = size;
		 arr = growTheArray(source, size); 
	 }
	    
	 private static<Item> Item[] growTheArray(Item[] source, int size){
		 Item[] newArray = (Item[]) new Object[size];
		 System.arraycopy(source, 0, newArray, 0, source.length);
		 return newArray;
	 }
	 private Item arr[];
	 private int size;
	 private int index = 0;
	 
	 public static void main(String[] args){
		 DijkstraStack<String> operators = new DijkstraStack<>(10);
		 DijkstraStack<Integer> values = new DijkstraStack<>(10);
		 
		 // Note that the expression is split based on the spaces between operands and operators.
		 String expr =  "( 1 + ( ( 2 + 3 ) * ( 4 * 5 ) ) )";
		 
		 DijkstraStack.evaluate(operators, values, expr);
		 
		 if(values.peek() == 101) System.out.println("result is = "+values.pop());
		 
	 }
}    
