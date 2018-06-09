package com.app.demos.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cnpc.framework.base.controller.UploaderController;
import com.cnpc.framework.base.entity.Dict;
import com.cnpc.framework.utils.StrUtil;

import org.hsqldb.lib.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnpc.framework.base.service.BaseService;
import com.cnpc.framework.query.controller.QueryController;
import com.cnpc.framework.query.service.QueryService;
import com.cnpc.framework.annotation.RefreshCSRFToken;
import com.cnpc.framework.annotation.VerifyCSRFToken;
import com.cnpc.framework.base.pojo.Result;
import com.app.demos.entity.Articles;
import com.alibaba.fastjson.JSON;

/**
 * 百问百答管理控制器
 * 
 * @author jrn 2018-05-27 23:54:44由代码生成器自动生成
 */
@Controller
@RequestMapping("/nc")
public class ArticlesMobileController {

	@Resource
	private QueryService queryService;

	@RequestMapping(value = "/article/{postId}/full-html", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getdetail(@PathVariable String postId, HttpServletRequest request) {
		String baseurl = UploaderController.getBaseURL(request);

		Articles art = queryService.get(Articles.class, postId);
		JSONObject m = (JSONObject) JSON.toJSON(art);
		m.put("shareLink", baseurl + "xxxx");
		m.put("body", m.get("body").toString().replaceAll("\n", "<br>"));
		String mtime = QueryConditions.dateString((Date) m.get("lmodify"));
		m.put("ptime", mtime);

		String imgextra = (String) m.get("imgextra");
		String imgsrc = (String) m.get("imgsrc");
		JSONArray imgextraList = new JSONArray();
		if (StringUtil.isEmpty(imgextra)) {
			imgextra = imgsrc;
		} else if (!StringUtil.isEmpty(imgsrc)) {
			imgextra = imgsrc.concat(",").concat(imgextra);
		}
		if (!StringUtil.isEmpty(imgextra)) {

			String[] imglist = imgextra.trim().split(",");
			int i = 0;
			for (String img : imglist) {
				img = img.trim();
				if (StringUtil.isEmpty(img))
					continue;
				String imgurl = UploaderController.getFileURL(img, request);
				JSONObject o = new JSONObject();
				o.put("ref", "<!--IMG#" + i + "-->");
				i++;
				o.put("src", imgurl);
				imgextraList.add(o);
			}

		}
		m.put("img", imgextraList);
		m.remove("imgextra");

		JSONObject mo = new JSONObject();
		mo.put(postId, m); // 加入深一层
		return mo;
	}

	@RequestMapping(value = "/article/{products}/{scope}/{startPage}-{pageSize}-html", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getlist(@PathVariable String products, @PathVariable String scope,
			@PathVariable int startPage, @PathVariable int pageSize, HttpServletRequest request) {

		String reqObj = QueryConditions.QueryString("articles",products, scope, startPage, pageSize);

		Map<String, Object> data;
		try {
			data = queryService.loadData(reqObj);
			data.put(scope, PureData((ArrayList<Articles>) data.get("rows"), request));
			// data.put(id, data.get("rows"));
			data.remove("rows");
			data.remove("query");
			data.remove("pageInfo");
			return data;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new HashMap<String, Object>();
	}

	ArrayList<Object> PureData(ArrayList<Articles> articlelist, HttpServletRequest request) {
		ArrayList<Object> list = new ArrayList<Object>();
		for (Articles art : articlelist) {
			JSONObject m = (JSONObject) JSON.toJSON(art);
			String mtime = QueryConditions.dateString((Date) m.get("lmodify"));
			m.put("lmodify", mtime);
			m.put("mtime", mtime);
			m.put("ptime", mtime);
			m.put("postid", m.get("id"));
			String body = m.getString("body");
			if (m.getString("digest").length() < 3 && !StringUtil.isEmpty(body)) {
				m.put("digest", body.substring(0, 20));
			}
			m.remove("body");

			String imgsrc = (String) m.get("imgsrc");
			if (!StringUtil.isEmpty(imgsrc)) {
				imgsrc = UploaderController.getFileURL(imgsrc, request);
			}
			m.put("imgsrc", imgsrc);

			String imgextra = (String) m.get("imgextra");
			if (!StringUtil.isEmpty(imgextra)) {
				JSONArray imgextraList = new JSONArray();
				String[] imglist = imgextra.trim().split(",");
				for (String img : imglist) {
					String imgurl = UploaderController.getFileURL(img, request);
					JSONObject o = new JSONObject();
					o.put("imgsrc", imgurl);
					imgextraList.add(o);
				}

				m.put("imgextra", imgextraList);
			}

			list.add(m);
		}
		return list;
	}


}
