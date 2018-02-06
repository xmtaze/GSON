package com.ittr.datas.PurchaseOrder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.ittr.datas.OrderDetail.Order_Detail;
import com.ittr.main.ExcelDataManager;

public class PurchaseOrderDetail extends Order_Detail implements Comparable<PurchaseOrderDetail>   { 

	public List<PurchaseOrder> setPurchaseOrderDetailItemsInPurchaseOrder(PurchaseOrder purchaseOrder) throws Exception {

		List<PurchaseOrderDetail> purchaseOrderDetailListFromExcel = new ArrayList<PurchaseOrderDetail>();
		ExcelDataManager excelDataManager = new ExcelDataManager();
		purchaseOrderDetailListFromExcel = excelDataManager.createPurchaseOrderDetailList();
		
		//Excelden gelen purchaseOrderDetailler Sort ediliyorlar 
		Collections.sort(purchaseOrderDetailListFromExcel);
		
		List<PurchaseOrder> purchaseOrderList = new ArrayList<>();
		List<PurchaseOrderDetail> puchaseOrderDetailSameItemList = new ArrayList<>();
		
		int preExternalOrderId = purchaseOrderDetailListFromExcel.get(0).getExternalOrderID();;
		for(int i = 0 ; i<purchaseOrderDetailListFromExcel.size(); i++) {
			int lastExternalOrderId = purchaseOrderDetailListFromExcel.get(i).getExternalOrderID();

			//İlk başta liste sıralı geldiği için aynı olanları puchaseOrderDetailSameItemList'e ekliyoruz
			if(preExternalOrderId==lastExternalOrderId) {
				puchaseOrderDetailSameItemList.add(purchaseOrderDetailListFromExcel.get(i));
				preExternalOrderId = lastExternalOrderId;
			}
			// Sonrasında değişen her bir ExternalOrderId için yeni bir PurchaseOrder oluşturup purchaseOrderListe ekleniyor
			else {
				PurchaseOrder purchaseOrderNewItem = new PurchaseOrder();
				List<PurchaseOrderDetail> puchaseOrderDetailNewSameItemList = new ArrayList<>();
				puchaseOrderDetailNewSameItemList.add(purchaseOrderDetailListFromExcel.get(i));
				purchaseOrderNewItem.setPurchaseOrderDetailListForSameItem(puchaseOrderDetailNewSameItemList);
				purchaseOrderList.add(purchaseOrderNewItem);
				preExternalOrderId = lastExternalOrderId;
			}
		}
		purchaseOrder.setPurchaseOrderDetailListForSameItem(puchaseOrderDetailSameItemList);
		purchaseOrderList.add(purchaseOrder);
		return purchaseOrderList;
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

