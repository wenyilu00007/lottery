package com.wyl.lotterycommon.utils.address;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SortByDistanceUtil {
	 /**
	    * 缓存距离
	    * @param cachDistanceMap  缓存的map
	    * @param firstId  经纬度1的主键
	    * @param secondId 经纬度2的主键
	    * @param distance 距离
	    */
	   public static void setDistance(Map<String,Double> cachDistanceMap,Long firstId,Long secondId,Double distance){
		   String key=null;
		   if(firstId.longValue()<secondId.longValue()){
			   key=firstId.toString()+secondId.toString();
		   }else{
			   key=secondId.toString()+firstId.toString();
		   }
		   cachDistanceMap.put(key,distance);
	   }
	   /**
	    * 得到缓存的距离
	    * @param cachDistanceMap  缓存的map
	    * @param firstId  经纬度1的主键
	    * @param secondId 经纬度2的主键
	    */
	   public static Double getDistance(Map<String,Double> cachDistanceMap,Long firstId,Long secondId){
		   String key=null;
		   if(firstId.longValue()<secondId.longValue()){
			   key=firstId.toString()+secondId.toString();
		   }else{
			   key=secondId.toString()+firstId.toString();
		   }
		   return cachDistanceMap.get(key);
	   }
	   /**
	    * 直接算两点间的最短距离
	    */
	   public static List<Map<String,Object>> getRoute(Double[] locations,List<Map<String,Object>> list,String coordinatesKey,String onlyKey){
		   //以唯一key为主键放入map
		   Map<Long,Map<String,Object>> onlyMap=new HashMap<Long,Map<String,Object>>();
		   //封装的经纬度列表
		   List<Point> pointList=new ArrayList<Point>();
		   //没有经纬度的数据
		   List<Map<String,Object>> noCoordinatesList=new ArrayList<Map<String,Object>>();
		   for(Map<String,Object> map:list){
			   Long onlyKeyValue=Long.valueOf(String.valueOf(map.get(onlyKey)));
			   onlyMap.put(onlyKeyValue,map);
			   Object coordinatesObj=map.get(coordinatesKey);
			   Point p=null;
			   if(null!=coordinatesObj){
				   String coordinates=String.valueOf(coordinatesObj);
				   String[] coordinatesArray=coordinates.split(",");
				   if(coordinatesArray.length==2){
					  p=new Point(Double.valueOf(coordinatesArray[0]),Double.valueOf(coordinatesArray[1]),onlyKeyValue);
					  pointList.add(p);
					  continue;
				   }
			   }
			   noCoordinatesList.add(map);
		   }
		   //起点
		   Point p=new Point(locations[0],locations[1],0l);
		   //排序后的list
		   List<Point> sortList=new ArrayList<Point>();
		   //计算两点之间的距离缓存，
		   Map<String,Double> cachDistanceMap=new HashMap<String,Double>();
		   
		   double distance=0;
		   Point cachP=null;
		   int size=pointList.size();
		   for(int i=0;i<size;i++){
			   for(int j=0;j<pointList.size();j++){
				   Point pt=pointList.get(j);
				   Double cachDistance=getDistance(cachDistanceMap,p.getId(),pt.getId());
				   if(null==cachDistance){
					   cachDistance=BDMapUtil.getShortDistance(p.getLon(),p.getLat(),pt.getLon(),pt.getLat());
					   setDistance(cachDistanceMap,p.getId(),pt.getId(),cachDistance);
				   }
				   if(j==0){
					   distance=cachDistance;
					   cachP=pt;
				   }else{
					   if(cachDistance<distance){
						   distance=cachDistance;
						   cachP=pt;
					   }
				   }
			   }
		       p=cachP;
		       sortList.add(cachP);
		       pointList.remove(cachP);
		   }
		   list=new ArrayList<Map<String,Object>>();
		   for(Point point:sortList){
			   list.add(onlyMap.get(point.getId()));
		   }
		   list.addAll(noCoordinatesList);
		   return list;
	   }
}
