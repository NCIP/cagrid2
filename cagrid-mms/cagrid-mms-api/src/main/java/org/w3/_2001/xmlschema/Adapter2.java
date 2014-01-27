
package org.w3._2001.xmlschema;

import java.util.Calendar;
import javax.xml.bind.annotation.adapters.XmlAdapter;

public class Adapter2
    extends XmlAdapter<String, Calendar>
{


    public Calendar unmarshal(String value) {
        return (org.cagrid.xml.adapter.CalendarAdapter.parseCalendar(value));
    }

    public String marshal(Calendar value) {
        return (org.cagrid.xml.adapter.CalendarAdapter.printCalendar(value));
    }

}
