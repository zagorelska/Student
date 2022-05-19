package sample;

import java.util.Scanner;

public class ScanStudent {

    public Student createNewStudent() {

        String name;
        String lastName;
        Gender gender = null;
        int id;
        String groupName;

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter name student");
        name = sc.nextLine();

        System.out.println("Enter lastName student");
        lastName = sc.nextLine();

        do {
            System.out.println("Enter gender student: female or male!");
            String gend = sc.nextLine();

            if (gend.equalsIgnoreCase("female"))
                gender = Gender.FEMALE;
            else if (gend.equalsIgnoreCase("male"))
                gender = Gender.MALE;
            else
                System.out.println("Unexpected value: " + gend);
        } while (gender == null);

        System.out.println("Enter groupName student");
        groupName = sc.nextLine();

        System.out.println("Enter id student");
        id = sc.nextInt();

        Student student = new Student(name, lastName, gender, id, groupName);

        return student;
    }

}
