package com.ittr.datas.PurchaseOrder;

import java.util.ArrayList;
import java.util.List;

import com.ittr.datas.Order.Order;
import com.ittr.main.PurchaseOrderListForItem;

public class PurchaseOrder extends Order{

	public PurchaseOrder() {
		super();

		// Eğer typeCode 2 ise PurchaseOrderder.
		setTypeCode(2);
	}
	List<PurchaseOrderListForItem> PurchaseOrderDetailListForItemLIST = new ArrayList<>();

	public List<PurchaseOrderListForItem> getPurchaseOrderDetailListForItemLIST() {
		return PurchaseOrderDetailListForItemLIST;
	}

	public void setPurchaseOrderDetailListForItemLIST(List<PurchaseOrderListForItem> purchaseOrderDetailListForItemLIST) {
		PurchaseOrderDetailListForItemLIST = purchaseOrderDetailListForItemLIST;
	}

	public PurchaseOrderDetail setPurchaseOrderDetail(PurchaseOrder purchaseOrder) throws Exception {

		PurchaseOrderDetail purchaseOrderDetail = new PurchaseOrderDetail();
		List<PurchaseOrderListForItem> PurchaseOrderDetailListForItemLIST = new ArrayList<>();
		purchaseOrderDetail.setPurchaseOrderDetailItemsInPurchaseOrder(this);
		return purchaseOrderDetail;
	}
}
