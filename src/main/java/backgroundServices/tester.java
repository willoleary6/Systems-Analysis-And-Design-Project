package backgroundServices;

import backgroundServices.API_Handlers.insertRequestHandler;
import org.json.JSONObject;


public class tester {

    public static void main(String[] args) {
        insertRequestHandler myHandler = new insertRequestHandler();
        JSONObject [] AwsResponse;
        String [] AwsKeys;

        //myHandler.addNewUser("will4543564","will@gmail.com","pass");
        myHandler.addNewDiscount( "2","3", "2018-10-30 12:00:00","2018-11-30 12:00:00","20");
        AwsResponse = myHandler.getApiResponseResults();
        AwsKeys = myHandler.getApiResponseKeys();

        for(int i = 0; i < AwsResponse.length; i++){
            System.out.println(AwsResponse[i].toString());
        }



    }
}
