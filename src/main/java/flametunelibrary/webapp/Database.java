package flametunelibrary.webapp;

import flametunelibrary.entity.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
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
    public void create(int id, String correo, String password, String nombre, String foto, int cantidad_memb, String fecha_inicio_memb, String nro_tarjeta) {
        // Create an EntityManager
        System.out.println("Creando Usuario : " + nombre+ " id : "+id);
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try {
            // empieza transaccion
            transaction = manager.getTransaction();
            transaction.begin();
            // crea objeto
            Usuario usr = new Usuario();
            usr.setId(id);
            usr.setCorreo(correo);
            usr.setNombre_usr(nombre);
            usr.setPassword(password);
            usr.setUrl_foto_usr(foto);
            usr.setCantidad_membresias(cantidad_memb);
            usr.setFecha_inicio_membresia(fecha_inicio_memb);
            usr.setNumero_tarjeta(nro_tarjeta);
            // guarda usuario persistentemente
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
            // Get Usuario
            usuarios = manager.createQuery("SELECT s FROM Usuario s", Usuario.class).getResultList();
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
}
