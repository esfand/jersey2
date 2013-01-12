/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package example;

import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientFactory;
import javax.ws.rs.core.GenericType;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author arungup
 */
public class MoviesResourceTest {
    
    public MoviesResourceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getMovies method, of class MoviesResource.
     */
    @Test
    public void testGetMovies() {
        System.out.println("getMovies");
        Client client = ClientFactory.newClient();
        List<Movie> movieList = client.target("http://localhost:8080/jersey2-helloworld/webresources/movies/list")
                                      .request()
                                      .get(new GenericType<List<Movie>>() {});
        assertEquals(3, movieList.size());
    }
}
