package kmitl.project.benjarat58070079.hawkeyes;

/**
 * Created by Benny on 26/11/2560.
 */

class PostValidation {
    private String result = "post";


    public boolean postEmpty(String name) {

        return name.isEmpty();
    }


    public String getResult(String name) {
        if(postEmpty(name)){
            result = "Your name is empty";
        }
        return result;
    }
}
