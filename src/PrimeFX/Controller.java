package PrimeFX;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.ArrayList;


public class Controller {
    @FXML
    private int numToCheck;
    @FXML
    private ArrayList<Integer> primes = new ArrayList<Integer>();
    @FXML
    private TextField num;
    @FXML
    private int rangeMin, rangeMax, greatestIntFactor = 1;
    @FXML
    private TextField rangeStart, rangeEnd;
    @FXML
    private Label primeLabel;
    @FXML
    private ProgressIndicator progressIndicatorOne;
    @FXML
    private TextArea primeList;

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
        primeLabel.setText("The number " + numToCheck + " is prime: " + isPrimeStr + "\n");
    }

    @FXML
    protected boolean checkIntPassOne(int intToCheck) {
        progressIndicatorOne.setProgress(0);
        boolean isPrime = false;
        if ((intToCheck == 2) || (intToCheck == 3) || (intToCheck == 5)) {
            isPrime = true;
            populatePrimeList(isPrime, intToCheck);
        } else if (intToCheck <= 1) {
            isPrime = false;
        } else if (intToCheck % 2 == 0){
            isPrime = false;
            //greatestIntFactor = 2;
        } else if (intToCheck > 5) {
            for(int n = 1; n < intToCheck; n++) {
                progressIndicatorOne.setProgress(n);
                if ((intToCheck == (6 * n) + 1) || (intToCheck == (6 * n) - 1)) {
                    isPrime = checkIntPassTwo(intToCheck);
                    //setGreatestIntFactor(isPrime);
                    break;
                } else {
                    //greatestIntFactor = n;
                }
            }
        }
        return isPrime;
    }

    @FXML
    private void setGreatestIntFactor(boolean isPrime) {
        if(isPrime) {
            greatestIntFactor = 1;
        }
    }

    @FXML
    private boolean checkIntPassTwo(int passTwoInt) {
        boolean isPrimePassTwo = true;
        for (int multiplier = 3; multiplier <= passTwoInt / multiplier; multiplier = multiplier + 2) {
            if (passTwoInt % multiplier == 0) {
                isPrimePassTwo = false;
                greatestIntFactor = multiplier;
                break;
            }
        }
        populatePrimeList(isPrimePassTwo, passTwoInt);
        return isPrimePassTwo;
    }

    @FXML
    protected void checkRange() {
        rangeMin = intParser(rangeStart.getText());
        rangeMax = intParser(rangeEnd.getText());
        primes.clear();
        primes = new ArrayList<Integer>(rangeMax - rangeMin);
        for(int i = rangeMin; i <= rangeMax; i++) {
            checkIntPassOne(i);
        }
        writeToPrimeList();
    }

    @FXML
    private void populatePrimeList(boolean isPrime, int aPrimeToBeChecked) {
        if(isPrime) {
            primes.add(aPrimeToBeChecked);
        }
    }

    @FXML
    private void writeToPrimeList() {
        int primeLen = primes.size();
        String primeListText = "Found " + primeLen + " primes in the given series\n";
        for(int i = 0; i < primeLen; i++) {
            primeListText += (i+1) + ": " + primes.get(i) + "\n";
        }
        primeList.setText(primeListText);
    }

}
