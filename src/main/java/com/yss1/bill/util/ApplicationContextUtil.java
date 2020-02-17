package com.yss1.bill.util;

import org.springframework.context.ApplicationContext;

public class ApplicationContextUtil {

	private static ApplicationContext ctx;

	
	public void setApplicationContext(ApplicationContext appContext)
			{
		ctx = appContext;
}

	public static ApplicationContext getApplicationContext() {
		return ctx;
	}
}