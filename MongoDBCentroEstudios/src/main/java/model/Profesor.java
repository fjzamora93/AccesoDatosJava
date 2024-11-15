package model;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Profesor extends Persona {
    List<String> subjects;
    String title;

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

    public void actualizarCalificacion(double nuevaCalificacion) {
        this.rating = nuevaCalificacion;
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
}