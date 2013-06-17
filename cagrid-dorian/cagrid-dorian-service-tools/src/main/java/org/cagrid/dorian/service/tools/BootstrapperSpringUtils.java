package org.cagrid.dorian.service.tools;

import org.cagrid.dorian.service.impl.BeanUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

public class BootstrapperSpringUtils extends BeanUtils {

	private static final ClassPathResource CONFIGURATION = new ClassPathResource("META-INF/spring/dorian-configuration.xml");
	private static final FileSystemResource PROPERTIES = new FileSystemResource("src/main/resources/dorian.properties");


	public BootstrapperSpringUtils() throws Exception {
		super(CONFIGURATION, PROPERTIES);
	}
}
