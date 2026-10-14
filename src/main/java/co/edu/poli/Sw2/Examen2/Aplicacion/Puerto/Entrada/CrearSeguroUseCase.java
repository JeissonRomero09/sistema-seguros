package co.edu.poli.Sw2.Examen2.Aplicacion.Puerto.Entrada;

import co.edu.poli.Sw2.Examen2.Dominio.Seguro;

/**
 * Contrato de entrada para la operación de creación de un seguro.
 * <p>
 * Este puerto representa el caso de uso de registrar un nuevo seguro en el
 * sistema y delega la lógica de persistencia al servicio de aplicación.
 * </p>
 *
 * @author Jeisson Romero
 * @version 1.0
 */
public interface CrearSeguroUseCase {

    /**
     * Registra un nuevo seguro en la aplicación.
     *
     * @param seguro entidad de dominio a persistir
     */
    void crearSeguro(Seguro seguro);
}