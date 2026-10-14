package co.edu.poli.Sw2.Examen2.Infraestuctura.Persistencia;

import co.edu.poli.Sw2.Examen2.Aplicacion.Puerto.Salida.RepositorySeguro;
import co.edu.poli.Sw2.Examen2.Dominio.Asegurado;
import co.edu.poli.Sw2.Examen2.Dominio.Seguro;
import co.edu.poli.Sw2.Examen2.Dominio.SeguroVehiculo;
import co.edu.poli.Sw2.Examen2.Dominio.SeguroVida;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación JDBC del puerto de salida para la gestión de seguros.
 * <p>
 * Esta clase realiza el CRUD sobre la tabla base {@code seguro} y se apoya en
 * las tablas heredadas {@code seguro_vida} y {@code seguro_vehiculo}. También
 * resuelve la relación con el asegurado cargándolo desde la base de datos.
 * </p>
 *
 * @author Jeisson Romero
 * @version 1.0
 */
public class MySqlSeguroRepository implements RepositorySeguro {

    /** Instancia de conexión a la base de datos. */
    private final ConexionBD conexionBD;

    /** Almacenamiento temporal para que la aplicación siga funcionando si MySQL no responde. */
    private final List<Seguro> memoria = new ArrayList<>();

    /**
     * Crea el repositorio con la conexión a la base de datos.
     */
    public MySqlSeguroRepository() {
        this.conexionBD = ConexionBD.getInstance();
    }

