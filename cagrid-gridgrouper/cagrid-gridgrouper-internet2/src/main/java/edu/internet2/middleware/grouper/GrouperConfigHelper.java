package edu.internet2.middleware.grouper;

import org.apache.commons.lang.StringUtils;

import java.util.Properties;

public class GrouperConfigHelper {

    public void setHibernateConnectionUrl(String value) {
        setHibernateProperty("hibernate.connection.url", value);
    }

    public void setHibernateDialect(String value) {
        setHibernateProperty("hibernate.dialect", value);
    }

    public void setHibernateConnectionDriverClass(String value) {
        setHibernateProperty("hibernate.connection.driver_class", value);
    }

    public void setHibernateConnectionUsername(String value) {
        setHibernateProperty("hibernate.connection.username", value);
    }

    public void setHibernateConnectionPassword(String value) {
        setHibernateProperty("hibernate.connection.password", value);
    }

    public void setHibernateShowSql(String value) {
        setHibernateProperty("hibernate.show_sql", value);
    }

    private void setHibernateProperty(String name, String value) {
        Properties properties = GrouperConfig.getHibernateProperties();
        if (properties.getProperty(name) == null || StringUtils.isNotBlank(value)) {
            properties.setProperty(name, value);
        }
    }
}
