package sample;

import java.util.*;

public class Group {
    private String groupName;
    private List<Student> students = new ArrayList<>(10);

    public Group(String groupName, List<Student> students) {
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

    public List<Student> getStudents() {
        return students;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public void addStudent(Student student) throws GroupOverflowException {

        if (equalStudentInGroup(student)) {
            System.out.println("Student " + student.getLastName() + " is already in the group " + student.getGroupName());
            return;
        }
        if (students.size() < 10) {
            students.add(student);
            System.out.println("Student " + student.getLastName() + " add to " + student.getGroupName());
        } else {
            throw new GroupOverflowException("The student " + student.getLastName() + " has not been added. "
                    + student.getGroupName() + " complete");
        }

    }

    public Student searchStudentByLastName(String lastName) throws StudentNotFoundException {

        for (Student element : students) {
            if (element.getLastName().equalsIgnoreCase(lastName)) return element;
        }
        throw new StudentNotFoundException("Student " + lastName + " not found");

    }

    public boolean removeStudentByID(int id) {
        for (Student element : students) {
            if (element.getId() == id) {
                students.remove(element);
                System.out.println("Removed student with id " + id);
                return true;
            }
        }
        System.out.println("Not removed student with id " + id);
        return false;
    }

    public List<Student> sortStudentsByLastName() {
        Collections.sort(students, Comparator.nullsLast(new StudentLastnameComparator()));

        return students;
    }

    public boolean equalStudentInGroup(Student student) {
        for (Student element : students) {
            if (element.equals(student)) return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Group{" +
                "groupName='" + groupName + '\'' +
                ", students=" + students +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Group)) return false;
        Group group = (Group) o;
        return Objects.equals(getGroupName(), group.getGroupName()) && Objects.equals(getStudents(), group.getStudents());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGroupName(), getStudents());
    }
}
