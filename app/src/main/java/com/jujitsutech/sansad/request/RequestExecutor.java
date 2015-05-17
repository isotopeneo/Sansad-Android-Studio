package com.jujitsutech.sansad.request;

import android.net.ParseException;
import android.util.Base64;

import com.jujitsutech.sansad.util.LoggerClass;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;

public class RequestExecutor {

	private final static String AUTHORIZATION = "Authorization";
	private final static String BASIC = "Basic ";

	public static String makeGetRequest(String url) {
		String responseText = "";
		try {
			DefaultHttpClient httpclient = new DefaultHttpClient();
			HttpGet httpGetReq = new HttpGet(url);
			
			HttpResponse response = httpclient.execute(httpGetReq);
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

	public static String makeRequestWithEncodedParametersForBingNews(String url, String APPLICATION_KEY_OPTION) {
		String responseText = "";
		byte[] accountKeyBytes = Base64.encode((APPLICATION_KEY_OPTION + ":" + APPLICATION_KEY_OPTION).getBytes(), Base64.NO_WRAP);
		String enc_key =  new String(accountKeyBytes);
		try {
			DefaultHttpClient httpclient = new DefaultHttpClient();
			HttpGet httpGetReq = new HttpGet();
			URI uri = new URI(url);

			httpGetReq.setURI(uri);
			LoggerClass.log(BASIC + enc_key);
			httpGetReq.setHeader(AUTHORIZATION, BASIC + enc_key);

			HttpResponse response = httpclient.execute(httpGetReq);
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
