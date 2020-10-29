/** Author: AlbertTan
 *  Date: 2020-10-17
 */
public class CompulsoryCourse extends Course {
    int credit;

    public CompulsoryCourse (String name, int type, int id, String teacherName, int studentAmount, int credit) {
        super(id, name, type, teacherName, studentAmount);
        this.credit = credit;
    }

    public void show() {
        super.show();
        System.out.println(" Credit：" + credit);
        System.out.println();
    }

    public String toString() {
        return super.toString() + " " + this.credit;
    }
}
