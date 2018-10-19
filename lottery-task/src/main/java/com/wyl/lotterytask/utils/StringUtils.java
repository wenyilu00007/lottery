package com.wyl.lotterytask.utils;


import com.wyl.lotterytask.ssq.constants.Constants;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类。
 */
public class StringUtils {

    public static final String[] EMPTY_STRING_ARRAY = new String[0];

    private static final Pattern KVP_PATTERN = Pattern.compile("([_.a-zA-Z0-9][-_.a-zA-Z0-9]*)[=](.*)"); //key value pair pattern.

    private static final Pattern INT_PATTERN = Pattern.compile("^\\d+$");

    public static final Pattern COMMA_SPLIT_PATTERN = Pattern.compile("\\s*[,]+\\s*");


    private StringUtils() {
    }

    /**
     * 检查指定的字符串是否为空。
     * <ul>
     * <li>SysUtils.isEmpty(null) = true</li>
     * <li>SysUtils.isEmpty("") = true</li>
     * <li>SysUtils.isEmpty("   ") = true</li>
     * <li>SysUtils.isEmpty("abc") = false</li>
     * </ul>
     *
     * @param value 待检查的字符串
     * @return true/false
     */
    public static boolean isEmpty(String value) {
        int strLen;
        if (value == null || (strLen = value.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if ((Character.isWhitespace(value.charAt(i)) == false)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 检查对象是否为数字型字符串,包含负数开头的。
     */
    public static boolean isNumeric(Object obj) {
        if (obj == null) {
            return false;
        }
        char[] chars = obj.toString().toCharArray();
        int length = chars.length;
        if (length < 1)
            return false;

        int i = 0;
        if (length > 1 && chars[0] == '-')
            i = 1;

        for (; i < length; i++) {
            if (!Character.isDigit(chars[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * 检查指定的字符串列表是否不为空。
     */
    public static boolean areNotEmpty(String... values) {
        boolean result = true;
        if (values == null || values.length == 0) {
            result = false;
        } else {
            for (String value : values) {
                result &= !isEmpty(value);
            }
        }
        return result;
    }

    /**
     * 把通用字符编码的字符串转化为汉字编码。
     */
    public static String unicodeToChinese(String unicode) {
        StringBuilder out = new StringBuilder();
        if (!isEmpty(unicode)) {
            for (int i = 0; i < unicode.length(); i++) {
                out.append(unicode.charAt(i));
            }
        }
        return out.toString();
    }

    public static String toUnderlineStyle(String name) {
        StringBuilder newName = new StringBuilder();
        for (int i = 0; i < name.length(); i++) {
            char c = name.charAt(i);
            if (Character.isUpperCase(c)) {
                if (i > 0) {
                    newName.append("_");
                }
                newName.append(Character.toLowerCase(c));
            } else {
                newName.append(c);
            }
        }
        return newName.toString();
    }

    public static String convertString(byte[] data, int offset, int length) {
        if (data == null) {
            return null;
        } else {
            try {
                return new String(data, offset, length, Constants.CHARSET_UTF8);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static byte[] convertBytes(String data) {
        if (data == null) {
            return null;
        } else {
            try {
                return data.getBytes(Constants.CHARSET_UTF8);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }


    public static boolean isBlank(String str)
    {
        if( str == null || str.length() == 0 )
            return true;
        return false;
    }

    /**
     * is not empty string.
     *
     * @param str source string.
     * @return is not empty.
     */
    public static boolean isNotEmpty(String str)
    {
        return str != null && str.length() > 0;
    }

    /**
     *
     * @param s1
     * @param s2
     * @return equals
     */
    public static boolean isEquals(String s1, String s2) {
        if (s1 == null && s2 == null)
            return true;
        if (s1 == null || s2 == null)
            return false;
        return s1.equals(s2);
    }

    /**
     * is integer string.
     *
     * @param str
     * @return is integer
     */
    public static boolean isInteger(String str) {
        if (str == null || str.length() == 0)
            return false;
        return INT_PATTERN.matcher(str).matches();
    }

    public static int parseInteger(String str) {
        if (! isInteger(str))
            return 0;
        return Integer.parseInt(str);
    }

    /**
     * Returns true if s is a legal Java identifier.<p>
     * <a href="http://www.exampledepot.com/egs/java.lang/IsJavaId.html">more info.</a>
     */
    public static boolean isJavaIdentifier(String s) {
        if (s.length() == 0 || !Character.isJavaIdentifierStart(s.charAt(0))) {
            return false;
        }
        for (int i=1; i<s.length(); i++) {
            if (!Character.isJavaIdentifierPart(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isContains(String values, String value) {
        if (values == null || values.length() == 0) {
            return false;
        }
        return isContains(COMMA_SPLIT_PATTERN.split(values), value);
    }

    /**
     *
     * @param values
     * @param value
     * @return contains
     */
    public static boolean isContains(String[] values, String value) {
        if (value != null && value.length() > 0 && values != null && values.length > 0) {
            for (String v : values) {
                if (value.equals(v)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isNumeric(String str) {
        if (str == null) {
            return false;
        }
        int sz = str.length();
        for (int i = 0; i < sz; i++) {
            if (Character.isDigit(str.charAt(i)) == false) {
                return false;
            }
        }
        return true;
    }

    /**
     * translat.
     *
     * @param src source string.
     * @param from src char table.
     * @param to target char table.
     * @return String.
     */
    public static String translat(String src, String from, String to)
    {
        if( isEmpty(src) ) return src;
        StringBuilder sb = null;
        int ix;
        char c;
        for(int i=0,len=src.length();i<len;i++)
        {
            c = src.charAt(i);
            ix = from.indexOf(c);
            if( ix == -1 )
            {
                if( sb != null )
                    sb.append(c);
            }
            else
            {
                if( sb == null )
                {
                    sb = new StringBuilder(len);
                    sb.append(src, 0, i);
                }
                if( ix < to.length() )
                    sb.append(to.charAt(ix));
            }
        }
        return sb == null ? src : sb.toString();
    }

    /**
     * split.
     *
     * @param ch char.
     * @return string array.
     */
    public static String[] split(String str, char ch)
    {
        List<String> list = null;
        char c;
        int ix = 0,len=str.length();
        for(int i=0;i<len;i++)
        {
            c = str.charAt(i);
            if( c == ch )
            {
                if( list == null )
                    list = new ArrayList<String>();
                list.add(str.substring(ix, i));
                ix = i + 1;
            }
        }
        if( ix > 0 )
            list.add(str.substring(ix));
        return list == null ? EMPTY_STRING_ARRAY : (String[])list.toArray(EMPTY_STRING_ARRAY);
    }

    /**
     * join string.
     *
     * @param array String array.
     * @return String.
     */
    public static String join(String[] array)
    {
        if( array.length == 0 ) return "";
        StringBuilder sb = new StringBuilder();
        for( String s : array )
            sb.append(s);
        return sb.toString();
    }

    /**
     * join string like javascript.
     *
     * @param array String array.
     * @param split split
     * @return String.
     */
    public static String join(String[] array, char split)
    {
        if( array.length == 0 ) return "";
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<array.length;i++)
        {
            if( i > 0 )
                sb.append(split);
            sb.append(array[i]);
        }
        return sb.toString();
    }

    /**
     * join string like javascript.
     *
     * @param array String array.
     * @param split split
     * @return String.
     */
    public static String join(String[] array, String split)
    {
        if( array.length == 0 ) return "";
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<array.length;i++)
        {
            if( i > 0 )
                sb.append(split);
            sb.append(array[i]);
        }
        return sb.toString();
    }

    public static String join(Collection<String> coll, String split) {
        if(coll.isEmpty()) return "";

        StringBuilder sb = new StringBuilder();
        boolean isFirst = true;
        for(String s : coll) {
            if(isFirst) isFirst = false; else sb.append(split);
            sb.append(s);
        }
        return sb.toString();
    }

    /**
     * parse key-value pair.
     *
     * @param str string.
     * @param itemSeparator item separator.
     * @return key-value map;
     */
    private static Map<String, String> parseKeyValuePair(String str, String itemSeparator)
    {
        String[] tmp = str.split(itemSeparator);
        Map<String, String> map = new HashMap<String, String>(tmp.length);
        for(int i=0;i<tmp.length;i++)
        {
            Matcher matcher = KVP_PATTERN.matcher(tmp[i]);
            if( matcher.matches() == false )
                continue;
            map.put(matcher.group(1), matcher.group(2));
        }
        return map;
    }

    public static String getUserTypeSql(List<Integer> typeList){
        String sql="(";
        for(Integer type : typeList){
            sql+="(type & "+type+"="+type+") or ";
        }
        sql+=" (1=2))";
        return sql;
    }


    /**
     * is cellphone string.
     *
     * @param str
     * @return is cellphone
     */
    public static boolean isCellphone(String str) {
        if (str == null || str.length() == 0){
            return false;
        }
        Pattern p = Pattern.compile("\\d{11}$");
        Matcher m = p.matcher(str);
        return m.matches();
    }

}
