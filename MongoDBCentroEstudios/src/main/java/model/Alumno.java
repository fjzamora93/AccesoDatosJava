package model;
import lombok.*;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Alumno extends Persona {
    @BsonId // Esta anotación es importante para mapear el campo _id de MongoDB a la propiedad id en el POJO
    @BsonProperty("_id")
    private ObjectId id;

    private double rating;
    private int age;
    private String name;
    private String gender;
    private String email;
    private String phone;


    private double calification;
    private String highergrade;

    private boolean FCTs;


    public Alumno(double rating, int age, String name, String gender, String email, String phone, double calification, String higherGrade) {
        this.rating = rating;
        this.age = age;
        this.name = name;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
        this.calification = calification;
        this.highergrade = higherGrade;
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
                ", higherGrade='" + highergrade + '\'' +
                '}';
    }

    @Override
    public void mostrarDetalles() {
        System.out.println(this.toString());
    }


}