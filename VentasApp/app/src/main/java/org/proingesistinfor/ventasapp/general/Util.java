package org.proingesistinfor.ventasapp.general;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;

/**
 * Created by peter on 04-11-17.
 */

public class Util {

    public static Bitmap convertToBitmap(Context context, int id) {
        return BitmapFactory.decodeResource(context.getResources(), id);
    }

    public static Bitmap base64ToBitmap(String base64) {
        byte[] decodedString = Base64.decode(base64, Base64.DEFAULT);
        return  BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
    }

    public static Bitmap compressBitmapImageView(Bitmap bitmap){
        return Bitmap.createScaledBitmap(bitmap, 200, 100, true);
    }

    public static String getBase64ImageView(ImageView imageView) {
        Bitmap bitmap =  ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 70, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream .toByteArray();
        return Base64.encodeToString(byteArray, Base64.DEFAULT).replace("\n", "");
    }

    public static Typeface fontBold(Context context) {
        return Typeface.createFromAsset(context.getAssets(),"fonts/titillium-bold.otf");
    }

    public static Typeface fontRegular(Context context) {
        return Typeface.createFromAsset(context.getAssets(),"fonts/titillium-regular.otf");
    }
}
