package flametunelibrary.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "tarjeta")
public class Tarjeta implements Serializable {
    @Id
    @Column(name = "numero_tarjeta", unique = true, nullable = false)
    private String nro_tarjeta;

    @Column(name = "cvc_tarjeta", nullable = false)
    private int cvc_tarjeta;

    @Column(name = "fecha_vencimiento_tarjeta", nullable = false)
    private String fecha_vencimiento_tarjeta;

    @Column(name = "tipo_tarjeta", nullable = false)
    private String tipo_tarjeta;

    @Column(name = "pais_tajeta", nullable = false)
    private String pais_tarjeta;

    @Column(name = "nombre_usuario_tarjeta", nullable = false)
    private String nombre_usuario_tarjeta;

    public String getNro_tarjeta() {
        return nro_tarjeta;
    }

    public void setNro_tarjeta(String nro_tarjeta) {
        this.nro_tarjeta = nro_tarjeta;
    }

    public int getCvc_tarjeta() {
        return cvc_tarjeta;
    }

    public void setCvc_tarjeta(int cvc_tarjeta) {
        this.cvc_tarjeta = cvc_tarjeta;
    }

    public String getFecha_vencimiento_tarjeta() {
        return fecha_vencimiento_tarjeta;
    }

    public void setFecha_vencimiento_tarjeta(String fecha_vencimiento_tarjeta) {
        this.fecha_vencimiento_tarjeta = fecha_vencimiento_tarjeta;
    }

    public String getTipo_tarjeta() {
        return tipo_tarjeta;
    }

    public void setTipo_tarjeta(String tipo_tarjeta) {
        this.tipo_tarjeta = tipo_tarjeta;
    }

    public String getPais_tarjeta() {
        return pais_tarjeta;
    }

    public void setPais_tarjeta(String pais_tarjeta) {
        this.pais_tarjeta = pais_tarjeta;
    }

    public String getNombre_usuario_tarjeta() {
        return nombre_usuario_tarjeta;
    }

    public void setNombre_usuario_tarjeta(String nombre_usuario_tarjeta) {
        this.nombre_usuario_tarjeta = nombre_usuario_tarjeta;
    }

    @Override
    public String toString() {
        return nro_tarjeta + "\t" + cvc_tarjeta + "\t" + fecha_vencimiento_tarjeta + "\t" + tipo_tarjeta + "\t" + pais_tarjeta
                + "\t" + nombre_usuario_tarjeta;
    }

}

