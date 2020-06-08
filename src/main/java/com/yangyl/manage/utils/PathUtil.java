package com.yangyl.manage.utils;

import java.io.File;
import java.util.UUID;

public class PathUtil {

	/**
	 * 
	 * @Title: generateSavePath   
	 * @Description: generate save path by loginName and module. 
	 * @param: @param loginName
	 * @param: @param module
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	public static String generateSavePath(String majorPath, String loginName, String module) {
		return new StringBuffer()
				.append(majorPath).append(File.separator)
				.append(loginName).append(File.separator)
				.append(module).append(module).toString();
	}
	
	/**
	 * 
	 * @Title: generateFilePath   
	 * @Description: generate file path by save_path and file_name. 
	 * @param: @param savePath
	 * @param: @param fileName
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	public static String generateFilePath(String savePath, String fileName) {
		return new StringBuffer()
				.append(savePath).append(File.separator)
				.append(fileName).toString();
	}
	
	/**
	 * 
	 * @Title: generateFileName   
	 * @Description: generate new name . unique.
	 * @param: @param sourceName
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	public static String generateFileName(String sourceName) {
		String randomName = UUID.randomUUID().toString().replaceAll("\\-","");
		String[] nameTokens = sourceName.split("\\.");
		return randomName + "." + nameTokens[nameTokens.length - 1];
	}
}
