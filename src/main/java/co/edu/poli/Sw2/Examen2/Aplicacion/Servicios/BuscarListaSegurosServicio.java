package co.edu.poli.Sw2.Examen2.Aplicacion.Servicios;

import co.edu.poli.Sw2.Examen2.Aplicacion.Puerto.Entrada.BuscarListaSegurosUseCase;
import co.edu.poli.Sw2.Examen2.Aplicacion.Puerto.Salida.RepositorySeguro;
import co.edu.poli.Sw2.Examen2.Dominio.Seguro;
import java.util.List;

/**
 * Servicio de aplicación encargado de listar todos los seguros existentes.
 * <p>
 * Esta clase implementa el caso de uso de consulta global y devuelve la lista
 * completa para que la interfaz o la capa superior la muestre al usuario.
 * </p>
 *
 * @author Jeisson Romero
 * @version 1.0
 */
public class BuscarListaSegurosServicio implements BuscarListaSegurosUseCase {

    /** Repositorio de seguros. */
    private final RepositorySeguro repositorySeguro;

    /**
     * Crea el servicio con la dependencia del repositorio.
     *
     * @param repositorySeguro repositorio de seguros
     */
    public BuscarListaSegurosServicio(RepositorySeguro repositorySeguro) {
        this.repositorySeguro = repositorySeguro;
    }

    /**
     * Retorna todos los seguros registrados en el sistema.
     *
     * @return lista de seguros
     */
    @Override
    public List<Seguro> listarSeguros() {
        return repositorySeguro.listarTodos();
    }
}