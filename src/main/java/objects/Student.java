package objects;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Student {
    private final String name;
    private final String surname;
    private final String middleName;
    private final Date birthdate;
    private final int course;

    public Student(RowInfo rowInfo) {
        name = rowInfo.getName();
        surname = rowInfo.getSurname();
        middleName = rowInfo.getMiddleName();
        birthdate = rowInfo.getBirthdate();
        course = rowInfo.getCourse();
    }


    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getMiddleName() {
        return middleName;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public int getCourse() {
        return course;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        return name.equals(student.name) &&
                surname.equals(student.surname) &&
                middleName.equals(student.middleName) &&
                birthdate.equals(student.birthdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, middleName, birthdate);
    }

    @Override
    public String toString() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        return surname + " " + name + " " + middleName + " " + simpleDateFormat.format(birthdate);
    }
}
