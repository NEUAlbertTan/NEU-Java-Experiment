import java.util.Scanner;
import java.util.Vector;
/** Author: AlbertTan
 *  Date: 2020-10-27
 */
public class Teacher extends User {
    int workId;     //工号
    String level;
    Vector<String> courseNames;

    public void show() {
        System.out.println("workId: " + workId + " Name: " + name);
    }

    public Teacher(String name, String pass, int workId, String level) {
        super(name, pass);
        this.workId = workId;
        this.level = level;
        courseNames = new Vector<>();
    }

    public int login() {
        System.out.print("Work ID：");
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
        if ( id == this.workId && pass.equals(this.pass) ) {
            System.out.println("Greetings! " + level + name);
            return 1;
        } else {
            return 0;
        }
    }



    public void showCourses () {
        for(var i : courseNames) {
            System.out.print(i + " ");
            System.out.println();
        }
    }

    public String toString() {
        StringBuilder s = new StringBuilder(name + " " + pass + " " + workId + " " + level + " ");
        for (var courseName : courseNames) {
            s.append(courseName);
            s.append(" ");
        }
        return s.toString();
    }
}
