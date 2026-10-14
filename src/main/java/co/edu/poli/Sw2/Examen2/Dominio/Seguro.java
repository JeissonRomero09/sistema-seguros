package co.edu.poli.Sw2.Examen2.Dominio;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Representa la clase base abstracta para todos los seguros del sistema.
 * <p>
 * Según el diagrama de clases, un seguro tiene número, fecha de expedición,
 * valor, estado y está asociado a un asegurado.
 * </p>
 *
 * @author Jeisson Romero
 * @version 1.0
 */
public abstract class Seguro {

    /** Número de identificación del seguro. */
    protected String numero;

    /** Fecha en la que se expidió el seguro. */
    protected LocalDate fechaExpedicion;

    /** Valor del seguro. */
    protected Double valor;

    /** Estado actual del seguro. */
    protected String estado;

    /** Asegurado que contrata el seguro. */
    protected Asegurado asegurado;

    /**
     * Constructor por defecto.
     */
    protected Seguro() {
    }

    /**
     * Crea un seguro con los atributos comunes del dominio.
     *
     * @param numero número del seguro
     * @param fechaExpedicion fecha de expedición
     * @param valor valor del seguro
     * @param estado estado actual del seguro
     * @param asegurado asegurado asociado
     */
    protected Seguro(String numero, LocalDate fechaExpedicion, Double valor, String estado, Asegurado asegurado) {
        this.numero = numero;
        this.fechaExpedicion = fechaExpedicion;
        this.valor = valor;
        this.estado = estado;
        this.asegurado = asegurado;
    }

    /**
     * Retorna el número del seguro.
     *
     * @return número del seguro
     */
    public String getNumero() {
        return numero;
    }

    /**
     * Establece el número del seguro.
     *
     * @param numero nuevo número
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    /**
     * Retorna la fecha de expedición del seguro.
     *
     * @return fecha de expedición
     */
    public LocalDate getFechaExpedicion() {
        return fechaExpedicion;
    }

    /**
     * Establece la fecha de expedición del seguro.
     *
     * @param fechaExpedicion nueva fecha de expedición
     */
    public void setFechaExpedicion(LocalDate fechaExpedicion) {
        this.fechaExpedicion = fechaExpedicion;
    }

    /**
     * Retorna el valor del seguro.
     *
     * @return valor del seguro
     */
    public Double getValor() {
        return valor;
    }

    /**
     * Establece el valor del seguro.
     *
     * @param valor nuevo valor
     */
    public void setValor(Double valor) {
        this.valor = valor;
    }

    /**
     * Retorna el estado del seguro.
     *
     * @return estado del seguro
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Establece el estado del seguro.
     *
     * @param estado nuevo estado
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Retorna el asegurado asociado.
     *
     * @return asegurado asociado
     */
    public Asegurado getAsegurado() {
        return asegurado;
    }

    /**
     * Establece el asegurado asociado.
     *
     * @param asegurado nuevo asegurado
     */
    public void setAsegurado(Asegurado asegurado) {
        this.asegurado = asegurado;
    }

    /**
     * Devuelve la representación textual del seguro base.
     *
     * @return representación textual del seguro
     */
    @Override
    public String toString() {
        return "Seguro{" +
                "numero='" + numero + '\'' +
                ", fechaExpedicion=" + fechaExpedicion +
                ", valor=" + valor +
                ", estado='" + estado + '\'' +
                ", asegurado=" + asegurado +
                '}';
    }

    /**
     * Compara dos seguros por sus atributos comunes.
     *
     * @param o objeto a comparar
     * @return {@code true} si ambos seguros son equivalentes
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Seguro seguro = (Seguro) o;
        return Objects.equals(numero, seguro.numero)
                && Objects.equals(fechaExpedicion, seguro.fechaExpedicion)
                && Objects.equals(valor, seguro.valor)
                && Objects.equals(estado, seguro.estado)
                && Objects.equals(asegurado, seguro.asegurado);
    }

    /**
     * Genera el hash code del seguro.
     *
     * @return valor hash calculado
     */
    @Override
    public int hashCode() {
        return Objects.hash(numero, fechaExpedicion, valor, estado, asegurado);
    }
}