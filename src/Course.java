/** Author: AlbertTan
 *  Date: 2020-10-17
 */
public class Course {
    //Teacher teacher;
    int studentAmount;
    int id;
    String name;
    int type;
    String teacherName;
    /*
    public Course (String name, String pass, int workId, String level, int studentAmount ) {
        teacher = new Teacher(name, pass, workId, level);
        this.studentAmount = studentAmount;
    }

    public void show() {
        this.teacher.show();
        System.out.println("学生总数：" + studentAmount);
    }
*/
    public Course (int _id, String _name, int _type, String _teacherName, int _stuAmount) {
        this.teacherName = _teacherName;
        this.studentAmount = _stuAmount;
        this.type = _type;
        this.name = _name;
        this.id = _id;
    }

    public void show () {
        System.out.println("Name: " + this.name + " Teacher's name: " + this.teacherName);
        System.out.print("Id: " + this.id + " Type: " + this.type + " Student amount: " + this.studentAmount);
    }

    public String toString() {
        return id + " " + name + " " + type + " " + studentAmount + " " + teacherName;
    }

}
