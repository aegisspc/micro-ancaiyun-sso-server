package com.ancaiyun.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang.StringUtils;

/**
 * @author Catch22
 * @date 2018年6月11日
 */
public abstract class RandomCodeUtil {

	public static String getRandomCode() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String code = sdf.format(date);
		int a = (int) ((Math.random() * 9 + 1) * 1000);
		return code + a;
	}

	public static Date convert(String source) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (StringUtils.isBlank(source)) {
			return null;
		} else if (source.length() == 10) {
			simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		} else {
			simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		}
		try {
			return simpleDateFormat.parse(source);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Date dateAdd(Date date,Integer num) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, num);
		Date time = calendar.getTime(); // 这个时间就是日期往后推一天的结果
		return time;
	}
	
	public static String dateToStr(Date dateDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateToStr = formatter.format(dateDate);
		return dateToStr;
	}
	
	public static String dateToLongStr(Date dateDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateToStr = formatter.format(dateDate);
		return dateToStr;
	}
}
