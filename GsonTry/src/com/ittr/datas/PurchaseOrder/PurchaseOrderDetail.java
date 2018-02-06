package com.ittr.datas.PurchaseOrder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.ittr.datas.OrderDetail.Order_Detail;
import com.ittr.main.ExcelDataManager;
import com.ittr.main.PurchaseOrderListPerItem;

public class PurchaseOrderDetail extends Order_Detail implements Comparable<PurchaseOrderDetail>   { 

	public PurchaseOrder setPurchaseOrderDetailItemsInPurchaseOrder(PurchaseOrder purchaseOrder) throws Exception {

		List<PurchaseOrderDetail> purchaseOrderDetailList = new ArrayList<PurchaseOrderDetail>();
		ExcelDataManager excelDataManager = new ExcelDataManager();
		purchaseOrderDetailList = excelDataManager.createPurchaseOrderDetailList();
		Collections.sort(purchaseOrderDetailList);

		PurchaseOrderListPerItem purchaseOrderListForItem = new PurchaseOrderListPerItem();
		List<PurchaseOrderListPerItem> PurchaseOrderDetailListForItemLIST = new ArrayList<>();
		List<PurchaseOrderDetail> purchaseOrderDetailListForSameItems = new ArrayList<PurchaseOrderDetail>();
		
		// İlk başta liste sıralı geldiği için aynı olanları purchaseOrderDetailListForSameItems'a ekliyoruz
		int preExternalOrderId = purchaseOrderDetailList.get(0).getExternalOrderID();;
		for(int i = 0 ; i<purchaseOrderDetailList.size(); i++) {
			int lastExternalOrderId = purchaseOrderDetailList.get(i).getExternalOrderID();
			
			if(preExternalOrderId==lastExternalOrderId) {
				purchaseOrderDetailListForSameItems.add(purchaseOrderDetailList.get(i));
				preExternalOrderId = lastExternalOrderId;
			}
			// Sonrasında değişen her bir ExternalOrderId için yeni bir Liste açıp purchaseOrderDetailListForSameItems'a ekliyoruz
			else {
				PurchaseOrderListPerItem purchaseOrderListForItemChange = new PurchaseOrderListPerItem();
				List<PurchaseOrderDetail> purchaseOrderDetailListForSameItemsAfterChange = new ArrayList<PurchaseOrderDetail>();
				purchaseOrderDetailListForSameItemsAfterChange.add(purchaseOrderDetailList.get(i));
				purchaseOrderListForItemChange.setPurchaseOrderDetailListForSameItem(purchaseOrderDetailListForSameItemsAfterChange);
				PurchaseOrderDetailListForItemLIST.add(purchaseOrderListForItemChange);
				preExternalOrderId = lastExternalOrderId;
			} 
		}
		purchaseOrderListForItem.setPurchaseOrderDetailListForSameItem(purchaseOrderDetailListForSameItems);
		PurchaseOrderDetailListForItemLIST.add(purchaseOrderListForItem);
		purchaseOrder.setPurchaseOrderLIST(PurchaseOrderDetailListForItemLIST);
		return purchaseOrder;
	}

	@Override
	public int compareTo(PurchaseOrderDetail a) {
		if ( this.getExternalOrderID() > a.getExternalOrderID() )
			return 1;
		else if ( this.getExternalOrderID() < a.getExternalOrderID() )
			return -1;
		else {
			if ( this.getExternalItemID() > a.getExternalItemID() )
				return 1;
			else
				return -1;
		}
	}
}

