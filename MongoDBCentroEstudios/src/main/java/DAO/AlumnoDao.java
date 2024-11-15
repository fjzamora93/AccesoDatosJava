package DAO;
import java.util.*;
import model.*;
import org.bson.Document;
import com.mongodb.client.MongoCollection;
import database.MongoDBConnection;

public class AlumnoDao {
    private List<Alumno> alumnos;

    MongoCollection coleccionAlumnos;


    public AlumnoDao() {
        this.alumnos = new ArrayList<>();
        coleccionAlumnos = new MongoDBConnection().getAlumnosCollecion();
    }

    // Método para insertar un alumno
    public void insertarAlumno(Alumno alumno) {
        alumnos.add(alumno);
        Document document = new Document()
                .append("nombre", alumno.getRating())
                .append("apellido", alumno.getAge())
                .append("nombre", alumno.getName())
                .append("correo",alumno.getGender())
                .append("apellido", alumno.getPhone())
                .append("correo",alumno.getEmail())
                .append("edad",alumno.getHigherGrade())
                .append("edad",alumno.getCalification());
        coleccionAlumnos.insertOne(document);
        System.out.println("Alumno insertado correctamente.");
    }

    // Método para buscar un alumno por su email
    public Alumno buscarAlumnoPorEmail(String email) {
        for (Alumno alumno : alumnos) {
            if (alumno.getEmail().equals(email)) {
                return alumno;
            }
        }
        return null;  // Retorna null si no se encuentra el alumno
    }

    // Método para mostrar todos los alumnos
    public void mostrarAlumnos() {
        if (alumnos.isEmpty()) {
            System.out.println("No hay alumnos registrados.");
        } else {
            for (Alumno alumno : alumnos) {
                System.out.println(alumno);
            }
        }
    }

    // Método para eliminar alumnos con calificación 5 o superior
    public void darDeBajaAlumnos() {
        alumnos.removeIf(alumno -> alumno.getCalification() >= 5);
        System.out.println("Alumnos con calificación 5 o superior han sido dados de baja.");
    }
}