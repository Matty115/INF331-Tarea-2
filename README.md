# Calculadora de Tarifas de Estacionamiento
### Matías Sandoval Kohler, Rol: 202104684-4

## Diseño
En el diagrama se expone el flujo del programa, incluendo sus interacciones específicas. Se trata de un patrón de diseño MVC, tal que: **App** inicializa las componentes; **ControladorEstacionamiento** orquesta la logística de la aplicación mediante comunicación directa con las otras componentes; **VistasEstacionamiento** le solicita entradas al usuario para que pueda responder a ControladorEstacionamiento; **AccionTicket** almacena en memoria y gestiona los objetos **Ticket** para que puedan ser consultados por ControladorEstacionamiento.

<img width="1200" height="700" alt="INF331_DiagramaT2" src="https://github.com/user-attachments/assets/13f10159-5766-49f9-8fff-b2e5b1a4bdf9" />


## Utilización
- Instalación de dependencias (JDK 17 y Maven)
  - sudo apt-get update
  - sudo apt-get install -y openjdk-17-jdk
  - sudo apt-get install -y maven
 
- Descarga del código fuente
  - git clone https://github.com/Matty115/INF331-Tarea-2.git
  - cd INF331-Tarea-2
 
- Test
  - mvn test (mvn -q test, en caso de que no se quiera ver la ejecución de todas las pruebas)
 
- Compilación, Test y Build (en un paso)
  - mvn package (mvn -q package, en caso de que no se quiera ver la ejecución de todas las pruebas)
    
- Cobertura
  - xdg-open target/site/jacoco/index.html
 
- Ejecución del programa
  - java -cp target/inf331-tarea-2-1.0-SNAPSHOT.jar App

### Ejemplo de Test (al utilizar 'nvm test' o 'nvm package')
$ mvn test
[INFO] Scanning for projects...
[INFO] 
[INFO] -----------------------< cl.usm:inf331-tarea-2 >------------------------
[INFO] Building inf331-tarea-2 1.0-SNAPSHOT
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] --- maven-compiler-plugin:3.8.1:compile (default-compile) @ inf331-tarea-2 ---
[INFO] Changes detected - recompiling the module!
[INFO] Compiling 5 source files to /home/usuario/INF331-Tarea-2/target/classes
[INFO] 
[INFO] --- maven-compiler-plugin:3.8.1:testCompile (default-testCompile) @ inf331-tarea-2 ---
[INFO] Compiling 2 source files to /home/usuario/INF331-Tarea-2/target/test-classes
[INFO] 
[INFO] --- maven-surefire-plugin:3.2.5:test (default-test) @ inf331-tarea-2 ---
[INFO] Using auto detected provider org.apache.maven.surefire.junitplatform.JUnitPlatformProvider
[INFO] 
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running AccionTicketTest
[INFO] Tests run: 20, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.045 s --  AccionTicketTest
[INFO] Running VistasEstacionamientoTest
[INFO] Tests run: 21, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.062 s --  VistasEstacionamientoTest
[INFO] 
[INFO] Results:
[INFO] 
[INFO] Tests run: 41, Failures: 0, Errors: 0, Skipped: 0
[INFO] 
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
Ejemplo de salida de tests
$ mvn test
[INFO] Scanning for projects...
[INFO] 
[INFO] -----------------------< cl.usm:inf331-tarea-2 >------------------------
[INFO] Building inf331-tarea-2 1.0-SNAPSHOT
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] --- maven-compiler-plugin:3.8.1:compile (default-compile) @ inf331-tarea-2 ---
[INFO] Changes detected - recompiling the module!
[INFO] Compiling 5 source files to /home/usuario/INF331-Tarea-2/target/classes
[INFO] 
[INFO] --- maven-compiler-plugin:3.8.1:testCompile (default-testCompile) @ inf331-tarea-2 ---
[INFO] Compiling 2 source files to /home/usuario/INF331-Tarea-2/target/test-classes
[INFO] 
[INFO] --- maven-surefire-plugin:3.2.5:test (default-test) @ inf331-tarea-2 ---
[INFO] Using auto detected provider org.apache.maven.surefire.junitplatform.JUnitPlatformProvider
[INFO] 
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running AccionTicketTest
[INFO] Tests run: 20, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.045 s -- in AccionTicketTest
[INFO] Running VistasEstacionamientoTest
[INFO] Tests run: 21, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.062 s -- in VistasEstacionamientoTest
[INFO] 
[INFO] Results:
[INFO] 
[INFO] Tests run: 41, Failures: 0, Errors: 0, Skipped: 0
[INFO] 
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------

### Consideraciones
Solo se realizó testing **AccionTicket** y **VistasEstacionamiento**. Esto se debe a que su comportamiento depende directamente de las otras componentes, y no posee funciones como tal además de init(). Para probarlo adecuadamente, no sirven las pruebas unitarias, sino de integración.

### Tipo de Cobertura
Se usa MC/DC y se usan clases de equivalencia. Esto porque además de cubrir todas las decisiones del código, también consideramos el efecto de las variables en ellas de manera individual.
