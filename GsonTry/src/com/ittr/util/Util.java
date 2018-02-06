package com.ittr.util;

import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

public class Util {

	public static String responsforOrderString = "Orders?$expand=Order_Details/Product";
	public static String northwindStartURl = "http://services.odata.org/V2/Northwind/Northwind.svc/";
	public static String responseForInvoiceString = "Invoices?$filter=";
	public static String filter = "OrderID%20eq%20";
	public static String or = "%20or%20";
	public static String orderUrl = northwindStartURl + responsforOrderString;
	public static String invoiceUrl = northwindStartURl +responseForInvoiceString;
	
	
	public static final String filterWithOrderID = "?$filter=OrderID%20eq%20";
    public static final String filterForProduct = "Products";
    public static final String filterWithProductID = "ProductID%20eq%20";
    private static AtomicLong idCounter = new AtomicLong(10000);
    
    public static  String ReadDataFromExcelPath ="src/com/ittr/PurchaseOrder.xls";

    // InternalOrderID Generate etmek için kullanılan fonksiyon
    public static synchronized long createID() {
        long lastvalue = idCounter.getAndIncrement();
        return lastvalue;
    }
	
}
