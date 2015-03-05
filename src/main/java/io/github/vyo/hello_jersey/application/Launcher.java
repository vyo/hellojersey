package io.github.vyo.hello_jersey.application;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import javax.servlet.ServletException;
import java.io.File;
import java.net.MalformedURLException;

/**
 * Created by Manuel Weidmann on 01.03.2015.
 */
public class Launcher {

    public static void main(String[] args) throws Exception, LifecycleException {
        new Launcher().start();
    }

    public void start() throws ServletException, LifecycleException,
            MalformedURLException {

        // Define a folder to hold web application contents.
        String webappDirLocation = "src/main/webapp/";
        Tomcat tomcat = new Tomcat();

        // Define port number for the web application
        String webPort = System.getenv("PORT");
        if (webPort == null || webPort.isEmpty()) {
            webPort = "8080";
        }
        // Bind the port to Tomcat server
        tomcat.setPort(Integer.valueOf(webPort));

        tomcat.enableNaming();

//        StandardContext ctx = (StandardContext)
        tomcat.addWebapp("/", new File(webappDirLocation).getAbsolutePath());
        //declare an alternate location for your "WEB-INF/classes" dir:
//        File additionWebInfClasses = new File("target/classes");
//        WebResourceRoot resources = new StandardRoot(ctx);
//        resources.addPreResources(new DirResourceSet(resources, "/WEB-INF/classes", additionWebInfClasses
//                .getAbsolutePath(), "/"));
//        ctx.setResources(resources);

        tomcat.start();
        tomcat.getServer().await();
    }

}