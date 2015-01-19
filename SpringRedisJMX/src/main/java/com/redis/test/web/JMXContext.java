package com.redis.test.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.context.annotation.Lazy;
import org.springframework.jmx.export.annotation.AnnotationMBeanExporter;

@Configuration
@EnableMBeanExport
public class JMXContext {

	@Bean @Lazy(false)
	public AnnotationMBeanExporter mbeanExport(){
		return new AnnotationMBeanExporter();
	}
	
}
