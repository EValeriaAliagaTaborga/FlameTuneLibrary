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

//        System.out.print(db.getListPlaylist());
//        System.out.print(db.getCancion(1));
        String res = db.search("nadie").toString();
        //System.out.print(db.search("nadie").toString());
        String fin = "{";
        String[] tags = new String[8];
        tags[0] = "id_cancion";
        tags[1] = "nombre_cancion";
        tags[2] = "genero_cancion";
        tags[3] ="artista_cancion";
        tags[4] ="album_cancion";
        tags[5] ="anio_cancion";
        tags[6] ="ranking_general_cancion";
        tags[7] ="url_cancion";

        for (int i = 0, j = 0; i < res.length();i++){
            fin+=tags[j]+":";
            if(res.charAt(i)!=' '&&res.charAt(i)!='['&&res.charAt(i)!=']'&&res.charAt(i)!=','){
                fin+=res.charAt(i);
            }
            if(res.charAt(i)!=' '){
                if(j<7)
                    j++;
                else
                    j=0;
            }
            fin+=",";


        }

        fin+="}";

        System.out.print(fin);

    }

}
