package com.ittr.datas.PurchaseOrder;

import com.ittr.datas.Order.Order;

public class PurchaseOrder extends Order{
	
	public PurchaseOrder() {
		super();
		
		// E�er typeCode 2 ise PurchaseOrderd�r.
		setTypeCode(2);
	}
}
