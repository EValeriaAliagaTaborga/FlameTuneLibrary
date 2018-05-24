package flametunelibrary.webapp;

import flametunelibrary.entity.Usuario;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/usuarios")
public class UsuarioWebApp {

    @POST
    @Path("/post")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createTrackInJSON(Usuario usr) {
        String result = "Usuario Guardado : " + usr;
        Database b = new Database();
        b.create(usr.getId(), usr.getCorreo(), usr.getPassword(), usr.getNombre_usr(), usr.getUrl_foto_usr(),
                 usr.getCantidad_membresias(), usr.getFecha_inicio_membresia(), usr.getNumero_tarjeta());
        return Response
                .status(200)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                .header("Access-Control-Max-Age", "1209600")
                .entity(usr)
                .build();
    }


    @GET()
    public String getUsuariosUrl(){
        Usuario usr = new Usuario();
        usr.setId(1);
        usr.setCorreo("example123@gmail.com");
        usr.setNombre_usr("Nombre de Usuario");
        usr.setPassword("******");
        usr.setUrl_foto_usr("url foto");
        usr.setCantidad_membresias(4);
        usr.setFecha_inicio_membresia("2018-05-23");
        usr.setNumero_tarjeta("123");
        return usr.toString();
//        return  "Algo";
    }

    /*
    @GET
    @Path("/get")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)

    public Response getTrackInJSON() {
        Usuario usr = new Usuario();
        track.setName("Enter Sandman");
        track.setAutor("Metallica");
        track.setId(5);
        return Response
                .status(200)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                .header("Access-Control-Max-Age", "1209600")
                .entity(track)
                .build();
    }
*/


    @PUT
    @Path("/put/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUser(@PathParam("id") int id, Usuario usr) {
        // Fetch user and return response
        String result = "Usuario Guardado : " + usr;
        Database b = new Database();
        b.update(id, usr.getCorreo(), usr.getPassword(), usr.getNombre_usr(), usr.getUrl_foto_usr(),
                usr.getCantidad_membresias(), usr.getFecha_inicio_membresia(), usr.getNumero_tarjeta());
        return Response
                .status(200)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                .header("Access-Control-Max-Age", "1209600")
                .entity(usr)
                .build();
    }


    @DELETE
    @Path("/delete/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUser(@PathParam("id") int id) {
        Database b = new Database();
        b.delete(id);
        return Response
                .status(200)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                .header("Access-Control-Max-Age", "1209600")
                        //.entity(libro)
                .build();
    }

}
