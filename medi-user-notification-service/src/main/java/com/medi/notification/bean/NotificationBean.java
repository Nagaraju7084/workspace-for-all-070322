/**
 * 
 */
package com.medi.notification.bean;

import java.io.Serializable;

import lombok.Data;

/**
 * @author SRI LALITHA DEVI
 *
 */
@Data
public class NotificationBean implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int notificationId;
	private String from;
	private String[] to;
	private String[] bcc;
	private String body;
	private String emailTemplateType;
	private String callbackUrl;
	private String userVerifyCode;

}
