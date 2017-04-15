package com.cal23;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

import java.util.Iterator;

import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.cal23.Command.AbstractCommand;
import com.cal23.Command.Addition;

import com.cal23.Command.Division;
import com.cal23.Command.Multiplication;
import com.cal23.Command.Subtraction;

public class Calculator {
	private static String operatorRegex;
	private ArrayList<String> inputList = new ArrayList<String>();
	private ArrayList<String> listWithoutParentheses = new ArrayList<String>();
	
	private static Map<String, AbstractCommand> map = new TreeMap<String, AbstractCommand>();
	static{
		map.put("+",new Addition(20,"+"));
		map.put("/", new Division(40,"/"));
		map.put("-", new Subtraction(20,"-"));
		map.put("x", new Multiplication(40, "x" ));
	}
	
	


	public static void main(String[] args) throws IOException {

		Calculator calculator = new Calculator();
		
		setOperatorRegex();
		
		showMenu();
		
		while (true) {
			try{
				calculator.getInput();

				calculator.deleteParenthesis();
				try{
					String output = calculator.calculate();
					System.out.println(output);
				}
				catch(ArithmeticException e){
					System.out.println("Arithmetic error, Try again");
				}
			}catch(IOException e){
				System.out.println("Problem with reading data, Try again");
			}
		}

	}	
		

	public static void showMenu() {
		String str = "For example, If you want to calculate 2 + 2 x 2,\nPlease\n Insert:2+2x2\nFor negative numbers use parentheses";

		System.out.println(str);

		System.out.println("\nAvailable operators");

		for (Map.Entry<String, AbstractCommand> entry : map.entrySet()) {

			System.out.print(entry.getKey());
			System.out.println("  " + entry.getValue().getClass().getSimpleName());

		}
		System.out.println("\n");

		System.out.println("Exit - press q");

	}
	
	public void getInput() throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		
		
		while (true) {

			inputList.clear();
			System.out.println("Insert: ");
			String input = br.readLine();

			if (input.equals("q")) {
				System.exit(0);
			}

			if (input.equals(null)) {

				continue;
			}

			Pattern p = Pattern.compile("(\\(\\-\\d+\\.*\\d*\\)|\\d+\\.*\\d*)|[+-x/]");
			Matcher m = p.matcher(input);

			while (m.find()) {

				inputList.add(m.group());
			}

			if (inputList.size() < 3 || (inputList.size() % 2 == 0)) {
				System.out.println("Try again");
				continue;

			}

			String regularexp1 = "(\\(\\-\\d+\\.*\\d*\\)|\\d+\\.*\\d*)";
			//String regularexp2 = "[+-x/]";
			String regularexp2 = operatorRegex;
			StringBuilder sb = new StringBuilder();
			for (int i = 1; i <= inputList.size(); i++) {

				if (i % 2 != 0) {
					sb.append(regularexp1);

				} else {
					sb.append(regularexp2);
				}

			}

			String regular = sb.toString();
			

			if (!(input.matches(regular))) {

				System.out.println("Try again");
				continue;
			} else {

				break;
			}

		}

	}

	
	public void getInput(String expression) throws IOException {
		
		
		
		while (true) {

			inputList.clear();
			System.out.println("Insert: ");
			String input = expression;

			if (input.equals("q")) {
				System.exit(0);
			}

			if (input.equals(null)) {

				continue;
			}
			
			Pattern p = Pattern.compile("(\\(\\-\\d+\\.*\\d*\\))|(\\d+\\.*\\d*)|([+-x/])");
			Matcher m = p.matcher(input);

			while (m.find()) {
				inputList.add(m.group());
			}

			if (inputList.size() < 3 || (inputList.size() % 2 == 0)) {
				System.out.println("Try again");
				continue;

			}

			String regularexp1 = "(\\(\\-\\d+\\.*\\d*\\)|\\d+\\.*\\d*)";
			//String regularexp2 = "[+-x/]";
			String regularexp2 = operatorRegex;
			
			StringBuilder sb = new StringBuilder();
			for (int i = 1; i <= inputList.size(); i++) {

				if (i % 2 != 0) {
					sb.append(regularexp1);

				} else {
					sb.append(regularexp2);
				}

			}

			String regular = sb.toString();
			

			if (!(input.matches(regular))) {

				System.out.println("Try again");
				continue;
			} else {

				break;
			}

		}

	}

	
	
	
	public void deleteParenthesis() {

		String element;

		Iterator<String> itr = inputList.iterator();

		while (itr.hasNext()) {

			element = itr.next();
			if (element.substring(0, 1).equals(("("))) {

				String elementWithParentheses = new String(element);
				
				String elementWithoutParentheses = elementWithParentheses.substring(1, elementWithParentheses.length() - 1);
				listWithoutParentheses.add(elementWithoutParentheses);

			} else {
				listWithoutParentheses.add(new String(element));

			}

		}

	}
	
	public String calculate() throws ArithmeticException {

		ArrayList<AbstractCommand> commandList = new ArrayList<AbstractCommand>();

		for (int i = 0; i < listWithoutParentheses.size(); i++) {

			for (Map.Entry<String, AbstractCommand> entry : map.entrySet()) {

				if (listWithoutParentheses.get(i).equals(entry.getKey())) {

					commandList.add(entry.getValue());

				}
			}
		}

		Collections.sort(commandList);

		Iterator<AbstractCommand> itr = commandList.iterator();

		while (itr.hasNext()) {

			AbstractCommand abstractCommand = itr.next();

			String sign = abstractCommand.getSign();

			for (int i = 0; i < listWithoutParentheses.size(); i++) {

				if (listWithoutParentheses.get(i).equals(sign)) {

					String prev = listWithoutParentheses.get(i - 1);
					String next = listWithoutParentheses.get(i + 1);

					String answer = abstractCommand.execute(prev, next);

					listWithoutParentheses.add(i, answer);
					listWithoutParentheses.remove(i - 1);
					listWithoutParentheses.remove(i + 1);
					listWithoutParentheses.remove(i);

				}

			}

		}

		String output = listWithoutParentheses.get(0);
		listWithoutParentheses.clear();
		return output;
	}
	
	public static void setOperatorRegex(){
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("[");
		
		for (Map.Entry<String, AbstractCommand> entry : map.entrySet()) {

			sb.append(entry.getKey());
			

		}
		sb.append("]");
		
		operatorRegex = sb.toString();
		
	}
}	
