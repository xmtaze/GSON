package com.ittr.main;

import java.util.ArrayList;
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
			HttpResponse responseForOrder = Connection.getResponse(Util.northwindStartURl+Util.responsforOrderString
					, httpClient);
			String responseStringforOrder = Connection.readData(responseForOrder);
			Gson gson = new Gson();

			// PurchaseOrder ve SalesOrder için ayný veriler kullanýyor bu yüzden ayný response ile gson classlara oturtuyor
			SalesOrderDataset salesOrder = gson.fromJson(responseStringforOrder, SalesOrderDataset.class);
			PurchaseOrderDataset purchaseOrder = gson.fromJson(responseStringforOrder, PurchaseOrderDataset.class);

			//Invoice için tüm verileri alýnýr
			String startUrl = Util.northwindStartURl+Util.responseForInvoiceString;
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
						startUrl =Util.northwindStartURl+Util.responseForInvoiceString;
					}

				} else {
					startUrl = startUrl+Util.filter+salesOrder.d.results.get(i).getOrderID()+Util.or;
				}
			}

			// Invoice içerisine SalesOrder, SalesOrder içerisine invoice set ediliyor
			for(int i = 0 ; i<salesOrder.d.results.size(); i++) {
				int orderID = salesOrder.d.results.get(i).getOrderID();

				for (int j = 0 ; j< invoiceListForSalesOrder.size(); j++) {
					int invoiceOrderId = invoiceListForSalesOrder.get(j).getOrderID();

					if(orderID == invoiceOrderId) {

						salesOrder.d.results.get(i).setInvoice(invoiceListForSalesOrder.get(j));
						invoiceListForSalesOrder.get(j).setOrderType(salesOrder.d.results.get(i).getTypeCode(), salesOrder.d.results.get(i));
					}
				}
			}

			// Invoice içerisine PurchaseOrder, PurchaseOrder içerisine invoice set ediliyor
			for(int i = 0 ; i<purchaseOrder.d.results.size(); i++) {
				int orderID = purchaseOrder.d.results.get(i).getOrderID();

				for (int j = 0 ; j< invoiceListForPurchaseOrder.size(); j++) {
					int invoiceOrderId = invoiceListForPurchaseOrder.get(j).getOrderID();

					if(orderID == invoiceOrderId) {

						purchaseOrder.d.results.get(i).setInvoice(invoiceListForPurchaseOrder.get(j));
						invoiceListForPurchaseOrder.get(j).setOrderType(purchaseOrder.d.results.get(i).getTypeCode(), purchaseOrder.d.results.get(i));
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			httpClient.getConnectionManager().shutdown();
		}

	}


}
