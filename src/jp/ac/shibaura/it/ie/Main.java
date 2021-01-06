package jp.ac.shibaura.it.ie;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        ApiTest apiTest = new ApiTest();
        apiTest.start();
    }
}
