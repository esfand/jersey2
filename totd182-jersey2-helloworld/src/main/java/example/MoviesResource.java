package example;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 *
 * @author arungup
 */
@Path("movies")
public class MoviesResource {
    
    @GET
    @Path("list")
    public List<Movie> getMovies() {
        List<Movie> movies = new ArrayList<Movie>();
        
        movies.add(new Movie("Million Dollar Baby", "Hillary Swank"));
        movies.add(new Movie("Toy Story", "Buzz Light Year"));
        movies.add(new Movie("Hunger Games", "Jennifer Lawrence"));

        return movies;
    }
}
