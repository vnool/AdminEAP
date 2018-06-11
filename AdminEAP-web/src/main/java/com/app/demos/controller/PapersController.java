package com.app.demos.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cnpc.framework.base.entity.Dict;
import com.cnpc.framework.utils.StrUtil;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnpc.framework.base.service.BaseService;
import com.cnpc.framework.query.service.QueryService;
import com.cnpc.framework.annotation.RefreshCSRFToken;
import com.cnpc.framework.annotation.VerifyCSRFToken;
import com.cnpc.framework.base.pojo.Result;
import com.app.demos.entity.Papers;

/**
 * 试卷管理控制器
 * 
 * @author jrn 2018-06-09 15:14:56由代码生成器自动生成
 */
@Controller
@RequestMapping("/papers")
public class PapersController {

	@Resource
	private QueryService queryService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list() {
		return "demos/papers_list";
	}
	@RequestMapping(value = "/listmobile", method = RequestMethod.GET)
	public String mobilelist() {
		return "demos/papers_listmobile";
	}
	@RefreshCSRFToken
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(String id, HttpServletRequest request) {
		request.setAttribute("id", id);
		return "demos/papers_edit";
	}

	@RefreshCSRFToken
	@RequestMapping(value = "/showpaper", method = RequestMethod.GET)
	public String showpaper(String id, HttpServletRequest request) {
		request.setAttribute("id", id);
		return "demos/papers_showpaper";
	}

	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public String detail(String id, HttpServletRequest request) {
		request.setAttribute("id", id);
		return "demos/papers_detail";
	}

	@RequestMapping(value = "/get/{id}")
	@ResponseBody
	public Papers get(@PathVariable("id") String id) {
		return queryService.get(Papers.class, id);
	}

	
	
	@RequestMapping(value = "/pagelist/{product}/{start}-{size}")
	@ResponseBody
	public Map<String, Object> pagelist(@PathVariable("product") String product, @PathVariable("start") int startPage,
			@PathVariable("size") int pageSize) {
		String qs = QueryString(product, startPage, pageSize);
		try {
			 Map<String, Object> data= queryService.loadData(qs);
			 List rows = (List) data.get("rows");
			 
			 List listX = new ArrayList();;
			 for(int i=0; i<rows.size();i++){
				  Papers paper = (Papers) rows.get(i);
				  Map<String,String> o = new HashMap<String,String>();
				  o.put("title", paper.getTitle());
				  o.put("id", paper.getId());
				  o.put("product", paper.getProduct().getName());
				  listX.add(o);
			 }
			
			 HashMap<String,Object> rlt = new HashMap<String,Object>();
			 rlt.put("rows", listX);
			 return rlt;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/loadData/{product}/{start}-{size}")
	@ResponseBody
	public Map<String, Object> loadData(@PathVariable("product") String product, @PathVariable("start") int startPage,
			@PathVariable("size") int pageSize) {
		String qs = QueryString(product, startPage, pageSize);
		try {
			return queryService.loadData(qs);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/loadData")
	@ResponseBody
	public Map<String, Object> loadData(String obj) {
		try {
			return queryService.loadData(obj);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static String QueryString(String product, int startPage, int pageSize) {
		// String reqObj =
		// "{\"queryId\":\"articles\",\"pageInfo\":null,\"query\":null,\"conditions\":[]}";
		JSONObject reqJs = new JSONObject();
		reqJs.put("queryId", "papers");

		JSONArray conditions = new JSONArray();

		// JSONObject sp = new JSONObject();
		// sp.put("key", "scope.code");
		// sp.put("value", scope);
		// sp.put("isCondition", true);
		// conditions.add(sp);

		if (!StringUtils.isEmpty(product) && !product.equals("ALL")) { // 非all
			JSONObject pdjs = new JSONObject();
			pdjs.put("key", "product.code");
			pdjs.put("value", product);
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

	// @VerifyCSRFToken
	@RequestMapping(value = "/save")
	@ResponseBody
	public Result save(String obj) {
		Papers papers = JSON.parseObject(obj, Papers.class);
		if (papers.getProduct() != null) {
			papers.setProduct(queryService.get(Dict.class, papers.getProduct().getId()));
		}

		if (StrUtil.isEmpty(papers.getId())) {
			queryService.save(papers);
		} else {
			papers.setUpdateDateTime(new Date());
			queryService.update(papers);
		}
		return new Result(true);
	}

	// @VerifyCSRFToken
	@RequestMapping(value = "/savepaper")
	@ResponseBody
	public Result savepaper(String obj) {
		Papers papers = JSON.parseObject(obj, Papers.class);

		HashMap<String, Object> p = new HashMap<String, Object>();
		p.put("id", papers.getId());
		p.put("pages", papers.getPages());
		p.put("update_date_time", new Date());

		queryService.updatePart(Papers.class, p);

		return new Result(true);
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	@ResponseBody
	public Result delete(@PathVariable("id") String id) {
		Papers papers = this.get(id);
		try {
			queryService.delete(papers);
			return new Result();
		} catch (Exception e) {
			return new Result(false, "该数据已经被引用，不可删除");
		}
	}

}
