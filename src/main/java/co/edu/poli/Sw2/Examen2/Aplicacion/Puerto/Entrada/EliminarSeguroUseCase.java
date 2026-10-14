package co.edu.poli.Sw2.Examen2.Aplicacion.Puerto.Entrada;

/**
 * Contrato de entrada para la eliminación de un seguro.
 * <p>
 * Encapsula la operación de borrar un seguro por su número principal.
 * </p>
 *
 * @author Jeisson Romero
 * @version 1.0
 */
public interface EliminarSeguroUseCase {

    /**
     * Elimina un seguro a partir de su número identificador.
     *
     * @param numero número del seguro a eliminar
     */
    void eliminarSeguro(String numero);
}