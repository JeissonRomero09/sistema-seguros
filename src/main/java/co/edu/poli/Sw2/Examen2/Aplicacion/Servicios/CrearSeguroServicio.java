package co.edu.poli.Sw2.Examen2.Aplicacion.Servicios;

import co.edu.poli.Sw2.Examen2.Aplicacion.Puerto.Entrada.CrearSeguroUseCase;
import co.edu.poli.Sw2.Examen2.Aplicacion.Puerto.Salida.RepositorySeguro;
import co.edu.poli.Sw2.Examen2.Dominio.Seguro;

/**
 * Servicio de aplicación encargado de crear un seguro.
 * <p>
 * Esta clase implementa el caso de uso de creación e invoca al puerto de salida
 * para persistir la entidad de dominio.
 * </p>
 *
 * @author Jeisson Romero
 * @version 1.0
 */
public class CrearSeguroServicio implements CrearSeguroUseCase {

    /** Repositorio de seguros. */
    private final RepositorySeguro repositorySeguro;

    /**
     * Crea el servicio con su dependencia del repositorio.
     *
     * @param repositorySeguro repositorio de seguros
     */
    public CrearSeguroServicio(RepositorySeguro repositorySeguro) {
        this.repositorySeguro = repositorySeguro;
    }

    /**
     * Registra un seguro en la base de datos.
     *
     * @param seguro entidad de dominio a persistir
     */
    @Override
    public void crearSeguro(Seguro seguro) {
        if (seguro == null) {
            throw new IllegalArgumentException("El seguro no puede ser nulo.");
        }
        repositorySeguro.guardar(seguro);
    }
}