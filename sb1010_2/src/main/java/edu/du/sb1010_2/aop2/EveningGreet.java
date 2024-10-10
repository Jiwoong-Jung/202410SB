package edu.du.sb1010_2.aop2;

import org.springframework.stereotype.Component;

@Component
public class EveningGreet implements Greet {
    @Override
    public void greeting() {
        System.out.println("------------------");
        System.out.println("좋은 저녁입니다.");
        System.out.println("------------------");
    }
}
