package co.edu.poli.Sw2.Examen2.Dominio;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Representa un seguro de vida que hereda de la clase base Seguro.
 * <p>
 * Incluye los atributos propios del tipo de seguro: nombre del beneficiario y
 * parentesco con el asegurado.
 * </p>
 *
 * @authorJeisson Romero
 * @version 1.0
 */
public class SeguroVida extends Seguro {

    /** Nombre del beneficiario del seguro de vida. */
    private String nombreBeneficiario;

    /** Parentesco del beneficiario con el asegurado. */
    private String parentesco;

    /**
     * Constructor por defecto.
     */
    public SeguroVida() {
        super();
    }

    /**
     * Crea un seguro de vida con información base y específica.
     *
     * @param numero numero del seguro
     * @param fechaExpedicion fecha de expedición
     * @param valor valor del seguro
     * @param estado estado del seguro
     * @param asegurado asegurado asociado
     * @param nombreBeneficiario nombre del beneficiario
     * @param parentesco parentesco con el asegurado
     */
    public SeguroVida(String numero, LocalDate fechaExpedicion, Double valor, String estado,
                     Asegurado asegurado, String nombreBeneficiario, String parentesco) {
        super(numero, fechaExpedicion, valor, estado, asegurado);
        this.nombreBeneficiario = nombreBeneficiario;
        this.parentesco = parentesco;
    }

    /**
     * Retorna el nombre del beneficiario.
     *
     * @return nombre del beneficiario
     */
    public String getNombreBeneficiario() {
        return nombreBeneficiario;
    }

    /**
     * Establece el nombre del beneficiario.
     *
     * @param nombreBeneficiario nombre del beneficiario
     */
    public void setNombreBeneficiario(String nombreBeneficiario) {
        this.nombreBeneficiario = nombreBeneficiario;
    }

    /**
     * Retorna el parentesco del beneficiario.
     *
     * @return parentesco del beneficiario
     */
    public String getParentesco() {
        return parentesco;
    }

    /**
     * Establece el parentesco del beneficiario.
     *
     * @param parentesco parentesco del beneficiario
     */
    public void setParentesco(String parentesco) {
        this.parentesco = parentesco;
    }

    /**
     * Devuelve la representación textual del seguro de vida.
     *
     * @return representación textual del seguro
     */
    @Override
    public String toString() {
        return "SeguroVida{" +
                "numero='" + numero + '\'' +
                ", fechaExpedicion=" + fechaExpedicion +
                ", valor=" + valor +
                ", estado='" + estado + '\'' +
                ", asegurado=" + asegurado +
                ", nombreBeneficiario='" + nombreBeneficiario + '\'' +
                ", parentesco='" + parentesco + '\'' +
                '}';
    }

    /**
     * Compara dos seguros de vida por sus atributos propios y heredados.
     *
     * @param o objeto a comparar
     * @return {@code true} si los seguros son equivalentes
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
        SeguroVida that = (SeguroVida) o;
        return Objects.equals(nombreBeneficiario, that.nombreBeneficiario)
                && Objects.equals(parentesco, that.parentesco);
    }

    /**
     * Genera el hash code del seguro de vida.
     *
     * @return valor hash calculado
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), nombreBeneficiario, parentesco);
    }
}