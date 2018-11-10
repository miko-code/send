package org.orgname.app;


import picocli.CommandLine;


import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Hello world!
 */
public class App {

 private final static Logger LOGGER = Logger.getLogger(App.class.getName());


    public static void main(String[] args) {


       try {
            //Parsing cmd args
            Util util = new Util();
            new CommandLine(util).parse(args);

           
            Service service = new Service();
            service.run(util.msg, util.time);
       } catch (Exception e) {
           LOGGER.log( Level.SEVERE, e.toString(), e );
        }
    }
}
