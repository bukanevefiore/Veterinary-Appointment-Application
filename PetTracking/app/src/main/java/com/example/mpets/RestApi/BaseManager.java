package com.example.mpets.RestApi;

public class BaseManager {

    protected RestApi getRestApi(){
        RestApiClient restApiClient=new RestApiClient(BaseUrl.Url);
        return restApiClient.getRestApi();
    }
}
