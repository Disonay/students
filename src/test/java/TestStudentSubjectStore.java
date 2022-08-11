import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import store.StudentSubjectStore;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class TestStudentSubjectStore extends Assertions {
    @TempDir
    static Path tempDir;
    static Path tempFile;

    static StudentSubjectStore studentSubjectStore;

    @BeforeAll
    public static void setUp() throws IOException, ParseException {
        tempFile = Files.createFile(tempDir.resolve("test.txt"));

        List<String> rows = new ArrayList<>();

        rows.add("Колесник,Сергей,Николаевич,М,25.10.2001,4,Алгебра");
        rows.add("Колесник,Сергей,Николаевич,М,25.10.2002,4,Алгебра");
        rows.add("Колесник,Сергей,Николаевич,М,25.10.2000,4,Алгебра");
        rows.add("Пупкин,Василий,Петрович,М,25.12.2002,4,Алгебра");
        rows.add("Пупкин,Василий,Петрович,М,25.12.2002,4,Матан");
        rows.add("Пукин,Василий,Николаевич,М,25.10.2002,4,Алгебра");
        rows.add("Пупкин,Сергей,Николаевич,М,25.10.2002,4,Алгебра");
        rows.add("Петров,Александр,Васильевич,М,24.10.2001,4,Политология");
        rows.add("Жмышенко,Валерий,Альбертович,М,25.06.2001,3,Программирование");

        Files.write(tempFile, rows);

        studentSubjectStore = new StudentSubjectStore(tempFile.toString());
    }

    @AfterAll
    public static void dropFile() throws IOException {
        Files.deleteIfExists(tempFile);
    }

    @Test
    public void testPrintRow() {
        assertEquals("Колесник,Сергей,Николаевич,М,25.10.2001,4,Алгебра",
                studentSubjectStore.printRow(0));
        assertEquals("Пупкин,Василий,Петрович,М,25.12.2002,4,Матан",
                studentSubjectStore.printRow(4));
        assertEquals("Жмышенко,Валерий,Альбертович,М,25.06.2001,3,Программирование",
                studentSubjectStore.printRow(8));
    }

    @Test
    public void testPrintStudents() {
        List<String> excepted = List.of("Колесник Сергей Николаевич 25.10.2001",
                "Колесник Сергей Николаевич 25.10.2002",
                "Колесник Сергей Николаевич 25.10.2000",
                "Пупкин Василий Петрович 25.12.2002",
                "Пукин Василий Николаевич 25.10.2002",
                "Пупкин Сергей Николаевич 25.10.2002",
                "Петров Александр Васильевич 24.10.2001",
                "Жмышенко Валерий Альбертович 25.06.2001");

        assertLinesMatch(excepted, studentSubjectStore.printStudents());
    }

    @Test
    public void testPrintSortedStudents() {
        List<String> excepted = List.of("Жмышенко Валерий Альбертович 25.06.2001",
                "Колесник Сергей Николаевич 25.10.2000",
                "Колесник Сергей Николаевич 25.10.2001",
                "Колесник Сергей Николаевич 25.10.2002",
                "Петров Александр Васильевич 24.10.2001",
                "Пукин Василий Николаевич 25.10.2002",
                "Пупкин Василий Петрович 25.12.2002",
                "Пупкин Сергей Николаевич 25.10.2002");

        assertLinesMatch(excepted, studentSubjectStore.printSortedStudents());
    }

    @Test
    public void testPrintStudentsFromOneCourse() {
        List<String> exceptedFourth = List.of("Колесник Сергей Николаевич 25.10.2001",
                "Колесник Сергей Николаевич 25.10.2002",
                "Колесник Сергей Николаевич 25.10.2000",
                "Пупкин Василий Петрович 25.12.2002",
                "Пукин Василий Николаевич 25.10.2002",
                "Пупкин Сергей Николаевич 25.10.2002",
                "Петров Александр Васильевич 24.10.2001");

        List<String> exceptedThird = List.of("Жмышенко Валерий Альбертович 25.06.2001");

        assertLinesMatch(exceptedFourth,
                studentSubjectStore.printStudentsFromOneCourse(4));
        assertLinesMatch(exceptedThird,
                studentSubjectStore.printStudentsFromOneCourse(3));
    }

    @Test
    public void testPrintStudentFromOneSubject() {
        List<String> exceptedAlgebra = List.of("Колесник Сергей Николаевич 25.10.2001",
                "Колесник Сергей Николаевич 25.10.2002",
                "Колесник Сергей Николаевич 25.10.2000",
                "Пупкин Василий Петрович 25.12.2002",
                "Пукин Василий Николаевич 25.10.2002",
                "Пупкин Сергей Николаевич 25.10.2002");

        List<String> exceptedMatan = List.of("Пупкин Василий Петрович 25.12.2002");

        assertLinesMatch(exceptedAlgebra,
                studentSubjectStore.printStudentFromOneSubject("Алгебра"));
        assertLinesMatch(exceptedMatan,
                studentSubjectStore.printStudentFromOneSubject("Матан"));
    }
}
