package com.kwonees.programs;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.kwonees.cfg.AppConfig3;
import com.kwonees.dao.DaoException;
import com.kwonees.dao.ProductDao;

public class P01_GetProductCount {

	public static void main(String[] args) throws DaoException{
		
		// a variable representing the spring container
		AnnotationConfigApplicationContext ctx;
		
		// object of spring container
		ctx = new AnnotationConfigApplicationContext(AppConfig3.class);
		
		System.out.println("---------------");

		// our dependency
		ProductDao dao;
		
		
		dao = ctx.getBean("jdbcProductDao", ProductDao.class);		
		System.out.println("dao is an instance of " + dao.getClass().getName());
		System.out.println("There are " + dao.count() + " products");
		ctx.close();
	}

}
