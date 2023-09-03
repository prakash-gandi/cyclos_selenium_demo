package com.accenture.rest.cyclos;

import com.accenture.utils.ConfigReader;
import com.accenture.utils.RestUtils;
import io.restassured.response.Response;

import java.util.Properties;

public class AccountsApi {
    RestUtils restUtils = new RestUtils();
    Properties prop = new Properties();
    ConfigReader configReader = new ConfigReader();

    public Response getAccountDetails(String userName, String password) {
        prop = configReader.init_prop();
        String URI = prop.getProperty("CYCLOS_REST_URL")+"/"+userName+"/accounts";
        return restUtils.getResponsePayload(userName, password, URI);
    }

}
