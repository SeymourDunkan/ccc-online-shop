package org.irn.store.product;

import java.math.BigDecimal;
import java.util.Date;
import java.sql.Timestamp;

public class Product {
     private Integer product_id;
     private String type;
     private String brand;
     private String model;
     private String material;
     private String color;
     private String image;
     private BigDecimal price;
     private Timestamp dateAdded;
     private Timestamp lastUpdated;
     private Integer categoryId;
     
	public Product(Integer product_id, String type, String brand, String model, String material, String color,
			String image, BigDecimal price, Date dateAdded, Integer categoryId) {
		this.product_id = product_id;
		this.type = type;
		this.brand = brand;
		this.model = model;
		this.material = material;
		this.color = color;
		this.image = image;
		this.price = price;
		this.dateAdded = new Timestamp(dateAdded.getTime());
		this.categoryId = categoryId;
	}
	
	public Product(String type, String brand, String model, String material, String color,
			String image, BigDecimal price, Date dateAdded, Integer categoryId) {
		this.type = type;
		this.brand = brand;
		this.model = model;
		this.material = material;
		this.color = color;
		this.image = image;
		this.price = price;
		this.dateAdded = new Timestamp(dateAdded.getTime());
		this.categoryId = categoryId;
	}
	
	public Product() {}

	public Integer getId() {
		return product_id;
	}

	public void setId(Integer product_id) {
		this.product_id = product_id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Timestamp getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Date date) {
		this.dateAdded = new Timestamp(date.getTime());
	}

	public Timestamp getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Timestamp lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	@Override
	public String toString() {
		return "Product [product_id=" + product_id + ", type=" + type + ", brand=" + brand + ", model=" + model
				+ ", material=" + material + ", color=" + color + ", image=" + image + ", price=" + price
				+ ", dateAdded=" + dateAdded + ", lastUpdated=" + lastUpdated + ", categoryId=" + categoryId + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((product_id == null) ? 0 : product_id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (product_id == null) {
			if (other.product_id != null)
				return false;
		} else if (!product_id.equals(other.product_id))
			return false;
		return true;
	}

    
}
