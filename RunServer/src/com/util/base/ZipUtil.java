package com.util.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletResponse;

public class ZipUtil {

	public static void downloadZip(HttpServletResponse response, List<String> filePathInZips) {
		
		response.setContentType("application/x-msdownload" ); // 通知客户文件的MIME类型：
		response.setHeader("Content-disposition" , "attachment;filename=" + "CMLAB.zip");
		//要下载的文件目录
		try {
			ZipOutputStream zos = new ZipOutputStream(response.getOutputStream());
			for (String filePath : filePathInZips) {
				File file = new File(filePath);
				if(file.exists()) {
					if (file.isFile()) {
						//如果是文件，写入到 zip 流中
						zos.putNextEntry(new ZipEntry(file.getName()));
						FileInputStream fis = new FileInputStream(file);
						byte[] buffer = new byte[1024];
						int r = 0;
						while ((r = fis.read(buffer)) != -1) {
							zos.write(buffer, 0, r);
						}
						zos.flush();				
						fis.close();
						file.delete();
					}
				}
			}			
			zos.close();
		} catch (FileNotFoundException e) {
			ActionBase.log.error(e);
		} catch (IOException e) {
			ActionBase.log.error(e);
		}
		
	}

	

}
