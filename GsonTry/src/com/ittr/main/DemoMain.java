package com.ittr.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.impl.client.DefaultHttpClient;
import com.google.gson.Gson;
import com.ittr.datas.Invoice.Invoice;
import com.ittr.datas.Invoice.InvoiceDataset;
import com.ittr.datas.PurchaseOrder.PurchaseOrder;
import com.ittr.datas.SalesOrder.SalesOrderDataset;
import com.ittr.services.Connection;
import com.ittr.util.Util;

public class DemoMain {

	public static void main(String[] args) throws IOException, Exception {

		// PurchaseOrder Operations
		PurchaseOrder purchaseOrder = new PurchaseOrder();
		List<PurchaseOrder> purchaseOrderList = new ArrayList<>();
		purchaseOrderList = purchaseOrder.setPurchaseOrderDetail(purchaseOrder);

		// SalesOrder Operations
		DefaultHttpClient httpClient = null;
		try {
			List<Invoice> invoiceListForSalesOrder = new ArrayList<>();

			//Order��in T�m verileri al�n�yor
			httpClient = new DefaultHttpClient();
			Connection connection = new Connection();
			HttpResponse responseForOrder = connection.getResponse(Util.orderUrl, httpClient);
			String responseStringforOrder = connection.readData(responseForOrder);
			Gson gson = new Gson();

			// PurchaseOrder ve SalesOrder i�in ayn� veriler kullan�yor bu y�zden ayn� response ile gson classlara oturtuyor
			SalesOrderDataset salesOrder = gson.fromJson(responseStringforOrder, SalesOrderDataset.class);
			//PurchaseOrderDataset purchaseOrder = gson.fromJson(responseStringforOrder, PurchaseOrderDataset.class);

			//Invoice i�in t�m verileri al�n�r invoiceListForSalesOrder listesine ekleniyor 
			String startUrl = Util.invoiceUrl;
			int serverResponseSize = 50;
			for(int i = 0 ; i<serverResponseSize; i++) {

				if( i == serverResponseSize-1 ) {
					startUrl = startUrl+Util.filter+salesOrder.d.results.get(i).getOrderID();

					HttpResponse responseForInvoice = connection.getResponse(startUrl, httpClient);
					String responseStringForInvoce = connection.readData(responseForInvoice);
					InvoiceDataset invoice = gson.fromJson(responseStringForInvoce, InvoiceDataset.class);
					invoiceListForSalesOrder.addAll(invoice.d.results);
					if(serverResponseSize < salesOrder.d.results.size()) {
						serverResponseSize = serverResponseSize + 50;
						startUrl =Util.invoiceUrl;
					}
				} else {
					startUrl = startUrl+Util.filter+salesOrder.d.results.get(i).getOrderID()+Util.or;
				}
			}

			//SalesOrder i�erisine invoice ve invoice i�erisine SalesOrder set etme i�lemleri
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

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			httpClient.getConnectionManager().shutdown();
		}
	}

}
