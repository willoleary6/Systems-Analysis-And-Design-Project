package backgroundServices;

import backgroundServices.API_Handlers.insertRequestHandler;
import org.json.JSONObject;


public class tester {

    public static void main(String[] args) {
        insertRequestHandler myHandler = new insertRequestHandler();
        JSONObject [] AwsResponse;
        String [] AwsKeys;

        myHandler.addNewUser("will","will@gmail.com","pass");

        AwsResponse = myHandler.getApiResponseResults();
        AwsKeys = myHandler.getApiResponseKeys();

        for(int i = 0; i < AwsResponse.length; i++){
            System.out.println(AwsResponse[i].toString());
        }



    }
}
