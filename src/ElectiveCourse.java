/** Author: AlbertTan
 *  Date: 2020-10-27
 */
public class ElectiveCourse extends Course {
    int maxStudentAmount;

    public ElectiveCourse (String name, int type, int id, String teacherName, int studentAmount, int maxStudentAmount) {
        super(id, name, type, teacherName, studentAmount);
        this.maxStudentAmount = maxStudentAmount;
    }

    public void show() {
        super.show();
        System.out.println(" Max student amount：" + maxStudentAmount);
        System.out.println();
    }

    public String toString() {
        return super.toString() + " " + this.maxStudentAmount;
    }
}
