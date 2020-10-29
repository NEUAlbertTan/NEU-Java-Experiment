import java.util.Scanner;
import java.util.Vector;
/** Author: AlbertTan
 *  Date: 2020-10-27
 */
public class Student extends User {
    int id;
    String Class;
    Vector<String> courseNames;


    public Student(String name, String pass, int id, String Class) {
        super(name,pass);
        this.id = id;
        this.Class = Class;
        courseNames = new Vector<>();
    }

    public void show() {
        System.out.print(id + " " + name + " " + Class);
        for (var courseName : this.courseNames) {
            System.out.print(courseName + " ");
        }
        System.out.println();
    }

    public int login() {
        System.out.print("Student ID：");
        Scanner sc = new Scanner(System.in);
        int id = sc.nextInt();
        System.out.print("Password：");
        String pass = sc.next();
        // check password
        while (pass.length() < 6 || pass.length()>16) {
            System.out.println("Invalid password length!");
            System.out.print("Password：");
            pass = sc.next();
        }
        if ( id == this.id && pass.equals(this.pass) ) {
            System.out.println(Class + " " + name + "你好！");
            return 1;
        } else {
            return 0;
        }
    }


    public String toString() {
        StringBuilder s = new StringBuilder(name + " " + pass + " " + id + " " + Class + " ");
        for (var courseName : courseNames) {
            s.append(courseName);
            s.append(" ");
        }
        return s.toString();
    }

    public void showCourses() {
        for (var i : courseNames) {
            System.out.print(i + " ");
            System.out.println();
        }
    }

    public void electACourse() {
        Scanner sc = new Scanner(System.in);

        for (var course : Courses.vec) {
            if (course.type == 1) {
                // an elective course
                course.show();
            }
        }
        System.out.println("Please enter name of the course you want to take");
        String courseName = sc.next();

        // find whether the student has already taken this course
        for (var stuCourse : courseNames) {
            if (stuCourse.equals(courseName)) {
                System.out.println("You've already taken this course!");
                return;
            }
        }
        boolean found = false;
        for (var course : Courses.vec) {
            if (course.name.equals(courseName)) {
                found = true;
                if (((ElectiveCourse) course).maxStudentAmount == course.studentAmount) {
                    System.out.println("Student amount limit!");
                } else {
                    courseNames.add(courseName);
                    course.studentAmount++;
                    System.out.println("Successfully done!");
                }
                break;
            }
        }
        if (!found) {
            System.out.println("No matching course!");
        }
    }
}
