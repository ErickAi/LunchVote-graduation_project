package com.example;

import org.springframework.context.support.GenericXmlApplicationContext;

public class SpringMain {
    public static void main(String[] args) {
        // java 7 Automatic resource management
        try (GenericXmlApplicationContext appCtx = new GenericXmlApplicationContext()) {
            appCtx.load("spring/spring-app.xml", "spring/spring-db.xml", "spring/spring-mvc.xml");
            appCtx.refresh();
            System.out.println("-------------------------------------------------------------------------");
            for (Object o : appCtx.getBeanDefinitionNames()) {
                System.out.println(o);
            }
            System.out.println("-------------------------------------------------------------------------");
        }
    }
}
