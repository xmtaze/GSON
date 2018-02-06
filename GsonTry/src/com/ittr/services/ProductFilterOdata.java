package com.ittr.services;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.impl.client.DefaultHttpClient;
import com.google.gson.Gson;
import com.ittr.datas.Product.Product;
import com.ittr.datas.Product.ProductDataSet;
import com.ittr.util.Util;

public class ProductFilterOdata {

    public static List<Product> productFilterOData() throws Exception {
       
    	// Tüm productlar servisten çekilir ve listeye eklenir
    	List<Product> productList = new ArrayList<Product>();
        DefaultHttpClient httpClient = null;
            Connection connection = new Connection();
            httpClient = new DefaultHttpClient();
            HttpResponse httpResponse = connection.getResponse(Util.northwindStartURl + Util.filterForProduct , httpClient);
            String responseFromJson = connection.readData(httpResponse);
            Gson gson = new Gson();
            ProductDataSet product = gson.fromJson(responseFromJson, ProductDataSet.class);
            productList.addAll(product.d.results);
            return productList;    
    }
}

