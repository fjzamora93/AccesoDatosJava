package DAO;
import java.util.*;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import model.*;
import org.bson.Document;
import com.mongodb.client.MongoCollection;
import database.MongoDBConnection;


public class ProfesorDao {
    private List<Profesor> profesores;
    private MongoCollection coleccionProfesores;


    public ProfesorDao() {
        this.profesores = new ArrayList<>();
        coleccionProfesores = new MongoDBConnection().getProfesoresCollection();
    }

    // Método para insertar un profesor
    public void insertarProfesor(Profesor profesor) {
        coleccionProfesores.insertOne(profesor);
        System.out.println("Profesor insertado correctamente." + profesor.toString());
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
        FindIterable<Profesor> iterable1 = this.coleccionProfesores.find(Profesor.class);
        MongoCursor<Profesor> cursor = iterable1.cursor();
        while (cursor.hasNext()) {
            Profesor usuario = cursor.next();
            System.out.println("-" + usuario.toString());
        }
        // Al usar el método de esta manera, NO vamos a cosneguir que lo mapee. Así que utilizaremos el da arriba.
        // FindIterable<Document> iterable2 =  this.coleccionAlumnos.find();
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
