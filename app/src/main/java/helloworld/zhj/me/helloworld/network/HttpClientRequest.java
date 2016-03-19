package helloworld.zhj.me.helloworld.network;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by zhj-plusplus on 3/7/16.
 */
public class HttpClientRequest {

    private static Context mCtx;
    public RequestQueue mRequestQueue;

    private HttpClientRequest() {
        mRequestQueue = getRequestQueue();
    }

    public static HttpClientRequest getInstance(Context context) {
        mCtx = context;
        return ClientHolder.CLIENT_REQUEST;
    }

    private static class ClientHolder {
        private static final HttpClientRequest CLIENT_REQUEST = new HttpClientRequest();
    }

    /**
     * Cancels all the request in the Volley queue for a given tag
     *
     * @param tag associated with the Volley requests to be cancelled
     */
    public void cancelAllRequests(String tag) {
        if (getRequestQueue() != null) {
            getRequestQueue().cancelAll(tag);
        }
    }

    /**
     * Returns a Volley request queue for creating network requests
     *
     * @return {@link RequestQueue}
     */
    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            // use  custom okhttpStack, make better work .
            mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext(),
                    new OkHttpStack());
        }
        return mRequestQueue;
    }

    /**
     * Adds a request to the Volley request queue
     *
     * @param request is the request to add to the Volley queue
     */
    public <T> void addRequest(Request<T> request) {
        getRequestQueue().add(request);
    }

    /**
     * Adds a request to the Volley request queue
     *
     * @param request is the request to add to the Volley queuest
     * @param tag     is the tag identifying the request
     */
    public <T> void addRequest(Request<T> request, String tag) {
        request.setTag(tag);
        getRequestQueue().add(request);
    }
}
