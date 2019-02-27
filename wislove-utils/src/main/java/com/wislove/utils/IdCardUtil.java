package com.rcsit.scmp.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * 身份证号码工具类
 * 百度百科的身份证号码规则:
 * 1、将前面的身份证号码17位数分别乘以不同的系数。从第一位到第十七位的系数分别为：7－9－10－5－8－4－2－1－6－3－7－9－10－5－8－4－2。
 * 2、将这17位数字和系数相乘的结果相加。
 * 3、用加出来和除以11，看余数是多少？
 * 4、余数只可能有0－1－2－3－4－5－6－7－8－9－10这11个数字。其分别对应的最后一位身份证的号码为1－0－X －9－8－7－6－5－4－3－2。(即余数0对应1，余数1对应0，余数2对应X...)
 * 5、通过上面得知如果余数是3，就会在身份证的第18位数字上出现的是9。如果对应的数字是2，身份证的最后一位号码就是罗马数字X。
 *
 * @author 廖双龙
 * @date 2019/1/25
 */
public class IdCardUtil {

    /**
     * 中国公民身份证号码最小长度
     **/
    private static final int CHINA_ID_MIN_LENGTH = 15;
    /**
     * 中国公民身份证号码最大长度
     **/
    private static final int CHINA_ID_MAX_LENGTH = 18;
    /**
     * 最低年限,暂时未判断
     */
    public static final int MIN = 1900;
    /**
     * 每位加权因子
     */
    private static final int[] POWER = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
    /**
     * 地区map
     **/
    private static Map<String, String> cityCodes = new HashMap<>();

    static {
        cityCodes.put("11", "北京");
        cityCodes.put("12", "天津");
        cityCodes.put("13", "河北");
        cityCodes.put("14", "山西");
        cityCodes.put("15", "内蒙古");
        cityCodes.put("21", "辽宁");
        cityCodes.put("22", "吉林");
        cityCodes.put("23", "黑龙江");
        cityCodes.put("31", "上海");
        cityCodes.put("32", "江苏");
        cityCodes.put("33", "浙江");
        cityCodes.put("34", "安徽");
        cityCodes.put("35", "福建");
        cityCodes.put("36", "江西");
        cityCodes.put("37", "山东");
        cityCodes.put("41", "河南");
        cityCodes.put("42", "湖北");
        cityCodes.put("43", "湖南");
        cityCodes.put("44", "广东");
        cityCodes.put("45", "广西");
        cityCodes.put("46", "海南");
        cityCodes.put("50", "重庆");
        cityCodes.put("51", "四川");
        cityCodes.put("52", "贵州");
        cityCodes.put("53", "云南");
        cityCodes.put("54", "西藏");
        cityCodes.put("61", "陕西");
        cityCodes.put("62", "甘肃");
        cityCodes.put("63", "青海");
        cityCodes.put("64", "宁夏");
        cityCodes.put("65", "新疆");
        cityCodes.put("71", "台湾");
        cityCodes.put("81", "香港");
        cityCodes.put("82", "澳门");
        cityCodes.put("91", "国外");
    }


    /**
     * 判断身份证号码是否正确
     *
     * @param idNumber 身份证号码
     * @return true, false
     */
    public static boolean isIdNumber(String idNumber) {
        if (idNumber == null || "".equals(idNumber)) {
            return false;
        }

        idNumber = idNumber.trim();

        // 主要判断18位，15位的只判断正则
        if (idNumber.length() == CHINA_ID_MAX_LENGTH) {
            String regex = "^[1-9]\\d{5}(18|19|([20]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$";
            if (Pattern.matches(regex, idNumber)) {
                return validateIdNumber18(idNumber);
            }
        }

        if (idNumber.length() == CHINA_ID_MIN_LENGTH) {
            String regex = "^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{2}[0-9Xx]$";
            return Pattern.matches(regex, idNumber);
        }

        return false;
    }

    /**
     * 获取生日
     *
     * @param idNumber 身份证号码
     * @return date生日
     */
    public static Date getBirthdayDate(String idNumber) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        Date birthday = new Date();
        try {
            birthday = sdf.parse(getBirthdayStr(idNumber));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return birthday;
    }

