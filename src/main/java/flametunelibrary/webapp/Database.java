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
            .createEntityManagerFactory("FlameTuneLibrary");



    public Database(){}

    public void closeDataBase(){ENTITY_MANAGER_FACTORY.close();}

    /**
     * Create a new Usuario.
     * @param name
     * @param autor
     */
    public void create(int id, String name, String autor) {
        // Create an EntityManager
        System.out.println("Creando Libro : " + name+ " id : "+id);
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try {
            // empieza transaccion
            transaction = manager.getTransaction();
            transaction.begin();
            // crea objeto
            Usuario usr = new Usuario();
            usr.setId(id);
            usr.setName(name);
            usr.setAutor(autor);
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
            usuarios = manager.createQuery("SELECT s FROM Libro s", Usuario.class).getResultList();
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
        System.out.println("eliminar Libro: "+id);
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
     * actualizando Usuario.
     *
     * @param id
     * @param name
     * @param age
     */
    public void update(int id, String name, String age) {
        // Create an EntityManager
        System.out.println("Actualizando Libro : "+name+ " con id : "+id);
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = manager.getTransaction();
            transaction.begin();
            Usuario usr = manager.find(Usuario.class, id);
            usr.setName(name);
            usr.setAutor(age);
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
