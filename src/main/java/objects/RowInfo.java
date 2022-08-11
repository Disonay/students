package objects;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RowInfo {
    private final String name;
    private final String surname;
    private final String middleName;
    private final Date birthdate;
    private final int course;

    private final String sex;
    private final String subject;

    public RowInfo(String row) throws ParseException {
        String[] data= row.split(",");
        surname = data[0];
        name = data[1];
        middleName = data[2];
        sex = data[3];
        birthdate = new SimpleDateFormat("dd.MM.yyyy").parse(data[4]);
        course = Integer.parseInt(data[5]);
        subject = data[6];
    }

    public String getSex() {
        return sex;
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

    public String getSubject() {
        return subject;
    }

}
