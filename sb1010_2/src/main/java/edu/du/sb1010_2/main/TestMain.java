package edu.du.sb1010_2.main;

import edu.du.sb1010_2.chap07.Calculator;
import edu.du.sb1010_2.chap07.ImpeCalculator;
import edu.du.sb1010_2.chap07.RecCalculator;

public class TestMain {
    public static void main(String[] args) {
        Calculator cal = new ImpeCalculator();
        System.out.println(cal.factorial(12));
        Calculator cal2 = new RecCalculator();
        System.out.println(cal2.factorial(12));

    }
}
