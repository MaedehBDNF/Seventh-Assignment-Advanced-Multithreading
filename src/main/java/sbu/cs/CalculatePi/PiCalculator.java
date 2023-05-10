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
        pi = new BigDecimal(0); // for tests
        ExecutorService threadPool = Executors.newFixedThreadPool(8);

        final int iterations = 100000;
        for (int i = 0; i <= iterations; i++){
            TermCalculator tc = new TermCalculator(i, floatingPoint);
            threadPool.execute(tc);
        }

        threadPool.shutdown();

        try {
            threadPool.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        pi = pi.setScale(floatingPoint, RoundingMode.HALF_UP);
        return pi.toPlainString();
    }

    public static void main(String[] args) {
        // Use the main function to test the code yourself
    }
}

// Chudnovsky brothers method doesn't worked!!!

//public class PiCalculator {
//
//    public static BigDecimal piInverse = new BigDecimal(0);
//
//    public static class TermCalculator implements Runnable {
//        MathContext mc = new MathContext(1000, RoundingMode.HALF_UP);
//        int n; // number of the term
//
//        public TermCalculator (int n) {
//            this.n = n;
//        }
//
//        public void run() {
//            BigDecimal sign = new BigDecimal(1);
//            if (n % 2 == 1) {
//                sign = new BigDecimal(-1);
//            }
//
//            BigDecimal term = new BigDecimal(1);
//            term = term.multiply(sign, mc);
//            term = term.multiply(numerator, mc);
//            term = term.divide(denominator(n), mc);
//            addTerm(term, mc);
//        }
//
//
//        private BigDecimal numerator(int n){
//            BigDecimal num = new BigDecimal(12 * (13591409 + 545140134*n));
//            num = num.multiply(factorial(6*n), mc);
//            return num;
//        }
//
//        private BigDecimal denominator (int n){
//            BigDecimal den = new BigDecimal(640320);
//            den = den.pow(6*n + 3, mc);
//            den = den.sqrt(mc);
//            den = den.multiply(factorial(n).pow(3), mc);
//            den = den.multiply(factorial(3*n), mc);
//            return den;
//        }
//
//        private BigDecimal factorial(int n){
//            BigDecimal temp = new BigDecimal(1);
//            for (int i = 1; i <= n; i++) {
//                temp = temp.multiply(new BigDecimal(i), mc);
//            }
//
//            return temp;
//        }
//
//    }
//
//    public static synchronized void addTerm(BigDecimal term, MathContext mc){
//        piInverse = piInverse.add(term, mc);
//    }
//
//    public String calculate(int floatingPoint)
//    {
//        piInverse = new BigDecimal(0); // for tests;
//
//        BigDecimal pi;
//        ExecutorService threadPool = Executors.newFixedThreadPool(8);
//
//        final int iterations = 1000;
//        for (int i = 1; i <= iterations; i++){
//            TermCalculator tc = new TermCalculator(i);
//            threadPool.execute(tc);
//        }
//
//        threadPool.shutdown();
//
//        try {
//            threadPool.awaitTermination(10, TimeUnit.SECONDS);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        BigDecimal one = new BigDecimal(1);
//        pi = one.divide(piInverse, new MathContext(1000, RoundingMode.HALF_UP));
//        pi = pi.setScale(floatingPoint, RoundingMode.HALF_UP);
//        return pi.toPlainString();
//    }
//
//    public static void main(String[] args) {
//        PiCalculator piCalculator = new PiCalculator();
//        System.out.println(piCalculator.calculate(100));
//    }
//}



