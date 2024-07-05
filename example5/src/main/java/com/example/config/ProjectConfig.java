package com.example.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

//no beans defined in Config class
@Configuration
@ComponentScan(basePackages = "com.example.beans")
public class ProjectConfig {
}
