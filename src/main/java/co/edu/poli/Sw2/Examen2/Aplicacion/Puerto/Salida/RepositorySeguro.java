package co.edu.poli.Sw2.Examen2.Aplicacion.Puerto.Salida;

import co.edu.poli.Sw2.Examen2.Dominio.Seguro;
import java.util.List;

/**
 * Puerto de salida que define los contratos de persistencia para la gestión de seguros.
 * <p>
 * Esta interfaz abstrae la infraestructura de almacenamiento y permite que la
 * capa de aplicación dependa de una interfaz, no de una implementación concreta.
 * </p>
 *
 * @author Jeisson Romero
 * @version 1.0
 */
public interface RepositorySeguro {

    /**
     * Guarda o registra un nuevo seguro en la base de datos.
     *
     * @param seguro entidad a persistir
     */
    void guardar(Seguro seguro);

    /**
     * Busca un seguro por su número principal.
     *
     * @param numero identificador del seguro
     * @return seguro encontrado o {@code null}
     */
    Seguro buscarPorNumero(String numero);

    /**
     * Actualiza la información de un seguro existente.
     *
     * @param seguro seguro con datos actualizados
     */
    void actualizar(Seguro seguro);

    /**
     * Elimina un seguro por su número identificador.
     *
     * @param numero número del seguro a eliminar
     */
    void eliminar(String numero);

    /**
     * Retorna todos los seguros registrados.
     *
     * @return lista de seguros
     */
    List<Seguro> listarTodos();
}