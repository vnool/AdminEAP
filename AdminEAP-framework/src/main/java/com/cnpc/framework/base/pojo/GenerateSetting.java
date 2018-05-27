package com.cnpc.framework.base.pojo;

import java.util.Date;
import java.util.List;

import org.hsqldb.lib.StringUtil;

import com.cnpc.framework.annotation.Model;

public class GenerateSetting {
	private String queryId;
	private String className;
	private String htmlTypes;
	private List<FieldSetting> fields;
	private Integer colsNum;
	private String htmlPrefix;
	private String htmlPath;
	private String title;

	private String javaTypes;
	private String javaPrefix;
	private String nameSpace;
	private String javaPath;
	private String businessPackage;

	private String modelName;

	private Boolean allowPaging;
	private String tableName;
	private Date curTime;
	private List<FieldSetting> allFields;
	private List<String> dateFields;
	private List<String> leftDates;
	private List<String> leftFields;

	// 生成时候创建菜单
	private String isCreateFunction;// 是否在生成代码的时候创建菜单 1创建菜单
	private String parFuncCode;// 父亲功能编码

	private String isExistQuery;// 是否存在query

	private String curdType;// curd类型 dialog page tab
	private String parFuncName;// 父级目录名称

	public String getParFuncName() {
		return parFuncName;
	}

	public void setParFuncName(String parFuncName) {
		this.parFuncName = parFuncName;
	}

	public String getCurdType() {
		return curdType;
	}

	public void setCurdType(String curdType) {
		this.curdType = curdType;
	}

	public String getQueryId() {
		return queryId;
	}

	public void setQueryId(String queryId) {
		this.queryId = queryId;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getHtmlTypes() {
		return htmlTypes;
	}

	public void setHtmlTypes(String htmlTypes) {
		this.htmlTypes = htmlTypes;
	}

	public List<FieldSetting> getFields() {
		return fields;
	}

	public void setFields(List<FieldSetting> fields) {
		this.fields = fields;
	}

	public Integer getColsNum() {
		return colsNum;
	}

	public void setColsNum(Integer colsNum) {
		this.colsNum = colsNum;
	}

	public String getHtmlPrefix() {
		String javaPrefix = getJavaPrefix();
		if (StringUtil.isEmpty(javaPrefix))
			return "";
		return getJavaPrefix().toLowerCase();
	}

	public String getHtmlPath() {
		return htmlPath;
	}

	public void setHtmlPath(String htmlPath) {
		this.htmlPath = htmlPath;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getJavaTypes() {
		return javaTypes;
	}

	public void setJavaTypes(String javaTypes) {
		this.javaTypes = javaTypes;
	}

	public String getJavaPrefix() {
		if (StringUtil.isEmpty(this.className))
			return "";
		int p = this.className.lastIndexOf(".");
		if (p < 1)
			return "";
		return this.className.substring(p + 1);
	}

	public void setNameSpace(String nameSpace) {
		this.nameSpace = nameSpace;
	}

	public String getJavaPath() {
		return javaPath;
	}

	public void setJavaPath(String javaPath) {
		this.javaPath = javaPath;
	}

	public String getBusinessPackage() {
		return businessPackage;
	}

	public void setBusinessPackage(String businessPackage) {
		this.businessPackage = businessPackage;
	}

	public String getModelName() {
		return modelName;
	}

	public String getNameSpace() {
		if (StringUtil.isEmpty(this.className))
			return "";

		int p = this.className.lastIndexOf(".");
		if (p < 1)
			return "";
		String entity = this.className.substring(0, p);
		p = entity.lastIndexOf(".");
		if (p < 1)
			return "";
		return this.className.substring(0, p);
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public Boolean getAllowPaging() {
		return allowPaging;
	}

	public void setAllowPaging(Boolean allowPaging) {
		this.allowPaging = allowPaging;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public Date getCurTime() {
		return curTime;
	}

	public void setCurTime(Date curTime) {
		this.curTime = curTime;
	}

	public List<FieldSetting> getAllFields() {
		return allFields;
	}

	public void setAllFields(List<FieldSetting> allFields) {
		this.allFields = allFields;
	}

	public List<String> getDateFields() {
		return dateFields;
	}

	public void setDateFields(List<String> dateFields) {
		this.dateFields = dateFields;
	}

	public List<String> getLeftDates() {
		return leftDates;
	}

	public void setLeftDates(List<String> leftDates) {
		this.leftDates = leftDates;
	}

	public List<String> getLeftFields() {
		return leftFields;
	}

	public void setLeftFields(List<String> leftFields) {
		this.leftFields = leftFields;
	}

	public String getIsCreateFunction() {
		return isCreateFunction;
	}

	public void setIsCreateFunction(String isCreateFunction) {
		this.isCreateFunction = isCreateFunction;
	}

	public String getParFuncCode() {
		if (StringUtil.isEmpty(this.className))
			return "";

		try {
			Class<?> cls = Class.forName(this.className);
			return cls.getAnnotation(Model.class).parentMenu();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";

	}

	public void setParFuncCode(String pf) {

	}

	public String getIsExistQuery() {
		return isExistQuery;
	}

	public void setIsExistQuery(String isExistQuery) {
		this.isExistQuery = isExistQuery;
	}

	boolean hasfile = false;

	public void setHasFile(boolean hasfile) {
		this.hasfile = hasfile;
	}

	public boolean getHasFile() {
		return this.hasfile;
	}
}
