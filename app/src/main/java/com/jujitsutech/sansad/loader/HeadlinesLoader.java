package com.jujitsutech.sansad.loader;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.os.Bundle;

import com.jujitsutech.sansad.R;
import com.jujitsutech.sansad.bean.Headlines;
import com.jujitsutech.sansad.contenthandler.HeadlinesHandler;
import com.jujitsutech.sansad.request.RequestExecutor;
import com.jujitsutech.sansad.util.Singleton;

import java.util.List;


/**
 * Created by Sujit Devkar on 24-02-2015.
 *
 *     // Bing API for news
 // Details at the following location
 // https://onedrive.live.com/view.aspx?resid=9C9479871FBFA822!109&app=Word&authkey=!ACvyZ_MNtngQyCU
 */
public class HeadlinesLoader extends AsyncTaskLoader<List<Headlines>> {

    List<Headlines> headline;
    private String BING_API_KEY;

    private final String BING_NEWS_URL_INCLUDING_QUERY = "https://api.datamarket.azure.com/Bing/Search/News?Query=%27parliament%27&Market=%27en-IN%27&NewsSortBy=%27Date%27&$top=30&$format=Json";

    public HeadlinesLoader(Context context, Bundle bundle) {
        super(context);
        this.BING_API_KEY = context.getString(R.string.bing_api_key);
    }

    @Override
    public List<Headlines> loadInBackground() {

        return makeBingNewsRequest();

        // Commenting out the new york times data
        /*
        String searchLocationFormat = "The New York Times";
        String location = "India";
        //String beginDate = "20150206";
        //String endDate = "20150228";
        String endDate = new SimpleDateFormat("yyyyMMdd").format(new Date());

        String sortType = "newest";
        String apiKey = "0423a6eaf9282f883afe2d14f610d013:14:71192101";

        String BASE_URL = "http://api.nytimes.com/svc/search/v2/articlesearch.json?";
        String QUERY_PARAM = "q";
        String SEARCH_LOCATION_PARAM = "fq";
        //String BEGIN_DATE_PARAM="begin_date";
        String END_DATE_PARAM = "end_date";
        String SORT_PARAM = "sort";
        String API_KEY_PARAM = "api-key";

        Uri buildUri = Uri.parse(BASE_URL).buildUpon()
                .appendQueryParameter(QUERY_PARAM, location)
                .appendQueryParameter(SEARCH_LOCATION_PARAM, searchLocationFormat)
                        //.appendQueryParameter(BEGIN_DATE_PARAM,beginDate)
                .appendQueryParameter(END_DATE_PARAM, endDate)
                .appendQueryParameter(SORT_PARAM, sortType)
                .appendQueryParameter(API_KEY_PARAM, apiKey).build();

        LoggerClass.log(buildUri.toString());

        try {
            URL url = new URL(buildUri.toString());

            if (arguments == null) {
                if (Singleton.headlinesList == null) {
                    headline = new HeadlinesHandler().parseContent(RequestExecutor.makeGetRequest(url.toString()));
                    return headline;
                } else {
                    return Singleton.headlinesList;
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
        */
    }

    private List<Headlines> makeBingNewsRequest() {
        if (Singleton.headlinesList == null) {
            return new HeadlinesHandler().parseContent(
                            RequestExecutor.
                                    makeRequestWithEncodedParametersForBingNews(BING_NEWS_URL_INCLUDING_QUERY, BING_API_KEY));
        } else {
            return Singleton.headlinesList;
        }
    }

}