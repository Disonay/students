package objects;

import java.util.Objects;

public class Subject {
    String subjectName;

    public Subject(RowInfo rowInfo) {
        subjectName = rowInfo.getSubject();
    }

    public Subject(String subjectName) {
        this.subjectName = subjectName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subject subject = (Subject) o;

        return subjectName.equals(subject.subjectName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subjectName);
    }

    @Override
    public String toString() {
        return subjectName;
    }
}

