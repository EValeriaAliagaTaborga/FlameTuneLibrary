package flametunelibrary.webapp;

import flametunelibrary.entity.Usuario;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Gustavo on 24/05/2018.
 */
public class Main {


    public static void main(String[] args){
        Database db = new Database();
        //System.out.print(db.create(7,"ejemplo6","123","nombre6","url6", 0, null, null));
        //System.out.println(db.getUserLogin("nombre"));
        //System.out.println(db.getUserLogin("nombre10"));
        //List<Usuario> users = db.readAll();
        //Usuario us = null;
        /*
        System.out.print("yolo:"+users.toString());
        for(int i = 0; i < users.size();i++){
            if(users.get(i).getId() == 3){
                us = users.get(i);
            }
        }*/

       // System.out.print(db.readAll());

       // System.out.print(db.deletePlaylist(3));

       // System.out.print(db.getListPlaylist(1).toString());

      //  System.out.println(db.createPlaylist(null,"playlist8",false,1));

//        System.out.print(db.getCancion(1));
        //System.out.print(db.search("nadie").toString());

  //      db.createTarjeta("4",123,"2018-06-02","Visa","Bolivia","Alguien",1);
        //System.out.println(new UsuarioWebApp().refrescarEstadoMembresias(11));

     /*   Usuario user = db.getUser(1);
        String jason = "{";
        jason+="\"id\":"+user.getId();
        jason+=",\"mail\":\""+user.getCorreo();
        jason+="\",\"pass\":\""+user.getPassword();
        jason+="\",\"name\":\""+user.getNombre_usr();
        if(user.getUrl_foto_usr()==null){
            jason+="\",\"url\":null";
        }else{
            jason+="\",\"url\":\""+user.getUrl_foto_usr()+"\"";
        }
        jason+=",\"cantidad\":"+user.getCantidad_membresias();
        if(user.getFecha_inicio_membresia()==null){
            jason+=",\"fecha\":null";
        }else{
            jason+=",\"fecha\":\""+user.getFecha_inicio_membresia()+"\"";
        }
        if(user.getNumero_tarjeta()==null){
            jason+=",\"numero\":null";
        }else{
            jason+=",\"numero\":\""+user.getNumero_tarjeta()+"\"";
        }
        jason+=",\"logged2\":"+user.isLogged();
        jason+="}";
        System.out.print(jason);

        */

    }

}
