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
    public Response getCancion(@PathParam("idCancion") int idCancion){
        Database db = new Database();
        Cancion cnn = db.getCancion(idCancion);


        return Response
                .status(200)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                .header("Access-Control-Max-Age", "1209600")
                .entity(cnn)
                .build();
    }


    @GET()
    @Path("/search/{texto}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)

    public Response getCancion(@PathParam("texto") String texto){
        Database db = new Database();
        List<Cancion> listCancion = db.search(texto);

        System.out.print(listCancion.toString());


        return Response
                .status(200)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                .header("Access-Control-Max-Age", "1209600")
                .entity(listCancion)
                .build();

    }


}
