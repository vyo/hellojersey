package io.github.vyo.hello_jersey.filter;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

/**
 * Simple filter to apply some headers to HTML incoming requests.
 * <p/>
 * Created by manuel.weidmann on 03.03.2015
 */
@Provider
public class CORSResponseFilter
        implements ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
            throws IOException {

        MultivaluedMap<String, Object> headers = responseContext.getHeaders();

        headers.add("Access-Control-Allow-Origin", "*");
        headers.add("Access-Control-Allow-Methods", "GET, POST, DELETE, QUERY, UPDATE");
        headers.add("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With");
    }

}