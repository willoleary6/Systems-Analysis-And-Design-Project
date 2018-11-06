/*package backgroundServices;

import backgroundServices.API_Handler.getRequestHandler;
import org.json.JSONObject;


public class tester {

    public static void main(String[] args) {
        getRequestHandler myHandler = new getRequestHandler();
        myHandler.getAllAirports();
        JSONObject [] AwsResponse = myHandler.getApiResponseResults();
        String [] AwsKeys = myHandler.getApiResponseKeys();

        myHandler.getUserInformation("WilliamOLeary", "pass");

        AwsResponse = myHandler.getApiResponseResults();
        AwsKeys = myHandler.getApiResponseKeys();

        for(int i = 0; i < AwsResponse.length; i++){
            System.out.println(AwsResponse[i].toString());
        }



    }
}
*/