package com.usc.searchonfb.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by adarsh on 4/19/2017.
 */

public class EncodeURIComponent {


    public static String encodeURI(String component) {
        String result = null;

        try {
            result = URLEncoder.encode(component, "UTF-8");
                    /*.replaceAll("\\%28", "(")
                    .replaceAll("\\%29", ")")
                    .replaceAll("\\+", "%20")
                    .replaceAll("\\%27", "'")
                    .replaceAll("\\%21", "!")
                    .replaceAll("\\%7E", "~");*/
        } catch (UnsupportedEncodingException e) {
            result = component;
        }

        return result;
    }


}
