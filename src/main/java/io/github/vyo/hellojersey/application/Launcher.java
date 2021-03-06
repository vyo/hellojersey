package io.github.vyo.hellojersey.application;

import io.swagger.models.Contact;
import io.swagger.models.Info;
import io.swagger.models.License;
import io.swagger.models.Swagger;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;
import org.h2.tools.DeleteDbFiles;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.io.File;
import java.net.MalformedURLException;
import java.sql.*;

/**
 * Created by Manuel Weidmann on 01.03.2015.
 */
public class Launcher {

    public static void main(String[] args) throws Exception {
        new Launcher().start();
    }

    public void start() throws ServletException, LifecycleException,
            MalformedURLException, ClassNotFoundException, SQLException {

        /*
        H2 INITIALISATION START
         */
        //set up H2 database
        // delete the database named 'test' in the user home directory
        DeleteDbFiles.execute("~/hellojersey-db", "hellojersey", false);

        Class.forName("org.h2.Driver");
        Connection conn = DriverManager.getConnection("jdbc:h2:~/hellojersey-db/hellojersey;" +
                "AUTO_SERVER=TRUE", "sa", "");
        Statement stat = conn.createStatement();

        // this line would initialize the database
        // from the SQL script file 'init.sql'
        // stat.execute("runscript from 'init.sql'");

        stat.execute("CREATE TABLE PUBLIC.GREETING(ID INT AUTO_INCREMENT PRIMARY KEY NOT NULL, ALIAS VARCHAR(255)," +
                "MESSAGE VARCHAR(255) NOT NULL);ALTER TABLE PUBLIC.GREETING ADD CONSTRAINT unique_ID UNIQUE (ID);");
        stat.execute("insert into GREETING values(1, 'default', 'Hello Jersey!')");
        stat.close();
        conn.close();
         /*
        H2 INITIALISATION END
         */

        /*
        TOMCAT INITIALISATION START
         */
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

        StandardContext ctx = (StandardContext) tomcat.addWebapp("/",
                new File(webappDirLocation).getAbsolutePath());


        Info info = new Info()
                .title("Swagger Sample App")
                .description("Desc")
                .termsOfService("http://helloreverb.com/terms/")
                .contact(new Contact()
                        .email("apiteam@swagger.io"))
                .license(new License()
                        .name("Apache 2.0")
                        .url("http://www.apache.org/licenses/LICENSE-2.0.html"));
        Swagger swagger = new Swagger().info(info);
        ctx.getServletContext().setAttribute("swagger", swagger);

        //declare an alternate location for your "WEB-INF/classes" dir:
        File additionWebInfClasses = new File("target/classes");
        WebResourceRoot resources = new StandardRoot(ctx);
        resources.addPreResources(new DirResourceSet(resources, "/WEB-INF/classes", additionWebInfClasses
                .getAbsolutePath(), "/"));
        ctx.setResources(resources);

        tomcat.start();
        tomcat.getServer().await();
        /*
        TOMCAT INITIALISATION END
         */
    }

}
