package org.cagrid.mms.service.impl.cadsr;

import gov.nih.nci.system.applicationservice.ApplicationException;
import gov.nih.nci.system.applicationservice.ApplicationService;
import gov.nih.nci.system.client.proxy.ApplicationServiceProxy;

import java.io.ByteArrayInputStream;
import java.util.Map;

import org.acegisecurity.AcegiSecurityException;
import org.acegisecurity.Authentication;
import org.acegisecurity.providers.AuthenticationProvider;
import org.acegisecurity.providers.UsernamePasswordAuthenticationToken;
import org.acegisecurity.providers.rcp.RemoteAuthenticationException;
import org.aopalliance.aop.Advice;
import org.springframework.aop.Advisor;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.io.InputStreamResource;

public class ApplicationServiceProvider
{

	private static String DEFAULT_SERVICE = "ServiceInfo";
	
	public static ApplicationService getApplicationService(ApplicationContext ctx) throws Exception
	{
		return getApplicationService(ctx,DEFAULT_SERVICE,null,null,null);
	}

	public static ApplicationService getApplicationService(ApplicationContext ctx,String username, String password) throws Exception
	{
		if(username == null || password == null || username.trim().length() == 0 || password.trim().length() == 0)
				throw new Exception("Cannot authenticate user");
		return getApplicationService(ctx,DEFAULT_SERVICE,null,username,password);
	}

	public static ApplicationService getApplicationService(ApplicationContext ctx,String service) throws Exception
	{
		return getApplicationService(ctx,service, null,null,null);
	}	

	public static ApplicationService getApplicationService(ApplicationContext ctx,String service, String username, String password) throws Exception
	{
		if(username == null || password == null || username.trim().length() == 0 || password.trim().length() == 0)
			throw new Exception("Cannot authenticate user");
		return getApplicationService(ctx,service,null,username,password);
	}
	
	public static ApplicationService getApplicationServiceFromUrl(ApplicationContext ctx,String url) throws Exception
	{
		return getApplicationService(ctx,DEFAULT_SERVICE,url,null,null);
	}

	public static ApplicationService getApplicationServiceFromUrl(ApplicationContext ctx,String url,String username, String password) throws Exception
	{
		if(username == null || password == null || username.trim().length() == 0 || password.trim().length() == 0)
			throw new Exception("Cannot authenticate user");
		return getApplicationService(ctx,DEFAULT_SERVICE,url,username,password);
	}

	public static ApplicationService getApplicationServiceFromUrl(ApplicationContext ctx,String url, String service) throws Exception
	{
		return getApplicationService(ctx,service, url,null,null);
	}	

	public static ApplicationService getApplicationServiceFromUrl(ApplicationContext ctx,String url, String service, String username, String password) throws Exception
	{
		if(username == null || password == null || username.trim().length() == 0 || password.trim().length() == 0)
			throw new Exception("Cannot authenticate user");
		return getApplicationService(ctx,service,url,username,password);
	}

	@SuppressWarnings("unchecked")
	public static ApplicationService getApplicationService(ApplicationContext ctx, String service, String url, String username, String password) throws Exception
	{
		Boolean secured = username!=null && password!=null;
		ApplicationContext context = getApplicationContext(ctx, service, url,secured);

		ApplicationService as = null;
		AuthenticationProvider ap = null;
		Map<String,Object> serviceInfoMap = (Map<String, Object>) ctx.getBean(service);
		if(serviceInfoMap == null)
		{
			as = (ApplicationService) context.getBean("applicationService");
			ap = (AuthenticationProvider)context.getBean("authenticationProvider");
		}
		else
		{
			if(url == null) url ="";
			String serviceName = (String)serviceInfoMap.get("APPLICATION_SERVICE_BEAN");
			as = (ApplicationService) context.getBean(serviceName);
			ap = (AuthenticationProvider)serviceInfoMap.get("AUTHENTICATION_SERVICE_BEAN");
		}

		if(secured)
		{
			Authentication auth = new UsernamePasswordAuthenticationToken(username,password);
			try
			{
				auth = ap.authenticate(auth);
				setApplicationService(as, auth);
			}
			catch(Exception e)
			{
				String message="";
				if(e instanceof RemoteAuthenticationException || e instanceof AcegiSecurityException)
					message = "Error authenticating user:";
				else
					message = "Unknown error in authenticating user:";
				throw new ApplicationException(message,e);
			}
		}
		else
		{
			setApplicationService(as, null);
		}
		return as;
	}
	
