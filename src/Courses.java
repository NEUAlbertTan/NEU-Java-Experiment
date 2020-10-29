import java.io.*;
import java.util.*;
/** Author: AlbertTan
 *  Date: 2020-10-27
 */
public class Courses {
    static Vector<Course> vec = new Vector<>();

    /* Author: AlbertTan
     * Function:
     * Create course_amount courses, whose information would be entered by keyboard
     * Date: 2020-10-17
     */
    public static void createCourses (int course_amount) {
        Scanner sc = new Scanner(System.in);
        for (int i=0; i<course_amount; ++i) {

            int studentAmount;
            int id;
            String name;
            int type;
            String teacherName;

            System.out.print("Name：");
            name = sc.next();
            // check whether the course exists
            boolean courseNameFound = false;
            for (var course : Courses.vec) {
                if ( name.equals(course.name) ) {
                    courseNameFound = true;
                    break;
                }
            }
            // this course already exists
            while (courseNameFound) {
                System.out.println("This course already exists");
                System.out.print("Name：");
                name = sc.next();
                // check whether the course exists
                courseNameFound = false;
                for (var course : Courses.vec) {
                    if ( name.equals(course.name) ) {
                        courseNameFound = true;
                        break;
                    }
                }
            }

            System.out.print("Teacher's name：");
            teacherName = sc.next();
            // check whether the teacher exists
            boolean foundTeacher = false;
            for (var teacher : Users.teachers) {
                if (teacher.name.equals(teacherName)) {
                    foundTeacher = true;
                    break;
                }
            }
            while (!foundTeacher) {
                System.out.println("Teacher's name not found!");
                System.out.print("Teacher's name：");
                teacherName = sc.next();
                // check whether the teacher exists
                foundTeacher = false;
                for (var teacher : Users.teachers) {
                    if (teacher.name.equals(teacherName)) {
                        foundTeacher = true;
                        break;
                    }
                }
            }

            System.out.print("Course ID: ");
            id = sc.nextInt();
            boolean foundID = false;
            for (var course : Courses.vec) {
                if (id == course.id) {
                    foundID = true;
                    break;
                }
            }
            while (foundID) {
                System.out.println("Course ID already exists!");
                System.out.print("Course ID: ");
                id = sc.nextInt();
                // check whether the id exists
                foundID = false;
                for (var course : Courses.vec) {
                    if (id == course.id) {
                        foundID = true;
                        break;
                    }
                }
            }

            System.out.print("Course type(0/1)：");
            type = sc.nextInt();

            // CompulsoryCourse
            if (type == 0) {
                System.out.print("Credit：");
                int credit = sc.nextInt();
                studentAmount = Users.students.size();
                var tempCourse = new CompulsoryCourse(name, type, id, teacherName, studentAmount, credit);
                vec.add(tempCourse);

                // add course to every student
                for (var student : Users.students) {
                    student.courseNames.add(name);
                }

                // add course to teacher
                for (var teacher : Users.teachers) {
                    if (teacherName.equals(teacher.name)) {
                        teacher.courseNames.add(name);
                        break;
                    }
                }

            } else {
                // Elective course
                System.out.print("Maximum student amount：");
                int maxStuAmount = sc.nextInt();
                while (maxStuAmount < 0) {
                    System.out.print("Invalid maximum student amount：");
                    maxStuAmount = sc.nextInt();
                }
                // no student have taken this course in the beginning
                int defaultStuAmount = 0;
                var tempCourse = new ElectiveCourse(name, type, id, teacherName, defaultStuAmount, maxStuAmount);
                vec.add(tempCourse);
            }
            System.out.println("Successfully added!");
        }
    }

    /* Author: AlbertTan
     * Function: ascending sort the array by studentAmount
     * Date: 2020-10-17
     */
    public static void sortCoursesAscending() {
        for (int i=0; i<vec.size(); ++i) {
            int k = i;
            for (int j=i+1; j<vec.size(); ++j) {
                if ( vec.elementAt(j).studentAmount < vec.elementAt(k).studentAmount ) {
                    k = j;
                }
            }
            if (k != i) {
                Collections.swap(vec,k,i);
            }
        }
    }

    /* Author: AlbertTan
     * Function: Descending sort the array by studentAmount
     * Date: 2020-10-17
     */
    public static void sortCoursesDescending() {
        for (int i=0; i<vec.size(); ++i) {
            int k = i;
            for (int j=i+1; j<vec.size(); ++j) {
                if ( vec.elementAt(j).studentAmount > vec.elementAt(k).studentAmount ) {
                    k = j;
                }
            }
            if (k != i) {
                Collections.swap(vec,k,i);
            }
        }
    }

