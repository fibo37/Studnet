package com.astro.studnet;

import android.content.Intent;
import android.util.Log;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import android.content.Context;


/**
 * Created by Fibo on 4/3/2014.
 */
public class UserFunctions {
    private JSONParser jsonParser;

    // Testing in localhost using wamp or xampp
    // use http://10.0.2.2/ to connect to your localhost ie http://localhost/

    private static final String LOGIN_URL = "http://iucommunity.info/test/login.php";

    private static final String TAG_SUCCESS = "code";
    private static final String TAG_MESSAGE = "message";

    public UserFunctions(){
        jsonParser = new JSONParser();
    }

    public JSONObject login(String username, String password){

            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("username", username));
            params.add(new BasicNameValuePair("password", password));

            Log.d("request!", "starting");
            // getting product details by making HTTP request

            JSONObject json = jsonParser.makeHttpRequest(
                    LOGIN_URL, "POST", params);

            // check your log for json response
            Log.d("Login attempt", json.toString());

            // json success tag
            return json;
    }

    public JSONObject register(String username, String password, String firstname, String lastname, String phone, String email, String male){
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("username", username));
        params.add(new BasicNameValuePair("password", password));
        params.add(new BasicNameValuePair("firstname", firstname));
        params.add(new BasicNameValuePair("lastname", lastname));
        params.add(new BasicNameValuePair("phone", phone));
        params.add(new BasicNameValuePair("email", email));
        params.add(new BasicNameValuePair("male", male));

        Log.d("request!", "starting");
        // getting product details by making HTTP request
        JSONObject json = jsonParser.makeHttpRequest(
                LOGIN_URL, "POST", params);
        return json;
    }

    public boolean isUserLoggedIn(Context context){
        DatabaseHandler db = new DatabaseHandler(context);
        int count = db.getRowCount();
        if(count > 0){
            // user logged in
            return true;
        }
        return false;
    }

    /**
     * Function to logout user
     * Reset Database
     * */
    public boolean logoutUser(Context context){
        DatabaseHandler db = new DatabaseHandler(context);
        db.resetTables();
        return true;
    }

}
