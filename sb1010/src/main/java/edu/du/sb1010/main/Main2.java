package edu.du.sb1010.main;

import edu.du.sb1010.config.AppCtx2;
import edu.du.sb1010.spring.Client;
import edu.du.sb1010.spring.Client2;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class Main2 {
    public static void main(String[] args) {
        AbstractApplicationContext ctx =
                new AnnotationConfigApplicationContext(AppCtx2.class);

        Client2 client2 = ctx.getBean(Client2.class);
        client2.send();

        ctx.close();
    }
}
