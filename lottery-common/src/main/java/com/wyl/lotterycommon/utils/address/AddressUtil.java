package com.wyl.lotterycommon.utils.address;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by LY on 2017/5/25.
 */
public class AddressUtil {

    private static Logger LOGGER = LoggerFactory.getLogger(AddressUtil.class);
    /**
     * 获取经纬度时，经度的key
     */
    public final static String LNT = "lnt";
    /**
     * 获取经纬度时，纬度的key
     */
    public final static String LAT = "lat";

    /**
     * 是否精确查找
     */
    public final static String PRECISE = "precise";


    public static List<Map<String, Object>> resolving( String cityName,
                                               String... address) throws IllegalArgumentException {
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        for (String string : address) {
            Map<String, Object> map=new HashMap<>();
            try {
                map = BDMapUtil.getDetialByAddress(string, cityName);
            }
            catch (Exception e){
                LOGGER.error(e.getMessage());
            }
            list.add(map);
        }
        return list;
    }

}
