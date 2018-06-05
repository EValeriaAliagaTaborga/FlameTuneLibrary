package flametunelibrary.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "cancion")
public class Cancion implements Serializable {
    @Id
    @Column(name = "id_cancion", unique = true, nullable = false)
    private int id_cancion;

    @Column(name = "nombre_cancion", nullable = false)
    private String nombre_cancion;

    @Column(name = "genero_cancion")
    private String genero;

    @Column(name = "artista_cancion", nullable = false)
    private String artista;

    @Column(name = "album_cancion")
    private String album;

    @Column(name = "anio_cancion")
    private int anio_cancion;

    @Column(name = "ranking_general_cancion")
    private int ranking_personal;

    @Column(name = "url_cancion", nullable = false)
    private String url_cancion;

    public int getId_cancion() {
        return id_cancion;
    }

    public void setId_cancion(int id_cancion) {
        this.id_cancion = id_cancion;
    }

    public String getNombre_cancion() {
        return nombre_cancion;
    }

    public void setNombre_cancion(String nombre_cancion) {
        this.nombre_cancion = nombre_cancion;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public int getAnio_cancion() {
        return anio_cancion;
    }

    public void setAnio_cancion(int anio_cancion) {
        this.anio_cancion = anio_cancion;
    }

    public int getRanking_personal() {
        return ranking_personal;
    }

    public void setRanking_personal(int ranking_personal) {
        this.ranking_personal = ranking_personal;
    }

    public String getUrl_cancion() {
        return url_cancion;
    }

    public void setUrl_cancion(String url_cancion) {
        this.url_cancion = url_cancion;
    }

    @Override
    public String toString() {
        return id_cancion + "\t" + nombre_cancion + "\t" + genero + "\t" + artista + "\t" + album
                + "\t" + anio_cancion + "\t" + ranking_personal + "\t" + url_cancion;
    }
}