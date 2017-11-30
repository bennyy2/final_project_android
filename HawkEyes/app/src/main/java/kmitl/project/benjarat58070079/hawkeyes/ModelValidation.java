package kmitl.project.benjarat58070079.hawkeyes;

/**
 * Created by Benny on 30/11/2560.
 */

class ModelValidation {

    private String result;


    public boolean userInModel(String name){
        return name.equals("");
    }


    public String getResult(String name) {
        if(userInModel(name)){
            result = "Sign in fail.";
        }else if(!userInModel(name)){
            result = "Sign in success.";
        }
        return result;
    }
}
