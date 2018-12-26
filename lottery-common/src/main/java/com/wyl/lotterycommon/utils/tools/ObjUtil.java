package com.wyl.lotterycommon.utils.tools;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.wyl.lotterycommon.utils.calculate.CalculateUtil;
import com.wyl.lotterycommon.utils.date.DateUtil;
import com.wyl.lotterycommon.utils.http.HttpUtil;
import com.wyl.lotterycommon.utils.string.StringUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.pkcs.RSAPrivateKeyStructure;
import org.bouncycastle.asn1.x509.RSAPublicKeyStructure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jmx.export.annotation.ManagedResource;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.*;
import java.text.DecimalFormat;
import java.util.*;
import java.util.Map.Entry;

@ManagedResource
public class ObjUtil {
	private final static Logger log = LoggerFactory.getLogger(ObjUtil.class);
	public static final String ALGORITHM="RSA";
	public static final String CIPHER_ALGORITHM = "RSA/ECB/PKCS1Padding";
	public static final String CHARCODE = "UTF-8";
	public static final String NUM_REG="[-]{0,1}[\\d]+";
	public static final String FLAG_COMMA = ",";
	public static final String MONGO_LONGSTR = "$numberLong";
	
	public final static Gson gson= new Gson();

	public static Long mongoLong2long(Object obj, String key){
		String mongoStr=mongoLong2str(obj, key);
		Long mongoLong=mongoStr==null?null:Long.valueOf(mongoStr);
		return mongoLong;
	}
		
	public static String mongoLong2str(Object obj, String key){
		if(obj==null){
			return null;
		}
		if(obj instanceof Map){
			@SuppressWarnings("rawtypes")
			Map objMap = (Map)obj;
			return (String)objMap.get(MONGO_LONGSTR);
		}
		return null;
	}

	
	public static <T> String coll2str(Collection<T> coll){
		return coll2str(coll, ",");
	}
	public static <T> String coll2str(Collection<T> coll, String split){
		if(CollectionUtils.isEmpty(coll)){
			return null;
		}
		StringBuilder sb= new StringBuilder();
		for(Object str : coll){
			sb.append(str).append(split);
		}
		return sb.substring(0, sb.length()-2);
	}

	// 比较两个Long类型的对象是否值不相等，两个都为空，返回false， 只有一个为空，返回true
	public static boolean noEqualLong(Long o1, Long o2){
		return !equalLong(o1, o2);
	}
	// 比较两个Long类型的对象是否值相等，两个都为空，返回true， 只有一个为空，返回false
	public static boolean equalLong(Long o1, Long o2){
		if(o1==null && o2==null){
			return true;
		}
		if((o1==null && o2 != null) || (o1  != null && o2 == null)){
			return false;
		}
		long long1= (long)o1;
		long long2= (long)o2;
		return long1== long2;
	}

	// 比较两个Integer类型的对象是否值不相等，两个都为空，返回false， 只有一个为空，返回true
	public static boolean noEqualInteger(Integer o1, Integer o2){
		return !equalInteger(o1, o2);
	}
	
	// 比较两个Integer类型的对象是否值相等，两个都为空，返回true， 只有一个为空，返回false
	public static boolean equalInteger(Integer o1, Integer o2){
		if(o1==null && o2==null){
			return true;
		}
		if((o1==null && o2 != null) || (o1  != null && o2 == null)){
			return false;
		}
		int val1= (int)o1;
		int val2= (int)o2;
		return val1== val2;
	}
	

	public static void p(Object o) {
		System.out.print(o);
	}

	public static void pl(Object o) {
		System.out.println(o);
	}

	public static void pl() {
		System.out.println();
	}
	public static void pobj(Object obj) {
		System.out.println(new Gson().toJson(obj));
	}
	
	/**
	 * 如果data对象不为null则返回该对象的值，否则返回给定的默认值defaultValue
	 * 
	 * @param data
	 *            判断是否为null的对象
	 * @param defaultValue
	 *            默认值
	 * @return
	 * @author Wenlong.Zhou
	 * @date 2016年4月27日上午11:08:19
	 */
	public static byte valueFrom(Byte data, byte defaultValue) {
		if (data == null) {
			return defaultValue;
		}
		return data.byteValue();
	}
	
	/**
	 * 如果data对象不为null则返回该对象的值，否则返回给定的默认值defaultValue
	 * 
	 * @param data
	 *            判断是否为null的对象
	 * @param defaultValue
	 *            默认值
	 * @return
	 * @author Wenlong.Zhou
	 * @date 2016年4月27日上午11:08:19
	 */
	public static short valueFrom(Short data, short defaultValue) {
		if (data == null) {
			return defaultValue;
		}
		return data.shortValue();
	}
	
	/**
	 * 如果data对象不为null则返回该对象的值，否则返回给定的默认值defaultValue
	 * 
	 * @param data
	 *            判断是否为null的对象
	 * @param defaultValue
	 *            默认值
	 * @return
	 * @author Wenlong.Zhou
	 * @date 2016年4月27日上午11:08:19
	 */
	public static int valueFrom(Integer data, int defaultValue) {
		if (data == null) {
			return defaultValue;
		}
		return data.intValue();
	}
	
	/**
	 * 如果data对象不为null则返回该对象的值，否则返回给定的默认值defaultValue
	 * 
	 * @param data
	 *            判断是否为null的对象
	 * @param defaultValue
	 *            默认值
	 * @return
	 * @author Wenlong.Zhou
	 * @date 2016年4月27日上午11:08:19
	 */
	public static long valueFrom(Long data, long defaultValue) {
		if (data == null) {
			return defaultValue;
		}
		return data.longValue();
	}
	
	/**
	 * 如果data对象不为null则返回该对象的值，否则返回给定的默认值defaultValue
	 * 
	 * @param data
	 *            判断是否为null的对象
	 * @param defaultValue
	 *            默认值
	 * @return
	 * @author Wenlong.Zhou
	 * @date 2016年4月27日上午11:08:19
	 */
	public static double valueFrom(Double data, double defaultValue) {
		if (data == null) {
			return defaultValue;
		}
		return data.doubleValue();
	}
	
	/**
	 * 如果data对象不为null则返回该对象的值，否则返回给定的默认值defaultValue
	 * 
	 * @param data
	 *            判断是否为null的对象
	 * @param defaultValue
	 *            默认值
	 * @return
	 * @author Wenlong.Zhou
	 * @date 2016年4月27日上午11:08:19
	 */
	public static float valueFrom(Float data, float defaultValue) {
		if (data == null) {
			return defaultValue;
		}
		return data.floatValue();
	}

	/**
	 * 如果data对象不为null则返回该对象的值，否则返回给定的默认值defaultValue
	 *
	 * @param data
	 *            判断是否为null的对象
	 * @param defaultValue
	 *            默认值
	 * @return
	 * @author Wenlong.Zhou
	 * @date 2016年7月4日上午11:08:19
	 */
	public static boolean valueFrom(Boolean data, boolean defaultValue) {
		if (data == null) {
			return defaultValue;
		}
		return data.booleanValue();
	}
	
	
	/**
	 * 如果data对象不为null则返回该对象的值，否则返回给定的默认值defaultValue
	 * 
	 * @param data
	 *            判断是否为null的对象
	 * @param defaultValue
	 *            默认值
	 * @return
	 * @author Wenlong.Zhou
	 * @date 2016年4月27日上午11:08:19
	 */
	public static String valueFrom(String data, String defaultValue) {
		if (data == null) {
			return defaultValue;
		}
		return data;
	}


	/**
	 * 获取json里面的值 
	 * @param json json字符串
	 * @param propStr 用英文逗号分隔的属性名，例："results,status"
	 * @return
	 */
	public static String getJsonVal(String json, String propStr) {
		if (StringUtils.isBlank(propStr)) {
			return json;
		}
		String[] props = propStr.split(",");
		return getJsonVal(json, props);
	}

	public static String getJsonVal(String json, String[] props) {
		if (StringUtils.isBlank(json) || props == null || props.length == 0) {
			return json;
		}
		JSONObject jsonObj = null;
		try{
			jsonObj=JSONObject.parseObject(json);
		}catch(Exception e){
			log.info("trans json error, json="+json);
		}
		return getJsonVal(jsonObj, props);
	}

	public static String getJsonVal(JSONObject jsonObj, String[] props) {
		JSONObject jo = jsonObj;
		JSONArray ja = null;
		if (jo == null || props == null || props.length == 0) {
			return null;
		}
		String s = null;
		for (int i = 0, l = props.length; i < l; ++i) {
			String p = props[i];
			if(jo.containsKey(p)){
				s = jo.getString(p);
			}else{
				return null;
			}
			if (StringUtils.isBlank(s)) {
				return null;
			}
			if (i == l - 1) {
				return s;
			}
			s = s.trim();
			try {
				if(s.startsWith("[")){
					ja = JSONArray.parseArray(s);
					jo= ja.getJSONObject(0);
				}else{
					jo = JSONObject.parseObject(s);
				}
			} catch (Exception e) {
				log.info("error json=" + s);
				return s;
			}
		}
		return s;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Properties getConfFromSpringCloud(String json){
		if(StringUtils.isBlank(json)){
			return null;
		}
		String content=json.trim();
		String val = getJsonVal(content, "propertySources,source");
		pl(val);
		Map map = json2obj(val, Map.class);
		Properties prop = new Properties();
		prop.putAll(map);
		return prop;
	}
	
	public static Set<String> arr2SetNoBlank(String[] strs){
		if( strs==null || strs.length==0){
			return null;
		}
		Set<String> set=new HashSet<String>();
		for(String str:strs){
			if(StringUtils.isNotBlank(str)){
				set.add(str);
			}
		}
		
		return set;
	}

	public static String obj2json(Object obj){
		try{
			return gson.toJson(obj);
		}catch(Exception e){
			log.info(e.getMessage(), e);
		}
		return null;
	}
	public static <C> C json2obj(String json, Class<C> cla){
		try{
			return gson.fromJson(json, cla);
		}catch(Exception e){
			log.info(e.getMessage(), e);
		}
		return null;
	}
	
	public static Date datetimeMerge(Date date, Date time){
		if(date==null && time==null){
			return null;
		}else if(date==null && time!=null){
			return time;
		}else if(date!=null && time==null){
			return date;
		}
		Calendar c = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(time);
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, cal2.get(Calendar.HOUR_OF_DAY));
        c.set(Calendar.MINUTE, cal2.get(Calendar.MINUTE));
        c.set(Calendar.SECOND, cal2.get(Calendar.SECOND));
        c.set(Calendar.MILLISECOND, cal2.get(Calendar.MILLISECOND));
        return c.getTime();
		
	}
	
	

