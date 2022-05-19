package sample;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

public class Group {
    private String groupName;
    private Student[] students = new Student[10];

    public Group(String groupName, Student[] students) {
        super();
        this.groupName = groupName;
        this.students = students;
    }

    public Group() {
        super();
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Student[] getStudents() {
        return students;
    }

    public void setStudents(Student[] students) {
        this.students = students;
    }

    public void addStudent(Student student) throws GroupOverflowException {

        if (equalStudentInGroup(student)) {
            System.out.println("Student " + student.getLastName() + " is already in the group " + student.getGroupName());
            return;
        }

        for (int i = 0; i < students.length; i++) {
            if (students[i] == null) {
                student.setGroupName(groupName);
                students[i] = student;
                System.out.println("Student " + student.getLastName() + " add to " + student.getGroupName());
                return;
            }
        }
        throw new GroupOverflowException("The student " + student.getLastName() + " has not been added. "
                + student.getGroupName() + " complete");
    }

    public Student searchStudentByLastName(String lastName) throws StudentNotFoundException {
        for (int i = 0; i < students.length; i++) {
            if (students[i] != null && students[i].getLastName().equalsIgnoreCase(lastName)) {
                return students[i];
            }
        }
        throw new StudentNotFoundException("Student " + lastName + " not found");

    }

    public boolean removeStudentByID(int id) {
        for (int i = 0; i < students.length; i++) {
            if (students[i] != null && students[i].getId() == id) {
                students[i] = null;
                System.out.println("Removed student with id " + id);
                return true;
            }
        }
        System.out.println("Not removed student with id " + id);
        return false;
    }

    public Student[] sortStudentsByLastName() {
        Arrays.sort(students, Comparator.nullsLast(new StudentLastnameComparator()));

        return students;
    }

    public boolean equalStudentInGroup(Student student) {
        for (int i = 0; i < students.length; i++) {
            if (students[i] != null && students[i].equals(student)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Group [groupName=" + groupName + ", students=" + Arrays.toString(students) + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.hashCode(students);
        result = prime * result + Objects.hash(groupName);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Group other = (Group) obj;
        return Objects.equals(groupName, other.groupName) && Arrays.equals(students, other.students);
    }

}
