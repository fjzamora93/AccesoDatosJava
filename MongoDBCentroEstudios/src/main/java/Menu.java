import java.util.*;

import database.MongoDBConnection;
import model.Alumno;
import model.Profesor;
import DAO.*;

/**
 * INSTRUCCIONES:
 *
 1.Insertar un profesor: tras seleccionar esta opción se pedirá al usuario la
 introducción de todos los datos
 2- Insertar un alumno: tras seleccionar esta opción se pedirá al usuario la
 introducción de todos los datos
 3- Mostrar todos los datos: se imprimirán todos los datos, tanto de usuarios
 como de profesores
 4- Mostrar profesores: se imprimirá solo los datos de los profesores
 5- Mostrar alumnos: se imprimirá solo los datos de los alumnos
 6- Buscar alumno: se pedirá un email y se mostrará el alumno que cumpla la
 condición de búsqueda
 7- Buscar profesor: se pedirá un rando de edad y se mostrará solo aquellos
 que cumpla la condición de búsqueda
 8- Actualizar profesor: se pedirá el email del profesor que se quiere actualizar
 y la nueva calificación que tendrña
 9- Dar de baja alumnos: se borrarán todos los alumnos cuya calificación sea 5
 o superior
 10- Salir
 Recordad que las estructuras de los objetos son las siguientes
 */

public class Menu {
    public static Scanner scanner;
    public static int opcion;
    public static ProfesorDao profesorDao;
    public static AlumnoDao alumnoDao;
    public static MongoDBConnection mongoDBConnection;
    public static long countRows;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        alumnoDao = new AlumnoDao();
        profesorDao = new ProfesorDao();
        mongoDBConnection = new MongoDBConnection();

