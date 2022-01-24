package org.facily.core_commerce.factory;

public class Seller {

	public Seller() {}
	
	public Seller(String name, String fulfillment_type) {
		super();
		this.name = name;
		this.fulfillment_type = fulfillment_type;
	}

	private String name;
	private String fulfillment_type;
	
	public String getName() {
		return name;
	}

	public String getFulfillment_type() {
		return fulfillment_type;
	}
	protected void setName(String name) {
		this.name = name;
	}

	protected void setfulfillment_type(String fulfillment_type) {
		this.fulfillment_type = fulfillment_type;
	}
}
	

