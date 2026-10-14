package co.edu.poli.Sw2.Examen2.Aplicacion.Servicios;

import co.edu.poli.Sw2.Examen2.Aplicacion.Puerto.Entrada.BuscarSeguroUseCase;
import co.edu.poli.Sw2.Examen2.Aplicacion.Puerto.Salida.RepositorySeguro;
import co.edu.poli.Sw2.Examen2.Dominio.Seguro;

/**
 * Servicio de aplicación encargado de consultar un seguro por número.
 * <p>
 * Implementa el caso de uso de búsqueda y delega la operación al repositorio.
 * </p>
 *
 * @author Jeisson Romero
 * @version 1.0
 */
public class BuscarSeguroServicio implements BuscarSeguroUseCase {

    /** Repositorio de seguros. */
    private final RepositorySeguro repositorySeguro;

    /**
     * Crea el servicio con la dependencia del repositorio.
     *
     * @param repositorySeguro repositorio de seguros
     */
    public BuscarSeguroServicio(RepositorySeguro repositorySeguro) {
        this.repositorySeguro = repositorySeguro;
    }

    /**
     * Busca un seguro por su número identificador.
     *
     * @param numero número del seguro
     * @return seguro encontrado o {@code null}
     */
    @Override
    public Seguro buscarPorNumero(String numero) {
        if (numero == null || numero.isBlank()) {
            throw new IllegalArgumentException("El número del seguro no puede estar vacío.");
        }
        return repositorySeguro.buscarPorNumero(numero);
    }
}