	public static Set<String> includeStr(String str){
		return includeStr(str, ",");
	}
	/**
	 * 根据“,”拆分字符串
	 * @param str要拆分的字符串
	 * @return
	 */
	public static Set<String> includeStr(String str, String split){
		Set<String> set = new LinkedHashSet<String>();
		if(StringUtils.isBlank(str)){
			return set;
		}
		String[] ss = str.split(split);
		for(String s:ss){
			if(StringUtils.isNotBlank(s)){
				set.add(s.trim());
			}
		}
		return set;
	}	
	

	/**
	 * 根据“,”拆分字符串，转成数字数组返回
	 * @param str要拆分的字符串
	 * @return 可转成数字的字符串，转成数字数组返回
	 */
	public static List<Long> splitStr2Long(String str){
		return splitStr2Long(str, FLAG_COMMA);
	}
	
	/**
	 * 根据“,”拆分字符串
	 * @param str要拆分的字符串
	 * @param split拆分符号字符串
	 * @return 可转成数字的字符串，转成数字数组返回
	 */
	public static List<Long> splitStr2Long(String str, String split){
		Set<Long> set = new LinkedHashSet<Long>();
		if(StringUtils.isBlank(str)){
			return null;
		}
		String[] ss = str.split(split);
		for(String s:ss){
			if(StringUtils.isNotBlank(s) && s.matches(NUM_REG)){
				set.add(Long.valueOf(s.trim()));
			}
		}
		return new ArrayList<Long>(set);
	}	
	

	/**
	 * 解析包含的字符串
	 * @param src
	 * @param range
	 * @return
	 */
	public static String parseIncludeStr(String src, Set<String> range){
		if(CollectionUtils.isEmpty(range)){
			return null;
		}
		for(String s:range){
			if(src.contains(s)){
				return s;
			}
		}
		return null;
	}

	/**
	 * 解析包含的字符串
	 * @param src
	 * @param range
	 * @return
	 */
	public static String includeOne(Set<String> set, Set<String> range){
		if(CollectionUtils.isEmpty(range) || CollectionUtils.isEmpty(set)){
			return null;
		}
		for(String s:range){
			if(set.contains(s)){
				return s;
			}
		}
		return null;
	}
	
	/**
	 * 从后面解析包含的字符串。如果源字符串中不包含 ends，则返回null
	 * @param src
	 * @param range
	 * @return
	 */
	public static String endExtract(String src, Set<String> starts, Set<String> ends, 
			boolean includeHead, boolean includeTail){
		if(StringUtils.isBlank(src) || CollectionUtils.isEmpty(ends)){
			return null;
		}
		String head="", detail="";
		int endIndex = -1;
		for(String str:ends){
			int tmp_endIndex=src.lastIndexOf(str);
			if(tmp_endIndex>endIndex){
				detail = str;
				endIndex = tmp_endIndex;
			}
		}
		if(endIndex<0){
			return null;  
		}
		int startIndex= -1;
		for(String str:starts){
			int tmp_startIndex=src.lastIndexOf(str, endIndex-1);
			if(tmp_startIndex>-1 && tmp_startIndex+str.length()-1>startIndex){
				head= str;
				startIndex=tmp_startIndex+str.length()-1;
			}
		}
		startIndex=startIndex<0?0:(startIndex+1);
		try{
			String aim=(includeHead?head:"")+ src.substring(startIndex, endIndex)+(includeTail?detail:"");
			return aim;
		}catch(Exception e){
			log.info(e.getMessage(), e);
		}
		return null;
	}	

	public static <K, V> Entry<K, V> getHead(LinkedHashMap<K, V> map) {
		if(map==null){
			return null;
		}
	    return map.entrySet().iterator().next();
	}
	public static <K, V> V getHeadVal(LinkedHashMap<K, V> map) {
		if(map==null){
			return null;
		}
	    return map.entrySet().iterator().next().getValue();
	}

	public static <K, V> Entry<K, V> getTail(LinkedHashMap<K, V> map) {
		if(map==null){
			return null;
		}
	    Iterator<Entry<K, V>> iterator = map.entrySet().iterator();
	    Entry<K, V> tail = null;
	    while (iterator.hasNext()) {
	        tail = iterator.next();
	    }
	    return tail;
	}
	public static <K, V> V getTailVal(LinkedHashMap<K, V> map) {
		if(map==null){
			return null;
		}
	    Iterator<Entry<K, V>> iterator = map.entrySet().iterator();
	    Entry<K, V> tail = null;
	    while (iterator.hasNext()) {
	        tail = iterator.next();
	    }
	    return tail.getValue();
	}

	/**
	 * 将map中的对象取出来，把对象的属性值设置到另一个对象的指定属性上
	 * @param orig 源map
	 * @param key 源对象key
	 * @param origProp 源对象属性
	 * @param obj 目标对象
	 * @param distProp 目标对象属性
	 * @param allowNull 是否设置空值 
	 * @author justin.li
	 * @date 2017年5月27日
	 */
	public static <K,V> void mapVal2objNoNull(Map<K,V> orig, K key, String origProp, Object obj, String distProp){
		mapVal2obj(orig, key, origProp, obj, distProp, false);
	}
	
	/**
	 * 将map中的对象取出来，把对象的属性值设置到另一个对象的指定属性上
	 * @param orig 源map
	 * @param key 源对象key
	 * @param origProp 源对象属性
	 * @param obj 目标对象
	 * @param distProp 目标对象属性
	 * @param allowNull 是否设置空值 
	 * @author justin.li
	 * @date 2017年5月27日
	 */
	public static <K,V> void mapVal2obj(Map<K,V> orig, K key, String origProp, Object obj, String distProp, boolean allowNull){
		if(orig == null || StringUtils.isBlank(origProp) || obj==null || StringUtils.isBlank(distProp)){// 目标对象或者目标属性为空，直接返回
			return;
		}
		V val = orig.get(key);
		if(val!=null){
			try{
				Object propVal = BeanUtils.getProperty(val, origProp);
				if(!allowNull && propVal==null){// 不处理空值，源属性为空，直接返回
					return;
				}
				BeanUtils.setProperty(obj, distProp, propVal);
			}catch(Exception e){
	        	log.info(e.getMessage(), e);
			}
		}
	}

	/**
	 * 将map中的对象取出来，把对象的属性值设置到另一个对象的指定属性上
	 * @param orig 源map
	 * @param origProp 要设置值的源对象属性。比如城市的id
	 * @param objs 目标对象组
	 * @param linkProp 目标对象的关联属性。比如，用户的城市id,站点id
	 * @param distProp 目标对象属性。比如，用户的城市的名称,站点的名称
	 * @param allowNull 是否设置空值 
	 * @author justin.li
	 * @date 2017年5月27日
	 */
	public static <K,V,T> void mapVal2objBatchNoNull(Map<K,V> orig, String origProp, Collection<T> objs, String linkProp, String distProp){
		mapVal2objBatch(orig, origProp, objs, linkProp, distProp, false);
	}
	
	/**
	 * 将map中的对象取出来，把对象的属性值设置到另一个对象的指定属性上
	 * @param orig 源map
	 * @param origProp 要设置值的源对象值的属性名称。比如城市的名称
	 * @param objs 目标对象组
	 * @param linkProp 目标对象的关联属性。比如，用户的城市id,站点id
	 * @param distProp 目标对象属性。比如，用户的城市的名称,站点的名称
	 * @param allowNull 是否设置空值 
	 * @author justin.li
	 * @date 2017年5月27日
	 */
	public static <K,V,T> void mapVal2objBatch(Map<K,V> orig, String origProp, Collection<T> objs, String linkProp, String distProp, boolean allowNull){
		if(orig == null || StringUtils.isBlank(origProp) || CollectionUtils.isEmpty(objs) ||
				StringUtils.isBlank(linkProp) || StringUtils.isBlank(distProp)){// 目标对象或者目标属性为空，直接返回
			return;
		}
		Method linkPropMethod = null;
		for(T obj : objs){
			Object key = null;
			try{
				if(linkPropMethod==null){
					linkPropMethod = getMethodByNameFromObj(obj, linkProp);
				}
				key= linkPropMethod.invoke(obj);
			}catch(Exception e){
	        	log.info(e.getMessage(), e);
	        	return ;
			}
			V val = orig.get(key);
			if(val!=null){
				try{
					Object propVal = BeanUtils.getProperty(val, origProp);
					if(!allowNull && propVal==null){// 不处理空值，源属性为空，直接返回
						return;
					}
					BeanUtils.setProperty(obj, distProp, propVal);
				}catch(Exception e){
		        	log.info(e.getMessage(), e);
		        	return ;
				}
			}
		}
	}
	
	/**
	 * 抽取List中的对象的属性，放到set中
	 * @param objs 需要抽取属性值的对象列表 
	 * @param prop 属性名称或者方法名称
	 * @return 属性值集合
	 */
	public static <K, T> Set<K> prop2set(Collection<T> objs, String prop){
		Set<K> vals = new LinkedHashSet<K>();
		if(CollectionUtils.isEmpty(objs)){
			return null;
		}
		// 属性方法
		Method method=null;
		for(T obj : objs){
			if(obj==null){
				continue;
			}
			if(method==null){
				method= getMethodByNameFromObj(obj, prop);
				if(method==null){
					return vals;
				}
			}
			try{
				@SuppressWarnings("unchecked")
				K retnObj = (K)method.invoke(obj);
				if(retnObj!=null){
					vals.add(retnObj);
				}
			}catch(Exception e){
				log.info(method.getDeclaringClass().getName()+" invoke fail methodName="+method.getName());
				return vals;
			}
		}
		return vals;
	}
	
	/**
	 * 将List转成Map
	 * @param objs 需要抽取属性值的对象列表 
	 * @param prop 属性名称或者方法名称
	 * @param cl 属性的数据类型
	 * @return 属性值与对象的映射
	 */
	public static <K, T> Map<K, T> coll2map(Collection<T> objs, String prop){
		return list2map(new ArrayList<T>(objs), prop);
	}

