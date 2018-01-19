package com.ittr.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.impl.client.DefaultHttpClient;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.ittr.datas.Invoice.Invoice;
public class Connection {

	public static HttpResponse getResponse(String url, DefaultHttpClient httpClient) throws IOException {
		try {

			HttpGet httpGet = new HttpGet(url);
			httpGet.addHeader("accept", "application/json");
			HttpResponse response = httpClient.execute(httpGet);
			return response;
		} catch (IOException e) {
			throw e;
		}
	}

	public static String readData(HttpResponse response) throws Exception {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(
					response.getEntity().getContent()));
			StringBuffer data = new StringBuffer();
			char[] dataLength = new char[1024];
			int read;
			while (((read = reader.read(dataLength)) != -1)) {
				data.append(dataLength, 0, read);
			}
			return data.toString();
		} finally {
			if (reader != null)
				reader.close();
		}
	}

//	public OrderDataset getOrder() throws Exception {
//
//		DefaultHttpClient httpClient = new DefaultHttpClient();
//		HttpResponse responseForOrder = getResponse(northwindStartURl+responsforOrderString
//				, httpClient);
//		String outPut = readData(responseForOrder);
//		Gson gson = new Gson();
//		OrderDataset order = gson.fromJson(outPut, OrderDataset.class);
//		return order;
//	}
//
//	public List<Invoice> getInvoice(OrderDataset order) throws Exception {
//
//		DefaultHttpClient httpClient = new DefaultHttpClient();
//		String startUrl = northwindStartURl+responseForInvoiceString;
//		int serverResponseSize = 50;
//		for(int i = 0 ; i < serverResponseSize; i++) {
//			if( i == serverResponseSize-1 ) {
//				startUrl = startUrl+filter+order.d.results.get(i).getOrderID();
//				HttpResponse responseForInvoice = getResponse(startUrl, httpClient);
//				String outPut2 = readData(responseForInvoice);
//				Gson gson = new Gson();
//				InvoiceDataset invoice = gson.fromJson(outPut2, InvoiceDataset.class);
//				invoiceList.addAll(invoice.d.results);
//				if(serverResponseSize < order.d.results.size()) {
//					serverResponseSize = serverResponseSize + 50;
//					startUrl =northwindStartURl+responseForInvoiceString;
//				}
//			} else {
//				startUrl = startUrl+filter+getOrder().d.results.get(i).getOrderID()+or;
//			}
//		}
//		return invoiceList;
//	}
//
//	public void setInvoiceInOrderAndOrderInInvoice(OrderDataset order, List<Invoice> invoiceListt) throws Exception {
//
//		for(int i = 0 ; i < order.d.results.size(); i++) {
//			int orderID = order.d.results.get(i).getOrderID();
//
//			for (int j = 0 ; j < invoiceListt.size(); j++) {
//				int invoiceOrderId = invoiceListt.get(j).getOrderID();
//
//				if(orderID == invoiceOrderId) {
//					order.d.results.get(i).setInvoice(invoiceListt.get(j));
//					invoiceListt.get(j).setOrder(order.d.results.get(i));
//				}
//			}
//		}
//	}
}
