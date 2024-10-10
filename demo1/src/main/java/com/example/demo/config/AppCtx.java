package com.example.demo.config;

import com.example.demo.aspect.ExeTimeAspect;
import com.example.demo.chap07.Calculator;
import com.example.demo.chap07.RecCalculator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class AppCtx {
	@Bean
	public ExeTimeAspect exeTimeAspect() {
		return new ExeTimeAspect();
	}

	@Bean
	public Calculator calculator() {
		return new RecCalculator();
	}

}