    /* Author: AlbertTan
     * Function:
     ** Sort the array by studentAmount, asc/des would
     ** be entered by keyboard.
     * Date: 2020-10-17
     */
    public static void sortByStuAmount () {
        System.out.println("Please enter 1.Asc↑ 2.Des↓ 0.Exit");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        switch (choice) {
            case 0:
                break;
            case 1:
                sortCoursesAscending();
                break;
            case 2:
                sortCoursesDescending();
                break;
        }
    }

    /* Author: AlbertTan
     * Function: Show all courses in the vector
     * Date: 2020-10-17
     */
    public static void showAllCourses() {
        for (Course i:vec) {
            i.show();
        }
    }

    /* Author: AlbertTan
     * Function: Enter a teachers' name and find his/her courses
     * Date: 2020-10-17
     */
    public static void printTeachersCourses () {
        int count = 0;
        Scanner sc = new Scanner(System.in);
        System.out.print("Please enter the teacher's name：");
        String name = sc.next();

        for (Course i : vec) {
            if ( i.teacherName.equals(name) ) {
                i.show();
                count++;
            }
        }

        if (count == 0) {
            System.out.println("No courses found！");
        }
    }

    /* Author: AlbertTan
     * Function: print all courses and chose one to delete
     * Date: 2020-10-17
     */
    public static void deleteACourse() {
        for (int i=0; i<vec.size(); ++i){
            System.out.println("Course index:" + (i+1) );
            vec.elementAt(i).show();
        }

        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the course index to be deleted:");
        int choice = sc.nextInt();
        if (choice>=1 && choice<= vec.size()) {
            vec.remove(choice-1);
            System.out.println("Successfully deleted!");
        } else {
            System.out.println("Input error!");
        }
    }

    /* Author: AlbertTan
     * Function: print all courses and chose one's teacher to modify
     * Date: 2020-10-17
     */
    public static void setTeacherOfCourse() {
        for (int i=0; i<vec.size(); ++i){
            System.out.println("Course index" + (i+1) );
            vec.elementAt(i).show();
        }

        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the index：");
        int choice = sc.nextInt();
        if (choice>=1 && choice<= vec.size()) {

            String name;

            System.out.print("Please enter the name：");
            name = sc.next();
            boolean found = false;
            for (var i : Users.teachers) {
                if ( name.equals(i.name) ) {
                    vec.elementAt(choice-1).teacherName = name;
                    found = true;
                    System.out.println("Successfully done!");
                    break;
                }
            }
            System.out.println(found?"Successfully done!":"Teacher not found!");
        } else {
            System.out.println("Input error");
        }
    }

    /* Author: AlbertTan
     * Function: Initialize for courses in the vector lazily
     * Date: 2020-10-17
     */
    public static void iniCourses() {
        for (int i=4; i>=1; i--) {
            Course tempCourse = new Course(i,i+"",i%2, i+"", i);
            vec.add(tempCourse);
        }
    }

    public static void saveCourse() {
        String filePath = System.getProperty("user.dir");
        filePath+="\\src\\Courses.txt";
        File file = new File(filePath);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            BufferedWriter out = new BufferedWriter(new FileWriter(file.getAbsoluteFile()));
            for (Course course : Courses.vec) {
                out.write(course.toString() + "\r\n");
            }

            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readCourses() {
        try {
            String filePath = System.getProperty("user.dir");
            filePath+="\\src\\Courses.txt";
            File file = new File(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
            String data = null;
            while ((data = br.readLine()) != null) {
                String[] ps = data.split(" ");
                int id = Integer.parseInt(ps[0]);
                String name = ps[1];
                int type = Integer.parseInt(ps[2]);
                int stuNum = Integer.parseInt(ps[3]);
                String teacher = ps[4];
                if (type == 0) {
                    int credit = Integer.parseInt(ps[5]);
                    vec.add(new CompulsoryCourse(name,type,id,teacher,stuNum,credit));
                } else {
                    int maxStu = Integer.parseInt(ps[5]);
                    vec.add(new ElectiveCourse(name,type,id,teacher,stuNum,maxStu));
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showOneCourseStu () {
        System.out.println("Please enter the name of the course: ");
        Scanner sc = new Scanner(System.in);
        String courseName = sc.next();
        boolean foundCourse = false;
        for (var course : Courses.vec) {
            if (courseName.equals(course.name)) {
                foundCourse = true;
                break;
            }
        }

        if (foundCourse) {
            // found this course

            int stuCount = 0;
            for (var stu : Users.students) {
                for (var stuCourse : stu.courseNames) {
                    if (stuCourse.equals(courseName)) {
                        System.out.print(stu.name + " ");
                        stuCount++;
                        break;
                    }
                }
            }
            // no student in this course
            if (stuCount == 0) {
                System.out.println("No student in this course!");
            }
            System.out.println();
        }

    }

}
