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

        System.out.print(db.deletePlaylist(3));

    }

}
