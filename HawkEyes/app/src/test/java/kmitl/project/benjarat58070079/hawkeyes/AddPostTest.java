package kmitl.project.benjarat58070079.hawkeyes;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class AddPostTest {


    @Test
    public void postIsEmpty(){
        PostValidation postValidation = new PostValidation();
        Boolean result = postValidation.postEmpty("");
        assertEquals("Write post before submit.", result);
    }

}