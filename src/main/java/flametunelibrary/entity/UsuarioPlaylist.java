package flametunelibrary.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "usuario_playlist")
public class UsuarioPlaylist implements Serializable{

    @Id
    @Column(name = "id_usuario", nullable = false)
    private int id_user;

    @Id
    @Column(name = "id_playlist", nullable = false)
    private int id_playlist;

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_playlist() {
        return id_playlist;
    }

    public void setId_playlist(int id_playlist) {
        this.id_playlist = id_playlist;
    }

    @Override
    public String toString() {
        return id_user+ "\t" + id_playlist;
    }
}
