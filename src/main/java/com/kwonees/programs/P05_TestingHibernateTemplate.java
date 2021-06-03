package com.kwonees.programs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.orm.hibernate5.HibernateTemplate;

import com.kwonees.cfg.AppConfig4;
import com.kwonees.entity.Category;

public class P05_TestingHibernateTemplate {

	@Autowired
	static HibernateTemplate ht;
	
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx;
		ctx = new AnnotationConfigApplicationContext(AppConfig4.class);
		
		HibernateTemplate ht = ctx.getBean(HibernateTemplate.class);
		Category c1 = ht.get(Category.class, 1);
		
		System.out.println(c1);
		
		ctx.close();
	}

}
