package com.tpeprc;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("com.tpeprc")
@PropertySource("classpath:applicationprc.properties")
public class AppConfig {



}
