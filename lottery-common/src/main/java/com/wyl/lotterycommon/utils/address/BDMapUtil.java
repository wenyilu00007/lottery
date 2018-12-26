package com.wyl.lotterycommon.utils.address;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonNull;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.MessageFormat;
import java.util.*;

public class BDMapUtil {

	private static Logger LOGGER = LoggerFactory.getLogger(BDMapUtil.class);
	private final static String AK = "GfCGy8NvGaaqwxtgAQ2ccZ3o";
	private final static String[] AKS = { "GfCGy8NvGaaqwxtgAQ2ccZ3o",
			"HrOkeK1P2hZqcN0du1d54bMc" };
	private final static String DISTANCE_URL = "http://api.map.baidu.com/direction/v1";
	private final static String DISTANCE_BODY = "mode={0}&origin={1},{2}&destination={3},{4}&origin_region={5}&destination_region={6}&output=json&tactics=10&ak={7}";
	private final static String LOCATION_URL = "http://api.map.baidu.com/geocoder/v2/";
	private final static double DEF_PI = Math.PI; // PI
	private final static double DEF_2PI = 6.28318530712; // 2*PI
	private final static double DEF_PI180 = 0.01745329252; // PI/180.0
	private final static double DEF_R = 6370693.5; // radius of earth

	/**
	 * 获取经纬度时，经度的key
	 */
	public final static String LNT = "lnt";
	/**
	 * 获取经纬度时，纬度的key
	 */
	public final static String LAT = "lat";
	/**
	 * 获取经纬度时，行政区的key
	 */
	public final static String DISTRICT = "district";

	/**
	 * 位置的附加信息，是否精确查找。1为精确查找，即准确打点；0为不精确，即模糊打点。
	 */
	public final static String PRECISE = "precise";

	/**
	 * 成功
	 */
	public final static String OK = "0";
	/**
	 * 参数错误
	 */
	public final static String PARA_ERROR = "2";
	/**
	 * 权限或配额校验失败
	 */
	public final static String RIGHT_ERROR = "5";
	
	
	
	/**
	 * 通过关键字查询相应地点
	 * @param keyword
	 * @param city
	 * @return 地点location
	 * @throws UnsupportedEncodingException
	 */
	@SuppressWarnings("unchecked")
	public static List<Map<String,Object>> getKeywordLocation(String keyword,String city) throws UnsupportedEncodingException{
		if(keyword == null || city ==null){
			return null;
		}
		keyword = URLEncoder.encode(keyword, "utf-8");
		city = URLEncoder.encode(city, "utf-8");
		//String tag=URLEncoder.encode(" 门址 道路","utf-8");
		
		String posturl = "http://api.map.baidu.com/telematics/v3/point";
		String body = "output=json&ak=" + AK + "&keyWord=" + keyword + "&cityName="+ city;
		
		String result = doGet(posturl + "?" + body);
		JSONObject obj = JSONObject.parseObject(result);
		String status = obj.getString("status");
		if (status.equals("Success")) {
			String liststr=obj.getString("pointList");
			JSONArray arr=JSONObject.parseArray(liststr);
			List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
			for(int i=0;i<arr.size();i++){
				list.add((Map<String, Object>)arr.get(i));
			}
			return list;
		}
		return null;
	}
	

