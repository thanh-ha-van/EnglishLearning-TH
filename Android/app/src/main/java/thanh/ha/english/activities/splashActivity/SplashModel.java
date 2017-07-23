package thanh.ha.english.activities.splashActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import retrofit.Callback;
import retrofit.Response;
import thanh.ha.english.constants.Constants;
import thanh.ha.english.constants.Globals;
import thanh.ha.english.models.WordSqlite;
import thanh.ha.english.models.response.LanguageResponse;
import thanh.ha.english.services.UserService;
import thanh.ha.english.utils.Utils;

/**
 * Created by HaVan on 5/24/2017.
 */

class SplashModel {
    private WordSqlite wordSqlite;
    private SharedPreferences sPref;
    private UserService userService;
    private Context context;
    private SplashInterface.RequiredPresenterOps mPresenter;

    SplashModel(Context context, SplashInterface.RequiredPresenterOps mPresenter) {
        this.context = context;
        wordSqlite = new WordSqlite(context);
        this.mPresenter = mPresenter;
        sPref = context.getSharedPreferences(Constants.FILE_CONFIG, Context.MODE_PRIVATE);
        userService = new UserService();
        loadAllConfig();
    }


    int getCountWordSelected() {
        return wordSqlite.getCountWordSelected();
    }

    private void loadAllConfig() {
        String sha1 = getCertificateSHA1Fingerprint(context);
        int wordOfDay = sPref.getInt(Constants.KEY_WORD_OF_DAY, 5);
        int userLevel = sPref.getInt(Constants.KEY_LEVEL, 0);
        String hobbies = sPref.getString(Constants.KEY_FAVORITE, Globals.getIns().getConfig().getHobbies());
        String languageCode = sPref.getString(Constants.KEY_LANGUAGE, null);
        Boolean isNotify = sPref.getBoolean(Constants.KEY_NOTIFICATION, false);
        Boolean isReview = sPref.getBoolean(Constants.KEY_REVIEW, false);
        Globals.getIns().getConfig().setToken(Utils.md5(sha1));
        Globals.getIns().getConfig().setmWordOfDay(wordOfDay);
        Globals.getIns().getConfig().setCurrentLanguageCode(languageCode);
        Globals.getIns().getConfig().setHobbies(hobbies);
        Globals.getIns().getConfig().setLevel(userLevel);
        Globals.getIns().getConfig().setNotify(isNotify);
        Globals.getIns().getConfig().setReviewApp(isReview);

    }


    private String getCertificateSHA1Fingerprint(Context mContext) {
        PackageManager pm = mContext.getPackageManager();
        String packageName = mContext.getPackageName();
        int flags = PackageManager.GET_SIGNATURES;
        PackageInfo packageInfo = null;
        try {
            packageInfo = pm.getPackageInfo(packageName, flags);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        Signature[] signatures = packageInfo.signatures;
        byte[] cert = signatures[0].toByteArray();
        InputStream input = new ByteArrayInputStream(cert);
        CertificateFactory cf = null;
        try {
            cf = CertificateFactory.getInstance("X509");
        } catch (CertificateException e) {
            e.printStackTrace();
        }
        X509Certificate c = null;
        try {
            c = (X509Certificate) cf.generateCertificate(input);
        } catch (CertificateException e) {
            e.printStackTrace();
        }
        String hexString = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA1");
            byte[] publicKey = md.digest(c.getEncoded());
            hexString = byte2HexFormatted(publicKey);
        } catch (NoSuchAlgorithmException | CertificateEncodingException e1) {
            e1.printStackTrace();
        }
        return hexString;
    }

    private String byte2HexFormatted(byte[] arr) {
        StringBuilder str = new StringBuilder(arr.length * 2);
        for (int i = 0; i < arr.length; i++) {
            String h = Integer.toHexString(arr[i]);
            int l = h.length();
            if (l == 1) h = "0" + h;
            if (l > 2) h = h.substring(l - 2, l);
            str.append(h.toUpperCase());
            if (i < (arr.length - 1)) str.append(':');
        }
        return str.toString();
    }

    void getLanguagesEnable() {
        userService.getLanguage(
                Globals.getIns().getConfig().getToken(),
                new Callback<LanguageResponse>() {
                    @Override
                    public void onResponse(Response<LanguageResponse> response) {
                        mPresenter.getLanguageEnable(response);
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        mPresenter.getLanguageEnable(null);
                    }
                });
    }
}
