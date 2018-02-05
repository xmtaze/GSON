package com.ittr.datas.OrderDetail;

import com.ittr.datas.Product.Product;

public class Order_Detail {

	private int OrderID;
	private int ProductID;
	private double UnitPrice;
	private short Quantity;
	private float Discount;
	
	// Excelden veri gelirken kullandığımız alanlar(PurchaseOrder için olan)
	private int ExternalOrderID;
	protected int ExternalItemID;
	protected long InternalOrderID;
	
	Product Product;
	
	public long getInternalOrderID() {
		return InternalOrderID;
	}
	public void setInternalOrderID(long internalOrderID) {
		InternalOrderID = internalOrderID;
	}
	public int getExternalOrderID() {
		return ExternalOrderID;
	}
	public void setExternalOrderID(int externalOrderID) {
		ExternalOrderID = externalOrderID;
	}
	public int getExternalItemID() {
		return ExternalItemID;
	}
	public void setExternalItemID(int externalItemID) {
		ExternalItemID = externalItemID;
	}
	public Product getProduct() {
		return Product;
	}
	public void setProduct(Product product) {
		Product = product;
	}
	public int getOrderID() {
		return OrderID;
	}
	public void setOrderID(int orderID) {
		OrderID = orderID;
	}
	public int getProductID() {
		return ProductID;
	}
	public void setProductID(int productID) {
		ProductID = productID;
	}
	public double getUnitPrice() {
		return UnitPrice;
	}
	public void setUnitPrice(double unitPrice) {
		UnitPrice = unitPrice;
	}
	public short getQuantity() {
		return Quantity;
	}
	public void setQuantity(short quantity) {
		Quantity = quantity;
	}
	public float getDiscount() {
		return Discount;
	}
	public void setDiscount(float discount) {
		Discount = discount;
	}
}
