package flametunelibrary.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "playlist_cancion")
public class PlaylistCancion implements Serializable {

    @Id
    @Column(name = "id_cancion", nullable = false)
    private int id_cancion;

    @Column(name = "id_playlist", nullable = false)
    private int id_playlist;

    public int getId_cancion() {
        return id_cancion;
    }

    public void setId_cancion(int id_cancion) {
        this.id_cancion = id_cancion;
    }

    public int getId_playlist() {
        return id_playlist;
    }

    public void setId_playlist(int id_playlist) {
        this.id_playlist = id_playlist;
    }

    @Override
    public String toString() {
        return id_cancion + "\t" + id_playlist;
    }
}