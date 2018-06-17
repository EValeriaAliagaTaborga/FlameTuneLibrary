package flametunelibrary.webapp;

import flametunelibrary.entity.Tarjeta;
import flametunelibrary.entity.Usuario;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
//import flametunelibrary.entity.Login;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

//import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

//import com.google.gson.JsonObject;

@Path("/usuarios")
public class UsuarioWebApp {


    private boolean[] checks (Usuario usr){
        boolean[] res = new boolean[5];

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
            if(Character.isUpperCase(pass[i]) && check1){
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

        res[0]=inSize;
        res[1]=inName;
        res[2]=mayus;
        res[3]=symbol;
        res[4]=number;

        return res;

    }

    @POST
    @Path("/post")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createTrackInJSON(Usuario usr) {
        Database db = new Database();
        String cr = db.create(usr.getId(), usr.getCorreo(), usr.getPassword(), usr.getNombre_usr(), usr.getUrl_foto_usr(),
                usr.getCantidad_membresias(), usr.getFecha_inicio_membresia(), usr.getNumero_tarjeta(), usr.isLogged());
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

        boolean[] res = checks(usr);

        boolean inSize = res[0];
        boolean inName = res[1];
        boolean mayus = res[2];
        boolean symbol = res[3];
        boolean number = res[4];

        if(mayus && symbol && inSize && inName && number) {
            Database b = new Database();

            String myjson = "User created: " + b.create(usr.getId(), usr.getCorreo(), usr.getPassword(), usr.getNombre_usr(), usr.getUrl_foto_usr(),
                    usr.getCantidad_membresias(), usr.getFecha_inicio_membresia(), usr.getNumero_tarjeta(),usr.isLogged());
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
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Usuario getUsuariosUrl(@PathParam("user") int user){
        Database d = new Database();

        Usuario usr = d.getUser(user);
        return usr;
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


    @PUT
    @Path("/put/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUser(@PathParam("id") int id, Usuario usr) {
        boolean[] res = checks(usr);

        boolean inSize = res[0];
        boolean inName = res[1];
        boolean mayus = res[2];
        boolean symbol = res[3];
        boolean number = res[4];

        if (mayus && symbol && inSize && inName && number) {
            Database b = new Database();

            b.update(id, usr.getCorreo(), usr.getPassword(), usr.getNombre_usr(), usr.getUrl_foto_usr(),
                    usr.getCantidad_membresias(), usr.getFecha_inicio_membresia(), usr.getNumero_tarjeta(),usr.isLogged());

            return Response
                    .status(200)
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
                    .header("Access-Control-Allow-Credentials", "true")
                    .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                    .header("Access-Control-Max-Age", "1209600")
                    .entity(usr)
                    .build();
        } else {
            return Response
                    .status(403)
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
                    .header("Access-Control-Allow-Credentials", "true")
                    .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                    .header("Access-Control-Max-Age", "1209600")
                    .entity(usr)
                    .build();

        }
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
                .entity("Usuario eliminado")
                .build();
    }

    private static int intentos = 1;
    private static int horaDesbloqueo = 0;
    private static boolean bloqueado = false;


    @POST
    //@Path("/login/{userName}")
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    //public Response login(@PathParam("userName") String userName, @QueryParam("password") String password) {
    public JSONObject login(JSONObject data) throws JSONException {

        //String result = "Usuario Guardado : " + usr;
        //TODO crear clase de login y adaptar metodo para usarla
        String userName = data.getString("name");
        String password = data.getString("password");
        Database b = new Database();
        Usuario user = b.getUserLogin(userName);

        String result = "";
        JSONObject res2 = null;

        if(!bloqueado) {
            if (user == null) {
                //Correo o nombre de usuario inexistentes
                result = "Usuario inexistente";
            } else {
                String passwordGuardada = user.getPassword();
                if (password.equals(passwordGuardada)) {
                    intentos = 1;
                    //result = "Login satisfactorio, acceso permitido";
                    result = "logged";
                    res2 = new JSONObject("{\"id\":"+user.getId()+"}");
                    b.update(user.getId(), user.getCorreo(), user.getPassword(), user.getNombre_usr(), user.getUrl_foto_usr(),
                            user.getCantidad_membresias(), user.getFecha_inicio_membresia(), user.getNumero_tarjeta(), true);
                } else {
                    if (intentos > 3) {
                        //bloqueado
                        result = "Usuario bloqueado, por favor espere";
                        Date date = new Date();
                        DateFormat hourFormat = new SimpleDateFormat("HHmmss");
                        horaDesbloqueo = Integer.parseInt(hourFormat.format(date))+100;
                        bloqueado = true;
                    } else {
                        //login fallido
                        intentos++;
                        result = "Login fallido, password incorrecto"+password+":"+passwordGuardada;
                    }
                }
            }
        } else {
            Date date2 = new Date();
            DateFormat hourFormat2 = new SimpleDateFormat("HHmmss");
            int horaActual = Integer.parseInt(hourFormat2.format(date2));
//            int horaComprobacion = horaDesbloqueo-40;
            if (horaActual < horaDesbloqueo){
//              int tiempoRestante = horaComprobacion - horaActual;
//               result = "Usuario bloqueado. Quedan "+tiempoRestante+" segundos\n ha"+horaActual;
                result = "Usuario bloqueado, por favor espere";
            } else {
                bloqueado = false;
                horaDesbloqueo = 0;
                intentos = 1;
            }
        }
        if(res2 != null){
            return res2;
        } else {
            return new JSONObject("{\"res\":"+result+"}");
        }


    }




    @GET()
    @Path("/loginPrueba")
    public String prueba() {
        return  "Algo";
    }


    @GET
    @Path("/logout/{userName}")
    public Response logout(@PathParam("userName") String userName) {
        Database db = new Database();
        Usuario user = db.getUserLogin(userName);
        db.update(user.getId(), user.getCorreo(), user.getPassword(), user.getNombre_usr(), user.getUrl_foto_usr(),
                user.getCantidad_membresias(), user.getFecha_inicio_membresia(), user.getNumero_tarjeta(), false);
        return Response
                .status(200)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                .header("Access-Control-Max-Age", "1209600")
                .entity("Sesion cerrada")
                .build();
    }



    @POST
    @Path("/createTarjeta/{id_user}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createTarjeta(@PathParam("id_user") int id_user, Tarjeta trj) {
        Database db = new Database();
        String cr = db.createTarjeta(trj.getNro_tarjeta(), trj.getCvc_tarjeta(), trj.getFecha_vencimiento_tarjeta(),trj.getTipo_tarjeta(), trj.getPais_tarjeta(),trj.getNombre_usuario_tarjeta(),id_user);
        if(cr.equals("try")) {
            cr="Tarjeta creada";
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
    //@Path("/comprarMembresias/{id_user}")
    @Path("/comprarMembresias/{id_user}/{cantidad_membresias}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response comprarMembresias(@PathParam("id_user") int id_user,@PathParam("cantidad_membresias") int cantidad_membresias) {
        Database db = new Database();
        Calendar today = Calendar.getInstance();
        String fecha = today.get(Calendar.YEAR)+"-"+today.get(Calendar.MONTH)+"-"+today.get(Calendar.DAY_OF_MONTH);


        String cr = db.comprarMembresiaUsuario(id_user, cantidad_membresias, fecha);
        if(cr.equals("try")) {
            cr = cantidad_membresias+" membresias compradas el "+ fecha;
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


//    public void refrescarEstadoMembresias

    //TODO Renovar membresia



}