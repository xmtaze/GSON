package com.ittr.datas.PurchaseOrder;

import com.ittr.datas.Order.AbstractOrder;

public class PurchaseOrder extends AbstractOrder{
	
	public PurchaseOrder() {
		super();
		
		// E�er typeCode 2 ise PurchaseOrderd�r.
		setTypeCode(2);
	}
}
