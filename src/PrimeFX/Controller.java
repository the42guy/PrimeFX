package PrimeFX;

import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;

import java.util.concurrent.CountDownLatch;


public class Controller {
    @FXML
    private int numToCheck;
    @FXML
    private TextField num;
    @FXML
    private int rangeMin, rangeMax;
    @FXML
    private TextField rangeStart, rangeEnd;
    @FXML
    private Label primeLabel;
    @FXML
    private ProgressIndicator progressIndicatorOne, progressIndicatorRange;

    @FXML
    private int intParser(String stringToTurnIntoInt) {
        int convertedInt;
        stringToTurnIntoInt = stringToTurnIntoInt.replaceAll("\\D+","");
        convertedInt = Integer.parseInt(stringToTurnIntoInt);
        return convertedInt;
    }

    @FXML
    protected void singleNumToCheck() {
        String numString = num.getText();
        numToCheck = intParser(numString);
        String isPrimeStr = checkIntPassOne(numToCheck) + "";
        primeLabel.setText("The number " + numToCheck + " is prime: " + isPrimeStr);
    }

    @FXML
    protected boolean checkIntPassOne(int intToCheck) {
        progressIndicatorOne.setProgress(0);
        boolean isPrime = false;
        if ((intToCheck == 2) || (intToCheck == 3)) {
            isPrime = true;
        } else if ((intToCheck <= 1) || (intToCheck % 2 == 0)) {
            isPrime = false;
        } else if (intToCheck > 3) {
            for(int n = 1; n < intToCheck; n++) {
                progressIndicatorOne.setProgress(n);
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

    @FXML
    protected void checkRange() {

    }

}
