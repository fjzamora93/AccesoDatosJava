package database;

import com.mongodb.MongoException;
import com.mongodb.client.*;
import model.Alumno;
import model.Profesor;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;


public class MongoDBConnection {

    // Credenciales y URL de conexión

    private String connectionString = "mongodb+srv://%s:%s@cluster0.phe4y.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0";
    private MongoClient mongoClient;
    private CodecProvider codecProvider;
    CodecRegistry codecRegistry;

    public MongoDBConnection() {

        // todos los datos que tu traduzcas son de un tipo concreto
        // INSERCIONES -> Usuario
        // INSERCIONES -> Coches
        // INSERCIONES -> Ciclos
        // SELECT -> List<Usuarios>
        // CodeProvider -> Pojo
        codecProvider = PojoCodecProvider.builder().automatic(true).build();
        codecRegistry = CodecRegistries.fromRegistries(
                MongoClients.create().getCodecRegistry(),
                CodecRegistries.fromProviders(codecProvider)
        );
        try {
            mongoClient = MongoClients.create(String.format(connectionString, DBScheme.USER, DBScheme.PASS));
            // Intenta listar las bases de datos para verificar la conexión
            mongoClient.listDatabaseNames().first();
            System.out.println("Conexión establecida con éxito a MongoDB.");
        } catch (MongoException e) {
            System.err.println("Error al conectar con MongoDB: " + e.getMessage());
        }
    }

    public void checkConnection() {
        try {
            MongoDatabase database = mongoClient.getDatabase("centro_estudios");
            MongoIterable<String> collections = database.listCollectionNames();
            System.out.println("Colecciones en la base de datos 'academia':");
            for (String collectionName : collections) {
                System.out.println("- " + collectionName);
            }
        } catch (MongoException e) {
            System.err.println("Error al listar las colecciones: " + e.getMessage());
        }
    }

    public MongoCollection getAlumnosCollecion() {
        MongoDatabase database = mongoClient.getDatabase("academia").withCodecRegistry(codecRegistry);
        return database.getCollection("usuarios", Alumno.class);
    }

    public MongoCollection getProfesoresCollection(){
        MongoDatabase database = mongoClient.getDatabase("academia").withCodecRegistry(codecRegistry);
        return database.getCollection("cursos",Profesor.class);
    }
}
