package flametunelibrary.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "playlist")
public class Playlist implements Serializable {
    @Id
    @Column(name = "id_playlist", unique = true, nullable = false)
    private int id_playlist;

    @Column(name = "nombre_playlist", nullable = false)
    private String nombre_playlist;

    @Column(name = "tipo_acceso_playlist")
    private int tipo_acceso_playlist;

    public int getId_playlist() {
        return id_playlist;
    }

    public void setId_playlist(int id_playlist) {
        this.id_playlist = id_playlist;
    }

    public String getNombre_playlist() {
        return nombre_playlist;
    }

    public void setNombre_playlist(String nombre_playlist) {
        this.nombre_playlist = nombre_playlist;
    }

    public int getTipo_acceso_playlist() {
        return tipo_acceso_playlist;
    }

    public void setTipo_acceso_playlist(int tipo_acceso_playlist) {
        this.tipo_acceso_playlist = tipo_acceso_playlist;
    }

    @Override
    public String toString() {
        return id_playlist + "\t" + nombre_playlist + "\t" + tipo_acceso_playlist;
    }

}