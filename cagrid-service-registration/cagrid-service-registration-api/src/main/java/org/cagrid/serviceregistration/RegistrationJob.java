package org.cagrid.serviceregistration;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Holder;

import org.apache.cxf.headers.Header;
import org.cagrid.serviceregistration.model.ServiceGroupRegistrationParameters;
import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourcelifetime_1_2_draft_01.SetTerminationTime;
import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourcelifetime_1_2_draft_01_wsdl.ScheduledResourceTermination;
import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_servicegroup_1_2_draft_01.Add;
import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_servicegroup_1_2_draft_01_wsdl.ServiceGroupRegistration;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.StatefulJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xmlsoap.schemas.ws._2004._03.addressing.EndpointReferenceType;
import org.xmlsoap.schemas.ws._2004._03.addressing.ReferencePropertiesType;

public class RegistrationJob implements StatefulJob {

	private final static Logger logger = LoggerFactory
			.getLogger(RegistrationJob.class);

	static final private int LIFETIMECONST = 2;

	/**
	 * if we have decided that the remote entry has no resource lifetime
	 * management operations/RPs, then we will set this to false so that we will
	 * not try them again. Care needs to be taken to ensure that it is only set
	 * to false by exceptions that indicate the appropriate RPs/ops do not
	 * exist, rather than (for example) network / resource non-existence errors.
	 */
	private boolean maybeHasLifetime = true;

	private ServiceGroupRegistrationParameters parameters = null;

	/**
	 * If we know about an existing ServiceGroupEntry EPR for this managed
	 * registration, it will be stored here so that we can attempt to extend its
	 * lifetime rather than adding a new entry.
	 */
	private EndpointReferenceType entryEPR = null;
	
	public RegistrationJob() {
		super();
	}

	public ServiceGroupRegistrationParameters getParameters() {
		return this.parameters;
	}

	/**
	 * Gets the current time, preferably from the remote resource specified, and
	 * failing that from local system.
	 */
	private Calendar getCurrentTimePreferringRemote(EndpointReferenceType epr) {
		logger.info("Attempting to get current time, preferring remote.");

		Calendar now = new GregorianCalendar();

//		if (epr != null && maybeHasLifetime) {
//
//			try {
//				GetResourceProperty getRPPort = ServiceGroupClientFactory.createGetResourcePropertySoapClient(epr.getAddress().getValue());
//				
//				Object sge = entryEPR.getReferenceProperties().getAny()
//						.get(0);
//
//				List<Header> headersList = (List<Header>) ((BindingProvider) getRPPort)
//						.getRequestContext().get(Header.HEADER_LIST);
//				if (headersList == null) {
//					headersList = new ArrayList<Header>();
//				}
//
//				ReferencePropertiesType rp = new ReferencePropertiesType();
//
//				Header testSoapHeader1 = new Header(new QName(
//						"http://mds.globus.org/bigindex/2008/11/24",
//						"ServiceGroupEntryKey"), sge);
//				headersList.add(testSoapHeader1);
//
//				// Add SOAP headers to the web service request
//				((BindingProvider) getRPPort).getRequestContext().put(
//						Header.HEADER_LIST, headersList);
//
//				((BindingProvider) getRPPort).getRequestContext().put(
//						BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
//						entryEPR.getAddress().getValue());
//				
//				
//				GetResourcePropertyResponse resp = getRPPort
//						.getResourceProperty(WSRFConstants.CURRENT_TIME);
//				
//				JAXBElement<Calendar> cal = (JAXBElement<Calendar>)resp.getAny().get(0);
//				System.out.println(cal.getValue());
//				//Calendar date = (String)resp.getAny().get(0).toString()
//				System.out.println("Service returned time of " + resp.getAny().get(0).toString());
//				//return null;
//
//			} catch (Exception e) {
//				e.printStackTrace();
//				/*
//				 * some other exception occurred. return null, but don't change
//				 * maybeHasLifetime
//				 */
//				;
//			}

//		}
//
//		// if the above hasn't produced a time, then pull time from
//		// local clock
//		if (now == null) {
//			System.out
//					.println("No remote current time available. Getting from local clock instead.");
//			now = Calendar.getInstance();
//		}

		return now;
	}

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		this.parameters = (ServiceGroupRegistrationParameters) arg0
				.getJobDetail().getJobDataMap().get("params");
		this.entryEPR = (EndpointReferenceType) arg0.getJobDetail()
				.getJobDataMap().get("epr");
		if (this.parameters == null) {
			String msg = "Error: Registration event got null parameters (Registration event will be canceled)";
			logger.error(msg);
			return;
		}

		/*
		 * Check the target Service Group EPR. First, check to see if a target
		 * ServiceGroupEPR exists in the supplied parameters object. If not,
		 * fall back on the default value which should either initially be set
		 * automatically from the BaseClient -e or -s arguments (if BaseClient
		 * used) OR set on this object programatically as a global default.
		 */
		EndpointReferenceType epr = (parameters.getServiceGroupEPR() != null) ? parameters
				.getServiceGroupEPR() : null;