	/**
	 * 根据地址获得详细信息
	 * 
	 * @author huff
	 * @date 2015年6月23日
	 * @param address
	 *            地址
	 * @param city
	 *            城市
	 * @return lat:纬度 lng:经度district:行政区
	 */
	public static Map<String, Object> getDetialByAddress(String address,
			String city) {
		Map<String, Object> map = null;
		try {
			String[] strings = getLocation(address, city);
			if (strings == null || strings.length < 2)
				return null;
			// String posturl = "http://api.map.baidu.com/geocoder/v2/";
			String body = "output=json&ak=" + AK + "&location=" + strings[1]
					+ "," + strings[0];
			String result = doGet(LOCATION_URL + "?" + body);
			LOGGER.error("百度地图经纬度解析：" + result);
			JSONObject obj = JSONObject.parseObject(result);
			String status = obj.getString("status");
			if (status.equals("0")) {
				map = new HashMap<String, Object>(3);
				obj = obj.getJSONObject("result").getJSONObject(
						"addressComponent");
				map.put(LAT, strings[1]);
				map.put(LNT, strings[0]);
				map.put(PRECISE, strings[2].equals("1")?true:false);
			} else {
				LOGGER.error(result);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return map;
	}

	/**
	 * 获取经纬度
	 * 
	 * @param address
	 * @param city
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String[] getLocation(String address, String city) {
		if (null == address || address.isEmpty()) {
			throw new IllegalArgumentException("获取经纬度地址不能为空！");
		}
		try {
			address = URLEncoder.encode(address, "utf-8");
			city = URLEncoder.encode(city, "utf-8");
		} catch (UnsupportedEncodingException e) {
			return null;
		}
		// String posturl = "http://api.map.baidu.com/geocoder/v2/";
		// String type = "application/x-www-form-urlencoded; charset=utf-8";
		String body = "output=json&ak=" + AK + "&address=" + address + "&city="
				+ city;
		String result = doGet(LOCATION_URL + "?" + body);
		JSONObject obj = JSONObject.parseObject(result);
		String status = obj.getString("status");
		if ("0".equals(status)) {
			obj = obj.getJSONObject("result");
			String precise=obj.getString("precise");
			obj = obj.getJSONObject("location");
			String lng = obj.getString("lng");
			String lat = obj.getString("lat");
			return new String[] { lng, lat,precise};
		} else {
			return null;
		}
	}

	/**
	 * 获取步行距离
	 * 
	 * @param lonBegin
	 * @param latBegin
	 * @param lonEnd
	 * @param latEnd
	 * @return
	 */
	public static double getDistanceByWalk(double lonBegin, double latBegin,
			double lonEnd, double latEnd, String startCity, String endCity) {
		return getDistanceByWalk(lonBegin, latBegin, lonEnd, latEnd, startCity,
				endCity, null);
	}

	/**
	 * 获取步行距离
	 * 
	 * @param lonBegin
	 * @param latBegin
	 * @param lonEnd
	 * @param latEnd
	 * @return
	 */
	private static double getDistanceByWalk(Object lonBegin, Object latBegin,
			Object lonEnd, Object latEnd, String startCity, String endCity,
			String ak) {
		return getDistanceByType(lonBegin, latBegin, lonEnd, latEnd, startCity, endCity, ak, "walking");
	}

	/**
	 * 获取驾车距离
	 * 
	 * @param lonBegin
	 * @param latBegin
	 * @param lonEnd
	 * @param latEnd
	 * @param startCity
	 * @param endCity
	 * @return
	 */
	public static double getDistanceByDriver(Object lonBegin, Object latBegin,
			Object lonEnd, Object latEnd, String startCity, String endCity) {
		return getDistanceByDriver(lonBegin, latBegin, lonEnd, latEnd,
				startCity, endCity, null);
	}
	
	
	/**
	 * 获取骑行距离
	 * @return
	 * 2016年9月9日
	 * @author run
	 */
	private static double getDistanceByRiding(Object lonBegin, Object latBegin,
			Object lonEnd, Object latEnd, String startCity, String endCity,
			String ak){
		return getDistanceByType(lonBegin, latBegin, lonEnd, latEnd, startCity,
				endCity, ak,"riding");
	}

	/**
	 * 获取驾车距离
	 * 
	 * @param lon1
	 * @param lat1
	 * @param lon2
	 * @param lat2
	 * @param startCity
	 * @param endCity
	 * @param ak
	 * @return
	 */
	private static double getDistanceByDriver(Object lonBegin, Object latBegin,
			Object lonEnd, Object latEnd, String startCity, String endCity,
			String ak) {
		return getDistanceByType(lonBegin, latBegin, lonEnd, latEnd, startCity,endCity, ak,"driving");
	}


	private static double getDistanceByType(Object lonBegin, Object latBegin,
			Object lonEnd, Object latEnd, String startCity, String endCity,
			String ak,String type) {
		try {
			startCity = URLEncoder.encode(startCity, "utf-8");
			endCity = URLEncoder.encode(endCity, "utf-8");
		} catch (UnsupportedEncodingException e) {
			return -1;
		}
		ak = ak == null ? AK : ak;
		// String type = "application/x-www-form-urlencoded; charset=utf-8";
		String body = MessageFormat.format(DISTANCE_BODY, type, latBegin,
				lonBegin, latEnd, lonEnd, startCity, endCity, ak);
		/*
		 * String body =
		 * "mode=driving&origin="+lat1+","+lon1+"&destination="+lat2
		 * +","+lon2+"&origin_region="+startCity+"&destination_region="
		 * +endCity+"&output=json&tactics=10&ak="+ak;
		 */
		String result = doGet(DISTANCE_URL + "?" + body);
		LOGGER.info("百度地图距离返回结果：" + result);
		JSONObject obj = JSONObject.parseObject(result);
		String status = obj.getString("status");
		if ("0".equals(status)) {
			obj = obj.getJSONObject("result");
			if (obj.get("routes") instanceof JsonNull) {
				return -1;
			} else {
				JSONArray objArray = obj.getJSONArray("routes");
				obj = (JSONObject) objArray.get(0);
				double distance = obj.getDouble("distance");
				if (distance == 0)
					return 1;
				else
					return distance;
			}
		} else {
			return -1;
		}
	}
	
	public static double getDistanceDriver(Object lonBegin,
			Object latBegin, Object lonEnd, Object latEnd, String startCity,
			String endCity) {
		double distance = -1D;
		for (String ak : AKS) {
				distance = getDistanceByDriver(lonBegin, latBegin, lonEnd,
						latEnd, startCity, endCity, ak);
			if (distance == -1D)
				continue;
			else
				break;
		}
		return distance;
	}

	/**
	 * 先获取步行距离，当获取不到步行距离的时候，获取驾车距离
	 * 
	 * @param lonBegin
	 * @param latBegin
	 * @param lonEnd
	 * @param latEnd
	 * @param startCity
	 * @param endCity
	 * @return 返回-1为没有获取到距离
	 */
	public static double getDistanceByWalkBeforeDriver(Object lonBegin,
			Object latBegin, Object lonEnd, Object latEnd, String startCity,
			String endCity) {
		double distance = -1D;
		for (String ak : AKS) {
			distance = getDistanceByWalk(lonBegin, latBegin, lonEnd, latEnd,
					startCity, endCity, ak);
			if (distance == -1D) {
				distance = getDistanceByDriver(lonBegin, latBegin, lonEnd,
						latEnd, startCity, endCity, ak);
			}
			if (distance == -1D)
				continue;
			else
				break;
		}
		return distance;
	}
	
	
	/**
	 * 先获取步行距离，当获取不到步行距离的时候，获取驾车距离
	 * 
	 * @param lonBegin
	 * @param latBegin
	 * @param lonEnd
	 * @param latEnd
	 * @param startCity
	 * @param endCity
	 * @return 返回-1为没有获取到距离
	 */
	public static double getDistanceByRiding(Object lonBegin,
			Object latBegin, Object lonEnd, Object latEnd, String startCity,
			String endCity) {
		double distance = -1D;
		for (String ak : AKS) {
			distance = getDistanceByRiding(lonBegin, latBegin, lonEnd, latEnd,
					startCity, endCity, ak);
			if (distance == -1D)
				continue;
			else
				break;
		}
		return distance;
	}

	/**
	 * httpclient发送get请求
	 * 
	 * @param url
	 * @return
	 */
	public static String doGet(String url) {
		HttpClient client = new HttpClient();
		GetMethod getMethod = new GetMethod(url);
		getMethod.getParams().setContentCharset("utf-8");
		String response = null;
		try {
			client.executeMethod(getMethod);
			if (getMethod.getStatusCode() == HttpStatus.SC_OK) {
				response = getMethod.getResponseBodyAsString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getMethod.releaseConnection();
		}
		return response;
	}

	/**
	 * 根据圆心、半径算出经纬度范围
	 * 
	 * @param x
	 *            圆心经度
	 * @param y
	 *            圆心纬度
	 * @param r
	 *            半径（米）
	 * @return double[0-1] 经度， double[2-3]纬度
	 */
	public static double[] getRange(double lng, double lat, double r) {
		double[] range = new double[4];
		// 角度转换为弧度
		double ns = lat * DEF_PI180;
		double sinNs = Math.sin(ns);
		double cosNs = Math.cos(ns);
		double cosTmp = Math.cos(r / DEF_R);
		// 经度的差值
		double lonDif = Math.acos((cosTmp - sinNs * sinNs) / (cosNs * cosNs))
				/ DEF_PI180;
		// 保存经度
		range[0] = lng - lonDif;
		range[1] = lng + lonDif;
		double m = 0 - 2 * cosTmp * sinNs;
		double n = cosTmp * cosTmp - cosNs * cosNs;
		double o1 = (0 - m - Math.sqrt(m * m - 4 * (n))) / 2;
		double o2 = (0 - m + Math.sqrt(m * m - 4 * (n))) / 2;
		// 纬度
		double lat1 = 180 / DEF_PI * Math.asin(o1);
		double lat2 = 180 / DEF_PI * Math.asin(o2);
		// 保存
		range[2] = lat1;
		range[3] = lat2;
		double a = range[0];
		if (range[0] > range[1]) {
			range[0] = range[1];
			range[1] = a;
		}
		double b = range[2];
		if (range[2] > range[3]) {
			range[2] = range[3];
			range[3] = b;
		}
		return range;
	}
	 /**
     * 距离较短时计算距离
     * @param lng1
     * @param lat1
     * @param lng2
     * @param lat2
     * @return
     * @author Jason
     * @date 2015年2月12日 下午4:10:42
     */
	public static double getShortDistance(double lon1, double lat1, double lon2, double lat2)
	 {
		 double ew1, ns1, ew2, ns2;
		 double dx, dy, dew;
		 double distance;
		 // 角度转换为弧度
		 ew1 = lon1 * DEF_PI180;
		 ns1 = lat1 * DEF_PI180;
		 ew2 = lon2 * DEF_PI180;
		 ns2 = lat2 * DEF_PI180;
		 // 经度差
		 dew = ew1 - ew2;
		 // 若跨东经和西经180 度，进行调整
		 if (dew > DEF_PI)
		 dew = DEF_2PI - dew;
		 else if (dew < -DEF_PI)
		 dew = DEF_2PI + dew;
		 dx = DEF_R * Math.cos(ns1) * dew; // 东西方向长度(在纬度圈上的投影长度)
		 dy = DEF_R * (ns1 - ns2); // 南北方向长度(在经度圈上的投影长度)
		 // 勾股定理求斜边长
		 distance = Math.sqrt(dx * dx + dy * dy);
		 return distance;
	 }
   public static void sortListByDistance(Double[] locations,List<Map<String,Object>> list){
	   for(Map<String,Object> m:list){
		   Object coordinatesObj=m.get("coordinates");
		   if(null!=coordinatesObj){
			   String coordinates=String.valueOf(coordinatesObj);
			   String[] coordinatesArray=coordinates.split(",");
			   if(coordinatesArray.length==2){
				   //计算快递到快递员的直线距离
				   m.put("distance",getShortDistance(locations[0],locations[1],Double.valueOf(coordinatesArray[0]),Double.valueOf(coordinatesArray[1])));
			   }else{
				   m.put("distance",99999999);  
			   }
		   }else{
			   m.put("distance",99999999);
		   }
	   }
	   Collections.sort(list,new Comparator<Map<String,Object>>() {
			@Override
			public int compare(Map<String, Object> m1, Map<String, Object> m2) {
				double distance1=Double.valueOf(String.valueOf(m1.get("distance")));
				double distance2=Double.valueOf(String.valueOf(m2.get("distance")));
				if(distance1>distance2) return 1;
				else return -1;
			}
	   });
   }
}
