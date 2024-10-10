package edu.du.sb1010_2.main;

import edu.du.sb1010_2.chap07.Calculator;
import edu.du.sb1010_2.config.AppCtxWithCache;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainAspectWithCache {
	
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = 
				new AnnotationConfigApplicationContext(AppCtxWithCache.class);

		Calculator cal = ctx.getBean("calculator", Calculator.class);
		cal.factorial(7);
		cal.factorial(7);
		cal.factorial(5);
		cal.factorial(5);
		ctx.close();
	}

}
