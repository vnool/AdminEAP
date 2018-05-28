package com.app.demos.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.cnpc.framework.base.entity.Dict;
import com.cnpc.framework.utils.StrUtil;
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

/**
 * 百问百答管理控制器
 * 
 * @author jrn 2018-05-27 23:54:44由代码生成器自动生成
 */
@Controller
@RequestMapping("/articles")
public class ArticlesController {

	@Resource
	private BaseService baseService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list() {
		return "demos/articles_list";
	}

	@RefreshCSRFToken
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(String id, HttpServletRequest request) {
		request.setAttribute("id", id);
		return "demos/articles_edit";
	}

	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public String detail(String id, HttpServletRequest request) {
		request.setAttribute("id", id);
		return "demos/articles_detail";
	}

	@RequestMapping(value = "/get/{id}", method = RequestMethod.POST)
	@ResponseBody
	public Articles get(@PathVariable("id") String id) {
		return baseService.get(Articles.class, id);
	}

	@VerifyCSRFToken
	@RequestMapping(value = "/save")
	@ResponseBody
	public Result save(Articles articles) {
		if (StrUtil.isEmpty(articles.getId())) {
			baseService.save(articles);
		} else {
			articles.setUpdateDateTime(new Date());
			baseService.update(articles);
		}
		return new Result(true);
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	@ResponseBody
	public Result delete(@PathVariable("id") String id) {
		Articles articles = this.get(id);
		try {
			baseService.delete(articles);
			return new Result();
		} catch (Exception e) {
			return new Result(false, "该数据已经被引用，不可删除");
		}
	}

	@Resource
	QueryController QueryCtrl;
	@RequestMapping(value = "/datalist")
	@ResponseBody
	public Map<String, Object> getDataList(String reqObj) {

		try {
			Map<String, Object> data = QueryCtrl.loadData(reqObj);
			data.get("");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new HashMap<String, Object>();
	}

}
