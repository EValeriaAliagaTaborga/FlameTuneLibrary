package flametunelibrary.webapp;

import flametunelibrary.entity.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.Calendar;
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
                       int cantidad_memb, String fecha_inicio_memb, String nro_tarjeta, boolean logged) {
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
            usr.setLogged(logged);
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
        Usuario us = null;
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try {
            // Get a transaction
            transaction = manager.getTransaction();
            transaction.begin();
            // Get Usuario
            us = manager.find(Usuario.class, id);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        } finally {
            manager.close();
        }
        return us;
    }

    /**
     *Eliminar Usuario
     * @param id
     */
    public void delete(int id) {
        // Create an EntityManager
        System.out.println("Eliminar Usuario: "+id);
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
    public void update(int id, String correo, String password, String nombre, String foto, int cantidad_memb, String fecha_inicio_memb, String nro_tarjeta,boolean logged) {
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
            usr.setLogged(logged);
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

        List<Usuario> listUser = new ArrayList();

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


    public String createPlaylist(int id_playlist, String nombre_playlist, boolean tipo_acceso_playlist, int id_user) {
        // Create an EntityManager
        System.out.println("Creando Playlist : " + nombre_playlist+ " id : "+id_playlist);
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        String w = "fail";

        try {
            // empieza transaccion
            transaction = manager.getTransaction();
            transaction.begin();
            // crea playlist
            Playlist pl = new Playlist();
            pl.setId_playlist(id_playlist);
            pl.setNombre_playlist(nombre_playlist);
            pl.setTipo_acceso_playlist(tipo_acceso_playlist);
            // guarda playlist persistentemente
            manager.persist(pl);
            // aniadir relacion entre usuario y playlist
            UsuarioPlaylist userPlaylist = new UsuarioPlaylist();
            userPlaylist.setId_user(id_user);
            userPlaylist.setId_playlist(id_playlist);
            manager.persist(userPlaylist);
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



    public void updatePlaylist(int id, String nombre_playlist, boolean tipo_acceso_playlist) {
        System.out.println("Actualizando Playlist : "+ nombre_playlist + " con id : "+id);
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = manager.getTransaction();
            transaction.begin();
            // actualizando datos playlist
            Playlist pl = manager.find(Playlist.class, id);
            pl.setNombre_playlist(nombre_playlist);
            pl.setTipo_acceso_playlist(tipo_acceso_playlist);
            manager.persist(pl);
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


    public String deletePlaylist(int id) {
        // Create an EntityManager
        System.out.println("eliminar Playlist: "+id);
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        List<UsuarioPlaylist> uplist = new ArrayList<UsuarioPlaylist>();

        String query = "SELECT up FROM UsuarioPlaylist up WHERE up.id_playlist=" + id;
        String res = "";

        try {
            transaction = manager.getTransaction();
            transaction.begin();
            // eliminando filas de relacion usuario-playlist que tengan relacion con la playlist
            uplist = manager.createQuery(query, UsuarioPlaylist.class).getResultList();
            res+="query: " + query + "\n";
            res+="uplist: "+uplist.toString()+"\n";
            if(!uplist.isEmpty()) {
                for(UsuarioPlaylist userPl : uplist){
                    manager.remove(userPl);
                    res+="algo ";
                }
            } else {
                res+="vacio ";
            }
            // eliminando playlist
            Playlist pl = manager.find(Playlist.class, id);
            manager.remove(pl);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
            res+="nada";
        } finally {
            manager.close();
        }

        return res;

    }

    public List<Playlist> getListPlaylist(int id_user) {
        List<Playlist> playlists = null;

        // Create an EntityManager
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;


        try {
            // Get a transaction
            transaction = manager.getTransaction();
            transaction.begin();
            // Get Playlists
            playlists = manager.createQuery("SELECT pl FROM Playlist pl, UsuarioPlaylist upl WHERE pl.id_playlist like upl.id_playlist and upl.id_user = "+id_user, Playlist.class).getResultList();
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        } finally {
            manager.close();
        }
        return playlists;
    }

    public Cancion getCancion(int idCancion) {
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        //String e = "";
        Cancion cnn = null;
        try {
            // Get a transaction
            transaction = manager.getTransaction();
            transaction.begin();
            // Get Cancion
            cnn = manager.find(Cancion.class, idCancion);
            transaction.commit();
            //e+="inTry";
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
            //e+="inCatch:er: "+ex+"\n";
        } finally {
            //e+="inFinally";
            manager.close();
        }
        return cnn;
    }


    public List<Cancion> search(String texto) {

        // Create an EntityManager
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        List<Cancion> canciones = null;

        String txt = "\'%"+texto+"%\'";
        String query = "SELECT c FROM Cancion c where nombre_cancion like "+ txt + " or genero like "+txt + " or artista like" + txt + " or album like "+txt ;
        try {
            // Get a transaction
            transaction = manager.getTransaction();
            transaction.begin();
            // Get Canciones de resultados de busqueda
            canciones = manager.createQuery(query, Cancion.class).getResultList();
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        } finally {
            manager.close();
        }
        return canciones;    }


    public String createTarjeta(String nro_tarjeta, int cvc_tarjeta, String fecha_vencimiento_tarjeta, String tipo_tarjeta, String pais_tarjeta, String nombre_usuario_tarjeta, int id_user) {
        System.out.println("Creando Tarjeta : " + nro_tarjeta);
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        String w = "fail";

        try {
            // empieza transaccion
            transaction = manager.getTransaction();
            transaction.begin();
            //verificar existencia de tarjeta
            Tarjeta trj = manager.find(Tarjeta.class, nro_tarjeta);
            if(trj == null) {
                // crear objeto
                trj = new Tarjeta();
                trj.setNro_tarjeta(nro_tarjeta);
                trj.setCvc_tarjeta(cvc_tarjeta);
                trj.setFecha_vencimiento_tarjeta(fecha_vencimiento_tarjeta);
                trj.setTipo_tarjeta(tipo_tarjeta);
                trj.setPais_tarjeta(pais_tarjeta);
                trj.setNombre_usuario_tarjeta(nombre_usuario_tarjeta);
                // guarda tarjeta persistentemente
                manager.persist(trj);
            }
            //aniadir tarjeta a usuario
            Usuario user = manager.find(Usuario.class, id_user);
            user.setNumero_tarjeta(nro_tarjeta);
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


    public String comprarMembresiaUsuario(int id_user, int cantidadMembresias, String fecha) {
        System.out.println("Aniadiendo : " + cantidadMembresias + " membresias al usuario " + id_user);

        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        String w = "fail";

        try {
            // empieza transaccion
            transaction = manager.getTransaction();
            transaction.begin();
            //aniadir datos membresia
            Usuario user = manager.find(Usuario.class, id_user);
            user.setCantidad_membresias(user.getCantidad_membresias() + cantidadMembresias);
            //si no hay una activa la fecha debe ser renovada a la actual
            if(user.getCantidad_membresias() == 0) {
                user.setFecha_inicio_membresia(fecha);
            }
            // guarda usuario persistentemente
             manager.persist(user);
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


    public String actualizarDatosMembresias(Calendar fecha, int cantidadMemb, int id_user) {

        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        String w = "fail";
        try {
            // empieza transaccion
            transaction = manager.getTransaction();
            transaction.begin();
            //actualizando datos membresia
            Usuario user = manager.find(Usuario.class,id_user);
            user.setCantidad_membresias(cantidadMemb);
            if(fecha != null) {
                user.setFecha_inicio_membresia(fecha.get(Calendar.YEAR) + "-" + (fecha.get(Calendar.MONTH) + 1) + "-" + fecha.get(Calendar.DAY_OF_MONTH));
            } else {
                user.setFecha_inicio_membresia(null);
            }
            // guarda usuario persistentemente
            manager.persist(user);
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
}
