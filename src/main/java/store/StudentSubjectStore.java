package store;

import comparator.StudentsComparator;
import objects.RowInfo;
import objects.Student;
import objects.Subject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.*;

public class StudentSubjectStore {
    private final List<String> rows;
    private final Map<Student, List<Subject>> students;
    private final Map<Subject, List<Student>> subjects;


    public StudentSubjectStore(String filePath) throws IOException, ParseException {
        rows = new ArrayList<>(Files.readAllLines(Paths.get(filePath)));
        students = new LinkedHashMap<>();
        subjects = new LinkedHashMap<>();

        fillCollections();
    }

    private void fillCollections() throws ParseException {
        for (String row: rows) {
            RowInfo rowInfo = new RowInfo(row);
            Student student = new Student(rowInfo);
            Subject subject = new Subject(rowInfo);

            saveStudent(student, subject);
            saveSubject(student, subject);
        }
    }

    private void saveStudent(Student student, Subject subject) {
        if (!students.containsKey(student)) {
            students.put(student, new LinkedList<>());
        }

        students.get(student).add(subject);
    }

    private void saveSubject(Student student, Subject subject) {
        if (!subjects.containsKey(subject)) {
            subjects.put(subject, new LinkedList<>());
        }

        subjects.get(subject).add(student);
    }


    public String printRow(int rowNumber) {
        return rows.get(rowNumber);
    }

    public List<String> printStudents() {
        return students.keySet().stream()
                .map(Student::toString)
                .toList();
    }

    public List<String> printSortedStudents() {
        return students.keySet().stream()
                .sorted(new StudentsComparator())
                .map(Student::toString)
                .toList();
    }

    public List<String> printStudentsFromOneCourse(int courseNumber) {
        return students.keySet().stream()
                .filter(x -> x.getCourse() == courseNumber)
                .map(Student::toString)
                .toList();
    }

    public List<String> printStudentFromOneSubject(String subjectName) {
        return subjects.get(new Subject(subjectName)).stream()
                .map(Student::toString)
                .toList();
    }
}
