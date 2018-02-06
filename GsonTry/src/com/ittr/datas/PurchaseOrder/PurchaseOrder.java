package com.ittr.datas.PurchaseOrder;

import java.util.ArrayList;
import java.util.List;
import com.ittr.datas.Order.Order;
import com.ittr.main.PurchaseOrderListPerItem;

public class PurchaseOrder extends Order{

	public PurchaseOrder() {
		super();

		// Eğer typeCode 2 ise PurchaseOrderder.
		setTypeCode(2);
	}
	List<PurchaseOrderListPerItem> PurchaseOrderITEMLIST = new ArrayList<>();

	public List<PurchaseOrderListPerItem> getPurchaseOrderLIST() {
		return PurchaseOrderITEMLIST;
	}

	public void setPurchaseOrderLIST(List<PurchaseOrderListPerItem> purchaseOrderDetailListForItemLIST) {
		PurchaseOrderITEMLIST = purchaseOrderDetailListForItemLIST;
	}

	public PurchaseOrderDetail setPurchaseOrderDetail(PurchaseOrder purchaseOrder) throws Exception {

		PurchaseOrderDetail purchaseOrderDetail = new PurchaseOrderDetail();
		purchaseOrderDetail.setPurchaseOrderDetailItemsInPurchaseOrder(this);
		return purchaseOrderDetail;
	}
}
