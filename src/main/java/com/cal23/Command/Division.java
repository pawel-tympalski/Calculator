package com.cal23.Command;

public class Division extends AbstractCommand {
	
	public Division(int priority, String sign){
		
		super(priority, sign);
		
	}
	
	@Override
	public String execute(String s2, String s3) throws ArithmeticException {
		
		double  firstNumber =  Double.parseDouble(s2);
		
		double  secondNumber = Double.parseDouble(s3);
		
		if(secondNumber == 0){
			throw new ArithmeticException();
		}
		
		double score  = firstNumber / secondNumber;
		
		return  Double.toString(score);
		
		
	}

}
