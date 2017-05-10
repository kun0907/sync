package com.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import Decoder.BASE64Decoder;
import com.util.base.ActionBase;
import com.util.base.FileUtil;

@Controller
@RequestMapping(value="img")
public class ImageManage extends ActionBase{
	 
	 
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/chart.req", method = RequestMethod.POST)
	public void img(HttpServletRequest request,HttpServletResponse response) {
		 String imgname = request.getParameter("imgname");
		 String analysisType = request.getParameter("analysisType");
		 String username = (String) request.getSession().getAttribute("username");
		 String path1 = request.getRealPath("/")+"Echarts/"+username+"/"+analysisType;
			 File folder = new File(path1);
			 if (!folder.exists()) {
				 log.info("图表文件夹不存在创建");
			 }else{
				   FileUtil.DeleteFolder(path1);
			 }
			 folder.mkdirs();
		 if(imgname!=null){
			 String path = path1+"/"+imgname+".png";//imgname和pcapFileName相同
			 String data = request.getParameter(imgname);
			 log.info("生成pdf时上传图标图片:  "+path+"   开始....");
			 ImageSave(data, path,request, response);
			 log.info("生成pdf时上传图标图片:  "+path+"   结束....");
		  }
		 
		
	}
	
	
	
	/**
	 * 接收"报文处理概述"和"结论概述"图片以供生成pdf时渲染
	 * @param request
	 * @param response
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/uploadImg.req", method = RequestMethod.POST)
	public void img2(HttpServletRequest request,HttpServletResponse response) {
		String username = (String) request.getSession().getAttribute("username");
		String path1 = request.getRealPath("/")+"xxxx/"+username;
		File folder = new File(path1);
		if (!folder.exists()) {
			System.out.println("图表文件夹不存在创建");
		}else{
			FileUtil.DeleteFolder(path1);
		}
		folder.mkdirs();
		String imgName=request.getParameter("imgName");
		String path = path1+"/"+imgName+".png";//imgname和pcapFileName相同
		File file = new File(path);
		if(file.exists()){
			 file.delete();
		}
		String data = request.getParameter("imgdata");
		ImageSave(data, path,request, response);
	}
	
	/**
	 * 创建文件夹;
	 * @param data
	 * @param path
	 * @param request
	 * @param response
	 */
	public void ImageSave(String data,String path,HttpServletRequest request, HttpServletResponse response){
            
		try {
			String[] url = data.split(",");
			String u = url[1];
			// Base64解码
			byte[] b = new BASE64Decoder().decodeBuffer(u);// 图片流
			// 生成图片
			OutputStream out = new FileOutputStream(new File(path));
			out.write(b);
			out.flush();
			out.close();
		} catch (Exception e) {
			log.error("图片下载异常*******图片路径:"+ path + "*******图片名称:" + data);
			log.error(e);
		}
	}
    
}
