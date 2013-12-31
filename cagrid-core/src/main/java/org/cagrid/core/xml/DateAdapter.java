package org.cagrid.core.xml;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import javax.xml.bind.DatatypeConverter;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * The DateAdapter is used by JAXB to convert GregorianCalendars to
 * java.util.Date. This allows us to re-map the dateTime xmlType to
 * java.util.Date rather than XMLGregorianCalendar. The jaxb-bindings.xjb
 * configuration specifies that the DateAdapter implements the parseDate and
 * printDate methods needed by JAXB.
 */
public class DateAdapter extends XmlAdapter<String, Date> {

	private final static TimeZone GMT = TimeZone.getTimeZone("GMT");

	public static Date parseDate(String s) {
		return DatatypeConverter.parseDate(s).getTime();
	}

	public static String printDate(Date d) {
		if (d == null)
			return null;
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		return DatatypeConverter.printDate(c);
	}

	public static Date parseDateTime(String s) {
		return DatatypeConverter.parseDateTime(s).getTime();
	}

	public static String printDateTime(Date d) {
		if (d == null)
			return null;
		Calendar c = Calendar.getInstance(GMT);
		c.setTime(d);
		return DatatypeConverter.printDateTime(c);
	}

	public static java.sql.Date parseSQLDate(String s) {
		return new java.sql.Date(DatatypeConverter.parseDate(s).getTime().getTime());
	}

	public static java.sql.Time parseSQLTime(String s) {
		return new java.sql.Time(DatatypeConverter.parseDate(s).getTime().getTime());
	}

	public static java.sql.Timestamp parseSQLTimestamp(String s) {
		return new java.sql.Timestamp(DatatypeConverter.parseDateTime(s).getTime().getTime());
	}

	@Override
	public Date unmarshal(String v) throws Exception {
		if (v == null)
			return null;
		DatatypeFactory dtf = DatatypeFactory.newInstance();
		XMLGregorianCalendar xgc = dtf.newXMLGregorianCalendar(v);
		GregorianCalendar cal = xgc.toGregorianCalendar();
		return cal.getTime();
	}

	@Override
	public String marshal(Date v) throws Exception {
		if (v == null)
			return null;
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(v);
		DatatypeFactory dtf = DatatypeFactory.newInstance();
		return dtf.newXMLGregorianCalendar(cal).toXMLFormat();
	}
}