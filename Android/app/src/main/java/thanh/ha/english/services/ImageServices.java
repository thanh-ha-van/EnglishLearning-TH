package thanh.ha.english.services;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import thanh.ha.english.constants.Constants;

/**
 * Created by NGUYENHUONG on 3/29/17.
 */

public class ImageServices {

    private Context context;

    public ImageServices(Context context) {
        this.context = context;
    }

    public void showImage(String fileName, ImageView imageView, int imageHolder) {
        if (fileName != null && !fileName.isEmpty()) {
            String url = String.format("%s%s", Constants.SERVER_IMAGE, fileName);
            if (imageHolder == 0) {
                Picasso.with(context).load(url).into(imageView);
            } else {
                Picasso.with(context).load(url).placeholder(imageHolder).into(imageView);
            }
        } else {
            if (imageHolder != 0) {
                Picasso.with(context).load(imageHolder).into(imageView);
            }
        }
    }

    public void showImageCircle(String fileName, ImageView imageView, int imageHolder) {
//        if (fileName != null && !fileName.isEmpty()) {
//            String url = String.format("%s%s", Constants.SERVER_IMAGE, fileName);
//            Picasso.with(context).load(url).transform(new CircleTransform()).into(imageView);
//        } else {
//            Picasso.with(context).load(imageHolder).into(imageView);
//        }

    }
}
