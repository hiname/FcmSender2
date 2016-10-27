package com.fcmsender2;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by USER on 2016-10-25.
 */
public class FcmSender {
    public static final String SERVER_FCM = "https://fcm.googleapis.com/fcm/send";


    public void main() {
        try {
            URL url = new URL(SERVER_FCM);
            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
            httpConn.setRequestProperty("Authorization", "key=" + "AIzaSyAN7qcnJgV5tlNx35-2J4xq-krVLIo2CQw");
            httpConn.setRequestProperty("Content-Type", "application/json");
            httpConn.setRequestMethod("POST");
            httpConn.setDoOutput(true);

            // Send GCM message content.
            OutputStream outputStream = httpConn.getOutputStream();
            // String token = "duOvNQ2bQzA:APA91bGNkagnaDMNwZnUx1T_FcI6sdnp42yAS4GDnWIrcRqzYe-0D92MszxDq5NDeUtJgTz5z45o6T6uEBuByRgZ4bBFEiJZVVlyilUp9dJeVeE86_NuQwFqEXzgR9PWsKfYr0Pv9L-s";
            // String token = "cI9PzZupjCI:APA91bEHBnG58i6yNO_D-Jgr5UXxFCfH5GlMv7olr-EDx0f0chTuwDk0s1Tcjh0yJOQMqndeC7Gmx6UagS3eJ9zoFRZMiiDoVK6lpGXW5WD8_PIxzqmEECfN4bJbMr_O9LrUb1-mfdCM";
            String token = "/topics/news";
            outputStream.write(getPayload(token, "newsTopic"));
            outputStream.flush();

            InputStream inputStream = httpConn.getInputStream();
            String resp = inputStream.toString();
            System.out.println(resp);
            System.out.println("Check your device/emulator for notification or logcat for " +
                    "confirmation of the receipt of the GCM message.");
        } catch (IOException e) {
            System.out.println("Unable to send GCM message.");
            System.out.println("Please ensure that API_KEY has been replaced by the server " +
                    "API key, and that the device's registration token is correct (if specified).");
            e.printStackTrace();
        }
    }

    /**
     *
     * @param tokenOrTopics
     * topicsType : /topics/topicName ex) /topics/news
     * @param msg
     * @return
     */
    public byte[] getPayload(String tokenOrTopics, String msg) {
        JSONObject jGcmData = new JSONObject();
        try {
            jGcmData.put("to", tokenOrTopics);
            JSONObject jsonData = getJSONData(msg);
            jGcmData.put("data", jsonData);
            jGcmData.put("notification", jsonData);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jGcmData.toString().getBytes();
    }

    public JSONObject getJSONData(String msg) {
        JSONObject jData = new JSONObject();
        try {
            jData.put("message", "mm" + msg);
            jData.put("body", "bb" + msg);
            jData.put("title", "Notification_title");
            jData.put("icon", "icon");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jData;
    }

    private static boolean isEmpty(String s) {
        return (s == null || "".equals(s));
    }

}

