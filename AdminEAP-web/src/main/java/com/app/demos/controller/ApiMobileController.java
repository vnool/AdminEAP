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
public class ApiMobileController {

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list() {
		return "demos/articles_list";
	}

	@RequestMapping(value = "/article/{type}/{id}/{startPage}-{pageSize}-html", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getlist(@PathVariable String type, @PathVariable String id,
			@PathVariable String startPage, @PathVariable String pageSize, HttpServletRequest request) {
		String baseurl = "http://" + request.getServerName() // 服务器地址
				+ ":" + request.getServerPort() // 端口号
				+ request.getContextPath(); // 项目名称
		// + request.getServletPath() //请求页面或其他地址
		// + "?" + (request.getQueryString()); //参数

		String reqObj = "{\"queryId\":\"articles\",\"pageInfo\":null,\"query\":null,\"conditions\":[]}";
		Map<String, Object> data;
		try {
			data = QueryCtrl.loadData(reqObj);

			data.put(id, PureData((ArrayList<Articles>) data.get("rows"), request));
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
			String mtime = dateString((Date) m.get("lmodify"));
			m.put("lmodify", mtime);
			m.put("mtime", mtime);
			m.put("ptime", mtime);

			String imgsrc = (String) m.get("imgsrc");
			if (!StringUtil.isEmpty(imgsrc)) {
				imgsrc = UploaderController.getFileURL(imgsrc, request);
			}
			m.put("imgsrc", imgsrc);

			String imgextra = (String) m.get("imgextra");
			if (!StringUtil.isEmpty(imgextra)) {
				JSONArray imgextraList = new JSONArray();
				String[] imglist = imgextra.split(",");
				for (String img : imglist) {
					String imgurl = UploaderController.getFileURL(img, request);
					JSONObject o = new JSONObject();
					o.put("imgsrc", imgurl);
					imgextraList.add(o);
				}
				imgsrc = UploaderController.getFileURL(imgsrc, request);
				m.put("imgextra", imgextraList);
			}

			//
			// = JSON.parseArray(" [ { \"imgsrc\":
			// \"http://cms-bucket.nosdn.127.net/45cdbef70b354e54bd539d52fd27f01b20180528171047.png\"
			// },"
			// + "{ \"imgsrc\":
			// \"http://cms-bucket.nosdn.127.net/2018/05/28/bfe3667ab3944982b45bc3b5d7a59b0f.png\"
			// } ]");
			// m.put("imgextra", imgextra);

			list.add(m);
		}
		return list;
	}

	String dateString(Date object) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String dateString = formatter.format(object);
		return dateString;
	}

	@Resource
	QueryController QueryCtrl;

	@RequestMapping(value = "/datalist")
	@ResponseBody
	public Map<String, Object> getDataList(String reqObj) {

		try {
			Map<String, Object> data = QueryCtrl.loadData(reqObj);
			return data;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new HashMap<String, Object>();
	}

}
