package com.cal23.Command;

public class Subtraction extends AbstractCommand{
	
	
	public Subtraction(int priority, String sign){
		
		super(priority, sign);
		
	}
	

	@Override
	public String execute(String s2, String s3) {
		
		double  firstNumber =  Double.parseDouble(s2);
		
		double  secondNumber = Double.parseDouble(s3);
		
		double score  = firstNumber - secondNumber;
		
		return  Double.toString(score);
		
	}

}
