package com.thc.sprboot.util;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;

@Component
public class FileUpload {

	public static String local(
			MultipartFile mf, HttpServletRequest request
	) throws IOException {
		String returnValue = "";
		try {
			String root_path = path(request);
			setDir(root_path);
			String filename = setFileName(mf);

			FileCopyUtils.copy(mf.getBytes(), new File(root_path + filename));
			returnValue = "/uploadfile/" + filename;
		} catch (Exception e) {
			System.out.println("======================Exception : " + e);
		}
		return returnValue;
	}
	public static String rootPath(HttpServletRequest request){
		String root_path = request.getSession().getServletContext().getRealPath("/");
		//역슬레시 넘어오는 경우, /로 바꿔준다.
		root_path = root_path.replace("\\", "/");
		return root_path;
	}
	public static String path(HttpServletRequest request) throws IOException {
		String root_path = rootPath(request);
		//String root_path = request.getSession().getServletContext().getRealPath("/");

		//서버 업로드 이후를 위한 코드
		// /home/centos/apache-tomcat-8.5.72/webapps/abc
		root_path = root_path.replace("wtpwebapps", "uploadfiles");
		root_path = root_path.replace("webapps", "uploadfiles");

		//로컬 서버를 위한 코드
		if(root_path.indexOf("C:/") == 0) {
			//윈도우 로컬을 위한 코드
			//C:/Users/01/AppData/Local/Temp/tomcat-docbase.8080.12040726205565672866/
			root_path = "C:/workspace/";
		} else if(root_path.indexOf("Users/") == 0) {
			//맥 로컬을 위한 코드
			// Users/01/Downloads/
			root_path = "Users/workspace/";
		}
		String attach_path = "uploadfiles/winterspr/";
		return root_path + attach_path;
	}
	public static String setFileName(MultipartFile mf){
		String result = "";
		if (mf == null || "".equals(mf.getOriginalFilename() + "")) {
		} else {
			Date date = new Date();
			String temp_date = date.getTime() + "";
			String filename = mf.getOriginalFilename();
			filename = filename.replaceAll(" ", "");
			// file.jpg
			result = temp_date + "_" + filename;
			// 92032037230952_file.jpg
		}
		return result;
	}
	public static void setDir(String root_path){
		// 디랙토리 주소에 대해서, File 객체에 담음.(파일이 아니라, 폴더!!)
		File newfile = new File(root_path);
		// File 객체에 담긴 폴더가 존재하는지 물어봄!
		if(!newfile.exists()) {
			// File 객체에 담긴 폴더가 존재안하면 강제 생성!!
			newfile.mkdirs();
		}
	}
	
}