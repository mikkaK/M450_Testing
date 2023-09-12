package ch.tbz.junitrechner;

public class Calculator {

        public int add(int a, int b) {
            return a + b;
        }

        public int substract(int a, int b){
            return a - b;
        }

        public int multiply(int a, int b){
            return a * b;
        }

        public double divide(int a, int b){
            return (double) a / b;
        }

        public int modulo(int a, int b){
            return a % b;
        }

        public int power(int a, int b){
            return (int) Math.pow(a, b);
        }

        public int root(int a, int b){
            return (int) Math.pow(a, 1.0 / b);
        }

        public int factorial(int a){
            int result = 1;
            for (int i = 1; i <= a; i++) {
                result *= i;
            }
            return result;
        }

}