	/**
	 * 将List转成Map
	 * @param objs 需要抽取属性值的对象列表 
	 * @param prop 属性名称或者方法名称
	 * @param cl 属性的数据类型
	 * @return 属性值集合
	 */
	public static <K, T> Map<K, T> list2map(List<T> objs, String prop){
		Map<K, T> vals = new LinkedHashMap<K, T>();
		if(CollectionUtils.isEmpty(objs)){
			return null;
		}
		// 属性方法
		Method method=null;
		for(T obj : objs){
			if(obj==null){
				continue;
			}
			if(method==null){
				method= getMethodByNameFromObj(obj, prop);
				if(method==null){
					return vals;
				}
			}
			try{
				@SuppressWarnings("unchecked")
				K retnObj = (K)method.invoke(obj);
				if(retnObj!=null){
					vals.put(retnObj, obj);
				}
			}catch(Exception e){
				log.info(method.getDeclaringClass().getName()+" invoke fail methodName="+method.getName());
				return vals;
			}
		}
		return vals;
	}
	

	/**
	 * 将List转成Map
	 * @param objs 需要抽取属性值的对象列表 
	 * @param prop 属性名称或者方法名称
	 * @param cl 属性的数据类型
	 * @return 属性值集合
	 */
	@SuppressWarnings("unchecked")
	public static <K, T, V> Map<K, Map<T, V>> maplist2map(List<Map<T, V>> objs, String prop, Class<K> cla){
		Map<K, Map<T, V>> vals = new LinkedHashMap<K, Map<T, V>>();
		if(CollectionUtils.isEmpty(objs)){
			return null;
		}
		for(Map<T, V> map : objs){
			if(MapUtils.isEmpty(map)){
				continue;
			}
			try{
				K retnObj = (K)map.get(prop);
				if(retnObj!=null){
					vals.put(retnObj, map);
				}
			}catch(Exception e){
				return vals;
			}
		}
		return vals;
	}
	
	/**
	 * 将List转成Map
	 * @param objs 需要抽取属性值的对象列表 
	 * @param prop 属性名称或者方法名称
	 * @param cl 属性的数据类型
	 * @return 属性值集合
	 */
	@SuppressWarnings("unchecked")
	public static <K, T> Map<K, T> list2map(List<T> objs, String prop, Class<K> cla){
		Map<K, T> vals = new HashMap<K, T>();
		if(CollectionUtils.isEmpty(objs)){
			return null;
		}
		// 属性方法
		Method method=null;
		for(T obj : objs){
			if(obj==null){
				continue;
			}
			if(method==null){
				method= getMethodByNameFromObj(obj, prop);
				if(method==null){
					return vals;
				}
			}
			try{
				Object retnObj = (Object)method.invoke(obj);
				if(retnObj!=null){
					vals.put((K)retnObj, obj);
				}
			}catch(Exception e){
				log.info(method.getDeclaringClass().getName()+" invoke fail methodName="+method.getName());
				return vals;
			}
		}
		return vals;
	}


	/**
	 * 将List转成Map
	 * @param objs 需要抽取属性值的对象列表 
	 * @param propKey key属性名称或者方法名称
	 * @param propVal value属性名称或者方法名称
	 * @param cl 属性的数据类型
	 * @return 属性值集合
	 */
	public static <K, V, T> Map<K, V> list2mapProp(Collection<T> objs, String propKey, String propVal){
		return list2mapProp(objs.toArray(), propKey, propVal);
		
	}
	/**
	 * 将List转成Map
	 * @param objs 需要抽取属性值的对象列表 
	 * @param propKey key属性名称或者方法名称
	 * @param propVal value属性名称或者方法名称
	 * @param cl 属性的数据类型
	 * @return 属性值集合
	 */
	@SuppressWarnings("unchecked")
	public static <K, V, T> Map<K, V> list2mapProp(T[] objs, String propKey, String propVal){
		Map<K, V> vals = new HashMap<K, V>();
		if(ArrayUtils.isEmpty(objs)){
			return null;
		}
		// 属性方法
		Method method=null;
		Method methodVal=null;
		for(T obj : objs){
			if(obj==null){
				continue;
			}
			if(method==null){
				method= getMethodByNameFromObj(obj, propKey);
				if(method==null){
					return vals;
				}
			}
			if(methodVal==null){
				methodVal= getMethodByNameFromObj(obj, propVal);
				if(methodVal==null){
					return vals;
				}
			}
			try{
				K retnObj = (K)method.invoke(obj);
				V retnObjVal = (V)methodVal.invoke(obj);
				if(retnObjVal!=null){
					vals.put(retnObj, retnObjVal);
				}
			}catch(Exception e){
				log.info(method.getDeclaringClass().getName()+" invoke fail methodName="+method.getName());
				return vals;
			}
		}
		return vals;
	}

	/**
	 *  map过滤
	 * @param keys
	 * @param saveOrRemove true,save keys. false, remove keys
	 * @return
	 * @author justin.li
	 * @date 2017年6月10日
	 */
	public static <V, T> Map<Long, V> mapNewRemoveLong(Map<Long, V> map, Collection<Long> keys, boolean saveOrRemove){
		if(MapUtils.isEmpty(map) && CollectionUtils.isEmpty(keys)){
			return map;
		}
		Map<Long, V> vals = new LinkedHashMap<Long, V>();
		for(Entry<Long, V> enty : map.entrySet()){
			Long key = Long.valueOf(enty.getKey()+"");
			if(saveOrRemove && keys.contains(key)){
				vals.put(key, enty.getValue());
			}else if(!saveOrRemove && !(keys.contains(key))){
				vals.put(key, enty.getValue());
			}
		}

		return vals;
	}

	/**
	 *  添加子集合
	 * @param all
	 * @param sub
	 * @return
	 * @author justin.li
	 * @date 2017年6月10日
	 */
	public static <T> Set<T> addAllSet(Set<T> all, Set<T> sub){
		if(CollectionUtils.isEmpty(all) && CollectionUtils.isEmpty(sub)){
			return all;
		}
		if(CollectionUtils.isEmpty(all)){
			all = new HashSet<>();
		}
		all.addAll(sub);
		return all;
	}

	/**
	 *  添加子列表
	 * @param all
	 * @param sub
	 * @return
	 * @author justin.li
	 * @date 2017年6月10日
	 */
	public static <T> List<T> addAllList(List<T> all, List<T> sub){
		if(CollectionUtils.isEmpty(all) && CollectionUtils.isEmpty(sub)){
			return all;
		}
		if(CollectionUtils.isEmpty(all)){
			all = new ArrayList<>();
		}
		all.addAll(sub);
		return all;
	}

	public static <T> T getFromCollection(List<T> list){
		if(CollectionUtils.isNotEmpty(list)){
			return list.get(0);
		}
		return null;
	}

