package vn.magik.hot8.constants;

import vn.magik.hot8.models.Config;

/**
 * Created by HaVan on 5/24/2017.
 */

public class Globals {
    private static Globals ourInstance = null;
    private Config config;


    public static Globals getIns() {
        if (ourInstance == null) {
            ourInstance = new Globals();
        }
        return ourInstance;
    }


    private Globals() {
        config = new Config();
    }

    public Config getConfig() {
        return config;
    }
}