package edu.internet2.middleware.grouper;

import java.io.InputStream;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Properties;
import java.util.Set;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;
import net.sf.hibernate.SessionFactory;
import net.sf.hibernate.Transaction;
import net.sf.hibernate.cfg.Configuration;

class HibernateHelper
{
  private static final Configuration CFG;
  private static final SessionFactory FACTORY;

  protected static void delete(Object paramObject)
    throws HibernateException
  {
    LinkedHashSet localLinkedHashSet = new LinkedHashSet();
    localLinkedHashSet.add(paramObject);
    delete(localLinkedHashSet);
  }

  protected static void delete(Set paramSet)
    throws HibernateException
  {
    Object localObject1 = null;
    String str = "delete";
    DebugLog.info(HibernateHelper.class, str + ": will delete " + paramSet.size());
    try
    {
      Session localSession = getSession();
      Transaction localTransaction = localSession.beginTransaction();
      Iterator localIterator = paramSet.iterator();
      try
      {
        while (localIterator.hasNext())
        {
          Object localObject2 = localIterator.next();
          localObject1 = localObject2;
          DebugLog.info(HibernateHelper.class, str + ": deleting " + localObject2);
          localSession.delete(_getPersistent(localSession, localObject2));
          DebugLog.info(HibernateHelper.class, str + ": deleted " + localObject2);
        }
        localTransaction.commit();
      }
      catch (HibernateException localHibernateException2)
      {
        str = str + ": unable to delete " + localObject1 + ": " + localHibernateException2.getMessage();
        localTransaction.rollback();
        throw new HibernateException(str, localHibernateException2);
      }
      finally
      {
        localSession.close();
      }
    }
    catch (HibernateException localHibernateException1)
    {
      str = "hibernate error: " + localHibernateException1.getMessage();
      ErrorLog.error(HibernateHelper.class, str);
      throw new HibernateException(str, localHibernateException1);
    }
    DebugLog.info(HibernateHelper.class, str + ": deleted " + paramSet.size());
  }

  protected static Session getSession()
    throws HibernateException
  {
    return FACTORY.openSession();
  }

  protected static void save(Object paramObject)
    throws HibernateException
  {
    LinkedHashSet localLinkedHashSet = new LinkedHashSet();
    localLinkedHashSet.add(paramObject);
    save(localLinkedHashSet);
  }

  protected static void save(Set paramSet)
    throws HibernateException
  {
    Object localObject1 = null;
    String str = "save";
    DebugLog.info(HibernateHelper.class, str + ": will save " + paramSet.size());
    try
    {
      Session localSession = getSession();
      Transaction localTransaction = localSession.beginTransaction();
      Iterator localIterator = paramSet.iterator();
      try
      {
        while (localIterator.hasNext())
        {
          Object localObject2 = localIterator.next();
          localObject1 = localObject2;
          DebugLog.info(HibernateHelper.class, str + ": saving " + localObject2);
          localSession.saveOrUpdate(localObject2);
          DebugLog.info(HibernateHelper.class, str + ": saved " + localObject2);
        }
        localTransaction.commit();
      }
      catch (HibernateException localHibernateException2)
      {
        str = str + ": unable to save " + localObject1 + ": " + localHibernateException2.getMessage();
        localTransaction.rollback();
        throw new HibernateException(str, localHibernateException2);
      }
      finally
      {
        localSession.close();
      }
    }
    catch (HibernateException localHibernateException1)
    {
      str = "hibernate error: " + localHibernateException1.getMessage();
      ErrorLog.error(HibernateHelper.class, str);
      throw new HibernateException(str, localHibernateException1);
    }
    DebugLog.info(HibernateHelper.class, str + ": saved " + paramSet.size());
  }

