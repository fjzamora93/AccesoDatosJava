package DAO;
import java.util.*;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import model.*;
import org.bson.Document;
import com.mongodb.client.MongoCollection;
import database.MongoDBConnection;
import org.bson.conversions.Bson;

public class AlumnoDao {
    private List<Alumno> alumnosList;

    MongoCollection coleccionAlumnos;


    public AlumnoDao() {
        this.alumnosList = new ArrayList<>();
        coleccionAlumnos = new MongoDBConnection().getAlumnosCollection();
    }

    /**
     * Sistema TRADICIONAL para insertar alumno. Tienes que ir campo a campo rellenándolo
     * */
    public void insertarAlumno(Alumno alumno) {
        alumnosList.add(alumno);
        Document document = new Document()
                .append("rating", alumno.getRating())
                .append("name", alumno.getName())
                .append("gender",alumno.getGender())
                .append("phone", alumno.getPhone())
                .append("email",alumno.getEmail())
                .append("higherGrade",alumno.getHigherGrade())
                .append("calification",alumno.getCalification());
        coleccionAlumnos.insertOne(document);
        System.out.println("Alumno insertado correctamente.");
    }


    /**
     * Sistema POJO: el alumno puede insertarse directamente.
     * */
    public void insertarAlumnoPOJO(Alumno alumno){
        this.coleccionAlumnos.insertOne(alumno);
    }

    /**
     * Sistema POJO: de búsqueda directa.
     * */
    public void mostrarAlumnos() {
        FindIterable<Alumno> iterable1 = this.coleccionAlumnos.find(Alumno.class);
        MongoCursor<Alumno> cursor = iterable1.cursor();
        while (cursor.hasNext()) {
            Alumno usuario = cursor.next();
            usuario.toString();
        }
        // Al usar el método de esta manera, NO vamos a cosneguir que lo mapee. Así que utilizaremos el da arriba.
        // FindIterable<Document> iterable2 =  this.coleccionAlumnos.find();
    }

    // Método para buscar un alumno por su email
    public Alumno buscarAlumnoPorEmail(String email) {
        Document filtro = new Document("email", email);
        FindIterable<Alumno> iterable = this.coleccionAlumnos.find(filtro);
        MongoCursor<Alumno> cursor = iterable.cursor();

        while (cursor.hasNext()) {
            Alumno usuario = cursor.next();
            System.out.println(usuario.toString());
            return usuario;
        }
        return null;
    }

    public List<Alumno> buscarPorCalificacion(int calification){
        //Limpiamos la búsqueda anterior
        this.alumnosList.clear();

        // Enfoque tradicional para realizar filtros
        //Document filter = new Document("calification", calification);

        Bson filter = Filters.and(Filters.eq("calification", calification));

        FindIterable <Alumno> iterable = this.coleccionAlumnos.find(filter);
        MongoCursor <Alumno> cursor = iterable.cursor();

        while (cursor.hasNext()){
            Alumno alumno = cursor.next();
            this.alumnosList.add(alumno);
        }
        return this.alumnosList;
    }


    /**POJO Dar de baja alumnos calification > 5, el deleteMany requiere un filtro en Bson */
    public void darDeBajaAlumnos(int calification) {
        Bson filter = Filters.and(
                Filters.gt("calification", 5)
        );
        DeleteResult result = this.coleccionAlumnos.deleteMany(filter);

        // Verificar cuántos documentos fueron eliminados
        long deletedCount = result.getDeletedCount();
        System.out.println(deletedCount + " documentos fueron eliminados.");
    }

    /**POJO para deleteMany con varios filtros */
    public void darDeBajaPorNombreEdad(String name, int age) {

        // Enfoque CONCISO para CONCATENAR filtros con Filters.and(.. filtros a concatenar...)
        Bson filtrado =
                Filters.and(
                        Filters.eq("nombre", name),
                        Filters.gt("edad", age),
                        Filters.lt("edad", 90)
                );
        this.coleccionAlumnos.deleteMany(filtrado);
    }

}