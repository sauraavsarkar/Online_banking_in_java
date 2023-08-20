import java.util.ArrayList;
import java.util.Scanner;


public class Bank {

    private ArrayList<Account> accList = new ArrayList<Account>();
    private float totalAmount;
    String bankName;
    Scanner ina = new Scanner(System.in);
    Scanner in = new Scanner(System.in);

    ///////////  Constructor  ///////////////

    public Bank(){
        bankName = "OUR Bank";
        totalAmount = 0;

    }


    /////////// Bank status ////////////

    public void bankStatus(){
        System.out.println("Bank status is "+ totalAmount);
    }



    //////////////   Add Account  ////////////////

    public void addAccount(){
        Account obj = new Account();
        accList.add(obj);

        System.out.println("Your account has been successfully created!\n\n");
        obj.showInfo();

        System.out.println("\nHow you wanna deposit?minimum 500 Tk:");

        int amount;

        while(true){
            amount = ina.nextInt();
            if(amount >= 500){
                obj.deposit(amount);
                totalAmount += amount;
                break;
            }
            else{
                System.out.println("Error!You can't deposit under 500!");
            }
        }
    }



    //////////////////   Process Account  ///////////////

    public void processAccount(){
        String username;
        String pass;


        System.out.println("Enter your username:");
        while(true){

            username = in.nextLine();
            int flag = 0;
            for(int i = 0;i < accList.size(); i++){

                if(username.equals(accList.get(i).username)){
                    flag = 1;
                    System.out.println("Enter your password:");
                    while (true){
                        pass = in.nextLine();

                        if(accList.get(i).checkPass(pass)){
                            individualAccount(accList.get(i));
                            break;
                        }

                        else{
                            System.out.println("Wrong password!Try again:");
                        }
                    }

                    break;
                }
            }

            if(flag == 1){
                break;
            }
            else{
                System.out.println("Error!try again");
            }
        }


    }



    //////////////////   Individual Account    /////////////////

    public void individualAccount(Account obj){
        int check;

        while(true){
            System.out.println("What you wanna do?" +
                    "\n1.Deposit to your Account" +
                    "\n2.Withdraw" +
                    "\n3.Send Money" +
                    "\n4.Mobile Recharge"+
                    "\n5.Take loan for your company" +
                    "\n6.Your Status"+
                    "\n7.Delete your account" +
                    "\n8.EXIT");

            check = ina.nextInt();

            if(check == 1){
                deposit(obj);
            }
            else if(check ==  2){
                withdraw(obj);
            }

            else if(check == 3){
                sendMoney(obj);
            }

            else if(check == 4){
                mobileRecharge(obj);
            }

            else if(check == 5){
                loanTake(obj);
            }

            else if(check == 6){
                obj.showInfo();
                obj.loanInfo();
            }

           else if(check == 7){
                deleteAcc(obj);
                break;
            }

            else if(check == 8){
                break;
            }
            else{
                System.out.println("Error!Try again!");
            }
        }
    }



    ////////////////////    Deposit    //////////////////////

    public void deposit(Account obj){
        int amount;
        System.out.println("How much you wanna deposit?");

        amount = ina.nextInt();
        obj.deposit(amount);
        totalAmount += amount;
        System.out.println("Deposit is successful!");
    }



    ////////////////    Withdraw    /////////////////

    public void withdraw(Account obj){
        int amount;
        boolean tof = false;

        System.out.println("How much you wanna withdraw?");

        while(true){
            amount = ina.nextInt();
            tof = obj.checkMoney(amount);

            if(tof){
                obj.withdraw(amount);
                totalAmount -= amount;
                System.out.println("Withdraw completed!");
                break;
            }

            else{
                System.out.println("you can't withdraw this amount.Your account balance is");
                obj.showBalance();
                System.out.println("Enter again:");
            }

        }
    }



    ///////////////    Send Money    /////////////////////

