package backgroundServices.API_Handlers;

import org.json.JSONObject;

/* File name : Animal.java */
interface ApiRequestHandler {
    JSONObject[] getApiResponseResults();
    String [] getApiResponseKeys();
}