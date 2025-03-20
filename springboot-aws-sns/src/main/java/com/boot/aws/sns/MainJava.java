package com.boot.aws.sns;

import java.util.EnumSet;

import org.apache.commons.lang3.EnumUtils;

public class MainJava {

	public static void main(String[] args) {
		EnumSet<Size> requiredEnums = EnumSet.of(Size.LARGE, Size.MEDIUM);
		if(requiredEnums.contains(Size.valueOf("MEDIUM"))) {
			System.out.println("success...");
		}
	}

}
