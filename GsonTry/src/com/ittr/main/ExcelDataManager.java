package com.ittr.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.impl.client.DefaultHttpClient;
import com.google.gson.Gson;
import com.ittr.datas.Invoice.Invoice;
import com.ittr.datas.Invoice.InvoiceDataset;
import com.ittr.datas.Product.Product;
import com.ittr.datas.PurchaseOrder.PurchaseOrderDataset;
import com.ittr.datas.PurchaseOrder.PurchaseOrderDetail;
import com.ittr.datas.SalesOrder.SalesOrderDataset;
import com.ittr.services.Connection;
import com.ittr.services.ProductFilterOdata;
import com.ittr.util.Util;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

public class ExcelDataManager {

	//içerisinde PurchaseOrderDetailler bulunan liste dönüyor
	public List<PurchaseOrderDetail> createPurchaseOrderDetailList() throws Exception {

		List<PurchaseOrderDetail> purchaseOrderDetailList = new ArrayList<PurchaseOrderDetail>();
		ProductFilterOdata productFilter = new ProductFilterOdata();
		List<Product> productList = productFilter.productFilterOData();

		ExcelReader excelReader = new ExcelReader();
		Map<Integer, List> hashMapComesFromExcel = new TreeMap<Integer, List>();
		List<Integer> itemValuesList = new ArrayList<Integer>();
		hashMapComesFromExcel = excelReader.loadExcelLines(Util.ReadDataFromExcelPath);


		for (Entry<Integer, List> entry : hashMapComesFromExcel.entrySet()) {
			Integer key = entry.getKey();
			itemValuesList = entry.getValue();
			
			PurchaseOrderDetail purchaseOrderDetail = new PurchaseOrderDetail();
			for(int i = 0 ; i < itemValuesList.size() ; i++) {
				
				purchaseOrderDetail.setExternalOrderID(itemValuesList.get(0));
				purchaseOrderDetail.setExternalItemID(itemValuesList.get(1));
				purchaseOrderDetail.setUnitPrice((double)(itemValuesList.get(2)));
				Integer obj = new Integer(itemValuesList.get(3));
				purchaseOrderDetail.setQuantity(obj.shortValue());
				purchaseOrderDetail.setDiscount((float)(itemValuesList.get(4)));
				purchaseOrderDetail.setProductID(itemValuesList.get(5));
				
				for (int j = 0; j < productList.size(); j++) {
					int productID = productList.get(j).getProductID();
					if (productID == purchaseOrderDetail.getProductID()) {
						purchaseOrderDetail.setProduct(productList.get(j));
						break;
					}
				}
				purchaseOrderDetail.setInternalOrderID(Util.createID());
			}
			purchaseOrderDetailList.add(purchaseOrderDetail);
			purchaseOrderDetail = null;
		}
		return purchaseOrderDetailList;
	}	
}
