package com.example.demo.main;

import com.example.demo.chap07.ExeTimeCalculator;
import com.example.demo.chap07.ImpeCalculator;
import com.example.demo.chap07.RecCalculator;

public class MainProxy {

	public static void main(String[] args) {
		ExeTimeCalculator ttCal1 = new ExeTimeCalculator(new ImpeCalculator());
		System.out.println(ttCal1.factorial(20));

		ExeTimeCalculator ttCal2 = new ExeTimeCalculator(new RecCalculator());
		System.out.println(ttCal2.factorial(20));
	}
}
