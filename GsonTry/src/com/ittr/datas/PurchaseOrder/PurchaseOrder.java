package com.ittr.datas.PurchaseOrder;

import com.ittr.datas.Order.Order;

public class PurchaseOrder extends Order{
	
	public PurchaseOrder() {
		super();
		
		// Eðer typeCode 2 ise PurchaseOrderdýr.
		setTypeCode(2);
	}
}
