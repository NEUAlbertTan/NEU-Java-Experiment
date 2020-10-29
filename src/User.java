import java.util.Scanner;
/** Author: AlbertTan
 *  Date: 2020-10-27
 */
public class User {
    String name;
    String pass;

    public User(String name, String pass) {
        this.name = name;
        this.pass = pass;
    }

    public void show() {
        System.out.println("用户名: "+name);
    }

    public void setPass() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("请输入新密码(6位): ");
            String xpass1 = sc.next();
            System.out.print("请再次输入新密码(6位): ");
            String xpass2 = sc.next();
            if ( xpass1.equals(xpass2) && xpass1.length() == 6 ) {
                this.pass = xpass1;
                break;
            }

        }
    }

    public int login() {
        System.out.print("请输入管理员密码：");
        Scanner sc = new Scanner(System.in);
        if ( sc.next().equals(this.pass) ) {
            return 1;
        } else {
            return 0;
        }
    }

    // reset current object's pass
    public void resetPass() {
        System.out.println("Please enter your current password");
        Scanner sc = new Scanner(System.in);

        String curPass = sc.next();

        if (curPass.equals(pass)) {
            System.out.println("Please enter your new password");
            String newPass = sc.next();
            // check password
            while (pass.length() < 6 || pass.length()>16) {
                System.out.println("Invalid password length!");
                System.out.print("Password：");
                pass = sc.next();
            }

            this.pass = newPass;
            System.out.println("Successfully reset!");
        } else {
            System.out.println("Wrong password!");
        }
    }
}
