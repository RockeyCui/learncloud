package com.rockeycui.learn.common.util;

import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

/**
 * 字符串工具类
 *
 * @author chenxinquan
 */
@Slf4j
public class StringUtil {
	/**
	 * 判断字符串是否为纯数字
	 * <p>
	 * Create Date: 2014年9月29日
	 * </p>
	 *
	 * @param str
	 * @return true 是，false 否
	 */
	public static boolean isDigital(String str) {
		if (isBlank(str)) {
            return false;
        }

		return str.matches("\\d+");
	}

	public static boolean isBlank(String str) {
		int length;
		if ((str == null) || ((length = str.length()) == 0)) {
            return true;
        }
		for (int i = 0; i < length; ++i) {
			if (!(Character.isWhitespace(str.charAt(i)))) {
				return false;
			}
		}
		return true;
	}

	public static boolean isNotBlank(String str) {
		int length;
		if ((str == null) || ((length = str.length()) == 0)) {
            return false;
        }
		for (int i = 0; i < length; ++i) {
			if (!(Character.isWhitespace(str.charAt(i)))) {
				return true;
			}
		}
		return false;
	}

	public static boolean isEmpty(String str) {
		return ((str == null) || (str.length() == 0));
	}

	public static boolean isNotEmpty(String str) {
		return ((str != null) && (str.length() > 0));
	}

	public static boolean equalsIgnoreCase(String str1, String str2) {
		if (str1 == null) {
			return str2 == null;
		}
		return str1.equalsIgnoreCase(str2);
	}

	public static boolean equals(String str1, String str2) {
		if (str1 == null) {
			return str2 == null;
		}
		return str1.equals(str2);
	}

    public static String getImgNameByUUId() {
        int machineId = 1;
        int hashCodeV = UUID.randomUUID().toString().hashCode();
        if (hashCodeV < 0) {
            hashCodeV = -hashCodeV;
        }
        return machineId + String.format("%015d", hashCodeV);
    }

}
