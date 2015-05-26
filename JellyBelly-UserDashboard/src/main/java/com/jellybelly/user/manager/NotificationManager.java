package com.jellybelly.user.manager;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

@Service
public class NotificationManager {

	public void sendSmsNotification(String toNumber, String code) throws Exception {
	        HttpClient client = new DefaultHttpClient();
	        ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
	        postParameters.add(new BasicNameValuePair("From", "08030752903"));
	        postParameters.add(new BasicNameValuePair("To", toNumber));
	        postParameters.add(new BasicNameValuePair("EncodingType", "unicode"));
	        String body = "Your unique verification OTP is " + code;
	        String out = new String(body.getBytes("UTF-8"), "ISO-8859-1");
	        postParameters.add(new BasicNameValuePair("Body", out));
	        String sid = "selfemp1";
	        String token = "ba3aeb4dea7d955b72c68d3cba5d28e53d63636d";
	        String url = "https://"+sid + ":"+token+"@twilix.exotel.in/v1/Accounts/"+sid+"/Sms/send";
	        HttpPost post = new HttpPost(url);
	        try {
	            post.setEntity(new UrlEncodedFormEntity(postParameters));
	        } catch (UnsupportedEncodingException e) {
	            e.printStackTrace();
	        }
	        try {
	            HttpResponse response = client.execute(post);
	            int httpStatusCode = response.getStatusLine().getStatusCode();
	            System.out.println(httpStatusCode + " is the status code");
	            HttpEntity entity = response.getEntity();
	            System.out.println(EntityUtils.toString(entity));
	        }
	        catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
}
