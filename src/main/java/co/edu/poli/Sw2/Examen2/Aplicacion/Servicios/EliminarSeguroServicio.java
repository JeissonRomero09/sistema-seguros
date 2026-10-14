package co.edu.poli.Sw2.Examen2.Aplicacion.Servicios;

import co.edu.poli.Sw2.Examen2.Aplicacion.Puerto.Entrada.EliminarSeguroUseCase;
import co.edu.poli.Sw2.Examen2.Aplicacion.Puerto.Salida.RepositorySeguro;

/**
 * Servicio de aplicación encargado de eliminar un seguro.
 * <p>
 * Implementa el caso de uso de eliminación y delega la acción de borrado al
 * repositorio de seguros.
 * </p>
 *
 * @author Jeisson Romero
 * @version 1.0
 */
public class EliminarSeguroServicio implements EliminarSeguroUseCase {

    /** Repositorio de seguros. */
    private final RepositorySeguro repositorySeguro;

    /**
     * Crea el servicio con la dependencia del repositorio.
     *
     * @param repositorySeguro repositorio de seguros
     */
    public EliminarSeguroServicio(RepositorySeguro repositorySeguro) {
        this.repositorySeguro = repositorySeguro;
    }

    /**
     * Elimina un seguro por su número identificador.
     *
     * @param numero número del seguro a eliminar
     */
    @Override
    public void eliminarSeguro(String numero) {
        if (numero == null || numero.isBlank()) {
            throw new IllegalArgumentException("El número del seguro no puede estar vacío.");
        }
        repositorySeguro.eliminar(numero);
    }
}