        while (true) {
            displayMenu();
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 0:  //OK
                    mongoDBConnection.checkConnection();
                    break;
                case 1: // Insertar un profesor OK
                    insertProf();
                    break;

                case 2: // Insertar un alumno OK
                    insertStudent();
                    break;

                case 3: // Mostrar todos los datos OK
                    System.out.println("\n--- Todos los Datos ---");
                    alumnoDao.mostrarAlumnos();
                    profesorDao.mostrarProfesores();
                    break;

                case 4: // Mostrar profesores
                    System.out.println("\n--- Datos de los Profesores ---");
                    profesorDao.mostrarProfesores();
                    break;

                case 5: // Mostrar alumnos
                    System.out.println("\n--- Datos de los Alumnos ---");
                    alumnoDao.mostrarAlumnos();
                    break;

                case 6: // Buscar alumno por email
                    System.out.print("Introduce el email del alumno: ");
                    String emailBuscadoAlumno = scanner.nextLine().toLowerCase().trim();
                    Alumno alumnoEncontrado = alumnoDao.buscarAlumnoPorEmail(emailBuscadoAlumno);
                    if (alumnoEncontrado != null) {
                        System.out.println("Alumno encontrado: " + alumnoEncontrado);
                    } else {
                        System.out.println("Alumno no encontrado.");
                    }
                    break;

                case 7: // Buscar profesor por rango de edad
                    System.out.print("Introduce la edad mínima: ");
                    int edadMin = scanner.nextInt();
                    System.out.print("Introduce la edad máxima: ");
                    int edadMax = scanner.nextInt();
                    scanner.nextLine();  // Limpiar el buffer

                    List<Profesor> profesoresEncontrados = profesorDao.buscarProfesoresPorEdad(edadMin, edadMax);
                    if (!profesoresEncontrados.isEmpty()) {
                        System.out.println("Profesores encontrados:");
                        for (Profesor p : profesoresEncontrados) {
                            System.out.println(p);
                        }
                    } else {
                        System.out.println("No se encontraron profesores en este rango de edad.");
                    }
                    break;

                case 8: // Actualizar calificación de un profesor
                    System.out.print("Introduce el email del profesor a actualizar: ");
                    String emailActualizar = scanner.nextLine();
                    System.out.print("Introduce la nueva calificación: ");
                    double nuevaCalificacion = scanner.nextDouble();
                    scanner.nextLine();  // Limpiar el buffer

                    if (profesorDao.actualizarProfesor(emailActualizar, nuevaCalificacion) == 0) {
                        System.out.println("No se pudo actualizar el profesor.");
                        break;
                    }
                    System.out.println("Profesor actualizado correctamente.");
                    break;

                case 9: // Dar de baja a alumnos con calificación 5 o superior
                    countRows = alumnoDao.deleteGraduated();
                    System.out.println("Se acaban de graduar: " + countRows + " alumnos. Hasta siempre");
                    break;

                case 10: // Salir
                    System.out.println("Saliendo del sistema...");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Opción no válida. Intenta de nuevo.");
            }
        }
    }

    /** Muestra el menú principal del programa. */
    public static void displayMenu(){
        System.out.println("\n--- Menú ---");
        System.out.println("0. Comprobar el estado de la conexión a MongoDb");
        System.out.println("1. Insertar un profesor");
        System.out.println("2. Insertar un alumno");
        System.out.println("3. Mostrar todos los datos");
        System.out.println("4. Mostrar profesores");
        System.out.println("5. Mostrar alumnos");
        System.out.println("6. Buscar alumno");
        System.out.println("7. Buscar profesor");
        System.out.println("8. Actualizar profesor");
        System.out.println("9. Dar de baja alumnos");
        System.out.println("10. Salir");
        System.out.print("Selecciona una opción: ");
    }


    /** Clasifica el input introducido por el usuario en el programa. */
    public static void switchOption(){

    }

    /** Añade a un nuevo profesor */
    public static void insertProf(){
        System.out.println("Introduce los datos del profesor:");

        System.out.print("Nombre: ");
        String nombreProfesor = scanner.nextLine();

        System.out.print("Email: ");
        String emailProfesor = scanner.nextLine();

        System.out.print("Edad: ");
        int edadProfesor = scanner.nextInt();

        System.out.print("Calificación: ");
        double calificacionProfesor = scanner.nextDouble();

        System.out.print("Rating: ");
        double ratingProfesor = scanner.nextDouble();

        System.out.print("Género: ");
        scanner.nextLine(); // Limpiar buffer
        String generoProfesor = scanner.nextLine();

        System.out.print("Teléfono: ");
        String telefonoProfesor = scanner.nextLine();

        System.out.print("Título: ");
        String tituloProfesor = scanner.nextLine();

        System.out.print("Número de materias: ");
        int numMaterias = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        List<String> materiasProfesor = new ArrayList<>();
        for (int i = 0; i < numMaterias; i++) {
            System.out.print("Materia " + (i + 1) + ": ");
            materiasProfesor.add(scanner.nextLine());
        }

        Profesor profesor = new Profesor(ratingProfesor, edadProfesor, nombreProfesor, generoProfesor, emailProfesor, telefonoProfesor, materiasProfesor, tituloProfesor);
        profesorDao.insertarProfesor(profesor);
    }

    /** Añade a un nuevo alumno a la base de datos */
    public static void insertStudent(){
        System.out.println("Introduce los datos del alumno:");

        System.out.println("Nombre: ");
        String nombreAlumno = scanner.nextLine();

        System.out.println("Email: ");
        String emailAlumno = scanner.nextLine();

        System.out.println("Edad: ");
        int edadAlumno = scanner.nextInt();

        System.out.println("Calificación: ");
        double calificacionAlumno = scanner.nextDouble();

        System.out.println("Rating: ");
        double ratingAlumno = scanner.nextDouble();

        System.out.println("Género: ");
        scanner.nextLine(); // Limpiar buffer
        String generoAlumno = scanner.nextLine();

        System.out.println("Teléfono: ");
        String telefonoAlumno = scanner.nextLine();

        System.out.println("Grado superior: ");
        String gradoSuperiorAlumno = scanner.nextLine();

        Alumno alumno = new Alumno(ratingAlumno, edadAlumno, nombreAlumno, generoAlumno, emailAlumno, telefonoAlumno, calificacionAlumno, gradoSuperiorAlumno);
        alumnoDao.insertarAlumno(alumno);
    }

}