		EndpointReferenceType registrantEPR = (parameters.getRegistrantEPR() != null) ? parameters
				.getRegistrantEPR() : null;
		try {
			ServiceGroupRegistration port = ServiceGroupClientFactory
					.createServiceGroupRegistrationSoapClient(epr.getAddress()
							.getValue());
			logger.info("Renewing/Adding: "
					+ parameters.getRegistrantEPR());

			/*
			 * Get current time (preferably from resource, but local otherwise)
			 */
			Calendar term = getCurrentTimePreferringRemote(entryEPR);

			/* bump it up to the desired lifetime */
			term.add(Calendar.SECOND,
					LIFETIMECONST * parameters.getRefreshIntervalSecs());

			/* store termTime back into the parameters block */
			parameters.setInitialTerminationTime(term);

			/*
			 * TODO at this interaction, should also perhaps perform clock skew
			 * for sanity checking that local and remote clocks are in sync? The
			 * WS-Lifetime spec also suggests that clock skew could be
			 * determined and compensated for, which might be desirable here
			 * too.
			 */

			/*
			 * First attempt to extend the lifetime of existing entry, if we
			 * know about one. If we cannot do that, (entry may have
			 * disappeared, entry may not exist) add a new entry and cache that.
			 * 
			 * TODO: if we cannot extend lifetime because there is no
			 * setlifetime operation, but the entry resource still exists, then
			 * we should not do an add; instead just leave the entry to exist.
			 * If it subsequently disappears, we can catch it and add it next
			 * time round.
			 */

			boolean successfullyRenewed = false;
			if (entryEPR != null && maybeHasLifetime) {

				try {
					// TODO: append hostname of EPR here?
					logger.info("Attempting lifetime extension of entry");

					ScheduledResourceTermination lifetimePort = ServiceGroupClientFactory
							.createLifetimeSoapClient(entryEPR.getAddress()
									.getValue());
					Object sge = entryEPR.getReferenceProperties().getAny()
							.get(0);

					List<Header> headersList = (List<Header>) ((BindingProvider) lifetimePort)
							.getRequestContext().get(Header.HEADER_LIST);
					if (headersList == null) {
						headersList = new ArrayList<Header>();
					}

					ReferencePropertiesType rp = new ReferencePropertiesType();

					Header testSoapHeader1 = new Header(new QName(
							"http://mds.globus.org/bigindex/2008/11/24",
							"ServiceGroupEntryKey"), sge);
					headersList.add(testSoapHeader1);

					// Add SOAP headers to the web service request
					((BindingProvider) lifetimePort).getRequestContext().put(
							Header.HEADER_LIST, headersList);

					((BindingProvider) lifetimePort).getRequestContext().put(
							BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
							entryEPR.getAddress().getValue());


					logger.debug("SETTING TERM TIME to address: "
							+ entryEPR.getAddress().getValue() + " to "
							+ term.getTime());
					SetTerminationTime setTermTimeReq = new SetTerminationTime();
					setTermTimeReq.setRequestedTerminationTime(parameters
							.getInitialTerminationTime());
					lifetimePort.setTerminationTime(setTermTimeReq
							.getRequestedTerminationTime(),
							new Holder<Calendar>(term), new Holder<Calendar>(
									new GregorianCalendar()));

					logger.info("Successfully Renewed registration " + registrantEPR.getAddress()
							+ " to servicegroup at " + epr.getAddress() + " until: " + term.getTime());
					/*
					 * if we get this far without exception, then we have
					 * successfully renewed lifetime.
					 */
					successfullyRenewed = true;


				} catch (Exception e) {
					logger.warn("Exception renewing entry lifetime of a registration for "
									+ entryEPR.getAddress() + " - " + e);
				}
			}

			if (!successfullyRenewed) {
				/*
				 * Next, if we couldn't renew then attempt an add instead
				 * 
				 * We don't cancel the timer here on any sort of failure, as we
				 * need to keep trying to make this registration in the future.
				 * Note, the only exception to this is if we receive a false
				 * return value from a setRegistrationStatus method call to a
				 * previously set ServiceGroupRegistrationClientCallback object.
				 * 
				 * (TODO: we should perhaps perform some kind of backoff?)
				 */
				logger.info("Attempting to add new registration for " + registrantEPR.getAddress()
							+ " to servicegroup at " + epr.getAddress());

				Add request = new Add();
				request.setMemberEPR(registrantEPR);
				request.setContent(parameters.getContent());

				if (parameters.getInitialTerminationTime() == null) {
					logger.info("No termination time computed for new registation.");
				}
				request.setInitialTerminationTime(parameters
						.getInitialTerminationTime());

				entryEPR = port.add(request);
				arg0.getJobDetail().getJobDataMap().remove("epr");
				arg0.getJobDetail().getJobDataMap().put("epr", entryEPR);
				// if (callback != null) {
				// if (!callback.setRegistrationStatus(parameters, true,
				// false, null)) {
				// this.cancel(timer);
				// if (ServiceGroupRegistrationClient.this.isDebug) {
				// ServiceGroupRegistrationClient.this.status(
				// LOG_D,
				// "Canceled registration event for: "
				// + entryEPR.getAddress());
				// }
				// return;
				// }
				// }
				String msg;

				msg = "Successfully registered " + registrantEPR.getAddress()
						+ " to servicegroup at " + epr.getAddress();
				logger.info(msg);
			}
		} catch (Exception e) {
			e.printStackTrace();
			String msg;

			msg = "Warning: Could not register " + registrantEPR.getAddress()
					+ " to servicegroup at " + epr.getAddress()
					+ " -- check the URL and that the remote service is up. "
					+ " Remote exception was " + e.getMessage();
			logger.warn(msg);

			// if (callback != null) {
			// if (!callback.setRegistrationStatus(parameters, false,
			// false, e)) {
			// this.cancel(timer);
			// if (ServiceGroupRegistrationClient.this.isDebug) {
			// ServiceGroupRegistrationClient.this.status(LOG_D,
			// "Canceled registration event for: "
			// + entryEPR.getAddress());
			// }
			// return;
			// }
			// }
		}

	}

}