	/**
	 * 抽取对象列表的属性值
	 * @param objs 需要抽取属性值的对象列表
	 * @param prop 属性名称或者方法名称
	 * @param cl 属性的数据类型
	 * @return 属性值集合
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static <T> Set<T> abstractVal(List objs, String prop, Class<T> cl){
		Set<T> vals = new HashSet<T>();
		if(CollectionUtils.isEmpty(objs) || StringUtils.isBlank(prop)){
			return vals;
		}

		// 属性方法
		Method method=null;
		for(Object obj: objs){
			if(obj==null){
				continue;
			}
			if(method==null){
				method= getMethodByNameFromObj(obj, prop);
				if(method==null){
					return vals;
				}
			}
			try{
				Object retnObj = (Object)method.invoke(obj);
				if(retnObj!=null){
					if(retnObj instanceof String){
						if(StringUtils.isBlank((String)retnObj)){
							continue;
						}
					}
					vals.add((T)retnObj);
				}
			}catch(Exception e){
				log.info(method.getDeclaringClass().getName()+" invoke fail methodName="+method.getName());
				return vals;
			}
		}

		return vals;
	}


	/**
	 * 批量抽取对象列表的属性
	 * @param objs 需要抽取属性值的对象列表
	 * @param propMap 键为属性名称，值为抽取的结果需要存在的集合
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void abstractBatch(List objs, Map<String, Set> propMap){
		if(CollectionUtils.isEmpty(objs) || MapUtils.isEmpty(propMap)){
			return ;
		}

		Map<String, Method> propMethod = new HashMap<String, Method>();

		Method method= null;
		for(Object obj: objs){
			if(obj==null){
				continue;
			}
			for(Entry<String, Set> enty : propMap.entrySet()){
				String key = enty.getKey();
				Set val = enty.getValue();
				if(key== null){
					continue;
				}
				method = propMethod.get(key);
				if(method==null){
					method= getMethodByNameFromObj(obj, key);
					if(method==null){
						return;
					}
				}
				try{
					Object retnObj = (Object)method.invoke(obj);
					if(retnObj!=null){
						val.add(retnObj);
					}
				}catch(Exception e){
					log.info(method.getDeclaringClass().getName()+" invoke fail methodName="+method.getName());
					return ;
				}
			}
		}

		return ;
	}



	/**
	 * 将list中对象的属性值设置到另一个list对象的属性中。批量属性
	 * 以用户所属的城为例，来描述注释，用户有城市id，现在需要在用户列表显示用户所属的城市名称
	 调用示例：			Map<String, String> distOrigPropMap = new HashMap<String, String>();
			distOrigPropMap.put("cityname", "name");
			distOrigPropMap.put("cityname2", "name2");
			Map<String, Class> distPropClaMap = new HashMap<String, Class>();
			distPropClaMap.put("cityname", String.class);
			distPropClaMap.put("cityname2", String.class);
			giveVal(cs, "id", ps, "cityid", distOrigPropMap, distPropClaMap);
	 * @param origObjs 源对象列表(例：城市列表citys)
	 * @param origMarkProp 源标对象的标识属性。即城市id在城市表中的列(例：城市id)
	 * @param distObjs 目标对象列表(例：用户列表persons)
	 * @param distMarkProp 目标对象的标识属性。即城市id在用户表中的列
	 * @param distOrigPropMap 目标对象的属性与源对象属性的映射。即用户类中的属性名称“城市名称”与城市表中的属性“名称”的映射
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void giveVal(List origObjs, String origMarkProp, List distObjs,
			String distMarkProp, Map<String, String> distOrigPropMap, Map<String, Class> distPropClaMap){
		if(CollectionUtils.isEmpty(origObjs) || CollectionUtils.isEmpty(distObjs)
				|| MapUtils.isEmpty(distOrigPropMap) || StringUtils.isBlank(origMarkProp)
				|| StringUtils.isBlank(distMarkProp)){
			return ;
		}

		Map origValMap = new HashMap(); // 源数据对象的标识，对象映射。即城市id与城市对象的映射关系

		Method origMarkGetMethod= getMethodByName(origObjs.get(0).getClass(), origMarkProp);
		if(origMarkGetMethod==null){
			return;
		}
		for(Object obj: origObjs){ // 将源数据转换为map
			if(obj==null){
				continue;
			}
			try{
				Object retnObj = (Object)origMarkGetMethod.invoke(obj);
				if(retnObj!=null){
					origValMap.put(retnObj, obj);
				}
			}catch(Exception e){
				log.info(origMarkGetMethod.getDeclaringClass().getName()+" invoke fail methodName="+origMarkGetMethod.getName());
				return ;
			}
		}
		Map<String, Method> origPropGetMethodMap = new HashMap<String, Method>(); // 源属性，方法映射
		Map<String, Method> distPropSetMethodMap = new HashMap<String, Method>(); // 目标属性，方法映射
		Method distMarkGetMethod= getMethodByName(distObjs.get(0).getClass(), distMarkProp); // 目标对象的外键标识的get方法，用来获取标识值。即城市id
		if(distMarkGetMethod== null){
			if(distMarkGetMethod==null){
				return ;
			}
		}
		for(Object distObj: distObjs){
			if(distObj==null){
				continue;
			}
			Object distMark= null;
			try{
				distMark=distMarkGetMethod.invoke(distObj); // 目标标识，即用户表中的城市id
			}catch(Exception e){
				log.info("invoke val fail", e);
				return;
			}
			if(distMark==null){ // 用户表的城市id为空
				continue;
			}
			Object origObj = origValMap.get(distMark); // 源对象，即城市对象。每个用户都有一个城市id
			if(origObj==null){ // 城市表的城市id对应的记录为空
				continue;
			}
			for(Entry<String, String> enty : distOrigPropMap.entrySet()){ // 给属性设置值
				String origProp = enty.getValue();
				String distProp = enty.getKey();
				Method origPropGetMethod= origPropGetMethodMap.get(origProp);
				if(origPropGetMethod==null){
					origPropGetMethod = getMethodByNameFromObj(origObj, origProp);
					if(origPropGetMethod==null){
						continue;
					}
					origPropGetMethodMap.put(origProp, origPropGetMethod);
				}
				Method distPropGetMethod= distPropSetMethodMap.get(distProp);
				if(distPropGetMethod==null){
					distPropGetMethod = setMethodByNameFromObj(distObj, distProp, distPropClaMap.get(distProp));
					if(distPropGetMethod==null){
						continue;
					}
					distPropSetMethodMap.put(distProp, distPropGetMethod);
				}
				try{
					Object origPropVal= origPropGetMethod.invoke(origObj);
					if(origPropVal!=null){
						distPropGetMethod.invoke(distObj, origPropVal);
					}
				}catch(Exception e){
					log.info("invoke val fail", e);
					return ;
				}
			}
		}
		return ;
	}

	/**
	 * 将list中对象的属性值设置到另一个list对象的属性中。单个属性
	 * 以用户所属的城为例，来描述注释，用户有城市id，现在需要在用户(Person)列表显示用户所属的城市(City)名称
	 * 调用示例：giveVal(cs, "id", ps, "cityid", "name", "cityname", String.class);
			giveVal(cs, "id", ps, "cityid", "name2", "cityname2", String.class);
	 * @param origObjs 源对象列表(例：城市列表citys)
	 * @param origMarkProp 源标对象的标识属性。即城市id在城市表中的列(例：城市id)
	 * @param distObjs 目标对象列表(例：用户列表persons)
	 * @param distMarkProp 目标对象的标识属性。即城市id在用户表中的列(例：Person类的成员变量属性名称cityid，存放的是city的id)
	 * @param origProp 源对象要被取值的属性名，比如，城市名称(例：城市类的成员变量属性名称name)
	 * @param distProp 目标对象要被设值的属性名，比如，用户的城市名称(例：Person类中存放城市名称的成员变量的名称“cityname”）
	 * @param distPropCla 目标对象要被设值的属，比如，用户的城市名称，应该是String类型(例：Person类中存放城市名称的变量的数据类型)
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void giveVal(List origObjs, String origMarkProp, List distObjs,
			String distMarkProp, String origProp, String distProp, Class distPropCla){
		if(CollectionUtils.isEmpty(origObjs) || StringUtils.isBlank(origMarkProp)
				|| CollectionUtils.isEmpty(distObjs) || StringUtils.isBlank(distMarkProp)
				|| StringUtils.isBlank(origProp) || StringUtils.isBlank(distProp) || distPropCla==null){
			return ;
		}

		Map origValMap = new HashMap(); // 源数据对象的标识，对象映射。即城市id与城市对象的映射关系
		Method origMarkGetMethod= getMethodByName(origObjs.get(0).getClass(), origMarkProp); // 源对象的标识方法，即getId()
		if(origMarkGetMethod==null){
			return;
		}
		for(Object obj: origObjs){ // 将源数据转换为map
			if(obj==null){
				continue;
			}
			try{
				Object retnObj = (Object)origMarkGetMethod.invoke(obj);
				if(retnObj!=null){
					origValMap.put(retnObj, obj);
				}
			}catch(Exception e){
				log.info(origMarkGetMethod.getDeclaringClass().getName()+" invoke fail methodName="+origMarkGetMethod.getName());
				return ;
			}
		}
		Method origPropGetMethod= getMethodByNameFromObj(origObjs.get(0), origProp);
		Method distMarkGetMethod= getMethodByName(distObjs.get(0).getClass(), distMarkProp); // 目标对象的外键标识的get方法，用来获取标识值。即城市id
		Method distPropSetMethod= setMethodByNameFromObj(distObjs.get(0), distProp, distPropCla);
		if(origPropGetMethod== null || distMarkGetMethod== null || distMarkGetMethod==null){
			return ;
		}
		for(Object distObj: distObjs){
			if(distObj==null){
				continue;
			}
			Object distMark= null;
			try{
				distMark=distMarkGetMethod.invoke(distObj); // 目标标识，即用户表中的城市id
			}catch(Exception e){
				log.info("invoke val fail", e);
				return;
			}
			if(distMark==null){ // 用户表的城市id为空
				continue;
			}
			Object origObj = origValMap.get(distMark); // 源对象，即城市对象。每个用户都有一个城市id
			if(origObj==null){ // 城市表的城市id对应的记录为空
				continue;
			}
			try{
				Object origPropVal= origPropGetMethod.invoke(origObj);
				if(origPropVal!=null){
					distPropSetMethod.invoke(distObj, origPropVal);
				}
			}catch(Exception e){
				log.info("invoke val fail", e);
				return ;
			}
		}
		return ;
	}


	/**
	 * 获取bean对象的get方法
	 * @param obj 对象
	 * @param prop 对象的属性
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Method getMethodByNameFromObj(Object obj, String prop){
		if(obj == null || StringUtils.isBlank(prop)){
			return null;
		}
		Class cla=obj.getClass();
		return getMethodByName(cla, prop);
	}


	/**
	 * 获取bean对象的get方法
	 * @param cla 对象的类
	 * @param prop 对象的属性
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Method getMethodByName(Class cla, String prop){
		String methodName= "get"+prop.substring(0,1).toUpperCase()+prop.substring(1);
		Method method=null;
		try{
			method=cla.getMethod(methodName);
		}catch(Exception e){
			try{
				method=cla.getMethod(prop);
			}catch(Exception e2){
				log.info(cla.getName()+" inexistence methodName "+prop+","+methodName);
			}
		}
		return method;
	}


	/**
	 * 获取bean对象的set方法
	 * @param obj 对象
	 * @param prop 对象的属性
	 * @param valCla 对象的属性的类
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Method setMethodByNameFromObj(Object obj, String prop, Class valCla){
		if(obj == null || StringUtils.isBlank(prop)){
			return null;
		}
		Class cla=obj.getClass();
		return setMethodByName(cla, prop, valCla);
	}

	// 收集bit位的值
	public static List<Long> splitBit(long src){
		List<Long> bi= new ArrayList<Long>();
		while(src>0){
			long v=-src&src;
			bi.add(v);
			src-=v;
		}
		return bi;
	}

	/**
	 * 获取bean对象的set方法
	 * @param cla 对象的类
	 * @param prop 对象的属性
	 * @param valCla 对象的属性的类
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Method setMethodByName(Class cla, String prop, Class valCla){
		String methodName= "set"+prop.substring(0,1).toUpperCase()+prop.substring(1);
		Method method=null;
		try{
			method=cla.getMethod(methodName, valCla);
		}catch(Exception e){
			try{
				method=cla.getMethod(prop, valCla);
			}catch(Exception e2){
				log.info(cla.getName()+" inexistence methodName "+prop+","+methodName+",valCla="+valCla);
			}
		}
		return method;
	}


	/**
	 * 获取所有的请求内容
	 * @param request
	 * @return
	 */
	public static String getReq(HttpServletRequest request) {
		StringBuilder sb = new StringBuilder();
		BufferedInputStream bis = null;
		InputStream is = null;
		try{
			is = request.getInputStream();
			bis = new BufferedInputStream(is);
			byte[] tmp = new byte[4096];
			int n = 0;
			while((n=bis.read(tmp))> 0){
				String temp = new String(tmp,0,n);
				sb.append(temp);
			}
		}catch(Exception e){
			log.error(e.getMessage(), e);
		}finally{
			try{
				if(bis!=null){
					bis.close();
				}
				if(is!=null){
					is.close();
				}
			}catch(Exception e){
				log.error(e.getMessage(), e);
			}
		}
		return sb.toString();
	}

	public static void printStackTrace() {
		StringBuilder sb = new StringBuilder();
		try{
	        StackTraceElement[] stackElements = new Throwable().getStackTrace();
	        if(stackElements != null) {
	            for(int i = 0, leng=stackElements.length; i < leng && i<200; i++) {
	            	sb.append(""+ stackElements[i]);
	            }
	        }
		}finally{

		}
		log.info("printStackTrace run "+sb);
    }


