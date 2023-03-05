package com.ktg.system.strategy;

import com.ktg.common.utils.StringUtils;

import java.util.regex.Pattern;

public class PhoneUtils {

    private static final String REGEX_MOBILE = "((\\+86|0086)?\\s*)((134[0-8]\\d{7})|(((13([0-3]|[5-9]))|(14[5-9])|15([0-3]|[5-9])|(16(2|[5-7]))|17([0-3]|[5-8])|18[0-9]|19([0-9]))\\d{8})|(14(0|1|4)0\\d{7})|(1740([0-5]|[6-9]|[10-12])\\d{7}))";

    /**
     * 正则：固定电话号码,可带区号,然后至少6,8位数字
     */
    private static final String REGEX_TEL = "^(\\d{3,4}-)?\\d{6,8}$";
    private static final Pattern PATTERN_REGEX_TEL = Pattern.compile(REGEX_TEL);

    /**
     * 判断是否是手机号
     *
     * @param tel 手机号
     * @return boolean true:是  false:否
     */
    public static boolean isMobile(String tel) {
        if (StringUtils.isEmpty(tel)) {
            return false;
        }
        return Pattern.matches(REGEX_MOBILE, tel);
    }

    /**
     * 验证固定电话号码
     */
    public static boolean isTel(String str) {
        return isMatch(PATTERN_REGEX_TEL, str);
    }

    public static boolean isMatch(Pattern pattern, String str) {
        return StringUtils.isNotEmpty(str) && pattern.matcher(str).matches();
    }

    public static void main(String[] args) {
        System.out.println(isTel("2887438"));
    }

}


