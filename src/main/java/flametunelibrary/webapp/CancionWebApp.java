package flametunelibrary.webapp;

import flametunelibrary.entity.Cancion;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by Gustavo on 12/06/2018.
 */

@Path("/canciones")
public class CancionWebApp {

    @GET()
    @Path("/get/{idCancion}")
    public Cancion getCancion(@PathParam("idCancion") int idCancion){
        Database db = new Database();
        Cancion cnn = db.getCancion(idCancion);

        return cnn;
    }


    @GET()
    @Path("/search/{texto}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Cancion> getCancion(@PathParam("texto") String texto){
        Database db = new Database();
        List<Cancion> listCancion = db.search(texto);

        System.out.print(listCancion.toString());

        return listCancion;

    }


}
