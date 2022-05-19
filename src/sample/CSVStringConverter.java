package sample;

public class CSVStringConverter implements StringConverter {

    @Override
    public String toStringRepresentation(Student student) {
        String d = ";";
        String str = student.getName() + d + student.getLastName() + d + student.getGender() + d + student.getId() + d
                + student.getGroupName();

        return str;
    }

    @Override
    public Student fromStringRepresentation(String str) {

        String[] arr = str.split("[;]");
        Student student = new Student(arr[0], arr[1], Gender.valueOf(arr[2]), Integer.parseInt(arr[3]), arr[4]);

        return student;
    }

}
