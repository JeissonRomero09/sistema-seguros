package co.edu.poli.Sw2.Examen2.Infraestuctura.Persistencia;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Clase utilitaria para gestionar la conexión a la base de datos.
 * <p>
 * Lee las variables de entorno desde el archivo {@code .env} ubicado en la
 * raíz del proyecto y construye la URL de conexión JDBC con usuario y contraseña.
 * </p>
 *
 * @author Jeisson Romero
 * @version 1.0
 */
public final class ConexionBD {

    /** Instancia singleton de la conexión. */
    private static ConexionBD instance;

    /** Propiedades cargadas desde el archivo .env. */
    private final Properties properties;

    /**
     * Constructor privado para asegurar el patrón singleton.
     */
    private ConexionBD() {
        this.properties = cargarPropiedades();
    }

    /**
     * Devuelve la única instancia de la clase.
     *
     * @return instancia singleton
     */
    public static synchronized ConexionBD getInstance() {
        if (instance == null) {
            instance = new ConexionBD();
        }
        return instance;
    }

    /**
     * Obtiene una conexión JDBC activa.
     *
     * @return conexión a la base de datos
     * @throws SQLException si falla la autenticación o la URL
     */
    public Connection getConnection() throws SQLException {
        String url = properties.getProperty("DB_URL");
        String user = properties.getProperty("DB_USER");
        String password = properties.getProperty("DB_PASSWORD", "");

        if (url == null || url.trim().isEmpty() || user == null || user.trim().isEmpty()) {
            throw new SQLException("Faltan datos de configuración en .env: DB_URL y DB_USER");
        }

        return DriverManager.getConnection(url, user, password);
    }

    /**
     * Carga las propiedades del archivo .env desde la raíz del proyecto.
     *
     * @return propiedades con la configuración de la base de datos
     */
    private Properties cargarPropiedades() {
        Properties props = new Properties();
        Path root = Path.of(System.getProperty("user.dir"));
        Path envPath = root.resolve(".env");

        if (!Files.exists(envPath)) {
            throw new IllegalStateException("No se encontró el archivo .env en la raíz del proyecto: " + envPath);
        }

        try {
            Map<String, String> valores = new HashMap<>();
            for (String linea : Files.readAllLines(envPath)) {
                String texto = linea.trim();
                if (texto.isEmpty() || texto.startsWith("#")) {
                    continue;
                }
                int posicion = texto.indexOf('=');
                if (posicion > 0) {
                    String clave = texto.substring(0, posicion).trim();
                    String valor = texto.substring(posicion + 1).trim();
                    valores.put(clave, valor);
                }
            }

            props.setProperty("DB_URL", valores.getOrDefault("DB_URL", ""));
            props.setProperty("DB_USER", valores.getOrDefault("DB_USER", ""));
            props.setProperty("DB_PASSWORD", valores.getOrDefault("DB_PASSWORD", ""));
        } catch (IOException e) {
            throw new IllegalStateException("No se pudo leer el archivo .env", e);
        }

        return props;
    }
}