package helloworld.zhj.me.helloworld.network;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;

/**
 * Created by zhj-plusplus on 3/11/16.
 */
public class HttpRequestService {

    String url = "http://www.weather.com.cn/adat/sk/101010100.html";

    public static void requestDemo(Context context, String url, Response.Listener<String> successListener, Response.ErrorListener errorListener) {
        CustomRequest.RequestBuilder requestBuilder = new CustomRequest.RequestBuilder();
        requestBuilder.method(Request.Method.GET).url(url).successListener(successListener).errorListener(errorListener).build();
        CustomRequest customRequest = new CustomRequest(requestBuilder);
        HttpClientRequest.getInstance(context).addRequest(customRequest);
    }
}
