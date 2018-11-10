package org.orgname.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Calendar;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     *
     *
     */




    private static JedisPool pool = null;
    @BeforeClass
    public static void setUp()throws Exception{
        pool = new JedisPool("localhost", 6379);
      try( Jedis jedis = pool.getResource();) {

          jedis.del("echotime");
      }
      finally {
          if (pool == null) {
              pool.close();
          }
      }
    }


    @Test
    public void shouldAnswerWithTrue()
    {
        Service s = new Service();
        java.util.Date date=new java.util.Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        for (int i = 0; i < 3; i++) {
            cal.add(Calendar.MINUTE,i);
            s.run("test msg " + i ,cal.getTime());

        }
      try(  Jedis jedis  = pool.getResource()){
         long elem = jedis.zcount("echotime","-inf", "+inf");
         assertEquals("expeted number of elements",3 ,elem);
    }finally {
          if (pool == null) {
              pool.close();
          }
      }
    }
}
