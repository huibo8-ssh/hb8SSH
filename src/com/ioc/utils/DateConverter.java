package com.ioc.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

/**
 * 日期格式字符串转化为Date对象，用于控制器方法中以Date对象方式接收日期字符串请求参数
 * @author Administrator
 *
 */
public class DateConverter implements Converter<String, Date>{

	@Override
	public Date convert(String dateStr) {
		String pattern = "yyyy-MM-dd";
		if(dateStr.contains(" ")) {
			pattern = "yyyy-MM-dd HH:mm:ss";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date date = null;
		try {
			date = sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

}
