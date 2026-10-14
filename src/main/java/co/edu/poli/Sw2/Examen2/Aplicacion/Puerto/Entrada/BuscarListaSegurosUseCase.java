package co.edu.poli.Sw2.Examen2.Aplicacion.Puerto.Entrada;

import co.edu.poli.Sw2.Examen2.Dominio.Seguro;
import java.util.List;

/**
 * Contrato de entrada para consultar la lista completa de seguros.
 * <p>
 * Este caso de uso permite listar todos los seguros del sistema, incluyendo el
 * escenario en el que un asegurado posee más de un seguro asociado.
 * </p>
 *
 * @author Jeisson Romero
 * @version 1.0
 */
public interface BuscarListaSegurosUseCase {

    /**
     * Retorna todos los seguros registrados en el sistema.
     *
     * @return lista de seguros
     */
    List<Seguro> listarSeguros();
}