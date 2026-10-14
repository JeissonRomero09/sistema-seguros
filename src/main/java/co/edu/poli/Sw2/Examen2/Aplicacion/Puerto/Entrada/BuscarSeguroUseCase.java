package co.edu.poli.Sw2.Examen2.Aplicacion.Puerto.Entrada;

import co.edu.poli.Sw2.Examen2.Dominio.Seguro;

/**
 * Contrato de entrada para consultar un seguro por su número.
 * <p>
 * Permite recuperar una entidad de dominio específica a partir de la
 * identificación principal del seguro.
 * </p>
 *
 * @author Jeisson Romero
 * @version 1.0
 */
public interface BuscarSeguroUseCase {

    /**
     * Busca un seguro por su número.
     *
     * @param numero número identificador del seguro
     * @return seguro encontrado o {@code null} si no existe
     */
    Seguro buscarPorNumero(String numero);
}