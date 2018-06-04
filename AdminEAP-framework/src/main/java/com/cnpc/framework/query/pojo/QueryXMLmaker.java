package com.cnpc.framework.query.pojo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Transient;
import javax.servlet.http.HttpServletRequest;

import org.hsqldb.lib.StringUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnpc.framework.base.pojo.FieldSetting;
import com.cnpc.framework.base.pojo.GenerateSetting;
import com.cnpc.framework.base.pojo.Result;
import com.cnpc.framework.utils.DateUtil;
import com.cnpc.framework.annotation.Header;
import com.cnpc.framework.annotation.Model;

public class QueryXMLmaker {

	// 生成xml配置文件

	public static Result generateXMLFile(String xmlContent, String xmlPath) {

		String rn = "\r\n";
		try {
			File file = new File(xmlPath);
			BufferedWriter output;
			String s;
			String s1 = new String();
			if (!file.exists()) {
				file.createNewFile();
				OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
				output = new BufferedWriter(write);
				output.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
				output.write(rn);
				output.write(
						"<queryContext xmlns=\"http://www.example.org/query\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.example.org/query query.xsd\">");
				output.write(rn);
				output.write("<!--本xml片段为代码生成器生成，时间：" + DateUtil.format(new Date(), DateUtil.formatStr_yyyyMMddHHmmss)
						+ "-->");
				output.write(rn);
				output.write(xmlContent);
				output.write(rn);
				output.write("</queryContext>");
				output.close();
				return new Result(true, "请刷新query下的" + xmlPath + "文件，查看首次生成xml配置");
			} else {
				InputStreamReader read = new InputStreamReader(new FileInputStream(file), "UTF-8");
				BufferedReader input = new BufferedReader(read);
				while ((s = input.readLine()) != null) {
					s1 += s + rn;
				}
				s1 = s1.replaceAll("</queryContext>", "");
				OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
				output = new BufferedWriter(write);
				output.write(s1);
				output.write(rn);
				output.write("<!--本xml片段为代码生成器生成，时间：" + DateUtil.format(new Date(), DateUtil.formatStr_yyyyMMddHHmmss)
						+ "-->");
				output.write(rn);
				output.write(xmlContent);
				output.write(rn);
				output.write("</queryContext>");
				output.close();
			}
			return new Result(true, "请刷新query下的" + xmlPath + "文件，查看追加的xml配置");

		} catch (IOException e) {
			e.printStackTrace();
			return new Result(false);
		}
	}

	public static Result XMLContentfromCls(GenerateSetting gs) throws ClassNotFoundException {

		String rn = "\r\n";
		// 生成query
		StringBuilder sb = new StringBuilder();
		sb.append("<query id=\"" + gs.getQueryId() + "\"");
		sb.append(" key=\"id\"");
		sb.append(" tableName=\"" + gs.getModelName() + "列表\"");
		sb.append(" className=\"" + gs.getClassName() + "\"");
		sb.append(" widthType=\"px\">");
		sb.append(rn);
		// 序号
		sb.append("        <column key=\"rowIndex\"");
		sb.append(" header=\"序号\"");
		sb.append(" width=\"80\"/>");
		sb.append(rn);

		List<FieldSetting> fields = gs.getFields();

		int i = 0;
		for (FieldSetting fs : fields) {

			sb.append("        <column key=\"" + fs.getColumnName().replace(".id", ".name") + "\"");
			sb.append(" header=\"" + fs.getLabelName() + "\"");

			sb.append(" classType=\"" + fs.getType() + "\"");

			String TagType = fs.getTagType();

			if ("textarea".equals(TagType)) {
				sb.append(" align=\"left\"");
				sb.append(" allowSort=\"false\"");
			} else if ("datepicker".equals(TagType)) {
				sb.append(" allowSort=\"true\"");
				sb.append(" dateFormat=\"yyyy-mm-dd\"");
				sb.append(" operator=\"between\"");
			} else {
				sb.append(" allowSort=\"true\"");
			}

			if (!StringUtil.isEmpty(fs.getCondition())) {
				sb.append(" operator=\"" + fs.getCondition() + "\"");
			}
			if ("image".equals(TagType) ||  "imageurl".equals(TagType) ) {
				sb.append(" fnRender=\"fnRenderImage\"");
			}
			if ("video".equals(TagType) ||  "videourl".equals(TagType) ) {
				sb.append(" fnRender=\"fnRenderVideoPlayButton\"");
			}
			
			// 查看详情链接
			/*
			 * if (i == 0) { sb.append(" render=\"type=window,url=/" + prefix +
			 * "/show,param=id,winId=" + prefix +
			 * "Win,winHeader=value,width=650,height=450,isMax=true\""); }
			 */
			sb.append(" width=\"150\"/>");
			i++;
			sb.append(rn);
		}

		sb.append("</query>");
		Result result = new Result();
		result.setMessage(sb.toString());
		return result;
	}

}