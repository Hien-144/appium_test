import org.junit.jupiter.api.Test;

import tutorial_appium.FileTest;
import tutorial_appium.Login;
import tutorial_appium.UserLogin;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.MalformedURLException;
import java.util.ArrayList;

public class TestExx {

    @Test
    public void testAddition() throws MalformedURLException, InterruptedException {
        int result = add(2, 3);
        assertEquals(5, result, "Expected the sum to be 5");
        Login lg = new Login();
        FileTest ft = new FileTest();
        ArrayList<UserLogin> userLogins = ft.Read();
        lg.Bip(userLogins);
    }
    
    

    private int add(int a, int b) {
        return a + b;
    }
}