package id.aldente.socket.util;

import com.prudential.platform.stream.compute.NonBlocking;
import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.json.JSONObject;

import javax.net.ssl.SSLContext;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


public class HttpUtility {

    public final static Logger LOGGER = Logger.getLogger(HttpUtility.class.getName());

    public static URI appendQueryString(String uri, String appendQuery) throws URISyntaxException {
        URI oldUri = new URI(uri);

        String newQuery = oldUri.getQuery();
        if (newQuery == null) {
            newQuery = appendQuery;
        } else {
            newQuery += "&" + appendQuery;
        }

        URI newUri = new URI(oldUri.getScheme(), oldUri.getAuthority(),
                oldUri.getPath(), newQuery, oldUri.getFragment());

        return newUri;
    }

    @NonBlocking
    public static JSONObject post(String URL, String authorization, JSONObject requestBody, boolean isMagnum) {

        JSONObject res = new JSONObject();
        res.put("success", true);
        res.put("message", "");
        try {
            StringEntity entity = new StringEntity(requestBody.toString(), ContentType.APPLICATION_JSON);

            URI uri = new URI(URL);

            if (isMagnum) {
                uri = appendQueryString(URL, "Authorization=" + authorization);
            }

            HttpPost http = new HttpPost(uri);

            if (!authorization.isEmpty()) {
                http.addHeader("Authorization", authorization);
            }
            http.setEntity(entity);
            HttpResponse httpResponse = null;
            CloseableHttpClient client = null;
            if (URL.toLowerCase().indexOf("https") > -1) {


                SSLContext sslContext = new SSLContextBuilder()
                        .loadTrustMaterial(null, (certificate, authType) -> true).build();

                client = HttpClients.custom()
                        .setSSLContext(sslContext)
                        .setSSLHostnameVerifier(new NoopHostnameVerifier())
                        .build();

                httpResponse = client.execute(http);
            } else {
                client = HttpClientBuilder.create().build();
                httpResponse = client.execute(http);
            }


            LOGGER.info(URL);
            LOGGER.info("Response Status:: "
                    + httpResponse.getStatusLine().getStatusCode());

            if (httpResponse.getStatusLine().getStatusCode() == 401) {
                res.put("success", false);
                res.put("message", "UNAUTHORIZED");
                res.put("statusCode", 401);
                return res;
            }


            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    httpResponse.getEntity().getContent()));

            StringBuffer response = new StringBuffer();
            String inputLine = "";


            while ((inputLine = reader.readLine()) != null) {
                response.append(inputLine);
            }
            reader.close();
            client.close();

            try {
                String str = response.toString();
                JSONObject objRes = new JSONObject(str);

                if (httpResponse.getStatusLine().getStatusCode() != 401 && httpResponse.getStatusLine().getStatusCode() != 200) {
                    res.put("success", false);
                    res.put("message", objRes);
                    res.put("statusCode", httpResponse.getStatusLine().getStatusCode());
                    return res;
                }


                res.put("success", true);
                res.put("message", "");
                res.put("data", objRes);
            } catch (Exception e) {
                res.put("success", false);
                res.put("message", e.getMessage());
                res.put("data", response.toString());
            }
        } catch (Exception e) {
            res.put("success", false);
            res.put("message", e.getMessage());
        }

        return res;
    }

    @NonBlocking
    public static JSONObject postFormUrlEncryptDecrypt(String URL, String text) {

        JSONObject res = new JSONObject();
        res.put("success", true);
        res.put("message", "");
        try {

            List<NameValuePair> form = new ArrayList<>();
            form.add(new BasicNameValuePair("app", "dpu"));
            form.add(new BasicNameValuePair("text", text));
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(form, Consts.UTF_8);


            URI uri = new URI(URL);


            HttpPost http = new HttpPost(uri);


            http.setEntity(entity);
            HttpResponse httpResponse = null;
            CloseableHttpClient client = null;
            if (URL.toLowerCase().indexOf("https") > -1) {


                SSLContext sslContext = new SSLContextBuilder()
                        .loadTrustMaterial(null, (certificate, authType) -> true).build();

                client = HttpClients.custom()
                        .setSSLContext(sslContext)
                        .setSSLHostnameVerifier(new NoopHostnameVerifier())
                        .build();

                httpResponse = client.execute(http);
            } else {
                client = HttpClientBuilder.create().build();
                httpResponse = client.execute(http);
            }


            LOGGER.info(URL);
            LOGGER.info("Response Status:: "
                    + httpResponse.getStatusLine().getStatusCode());

            if (httpResponse.getStatusLine().getStatusCode() == 401) {
                res.put("success", false);
                res.put("message", "UNAUTHORIZED");
                res.put("statusCode", 401);
                return res;
            }


            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    httpResponse.getEntity().getContent()));

            StringBuffer response = new StringBuffer();
            String inputLine = "";


            while ((inputLine = reader.readLine()) != null) {
                response.append(inputLine);
            }
            reader.close();
            client.close();

            try {
                String str = response.toString();

                //JSONObject objRes = new JSONObject(str);

                if (httpResponse.getStatusLine().getStatusCode() != 401 && httpResponse.getStatusLine().getStatusCode() != 200) {
                    res.put("success", false);
                    res.put("message", str);
                    res.put("statusCode", httpResponse.getStatusLine().getStatusCode());
                    return res;
                }


                res.put("success", true);
                res.put("message", "");
                res.put("data", str);
            } catch (Exception e) {
                res.put("success", false);
                res.put("message", e.getMessage());
                res.put("data", response.toString());
            }
        } catch (Exception e) {
            res.put("success", false);
            res.put("message", e.getMessage());
        }

        return res;
    }
}
