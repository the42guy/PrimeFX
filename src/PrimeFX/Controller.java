package PrimeFX;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class Controller {
    @FXML
    private int numToCheck;
    @FXML
    private TextArea num;
    @FXML
    private int rangeMin, rangeMax;
    @FXML
    private TextArea rangeStart, rangeEnd;

    @FXML
    protected void setNumToCheck() {
        String numString = num.getText();
        numString = numString.replaceAll("\\D+","");
        numToCheck = Integer.parseInt(numString);
    }

    @FXML
    protected boolean checkIntPassOne(int intToCheck) {
        boolean isPrime = false;
        if ((intToCheck <= 1) || (intToCheck % 2 == 0)) {
            isPrime = false;
        } else if ((intToCheck == 2) || (intToCheck == 3)) {
            isPrime = true;
        } else if (intToCheck > 3) {
            for(int n = 1; n < intToCheck; n++) {
                if ((intToCheck == (6 * n) + 1) || (intToCheck == (6 * n) -1)) {
                    isPrime = checkIntPassTwo(intToCheck);
                    break;
                }
            }
        }
        return isPrime;
    }

    @FXML
    private boolean checkIntPassTwo(int passTwoInt) {
        boolean isPrimePassTwo = true;
        for (int multiplier = 3; multiplier < passTwoInt/2; multiplier = multiplier + 2) {
            if (passTwoInt % multiplier == 0) {
                isPrimePassTwo = false;
            }
        }
        return isPrimePassTwo;
    }

}
