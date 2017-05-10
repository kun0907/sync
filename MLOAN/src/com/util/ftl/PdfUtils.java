package com.util.ftl;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import org.xhtmlrenderer.pdf.ITextRenderer;
import org.xhtmlrenderer.pdf.PDFCreationListener;


public class PdfUtils {
	/**
	 * 生成PDF到文件
	 * @param ftlPath 模板文件路径（不含文件名）
	 * @param ftlName 模板文件吗（不含路径）
	 * @param imageDiskPath 图片的磁盘路径
	 * @param data 数据
	 * @param outputFile 目标文件（全路径名称）
	 * @throws Exception
	 */
	@SuppressWarnings("resource")
	public static void generateToFile(String ftlPath,String ftlName,String imageDiskPath,Object data,String outputFile) throws Exception {
		String html=PdfHelper.getPdfContent(ftlPath, ftlName, data);
		/*System.out.println("html="+html);
		FileOutputStream fos = new FileOutputStream(new File("D://xxxx.html"));
		fos.write(html.getBytes());
		fos.close();*/
		
		/**
		 * ftlPath=D:/install/apache-tomcat-8.0.36/webapps/MLOAN_renbaokun/WEB-INF/classes/com/util/ftl/
		   ftlName=resources/details_whenCompare.html
		   imageDiskPath=D:/install/apache-tomcat-8.0.36/webapps/MLOAN_renbaokun/WEB-INF/classes/com/util/ftl/resources/
		 */
		
		OutputStream out = null;
		ITextRenderer render = null;
		out = new FileOutputStream(outputFile);
		render = PdfHelper.getRender();
		render.setDocumentFromString(html);
		if(imageDiskPath!=null&&!imageDiskPath.equals("")){
			String os = System.getProperty("os.name");  
			if(os.toLowerCase().startsWith("win")){  
				render.getSharedContext().setBaseURL("file:/"+imageDiskPath);//html中如果有图片，图片的路径则使用这里设置的路径的相对路径，这个是作为根路径
			}else{
				render.getSharedContext().setBaseURL("file:"+imageDiskPath);//html中如果有图片，图片的路径则使用这里设置的路径的相对路径，这个是作为根路径
			}
		}
		
		render.layout();
		render.createPDF(out);
		
		render.finishPDF();
		render = null;
		out.close();
	}
}