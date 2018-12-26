package com.wyl.lotterycommon.utils.push;


import com.wyl.lotterycommon.utils.encode.EncryptMD5Util;

/**
 * 推送别名
 * @author Jason
 */
public class AliasUtil {
  /**
   * 获得快递员别名
   * @param id    快递员id
   * @param appserialnumber  手机唯一标识
   * @return
   * @author Jason.Li
   * @date 2015年11月24日下午5:32:16
   */
  public static String getAlias(Long id,String appserialnumber){
	 return EncryptMD5Util.encryptMD5(id+appserialnumber).substring(8,24);
  }
}

