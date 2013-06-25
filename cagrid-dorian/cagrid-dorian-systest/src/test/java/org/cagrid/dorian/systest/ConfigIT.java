package org.cagrid.dorian.systest;

import static org.apache.karaf.tooling.exam.options.KarafDistributionOption.karafDistributionConfiguration;
import static org.apache.karaf.tooling.exam.options.KarafDistributionOption.keepRuntimeFolder;
import static org.ops4j.pax.exam.CoreOptions.junitBundles;
import static org.ops4j.pax.exam.CoreOptions.maven;
import static org.ops4j.pax.exam.CoreOptions.options;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ops4j.pax.exam.MavenUtils;
import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.junit.Configuration;
import org.ops4j.pax.exam.junit.JUnit4TestRunner;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;

@RunWith(JUnit4TestRunner.class)
public class ConfigIT {

	private final Map<Integer, String> bundleStates = new HashMap<Integer, String>();

	@Inject
	private BundleContext bundleContext;

	public ConfigIT() {
		bundleStates.put(Bundle.UNINSTALLED, "UNINSTALLED");
		bundleStates.put(Bundle.INSTALLED, "INSTALLED");
		bundleStates.put(Bundle.RESOLVED, "RESOLVED");
		bundleStates.put(Bundle.STARTING, "STARTING");
		bundleStates.put(Bundle.STOPPING, "STOPPING");
		bundleStates.put(Bundle.ACTIVE, "ACTIVE");
	}

	@Configuration
	public Option[] config() {
		List<Option> options = new ArrayList<Option>();
		String karafVersion = MavenUtils.getArtifactVersion("org.apache.karaf",
				"apache-karaf");
		options.add(karafDistributionConfiguration()
				.frameworkUrl(
						maven("org.apache.servicemix", "apache-servicemix")
								.versionAsInProject().type("tar.gz"))
				.name("Apache ServiceMix").karafVersion(karafVersion));
		options.add(keepRuntimeFolder());
		options.add(junitBundles());
		return options(options.toArray(new Option[options.size()]));
	}

	@Test
	public void testConfig() throws Exception {
		Assert.assertNotNull(bundleContext);

		for (Bundle bundle : bundleContext.getBundles()) {
			String bundleState = bundleStates.get(bundle.getState());
			System.out.println(bundle.getBundleId() + ": "
					+ bundle.getSymbolicName() + " - " + bundle.getLocation()
					+ " [" + bundleState + "]");
		}
	}
}
