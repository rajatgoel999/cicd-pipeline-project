package com.rajat.app;

public class App {
    public String getGreeting() {
        return "Hello from Rajat's CI/CD pipeline!";
    }

    public static void main(String[] args) {
        System.out.println(new App().getGreeting());
    }
}
