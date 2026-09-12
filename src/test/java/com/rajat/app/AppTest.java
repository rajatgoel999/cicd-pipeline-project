package com.rajat.app;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class AppTest {
    @Test
    public void testGetGreeting() {
        App app = new App();
        assertEquals("Hello from Rajat's CI/CD pipeline!", app.getGreeting());
    }
}
