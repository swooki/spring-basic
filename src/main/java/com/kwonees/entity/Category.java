package com.kwonees.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Setter
@Getter
@ToString (exclude="picture")
public class Category {
	private Integer categoryId;
	private String categoryName;
	private String description;
	private byte[] picture;
}
