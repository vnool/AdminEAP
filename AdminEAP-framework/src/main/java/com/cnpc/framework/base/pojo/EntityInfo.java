package com.cnpc.framework.base.pojo;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.servlet.http.HttpServletRequest;

import com.cnpc.framework.annotation.Header;
import com.cnpc.framework.annotation.Model;
import com.cnpc.framework.base.pojo.FieldSetting;
import com.cnpc.framework.base.pojo.GenerateSetting;
import com.cnpc.framework.utils.StrUtil;

public class EntityInfo{

	public static GenerateSetting entityInfo(String className, HttpServletRequest request)
			throws ClassNotFoundException {
		Class<?> clazz = Class.forName(className);
		GenerateSetting setting = new GenerateSetting();
		// setting.setQueryId(queryId);
		setting.setColsNum(1);
		setting.setClassName(className);
		setting.setAllowPaging(true);
		//String tableName =   clazz.getSimpleName();
		String modelName = clazz.getAnnotation(Model.class).label();
		String queryId = clazz.getAnnotation(Model.class).name();
		
		setting.setQueryId(queryId);
		setting.setTableName(modelName + "列表");
		setting.setModelName(modelName);
		
		String nameSpace = setting.getNameSpace();
		
		// 文件路径
		String htmlDir = "";
		String[] level = new String[] {};
		if (!StrUtil.isEmpty(nameSpace)) {
			level = nameSpace.split("[.]");
		}
		// 过滤掉com.cnpc
		for (int i = 2; i < level.length; i++) {
			htmlDir += (i < level.length - 1) ? level[i] + File.separator : level[i];
		}
		setting.setBusinessPackage(htmlDir.replace(File.separator, "/"));
        
		
		String realPath = request.getRealPath("/");
		String htmlPath = realPath + File.separator + "WEB-INF" + File.separator + "views" + File.separator + htmlDir;
		String javaPath = realPath.replaceAll("webapp", "java") + File.separator
				+ setting.getNameSpace().replace(".", File.separator);
		setting.setHtmlPath(htmlPath);
		setting.setJavaPath(javaPath);

		// 要编辑的属性列表

		Field[] fields = clazz.getDeclaredFields();
		List<FieldSetting> fslist = new ArrayList<>();
		int i = 0;
		for (Field field : fields) {

			if (field.getAnnotation(Column.class) == null && field.getAnnotation(JoinColumn.class) == null)
				continue;
			if (field.getAnnotation(Id.class) != null)
				continue;

			String key = field.getAnnotation(Column.class).name();

			FieldSetting fs = new FieldSetting();
			fs.setRowIndex(++i);
			fs.setFieldParam(field);
			String name = "";  
			if (field.getAnnotation(Header.class) != null) {
				name = field.getAnnotation(Header.class).name();
			}
			if (name == null) {
				name = fs.getColumnName();
			}
			fs.setLabelName(name);
			fs.setIsSelected("1");
			fs.setIsCondition("0");
			fslist.add(fs);
		}
		setting.setFields(fslist);
		setting.setHtmlTypes("list,addUpdate");
		setting.setJavaTypes("controller");
		setting.setIsCreateFunction("1");
		setting.setCurdType("dialog");
		return setting;
	}
}