  protected static void saveAndDelete(Set paramSet1, Set paramSet2)
    throws HibernateException
  {
    try
    {
      Session localSession = getSession();
      Transaction localObject1 = localSession.beginTransaction();
      Iterator localIterator1 = paramSet2.iterator();
      Iterator localIterator2 = paramSet1.iterator();
      try
      {
        String str;
        while (localIterator1.hasNext())
        {
          Object localObject2 = localIterator1.next();
          try
          {
            localSession.delete(_getPersistent(localSession, localObject2));
          }
          catch (HibernateException localHibernateException2)
          {
            str = "hibernate error: unable to delete " + localObject2 + ": " + localHibernateException2.getMessage();
            throw new HibernateException(str, localHibernateException2);
          }
        }
        while (localIterator2.hasNext())
        {
          Object localObject3 = localIterator2.next();
          try
          {
            localSession.saveOrUpdate(localObject3);
          }
          catch (HibernateException localHibernateException3)
          {
            str = "hibernate error: unable to save " + localObject3 + ": " + localHibernateException3.getMessage();
            throw new HibernateException(str, localHibernateException3);
          }
        }
        try
        {
          ((Transaction)localObject1).commit();
        }
        catch (HibernateException localHibernateException4)
        {
          str = "hibernate commit error: " + localHibernateException4.getMessage();
          throw new HibernateException(str, localHibernateException4);
        }
      }
      catch (HibernateException localHibernateException5)
      {
        ((Transaction)localObject1).rollback();
        throw new HibernateException(localHibernateException5.getMessage(), localHibernateException5);
      }
      finally
      {
        localSession.close();
      }
    }
    catch (HibernateException localHibernateException1)
    {
      Object localObject1 = localHibernateException1.getMessage();
      ErrorLog.error(HibernateHelper.class, (String)localObject1);
      throw new HibernateException((String)localObject1, localHibernateException1);
    }
    DebugLog.info(HibernateHelper.class, "saved: " + paramSet1.size() + " deleted: " + paramSet2.size());
  }

  private static Object _getPersistent(Session paramSession, Object paramObject)
  {
    int i = 0;
    if (paramSession.contains(paramObject))
      i = 1;
    else
      try
      {
        paramSession.update(paramObject);
        if (paramSession.contains(paramObject))
          i = 1;
      }
      catch (HibernateException localHibernateException)
      {
        ErrorLog.error(HibernateHelper.class, "error getting persistent object: " + localHibernateException.getMessage());
      }
    if (i == 0)
      try
      {
        throw new GrouperRuntimeException();
      }
      catch (GrouperRuntimeException localGrouperRuntimeException)
      {
        String str = "error getting persistent object: " + paramObject + ":" + localGrouperRuntimeException.getMessage();
        ErrorLog.fatal(HibernateHelper.class, str);
        localGrouperRuntimeException.printStackTrace();
        throw new GrouperRuntimeException(str, localGrouperRuntimeException);
      }
    return paramObject;
  }

  static
  {
    try
    {
      CFG = new Configuration().addProperties(GrouperConfig.getHibernateProperties()).addClass(Attribute.class).addClass(Field.class).addClass(GrouperSession.class).addClass(GroupType.class).addClass(HibernateSubject.class).addClass(HibernateSubjectAttribute.class).addClass(Member.class).addClass(Membership.class).addClass(Owner.class).addClass(Settings.class);
      FACTORY = CFG.buildSessionFactory();
    }
    catch (Throwable localThrowable)
    {
      Object localObject = "unable to initialize hibernate: " + localThrowable.getMessage();
      ErrorLog.fatal(HibernateHelper.class, (String)localObject);
      throw new ExceptionInInitializerError(localThrowable);
    }
  }
}

/* Location:           /Users/cmelean/Documents/Developer/source/cagrid/git/caGrid2/cagrid-gridgrouper/cagrid-gridgrouper-internet2/src/main/resources/grouper.jar
 * Qualified Name:     edu.internet2.middleware.grouper.HibernateHelper
 * JD-Core Version:    0.6.2
 */