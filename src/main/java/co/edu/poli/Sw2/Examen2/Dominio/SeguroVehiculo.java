package co.edu.poli.Sw2.Examen2.Dominio;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Representa un seguro de vehículo que hereda de la clase base Seguro.
 * <p>
 * Incluye únicamente la marca del vehículo como atributo específico del tipo
 * de seguro.
 * </p>
 *
 * @author Jeisson Romero
 * @version 1.0
 */
public class SeguroVehiculo extends Seguro {

    /** Marca del vehículo asegurado. */
    private String marca;

    /**
     * Constructor por defecto.
     */
    public SeguroVehiculo() {
        super();
    }

    /**
     * Crea un seguro de vehículo con la información común y específica.
     *
     * @param numero número del seguro
     * @param fechaExpedicion fecha de expedición
     * @param valor valor del seguro
     * @param estado estado del seguro
     * @param asegurado asegurado asociado
     * @param marca marca del vehículo
     */
    public SeguroVehiculo(String numero, LocalDate fechaExpedicion, Double valor, String estado,
                         Asegurado asegurado, String marca) {
        super(numero, fechaExpedicion, valor, estado, asegurado);
        this.marca = marca;
    }

    /**
     * Retorna la marca del vehículo.
     *
     * @return marca del vehículo
     */
    public String getMarca() {
        return marca;
    }

    /**
     * Establece la marca del vehículo.
     *
     * @param marca nueva marca
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }

    /**
     * Devuelve la representación textual del seguro de vehículo.
     *
     * @return representación textual del seguro
     */
    @Override
    public String toString() {
        return "SeguroVehiculo{" +
                "numero='" + numero + '\'' +
                ", fechaExpedicion=" + fechaExpedicion +
                ", valor=" + valor +
                ", estado='" + estado + '\'' +
                ", asegurado=" + asegurado +
                ", marca='" + marca + '\'' +
                '}';
    }

    /**
     * Compara dos seguros de vehículo por sus atributos específicos.
     *
     * @param o objeto a comparar
     * @return {@code true} si ambos seguros representan la misma información
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        SeguroVehiculo that = (SeguroVehiculo) o;
        return Objects.equals(marca, that.marca);
    }

    /**
     * Genera el hash code del seguro de vehículo.
     *
     * @return valor hash calculado
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), marca);
    }
}