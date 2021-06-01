package com.kwonees.programs;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.kwonees.cfg.AppConfig4;
import com.kwonees.dao.DaoException;
import com.kwonees.dao.ProductDao;
import com.kwonees.entity.Product;

public class P03_TestingJdbcTemplateProductDao {

	public static void main(String[] args) throws DaoException {
		AnnotationConfigApplicationContext ctx;
		ctx = new AnnotationConfigApplicationContext(AppConfig4.class);
		
		ProductDao dao = ctx.getBean("jtDao", ProductDao.class);
		Product p = dao.getProduct(1);
		System.out.println(p);

		p.setUnitPrice(p.getUnitPrice() + 1);
		dao.updateProduct(p);
		System.out.println("Price updated");

		p = dao.getProduct(1);
		System.out.println(p);

		List<Product> list = dao.getProductsByPriceRange(50.0, 200.0);
		for(Product product: list) {
			System.out.println(product.getProductName() + ":" + product.getUnitPrice());
		}
		
		ctx.close();
	}

}
