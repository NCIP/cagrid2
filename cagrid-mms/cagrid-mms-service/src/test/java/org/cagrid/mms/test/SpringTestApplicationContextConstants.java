package org.cagrid.mms.test;

public interface SpringTestApplicationContextConstants {

    public static final String SPRING_CLASSPATH_PREFIX = "classpath*:";
    public static final String SPRING_FILE_PREFIX = "file:";

    public static final String MMS_BASE_LOCATION = SPRING_FILE_PREFIX + "src/main/resources/META-INF/spring/mms-beans-configuration.xml";
}
