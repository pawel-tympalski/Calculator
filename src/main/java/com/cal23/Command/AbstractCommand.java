package com.cal23.Command;

public class AbstractCommand implements Comparable<AbstractCommand>{
	
	int priority;
	String sign ;

	public AbstractCommand (int priority, String sign ){
		
		this.priority = priority;
		this.sign = sign;
		
	}
	
	
	 void printInfo(){
		 
		 System.out.println(sign);
		 
	 }
	public  String execute(String s1, String s2){
		 
		return null;
	 };
	 int getPriority(){
		 
		 return priority;
	 };


	public String getSign() {
		return sign;
	}


	public void setSign(String sign) {
		this.sign = sign;
	}


	public void setPriority(int priority) {
		this.priority = priority;
	}


	
	public int compareTo(AbstractCommand obj) {
		
		int rt = 1;
		
		if(priority == ((AbstractCommand) obj).getPriority()){
			
			rt = 0;
		}
		
		if(priority > ((AbstractCommand) obj).getPriority()){
					
					rt = -1;
				}
		
		if(priority < ((AbstractCommand) obj).getPriority()){
			
			rt = 1;
		}
		
		
		
		return rt;
	}
	
	

}
