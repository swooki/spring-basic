package com.kwonees.programs;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.kwonees.cfg.AppConfig4;
import com.kwonees.entity.Category;

public class P02_TestingJdbcTemplate {

	static JdbcTemplate template;

	static RowMapper<Category> categoryMapper = (rs, rowNum) -> {
		Category category = new Category();
		category.setCategoryId(rs.getInt("category_id"));
		category.setCategoryName(rs.getString("category_name"));
		category.setDescription(rs.getString("description"));
		category.setPicture(rs.getBytes("picture"));
		return category;
	};

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx;
		ctx = new AnnotationConfigApplicationContext(AppConfig4.class);

		template = ctx.getBean(JdbcTemplate.class);

//		insertShipper();
//		updateShipperPhone(4, "(543)234-2938");
//		System.out.println(getProductCount() + " products found");
// 		System.out.println(getShipperName(4));
// 		 
//		Map<String, Object> result = getProductDetails(33);
//		for(String key: result.keySet()) {
//			System.out.println(key + "=" + result.get(key));
//		}
//
//		List<Map<String, Object>> list = getAllShippers();
// 		for(Map<String, Object> data:list) {
// 			System.out.println("----------------");
// 			for(String key: data.keySet()) {
// 				System.out.println(key + "=" + data.get(key));
// 			}
// 		}
// 		
// 		List<String> nameList = getAllShipperNames();
// 		for (String name: nameList) {
// 			System.out.println(name);
// 		}
		System.out.println(getCategory(2));

		List<Category> list = getAllCategories();
		for(Category cat: list) {
			System.out.println(cat);
		}
		  

		ctx.close();
	}

	static List<Category> getAllCategories() {
		return template.query("select * from categories", categoryMapper);
 	}

	static Category getCategory(int id) {
		String sql = "select * from categories where category_id = ?";
		Category cat = template.queryForObject(sql, categoryMapper, id);
		return cat;
	}

	static List<String> getAllShipperNames() {
		String sql = "select company_name from shippers";
		List<String> list = template.queryForList(sql, String.class);
		return list;
	}

	static List<Map<String, Object>> getAllShippers() {
		String sql = "select * from shippers";
		List<Map<String, Object>> list = template.queryForList(sql);
		return list;
	}

	static Map<String, Object> getProductDetails(int id) {
		String sql = "select * from products where product_id = ?";
		Map<String, Object> data = template.queryForMap(sql, id);
		return data;
	}

	static String getShipperName(int id) {
		String sql = "select company_name from shippers where shipper_id = ?";
		String name = template.queryForObject(sql, String.class, id);
		return name;
	}

	static Integer getProductCount() {
		String sql = "select count(*) from products";
		return template.queryForObject(sql, Integer.class);
	}

	static void updateShipperPhone(int id, String phone) {
		template.update("update shippers set phone = ? where shipper_id = ?", phone, id);
	}

	static void insertShipper() {
		template.update("insert into shippers values (?,?,?)", 4, "MegaTransportation", "(122) 993-0203");
	}

}
