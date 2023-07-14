package com.sprint.classicmodelsbussiness.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "productLines")
public class ProductLines {
	@Id
	@Column(name = "productLine", nullable = false)
	private String productLine;
	
	@Column(name = "textDescription", nullable = true)
	private String textDescription;
	
	@Column(name = "htmlDescription", nullable = true, columnDefinition = "mediumtext")
	private String htmlDescription;
	
	@Column(name = "image", nullable = true)
	private String image;

	public ProductLines(String productLine, String textDescription, String htmlDescription, String image) {
		super();
		this.productLine = productLine;
		this.textDescription = textDescription;
		this.htmlDescription = htmlDescription;
		this.image = image;
	}

	public ProductLines() {
		super();
	}

	public String getProductLine() {
		return productLine;
	}

	public void setProductLine(String productLine) {
		this.productLine = productLine;
	}

	public String getTextDescription() {
		return textDescription;
	}

	public void setTextDescription(String textDescription) {
		this.textDescription = textDescription;
	}

	public String getHtmlDescription() {
		return htmlDescription;
	}

	public void setHtmlDescription(String htmlDescription) {
		this.htmlDescription = htmlDescription;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public int hashCode() {
		return Objects.hash(htmlDescription, image, productLine, textDescription);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductLines other = (ProductLines) obj;
		return Objects.equals(htmlDescription, other.htmlDescription) && Objects.equals(image, other.image)
				&& Objects.equals(productLine, other.productLine)
				&& Objects.equals(textDescription, other.textDescription);
	}

	@Override
	public String toString() {
		return "ProductLines [productLine=" + productLine + ", textDescription=" + textDescription
				+ ", htmlDescription=" + htmlDescription + ", image=" + image + "]";
	}

//	@OneToMany(mappedBy = "productLine",cascade = CascadeType.ALL)
//	private Collection<Products> productsCollection;
//
//
//	public Collection<Products> getProductsCollection() {
//		return productsCollection;
//	}
//
//
//	public void setProductsCollection(Collection<Products> productsCollection) {
//		this.productsCollection = productsCollection;
//	}

}
