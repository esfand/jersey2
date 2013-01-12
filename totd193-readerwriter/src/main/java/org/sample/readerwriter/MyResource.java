package org.sample.readerwriter;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

/**
 * @author Arun Gupta
 */
@Path("fruits")
public class MyResource {
    
    private String[] response = { "apple", "banana", "mango" };
    
    @POST
    @Consumes(value=MyObject.MIME_TYPE)
    public String getFruit(MyObject mo) {
        System.out.println("endpoint invoked (getFruit(" + mo.getIndex() + "))");
        
        return response[Integer.valueOf(mo.getIndex()) % 3];
    }
    
    @POST
    @Path("fruitInt")
    public String getFruit2(int index) {
        return response[index % 3];
    }
}
