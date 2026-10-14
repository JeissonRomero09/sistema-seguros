package co.edu.poli.Sw2.Examen2.Aplicacion.Puerto.Entrada;

import co.edu.poli.Sw2.Examen2.Dominio.Seguro;

/**
 * Contrato de entrada para la actualización de un seguro existente.
 * <p>
 * Este caso de uso encapsula la intención de modificar los datos del seguro
 * que ya están almacenados.
 * </p>
 *
 * @author Jeisson Romero
 * @version 1.0
 */
public interface EditarSeguroUseCase {

    /**
     * Actualiza la información de un seguro ya registrado.
     *
     * @param seguro seguro con los datos actualizados
     */
    void editarSeguro(Seguro seguro);
}