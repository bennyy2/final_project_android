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
    public void postIsEmptyFail(){
        PostValidation postValidation = new PostValidation();
        String result = postValidation.getResult("");
        assertEquals("please write your post before submit.", result);
    }

    @Test
    public void postIsEmptySuccess(){
        PostValidation postValidation = new PostValidation();
        String result = postValidation.getResult("test");
        assertEquals("post", result);
    }







//    @Test
//    public void locationIsEmpty(){
//        PostValidation postValidation = new PostValidation();
//        String result = postValidation.getResult("");
//
//    }

}