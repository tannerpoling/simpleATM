import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class ATMtests {
    protected ATM createATM() { return new ATM(); }

    @Test
    public void cantRequestWithdrawal_invalidSession() {
        ATM atm = createATM();
        boolean test = atm.getWithdrawal(30);
        Assertions.assertTrue(!test);
    }

    @Test
    public void cantRequestDeposit_invalidSession() {
        ATM atm = createATM();
        boolean test = atm.getDeposit(100);
        Assertions.assertTrue(!test);
    }

    @Test
    public void cantRequestBalance_invalidSession() {
        ATM atm = createATM();
        boolean test = atm.getBalance();
        Assertions.assertTrue(!test);
    }

    @Test
    public void validSession_canWithdraw() {
        ATM atm = createATM();
        atm.cardIntake();
        atm.getPINinput(1111);
        atm.verifyInfo();
        boolean test = atm.getWithdrawal(30);
        Assertions.assertTrue(test);
    }

    @Test
    public void validSession_withdrawDecreasesBinAmount() {
        ATM atm = createATM();
        atm.cardIntake();
        atm.getPINinput(1111);
        atm.verifyInfo();
        boolean test = atm.getWithdrawal(30);
        Assertions.assertEquals(70, atm.getBinAmount());
    }

    @Test
    public void validSession_withdrawTooMuchFails() {
        ATM atm = createATM();
        atm.cardIntake();
        atm.getPINinput(1111);
        atm.verifyInfo();
        atm.getWithdrawal(40);
        boolean test = atm.getWithdrawal(80);
        Assertions.assertTrue(!test);
    }

    @Test
    public void giveBackCard_invalidatesSessionWithdrawal() {
        ATM atm = createATM();
        atm.cardIntake();
        atm.getPINinput(1111);
        atm.verifyInfo();
        atm.getWithdrawal(30);
        atm.giveBackCard();
        boolean test = atm.getWithdrawal(80);
        Assertions.assertTrue(!test);
    }

    @Test
    public void depositIncreasesBinAmount() {
        ATM atm = createATM();
        atm.cardIntake();
        atm.getPINinput(1111);
        atm.verifyInfo();
        atm.getDeposit(150);
        Assertions.assertEquals(250, atm.getBinAmount());
    }

    @Test
    public void cantRequestWithdrawal_badPIN() {
        ATM atm = createATM();
        atm.cardIntake();
        atm.getPINinput(-22222222);
        boolean test = atm.getWithdrawal(30);
        Assertions.assertTrue(!test);
    }

}