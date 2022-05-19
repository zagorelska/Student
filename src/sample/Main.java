package sample;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        Group group1 = new Group();
        group1.setGroupName("Group 1");

        Student student1 = new Student("Ivan", "Ivanchenko", Gender.MALE, 1, group1.getGroupName());
        Student student2 = new Student("Marina", "Mishko", Gender.FEMALE, 2, group1.getGroupName());
        Student student3 = new Student("Denys", "Sorokin", Gender.MALE, 3, group1.getGroupName());
        Student student4 = new Student("Sergii", "Borov", Gender.MALE, 4, group1.getGroupName());
        Student student5 = new Student("Elena", "Marchenko", Gender.FEMALE, 5, group1.getGroupName());
        Student student6 = new Student("Iryna", "Sergeenko", Gender.FEMALE, 6, group1.getGroupName());
        Student student7 = new Student("Maria", "Marchenko", Gender.FEMALE, 2, group1.getGroupName());
        Student student8 = new Student("Dima", "Andreev", Gender.MALE, 8, group1.getGroupName());
        Student student9 = new Student("Anton", "Melenko", Gender.MALE, 9, group1.getGroupName());
        Student student10 = new Student("Andrey", "Lebed", Gender.MALE, 10, group1.getGroupName());
        Student student11 = new Student("Oleg", "Larin", Gender.MALE, 11, group1.getGroupName());

        try {
            group1.addStudent(student1);
            group1.addStudent(student2);
            group1.addStudent(student3);
            group1.addStudent(student4);
            group1.addStudent(student1);
            group1.addStudent(student5);
            group1.addStudent(student6);
            group1.addStudent(student7);
            group1.addStudent(student7);
            group1.addStudent(student8);
            group1.addStudent(student9);
            group1.addStudent(student10);
            group1.addStudent(student11);
        } catch (GroupOverflowException e) {
            System.err.println(e.getMessage());
        }

        try {
            Student search = group1.searchStudentByLastName("Melenko");
            System.out.println(search.getLastName());
        } catch (StudentNotFoundException e) {
            System.err.println(e.getMessage());
        }

        group1.removeStudentByID(12345);
        group1.removeStudentByID(4);

        group1.sortStudentsByLastName();
        System.out.println(group1);

//		ScanStudent st = new ScanStudent();
//		Student studentN = st.createNewStudent();
//
//		Group groupN = new Group();
//		groupN.setGroupName(studentN.getGroupName());
//
//		try {
//			groupN.addStudent(studentN);
//		} catch (GroupOverflowException e) {
//			System.err.println(e.getMessage());
//		}

        CSVStringConverter studentConvert = new CSVStringConverter();
        String str = studentConvert.toStringRepresentation(student1);
        System.out.println(str);

        CSVStringConverter std = new CSVStringConverter();
        System.out.println(std.fromStringRepresentation(str).toString());

        GroupFileStorage fileSt = new GroupFileStorage();
        fileSt.saveGroupToCSV(group1);

        Group csvGroup = new Group();
        File file = new File(new File("D:/Обучение/Java_OOP/Groups"), group1.getGroupName() + ".csv");
        csvGroup = fileSt.loadGroupFromCSV(file);
        System.out.println(csvGroup.toString());

        File workFolder = new File("D:/Обучение/Java_OOP/Groups");
        File file1 = fileSt.findFileByGroupName(group1.getGroupName(), workFolder);

        File workFolder2 = new File("D:/Обучение/Java_OOP");
        File file2 = fileSt.findFileByGroupName(group1.getGroupName(), workFolder2);


    }
}
