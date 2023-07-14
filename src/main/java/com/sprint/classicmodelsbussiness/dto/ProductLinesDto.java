package com.sprint.classicmodelsbussiness.dto;

import java.util.Objects;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ProductLinesDto {

	@NotBlank(message = "productLine Should not be null")
	@Size(min = 3, max = 50, message = "Range of characters for productLine is 3 to 15. please enter valid productLine")
	private String productLine;

	@Size(min = 3, max = 4000, message = "Range of characters for textDescription is 3 to 70. please enter valid textDescription")
	private String textDescription;

	@Size(min = 3, max = 100, message = "Range of characters for htmlDescription is 3 to 70. please enter valid htmlDescription")
	private String htmlDescription;

	@Size(min = 3, max = 100, message = "Range of characters for image is 3 to 70. please enter valid image")
	private String image;

	public ProductLinesDto(String productLine, String textDescription, String htmlDescription, String image) {
		super();
		this.productLine = productLine;
		this.textDescription = textDescription;
		this.htmlDescription = htmlDescription;
		this.image = image;
	}

	public ProductLinesDto() {
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
		ProductLinesDto other = (ProductLinesDto) obj;
		return Objects.equals(htmlDescription, other.htmlDescription) && Objects.equals(image, other.image)
				&& Objects.equals(productLine, other.productLine)
				&& Objects.equals(textDescription, other.textDescription);
	}

}
