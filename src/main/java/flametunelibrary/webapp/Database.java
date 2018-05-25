package flametunelibrary.webapp;

import flametunelibrary.entity.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class Database {
    // Create an EntityManagerFactory when you start the application.
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
            .createEntityManagerFactory("flametunelibrary");



    public Database(){}

    public void closeDataBase(){ENTITY_MANAGER_FACTORY.close();}

    /**
     *
     * @param id
     * @param correo
     * @param password
     * @param nombre
     * @param foto
     * @param cantidad_memb
     * @param fecha_inicio_memb
     * @param nro_tarjeta
     */
    public String create(int id, String correo, String password, String nombre, String foto,
                       int cantidad_memb, String fecha_inicio_memb, String nro_tarjeta) {
        // Create an EntityManager
        System.out.println("Creando Usuario : " + nombre+ " id : "+id);
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        String w = "fail";

        try {
            // empieza transaccion
            transaction = manager.getTransaction();
            transaction.begin();
            // crea objeto
            Usuario usr = new Usuario();
            usr.setId(id);
            usr.setCorreo(correo);
            usr.setPassword(password);
            usr.setNombre_usr(nombre);
            usr.setUrl_foto_usr(foto);
            usr.setCantidad_membresias(cantidad_memb);
            usr.setFecha_inicio_membresia(fecha_inicio_memb);
            usr.setNumero_tarjeta(nro_tarjeta);
            // guarda usuario persistentemente
            manager.persist(usr);
            // envia transaccion
            transaction.commit();
            w = "try";
        } catch (Exception ex) {
            w = "execption: " + ex;
            if (transaction != null) {
                w+="\nrollback()";
                transaction.rollback();
            }
            ex.printStackTrace();
        } finally {
            manager.close();
        }
        return w;
    }

    /**
     * Get Usuarios.
     *
     * @return a List of Usuarios
     */
    public List<Usuario> readAll() {

        List<Usuario> usuarios = null;

        // Create an EntityManager
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try {
            // Get a transaction
            transaction = manager.getTransaction();
            transaction.begin();
            // Get Usuarios
            usuarios = manager.createQuery("SELECT s FROM Usuario s", Usuario.class).getResultList();
            // Get Usuario
//            usuarios = manager.createQuery("SELECT s FROM usuario s", Usuario.class).getResultList();
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        } finally {
            manager.close();
        }
        return usuarios;
    }

    public Usuario getUser(int id){
        List <Usuario> users = readAll();
        Usuario us = null;
        for(int i = 0; i < users.size();i++){
            if(users.get(i).getId() == id){
                us = users.get(i);
            }
        }
        return us;
    }

    /**
     *Eliminar Usuario
     * @param id
     */
    public void delete(int id) {
        // Create an EntityManager
        System.out.println("eliminar Usuario: "+id);
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = manager.getTransaction();
            transaction.begin();
            Usuario usr = manager.find(Usuario.class, id);
            manager.remove(usr);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        } finally {
            manager.close();
        }
    }

    /**
     *
     * @param id
     * @param correo
     * @param password
     * @param nombre
     * @param foto
     * @param cantidad_memb
     * @param fecha_inicio_memb
     * @param nro_tarjeta
     */
    public void update(int id, String correo, String password, String nombre, String foto, int cantidad_memb, String fecha_inicio_memb, String nro_tarjeta) {
        // Create an EntityManager
        System.out.println("Actualizando Usuario : "+nombre+ " con id : "+id);
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = manager.getTransaction();
            transaction.begin();
            Usuario usr = manager.find(Usuario.class, id);
            usr.setCorreo(correo);
            usr.setNombre_usr(nombre);
            usr.setPassword(password);
            usr.setUrl_foto_usr(foto);
            usr.setCantidad_membresias(cantidad_memb);
            usr.setFecha_inicio_membresia(fecha_inicio_memb);
            usr.setNumero_tarjeta(nro_tarjeta);
            manager.persist(usr);
            // envia transaccion
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        } finally {
            manager.close();
        }
    }


    public Usuario getUserLogin(String userName) {
        // Create an EntityManager
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;


        List<Usuario> listUser = new ArrayList<>();

        String un = "\'"+userName+"\'";
        String query = "select u from Usuario u where correo like " + un + " or nombre_usr like " + un;
        System.out.println(query);
        Usuario usr = null;

        try {
            // Get a transaction
            transaction = manager.getTransaction();
            transaction.begin();
            listUser = manager.createQuery(query, Usuario.class).getResultList();
            if(listUser != null && !listUser.isEmpty()) {
                usr = listUser.get(0);
                transaction.commit();
            } else {
                usr = null;
            }
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        } finally {
            manager.close();
        }
        return usr;
    }

}
