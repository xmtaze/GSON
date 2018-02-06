package com.ittr.datas.PurchaseOrder;

import java.util.ArrayList;
import java.util.List;
import com.ittr.datas.Order.Order;

public class PurchaseOrder extends Order{
	
	private List<PurchaseOrderDetail> purchaseOrderDetailList = new ArrayList<PurchaseOrderDetail>();
	
	public PurchaseOrder() {
		super();

		// Eğer typeCode 2 ise PurchaseOrderder.
		setTypeCode(2);
	}
	
	public List<PurchaseOrderDetail> getPurchaseOrderDetailListForSameItem() {
		return purchaseOrderDetailList;
	}
	public void setPurchaseOrderDetailListForSameItem(List<PurchaseOrderDetail> purchaseOrderDetailListForSameItem) {
		this.purchaseOrderDetailList = purchaseOrderDetailListForSameItem;
	}
	
	// PurchaseOrderların içerisine PurchaseOrderDetaillerin set edilmesi
	public List<PurchaseOrder> setPurchaseOrderDetail(PurchaseOrder purchaseOrder) throws Exception {

		List<PurchaseOrder> purchaseOrderList = new ArrayList<PurchaseOrder>();
		PurchaseOrderDetail purchaseOrderDetail = new PurchaseOrderDetail();
		purchaseOrderList = purchaseOrderDetail.setPurchaseOrderDetailItemsInPurchaseOrder(purchaseOrder);
		return purchaseOrderList;
	}
}
