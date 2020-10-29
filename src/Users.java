import java.io.*;
import java.util.Scanner;
import java.util.Vector;
/** Author: AlbertTan
 *  Date: 2020-10-27
 */
public class Users {
    static User admin = new User("admin","123456");
    static Vector<Teacher> teachers = new Vector<>();
    static Vector<Student> students = new Vector<>();



    static public void showAllStu(){
        for (Student i : students) {
            i.show();
        }
    }

    static public void showAllTeachers(){
        for (Teacher i : teachers) {
            i.show();
        }
    }

    // save all teachers' information in Teachers.txt
    static public void saveTeachers() {
        String filePath = System.getProperty("user.dir");
        filePath+="\\src\\Teachers.txt";
        File file = new File(filePath);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            BufferedWriter out = new BufferedWriter(new FileWriter(file.getAbsoluteFile()));
            for (var teacher : teachers) {
                out.write(teacher.toString() + "\r\n");
            }

            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // save all students' information in Students.txt
    static public void saveStudents() {
        String filePath = System.getProperty("user.dir");
        filePath+="\\src\\Students.txt";
        File file = new File(filePath);

        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            BufferedWriter out = new BufferedWriter(new FileWriter(file.getAbsoluteFile()));
            for (var student : students) {
                out.write(student.toString() + "\r\n");
            }

            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // read all teachers' information from Teachers.txt
    static public void readTeachers() {
        try {
            String filePath = System.getProperty("user.dir");
            filePath+="\\src\\Teachers.txt";
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
            String data = null;
            while ((data = br.readLine()) != null) {
                String[] ps = data.split(" ");
                String name = ps[0];
                String pass = ps[1];
                int workId = Integer.parseInt(ps[2]);
                String level = ps[3];
                var curTeacher = new Teacher(name,pass,workId,level);
                for (int i=4; i<ps.length; i++) {
                    curTeacher.courseNames.add(ps[i]);
                }
                teachers.add(curTeacher);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // read all students' information from Students.txt
    static public void readStudents() {
        try {
            String filePath = System.getProperty("user.dir");
            filePath+="\\src\\Students.txt";
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
            String data = null;
            while ((data = br.readLine()) != null) {
                String[] ps = data.split(" ");
                String name = ps[0];
                String pass = ps[1];
                int id = Integer.parseInt(ps[2]);
                String Class = ps[3];
                var curStudent = new Student(name,pass,id,Class);
                for (int i=4; i<ps.length; i++) {
                    curStudent.courseNames.add(ps[i]);
                }
                students.add(curStudent);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void teacherLogin() {
        for (var i : teachers) {
            if( i.login() == 1 ) {

            }
        }
    }

    // initialize a user's password by his id/workid
    public static void iniUserPassword() {
        System.out.println("Please enter the ID/workID: ");
        try {
            Scanner sc = new Scanner(System.in);
            int id = sc.nextInt();
            boolean teacherFound = false;
            for (var user : Users.teachers) {
                if (id == user.workId) {
                    user.pass = "123456";
                    System.out.println("Initialized " + user.level + " " + user.name + "'s password");
                    teacherFound = true;
                    break;
                }
            }
            if (!teacherFound) {
                for (var user : Users.students) {
                    if (id == user.id) {
                        user.pass = "123456";
                        System.out.println("Initialized " + user.name + "'s password");
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void addTeacher() {
        Scanner sc = new Scanner(System.in);

        String name;
        String pass;
        int workId;
        String level;
        try {
            System.out.print("Name：");
            name = sc.next();
            boolean teacherFound = false;
            for (var teacher : Users.teachers) {
                if (teacher.name.equals(name)){
                    teacherFound = true;
                    break;
                }
            }
            while (teacherFound) {
                System.out.println("This teacher already exist!");
                System.out.print("Name：");
                name = sc.next();
                teacherFound = false;
                for (var teacher : Users.teachers) {
                    if (teacher.name.equals(name)){
                        teacherFound = true;
                        break;
                    }
                }
            }
            System.out.print("Password：");
            pass = sc.next();
            // check password
            while (pass.length() < 6 || pass.length()>16) {
                System.out.println("Invalid password length!");
                System.out.print("Password：");
                pass = sc.next();
            }
            System.out.print("WorkID: ");
            workId = sc.nextInt();
            boolean idFound = false;
            for (var teacher : Users.teachers) {
                if (teacher.workId == workId){
                    idFound = true;
                    break;
                }
            }
            if (!idFound) {
                // search in students
                for (var student : Users.students) {
                    if (student.id == workId) {
                        idFound = true;
                        break;
                    }
                }
            }
            while (idFound) {
                System.out.println("This id already exist!");
                System.out.print("workId: ");
                workId = sc.nextInt();
                idFound = false;
                for (var teacher : Users.teachers) {
                    if (teacher.workId == workId){
                        idFound = true;
                        break;
                    }
                }
                if (!idFound){
                    // search in students
                    for (var student : Users.students) {
                        if (student.id == workId){
                            idFound = true;
                            break;
                        }
                    }
                }
            }

            System.out.print("Level: ");
            level = sc.next();
            Teacher t = new Teacher(name,pass,workId,level);
            Users.teachers.add(t);
            System.out.println("Successfully added!");
        } catch (Exception e) {
            e.printStackTrace();
        }
/*
        System.out.print("Enter courses: (1/0):");
        int yes = sc.nextInt();

        Vector<String> courses = new Vector<>();
        while (yes == 1) {
            boolean foundCourse = false;
            System.out.println("Course name: ");
            String courseName = sc.next();

            // search whether the course is in the list
            for (var i : Courses.vec) {
                if ( courseName.equals(i.name) ) {
                    foundCourse = true;
                    break;
                }
            }
            // if found this course
            if (foundCourse) {
                courses.add(courseName);
            } else {
                System.out.println("This course not found!");
            }

            // enter again or not
            System.out.print("Enter courses: (1/0):");
            yes = sc.nextInt();
        }
        t.courseNames = courses;
        */

    }

    public static void removeTeacher() {
        Scanner sc = new Scanner(System.in);

        String name;
        int workId;

        try {
            System.out.print("Teacher's name to be removed：");
            name = sc.next();
            System.out.print("The teacher's workID: ");
            workId = sc.nextInt();

            boolean foundTeacher = false;
            for (var teacher : Users.teachers) {
                if (teacher.name.equals(name) && teacher.workId == workId) {
                    foundTeacher = true;

                    // this teacher teaches some courses
                    if ( !teacher.courseNames.isEmpty() ) {
                        System.out.println("This teacher's courses will be deleted as well: ");
                        for (var j : teacher.courseNames) {
                            System.out.print(j + " ");
                        }

                        for (var teacherCourse : teacher.courseNames) {
                            Courses.vec.removeIf(course -> teacherCourse.equals(course.name));
                        }
                        System.out.println();
                        System.out.println("Continue?(0/1)");
                        int yes = sc.nextInt();

                        // delete all relative courses
                        if (yes == 1) {

                            // delete all student's records of this course
                            for (var student : Users.students) {
                                for (var teacherCourse : teacher.courseNames) {
                                    student.courseNames.removeIf(studentCourse -> studentCourse.equals(teacherCourse));
                                }
                            }

                            // remove all this teacher's courses
                            Courses.vec.removeIf(j -> teacher.name.equals(j.teacherName));

                            // remove this teacher
                            Users.teachers.remove(teacher);
                            System.out.println("All deleted!");
                        } else {
                            break;
                        }
                    } else {
                        // this teacher don't have any courses
                        // remove this teacher
                        Users.teachers.remove(teacher);
                        System.out.println("All deleted!");
                    }
                    break;
                }
            }
            if (!foundTeacher) {
                System.out.println("No matching teacher!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addStudent() {
        Scanner sc = new Scanner(System.in);

        String name;
        String pass;
        int id;
        String Class;
        try {
            System.out.print("Name：");
            name = sc.next();
            boolean studentFound = false;
            for (var student : Users.students) {
                if (student.name.equals(name)){
                    studentFound = true;
                    break;
                }
            }
            while (studentFound) {
                System.out.println("This student already exist!");
                System.out.print("Name：");
                name = sc.next();
                studentFound = false;
                for (var student : Users.students) {
                    if (student.name.equals(name)){
                        studentFound = true;
                        break;
                    }
                }
            }
            System.out.print("Password：");
            pass = sc.next();
            // check password
            while (pass.length() < 6 || pass.length()>16) {
                System.out.println("Invalid password length!");
                System.out.print("Password：");
                pass = sc.next();
            }
            System.out.print("ID: ");
            id = sc.nextInt();
            boolean idFound = false;
            for (var student : Users.students) {
                if (student.id == id){
                    idFound = true;
                    break;
                }
            }
            if (!idFound) {
                // search in teachers
                for (var teacher : Users.teachers) {
                    if (teacher.workId == id) {
                        idFound = true;
                        break;
                    }
                }
            }

            // there is a same id
            while (idFound) {
                System.out.println("This id already exist!");
                System.out.print("ID");
                id = sc.nextInt();
                idFound = false;
                for (var student : Users.students) {
                    if (student.id == id){
                        idFound = true;
                        break;
                    }
                }
                if (!idFound) {
                    // search in teachers
                    for (var teacher : Users.teachers) {
                        if (teacher.workId == id) {
                            idFound = true;
                            break;
                        }
                    }
                }
            }
            System.out.print("Class: ");
            Class = sc.next();
            var stu = new Student(name, pass, id, Class);
            Users.students.add(stu);
            System.out.println("Successfully added!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void removeStudent() {
        Scanner sc = new Scanner(System.in);

        String name;
        int id;

        try{
            System.out.print("The name of the student to be deleted：");
            name = sc.next();
            System.out.print("The student's ID: ");
            id = sc.nextInt();

            boolean found = false;
            for (var student : Users.students) {
                if (student.name.equals(name) && student.id == id) {
                    found = true;

                    // change courses infomation
                    for (var stuCourse : student.courseNames) {
                        for (var course : Courses.vec) {
                            if (stuCourse.equals(course.name)) {
                                course.studentAmount--;
                            }
                        }
                    }
                    Users.students.remove(student);
                    System.out.println("Successfully deleted!");
                    break;
                }
            }
            if (!found) {
                System.out.println("No matching student found!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void addAndRemoveUser() {
        System.out.println("1.Add a teacher 2.Remove a teacher");
        System.out.println("3.Add a student 4.Remove a student");
        System.out.println("0.Exit");
        Scanner sc = new Scanner(System.in);

        System.out.print("Your choice: ");
        int choice = sc.nextInt();

        switch (choice) {
            case 1 -> addTeacher();
            case 2 -> removeTeacher();
            case 3 -> addStudent();
            case 4 -> removeStudent();
        }
    }
}
