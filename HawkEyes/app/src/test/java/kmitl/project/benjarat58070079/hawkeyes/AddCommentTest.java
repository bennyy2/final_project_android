package kmitl.project.benjarat58070079.hawkeyes;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Benny on 30/11/2560.
 */

public class AddCommentTest {

    @Test
    public void commentIsEmpty(){
        CommentValidation commentValidation = new CommentValidation();
        String result = commentValidation.getResult("");
        assertEquals("please write comment before submit.", result);
    }

}
