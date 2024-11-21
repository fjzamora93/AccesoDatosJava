package model;
import lombok.*;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Profesor extends Persona {

    @BsonId  // Esta anotación es importante para mapear el campo _id de MongoDB a la propiedad id en el POJO
    @BsonProperty("_id")
    private ObjectId id;

    private double rating;
    private int age;
    private String name;
    private String gender;
    private String email;
    private String phone;
    private List<String> subjects;
    private String title;



    public Profesor(double rating, int age, String name, String gender, String email, String phone, List<String> subjects, String title) {
        this.rating = rating;
        this.age = age;
        this.name = name;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
        this.subjects = subjects;
        this.title = title;
    }


    @Override
    public String toString() {
        return "Profesor{" +
                "rating=" + rating +
                ", age=" + age +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", subjects=" + subjects +
                ", title='" + title + '\'' +
                '}';
    }

    @Override
    public void mostrarDetalles() {
        System.out.println(this.toString());
    }


    public void actualizarCalificacion(double nuevaCalificacion) {
        this.rating = nuevaCalificacion;
    }

}