package com.cal23;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ApplicationTest {
	
	@Test
	public void Addition() throws IOException {
		
		Calculator.setOperatorRegex();
		Calculator calculator = new Calculator();
		
		String expression = "2+2";
		calculator.getInput(expression);
		calculator.deleteParenthesis();
		String output =  calculator.calculate();
		
		assertEquals("4.0", output );
	}
		
	
	
	@Test
	public void Subtraction() throws IOException {
		
		Calculator.setOperatorRegex();
		Calculator calculator = new Calculator();
		
		String expression = "2-5";
		calculator.getInput(expression);
		calculator.deleteParenthesis();
		String output =  calculator.calculate();
		
		assertEquals("-3.0", output );
	}
	
	@Test
	public void Multiplication() throws IOException {
		
		Calculator.setOperatorRegex();
		Calculator calculator = new Calculator();
		
		String expression = "2x5";
		calculator.getInput(expression);
		calculator.deleteParenthesis();
		String output =  calculator.calculate();
		
		assertEquals("10.0", output );
	}
	
	@Test
	public void Division() throws IOException {
		
		Calculator.setOperatorRegex();
		Calculator calculator = new Calculator();
		
		String expression = "9/2";
		calculator.getInput(expression);
		calculator.deleteParenthesis();
		String output =  calculator.calculate();
		
		
		assertEquals("4.5", output );
	}
	
	@Test
	public void Multiplication_before_Addition() throws IOException {
				
		Calculator.setOperatorRegex();
		Calculator calculator = new Calculator();
		
		String expression = "(-2)+2x2";
		calculator.getInput(expression);
		calculator.deleteParenthesis();
		String output =  calculator.calculate();
		
		assertEquals("2.0", output );
	}
	
	@Test
	public void Division_before_Addition() throws IOException {
				
		Calculator.setOperatorRegex();
		Calculator calculator = new Calculator();
		
		String expression = "2+2/2";
		calculator.getInput(expression);
		calculator.deleteParenthesis();
		String output =  calculator.calculate();
	
		assertEquals("3.0", output );
	}
	
	@Test
	public void Division_Multiplication() throws IOException {
		
		Calculator.setOperatorRegex();
		Calculator calculator = new Calculator();
		
		String expression = "8/2x2";
		calculator.getInput(expression);
		calculator.deleteParenthesis();
		String output =  calculator.calculate();
	
		assertEquals("8.0", output );
	}
	
	@Test(expected = ArithmeticException.class)
	public void Division_by_zero() throws IOException {
		
		Calculator.setOperatorRegex();
		Calculator calculator = new Calculator();
		
		String expression = "8/0";
		calculator.getInput(expression);
		calculator.deleteParenthesis();
		String output =  calculator.calculate();
			
	}
	
	
}