	public static String stackTrace() {
		log.info("stackTrace run");
		StringBuilder sb = new StringBuilder();
		try{
	        StackTraceElement[] stackElements = new Throwable().getStackTrace();
	        if(stackElements != null) {
	            for(int i = 0, leng=stackElements.length; i < leng && i<200; i++) {
	            	sb.append(stackElements[i]).append("\n");
	            }
	        }
		}finally{

		}
		return sb.toString();
    }

	public static void grabHolidayByDay(String start, String end){
		String httpUrl = "http://apis.baidu.com/xiaogg/holiday/holiday";

		Calendar cal = Calendar.getInstance();
		// long currTime= System.currentTimeMillis();
		try{
			cal.setTime(DateUtils.parseDate(start, "yyyyMMdd"));
			Date endDate = DateUtils.parseDate(end, "yyyyMMdd");
			for(int i=0, leng=500;i<leng;++i){
				if(cal.getTimeInMillis() > endDate.getTime()){
					return;
				}
				String time= DateUtil.format(cal.getTime(), DateUtil.FORMAT_DATE_SHORT);
				String httpArg = "d="+time;

				String jsonResult = grabHoliday(httpUrl, httpArg);
				if(jsonResult==null){
					return ;
				}
				jsonResult=jsonResult.trim();
				System.out.println("insert into legalholiday(id, type, holidaydate) value ("+time+","+jsonResult+","+time+");");
				cal.add(Calendar.DAY_OF_MONTH, 1);
			}
		}catch(Exception e){
	        log.info(e.getMessage(), e);
		}
	}

	/**
	 * @param urlAll
	 *            :请求接口
	 * @param httpArg
	 *            :参数
	 * @return 返回结果
	 */
	public static String grabHoliday(String httpUrl, String httpArg) {
	    BufferedReader reader = null;
	    String result = null;
	    StringBuffer sbf = new StringBuffer();
	    httpUrl = httpUrl + "?" + httpArg;

	    try {
	        URL url = new URL(httpUrl);
	        HttpURLConnection connection = (HttpURLConnection) url
	                .openConnection();
	        connection.setRequestMethod("GET");
	        // 填入apikey到HTTP header
	        // connection.setRequestProperty("apikey",  "您自己的apikey");
	        // connection.setRequestProperty("apikey",  "3b85bbc8cef8fc1252965179c44ab95b");
	        connection.setRequestProperty("apikey",  "4705303f6de7fe58fe5690a91b008ab8");

	        connection.connect();
	        InputStream is = connection.getInputStream();
	        reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
	        String strRead = null;
	        while ((strRead = reader.readLine()) != null) {
	            sbf.append(strRead);
	            sbf.append("\r\n");
	        }
	        reader.close();
	        result = sbf.toString();
	    } catch (Exception e) {
	        log.info(e.getMessage(), e);
	    }
	    return result;
	}


	/**
	 * 判断给定的点是否在给定的区域内
	 * @param point, 需要判断的坐标点。一般指一个地理位置，比如一个小区，一个大厦。经纬度用英文逗号分隔，经度在前，纬度在后。经纬度用“度数表示法”。示例：121.55411,31.396198
	 * @param area, 区域的坐标点。多个坐标用英文分号分隔。经纬度格式同point参数
	 * @return
	 */
	public static boolean pointInArea(String point, String area){
		List<String> areaList=  Arrays.asList(area.split(";"));

		String[] ts= point.split(",");
		if(ts.length<2){
			log.info("error point="+point);
			return false;
		}
		double lng= Double.valueOf(ts[0]);
		double lat= Double.valueOf(ts[1]);
		return pointInArea(lng, lat, areaList);
	}
	public static boolean pointInArea(double lng, double lat, List<String> area){
		if(StringUtils.isBlank(area.get(area.size()-1))){
			area.remove(area.size()-1);
		}
		boolean oddNodes = false;
		for (int i = 0, l=area.size(), j=l-1; i < l; i++) {
			String[] ps1 = area.get(i).split(",");
			String[] ps0 = area.get(j).split(",");
			if(ps1.length<2 || ps0.length<2){
				log.info("lack coords "+area.get(i)+" or "+area.get(j));
				continue;
			}
			double pd1lng= Double.valueOf(ps1[0].trim());
			double pd1lat= Double.valueOf(ps1[1].trim());

			double pd0lng= Double.valueOf(ps0[0].trim());
			double pd0lat= Double.valueOf(ps0[1].trim());

			if (pd1lat < lat && pd0lat >= lat || pd0lat < lat && pd1lat >= lat) {
				if (pd1lng +  (lat - pd1lat) / (pd0lat - pd1lat) * (pd0lng - pd1lng) < lng) {
					oddNodes = !oddNodes;
				}
			}
			j = i;
		}

		return oddNodes;

	}

	public static String getFirstFromArray(Map<String, String[]> reqMap, String key){
		if(reqMap!=null && reqMap.get(key)!=null){
			return ((String[])reqMap.get(key))[0];
		}
		return null;
	}

	/**
	 * 获取唯一标识码
	 * @return
	 * @author justin.li
	 * @date 2017年4月11日
	 */
	public static String getUUID(){
		UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
        // 去掉"-"符号
        //String temp = str.substring(0, 8) + str.substring(9, 13) + str.substring(14, 18) + str.substring(19, 23) + str.substring(24);
        //return temp;
        StringBuilder sb = new StringBuilder(str.substring(0, 8)).append(str.substring(9, 13)).append(str.substring(14, 18))
        		.append(str.substring(19, 23)).append(str.substring(24));
        return sb.toString();
	}

    /**
     * 将list转化为用逗号分隔的字符串
     *
     * @param list
     * @return string 逗号分隔的字符串
     * @author Tony
     * @date 2016年04月22日 11:13
     */
    public static String list2string(List<Object> list) {
        StringBuilder sb = new StringBuilder();
        if (CollectionUtils.isNotEmpty(list)) {
            for (Object o : list) {
                sb.append(o.toString()).append(",");
            }
        }
        String str = sb.toString();
        int len = str.length();
        if (len > 0) {
            str = str.substring(0, len - 1);
        }
        return str;
    }


    /**
     * 按UTF-8格式读取文件为一个内存字符串,保持文件原有的换行格式
     * @param filePath 文件路径
     * @return 文件内容的字符串
     */
	public static String file2String(String filePath) {
		String str = file2String(filePath, "UTF-8");
		return str;
	}

    /**
     * 读取文件为一个内存字符串,保持文件原有的换行格式
     * @param filePath 文件路径
     * @param charset 文件字符集编码
     * @return 文件内容的字符串
     */
	public static String file2String(String filePath, String charset) {
		File fileObj = new File(filePath);
		String str = file2String(fileObj, charset);
		return str;
	}

    /**
     * 读取文件为一个内存字符串,保持文件原有的换行格式
     * @param file 文件对象
     * @param charset 文件字符集编码
     * @return 文件内容的字符串
     */
	public static String file2String(File file, String charset) {
		StringBuffer sb = new StringBuffer();
		LineNumberReader reader = null;
		try {
			reader = new LineNumberReader(new InputStreamReader(
					new FileInputStream(file), charset));
			String line;
			while ((line = reader.readLine()) != null) {
				sb.append(line).append(System.getProperty("line.separator"));
			}
		} catch (UnsupportedEncodingException e) {
			log.error("读取文件为一个内存字符串失败，失败原因是使用了不支持的字符编码" + charset, e);
			return null;
		} catch (FileNotFoundException e) {
			log.error("读取文件为一个内存字符串失败，失败原因所给的文件" + file + "不存在！", e);
			return null;
		} catch (IOException e) {
			log.error("读取文件为一个内存字符串失败，失败原因是读取文件异常！", e);
			return null;
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (Exception e) {
				log.error("关闭文件异常！", e);
			}
		}
		return sb.toString();
	}

	public static byte[] str2byte(String str){
		if(str==null){
			return null;
		}
		try{
			return str.getBytes(CHARCODE);
		}catch (Exception e) {
			log.error("转换失败", e);
			return null;
		}
	}

	/**
	 * 将字节数组加密成base64字符串
	 * @param src 明文的字节数组
	 * @return 经过base64计算后的base64格式的字符串
	 */
	public static String base64Encode(byte[] src) {
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(src);
	}

	/**
	 * 将字符串转换成字节数组加密成base64字符串
	 * @param src 明文的字节数组
	 * @return 经过base64计算后的base64格式的字符串
	 */
	public static String base64Encode(String src) {
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(str2byte(src));
	}

	/**
	 * 将base64字符串解密成字节数组
	 * @param base64 base64格式的字符串
	 * @return 解密后的字节数组
	 */
	public static byte[] base64Decode(String base64) {
		BASE64Decoder decoder = new BASE64Decoder();
        try {
            return decoder.decodeBuffer(base64);
        } catch (IOException io) {
        	throw new RuntimeException(io.getMessage(), io.getCause());
        }
	}


	/**
	 * 将base64字符串解密成字节数组
	 * @param base64 base64格式的字符串
	 * @return 解密后的字节数组
	 */
	public static String base64Decode2str(String base64) {
		BASE64Decoder decoder = new BASE64Decoder();
        try {
        	byte[] b1= decoder.decodeBuffer(base64);
        	return new String(b1, CHARCODE);
        } catch (IOException io) {
        	throw new RuntimeException(io.getMessage(), io.getCause());
        }
	}

