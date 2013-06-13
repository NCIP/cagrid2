package org.cagrid.gme.service.impl.testutils;

public interface SpringTestApplicationContextConstants {
    public static final String GME_BASE_LOCATION = "classpath*:META-INF/spring/gme-beans-configuration.xml";
    public static final String TEST_BASE_LOCATION = "classpath*:META-INF/spring/test-applicationContext-gme.xml";
    public static final String CYCLES_LOCATION = "classpath*:META-INF/spring/test-applicationContext-cycles.xml";
    public static final String ERRORS_LOCATION = "classpath*:META-INF/spring/test-applicationContext-errors.xml";
    public static final String INCLUDES_LOCATION = "classpath*:META-INF/spring/test-applicationContext-includes.xml";
    public static final String REDEFINES_LOCATION = "classpath*:META-INF/spring/test-applicationContext-redefines.xml";
    public static final String SIMPLE_LOCATION = "classpath*:META-INF/spring/test-applicationContext-simple.xml";
}
