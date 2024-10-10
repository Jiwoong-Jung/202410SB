package com.example.demo.config;

import com.example.demo.aspect.CacheAspect;
import com.example.demo.aspect.ExeTimeAspect;
import com.example.demo.chap07.Calculator;
import com.example.demo.chap07.RecCalculator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.annotation.Order;

@Configuration
@EnableAspectJAutoProxy
public class AppCtxWithCache {

	@Bean
	public CacheAspect cacheAspect() {
		return new CacheAspect();
	}

	@Bean
	public ExeTimeAspect exeTimeAspect() {
		return new ExeTimeAspect();
	}

	@Bean
	public Calculator calculator() {
		return new RecCalculator();
	}

}
