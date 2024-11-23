package org.example.utility;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HiberUtil {
    public static Session getSession() {
      try  {
            SessionFactory factory = new Configuration().configure("config.xml").buildSessionFactory();
            return factory.openSession();
        }
      catch (Exception e)
      {
          System.out.println(e.toString());
      }
        return null;
    }
}
