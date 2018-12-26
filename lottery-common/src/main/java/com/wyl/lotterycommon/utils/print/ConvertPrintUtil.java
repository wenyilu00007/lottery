package com.wyl.lotterycommon.utils.print;


import org.apache.commons.lang3.StringUtils;

/**
 * @author wangshuo
 * @Description: 打印模块相关工具及转换类
 * @Package com.ksudi.comn.util.print
 * @date 2017/7/18 13:55
 */
public class ConvertPrintUtil {

    /**
     * @Description: 将打印货品名称分割</br>
     * @Author <a href="mailto:shuo.wang@ksudi.com">wangshuo</a>
     * @Date 2017/7/18 13:58
     */
    public static String convertPrintName(String printName) {
        String name = "";
        if (StringUtils.isNotEmpty(printName)) {
            String[] nameArr = printName.split(",");
            for (String str : nameArr) {
                name += str + "\n";
            }
        }
        return name;
    }
}
