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
        Database db = new Database();
        String cr = db.create(usr.getId(), usr.getCorreo(), usr.getPassword(), usr.getNombre_usr(), usr.getUrl_foto_usr(),
                 usr.getCantidad_membresias(), usr.getFecha_inicio_membresia(), usr.getNumero_tarjeta());
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

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUser(Usuario usr) {
        String password = usr.getPassword();
        char[] pass = password.toCharArray();
        int size = password.length();
        boolean inSize = size > 7;
        boolean inName = !password.contains(usr.getNombre_usr());
        boolean check1 = true;
        boolean check2 = true;
        boolean check3 = true;
        boolean mayus = false;
        boolean symbol = false;
        boolean number = false;

        for (int i =0;i<size;i++){
                if(!Character.isLowerCase(pass[i]) && check1){
                        mayus = true;
                        check1 = false;
                }
                if(!Character.isLetterOrDigit(pass[i]) && check2){
                        symbol = true;
                        check2 = false;
                }
                if(Character.isDigit(pass[i]) && check3){
                        number = true;
                        check3 = false;
                }
                if(!check1 && !check2 && !check3){
                        break;
                }
        }

        if(mayus && symbol && inSize && inName && number) {
                Database b = new Database();
                b.create(usr.getId(), usr.getCorreo(), usr.getPassword(), usr.getNombre_usr(), usr.getUrl_foto_usr(),
                 usr.getCantidad_membresias(), usr.getFecha_inicio_membresia(), usr.getNumero_tarjeta());
                String myjson = "User created";
                return Response
                        .status(200)
                        .header("Access-Control-Allow-Origin", "*")
                        .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
                        .header("Access-Control-Allow-Credentials", "true")
                        .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                        .header("Access-Control-Max-Age", "1209600")
                        .entity(myjson)
                        .build();
        } else {
                String[] myjson = {"Have Caps:"+mayus,"Have symbols: "+symbol,
                        "Larger than 8: "+inSize,"Password have name: "+!inName,"Have numbers: "+number
                        ,"password incorrect"};
                return Response
                        .status(403)
                        .header("Access-Control-Allow-Origin", "*")
                        .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
                        .header("Access-Control-Allow-Credentials", "true")
                        .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                        .header("Access-Control-Max-Age", "1209600")
                        .entity(myjson)
                        .build();
                        
        }
    }

    @GET()
    @Path("/get/{user}")
    public String getUsuariosUrl(@PathParam("user") int user){
        Database d = new Database();

        Usuario usr = d.getUser(user);
        return usr.toString();
        /*new Usuario();
        usr.setId(1);
        usr.setCorreo("example123@mail.com");
        usr.setNombre_usr("Nombre de Usuario");
        usr.setPassword("******");
        usr.setUrl_foto_usr("url foto");
        usr.setCantidad_membresias(4);
        usr.setFecha_inicio_membresia("2018-05-23");
        usr.setNumero_tarjeta("123");
        */

//        return  "Algo";
    }


    @GET
    @Path("/get")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)

    public String getTrackInJSON() {
        Usuario user = new Usuario();
        user.setId(4);
        user.setCantidad_membresias(0);
        user.setCorreo("mail");
        user.setFecha_inicio_membresia("0");
        user.setNombre_usr("user");
        user.setNumero_tarjeta("0");
        user.setPassword("pass");
        user.setUrl_foto_usr("foto");

        return user.toString();
    }



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
