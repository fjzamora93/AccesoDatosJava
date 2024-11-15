package model;
import lombok.*;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Alumno extends Persona {
    @BsonId  // Esta anotación es importante para mapear el campo _id de MongoDB a la propiedad id en el POJO
    private String id;  // MongoDB asigna automáticamente un valor único a _id, aquí lo mapeamos como String

    private double rating;
    private int age;
    private String name;
    private String gender;
    private String email;
    private String phone;

    // Hay un error tipográfico en la base de datos de mongo, nosotros vamos a hacer un mapeo para "ignorarlo"
    @BsonProperty("calificaation")
    private double calification;

    @BsonProperty("higher_grade")  // Mapeo explícito de "higher_grade" en MongoDB a "higherGrade" en el POJO
    private String higherGrade;

    private boolean FCTs;


    public Alumno(double rating, int age, String name, String gender, String email, String phone, double calification, String higherGrade) {
        this.rating = rating;
        this.age = age;
        this.name = name;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
        this.calification = calification;
        this.higherGrade = higherGrade;
    }

    @Override
    public String toString() {
        return "Alumno{" +
                "rating=" + rating +
                ", age=" + age +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", calification=" + calification +
                ", higherGrade='" + higherGrade + '\'' +
                '}';
    }

    @Override
    public void mostrarDetalles() {
        System.out.println(this.toString());
    }
}