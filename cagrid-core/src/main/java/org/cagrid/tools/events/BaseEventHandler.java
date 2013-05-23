package org.cagrid.tools.events;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author <A href="mailto:langella@bmi.osu.edu">Stephen Langella </A>
 * @author <A href="mailto:oster@bmi.osu.edu">Scott Oster </A>
 * @author <A href="mailto:hastings@bmi.osu.edu">Shannon Hastings </A>
 * @author <A href="mailto:ervin@bmi.osu.edu">David Ervin</A>
 */
public abstract class BaseEventHandler implements EventHandler {
	private String name;

	private Logger log;

	public BaseEventHandler(String name) {
		this.name = name;
		this.log = LoggerFactory.getLogger(this.getClass());
	}

	public Logger getLog() {
		return log;
	}

	public String getName() {
		return name;
	}

	public abstract void handleEvent(Event event) throws EventHandlingException;

}
