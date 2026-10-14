package co.edu.poli.Sw2.Examen2.Dominio;

import java.util.Objects;

/**
 * Representa a la persona que contrata uno o varios seguros.
 * <p>
 * En el diagrama de clases se modela como una entidad con la información básica
 * de identificación, nombre y correo electrónico del asegurado.
 * </p>
 *
 * @author Jeisson Romero
 * @version 1.0
 */
public class Asegurado {

    /** Número de identificación del asegurado. */
    private String identificacion;

    /** Nombre completo del asegurado. */
    private String nombre;

    /** Correo electrónico del asegurado. */
    private String correoElectronico;

    /**
     * Constructor por defecto.
     */
    public Asegurado() {
    }

    /**
     * Crea un asegurado con sus datos principales.
     *
     * @param identificacion número de identificación
     * @param nombre nombre completo
     * @param correoElectronico correo electrónico
     */
    public Asegurado(String identificacion, String nombre, String correoElectronico) {
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.correoElectronico = correoElectronico;
    }

    /**
     * Retorna la identificación del asegurado.
     *
     * @return identificación del asegurado
     */
    public String getIdentificacion() {
        return identificacion;
    }

    /**
     * Establece la identificación del asegurado.
     *
     * @param identificacion nueva identificación
     */
    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    /**
     * Retorna el nombre completo del asegurado.
     *
     * @return nombre del asegurado
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del asegurado.
     *
     * @param nombre nombre completo
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Retorna el correo electrónico del asegurado.
     *
     * @return correo electrónico
     */
    public String getCorreoElectronico() {
        return correoElectronico;
    }

    /**
     * Establece el correo electrónico del asegurado.
     *
     * @param correoElectronico nuevo correo electrónico
     */
    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    /**
     * Devuelve la representación textual del asegurado.
     *
     * @return representación en texto del objeto
     */
    @Override
    public String toString() {
        return "Asegurado{" +
                "identificacion='" + identificacion + '\'' +
                ", nombre='" + nombre + '\'' +
                ", correoElectronico='" + correoElectronico + '\'' +
                '}';
    }

    /**
     * Compara dos asegurados por sus atributos principales.
     *
     * @param o objeto a comparar
     * @return {@code true} si representan al mismo asegurado
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Asegurado that = (Asegurado) o;
        return Objects.equals(identificacion, that.identificacion)
                && Objects.equals(nombre, that.nombre)
                && Objects.equals(correoElectronico, that.correoElectronico);
    }

    /**
     * Genera el hash code del asegurado.
     *
     * @return valor hash calculado
     */
    @Override
    public int hashCode() {
        return Objects.hash(identificacion, nombre, correoElectronico);
    }
}