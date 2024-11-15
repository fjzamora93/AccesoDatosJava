package model;
import lombok.*;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Alumno extends Persona {
    double calification;
    String higherGrade;

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