package kmitl.project.benjarat58070079.hawkeyes;

import org.junit.Test;

import kmitl.project.benjarat58070079.hawkeyes.Model.User;

import static org.junit.Assert.assertEquals;

/**
 * Created by Benny on 30/11/2560.
 */

public class SignInTest {


    @Test
    public void signInFail(){
        ModelValidation modelValidation = new ModelValidation();
        String result = modelValidation.getResult("");
        assertEquals("Sign in fail.", result);
    }



    @Test
    public void signInSuccess(){
        ModelValidation modelValidation = new ModelValidation();
        User user = new User();
        String result = modelValidation.getResult(String.valueOf(user));
        assertEquals("Sign in success.", result);
    }
}
