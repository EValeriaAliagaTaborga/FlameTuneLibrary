package flametunelibrary.webapp;

import flametunelibrary.entity.Playlist;
import flametunelibrary.entity.Usuario;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Path("/playlist")
public class PlaylistWebApp {


    @POST
    @Path("/create/{id_user}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createPlaylist(@PathParam("id_user") int id_user, Playlist pl) {
        Database db = new Database();
        String cr = db.createPlaylist(pl.getId_playlist(),pl.getNombre_playlist(),pl.getTipo_acceso_playlist(),id_user);
        if(cr.equals("try")) {
            cr = "Playlist" +pl.getNombre_playlist()+ "creada";
        }
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



    @DELETE
    @Path("/delete/{id_pl}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUser(@PathParam("id_pl") int id) {
        Database b = new Database();
        b.deletePlaylist(id);
        return Response
                .status(200)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                .header("Access-Control-Max-Age", "1209600")
                .entity("Playlist con id "+id+" eliminada")
                .build();
    }



    @GET()
    @Path("/getList/{id_user}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)

    public List<Playlist> getPlaylist(@PathParam("id_user") int id_user){

        Database db = new Database();

        List<Playlist> listPlaylist = db.getListPlaylist(id_user);
        return listPlaylist;

    }


}
