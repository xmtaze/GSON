package com.ittr.datas.Order;

import com.ittr.datas.PurchaseOrder.PurchaseOrder;
import com.ittr.datas.SalesOrder.SalesOrder;


// bu class þuan kullanýlmýyor kafam baya karýþtý.
public class TypeFactory {

	public static Order getOrderType(String orderType) {
		
		if (orderType == null) {
			return null;
		}
		else if (orderType.equalsIgnoreCase("Sales")) {
			return new SalesOrder();
		}
		else if (orderType.equalsIgnoreCase("Purchase")) {
			return new PurchaseOrder();
		} 
		return null;
	}
}
