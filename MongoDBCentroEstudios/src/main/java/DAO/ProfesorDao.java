package DAO;
import java.util.*;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import model.*;
import org.bson.Document;
import com.mongodb.client.MongoCollection;
import database.MongoDBConnection;
import org.bson.conversions.Bson;


public class ProfesorDao {
    private List<Profesor> profesoresList;
    private MongoCollection coleccionProfesores;


    public ProfesorDao() {
        this.profesoresList = new ArrayList<>();
        coleccionProfesores = new MongoDBConnection().getProfesoresCollection();
    }

    /**SISTEMA POJO: añadir un profesor*/
    public void insertarProfesor(Profesor profesor) {
        coleccionProfesores.insertOne(profesor);
        System.out.println("Profesor insertado correctamente." + profesor.toString());
    }

    /** POJO: búsqueda de profesores */
    public List<Profesor> buscarProfesoresPorEdad(int edadMin, int edadMax) {
        this.profesoresList.clear();
        Bson filter = Filters.and(
                Filters.gte("age", edadMin),
                Filters.lte("age", edadMax)
        );
        FindIterable <Profesor> iterable = this.coleccionProfesores.find(filter);
        MongoCursor <Profesor> cursor = iterable.cursor();

        while (cursor.hasNext()){
            Profesor profesor = cursor.next();
            this.profesoresList.add(profesor);
        }
        return this.profesoresList;

    }


    /** Mostrar todos los profesores */
    public List mostrarProfesores() {
        profesoresList.clear();
        FindIterable<Profesor> iterable1 = this.coleccionProfesores.find(Profesor.class);
        MongoCursor<Profesor> cursor = iterable1.cursor();
        while (cursor.hasNext()) {
            Profesor usuario = cursor.next();
            profesoresList.add(usuario);
            System.out.println("-" + usuario.toString());
        }
        return profesoresList;
    }

    // Método para actualizar la calificación de un profesor por email
    public long actualizarProfesor(String email, double nuevaCalificacion) {
        Bson filter = Filters.and(
                Filters.eq("email", email)
        );

        Bson actualizacion = Updates.set(
                "rating", nuevaCalificacion
        );

        return coleccionProfesores.updateOne(filter, actualizacion).getModifiedCount();
    }
}
