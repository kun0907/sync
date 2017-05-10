package com.util.ftl;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Locale;

import org.jfree.util.Log;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.BaseFont;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;



@SuppressWarnings("deprecation")
public class PdfHelper {
	
	private static ITextRenderer render=null;
	static{
		render = new ITextRenderer();
		String path = getPath();
		//添加字体，以支持中文
		try {
			render.getFontResolver().addFont(path + "resources/ARIALUNI.TTF", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		/*render.getFontResolver().addFont(path + "resources/simsun.ttc", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
		render.getFontResolver().addFont(path + "resources/simhei.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
		render.getFontResolver().addFont(path + "resources/SIMYOU.TTF", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);*/
	}
	
	public static ITextRenderer getRender() {
			Log.info("**************************render="+render+"*********************************************");
			return render;
	}
	
	
	//获取要写入PDF的内容
	public static String getPdfContent(String ftlPath, String ftlName, Object o) throws IOException, TemplateException{
		return getHtmlStrByTemplate(ftlPath, ftlName, o);
	}
	//使用freemarker得到html内容
	public static String getHtmlStrByTemplate(String ftlPath, String ftlName, Object o) throws IOException, TemplateException{
		String html = null;
		Configuration config = getFreemarkerConfig(ftlPath);
		Template template = config.getTemplate(ftlName);
		template.setEncoding("UTF-8");
		StringWriter writer = new StringWriter();
		template.process(o, writer);
		writer.flush();
		html = writer.toString();
		return html;
	}
	
	
	/**
	 * 获取Freemarker配置
	 */
	private static Configuration getFreemarkerConfig(String templatePath) throws IOException {
		Configuration config = new Configuration();
		config.setDirectoryForTemplateLoading(new File(templatePath));
		config.setEncoding(Locale.CHINA, "utf-8");
		return config;
	}
	/**
	 * 获取类路径
	 */
	public static String getPath(){
		String path = PdfHelper.class.getResource("").getPath();
		String os = System.getProperty("os.name");  
		if(os.toLowerCase().startsWith("win")){  
			return  path.substring(1);
		}else{
			return path;
		}
	}
}