package com.zy.util;

public class MathUtil {
	public static void main(String[] args) {
		System.out.println(chineseNumber2Int("十二"));
	}
	
	/**
	 * 中文數字转阿拉伯数组【十万九千零六十  --> 109060】
	 * @param chineseNumber
	 * @return 阿拉伯数字
	 */
	public static int chineseNumber2Int(String chineseNumber) {
		int result = 0;
		int temp = 1;// 存放一个单位的数字如：十万
		int count = 0;// 判断是否有chArr
		char[] cnArr = new char[] { '一', '二', '三', '四', '五', '六', '七', '八', '九' };
		char[] chArr = new char[] { '十', '百', '千', '万', '亿' };
		for (int i = 0; i < chineseNumber.length(); i++) {
			boolean b = true;// 判断是否是chArr
			char c = chineseNumber.charAt(i);
			for (int j = 0; j < cnArr.length; j++) {// 非单位，即数字
				if (c == cnArr[j]) {
					if (0 != count) {// 添加下一个单位之前，先把上一个单位值添加到结果中
						result += temp;
						temp = 1;
						count = 0;
					}
					// 下标+1，就是对应的值
					temp = j + 1;
					b = false;
					break;
				}
			}
			if (b) {// 单位{'十','百','千','万','亿'}
				for (int j = 0; j < chArr.length; j++) {
					if (c == chArr[j]) {
						switch (j) {
						case 0:
							temp *= 10;
							break;
						case 1:
							temp *= 100;
							break;
						case 2:
							temp *= 1000;
							break;
						case 3:
							temp *= 10000;
							break;
						case 4:
							temp *= 100000000;
							break;
						default:
							break;
						}
						count++;
					}
				}
			}
			if (i == chineseNumber.length() - 1) {// 遍历到最后一个字符
				result += temp;
			}
		}
		return result;
	}
	
	/**
	 * 
	 * @param str  目标字符串
	 * @param type 1/是否数字(整数) 2/是否小数 3/是否整数或小数
	 * @return
	 */
	public static boolean isNumber(String str, Integer type) {
		if (null == str || str.equals("") || str.trim().equals(" ")) {
			return false;
		}
		if (1 == type) {
			return java.util.regex.Pattern.compile("[0-9]*").matcher(str).matches();
		} else if (2 == type) {
			return java.util.regex.Pattern.compile("\\d+\\.\\d+$|-\\d+\\.\\d+$").matcher(str)
					.matches();
		} else if (3 == type) {
			return java.util.regex.Pattern.compile("[0-9]*").matcher(str).matches()	|| java.util.regex.Pattern.compile("\\d+\\.\\d+$|-\\d+\\.\\d+$").matcher(str).matches();
		}
		return false;
	}
}
