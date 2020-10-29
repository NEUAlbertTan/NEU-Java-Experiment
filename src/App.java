import java.util.*;

/** Author: AlbertTan
 *  Date: 2020-10-27
 */
public class App {

    public static void adminLogin() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter Admin's password: ");
        String pass = sc.next();
        // check password
        while (pass.length() < 6 || pass.length()>16) {
            System.out.println("Invalid password length!");
            System.out.print("Password：");
            pass = sc.next();
        }
        if ( pass.equals(Users.admin.pass) ) {
            while (true) {
                System.out.println("-----------------Admin----------------");
                System.out.println("1.Create a course   2.Remove a course");
                System.out.println("3.Appoint a teacher 4.Find a teacher's courses");
                System.out.println("5.Show all courses  6.Sort all courses");
                System.out.println("7.Show all students 8.Show all teachers");
                System.out.println("9.Recover password  10.Users management");
                System.out.println("0.Exit");
                System.out.print("Your choice: ");
                try {
                    int choice = sc.nextInt();
                    if (choice == 0) {
                        break;
                    }
                    while (choice < 1 || choice > 10) {
                        System.out.println("Input error!");
                        System.out.print("Your choice: ");
                        choice = sc.nextInt();
                    }
                    switch (choice) {
                        case 1 -> Courses.createCourses(1);
                        case 2 -> Courses.deleteACourse();
                        case 3 -> Courses.setTeacherOfCourse();
                        case 4 -> Courses.printTeachersCourses();
                        case 5 -> Courses.showAllCourses();
                        case 6 -> Courses.sortByStuAmount();
                        case 7 -> Users.showAllStu();
                        case 8 -> Users.showAllTeachers();
                        case 9 -> Users.iniUserPassword();
                        case 10 ->Users.addAndRemoveUser();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    adminLogin();
                }
            }
        } else {
            System.out.println("Wrong password!");
        }
    }

    public static void teacherMenu(int teacherIndex){
        Scanner sc = new Scanner(System.in);
        //Login successfully
        var curTeacher = Users.teachers.get(teacherIndex);
        System.out.println("---Welcome! " + curTeacher.level + " " + curTeacher.name + "---");
        while (true) {
            System.out.println("---------------Teacher---------------");
            System.out.println("1.Reset password   2.Check my course");
            System.out.println("3.Check one course's all students");
            System.out.println("0.Exit");
            System.out.print("Your choice: ");
            int choice = sc.nextInt();
            if (choice == 0) {
                break;
            } else if (choice < 1 || choice > 3) {
                System.out.println("Input error!");
            }
            switch (choice) {
                case 1 -> curTeacher.resetPass();
                case 2 -> curTeacher.showCourses();
                case 3 -> Courses.showOneCourseStu();
            }
        }
    }
    public static void teacherLogin() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Please enter your workID: ");
        try {
            int workId = sc.nextInt();
            // check workID
            while (workId < 0) {
                System.out.println("Invalid workID!");
                System.out.println("Please enter your workID: ");
                workId = sc.nextInt();
            }

            System.out.println("Please enter your password: ");
            String pass = sc.next();
            // check password
            while (pass.length() < 6 || pass.length()>16) {
                System.out.println("Invalid password length!");
                System.out.print("Password：");
                pass = sc.next();
            }

            int teacherIndex = -1;
            for (int i=0; i < Users.teachers.size(); i++) {
                if (Users.teachers.get(i).workId == workId && pass.equals(Users.teachers.get(i).pass)) {
                    teacherIndex = i;
                    break;
                }
            }
            // No matching teacher, return to the menu
            if (teacherIndex == -1) {
                System.out.println("ID or password wrong!");
                usersMenu();
            }
            // login successfully
            teacherMenu(teacherIndex);

        }catch (Exception e) {
            e.printStackTrace();
            usersMenu();
        }

    }


    public static void studentMenu(int studentIndex) {
        //Login successfully
        Scanner sc = new Scanner(System.in);var curStudent = Users.students.get(studentIndex);
        String s = "discrete";
        System.out.println("-----------Welcome! "+ curStudent.name + "-------------");
        while (true) {
            System.out.println("--------------Student----------------");
            System.out.println("1.Reset password   2.Check my course");
            System.out.println("3.Elect a course   0.Exit");
            System.out.print("Your choice: ");
            int choice = sc.nextInt();
            if (choice == 0) {
                break;
            } else if (choice < 1 || choice > 3) {
                System.out.println("Input error!");
            }
            switch (choice) {
                case 1 -> curStudent.resetPass();
                case 2 -> curStudent.showCourses();
                case 3 -> curStudent.electACourse();
            }
        }
    }

    public static void studentLogin() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Please enter your ID: ");
        try {
            int ID = sc.nextInt();
            // check ID
            while (ID < 0) {
                System.out.println("Invalid ID!");
                System.out.println("Please enter your ID: ");
                ID = sc.nextInt();
            }

            System.out.println("Please enter your password: ");
            String pass = sc.next();
            // check password
            while (pass.length() < 6 || pass.length()>16) {
                System.out.println("Invalid password length!");
                System.out.print("Password：");
                pass = sc.next();
            }

            int studentIndex = -1;
            for (int i=0; i < Users.students.size(); i++) {
                if (Users.students.get(i).id == ID && pass.equals(Users.students.get(i).pass)) {
                    studentIndex = i;
                    break;
                }
            }
            // No matching teacher, return to the menu
            if (studentIndex == -1) {
                System.out.println("ID or password wrong!");
                usersMenu();
            }

            // login successfully
            studentMenu(studentIndex);

        } catch (Exception e) {
            e.printStackTrace();
            usersMenu();
        }

    }

    public static void usersMenu(){
        Scanner sc = new Scanner(System.in);

        System.out.println("------Courses System------");
        System.out.println("--NEU-CSE-1804-AlbertTan--");
        System.out.println();
        System.out.println("-----------Login----------");

        System.out.println("User: 1.Admin 2.Teacher 3.Student 0.Exit");
        System.out.print("Your choice: ");
        try {
            int choice = sc.nextInt();
            if (choice == 0) {
                return;
            }
            while (choice < 0 || choice > 3) {
                System.out.println("Invalid input!");
                System.out.print("Your choice: ");
                choice = sc.nextInt();
            }
            switch (choice) {
                case 1 -> adminLogin();
                case 2 -> teacherLogin();
                case 3 -> studentLogin();
            }
        } catch (Exception e) {
            e.printStackTrace();
            usersMenu();
        }

    }

    // read all data
    public static void readData() {
        Users.readStudents();
        Users.readTeachers();
        Courses.readCourses();
    }

    // save all data
    public static void saveData() {
        Users.saveTeachers();
        Users.saveStudents();
        Courses.saveCourse();
    }

    public static void main(String[] args) {

        readData();

        usersMenu();

        saveData();

    }

}

