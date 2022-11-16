package com.jee.ihm.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class Header extends TagSupport {
	private static final long serialVersionUID = 4318240657455371033L;
	
	String userName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public void release() {
		userName = "";
	}
	
	public int doStartTag() throws JspException{
		JspWriter out = pageContext.getOut();
		
		try {
			out.println("<header>");
			out.println("<h1>Welcome" + userName + "</h1>");
		} catch (Exception e) {
			
		}
		return -1;
	}
	public int doEndTag() throws JspException{
		JspWriter out = pageContext.getOut();
		
		try {
			out.println("</header>");
		} catch (Exception e) {
			
		}
		return -1;
	}
}
