package com.sprint.classicmodelsbussiness.dto;

import java.math.BigDecimal;
import java.util.Objects;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ProductDto {

	@NotBlank(message = "productCode Should not be null")
	@Size(min = 3, max = 15, message = "Range of characters for productCode is 3 to 15. please enter valid productCode")
	private String productCode;

	@NotBlank(message = "productName Should not be null")
	@Size(min = 3, max = 70, message = "Range of characters for productName is 3 to 70. please enter valid productName")
	private String productName;

	@NotBlank(message = "productLine Should not be null")
	@Size(min = 3, max = 50, message = "Range of characters for productLine is 3 to 50. please enter valid productLine")
	private String productLine;

	@NotBlank(message = "productScale Should not be null")
	@Size(min = 3, max = 10, message = "Range of characters for productScale is 3 to 10. please enter valid productScale")
	private String productScale;

	@NotBlank(message = "productVendor Should not be null")
	@Size(min = 3, max = 50, message = "Range of characters for productVendor is 3 to 50. please enter valid productVendor")
	private String productVendor;

	@NotBlank(message = "productDescription Should not be null")
	private String productDescription;

	@NotNull(message = "quantityInStock Should not be null")
	private Short quantityInStock;

	@NotNull(message = "buyPrice Should not be null")
	@Digits(integer = 10, fraction = 2, message = "Limit of characters for buyPrice is integer=10, fraction=2. please enter valid buyPrice")
	private BigDecimal buyPrice;

	@NotNull(message = "msrp Should not be null")
	@Digits(integer = 10, fraction = 2, message = "Limit of characters for msrp is integer=10, fraction=2. please enter valid msrp")
	private BigDecimal msrp;

	public ProductDto(String productCode, String productName, String productLine, String productScale,
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

	public ProductDto() {
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

	public String getProductLine() {
		return productLine;
	}

	public void setProductLine(String productLine) {
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
		ProductDto other = (ProductDto) obj;
		return Objects.equals(buyPrice, other.buyPrice) && Objects.equals(msrp, other.msrp)
				&& Objects.equals(productCode, other.productCode)
				&& Objects.equals(productDescription, other.productDescription)
				&& Objects.equals(productLine, other.productLine) && Objects.equals(productName, other.productName)
				&& Objects.equals(productScale, other.productScale)
				&& Objects.equals(productVendor, other.productVendor)
				&& Objects.equals(quantityInStock, other.quantityInStock);
	}

}
