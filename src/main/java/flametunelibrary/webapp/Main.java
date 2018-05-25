package flametunelibrary.webapp;

/**
 * Created by Gustavo on 24/05/2018.
 */
public class Main {


    public static void main(String[] args){
        Database db = new Database();
        //System.out.print(db.create(7,"ejemplo6","123","nombre6","url6", 0, null, null));
        System.out.println(db.getUserLogin("nombre"));
        System.out.println(db.getUserLogin("nombre10"));

    }

}
