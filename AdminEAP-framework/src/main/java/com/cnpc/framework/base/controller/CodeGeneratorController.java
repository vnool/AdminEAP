package com.cnpc.framework.base.controller;

import com.alibaba.fastjson.JSON;
import com.cnpc.framework.annotation.Header;
import com.cnpc.framework.base.entity.Function;
import com.cnpc.framework.base.pojo.FieldSetting;
import com.cnpc.framework.base.pojo.GenerateSetting;
import com.cnpc.framework.base.pojo.Result;
import com.cnpc.framework.base.service.CodeGeneratorService;
import com.cnpc.framework.query.entity.Query;
import com.cnpc.framework.query.pojo.QueryDefinition;
import com.cnpc.framework.utils.DateUtil;
import com.cnpc.framework.utils.FreeMarkerUtil;
import com.cnpc.framework.utils.PingYinUtil;
import com.cnpc.framework.utils.StrUtil;
import freemarker.template.TemplateException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.cnpc.framework.query.pojo.QueryXMLmaker;
import com.cnpc.framework.base.pojo.EntityInfo;

/**
 * Created by billJiang on 2017/2/6. e-mail:jrn1012@petrochina.com.cn
 * qq:475572229 代码生成器控制类
 */
@Controller
@RequestMapping(value = "/generator")
public class CodeGeneratorController {

	@Resource
	private CodeGeneratorService codeGeneratorService;

	@RequestMapping(value = "/setting", method = RequestMethod.GET)
	public String setting(HttpServletRequest request) {
		request.setAttribute("rootPath", request.getRealPath("/"));
		// request.setAttribute("rootPath", "xxxxx");
		return "tool/generator/generator_setting";
	}

	@RequestMapping(value = "/getQuerySetting", method = RequestMethod.POST)
	@ResponseBody
	public GenerateSetting getQuerySetting(String queryId, HttpServletRequest request) throws ClassNotFoundException {
		GenerateSetting setting = new GenerateSetting();
		String realPath = request.getRealPath("/");
		if (StrUtil.isEmpty(realPath)) {
			System.out.println("realPath is null:请在开发环境中使用该功能");
			setting.setIsExistQuery("2");
			return setting;
		}

		Query query = QueryDefinition.getQueryById(queryId);
		if (query == null) {
			setting.setIsExistQuery("0");
			return setting;
		}

		String className = query.getClassName();
		// 从className 获取
		setting = EntityInfo.entityInfo(className, request);
		setting.setIsExistQuery("1");
		return setting;

	}


	@RequestMapping(value = "/xml", method = RequestMethod.GET)
	public String configXML(String queryId, String className, String modelName, HttpServletRequest request) {
		request.setAttribute("queryId", queryId);
		request.setAttribute("className", className);
		request.setAttribute("modelName", modelName);
		return "tool/generator/generator_xml";
	}

	// 生成xml配置文件
	@RequestMapping(value = "generateXMLFile", method = RequestMethod.POST)
	@ResponseBody
	public Result generateXMLFile(String xmlContent, String xmlFile, HttpServletRequest request) {
		String xmlPath = request.getRealPath("/").replaceAll("webapp", "resources") + File.separator + "query"
				+ File.separator + xmlFile;
		return QueryXMLmaker.generateXMLFile(xmlContent, xmlPath );
	}

	// 生成xml配置内容
	@RequestMapping(value = "/getXMLContent", method = RequestMethod.POST)
	@ResponseBody
	public Result XMLContent( String className) throws ClassNotFoundException {
		return QueryXMLmaker.XMLContentfromCls( className );
	}

	// 代码生成 快速版
	@RequestMapping(value = "startGenerateQuick", method = RequestMethod.POST)
	@ResponseBody
	public Result startGenerateQuick(  String className, HttpServletRequest request ) throws IOException, TemplateException, ClassNotFoundException {
	 

		GenerateSetting s = EntityInfo.entityInfo(className,   request);
 		makeXMLappend(s, request);
     	s.setAllFields(s.getFields());
       
		
		return GenerateCodeDo(s);
	}

