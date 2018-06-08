package flametunelibrary.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {
    @Id
    //Si no funciona el autoincrement
    //@Column(name = "id_usuario", unique = true, nullable = false)
    @Column(name = "id_usuario", unique = true)
    private int id;

    @Column(name = "correo_usuario", nullable = false, unique = true)
    private String correo;

    @Column(name = "password_usuario", nullable = false)
    private String password;

    @Column(name = "nombre_usuario", nullable = false, unique = true)
    private String nombre_usr;

    @Column(name = "url_foto_usuario")
    private String url_foto_usr;

    @Column(name = "cantidad_membresias_usuario", nullable = false)
    private int cantidad_membresias;

    @Column(name = "fecha_inicio_membresia_usuario")
    private String fecha_inicio_membresia;

    @Column(name = "numero_tarjeta")
    private String numero_tarjeta;

    @Column(name = "logged", nullable = false)
    private boolean logged;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre_usr() {
        return nombre_usr;
    }

    public void setNombre_usr(String nombre_usr) {
        this.nombre_usr = nombre_usr;
    }

    public String getUrl_foto_usr() {
        return url_foto_usr;
    }

    public void setUrl_foto_usr(String url_foto_usr) {
        this.url_foto_usr = url_foto_usr;
    }

    public int getCantidad_membresias() {
        return cantidad_membresias;
    }

    public void setCantidad_membresias(int cantidad_membresias) {
        this.cantidad_membresias = cantidad_membresias;
    }

    public String getFecha_inicio_membresia() {
        return fecha_inicio_membresia;
    }

    public void setFecha_inicio_membresia(String fecha_inicio_membresia) {
        this.fecha_inicio_membresia = fecha_inicio_membresia;
    }

    public String getNumero_tarjeta() {
        return numero_tarjeta;
    }

    public void setNumero_tarjeta(String numero_tarjeta) {
        this.numero_tarjeta = numero_tarjeta;
    }

    public boolean isLogged() {
        return logged;
    }

    public void setLogged(boolean logged) {
        this.logged = logged;
    }

    @Override
    public String toString() {
        return id + "\t" + correo + "\t" + password + "\t" + nombre_usr + "\t" + url_foto_usr
                + "\t" + cantidad_membresias + "\t" + fecha_inicio_membresia + "\t" + numero_tarjeta + "\t" + logged;
    }
}