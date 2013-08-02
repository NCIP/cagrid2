package org.cagrid.serviceregistration;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.xml.ws.Holder;

import org.cagrid.serviceregistration.model.ServiceGroupRegistrationParameters;
import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourcelifetime_1_2_draft_01.SetTerminationTime;
import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourcelifetime_1_2_draft_01_wsdl.ScheduledResourceTermination;
import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_servicegroup_1_2_draft_01.Add;
import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_servicegroup_1_2_draft_01_wsdl.ServiceGroupRegistration;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.xmlsoap.schemas.ws._2004._03.addressing.EndpointReferenceType;

public class RegistrationJob implements Job {
	
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


	public ServiceGroupRegistrationParameters getParameters() {
		return this.parameters;
	}

	/**
	 * Gets the current time, preferably from the remote resource specified, and
	 * failing that from local system.
	 */
	private Calendar getCurrentTimePreferringRemote(EndpointReferenceType epr) {
		System.out.println(
				"Attempting to get current time, preferring remote.");

		Calendar now = null;

		if (epr != null && maybeHasLifetime) {

			// try {
			// GetResourceProperty getRPPort = rpLocator
			// .getGetResourcePropertyPort(epr);
			// setSecurity((Stub) getRPPort);
			//
			// GetResourcePropertyResponse resp = getRPPort
			// .getResourceProperty(WSRFConstants.CURRENT_TIME);
			//
			// MessageElement[] any = resp.get_any();
			// return (Calendar) ObjectDeserializer.toObject(any[0],
			// Calendar.class);
			//
			// } catch (InvalidResourcePropertyQNameFaultType e) {
			// /*
			// * this fault means that we can talk to the resource but it
			// * does not have current time, which implies that it does
			// * not support WS-ResourceLifetime mechanisms
			// */
			// maybeHasLifetime = false;
			// /* now fall through */
			// } catch (Exception e) {
			// /*
			// * some other exception occurred. return null, but don't
			// * change maybeHasLifetime
			// */
			// ;
			// }

		}

		// if the above hasn't produced a time, then pull time from
		// local clock
		if (now == null) {
			System.out.println(
							"No remote current time available. Getting from local clock instead.");
			now = Calendar.getInstance();
		}

		return now;
	}

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		System.out.println("HERE");
		this.parameters = (ServiceGroupRegistrationParameters) arg0
				.getJobDetail().getJobDataMap().get("params");
		if (this.parameters == null) {
			String msg = "Error: Registration event got null parameters (Registration event will be canceled)";
			System.out.println(msg);
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
				.getServiceGroupEPR()
				:null;

		// // if we have no servicegroup EPR specified, generate the
		// // local index EPR, but do *not* cache
		// if (epr == null) {
		// try {
		// epr = getIndexEPR();
		// } catch (Exception e) {
		// logger.error("When forming local target index EPR: " + e);
		// }
		// }

		EndpointReferenceType registrantEPR = (parameters.getRegistrantEPR() != null) ? parameters
				.getRegistrantEPR()
				:null;

		// // if have no registratant EPR, generate the local index EPR
		// // but do *not* cache
		// if (registrantEPR == null) {
		// try {
		// registrantEPR = getIndexEPR();
		// } catch (Exception e) {
		// logger.error("When forming local registrant index EPR: "
		// + e);
		// }
		// }

		try {
			ServiceGroupRegistration port = ServiceGroupClientFactory
					.createServiceGroupRegistrationSoapClient(epr.getAddress()
							.getValue());
				System.out.println(
						"Renewing/Adding: " + parameters.getRegistrantEPR());

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
					System.out.println(
							"Attempting lifetime extension of entry");

					ScheduledResourceTermination lifetimePort = ServiceGroupClientFactory
							.createLifetimeSoapClient(entryEPR.getAddress()
									.getValue());
					// setSecurity((Stub) lifetimePort);
					SetTerminationTime setTermTimeReq = new SetTerminationTime();
					setTermTimeReq.setRequestedTerminationTime(parameters
							.getInitialTerminationTime());
					lifetimePort.setTerminationTime(
							setTermTimeReq.getRequestedTerminationTime(),
							new Holder<Calendar>(setTermTimeReq
									.getRequestedTerminationTime()),
							new Holder<Calendar>(new GregorianCalendar()));

					/*
					 * if we get this far without exception, then we have
					 * successfully renewed lifetime.
					 */
					successfullyRenewed = true;

					// if (callback != null) {
					// if (!callback.setRegistrationStatus(parameters,
					// true, true, null)) {
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
				} catch (Exception e) {
					System.out.println(
							"Exception renewing entry lifetime of a registration for "
									+ entryEPR.getAddress() + " - " + e);
					//
					// if (callback != null) {
					// if (!callback.setRegistrationStatus(parameters,
					// false, true, e)) {
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
					/* so now we will continue into the add code */
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
				System.out.println(
						"Attempting add");

				Add request = new Add();
				request.setMemberEPR(registrantEPR);
				request.setContent(parameters.getContent());

				if (parameters.getInitialTerminationTime() == null) {
					System.out.println(
									"No termination time computed for new registation.");
				}
				request.setInitialTerminationTime(parameters
						.getInitialTerminationTime());

				entryEPR = port.add(request);
				System.out.println(
							"Add response: " + entryEPR + entryEPR.getAddress().getValue());
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

					msg = "Successfully registered "
							+ registrantEPR.getAddress()
							+ " to servicegroup at " + epr.getAddress();
					System.out.println(msg);
			}
		} catch (Exception e) {
			e.printStackTrace();
			String msg;

			msg = "Warning: Could not register " + registrantEPR.getAddress()
					+ " to servicegroup at " + epr.getAddress()
					+ " -- check the URL and that the remote service is up. "
					+ " Remote exception was " + e.getMessage();
			System.out.println( msg);

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