    public void sendMoney(Account obj){
        System.out.println("Enter another account username:");

        String username;
        int amount;
        boolean tof = false;

        while(true){
            int flag = 0;
            username = in.nextLine();

            for(int i = 0;i < accList.size(); i++){

                if(username.equals(accList.get(i).username)){

                    flag = 1;
                    System.out.println("How much you want to send?");
                    while(true){
                        amount = ina.nextInt();
                        tof = obj.checkMoney(amount);

                        if(tof){
                            obj.withdraw(amount);
                            accList.get(i).deposit(amount);
                            System.out.println("Send money successful!");
                            break;
                        }

                        else{
                            System.out.println("you can't send this amount.Your account balance is");
                            obj.showBalance();
                            System.out.println("Enter again:");
                        }
                    }
                    break;
                }
            }

            if(flag == 1){
                break;
            }
            else if(flag == 0){
                System.out.println("Wrong username!Try again:");
            }
        }
    }




    ////////////////////      Mobile Recharge      ///////////////////////

    public void mobileRecharge(Account obj){
        System.out.println("Enter your mobile Number:");
        String mobNumber;
        mobNumber = in.nextLine();

        int amount;

        boolean tof = false;

        System.out.println("How much you wanna recharge?");

        while(true){
            amount = ina.nextInt();
            tof = obj.checkMoney(amount);

            if(tof){
                obj.withdraw(amount);
                totalAmount -= amount;
                System.out.println("Mobile recharge has been successful!");
                break;
            }

            else{
                System.out.println("you can't recharge this amount.Your account balance is");
                obj.showBalance();
                System.out.println("Enter again:");
            }

        }
    }




    /////////////////////    LOAN    //////////////////

    public void loanTake(Account obj){
        int check;

        System.out.println("What do you want?\n1.Take Loan\n2.Return Loan\n3.Loan Status");

        check = ina.nextInt();

        if(check == 1){
            takeLoan(obj);
        }
        else if(check == 2){
            returnLoan(obj);
        }
        else if(check == 3){
            obj.loanInfo();
        }
        else{
            System.out.println("Error!");
        }
    }




    ///////////////   Take loan   /////////////////
        String companyName;
        String reason;
        String mobNumber;
    public void takeLoan(Account obj){
        

        System.out.println("Enter your Company name:");
        companyName = in.nextLine();

        System.out.println("Enter your reason for taking loan:");
        reason = in.nextLine();

        System.out.println("Enter your mobile:");
        mobNumber = in.nextLine();

        System.out.println("How Much you want to take loan?(max. 5000 taka!)");

        int loanAmount;
        loanAmount = ina.nextInt();

        if(loanAmount <= 5000 && loanAmount <= totalAmount){
            obj.deposit(loanAmount);
            obj.loan_has_taken(loanAmount);
            totalAmount -= loanAmount;
            System.out.println("Hola!your amount has been credited!");
        }

        else{
            System.out.println("Error! Bank has not enough money to give loan!\n");
        }
    }




    ///////////////    Return Loan    //////////////////

    public void returnLoan(Account obj){
        int amount;

        System.out.println("How much you want to return?");
        amount = ina.nextInt();

        boolean tof;

        tof = obj.checkMoney(amount);

        if(tof){
            obj.withdraw(amount);
            obj.loan_has_returned(amount);
            totalAmount += amount;
            System.out.println("Thanks for returning!\n");
        }
        else {
            System.out.println("Your balance is insufficient!Please deposit first!");
        }
    }



    //////////////////   DELETE   /////////////////

    public void deleteAcc(Account obj){
        if(obj.return_loan_money() == 0){
            for(int i = 0; i< accList.size();i++){
                if(accList.get(i) == obj){
                    totalAmount -= obj.returnMoney();
                    accList.remove(i);
                    System.out.println("Delete successful!");
                    break;
                }
            }
        }
        else {
            System.out.println("Please return your loan first!\nThanks");
        }
    }

}
