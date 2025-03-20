package com.boot.elk.util;

import java.io.PrintWriter;
import java.io.StringWriter;

public interface AppUtil {

	static String getLogSupport(Exception e) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		return sw.toString();
	}
}
