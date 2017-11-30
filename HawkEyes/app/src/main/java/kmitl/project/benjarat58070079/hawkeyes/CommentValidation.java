package kmitl.project.benjarat58070079.hawkeyes;

/**
 * Created by Benny on 30/11/2560.
 */

class CommentValidation {
    private String result;

    public boolean commentEmpty(String name) {

        return name.equals("");
    }

    public String getResult(String name) {
        if(commentEmpty(name)){
            result = "please write comment before submit.";
        }
        return result;
    }

}
