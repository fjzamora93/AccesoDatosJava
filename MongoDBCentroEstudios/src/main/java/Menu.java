import java.util.*;

import database.MongoDBConnection;
import model.Alumno;
import model.Profesor;
import DAO.*;

/**
 * INSTRUCCIONES:
 *
 * READ
 * 5. Obtener todos los alumnos que están matriculados en DAM
 * 6. Obtener todos los alumnos que tienen más de 20 años
 * 7. Obtener todos los profesores que imparten la asignatura de Programación
 * 8. Obtener todos los profesores que son ingenieros informáticos
 * 9. Obtener todos los profesores que tienen mensos de 40 años y más de 30
 * 10. Obtener el profesor mejor valorado
 * 11. Obtener el profesor que más asignaturas imparte
 *
 * UPDATE
 * 12. Actualizar la edad del alumno cuyo correo es aprilmanning@proflex.com a
 * 32
 *
 * 13. Actualizar la edad de todos los alumnos del ciclo de DAM en un año
 * 14. Actualizar todos los alumnos y añade el campo FCTs puesto como true
 * 15. Actualiza todos los alumnos documentos que tengan una nota inferior a 5 y
 * pon las FCTs como false
 * DELETE
 * 16.  Elimina todos aquellos registros de los alumnos que tenga las FCTs como
 * false
 * 17. Elimina todos aquellos profesores que tienen una calificación entre 1 y 4
 */

public class Menu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AlumnoDao alumnoDao = new AlumnoDao();
        ProfesorDao profesorDao = new ProfesorDao();
        MongoDBConnection mongoDBConnection = new MongoDBConnection();


        while (true) {
            // Menú principal
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
            int opcion = scanner.nextInt();
            scanner.nextLine();  // Limpiar el buffer

            switch (opcion) {
                case 0:
                    mongoDBConnection.checkConnection();
                    break;
                case 1: // Insertar un profesor
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
                    break;

                case 2: // Insertar un alumno
                    System.out.println("Introduce los datos del alumno:");

                    System.out.print("Nombre: ");
                    String nombreAlumno = scanner.nextLine();

                    System.out.print("Email: ");
                    String emailAlumno = scanner.nextLine();

                    System.out.print("Edad: ");
                    int edadAlumno = scanner.nextInt();

                    System.out.print("Calificación: ");
                    double calificacionAlumno = scanner.nextDouble();

                    System.out.print("Rating: ");
                    double ratingAlumno = scanner.nextDouble();

                    System.out.print("Género: ");
                    scanner.nextLine(); // Limpiar buffer
                    String generoAlumno = scanner.nextLine();

                    System.out.print("Teléfono: ");
                    String telefonoAlumno = scanner.nextLine();

                    System.out.print("Grado superior: ");
                    String gradoSuperiorAlumno = scanner.nextLine();

                    Alumno alumno = new Alumno(ratingAlumno, edadAlumno, nombreAlumno, generoAlumno, emailAlumno, telefonoAlumno, calificacionAlumno, gradoSuperiorAlumno);
                    alumnoDao.insertarAlumno(alumno);
                    break;

                case 3: // Mostrar todos los datos
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
                    String emailBuscadoAlumno = scanner.nextLine();
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

                    if (!profesorDao.actualizarProfesor(emailActualizar, nuevaCalificacion)) {
                        System.out.println("No se pudo actualizar el profesor.");
                    }
                    break;

                case 9: // Dar de baja a alumnos con calificación 5 o superior
                    alumnoDao.darDeBajaAlumnos();
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
}