package com.ittr.datas.PurchaseOrder;

import com.ittr.datas.Order.AbstractOrder;

public class PurchaseOrder extends AbstractOrder{
	
	public PurchaseOrder() {
		super();
		
		// Eğer typeCode 2 ise PurchaseOrderdır.
		setTypeCode(2);
	}
}
