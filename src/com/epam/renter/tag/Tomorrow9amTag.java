package com.epam.renter.tag;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Tomorrow9amTag extends TagSupport {

	private static final long serialVersionUID = 1L;
	private static final String FORMAT = "yyyy-MM-dd'T'HH:mm";
	private final Logger logger = LogManager.getLogger(Tomorrow9amTag.class
			.getName());
	
	 public int doStartTag() throws JspException {
	        try {
	            JspWriter out = pageContext.getOut();
	            Date today = new Date();
	            Calendar cal = Calendar.getInstance();
	            cal.setTime(today);
	            cal.add(Calendar.DAY_OF_MONTH, 1);
	            cal.set(Calendar.HOUR_OF_DAY, 9);
	            cal.set(Calendar.MINUTE, 0);
	            Date tomorrow9am = cal.getTime();
	            SimpleDateFormat dateFormatter = new SimpleDateFormat(FORMAT);
	            out.print(dateFormatter.format(tomorrow9am));
	            
	        } catch(IOException e) {
	        	logger.error(e);
	        }       
	        return SKIP_BODY;
	    }
}
