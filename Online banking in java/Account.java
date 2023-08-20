import java.util.Scanner;

public class Account {

    private String fName;
    private String lName;
    public String username;
    private String gender;
    private int age;
    private float money;
    private String password;
    private static int userNumber;
    private int loanAmount;

    Scanner in = new Scanner(System.in);
    Scanner ina = new Scanner(System.in);

    public Account(){
        System.out.println("Please Enter your first name:");
        fName = in.nextLine();

        System.out.println("Please Enter your last name:");
        lName = in.nextLine();

        System.out.println("Your gender:");
        gender = in.nextLine();

        System.out.println("Your age");
        age = ina.nextInt();

        money = 0;
        loanAmount = 0;
        userNumber++;
        username = fName + userNumber;

        System.out.println("Enter your password:");
        password = in.nextLine();

    }

    public void deposit(int amount){
        money += amount;
    }

    public void withdraw(int amount){
        money -= amount;
    }

    public void showInfo(){
        System.out.println("your username is **** " + username +" **** Please do not forget this!!");
        System.out.println("Your name is "+ fName+ " "+ lName);
        System.out.println("Your age: "+ age);
        System.out.println("your gender:"+ gender);

        System.out.println("Your money is "+ money);

    }

    public void showBalance(){
        System.out.println(money);
    }

    boolean checkPass(String pass){
        boolean tof = false;

        if(pass.equals(password)){
            tof = true;
        }
        else{
            tof = false;
        }

        return tof;
    }

    boolean checkMoney(int amount){
        boolean tof = false;

        if(amount >= 500 && amount <= money){
            tof = true;
        }
        else {
            tof = false;
        }

        return tof;
    }


    public void loan_has_taken(int amount){
        loanAmount += amount;
    }

    public void loan_has_returned(int amount){
        loanAmount -= amount;
    }

    public void loanInfo(){
        System.out.println("Your loan balance due is:"+loanAmount +" taka");
    }


    public float returnMoney(){
        return money;
    }

    public int return_loan_money(){
        return loanAmount;
    }

}