	private static void setApplicationService(ApplicationService as, Authentication auth) {
		if(as instanceof org.springframework.aop.framework.Advised)
		{
			org.springframework.aop.framework.Advised proxy = (org.springframework.aop.framework.Advised)as;
			for(Advisor advisor: proxy.getAdvisors())
			{
				Advice advice = advisor.getAdvice();
				if(advice instanceof ApplicationServiceProxy)
				{
					ApplicationServiceProxy asp = (ApplicationServiceProxy)advice;
					asp.setApplicationService(as);
					asp.setAuthentication(auth);
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	private static ApplicationContext getApplicationContext(ApplicationContext ctx, String service, String url, Boolean secured) throws Exception
	{
		if(service == null || service.trim().length() == 0)
			throw new Exception("Name of the service can not be empty");
		
		Map<String,Object> serviceInfoMap = (Map<String, Object>) ctx.getBean(service);
		
		if(serviceInfoMap == null)
			throw new Exception("Change the configuration file!!!");
		
		//Initialized instances found in the configuration
		String asName = (String)serviceInfoMap.get("APPLICATION_SERVICE_BEAN");
		ApplicationService as = (ApplicationService)ctx.getBean(asName);
		AuthenticationProvider ap = (AuthenticationProvider)serviceInfoMap.get("AUTHENTICATION_SERVICE_BEAN");
		
		//Returning initialized instance
		if((!secured && as!=null && url == null)||(secured && as!=null && ap!=null && url == null)) //Empty URL, return the service. This helps in improving performance
			return ctx;
		
		String serviceInfo = (String)serviceInfoMap.get("APPLICATION_SERVICE_CONFIG");;
		if(url == null)	url = (String)serviceInfoMap.get("APPLICATION_SERVICE_URL");

		//URL_KEY must be present if the user is trying to use the url to reach the service
		if(serviceInfo==null ||(url == null && serviceInfo.indexOf("URL_KEY")>0) || (url!=null && serviceInfo.indexOf("URL_KEY")<0))
			throw new Exception("Change the configuration file!!!");
		
		//Resetting the URL as URL_KEY is absent in the configuration
		if(serviceInfo.indexOf("URL_KEY") <0 || url == null)
			url="";
		
		serviceInfo = serviceInfo.replace("URL_KEY", url);
		
		//Prepare in memory configuration from the information retrieved of the configuration file
		String xmlFileString = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><!DOCTYPE beans PUBLIC \"-//SPRING//DTD BEAN//EN\" \"http://www.springframework.org/dtd/spring-beans.dtd\"><beans>"+serviceInfo+"</beans>";
		GenericApplicationContext context = new GenericApplicationContext();
		XmlBeanDefinitionReader xmlReader = new XmlBeanDefinitionReader(context);
		xmlReader.setValidationMode(XmlBeanDefinitionReader.VALIDATION_NONE);
		InputStreamResource inputStreamResource = new InputStreamResource(new ByteArrayInputStream(xmlFileString.getBytes()));
		xmlReader.loadBeanDefinitions(inputStreamResource);
		context.refresh();

		as = (ApplicationService) context.getBean("applicationService");
		ap = (AuthenticationProvider)context.getBean("authenticationProvider");
		
		//Make sure the configuration has the required objects present
		if(as==null || (secured && ap==null))
			throw new Exception("Change the configuration file!!!");

			return context;
	}
}