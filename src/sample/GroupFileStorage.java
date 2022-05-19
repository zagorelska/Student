package sample;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Scanner;

public class GroupFileStorage {

    void saveGroupToCSV(Group gr) {

        File file = new File("D:/Обучение/Java_OOP/Groups", gr.getGroupName() + ".csv");
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (PrintWriter a = new PrintWriter(file)) {
            for (Student s : gr.getStudents()) {
                if (s != null) {
                    CSVStringConverter st = new CSVStringConverter();
                    a.println(st.toStringRepresentation(s));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("ERROR FILE WRITE");
        }
    }

    public Group loadGroupFromCSV(File file) {

        Group group = new Group();
        group.setGroupName(file.getName().substring(0, file.getName().lastIndexOf(".")));

        String text = "";

        try (Scanner sc = new Scanner(file)) {

            for (; sc.hasNextLine(); ) {
                text = sc.nextLine() + System.lineSeparator();
                CSVStringConverter csv = new CSVStringConverter();
                try {
                    group.addStudent(csv.fromStringRepresentation(text));
                } catch (GroupOverflowException e) {
                    e.printStackTrace();
                }
            }

        } catch (IOException e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        return group;
    }

    public File findFileByGroupName(String groupName, File workFolder) {
        File file = new File(workFolder, groupName + ".csv");

        if (file.exists() && file.isFile()) {
            System.out.println("File found");
            return file;
        } else {
            System.out.println("File not found");
            return null;
        }

    }

}
