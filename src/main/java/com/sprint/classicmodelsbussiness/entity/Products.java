package com.sprint.classicmodelsbussiness.entity;

import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "products")
public class Products {
	@Id
	@Column(name = "productCode", nullable = false)
	private String productCode;
	
	@Column(name = "productName", nullable = false)
	private String productName;

	@ManyToOne
	@JoinColumn(name = "productLine", nullable = false)
	private ProductLines productLine;

	@Column(name = "productScale", nullable = false)
	private String productScale;
	
	@Column(name = "productVendor", nullable = false)
	private String productVendor;
	
	@Column(name = "productDescription", nullable = false, columnDefinition = "text")
	private String productDescription;
	
	@Column(name = "quantityInStock", nullable = false)
	private Short quantityInStock;
	
	@Column(name = "buyPrice", nullable = false)
	private BigDecimal buyPrice;
	
	@Column(name = "msrp", nullable = false)
	private BigDecimal msrp;

	public Products(String productCode, String productName, ProductLines productLine, String productScale,
			String productVendor, String productDescription, Short quantityInStock, BigDecimal buyPrice,
			BigDecimal msrp) {
		super();
		this.productCode = productCode;
		this.productName = productName;
		this.productLine = productLine;
		this.productScale = productScale;
		this.productVendor = productVendor;
		this.productDescription = productDescription;
		this.quantityInStock = quantityInStock;
		this.buyPrice = buyPrice;
		this.msrp = msrp;
	}

	public Products() {
		super();
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public ProductLines getProductLine() {
		return productLine;
	}

	public void setProductLine(ProductLines productLine) {
		this.productLine = productLine;
	}

	public String getProductScale() {
		return productScale;
	}

	public void setProductScale(String productScale) {
		this.productScale = productScale;
	}

	public String getProductVendor() {
		return productVendor;
	}

	public void setProductVendor(String productVendor) {
		this.productVendor = productVendor;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public Short getQuantityInStock() {
		return quantityInStock;
	}

	public void setQuantityInStock(Short quantityInStock) {
		this.quantityInStock = quantityInStock;
	}

	public BigDecimal getBuyPrice() {
		return buyPrice;
	}

	public void setBuyPrice(BigDecimal buyPrice) {
		this.buyPrice = buyPrice;
	}

	public BigDecimal getMsrp() {
		return msrp;
	}

	public void setMsrp(BigDecimal msrp) {
		this.msrp = msrp;
	}

	@Override
	public int hashCode() {
		return Objects.hash(buyPrice, msrp, productCode, productDescription, productLine, productName, productScale,
				productVendor, quantityInStock);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Products other = (Products) obj;
		return Objects.equals(buyPrice, other.buyPrice) && Objects.equals(msrp, other.msrp)
				&& Objects.equals(productCode, other.productCode)
				&& Objects.equals(productDescription, other.productDescription)
				&& Objects.equals(productLine, other.productLine) && Objects.equals(productName, other.productName)
				&& Objects.equals(productScale, other.productScale)
				&& Objects.equals(productVendor, other.productVendor)
				&& Objects.equals(quantityInStock, other.quantityInStock);
	}

	@Override
	public String toString() {
		return "Products [productCode=" + productCode + ", productName=" + productName + ", productLine=" + productLine
				+ ", productScale=" + productScale + ", productVendor=" + productVendor + ", productDescription="
				+ productDescription + ", quantityInStock=" + quantityInStock + ", buyPrice=" + buyPrice + ", msrp="
				+ msrp + "]";
	}

}
