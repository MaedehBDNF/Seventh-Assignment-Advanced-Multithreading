package sbu.cs.CalculatePi;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class PiCalculator {

    /**
     * Calculate pi and represent it as a BigDecimal object with the given floating point number (digits after . )
     * There are several algorithms designed for calculating pi, it's up to you to decide which one to implement.
     Experiment with different algorithms to find accurate results.

     * You must design a multithreaded program to calculate pi. Creating a thread pool is recommended.
     * Create as many classes and threads as you need.
     * Your code must pass all of the test cases provided in the test folder.

     * @param floatingPoint the exact number of digits after the floating point
     * @return pi in string format (the string representation of the BigDecimal object)
     */

    public static BigDecimal pi = new BigDecimal(0);

    public static class TermCalculator implements Runnable {
        MathContext mc = new MathContext(1000);
        BigDecimal numerator = new BigDecimal(4);
        int floatingPoint;
        int n; // number of the term

        public TermCalculator (int n, int floatingPoint) {
            this.n = n;
            this.floatingPoint = floatingPoint;
        }

        public void run(){
            BigDecimal sign = new BigDecimal(1);
            if (n%2 == 1) {
                sign = new BigDecimal(-1);
            }

            BigDecimal term = new BigDecimal(1);
            term = term.multiply(sign, mc);
            term = term.multiply(numerator, mc);
            term = term.divide(denominator(n), mc);
            addTerm(term, mc);
        }

        private BigDecimal denominator (int n){
            BigDecimal den = new BigDecimal(Integer.toString(2*n + 1));
            return den;
        }
    }

    public static synchronized void addTerm(BigDecimal term, MathContext mc){
        pi = pi.add(term, mc);
    }

    public String calculate(int floatingPoint)
    {
        // TODO
        return null;
    }

    public static void main(String[] args) {
        // Use the main function to test the code yourself
    }
}
