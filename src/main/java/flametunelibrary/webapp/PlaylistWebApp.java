package flametunelibrary.webapp;

import flametunelibrary.entity.Playlist;
import flametunelibrary.entity.Usuario;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Path("/playlist")
public class PlaylistWebApp {


    @POST
    @Path("/create/{id_user}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createPlaylist(@PathParam("id_user") int id_user, Playlist pl) {
        Database db = new Database();
        String cr = db.createPlaylist(pl.getId_playlist(),pl.getNombre_playlist(),pl.getTipo_acceso_playlist(),id_user);
        return Response
                .status(200)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                .header("Access-Control-Max-Age", "1209600")
                .entity(cr)
                .build();
    }


    @PUT
    @Path("/update/{id_pl}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updatePlaytlist(@PathParam("id_pl") int id, Playlist pl) {
        // Fetch user and return response
        String result = "Playlist Guardada : " + pl;
        Database b = new Database();
        b.updatePlaylist(id, pl.getNombre_playlist(), pl.getTipo_acceso_playlist());
        return Response
                .status(200)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                .header("Access-Control-Max-Age", "1209600")
                .entity(pl)
                .build();
    }




}