	// 代码生成
	@RequestMapping(value = "startGenerate", method = RequestMethod.POST)
	@ResponseBody
	public Result startGenerate(String object, HttpServletRequest request)
			throws IOException, TemplateException, ClassNotFoundException {
		GenerateSetting setting = JSON.parseObject(object, GenerateSetting.class);
		// setting.setHtmlPrefix();

		String className = setting.getClassName();
		
		return startGenerateQuick(className, request);
 	
	}

	public Result GenerateCodeDo(GenerateSetting setting) throws IOException, TemplateException {
		setting.setCurTime(new Date());

		// 创建功能菜单
		if ("1".equals(setting.getIsCreateFunction()) && !StrUtil.isEmpty(setting.getParFuncCode())) {
			// 获取父节点对象
			String hql = "from Function where code='" + setting.getParFuncCode() + "'";
			Function parFunc = codeGeneratorService.get(hql);
			if (parFunc != null) { // 如果parent.code已经存在
				setting.setParFuncName(parFunc.getName());
				String code = setting.getJavaPrefix().toUpperCase();
				Function function = codeGeneratorService.get("from Function where code='" + code + "'");
				if (function == null) {
					function = new Function();
					function.setCode(code);
					function.setParentId(parFunc.getId());
					function.setLevelCode(codeGeneratorService.getLevelCode(Function.class.getName(), parFunc.getId()));
					function.setFunctype("1");
					// function.setUrl("/" + setting.getBusinessPackage() + "/"
					// + setting.getHtmlPrefix() + "/list");
					function.setUrl("/" + setting.getHtmlPrefix() + "/list");
					function.setDeleted(1);
					function.setCreateDateTime(new Date());
					function.setUpdateDateTime(new Date());
					function.setName(setting.getModelName() + "管理");
					function.setIcon("fa fa-edit");
					function.setRemark(
							"本功能菜单为代码生成器生成，时间：" + DateUtil.format(new Date(), DateUtil.formatStr_yyyyMMddHHmmss));
				}
				codeGeneratorService.saveOrUpdate(function);
			}
		}

		makeHTMLCode(setting);

		makeJavaCode(setting);

		return new Result();
	}

	void makeXMLappend(GenerateSetting setting,  HttpServletRequest request)
			throws ClassNotFoundException {
		//String queryId = setting.getQueryId();
		String className = setting.getClassName();
			Result result = XMLContent( className);
		String XMLContent = result.getMessage();
		
		//String modelName = setting.getModelName();
		String nameSpace = setting.getNameSpace();
		String xmlFile = nameSpace+".xml";
	

		generateXMLFile(XMLContent, xmlFile, request);
	}

	void makeHTMLCode(GenerateSetting setting) throws IOException, TemplateException {
		// html文件生成
		String[] htmlArr;
		String fileName;
		List<FieldSetting> selectFields = new ArrayList<>();
		List<FieldSetting> leftFields = new ArrayList<>();
		for (FieldSetting fs : setting.getAllFields()) {
			if ("1".equals(fs.getIsSelected())) {
				selectFields.add(fs);
			} else {
				leftFields.add(fs);
			}
		}

		if (!StrUtil.isEmpty(setting.getHtmlTypes())) {
			htmlArr = setting.getHtmlTypes().split(",");
			setting.setFields(selectFields);
			for (String htmlType : htmlArr) {
				if ("list".equals(htmlType)) {
					setting.setTitle(setting.getModelName() + "管理");
					fileName = setting.getHtmlPrefix() + "_list.html";
					FreeMarkerUtil.generateFile("list.html", setting.getHtmlPath() + File.separator + fileName,
							setting);
				} else if (htmlType.equals("addUpdate")) {
					// System.out.println(FreeMarkerUtil.getTemplatePath("addUpdate.html"));
					setting.setTitle("新增/编辑" + setting.getModelName());
					fileName = setting.getHtmlPrefix() + "_edit.html";
					FreeMarkerUtil.generateFile("edit.html", setting.getHtmlPath() + File.separator + fileName,
							setting);
				} else if (htmlType.equals("add")) {
					setting.setTitle("新增" + setting.getModelName());
					fileName = setting.getHtmlPrefix() + "_add.html";
					FreeMarkerUtil.generateFile("add.html", setting.getHtmlPath() + File.separator + fileName, setting);
				} else if (htmlType.equals("update")) {
					setting.setTitle("编辑" + setting.getModelName());
					fileName = setting.getHtmlPrefix() + "_update.html";
					FreeMarkerUtil.generateFile("update.jsp", setting.getHtmlPath() + File.separator + fileName,
							setting);
				} else if (htmlType.equals("show")) {
					setting.setTitle("查看" + setting.getModelName());
					fileName = setting.getHtmlPrefix() + "_detail.html";
					List<FieldSetting> fields = setting.getAllFields();
					for (FieldSetting fs : fields) {
						fs.setColumnName(fs.getColumnName().replaceAll(".id", ".name"));
					}
					List<FieldSetting> field_list = setting.getFields();
					setting.setFields(fields);
					FreeMarkerUtil.generateFile("detail.html", setting.getHtmlPath() + File.separator + fileName,
							setting);
					setting.setFields(field_list);
				}
			}
		}

	}