	/**
	 * 将base64字符串解密成字符串
	 * 先将base64字符串解密成字节数组，再用UTF-8编码成字符串
	 * @param base64 base64格式的字符串
	 * @return 解密后的字符串
	 */
	public static String decodeToString(String base64)
	{
	    try {
			return new String(base64Decode(base64),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 将字符串加密成base64字符串
	 * 先将字符串用UTF-8解码成字节数组， 再加密成base64字符串
	 * @param base64 base64格式的字符串
	 * @return 解密后的字符串
	 */
	public static String encodeString(String plaintext){
	    try {
			return base64Encode(plaintext.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 将二进制转换成16进制
	 *
	 * @param buf 字节数组
	 * @return 16进制字符串
	 */
	public static String parseByte2HexStr(byte buf[]) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < buf.length; i++) {
			String hex = Integer.toHexString(buf[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			sb.append(hex.toUpperCase());
		}
		return sb.toString();
	}

	/**
	 * 将16进制转换为二进制
	 *
	 * @param hexStr 16进制字符串
	 * @return
	 */
	public static byte[] parseHexStr2Byte(String hexStr) {
		if (hexStr.length() < 1)
			return null;
		byte[] result = new byte[hexStr.length() / 2];
		for (int i = 0; i < hexStr.length() / 2; i++) {
			int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
			int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2),
					16);
			result[i] = (byte) (high * 16 + low);
		}
		return result;
	}

	public static String base64hex(String base64){
		try{
			byte[] b1 = base64Decode(base64);
			String hex=parseByte2HexStr(b1);
			return hex;
		} catch (Exception e) {
			log.info(e.getMessage(),e);
		}
		return null;
	}

	public static String hex2base64(String hex){
		try{
			byte[] b1 = parseHexStr2Byte(hex);
			String base64=base64Encode(b1);
			return base64;
		} catch (Exception e) {
			log.info(e.getMessage(),e);
		}
		return null;
	}

	/**
	 * RSA算法加密
	 * @param keyBytes 密钥（一般为公钥）
	 * @param plaintext 明文
	 * @return
	 */
	public static byte[] rsaEncrypt(byte[] keyBytes, String plaintext){
		return rsaCalc(keyBytes, plaintext, Cipher.ENCRYPT_MODE);
	}


	/**
	 * RSA算法解密
	 * @param keyBytes 密钥（一般为私钥）
	 * @param hexStr 16进制的密文
	 * @return
	 */
	public static byte[] rsaDecrypt(byte[] keyBytes, String hexStr){
		return rsaCalc(keyBytes, hexStr, Cipher.DECRYPT_MODE);
	}

	/**
	 * RSA算法加解密
	 * mode:
	 * @param keyBytes 字节数组格式的公钥或者私钥
	 * @param str 明文或者密文
	 * @param mode Cipher.DECRYPT_MODE, Cipher.ENCRYPT_MODE
	 * @return
	 */
	public static byte[] rsaCalc(byte[] keyBytes, String str, int mode){
		byte[] b;
		try {
			KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
			Key key =null;
			if(mode==Cipher.DECRYPT_MODE){
				PKCS8EncodedKeySpec keyObj = new PKCS8EncodedKeySpec(keyBytes);
				key=keyFactory.generatePrivate(keyObj);
				b=parseHexStr2Byte(str);
			 }else{
				X509EncodedKeySpec keyObj = new X509EncodedKeySpec(keyBytes);
				key=keyFactory.generatePublic(keyObj);
				b=str.getBytes("UTF-8");
			 }
			 Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
			 cipher.init(mode, key);
			 byte[] b1 = cipher.doFinal(b);
			 return b1;
		} catch (Exception e) {
			log.info(e.getMessage(),e);
		}
		return null;
	}

	/**
	 * 将明文字符串RSA加密再转为base64
	 * @param key 公钥
	 * @param plaintext 明文
	 * @return
	 */
	public static String rsaEncrypt(Key key, String plaintext){
		 try{
			 // Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
			 Cipher cipher = Cipher.getInstance(ALGORITHM);
			 
			 cipher.init(Cipher.ENCRYPT_MODE, key);
			 byte[] plaintextByte=plaintext.getBytes("UTF-8");
			 byte[] resultBytes = null;
			 int stepLeng=  64;
			 for (int i = 0, leng=plaintextByte.length; i < leng; i += stepLeng) {
				 byte[] plaintextSeg= ArrayUtils.subarray(plaintextByte, i,(i + stepLeng)>leng?leng:(i+stepLeng));
				 // 注意要使用2的倍数，否则会出现加密后的内容再解密时为乱码
				 byte[] doFinal = cipher.doFinal(plaintextSeg);  
				 resultBytes = ArrayUtils.addAll(resultBytes, doFinal);
			 }
			 return base64Encode(resultBytes);
		 }catch(Exception e){
			 log.info(e.getMessage(),e);
		 }
		 return null;
	}
	
	/**
	 * 将base64转为字节数组,rsa解密，再转为字符串
	 * @param priv
	 * @param base64ciphertext
	 * @return
	 */
	public static String rsaDecrypt(Key priv, String base64ciphertext){
		 try{
			 Cipher cipher = Cipher.getInstance(ALGORITHM);
			 cipher.init(Cipher.DECRYPT_MODE, priv);
			 byte[] resultBytes=null;
			 byte[] cipherByte= base64Decode(base64ciphertext);
			 int stepLeng=  128;
			 for (int i = 0, leng=cipherByte.length; i < leng; i += stepLeng) {
				 byte[] seg= ArrayUtils.subarray(cipherByte, i,(i + stepLeng)>leng?leng:(i+stepLeng));
				 byte[] doFinal = cipher.doFinal(seg);  
				 resultBytes = ArrayUtils.addAll(resultBytes, doFinal);
			 }

			 return new String(resultBytes, "UTF-8");
		 }catch(Exception e){
			 log.info(e.getMessage(),e);
		 }
		 return null;
	}
	

	/**
	 * 摘要算法，字符串进，16进制出
	 * @param plaintext 明文字节数组
	 * @param digestAlg 算法
	 * @return 密文字节数组
	 * @throws Exception
	 */
	public static byte[] digest(byte[] plaintext,MessageDigest digestAlg)
			throws Exception{
		digestAlg.update(plaintext);
		// 获得密文
		byte[] digestVal = digestAlg.digest();
		//String ciphertext = Hex.encodeHexString(digestVal);
		return digestVal;
	}
	
	/**
	 * SHA512摘要算法
	 * 摘要字节数组
	 */
	public static byte[] sha512(byte[] plaintext) {
		try {
			MessageDigest mdInst = MessageDigest.getInstance("SHA-512");
			// 获得密文
			byte[] ciphertext =digest(plaintext,mdInst);
			return ciphertext;
		} catch (Exception e) {
			log.info(e.getMessage(), e);
		}
		return null;
	}


	/**
	 * 将字符串加签并转为base64格式
	 * @param str 明文
	 * @param privateKey
	 * @return base64格式的签名结果
	 */
	public static String sign2base64SHA1WITHRSA(String str, PrivateKey privateKey){
		try{
			byte[] text = str.getBytes(CHARCODE);
			return sign2base64SHA1WITHRSA(text, privateKey);
		}catch(Exception e){
			log.info(e.getMessage(), e);
		}
		return null;
	}

	public static String sign2base64SHA1WITHRSA(byte[] text, PrivateKey privateKey){
		try{
			Signature signatureChecker = Signature.getInstance("SHA1WITHRSA");
			signatureChecker.initSign(privateKey);
			signatureChecker.update(text);
			byte[] signbyte= signatureChecker.sign();
			return base64Encode(signbyte);
		}catch(Exception e){
			log.info(e.getMessage(), e);
		}
		return null;
	}
	
	/**
	 * SHA1WITHRSA加签
	 * @param str 明文
	 * @param privateKeyData RSA的PKCS8私钥
	 * @return 签名摘要
	 */
	public static byte[] signSHA1WITHRSA(String str, final byte[] privateKeyData)  {
		try{
			byte[] text = str.getBytes(CHARCODE);
			PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKeyData);
			KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
			PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
			Signature signatureChecker = Signature.getInstance("SHA1WITHRSA");
			signatureChecker.initSign(privateKey);
			signatureChecker.update(text);
			return signatureChecker.sign();
		}catch(Exception e){
			log.info(e.getMessage(), e);
		}
		return null;
	}

	/**
	 * 验签
	 * @param str 待验签明文
	 * @param signedBase64 base64格式的摘要
	 * @param publicKey 公钥
	 * @return 验签是否成功
	 */
	public static boolean verifyByBase64SHA1WITHRSA(String str, String signedBase64, PublicKey key) {
		try{
			byte[] text = str.getBytes(CHARCODE);
			Signature signatureChecker = Signature.getInstance("SHA1WITHRSA");
			signatureChecker.initVerify(key);
			signatureChecker.update(text);
			return signatureChecker.verify(base64Decode(signedBase64));
		}catch(Exception e){
			log.info(e.getMessage(), e);
		}
		return false;
	}
	
	/**
	 * 验签
	 * @param str 待验签明文
	 * @param signedText 字符串格式的摘要
	 * @param publicKeyData PKCS8格式的公钥
	 * @return 验签是否成功
	 */
	public static boolean verifySHA1WITHRSA(String str, final byte[] signedText, final byte[] publicKeyData) {
		try{
			byte[] text = str.getBytes(CHARCODE);
			X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyData);
			KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
			PublicKey publicKey = keyFactory.generatePublic(keySpec);
			Signature signatureChecker = Signature.getInstance("SHA1WITHRSA");
			signatureChecker.initVerify(publicKey);
			signatureChecker.update(text);
			// return signatureChecker.verify(signedText);
			return true;
		}catch(Exception e){
			log.info(e.getMessage(), e);
		}
		return false;
	}
	
	/**
	 * 从PKCS#1格式的密钥文件中加载密钥（公钥或私钥）
	 * @param filePath 文件路径
	 * @return base64格式的密钥
	 */
	public static String base64FromPKCS1File(String filePath){
		String file = file2String(filePath);
		if(StringUtils.isBlank(file)){
			return null;
		}
		String[] txt= file.split(System.getProperty("line.separator"));
		StringBuilder sb = new StringBuilder();
		for(String str : txt){
			if(str.startsWith("-----")){
				continue;
			}
			sb.append(str);
		}

		return sb.toString();
	}

	/**
	 * 从PKCS#1格式的私钥文件中加载私钥
	 * 生成PKCS1格式的公钥： openssl genrsa -out rsa_pkcs1_private_key.pem 1024
	 * @param filePath
	 * @return
	 */
	public static PrivateKey loadRsaPrivKeyPKCS1(String base64key){
		try{
			byte[] keyBytes = base64Decode(base64key);
			RSAPrivateKeyStructure asn1PrivKey = new RSAPrivateKeyStructure((ASN1Sequence) ASN1Sequence.fromByteArray(keyBytes));  
			RSAPrivateKeySpec rsaPrivKeySpec = new RSAPrivateKeySpec(asn1PrivKey.getModulus(), asn1PrivKey.getPrivateExponent());  
			KeyFactory keyFactory= KeyFactory.getInstance("RSA");  
			PrivateKey priKey= keyFactory.generatePrivate(rsaPrivKeySpec);  
			return priKey;
		}catch(Exception e){
			log.info(e.getMessage(), e);
		}
		return null;
	}

	/**
	 * 暂不使用，
	 * openssl rsa -in rsa_pkcs1_private_key.pem -pubout -out rsa_pkcs1_public_key.pem 生成的公钥，不是PKCS1格式
	 * 从PKCS#1格式的私钥文件中加载公钥
	 * @param filePath
	 * @return
	 */
	public static PublicKey loadRsaPubKeyPKCS1(String base64key){
		try{
			byte[] keyBytes = base64Decode(base64key);
			RSAPublicKeyStructure asn1Key = new RSAPublicKeyStructure((ASN1Sequence) ASN1Sequence.fromByteArray(keyBytes));  
			RSAPublicKeySpec rsaKeySpec = new RSAPublicKeySpec(asn1Key.getModulus(), asn1Key.getPublicExponent());  
			KeyFactory keyFactory= KeyFactory.getInstance("RSA");  
			PublicKey priKey= keyFactory.generatePublic(rsaKeySpec);  
			return priKey;
		}catch(Exception e){
			log.info(e.getMessage(), e);
		}
		return null;
	}
	

	/**
	 * 无效，暂不使用
	 * 从PKCS#8格式的私钥文件中加载私钥
	 * 将PKCS1的私钥转为PKCS8格式的私钥： openssl pkcs8 -topk8 -inform PEM -in rsa_pkcs1_private_key.pem -outform PEM -nocrypt
	 * @param filePath
	 * @return
	public static PrivateKey loadRsaPrivKeyPKCS8(String base64key){
		try{
			byte[] keyBytes = base64Decode(base64key);
			RSAPrivateKeyStructure asn1PrivKey = new RSAPrivateKeyStructure((ASN1Sequence) ASN1Sequence.fromByteArray(keyBytes));  
			RSAPrivateKeySpec rsaPrivKeySpec = new RSAPrivateKeySpec(asn1PrivKey.getModulus(), asn1PrivKey.getPrivateExponent());  
			KeyFactory keyFactory= KeyFactory.getInstance("RSA");  
			PrivateKey priKey= keyFactory.generatePrivate(rsaPrivKeySpec);  
			return priKey;
		}catch(Exception e){
			log.info(e.getMessage(), e);
		}
		return null;
	}
	 */
	
	/** 
     * 从PKCS8格式的字符串中加载公钥 
	 * 通过PKCS1格式的私钥导出PKCS8的公钥： openssl rsa -in rsa_pkcs1_private_key.pem -pubout -out rsa_public_key.pem
     * @param base64pub base64格式的公钥数据字符串 
     * @throws Exception 加载公钥时产生的异常 
     */  
    public static PublicKey loadPublicKey(String base64pub){  
        try {  
            BASE64Decoder base64Decoder= new BASE64Decoder();  
            byte[] buffer= base64Decoder.decodeBuffer(base64pub);  
            KeyFactory keyFactory= KeyFactory.getInstance("RSA");  
            X509EncodedKeySpec keySpec= new X509EncodedKeySpec(buffer);  
            PublicKey publicKey= (RSAPublicKey) keyFactory.generatePublic(keySpec); 
            return publicKey;
        } catch (NoSuchAlgorithmException e) {  
            log.info("无此算法");  
        } catch (InvalidKeySpecException e) {  
        	log.info("公钥非法");  
        } catch (IOException e) {  
        	log.info("公钥数据内容读取错误");  
        } catch (NullPointerException e) {  
        	log.info("公钥数据为空");  
        } catch (Exception e) {  
        	log.info(e.getMessage(), e);  
        }
        return null;        
    }
    
    /**
     * PKCS8格式的字符串中加载私钥 
     * @param base64 base64格式的私钥数据字符串 
     * @return
     * @throws Exception
     */
    public static PrivateKey loadPrivateKey(String base64){  
        try {  
            BASE64Decoder base64Decoder= new BASE64Decoder();  
            byte[] buffer= base64Decoder.decodeBuffer(base64);  
            PKCS8EncodedKeySpec keySpec= new PKCS8EncodedKeySpec(buffer);  
            KeyFactory keyFactory= KeyFactory.getInstance("RSA");  
            PrivateKey privateKey= (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
            return privateKey;
        } catch (NoSuchAlgorithmException e) {  
        	log.info("无此算法");  
        } catch (InvalidKeySpecException e) {  
        	log.info("私钥非法");  
        } catch (IOException e) {  
        	log.info("私钥数据内容读取错误");  
        } catch (NullPointerException e) {  
        	log.info("私钥数据为空");  
        }  
        return null;
    }  

    
    /**
     * 同步对象。将orig的第一层属性同步到target对象中。只有同一类型才能复制
     * @param orig
     * @param target
     * @author justin.li
     * @date 2017年5月24日
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public static void syncBean(Object orig, Object target){
		Class clsOrig = orig.getClass();
    	Class clsTarget = target.getClass();
    	if(clsOrig!=clsTarget){
    		log.info("sync different class "+clsOrig.getName()+","+clsTarget.getName());
    		return;
    	}
    	Method[] metds=clsOrig.getMethods();
    	for(Method meth : metds){
    		String methName =meth.getName();
    		if(methName.startsWith("get")){
    			try{
    				Object objVal = meth.invoke(orig);
    				if(objVal!=null){
    					Method methSet = null;
    					try{
    						methSet= clsTarget.getMethod("set"+methName.substring(3, methName.length()), objVal.getClass());
    					}catch(Exception e){
    						
    					}
    					
    					if(methSet!=null){
    						methSet.invoke(target, objVal);
    					}
    				}
    			}catch(Exception e){
    	        	log.info(e.getMessage(), e);  
    				
    			}
    		}
    	}
    }
 
    // 下面是数字处理
    
    /**
     * @param number 判断基本数据类型或者包装类是否为null或者0
     * @return boolean
     * @author Jin
     * @date 2016年1月13日
     */
	public static boolean isEmpty(Number number){
		return number == null || number.doubleValue() == 0;
	}

    /**
     * @param number 判断基本数据类型或者包装类是否不为null或者不为0
     * @return boolean
     * @author Jin
     * @date 2016年1月13日
     */
	public static boolean isNotEmpty(Number number){
		return !isEmpty(number);
	}
	
	/**
     * @param number 判断基本数据类型或者包装类是否为null或者大于等于0
     * @return boolean
     * @author sourny
     * @date 2016年8月3日
     */
	public static boolean isGreaterThanOrEqualZero(Number number){
		return number == null || number.doubleValue() >= 0;
	}
	/**
     * @param number 判断基本数据类型或者包装类是否为null或者小于0
     * @return boolean
     * @author sourny
     * @date 2016年8月3日
     */
	public static boolean isLessThanZero(Number number){
		return number == null || number.doubleValue() < 0;
	}
	/** 字符按比率转数字
     * @param value 需要转换的值
     * @param ratio 转换比率
     * @param defaultValue 转换失败的默认值
     * @author sourny
     * @date 2016年8月25日
     */
	public static Integer transformValue(String value, String ratio, Integer defaultValue){
		Integer returnValue = defaultValue;
		try {
			if(StringUtils.isNotEmpty(value) && !"--".equals(value)){
				returnValue = CalculateUtil.multiply(value, ratio, 2).intValue();
				if(returnValue < 0){
					returnValue = defaultValue;
				}
			}
		} catch (Exception e) {
		}
		return returnValue;
	}
	
    /**
     * 将字符类型的小数转换成指定小数点右移后的整数
     * @param number 需要处理的字符串
     * @param index 小数点右移的位数
     * @return int int类型的分
     * @author Jin
     * @date 2016年3月11日
     */
	public static int str2int(String number,int index){
		int result = 0;
		if(StringUtils.isEmpty(number)){
			return 0;
		}
		String temp[] = number.split("\\.");
		//处理整数
		if(StringUtil.isInteger(temp[0])){
			result = new Integer(temp[0]) * (int)Math.pow(10, index);
		}
		//处理小数
		if(temp.length > 1 && StringUtil.isInteger(temp[1])){
			//为了处理0.01这种小数点右边是0的情况
			Integer decimals = new Integer("1"+temp[1]) * (int)Math.pow(10, index);
			//截取已经转换为整数的指定位数并合并到结果中
			result += new Integer(decimals.toString().substring(1, index + 1));
		}
		return result;
	}
	
	// 分转成元
	public static String cent2yuan(Long cent){
		if(cent==null){
			return "";
		}
		BigDecimal bigDec=BigDecimal.valueOf(cent).divide(new BigDecimal(100));
		DecimalFormat df =new DecimalFormat("#0.00"); 
		String yuan= df.format(bigDec);
		return yuan;
	}
	

	// 元
	public static String yuan2str(Integer money){
		if(money==null){
			return "0.00";
		}
		return yuan2str(Long.valueOf(money));
	}
	// 元
	public static String yuan2str(String money){
		if(money==null){
			return "0.00";
		}
		BigDecimal bigDec=new BigDecimal(money);
		DecimalFormat df =new DecimalFormat("#0.00"); 
		String yuan= df.format(bigDec);
		return yuan;
	}
	// 元
	public static String yuan2str(Long money){
		if(money==null){
			return "0.00";
		}
		BigDecimal bigDec=BigDecimal.valueOf(money);
		DecimalFormat df =new DecimalFormat("#0.00"); 
		String yuan= df.format(bigDec);
		return yuan;
	}
	
	public static String cent2yuanInt(Integer cent){
		if(cent==null){
			return "";
		}
		String yuan= cent2yuan(Long.valueOf(cent));
		return yuan;
	}
	
	public static boolean isNumber(String str) {
		return org.apache.commons.lang3.math.NumberUtils.isNumber(str);
	}
	
	public static boolean isNotNumber(String str) {
		return !isNumber(str);
	}
	public static long double2long(Object d){
		double temp = Double.valueOf(d.toString());
		return (long)temp;
	}
    
    // 下面是测试
	
	public static void testCipher(){
		String pubfilePath= "F:\\datum\\data\\appdata\\openssl\\rsa_public_key.pem";
		String privfilePath1= "F:\\datum\\data\\appdata\\openssl\\rsa_pkcs1_private_key.pem";
		String privfilePath8= "F:\\datum\\data\\appdata\\openssl\\rsa_pkcs8_private_key.pem";
		// PKCS1
		PublicKey pubkey = loadPublicKey(base64FromPKCS1File(pubfilePath));
		PrivateKey privkey = loadRsaPrivKeyPKCS1(base64FromPKCS1File(privfilePath1));
		PrivateKey privkey8 = loadPrivateKey(base64FromPKCS1File(privfilePath8));
		String ciphertext= rsaEncrypt(pubkey, "张囧囧比昂题都城南庄=plaintext1234!@##$$plaintext1234!@##$$plaintext1234!@##$$plaintext1234!@##$$plaintext1234!@##$$plaintext1234!@##$$plaintext1234!@##$$plaintext1234!@##$$plaintext1234!@##$$plaintext1234!@##$$plaintext1234!@##$$plaintext1234!@##$$plaintext1234!@##$$plaintext1234!@##$$");
		String plaintext= rsaDecrypt(privkey, ciphertext);
		pl("公钥加密："+ciphertext);
		pl("PKCS1私钥解密："+plaintext);
		plaintext= rsaDecrypt(privkey8, ciphertext);
		pl("PKCS8私钥解密："+plaintext);
		
		// 签名，验签
		String signed = sign2base64SHA1WITHRSA(ciphertext, privkey);
		pl("sign:"+signed+", verify:"+verifyByBase64SHA1WITHRSA(ciphertext, signed, pubkey)+
				", verify2:"+verifyByBase64SHA1WITHRSA(ciphertext+"aaa", signed, pubkey));
		
		// 解密
		pl("解jsencrypt.js加的密:"+rsaDecrypt(privkey, "z4Nj8+122p+typmueGE/uFALhimoO7+9WsgRinYk5O0bz8wF0tJyGp+VMXMm68oKDHdBRudioJ4kWgqThtgffRbpOdnCKBMpBnBfqOq6kTypn5AGJdZtLlwIcPkpfvLMzDbSyDHa8K3GoI0+8VPvaD6jl/mckkqs107yN1Iu6pE="));
	}
	
	
	public static void testInsurance() {
		String bizContent="b0b9vqKt9iaRpYneIRAn6yAHMS4xrWfjalyeB6bBa193shCMnmK36MCZu0iClp8tUmfpev6xkVHBLvN1c2w96Wsi3E8PiZ3G5eH2cnIlXI/JXdDZAVslMpLsHSgBaYksq6jGV5YYFXfWT24Pj01WlNs9yuqrQSKPVTMyilnnysIn6inNS5W5Pg3+FpmhwKiN5Klzhpv6pdT6O/Kl8jAvp9d8FoBEY6S2Sv43CZ5rAPT6NwiMAqycBtqA8bcrVYNp+9ONJMU9fzkfQsqoqJUwECRQx6CdwILIxdZ28vekfgBZ8gngGVPaaMAS2LRCYAj9Q6su35ypAsHozNO72zLLtLF5FkVn6jAq3p01QkLowb6BQR+HSEjDC1l6/yBIVp7HlBF8cmBGqMUb7XvF4PZEIHKqN3V6nc5rlCQUv/AseZXGYVmmEFGnqnzW+rTKaPnoZQvCsiNvYJvY1cT7cvmvF5WMM4w3R9YuZc6wnXDXnl3BX7qv3gcdXw9Hyp9G0gB1cjYReFaR3R9oV5KUoigtQASsWVhGQ7KrnRK93d3b0J5zIANcnCqVOZ9a8PxYenbzEzxDkNHIw0Obf0q+D6rEpTq+4LsORNkojtMBiIcD6nfrrijN7j41l2WYDUadTBwlc386s5Ut64paV1/aS8NDPGq5Qbmu4OB/LUzfQFq5PkmMMgnfnbCvrnzHs/fsK3zKjZ+Sml8mm9b9nKyMcz61kwZEy6ldl4YEpqfO0eehkeISQpeXd2JfpsSmkECijF4bqg62B/xnK0NDm3sF0Snl8UToImrZzVfy6G15xfULIIJMdF12r0TkCEpIvaLpzEDhtLhmy57Q6LczPdWCuUEtARrLw5ptMtmxFmBeqpx66R4R4NF+sLGii/T+gsMuZnc70Doq4WyBsiYoOfU0AuM1u7BSEVfpZCM+QrVnvmDme3ucNpx6L1f1zpptAcgQJ159lPhdgeu+jNrbgdXHSBrwWg7xVl36Y9D/9oLDncu/hxRVtVgPPx2PyeQUVK8gojHH, charset=UTF-8, format=json, serviceName=acci.order.create, sign=hQ8Ph0s2kzS1jEDKC8XC/mW5AsFhRJ7fE2b0CKaTMg+i1oIVodIfLXhTdYMtgV+8CPCKld1pd3B3Cdlyl1SY1JOeLmf7r9fobTtqqpvRF0lHr7iWKnfriTFJhyeG5B4YBUhUG6wBQf28MwrcQaYbv9g7IhTFVT7iXpuNZFuMlt8=";
		String sign="hQ8Ph0s2kzS1jEDKC8XC/mW5AsFhRJ7fE2b0CKaTMg+i1oIVodIfLXhTdYMtgV+8CPCKld1pd3B3Cdlyl1SY1JOeLmf7r9fobTtqqpvRF0lHr7iWKnfriTFJhyeG5B4YBUhUG6wBQf28MwrcQaYbv9g7IhTFVT7iXpuNZFuMlt8=";

		Map<String, String> reqMsgMap = new TreeMap<>();
		reqMsgMap.put("appKey", "87214245e27197bfbc507e560a93ea2a");
		reqMsgMap.put("appSecret", "fec5d2a40fe8625ce13160957b321cdf");
		reqMsgMap.put("serviceName", "acci.order.create");
		reqMsgMap.put("bizContent", bizContent);
		reqMsgMap.put("timestamp", "20170515185000861");
		reqMsgMap.put("format", "json");
		reqMsgMap.put("signType", "RSA");
		reqMsgMap.put("charset", "UTF-8");
		reqMsgMap.put("version", "1.0.0");
		reqMsgMap.put("sign", sign);
		
		String url="https://www.51bzg.cn/gateway";
		// String reqJson="{appKey=87214245e27197bfbc507e560a93ea2a, appSecret=fec5d2a40fe8625ce13160957b321cdf, bizContent=b0b9vqKt9iaRpYneIRAn6yAHMS4xrWfjalyeB6bBa193shCMnmK36MCZu0iClp8tUmfpev6xkVHBLvN1c2w96Wsi3E8PiZ3G5eH2cnIlXI/JXdDZAVslMpLsHSgBaYksq6jGV5YYFXfWT24Pj01WlNs9yuqrQSKPVTMyilnnysIn6inNS5W5Pg3+FpmhwKiN5Klzhpv6pdT6O/Kl8jAvp9d8FoBEY6S2Sv43CZ5rAPT6NwiMAqycBtqA8bcrVYNp+9ONJMU9fzkfQsqoqJUwECRQx6CdwILIxdZ28vekfgBZ8gngGVPaaMAS2LRCYAj9Q6su35ypAsHozNO72zLLtLF5FkVn6jAq3p01QkLowb6BQR+HSEjDC1l6/yBIVp7HlBF8cmBGqMUb7XvF4PZEIHKqN3V6nc5rlCQUv/AseZXGYVmmEFGnqnzW+rTKaPnoZQvCsiNvYJvY1cT7cvmvF5WMM4w3R9YuZc6wnXDXnl3BX7qv3gcdXw9Hyp9G0gB1cjYReFaR3R9oV5KUoigtQASsWVhGQ7KrnRK93d3b0J5zIANcnCqVOZ9a8PxYenbzEzxDkNHIw0Obf0q+D6rEpTq+4LsORNkojtMBiIcD6nfrrijN7j41l2WYDUadTBwlc386s5Ut64paV1/aS8NDPGq5Qbmu4OB/LUzfQFq5PkmMMgnfnbCvrnzHs/fsK3zKjZ+Sml8mm9b9nKyMcz61kwZEy6ldl4YEpqfO0eehkeISQpeXd2JfpsSmkECijF4bqg62B/xnK0NDm3sF0Snl8UToImrZzVfy6G15xfULIIJMdF12r0TkCEpIvaLpzEDhtLhmy57Q6LczPdWCuUEtARrLw5ptMtmxFmBeqpx66R4R4NF+sLGii/T+gsMuZnc70Doq4WyBsiYoOfU0AuM1u7BSEVfpZCM+QrVnvmDme3ucNpx6L1f1zpptAcgQJ159lPhdgeu+jNrbgdXHSBrwWg7xVl36Y9D/9oLDncu/hxRVtVgPPx2PyeQUVK8gojHH, charset=UTF-8, format=json, serviceName=acci.order.create, sign=hQ8Ph0s2kzS1jEDKC8XC/mW5AsFhRJ7fE2b0CKaTMg+i1oIVodIfLXhTdYMtgV+8CPCKld1pd3B3Cdlyl1SY1JOeLmf7r9fobTtqqpvRF0lHr7iWKnfriTFJhyeG5B4YBUhUG6wBQf28MwrcQaYbv9g7IhTFVT7iXpuNZFuMlt8=, signType=RSA, timestamp=20170515185000861, version=1.0.0}";
		String respStr= HttpUtil.hspostParam(url, reqMsgMap, HttpUtil.createSSLClientDefault());
		pl(respStr);
		
	}
	
	public static void test_list2mapProp(){
		TestBean[] bs = {new TestBean(null, "aa"), new TestBean(null, "bb"), new TestBean("cc", "cc"), new TestBean("dd", null)};
		Map<Object, String> map= list2mapProp(bs, "aa", "bb");
		pobj(map);
	}


	
	
	public static void main(String[] args) {
		// pl(Gson.class.getProtectionDomain());
		// pl("1234".matches("[-]{0,1}[\\d]+"));
		// pl(stackTrace());
		// printStackTrace();
		// pl("getUUID="+getUUID());
		testCipher();
		// testInsurance();
		// test_list2mapProp();
		// test_json();
		// test_getConfFromSpringCloud();
	}
	
}
