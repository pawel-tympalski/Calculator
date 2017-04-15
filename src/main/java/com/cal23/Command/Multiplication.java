package com.cal23.Command;

public class Multiplication extends AbstractCommand{

	
	public Multiplication(int priority, String sign){
		
		super(priority, sign);
		
	}
	

	@Override
	public String execute(String s2, String s3) throws ArithmeticException {
		
		double  firstNumber =  Double.parseDouble(s2);
		
		double secondNumber = Double.parseDouble(s3);
		
		double score = firstNumber * secondNumber;
		
		return  Double.toString(score);
				
	}

}
