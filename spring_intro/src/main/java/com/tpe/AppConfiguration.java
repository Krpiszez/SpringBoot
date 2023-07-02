package com.tpe;

import org.springframework.context.annotation.*;

import java.awt.*;

@Configuration // This will be our Configuration class and this class is in the top(the most out) package because it includes
//everything in this app in it.
@ComponentScan("com.tpe")// by default(without parenthesis) it will scan all classes, but we type the top package so here it scans
// every classes in those packages. It will scan all sub-packages(classes etc.) starting from "com.tpe"
@PropertySource("classpath:application.properties") // we add this here to let our Spring reach out our properties file in resources.
public class AppConfiguration {

    @Bean // Here bean is a way to add objects to container but different than the component this annotation is on method level.
    public Point point(){
        return new Point(45, 33);
    }

}
