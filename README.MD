# Instrucciones de Despliegue

## Requisitos previos
- **Java**: Asegúrese de tener instalada la versión 1.8 de Java.
- **Tomcat**: Utilice la versión 8.5.96 de Apache Tomcat.

## Pasos para el despliegue
1. **Clonar el repositorio**:
    ```bash
    git clone https://github.com/usuario/reservas-app.git
    ```
2. **Importar el proyecto** en su IDE preferido (Eclipse, IntelliJ, etc.).
3. **Compilar el proyecto** utilizando Maven:
    ```bash
    mvn clean install
    ```
4. **Desplegar en Tomcat**:
    - Copie el archivo `reservas-app.war` generado en la carpeta `target` a la carpeta `webapps` de su instalación de Tomcat.
    - Inicie el servidor Tomcat.

## Acceso a la aplicación
- **URL predeterminada**: [http://localhost:8080/reservas-app/index](http://localhost:8080/reservas-app/index)
