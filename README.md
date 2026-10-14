# sistema-seguros
Aplicación de gestión de seguros con arquitectura hexagonal y JavaFX.

Aplicación de escritorio desarrollada en **Java** utilizando **JavaFX** para la interfaz gráfica, conectada a una base de datos relacional (MariaDB/MySQL) y estructurada bajo los principios de **Arquitectura Hexagonal**.

##   Tecnologías Utilizadas
* **Lenguaje:** Java 17+
* **Interfaz Gráfica:** JavaFX (FXML, Scene Builder)
* **Gestión de Dependencias:** Maven
* **Base de Datos:** MySQL / MariaDB
* **IDE:** Eclipse / IntelliJ IDEA

---

## Estructura del Proyecto (Arquitectura Hexagonal)
El proyecto está organizado en capas estrictamente desacopladas:


co.edu.poli.seguros
│
├── dominio (Domain - Entidades puras)
│   ├── Seguro.java (Clase abstracta)
│   ├── SeguroVida.java
│   ├── SeguroVehiculo.java
│   └── Asegurado.java
│
├── aplicacion (Application - Casos de uso y puertos)
│   ├── puerto (Interfaces: SeguroRepository, GestionarSeguroUseCase)
│   └── servicio (Lógica de negocio / Casos de uso)
│
└── infraestructura (Infrastructure - Adaptadores)
    ├── adaptador
    │   ├── entrada (SeguroController, Vistas FXML)
    │   └── salida (MySqlSeguroRepository, ConexionBD)


**Configuracion de la Base de Datos (Archivo .sql)**

Para facilitar la configuracion, el repositorio incluye un archivo con la extension .sql en la raiz del proyecto (o puedes crear una consulta ejecutando el script provisto). Sigue estos pasos para importarlo:

**Opcion A: Importar mediante phpMyAdmin (XAMPP / WampServer)**
1. Inicia los servicios de Apache y MySQL desde tu panel de XAMPP.
2. Abre tu navegador web e ingresa a http://localhost/phpmyadmin/.
3. En el menu de la izquierda, haz clic en Nueva para crear una base de datos vacia llamada db_seguros (asegurate de que el cotejamiento sea utf8mb4_general_ci).
4. Haz clic sobre la base de datos db_seguros que acabas de crear.
5. En el menu superior, selecciona la pestana Importar (Import).
6. Haz clic en Seleccionar archivo (Choose File) y busca el archivo .sql ubicado en la carpeta de tu repositorio.
7. Baja hasta el final de la pagina y presiona el boton Continuar (Go) para ejecutar la importacion automatica de las tablas y los registros de prueba.

**Opcion B: Importar mediante MySQL Workbench o consola**
1. Abre tu herramienta de gestion de base de datos preferida.
2. Abre el archivo .sql provisto en el repositorio.
3. Ejecuta todo el script para que se cree la base de datos db_seguros, las tablas asegurado y seguro, y se inserten los datos predeterminados.

**Como Iniciar y Ejecutar la Aplicacion**

1. Clonar o descargar el repositorio en tu equipo local.
2. Configurar la conexion: Verifica que los parametros de conexion en tu clase de infraestructura (ConexionBD.java) coincidan con tu entorno local (por lo general: URL jdbc:mysql://localhost:3306/db_seguros, usuario root y contrasena en blanco).
3. Abrir en el IDE: Importa el proyecto como un proyecto Maven existente en tu IDE (Eclipse o IntelliJ IDEA).
4. Ejecutar:
   - Mediante la terminal de Maven en la raiz del proyecto ejecutando: `mvn clean javafx:run`
   - O ejecutando directamente la clase principal de la interfaz grafica de JavaFX desde tu IDE.

**Operaciones CRUD Soportadas**

La aplicacion permite realizar de forma fluida las siguientes operaciones desde la interfaz visual:
- **Crear**: Registro de nuevos seguros (de vida o de vehiculos) enlazados a los asegurados predeterminados.
- **Consultar**: Busqueda, filtrado y visualizacion de listados de seguros (incluyendo casos donde un mismo asegurado cuenta con multiples seguros registrados).
- **Actualizar**: Modificacion de los atributos de un seguro existente.
- **Eliminar**: Borrado de registros directamente desde la interfaz grafica.
