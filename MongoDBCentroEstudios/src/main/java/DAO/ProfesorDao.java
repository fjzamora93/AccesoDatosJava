package DAO;
import java.util.*;

import com.mongodb.client.FindIterable;
import model.*;
import org.bson.Document;
import com.mongodb.client.MongoCollection;
import database.MongoDBConnection;


public class ProfesorDao {
    private List<Profesor> profesores;
    private MongoCollection coleccionProfesores;
    private MongoCollection<Document> profesoresCollection;


    public ProfesorDao() {
        this.profesores = new ArrayList<>();
        coleccionProfesores = new MongoDBConnection().getProfesoresCollection();
    }

    // Método para insertar un profesor
    public void insertarProfesor(Profesor profesor) {
        profesores.add(profesor);
        Document document = new Document()
                .append("nombre", profesor.getRating())
                .append("apellido", profesor.getAge())
                .append("nombre", profesor.getName())
                .append("correo",profesor.getGender())
                .append("apellido", profesor.getPhone())
                .append("correo",profesor.getEmail())
                .append("edad",profesor.getSubjects())
                .append("edad",profesor.getTitle());
        coleccionProfesores.insertOne(document);
        System.out.println("Profesor insertado correctamente.");
    }

    // Método para buscar profesores por rango de edad
    public List<Profesor> buscarProfesoresPorEdad(int edadMin, int edadMax) {
        List<Profesor> encontrados = new ArrayList<>();
        for (Profesor profesor : profesores) {
            if (profesor.getAge() >= edadMin && profesor.getAge() <= edadMax) {
                encontrados.add(profesor);
            }
        }
        return encontrados;
    }

    // Método para mostrar todos los profesores
    public void mostrarProfesores() {
        // Obtener todos los documentos de la colección "profesores"
        FindIterable<Document> profesoresDocs = profesoresCollection.find();

        // Verificar si la colección tiene documentos
        if (!profesoresDocs.iterator().hasNext()) {
            System.out.println("No hay profesores registrados en la base de datos.");
        } else {
            // Recorrer todos los documentos y mostrar la información de los profesores
            for (Document doc : profesoresDocs) {
                double rating = doc.getDouble("rating");
                String nombre = doc.getString("nombre");
                String gender = doc.getString("gender");
                String email = doc.getString("email");
                String phone = doc.getString("phone");
                int edad = doc.getInteger("edad");
                double calificacion = doc.getDouble("calificacion");
                List<String> subjects = (List<String>) doc.get("subjects");
                String title = doc.getString("title");

                // Crear un objeto Profesor
                Profesor profesor = new Profesor(rating, edad, nombre, gender, email, phone, subjects, title);

                // Mostrar el profesor
                profesor.mostrarDetalles();
            }
        }
    }

    // Método para actualizar la calificación de un profesor por email
    public boolean actualizarProfesor(String email, double nuevaCalificacion) {
        for (Profesor profesor : profesores) {
            if (profesor.getEmail().equals(email)) {
                profesor.actualizarCalificacion(nuevaCalificacion);
                System.out.println("Calificación actualizada.");
                return true;
            }
        }
        System.out.println("Profesor no encontrado.");
        return false;
    }
}