	void makeJavaCode(GenerateSetting setting) throws IOException, TemplateException {
		// 生成java后台文件
		String fileName;
		String[] javaArr;
		List<FieldSetting> selectFields = new ArrayList<>();
		List<FieldSetting> leftFields = new ArrayList<>();
		for (FieldSetting fs : setting.getAllFields()) {
			if ("1".equals(fs.getIsSelected())) {
				selectFields.add(fs);
			} else {
				leftFields.add(fs);
			}
		}

		if (!StrUtil.isEmpty(setting.getJavaTypes())) {
			javaArr = setting.getJavaTypes().split(",");
			for (String javaType : javaArr) {
				if ("controller".equals(javaType)) {
					// ---------------------新增/更新丢掉的属性-----------------
					List<String> dateFields = new ArrayList<String>();
					List<String> leftDates = new ArrayList<String>();
					List<String> leftList = new ArrayList<String>();
					if (leftFields.size() > 0) {
						// 丢掉的日期类型 dateFields 设置值 leftDates 更新值
						for (FieldSetting fs : leftFields) {
							if (fs.getType().equals("Date")) {
								String str_add = setting.getHtmlPrefix() + "." + "set"
										+ fs.getColumnName().substring(0, 1).toUpperCase()
										+ fs.getColumnName().substring(1, fs.getColumnName().length()) + "(new Date())";
								dateFields.add(str_add);
								String str_update = setting.getHtmlPrefix() + "_old." + "set"
										+ fs.getColumnName().substring(0, 1).toUpperCase()
										+ fs.getColumnName().substring(1, fs.getColumnName().length()) + "("
										+ setting.getHtmlPrefix() + ".get"
										+ fs.getColumnName().substring(0, 1).toUpperCase()
										+ fs.getColumnName().substring(1, fs.getColumnName().length()) + "())";
								leftDates.add(str_update);
							}
							leftList.add(fs.getColumnName());
						}
						setting.setDateFields(dateFields);
						setting.setLeftDates(leftDates);
						setting.setLeftFields(leftList);
					}
					// ------------------------------------------------------------
					setting.setTitle(setting.getModelName() + "管理控制器");
					fileName = setting.getJavaPrefix() + "Controller.java";
					FreeMarkerUtil.generateFile("controller.html",
							setting.getJavaPath() + File.separator + "controller" + File.separator + fileName, setting);
				} else if ("service".equals(javaType)) {
					// 接口
					setting.setTitle(setting.getModelName() + "服务接口");
					fileName = setting.getJavaPrefix() + "Service.java";
					FreeMarkerUtil.generateFile("service.html",
							setting.getJavaPath() + File.separator + "service" + File.separator + fileName, setting);
					// 实现
					setting.setTitle(setting.getModelName() + "服务实现");
					fileName = setting.getJavaPrefix() + "ServiceImpl.java";
					FreeMarkerUtil.generateFile("serviceImpl.html",
							setting.getJavaPath() + File.separator + "service" + File.separator + fileName, setting);
				}
			}
		}
	}
}