    /**
     * 获取生日字符
     *
     * @param idNumber 身份证号码
     * @return yyyyMMdd的生日字符
     */
    public static String getBirthdayStr(String idNumber) {
        return idNumber.substring(6, 14);
    }

    /**
     * 根据身份证号码获取年龄
     *
     * @param idNumber 身份证号码
     * @return 年龄
     */
    public static int getAge(String idNumber) {
        int age;

        String year = idNumber.substring(6, 10);
        Calendar cal = Calendar.getInstance();
        int currYear = cal.get(Calendar.YEAR);
        age = currYear - Integer.valueOf(year);
        return age;
    }

    /**
     * 根据身份证号码获取性别
     *
     * @param idNumber 身份证号码
     * @return "男","女"
     */
    public static String getGender(String idNumber) {
        String genderCode = idNumber.substring(16, 17);
        int genderNum = Integer.valueOf(genderCode);
        int checkNum = 2;

        if (genderNum % checkNum != 0) {
            return "男";
        } else {
            return "女";
        }
    }

    /**
     * 获取户籍省份
     *
     * @param idNumber 身份证号码
     * @return "四川"
     */
    public static String getProvince(String idNumber) {
        String provinceNum = idNumber.substring(0, 2);

        return cityCodes.get(provinceNum);
    }

    /**
     * 校验18位身份证号码是否正确
     *
     * @param idNumber 身份证号码
     * @return true, false
     */
    private static boolean validateIdNumber18(String idNumber) {
        if (idNumber.length() == CHINA_ID_MAX_LENGTH) {

            int powerSum = getPowerSum(idNumber);
            // 第18位
            String code18 = idNumber.substring(17, 18);
            String checkCode = getCheckCode18(powerSum);
            if (code18.equalsIgnoreCase(checkCode)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 执行百度百科的算法前两步骤:
     * 1、将前面的身份证号码17位数分别乘以不同的系数。从第一位到第十七位的系数分别为：7－9－10－5－8－4－2－1－6－3－7－9－10－5－8－4－2
     * 2、将这17位数字和系数相乘的结果相加
     *
     * @param idNumber 身份证号码
     * @return 得到加权因子相乘以后的和
     */
    private static int getPowerSum(String idNumber) {
        int sum = 0;

        // 前17位
        String pre17Num = idNumber.substring(0, 17);
        char[] pre17Array = pre17Num.toCharArray();

        for (int i = 0; i < pre17Num.length(); i++) {
            // 将char转为int
            int everyIdNum = Integer.parseInt(String.valueOf(pre17Array[i]));

            sum += everyIdNum * POWER[i];
        }

        return sum;
    }

    /**
     * 获取第18位的数字
     * 算法第三步：
     * 3、用加出来和除以11，看余数是多少
     * 4、余数只可能有0－1－2－3－4－5－6－7－8－9－10这11个数字。其分别对应的最后一位身份证的号码为1－0－X －9－8－7－6－5－4－3－2
     * 将power和值与11取模获得余数进行校验码判断
     *
     * @param iSum 上面方法算出来的和
     * @return 校验位
     */
    private static String getCheckCode18(int iSum) {
        String sCode = "";
        switch (iSum % 11) {
            case 0:
                sCode = "1";
                break;
            case 1:
                sCode = "0";
                break;
            case 2:
                sCode = "x";
                break;
            case 3:
                sCode = "9";
                break;
            case 4:
                sCode = "8";
                break;
            case 5:
                sCode = "7";
                break;
            case 6:
                sCode = "6";
                break;
            case 7:
                sCode = "5";
                break;
            case 8:
                sCode = "4";
                break;
            case 9:
                sCode = "3";
                break;
            case 10:
                sCode = "2";
                break;
            default:
                break;
        }
        return sCode;
    }

    public static void main(String[] args) {
        String idNumber = "410802199310188146";

        System.out.println("身份证号码是否正确:" + isIdNumber(idNumber));
        System.out.println("生日:" + IdCardUtil.getBirthdayStr(idNumber));
        System.out.println("年龄：" + IdCardUtil.getAge(idNumber));
        System.out.println("性别：" + IdCardUtil.getGender(idNumber));
        System.out.println("籍贯:" + IdCardUtil.getProvince(idNumber));
    }
}
