package com.ittr.datas.PurchaseOrder;

import com.ittr.datas.Order.AbstractOrder;

public class PurchaseOrder extends AbstractOrder{
	
	public PurchaseOrder() {
		super();
		
		// Eðer typeCode 2 ise PurchaseOrderdýr.
		setTypeCode(2);
	}
}
