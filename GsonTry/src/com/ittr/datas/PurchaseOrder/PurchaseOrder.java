package com.ittr.datas.PurchaseOrder;

import com.ittr.datas.Order.Order;

public class PurchaseOrder extends Order{
	
	public PurchaseOrder() {
		super();
		
		// Eğer typeCode 2 ise PurchaseOrderdır.
		setTypeCode(2);
	}
}