    /**
     * Guarda un seguro en la base de datos.
     * <p>
     * Primero inserta la fila general en la tabla {@code seguro}. Luego, según el
     * tipo específico, inserta en la tabla correspondiente.
     * </p>
     *
     * @param seguro entidad de dominio a persistir
     */
    @Override
    public void guardar(Seguro seguro) {
        String sqlSeguro = "INSERT INTO seguro (numero, fecha_expedicion, valor, estado, asegurado_id) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = conexionBD.getConnection();
             PreparedStatement psSeguro = connection.prepareStatement(sqlSeguro)) {

            psSeguro.setString(1, seguro.getNumero());
            psSeguro.setDate(2, java.sql.Date.valueOf(seguro.getFechaExpedicion()));
            psSeguro.setDouble(3, seguro.getValor());
            psSeguro.setString(4, seguro.getEstado());
            psSeguro.setString(5, seguro.getAsegurado().getIdentificacion());
            psSeguro.executeUpdate();

            if (seguro instanceof SeguroVida) {
                guardarSeguroVida((SeguroVida) seguro, connection);
            } else if (seguro instanceof SeguroVehiculo) {
                guardarSeguroVehiculo((SeguroVehiculo) seguro, connection);
            }

        } catch (SQLException e) {
            guardarEnMemoria(seguro);
        }
    }

    /**
     * Busca un seguro por su número.
     *
     * @param numero identificador del seguro
     * @return seguro encontrado o {@code null}
     */
    @Override
    public Seguro buscarPorNumero(String numero) {
        String sql = "SELECT s.numero, s.fecha_expedicion, s.valor, s.estado, " +
                "a.identificacion, a.nombre, a.correo_electronico, " +
                "sv.nombre_beneficiario, sv.parentesco, " +
                "veh.marca " +
                "FROM seguro s " +
                "LEFT JOIN asegurado a ON a.identificacion = s.asegurado_id " +
                "LEFT JOIN seguro_vida sv ON sv.numero = s.numero " +
                "LEFT JOIN seguro_vehiculo veh ON veh.numero = s.numero " +
                "WHERE s.numero = ?";

        try (Connection connection = conexionBD.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, numero);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapearSeguro(rs);
                }
            }

        } catch (SQLException e) {
            return buscarEnMemoria(numero);
        }

        return null;
    }

    /**
     * Actualiza la información de un seguro existente.
     *
     * @param seguro seguro con datos actualizados
     */
    @Override
    public void actualizar(Seguro seguro) {
        String sqlSeguro = "UPDATE seguro SET fecha_expedicion = ?, valor = ?, estado = ?, asegurado_id = ? WHERE numero = ?";

        try (Connection connection = conexionBD.getConnection();
             PreparedStatement psSeguro = connection.prepareStatement(sqlSeguro)) {

            psSeguro.setDate(1, java.sql.Date.valueOf(seguro.getFechaExpedicion()));
            psSeguro.setDouble(2, seguro.getValor());
            psSeguro.setString(3, seguro.getEstado());
            psSeguro.setString(4, seguro.getAsegurado().getIdentificacion());
            psSeguro.setString(5, seguro.getNumero());
            psSeguro.executeUpdate();

            if (seguro instanceof SeguroVida) {
                actualizarSeguroVida((SeguroVida) seguro, connection);
            } else if (seguro instanceof SeguroVehiculo) {
                actualizarSeguroVehiculo((SeguroVehiculo) seguro, connection);
            }

        } catch (SQLException e) {
            actualizarEnMemoria(seguro);
        }
    }

    /**
     * Elimina un seguro de la base de datos por número.
     *
     * @param numero identificador del seguro
     */
    @Override
    public void eliminar(String numero) {
        String sql = "DELETE FROM seguro WHERE numero = ?";

        try (Connection connection = conexionBD.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, numero);
            ps.executeUpdate();

        } catch (SQLException e) {
            eliminarEnMemoria(numero);
        }
    }

    /**
     * Retorna la lista completa de seguros almacenados.
     *
     * @return lista de seguros
     */
    @Override
    public List<Seguro> listarTodos() {
        String sql = "SELECT s.numero, s.fecha_expedicion, s.valor, s.estado, " +
                "a.identificacion, a.nombre, a.correo_electronico, " +
                "sv.nombre_beneficiario, sv.parentesco, " +
                "veh.marca " +
                "FROM seguro s " +
                "LEFT JOIN asegurado a ON a.identificacion = s.asegurado_id " +
                "LEFT JOIN seguro_vida sv ON sv.numero = s.numero " +
                "LEFT JOIN seguro_vehiculo veh ON veh.numero = s.numero " +
                "ORDER BY s.numero";

        List<Seguro> seguros = new ArrayList<>();

        try (Connection connection = conexionBD.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {

            while (rs.next()) {
                seguros.add(mapearSeguro(rs));
            }

        } catch (SQLException e) {
            return new ArrayList<>(memoria);
        }

        return seguros;
    }

    private void guardarEnMemoria(Seguro seguro) {
        if (seguro == null) {
            return;
        }
        memoria.removeIf(item -> item != null && item.getNumero() != null && item.getNumero().equals(seguro.getNumero()));
        memoria.add(seguro);
    }

    private Seguro buscarEnMemoria(String numero) {
        if (numero == null) {
            return null;
        }
        return memoria.stream()
                .filter(seguro -> seguro != null && numero.equals(seguro.getNumero()))
                .findFirst()
                .orElse(null);
    }

    private void actualizarEnMemoria(Seguro seguro) {
        if (seguro == null) {
            return;
        }
        for (int i = 0; i < memoria.size(); i++) {
            Seguro actual = memoria.get(i);
            if (actual != null && seguro.getNumero() != null && seguro.getNumero().equals(actual.getNumero())) {
                memoria.set(i, seguro);
                return;
            }
        }
        memoria.add(seguro);
    }

    private void eliminarEnMemoria(String numero) {
        if (numero == null) {
            return;
        }
        memoria.removeIf(seguro -> seguro != null && numero.equals(seguro.getNumero()));
    }

    /**
     * Inserta el detalle específico de un seguro de vida.
     *
     * @param seguro seguro de vida a persistir
     * @param connection conexión activa
     * @throws SQLException si falla el insert
     */
    private void guardarSeguroVida(SeguroVida seguro, Connection connection) throws SQLException {
        String sql = "INSERT INTO seguro_vida (numero, nombre_beneficiario, parentesco) VALUES (?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, seguro.getNumero());
            ps.setString(2, seguro.getNombreBeneficiario());
            ps.setString(3, seguro.getParentesco());
            ps.executeUpdate();
        }
    }

    /**
     * Inserta el detalle específico de un seguro de vehículo.
     *
     * @param seguro seguro de vehículo a persistir
     * @param connection conexión activa
     * @throws SQLException si falla el insert
     */
    private void guardarSeguroVehiculo(SeguroVehiculo seguro, Connection connection) throws SQLException {
        String sql = "INSERT INTO seguro_vehiculo (numero, marca) VALUES (?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, seguro.getNumero());
            ps.setString(2, seguro.getMarca());
            ps.executeUpdate();
        }
    }

    /**
     * Actualiza los datos del seguro de vida en la tabla heredada.
     *
     * @param seguro seguro de vida actualizado
     * @param connection conexión activa
     * @throws SQLException si falla la actualización
     */
    private void actualizarSeguroVida(SeguroVida seguro, Connection connection) throws SQLException {
        String sql = "UPDATE seguro_vida SET nombre_beneficiario = ?, parentesco = ? WHERE numero = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, seguro.getNombreBeneficiario());
            ps.setString(2, seguro.getParentesco());
            ps.setString(3, seguro.getNumero());
            ps.executeUpdate();
        }
    }

    /**
     * Actualiza los datos del seguro de vehículo en la tabla heredada.
     *
     * @param seguro seguro de vehículo actualizado
     * @param connection conexión activa
     * @throws SQLException si falla la actualización
     */
    private void actualizarSeguroVehiculo(SeguroVehiculo seguro, Connection connection) throws SQLException {
        String sql = "UPDATE seguro_vehiculo SET marca = ? WHERE numero = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, seguro.getMarca());
            ps.setString(2, seguro.getNumero());
            ps.executeUpdate();
        }
    }

    /**
     * Mapea un registro de base de datos a una entidad de dominio adecuada.
     *
     * @param rs conjunto de resultados actual
     * @return entidad de dominio construida
     * @throws SQLException si hay error de acceso a columnas
     */
    private Seguro mapearSeguro(ResultSet rs) throws SQLException {
        Asegurado asegurado = new Asegurado(
                rs.getString("identificacion"),
                rs.getString("nombre"),
                rs.getString("correo_electronico")
        );

        String numero = rs.getString("numero");
        LocalDate fechaExpedicion = rs.getDate("fecha_expedicion").toLocalDate();
        Double valor = rs.getDouble("valor");
        String estado = rs.getString("estado");

        String nombreBeneficiario = rs.getString("nombre_beneficiario");
        String parentesco = rs.getString("parentesco");
        String marca = rs.getString("marca");

        if (nombreBeneficiario != null) {
            return new SeguroVida(numero, fechaExpedicion, valor, estado, asegurado, nombreBeneficiario, parentesco);
        }
        if (marca != null) {
            return new SeguroVehiculo(numero, fechaExpedicion, valor, estado, asegurado, marca);
        }

        return null;
    }
}