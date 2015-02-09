package com.jujitsutech.sansad.request;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.net.ParseException;

import com.jujitsutech.sansad.util.LoggerClass;

public class RequestExecutor {
	
	public static String makeGetRequest(String url) {
		String responseText = "";
		try {
			DefaultHttpClient httpclient = new DefaultHttpClient();
			HttpGet httpgetreq = new HttpGet(url);
			
			HttpResponse response = httpclient.execute(httpgetreq);
			responseText = EntityUtils.toString(response.getEntity());
			LoggerClass.log("**********************************");
			LoggerClass.log(responseText);
			LoggerClass.log("**********************************");
		} catch (UnsupportedEncodingException e) {
			LoggerClass.log(e.getMessage());
		} catch (ClientProtocolException e) {
			LoggerClass.log(e.getMessage());
		} catch (IOException e) {
			LoggerClass.log(e.getMessage());
		} catch (ParseException e) {
			LoggerClass.log(e.getMessage());
		} catch (Exception e) {
			LoggerClass.log(e.getMessage());
		}
		return responseText;
	}
}
