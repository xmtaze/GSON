package com.ittr.main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.impl.client.DefaultHttpClient;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.ittr.datas.Invoice.Invoice;
import com.ittr.datas.Invoice.InvoiceDataset;
import com.ittr.datas.PurchaseOrder.PurchaseOrderData;
import com.ittr.datas.PurchaseOrder.PurchaseOrderDataset;
import com.ittr.datas.SalesOrder.SalesOrderDataset;
import com.ittr.services.Connection;
import com.ittr.util.Util;

public class DemoMain {

	public static void main(String[] args) {

		DefaultHttpClient httpClient = null;
		try {
			List<Invoice> invoiceListForSalesOrder = new ArrayList<>();
			List<Invoice> invoiceListForPurchaseOrder = new ArrayList<>();

			//OrderÝçin Tüm verileri alýnýyor
			httpClient = new DefaultHttpClient();
			HttpResponse responseForOrder = Connection.getResponse(Util.orderUrl
					, httpClient);
			String responseStringforOrder = Connection.readData(responseForOrder);
			Gson gson = new Gson();

			// PurchaseOrder ve SalesOrder için ayný veriler kullanýyor bu yüzden ayný response ile gson classlara oturtuyor
			SalesOrderDataset salesOrder = gson.fromJson(responseStringforOrder, SalesOrderDataset.class);
			PurchaseOrderDataset purchaseOrder = gson.fromJson(responseStringforOrder, PurchaseOrderDataset.class);

			//Invoice için tüm verileri alýnýr SalesOrder ve PurchaseOrder için iki ayrý listeye eklenir.
			String startUrl = Util.invoiceUrl;
			int serverResponseSize = 50;
			for(int i = 0 ; i<serverResponseSize; i++) {

				if( i == serverResponseSize-1 ) {
					startUrl = startUrl+Util.filter+salesOrder.d.results.get(i).getOrderID();

					HttpResponse responseForInvoice = Connection.getResponse(startUrl, httpClient);
					String responseStringForInvoce = Connection.readData(responseForInvoice);
					InvoiceDataset invoice = gson.fromJson(responseStringForInvoce, InvoiceDataset.class);
					invoiceListForSalesOrder.addAll(invoice.d.results);
					invoiceListForPurchaseOrder.addAll(invoice.d.results);
					if(serverResponseSize < salesOrder.d.results.size()) {
						serverResponseSize = serverResponseSize + 50;
						startUrl =Util.invoiceUrl;
					}
				} else {
					startUrl = startUrl+Util.filter+salesOrder.d.results.get(i).getOrderID()+Util.or;
				}
			}
			
			//List<Invoice> invoiceListForPurchaseOrder = createDeepCopyOfArrayList(invoiceListForSalesOrder);
			
			
			//SalesOrder içerisine invoice ve invoice içerisine SalesOrder set etme iþlemleri.
			for(int i = 0 ; i < salesOrder.d.results.size(); i++) {
				int orderId = salesOrder.d.results.get(i).getOrderID();
				for(int j = 0 ; j < invoiceListForSalesOrder.size(); j++) {
					int invoiceOrderId = invoiceListForSalesOrder.get(j).getOrderID();
					if(orderId == invoiceOrderId) {
						if(salesOrder.d.results.get(i).getTypeCode() == 1) {
							salesOrder.d.results.get(i).setInvoice(invoiceListForSalesOrder.get(j));
							invoiceListForSalesOrder.get(j).setOrder(salesOrder.d.results.get(i));
						}
					}				
				}
			}
			//SalesOrder içerisine invoice ve invoice içerisine SalesOrder set etme iþlemleri.
			for(int i = 0 ; i < purchaseOrder.d.results.size() ; i++) {
				int orderId = purchaseOrder.d.results.get(i).getOrderID();
				for(int j = 0 ; j < invoiceListForPurchaseOrder.size() ; j++) {
					int invoiceOrderId = invoiceListForPurchaseOrder.get(j).getOrderID();
					if(orderId == invoiceOrderId) {
						if(purchaseOrder.d.results.get(i).getTypeCode() == 2) {
							purchaseOrder.d.results.get(i).setInvoice(invoiceListForPurchaseOrder.get(j));
							invoiceListForPurchaseOrder.get(j).setOrder(purchaseOrder.d.results.get(i));
						}
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			httpClient.getConnectionManager().shutdown();
		}
	}
	
	// Elimizde var olan listenin deep copysini yaratmaya çalýþýyoruz.
	public static List<Invoice> createDeepCopyOfArrayList(final List<Invoice> list) {	
		ArrayList copyList = new ArrayList<>(list);
		return copyList;
	}
	
	
	
}
