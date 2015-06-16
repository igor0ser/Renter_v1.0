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

public class TimelineTag extends TagSupport {

	private static final long serialVersionUID = 1L;
	private static final String FORMAT = "yyyy-MM-dd'T'HH:mm";
	private final Logger logger = LogManager.getLogger(TimelineTag.class
			.getName());

	public int doStartTag() throws JspException {
		try {
			JspWriter out = pageContext.getOut();
			String js = "dataTable.addRows([[ 'Will Smith',  new Date(2015, 6, 16, 19, 00),  new Date(2015, 6, 16, 20, 00)]]);chart.draw(dataTable);";
			System.out.println(js);
			out.println(js);

		} catch (IOException e) {
			logger.error(e);
		}
		return SKIP_BODY;
	}
}
