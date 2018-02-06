package com.ittr.main;

import java.util.ArrayList;
import java.util.List;

import com.ittr.datas.PurchaseOrder.PurchaseOrderDetail;

// bu class PurchaseOrderDetaillerin ExternalOrderId leri aynı olanları PurchaseOrderDetailistesi halinde tutmamızı sağlıyor
// Aslında PurchaseOrder Bu class
public class PurchaseOrderListPerItem {

	private List<PurchaseOrderDetail> purchaseOrderDetailListForSameItem = new ArrayList<PurchaseOrderDetail>();
	
	public List<PurchaseOrderDetail> getPurchaseOrderDetailListForSameItem() {
		return purchaseOrderDetailListForSameItem;
	}
	public void setPurchaseOrderDetailListForSameItem(List<PurchaseOrderDetail> purchaseOrderDetailListForSameItem) {
		this.purchaseOrderDetailListForSameItem = purchaseOrderDetailListForSameItem;
	}
}
