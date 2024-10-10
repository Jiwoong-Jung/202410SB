package com.example.demo.main;

import com.example.demo.chap07.RecCalculator;
import com.example.demo.config.AppCtx;
import com.example.demo.config.AppCtxWithClassProxy;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class MainAspectWithClassProxy {
	
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = 
				new AnnotationConfigApplicationContext(AppCtx.class);

		RecCalculator cal = ctx.getBean("calculator", RecCalculator.class);
		long fiveFact = cal.factorial(5);
		System.out.println("cal.factorial(5) = " + fiveFact);
		System.out.println(cal.getClass().getName());
		ctx.close();
	}

}
