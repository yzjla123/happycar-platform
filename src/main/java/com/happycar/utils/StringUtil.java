package com.happycar.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;

public class StringUtil {
	private final static String key = "is2obxom7vboya3h5pf4fikby3nponie";

	public static boolean matchChi(String str, int min, int max) {
		return Pattern.matches("^[A-z-\\d\\-\\u4e00-\\u9fa5]{" + min + "," + max + "}$", str);
	}

	public static String toString(int[] array, String split) {
		String str = "";
		for (int i : array) {
			str += i + split;
		}
		return str.substring(0, str.lastIndexOf(split));
	}

	public static String toString(String[] array, String split) {
		String str = "";
		for (String i : array) {
			str += i + split;
		}
		return str.substring(0, str.lastIndexOf(split));
	}

	public static int[] toIntArray(String str, String splitChar) {
		String[] tmp = str.split(splitChar);
		int[] nums = new int[tmp.length];
		for (int i = 0; i < tmp.length; i++) {
			nums[i] = Integer.valueOf(tmp[i]);
		}
		return nums;
	}

	public static boolean notIn(String val, String[] strs) {
		boolean flag = false;
		for (int i = 0; i < strs.length; i++) {
			if (strs[i].equals(val)) {
				flag = true;
				break;
			}
		}
		return flag;
	}

	/**
	 * 从序列中找到一个不相等的字符串
	 * 
	 * @param val
	 * @param strs
	 * @return
	 */
	public static String notEquals(String val, String[] strs) {
		for (String str : strs) {
			if (!val.equals(str))
				return str;
		}
		return val;
	}

	/**
	 * 从序列中找到一个相等的字符串
	 * 
	 * @param val
	 * @param strs
	 * @return
	 */
	public static boolean equals(String val, String... strs) {
		for (String str : strs) {
			if (val.equals(str))
				return true;
		}
		return false;
	}

	public static boolean isNull(String str) {
		if (str == null || str.trim().equals("")) {
			return true;
		}
		return false;
	}

	public static String getIp(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (!StringUtils.isEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
			// 多次反向代理后会有多个ip值，第一个ip才是真实ip
			int index = ip.indexOf(",");
			if (index != -1) {
				return ip.substring(0, index);
			} else {
				return ip;
			}
		}
		ip = request.getHeader("X-Real-IP");
		if (!StringUtils.isEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
			return ip;
		}
		return request.getRemoteAddr();
	}

	/**
	 * 功能：生成签名结果
	 * 
	 * @param sArray
	 *            要签名的数组
	 * @param key
	 *            安全校验码
	 * @return 签名结果字符串
	 */
	public static String BuildMysign(Map sArray) {
		String prestr = CreateLinkString(sArray); // 把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
		prestr = prestr + "&" + key; // 把拼接后的字符串再与安全校验码直接连接起来
		String mysign = MD5Util.md5(prestr);
		return mysign;
	}

	/**
	 * 功能：除去数组中的空值和签名参数
	 * 
	 * @param sArray
	 *            签名参数组
	 * @return 去掉空值与签名参数后的新签名参数组
	 */
	public static Map ParaFilter(Map sArray) {
		List keys = new ArrayList(sArray.keySet());
		Map sArrayNew = new HashMap();

		for (int i = 0; i < keys.size(); i++) {
			String key = (String) keys.get(i);
			String value = (String) sArray.get(key);

			if (value == null || value.equals("") || key.equalsIgnoreCase("sign")
					|| key.equalsIgnoreCase("sign_type")) {
				continue;
			}

			sArrayNew.put(key, value);
		}

		return sArrayNew;
	}

	/**
	 * 功能：把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
	 * 
	 * @param params
	 *            需要排序并参与字符拼接的参数组
	 * @return 拼接后字符串
	 */
	public static String CreateLinkString(Map params) {
		List keys = new ArrayList(params.keySet());
		Collections.sort(keys);

		String prestr = "";

		for (int i = 0; i < keys.size(); i++) {
			String key = (String) keys.get(i);
			String value = params.get(key) == null ? "" : params.get(key).toString();

			if (i == keys.size() - 1) {// 拼接时，不包括最后一个&字符
				prestr = prestr + key + "=" + value;
			} else {
				prestr = prestr + key + "=" + value + "&";
			}
		}

		return prestr;
	}

	/**
	 * 这是典型的随机洗牌算法。 流程是从备选数组中选择一个放入目标数组中，将选取的数组从备选数组移除（放至最后，并缩小选择区域） 算法时间复杂度O(n)
	 * 
	 * @return 随机8为不重复数组
	 */
	public static String generateNumber() {
		String no = "";
		// 初始化备选数组
		int[] defaultNums = new int[10];
		for (int i = 0; i < defaultNums.length; i++) {
			defaultNums[i] = i;
		}

		Random random = new Random();
		int[] nums = new int[LENGTH];
		// 默认数组中可以选择的部分长度
		int canBeUsed = 10;
		// 填充目标数组
		for (int i = 0; i < nums.length; i++) {
			// 将随机选取的数字存入目标数组
			int index = random.nextInt(canBeUsed);
			nums[i] = defaultNums[index];
			// 将已用过的数字扔到备选数组最后，并减小可选区域
			swap(index, canBeUsed - 1, defaultNums);
			canBeUsed--;
		}
		if (nums.length > 0) {
			for (int i = 0; i < nums.length; i++) {
				no += nums[i];
			}
		}

		return no;
	}

	private static final int LENGTH = 8;

	private static void swap(int i, int j, int[] nums) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}

	public static String generateNumber2() {
		String no = "";
		int num[] = new int[8];
		int c = 0;
		for (int i = 0; i < 8; i++) {
			num[i] = new Random().nextInt(10);
			c = num[i];
			for (int j = 0; j < i; j++) {
				if (num[j] == c) {
					i--;
					break;
				}
			}
		}
		if (num.length > 0) {
			for (int i = 0; i < num.length; i++) {
				no += num[i];
			}
		}
		return no;
	}

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			 System.out.println(generateNumber());
//			System.out.println(generateNumber2());
			} 
	}
}
