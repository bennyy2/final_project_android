package kmitl.project.benjarat58070079.hawkeyes;

import kmitl.project.benjarat58070079.hawkeyes.Model.User;

/**
 * Created by Benny on 26/11/2560.
 */

class PostValidation {
    private String result = "post";


    public boolean postEmpty(String name) {

        return name.equals("");
    }

    public boolean postNull(String name){
        return name == null;
    }

//    public boolean postIsWrite(String name){
//        return !name.equals("");
//    }


    public String getResult(String name) {
        if(postEmpty(name)){
            result = "please write your post before submit.";
        }else if(postNull(name)){
            result = "please write your post before submit.";
        }else{
            result = "post";
        }
        return result;
    }


}
