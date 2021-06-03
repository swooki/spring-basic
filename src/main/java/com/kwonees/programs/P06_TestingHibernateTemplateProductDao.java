package com.kwonees.programs;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.kwonees.cfg.AppConfig4;
import com.kwonees.dao.DaoException;
import com.kwonees.dao.ProductDao;
import com.kwonees.entity.Product;

public class P06_TestingHibernateTemplateProductDao {

	public static void main(String[] args) throws DaoException {
		AnnotationConfigApplicationContext ctx;
		ctx = new AnnotationConfigApplicationContext(AppConfig4.class);

		ProductDao dao = ctx.getBean("htDao", ProductDao.class);
		System.out.println("dao is an instance of " + dao.getClass().getName());

		Product p = dao.getProduct(1);
		System.out.println("unit price of 1 : $" + p.getUnitPrice());

		System.out.println("There are total " + dao.count());

//		p.setUnitPrice(p.getUnitPrice() + 1);
//		dao.updateProduct(p);
//		

		// System.out.println("-----------------");
//		List<Product> list = dao.getAllProducts();
//		for(Product p1: list) {
//			System.out.println(p1);
//		}
//		System.out.println("-----------------");

// 		list = dao.getDiscontinuedProducts();
//		for(Product p1: list) {
//			System.out.println(p1);
//		}
//		System.out.println("-----------------");

		System.out.println("-----------------");

		double min = 50, max = 200;
		List<Product> list = dao.getProductsByPriceRange(min, max);
		System.out.println("There are " + list.size() + " products between " + min + " and " + max);

		min = 200;
		max = 50;
		List<Product> list2 = dao.getProductsByPriceRange(min, max);
		System.out.println("There are " + list2.size() + " products between " + min + " and " + max);

		p.setUnitPrice(p.getUnitPrice() + 1);
		try {
			dao.updateProduct(p);
		} catch (DaoException ex) {
			System.out.println("There was an error: " + ex.getMessage());
		}
		
		System.out.println("End of testing");
		ctx.close();
	}
}
