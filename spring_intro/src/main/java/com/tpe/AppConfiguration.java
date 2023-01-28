package com.tpe;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration // This will be our Configuration class and this class is in the top(the most out) package because it includes
//everything in this app in it.
@ComponentScan("com.tpe")// by default(without parenthesis) it will scan all classes, but we type the top package so here it scans
// every classes in those packages. It will scan all sub-packages(classes etc.) starting from "com.tpe"
public class AppConfiguration {
}
