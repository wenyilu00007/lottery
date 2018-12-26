package com.wyl.lotterycommon.utils.tools;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.UrlResource;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 
 * com.ksudi.star.util.tools.InitUtil
 */
public class InitUtil {
	private static Logger log = LoggerFactory.getLogger(InitUtil.class);
	public static String rootConfFilePath= "file:/datum/data/conf/rootconfig.properties";
	public static String CONF_SUFFIX= "__configpath";
	public static String FILE_PREFIX= "file:";
	
	public static String commonConfFilePath= "file:/datum/data/conf/config.properties";
	public static String SYS_PROJECT_NAME__KEY= "sys_project_name";
	private static boolean readRoot = true; // 是否要读根配置文件
	
	public static String getCommonConf(String key){
		initConf();
		
		Properties prop = getSysConf(commonConfFilePath);
		String val = null;
		if(prop!=null){
			val = prop.getProperty(key);
		}
		return val;
		
	}

	public static Properties getCommonProp(){
		initConf();
		Properties prop = getSysConf(commonConfFilePath);
		return prop;
		
	}
	
	public static void initConf(){
		if(readRoot){
			String sysProjectName=System.getProperty(SYS_PROJECT_NAME__KEY);
			if(StringUtils.isNotBlank(sysProjectName)){
				File realFile=null;
				try{
					UrlResource urlResource = new UrlResource(rootConfFilePath);
					realFile=urlResource.getFile();
				}catch(Exception e){
					log.info(rootConfFilePath + e.getMessage(), e);
				}
				
				if(realFile!=null && realFile.exists()){
					Properties prop = getSysConf(rootConfFilePath);
					if(prop!=null){
						String rootKey=sysProjectName+CONF_SUFFIX;
						String confFilePath=prop.getProperty(rootKey);
						if(StringUtils.isNotBlank(confFilePath)){
							commonConfFilePath= FILE_PREFIX+confFilePath;
						}
					}
				}
			}
			readRoot=false;
		}
	}
	
	private static Map<String, Properties> propMap = new ConcurrentHashMap<String, Properties>(); // 配置文件的属性对象
	public static Properties getSysConf(String propPath){
		Properties sysConf= propMap.get(propPath);
		if(sysConf==null){
			log.info("gain config "+propPath);
			synchronized (InitUtil.class) {
				sysConf= propMap.get(propPath);
				if(sysConf==null){
					sysConf = new Properties();
					FileInputStream fis = null;
					InputStreamReader isr=null;
					BufferedReader br = null;
					try{
						UrlResource urlResource = new UrlResource(propPath);
						String realPath=urlResource.getFile().getAbsolutePath();
						
						fis = new FileInputStream(realPath);
						isr = new InputStreamReader(fis);
						br = new BufferedReader(isr);  
						sysConf.load(br); 
						propMap.put(propPath, sysConf);
					}catch(Exception e){
						log.info("maybe propPath is not exit, it should in same disk with server"+e.getMessage(), e);
						return sysConf;
					} finally{
						try{
							if(fis!=null){
								fis.close();
							}
							if(isr!=null){
								isr.close();
							}
							if(br!=null){
								br.close();
							}
						}catch(Exception e){
							log.info(e.getMessage(), e);
						}
					}
				}
			}
				
		}
		return sysConf;
		
	}

	

	/**
	 * 加载根配置
	 * @author justin.li
	 * @date 2017年1月11日
	 */
	public static Properties loadRootConf(){
		return loadRootConf(rootConfFilePath);
	}
	public static Properties loadRootConf(String filePath){
		File realFile=null;
		try{
			UrlResource urlResource = new UrlResource(rootConfFilePath);
			realFile=urlResource.getFile();
		}catch(Exception e){
			log.info(rootConfFilePath + e.getMessage(), e);
		}
		
		if(realFile!=null && realFile.exists()){
			Properties prop = getSysConf(rootConfFilePath);
			return prop;
		}
		return null;
	}
	
	public static Properties loadProp(String propPath){
		Properties sysConf = new Properties();
		FileInputStream fis = null;
		InputStreamReader isr=null;
		BufferedReader br = null;
		try{
			UrlResource urlResource = new UrlResource(propPath);
			String realPath=urlResource.getFile().getAbsolutePath();
			
			fis = new FileInputStream(realPath);
			isr = new InputStreamReader(fis);
			br = new BufferedReader(isr);  
			sysConf.load(br); 
			return sysConf;
		}catch(Exception e){
			log.info("loadProp, maybe propPath is not exit, it should in same disk with server "+e.getMessage(), e);
			return sysConf;
		} finally{
			try{
				if(fis!=null){
					fis.close();
				}
				if(isr!=null){
					isr.close();
				}
				if(br!=null){
					br.close();
				}
			}catch(Exception e){
				log.info(e.getMessage(), e);
			}
		}
	}
}
