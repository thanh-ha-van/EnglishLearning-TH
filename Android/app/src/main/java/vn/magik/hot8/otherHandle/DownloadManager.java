package vn.magik.hot8.otherHandle;


import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by NGUYENHUONG on 5/23/17.
 */

public class DownloadManager {
    public void downloadFile(final String fileName) {
        Observable<Integer> downloadObservable = Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> sub) {
                int count;
                InputStream input = null;
                FileOutputStream output = null;
                try {
                    byte data[] = new byte[1024];
                    long total = 0;
                    URL url = new URL(FileManager.getLinkServer(fileName));
                    URLConnection conection = url.openConnection();
                    conection.connect();
                    sub.onNext(0);
                    int lenghOfFile = conection.getContentLength();
                    input = new BufferedInputStream(url.openStream(), 10 * 1024);
                    output = new FileOutputStream(FileManager.getLinkLocalFile(fileName));
                    while ((count = input.read(data)) != -1) {
                        total += count;
                        sub.onNext((int) ((total * 100) / lenghOfFile));
                        output.write(data, 0, count);
                    }
                    output.flush();
                } catch (Exception e) {
                    sub.onError(e);
                    e.printStackTrace();
                } finally {
                    if (output != null) {
                        try {
                            output.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (input != null) {
                        try {
                            input.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }
        });
        downloadObservable
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
        //.subscribe(callback);
    }
}
