package com.app.demos.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
 
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;


public class QueryConditions{

	public static String QueryString(String MediaType, String products, String scope, int startPage, int pageSize) {
		// String reqObj =
		// "{\"queryId\":\"articles\",\"pageInfo\":null,\"query\":null,\"conditions\":[]}";
		JSONObject reqJs = new JSONObject();
		reqJs.put("queryId", MediaType);
		JSONArray conditions = new JSONArray();
		JSONObject sp = new JSONObject();
		sp.put("key", "scope.code");
		sp.put("value", scope);
		sp.put("isCondition", true);
		conditions.add(sp);

		if (!products.equals("ALL")) { // 非all
			JSONObject pdjs = new JSONObject();
			pdjs.put("key", "product.code");
			pdjs.put("value", products);
			pdjs.put("isCondition", true);
			pdjs.put("operator", "in");
			conditions.add(pdjs);
		}

		reqJs.put("conditions", conditions);

		JSONObject page = new JSONObject();
		page.put("pageNum", startPage / pageSize + 1);
		page.put("pageSize", pageSize);
		reqJs.put("pageInfo", page);

		return reqJs.toJSONString();

	}

	public static String dateString(Date object) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String dateString = formatter.format(object);
		return dateString;
	}

}