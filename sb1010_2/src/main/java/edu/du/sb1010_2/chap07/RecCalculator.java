package edu.du.sb1010_2.chap07;

public class RecCalculator implements Calculator {

	@Override
	public long factorial(long num) {
        if (num == 0)
            return 1;
        else
            return num * factorial(num - 1);
	}

}
