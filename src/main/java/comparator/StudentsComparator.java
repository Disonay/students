package comparator;

import objects.Student;

import java.util.Comparator;

public class StudentsComparator implements Comparator<Student> {
    @Override
    public int compare(Student o1, Student o2) {
        if (o1.getSurname().compareTo(o2.getSurname()) != 0) {
            return o1.getSurname().compareTo(o2.getSurname());
        }
        else if (o1.getName().compareTo(o2.getName()) != 0) {
            return o1.getName().compareTo(o2.getName());
        }
        else if (o1.getMiddleName().compareTo(o2.getMiddleName()) != 0) {
            return o1.getMiddleName().compareTo(o2.getMiddleName());
        }

        return o1.getBirthdate().compareTo(o2.getBirthdate());
    }
}
