package com.weizhi.libra.common.utils;

import java.util.List;

public class ArrayUtil {
	public static boolean isListNotNull(List list) {
		return list != null && list.size() > 0;
	}

	public static boolean isArrayNotNull(String[] strs) {
		return strs != null && strs.length > 0;
	}
	

}
