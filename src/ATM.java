import java.util.*;

public class ATM {
    private boolean cardInserted;
    private boolean validSession;
    private int userBalance;
    private int currentBinAmount;
    private int userPIN;
    private double userCardInfo;
    private BankConnection bankConnection;

    public ATM() {
        cardInserted = false;
        validSession = false;
        userPIN = 0;
        userBalance = 0;
        currentBinAmount = getCurrentBinAmount();
        bankConnection = null;
    }

    private class BankConnection {
        private final int PIN;
        private final double cardInfo;

        public BankConnection() {
            PIN = -1;
            cardInfo = -1;
        }

        public BankConnection(int PIN, double cardInfo) {
            this.PIN = PIN;
            this.cardInfo = cardInfo;
        }

        public boolean verify() {
            if (PIN == -1 || cardInfo == -1) {
                return false;
            }
            // verifies info with bank
            return true;
        }

        public int getBalance() {
            // get account balance from bank
            if (verify()) {
                // return info from bank
                return 1;
            }
            return -1;
        }

        public boolean requestDeposit() {
            // ask for deposit from bank, return success/failure
            return true;
        }

        public boolean deposit(int deposited) {
            if (verify()) {
                return sendDeposit(PIN, cardInfo, deposited);
            } else {
                return false;
            }
        }

        private boolean sendDeposit(int PIN, double cardInfo, int deposit) {
            // actually send updated balance to bank
            if (verify()) {
                // send desposit to bank, get success/fail
                return true;
            } else {
                return false;
            }
        }

        public boolean requestWithdrawal(int userWithdraw) {
            if (verify()) {
                return verifyWithdrawal(userWithdraw);
            } else {
                return false;
            }
        }

        private boolean verifyWithdrawal(int amount) {
            // ask bank to approve withdrawal from account, return success/failure
            return true;
        }
    }


    private int getCurrentBinAmount() {
        // interface with cash bin to obtain current amount of money in ATM bin
        return 100;
    }

    private void openCashDoor() {
        // opens door for dispensing or recieving money
    }

    private void closeCashDoor() {
        // closes door for dispensing or recieving money
    }

    private void dispenseMoney(int amount) {
        // use cash bin hardward to dispense money
        currentBinAmount = currentBinAmount - amount;
    }

    private boolean getDeposit() {
        openCashDoor();
        int deposited = 0;
        // use some sort of sensor to determine amount of money added
        // add this to the current amount of money in the cash bin
        deposited = 100;
        this.currentBinAmount += deposited;
        closeCashDoor();
        return (bankConnection.deposit(deposited));
    }

    private void verifyInfo(int userPIN, double userCardInfo) {
        // connect to bank and verify given information
        bankConnection = new BankConnection(userPIN, userCardInfo);
        validSession = bankConnection.verify();
    }

    private void cardIntake() {
        // open card slot, turn on lights, etc
        cardInserted = cardSensor();
        if (cardInserted) {
            userCardInfo = readCard();
        }
    }

    private double readCard() {
        // read card that has been given
        return 0;
    }

    private boolean cardSensor() {
        // detects, waits until card is physically inserted (get info from hardware)
        // make sure to obtain card, not allow user to take it back out after inserting
        return true;
    }

    private void getPINinput() {
        // could use a Scanner or something for now
        Scanner input = new Scanner(System.in);
        System.out.println("Please input your PIN");
        userPIN = input.nextInt();
        while (userPIN < 0) {
            System.out.println("Invalid input. Please try again.");
            userPIN = input.nextInt();
        }
    }

    private boolean getWithdrawal() {
        Scanner input = new Scanner(System.in);
        System.out.println("Please input the amount you would like to withdraw");
        int userWithdraw = input.nextInt();
        while (userWithdraw < 0 || userWithdraw > currentBinAmount) {
            if (userWithdraw < 0) {
                System.out.println("Invalid input. Please try again.");
            } else {
                System.out.println("Unable to fulfill request. Please try again.");
            }
            userWithdraw = input.nextInt();
        }
        if (bankConnection.requestWithdrawal(userWithdraw)) {
            openCashDoor();
            dispenseMoney(userWithdraw);
            closeCashDoor();
        } else {
            return false;
        }
        return true;


    }

    private void getBalance() {
        int result = bankConnection.getBalance();
        if (result == -1) {
            System.out.println("Verification failed");
        } else {
            System.out.println("Printing balance");
            // print the balance on paper
        }
    }

    public static void main(String[] args) {
        // ask for card -> call cardIntake()
        // once card inserted, ask for PIN -> call getPINinput()
        // verify info with bank -> call verifyInfo
        // check validSession = true before continuing
        // ask which account
        // ask which command
        // if deposit, no need to verify -> call getDeposit()
        // if balance, ask for info from bank and print results -> call getBalance()
        // if withdraw, ask how much -> call getWithdrawal()
    }
}