package io.github.vyo.hello_jersey.application;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

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


        tomcat.addWebapp("", new File(webappDirLocation).getAbsolutePath());
//        // Define a web application context.
//        Context context = tomcat.addWebapp("/embedded", new File(
//                webappDirLocation).getAbsolutePath());
//
//        // Add servlet that will register Jersey REST resources
//        Tomcat.addServlet(context, "jersey-container-servlet", resourceConfig());
//        context.addServletMapping("/rest/*", "jersey-container-servlet");

        tomcat.start();
        tomcat.getServer().await();
    }

    private ServletContainer resourceConfig() {
        return new ServletContainer(new ResourceConfig(
                new Application().getClasses()));
    }


}
