package co.edu.poli.Sw2.Examen2.Aplicacion.Servicios;

import co.edu.poli.Sw2.Examen2.Aplicacion.Puerto.Entrada.EditarSeguroUseCase;
import co.edu.poli.Sw2.Examen2.Aplicacion.Puerto.Salida.RepositorySeguro;
import co.edu.poli.Sw2.Examen2.Dominio.Seguro;

/**
 * Servicio de aplicación encargado de actualizar un seguro.
 * <p>
 * Implementa el caso de uso de edición y delega la operación de persistencia
 * al repositorio asociado.
 * </p>
 *
 * @author Jeisson Romero
 * @version 1.0
 */
public class EditarSeguroServicio implements EditarSeguroUseCase {

    /** Repositorio de seguros. */
    private final RepositorySeguro repositorySeguro;

    /**
     * Crea el servicio con la dependencia del repositorio.
     *
     * @param repositorySeguro repositorio de seguros
     */
    public EditarSeguroServicio(RepositorySeguro repositorySeguro) {
        this.repositorySeguro = repositorySeguro;
    }

    /**
     * Actualiza la información de un seguro ya registrado.
     *
     * @param seguro seguro actualizado
     */
    @Override
    public void editarSeguro(Seguro seguro) {
        if (seguro == null) {
            throw new IllegalArgumentException("El seguro no puede ser nulo.");
        }
        repositorySeguro.actualizar(seguro);
    }
}