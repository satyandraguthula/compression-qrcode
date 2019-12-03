package com.journeyapps.barcodescanner;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.RGBLuminanceSource;
import com.google.zxing.Result;
import com.google.zxing.ResultPoint;
import com.google.zxing.client.android.R;
import com.google.zxing.common.HybridBinarizer;
import com.journeyapps.barcodescanner.camera.CameraInstance;
import com.journeyapps.barcodescanner.camera.PreviewCallback;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.opencv.android.Utils;
import org.opencv.core.*;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
/**
 *
 */
public class DecoderThread {
    private static final String TAG = DecoderThread.class.getSimpleName();

    private CameraInstance cameraInstance;
    private HandlerThread thread;
    private Handler handler;
    private Decoder decoder;
    private Handler resultHandler;
    private Rect cropRect;
    private boolean running = false;
    private final Object LOCK = new Object();
    String strarray[]=new String[18000000];
    private final Handler.Callback callback = new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            if (message.what == R.id.zxing_decode) {
                decode((SourceData) message.obj);
            }
            return true;
        }
    };

    public DecoderThread(CameraInstance cameraInstance, Decoder decoder, Handler resultHandler) {
        Util.validateMainThread();

        this.cameraInstance = cameraInstance;
        try {
            File fileh1 = new File(Environment.getExternalStorageDirectory().getPath().toString(), "hash.txt");
            InputStream inputStream = new BufferedInputStream(new FileInputStream(fileh1));

            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String line = null;
                while ((line = bufferedReader.readLine()) != null) {
                    if (line.contains(",")) {
                        String[] strings = line.split(",");
                        strarray[Integer.parseInt(strings[1])] = strings[0];
                    }
                }

            }
        }
            catch(Exception exception)
            {

            }
            this.decoder = decoder;
            this.resultHandler = resultHandler;
            //this.cameraInstance.setTorch(true);
    }
    public Decoder getDecoder() {
        return decoder;
    }

    public void setDecoder(Decoder decoder) {
        this.decoder = decoder;
    }

    public Rect getCropRect() {
        return cropRect;
    }

    public void setCropRect(Rect cropRect) {
        this.cropRect = cropRect;
    }

    /**
     * Start decoding.
     *
     * This must be called from the UI thread.
     */
    public void start() {
        Util.validateMainThread();

        thread = new HandlerThread(TAG);
        thread.start();
        handler = new Handler(thread.getLooper(), callback);
        running = true;
        requestNextPreview();
    }


    /**
     * Stop decoding.
     *
     * This must be called from the UI thread.
     */
    public void stop() {
        Util.validateMainThread();

        synchronized (LOCK) {
            running = false;
            handler.removeCallbacksAndMessages(null);
            thread.quit();
        }
    }


    private final PreviewCallback previewCallback = new PreviewCallback() {
        @Override
        public void onPreview(SourceData sourceData) {
            // Only post if running, to prevent a warning like this:
            //   java.lang.RuntimeException: Handler (android.os.Handler) sending message to a Handler on a dead thread

            // synchronize to handle cases where this is called concurrently with stop()
            synchronized (LOCK) {
                if (running) {
                    // Post to our thread.
                    handler.obtainMessage(R.id.zxing_decode, sourceData).sendToTarget();
                }
            }
        }
    };

    private void requestNextPreview() {
        if (cameraInstance.isOpen()) {
            cameraInstance.requestPreview(previewCallback);
        }
    }

    protected LuminanceSource createSource(SourceData sourceData) {
        if (this.cropRect == null) {
            return null;
        } else {
            return sourceData.createSource();
        }
    }
    boolean hue0=true,hue1=true,hue2=true,hue3=true,ascflag=false;
    Result rawResult = null;
    Result rawResult2 = null;
    Result rawResult3 = null;
    Result rawResult4 = null;
    private void decode(SourceData sourceData) {
        long start = System.currentTimeMillis();
        sourceData.setCropRect(cropRect);
        LuminanceSource source = createSource(sourceData);
        Bitmap bitmap = sourceData.getBitmap();
        if(source != null) {
            String root = Environment.getExternalStorageDirectory().toString();
            File myDir = new File(root + "/saved_images");
            myDir.mkdirs();
            String fname = "hue0"+ ".jpg";
            String fname1 = "hue1"+ ".jpg";
            String fname2 = "hue2"+ ".jpg";
            String fname3 = "hue3"+ ".jpg";
            String fname4 = "hue4"+ ".jpg";
            String fname5 = "hue5"+ ".jpg";
            String fname6 = "hue6"+ ".jpg";
            String fname7 = "hue7"+ ".jpg";
            String fname8 = "hue8"+ ".jpg";
            String fname9 = "hue9"+ ".jpg";
            String fname10 = "hue10"+ ".jpg";
            String fname11 = "hue11"+ ".jpg";
            String fname12 = "hue12"+ ".jpg";
            String fname13 = "hue13"+ ".jpg";
            File file = new File (myDir, fname);
            File file1 = new File (myDir, fname1);
            File file2 = new File (myDir, fname2);
            File file3 = new File (myDir, fname3);
            File file4 = new File (myDir, fname4);
            File file5 = new File (myDir, fname5);
            File file6 = new File (myDir, fname6);
            File file7 = new File (myDir, fname7);
            File file8 = new File (myDir, fname8);
            File file9 = new File (myDir, fname9);
            File file10 = new File (myDir, fname10);
            File file11 = new File (myDir, fname11);
            File file12 = new File (myDir, fname12);
            File file13 = new File (myDir, fname13);
            if(source!=null) {
                int dilation_size = 2;
                Mat element = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(2*dilation_size + 1, 2*dilation_size+1));
                Mat hsv = new Mat(bitmap.getHeight(),bitmap.getWidth(), CvType.CV_8UC1);
                Mat hsv1 = new Mat(bitmap.getHeight(),bitmap.getWidth(), CvType.CV_8UC1);
                Mat hsv2 = new Mat(bitmap.getHeight(),bitmap.getWidth(), CvType.CV_8UC1);
                Mat hsv3 = new Mat(bitmap.getHeight(),bitmap.getWidth(), CvType.CV_8UC1);
                Mat hsv4 = new Mat(bitmap.getHeight(),bitmap.getWidth(), CvType.CV_8UC1);
                Mat hsv5 = new Mat(bitmap.getHeight(),bitmap.getWidth(), CvType.CV_8UC1);
                Mat hsv6 = new Mat(bitmap.getHeight(),bitmap.getWidth(), CvType.CV_8UC1);
                Mat hsv7 = new Mat(bitmap.getHeight(),bitmap.getWidth(), CvType.CV_8UC1);
                Mat hsv8 = new Mat(bitmap.getHeight(),bitmap.getWidth(), CvType.CV_8UC1);
                Mat hsv9 = new Mat(bitmap.getHeight(),bitmap.getWidth(), CvType.CV_8UC1);
                Mat hsv10 = new Mat(bitmap.getHeight(),bitmap.getWidth(), CvType.CV_8UC1);
                Mat hsv11 = new Mat(bitmap.getHeight(),bitmap.getWidth(), CvType.CV_8UC1);
                Mat hsv12 = new Mat(bitmap.getHeight(),bitmap.getWidth(), CvType.CV_8UC1);
                Mat hsv13 = new Mat(bitmap.getHeight(),bitmap.getWidth(), CvType.CV_8UC1);
                Mat hsv14 = new Mat(bitmap.getHeight(),bitmap.getWidth(), CvType.CV_8UC1);
                Utils.bitmapToMat(bitmap, hsv);
                Imgproc.cvtColor(hsv, hsv14, Imgproc.COLOR_RGB2HSV);
                Core.inRange(hsv14, new Scalar(67, 100, 100), new Scalar(80, 255, 255),/*new Scalar(0, 150, 100), new Scalar(8, 255, 255),*/ hsv);
                Imgproc.dilate(hsv, hsv, element);
                Imgproc.erode(hsv, hsv, element);
                Imgproc.threshold(hsv, hsv, 200, 255, Imgproc.THRESH_BINARY_INV);
                Core.inRange(hsv14, new Scalar(95, 100, 100), new Scalar(100, 255, 255),/*new Scalar(12, 150, 100), new Scalar(16, 255, 255),*/ hsv1);
                Imgproc.dilate(hsv1, hsv1, element);
                Imgproc.erode(hsv1, hsv1, element);
                Imgproc.threshold(hsv1, hsv1, 200, 255, Imgproc.THRESH_BINARY_INV);
                Core.inRange(hsv14, new Scalar(100, 100, 100), new Scalar(106, 255, 255),/*new Scalar(17, 150, 100), new Scalar(22, 255, 255),*/ hsv2);
                Imgproc.dilate(hsv2, hsv2, element);
                Imgproc.erode(hsv2, hsv2, element);
                Imgproc.threshold(hsv2, hsv2, 200, 255, Imgproc.THRESH_BINARY_INV);
                Core.inRange(hsv14, new Scalar(110, 100, 100), new Scalar(155, 255, 255),/*new Scalar(22, 150, 100), new Scalar(27, 255, 255),*/ hsv3);
                Imgproc.dilate(hsv3, hsv3, element);
                Imgproc.erode(hsv3, hsv3, element);
                Imgproc.threshold(hsv3, hsv3, 200, 255, Imgproc.THRESH_BINARY_INV);


                if (/*!*/(hue0&&hue1&&hue2)) {
                    Core.inRange(hsv14, new Scalar(45, 150, 100), new Scalar(53, 255, 255),/*new Scalar(106, 150, 100), new Scalar(112, 255, 255),*/ hsv10);
                    Imgproc.dilate(hsv10, hsv10, element);
                    Imgproc.erode(hsv10, hsv10, element);
                    Imgproc.threshold(hsv10, hsv10, 200, 255, Imgproc.THRESH_BINARY_INV);
                    bitmap=BitmapFactory.decodeFile(Environment.getExternalStorageDirectory().getPath().toString()+"//temp/hue10.jpg");
                    if (file10.exists()) file10.delete();
                    try {
                        FileOutputStream out = new FileOutputStream(file10);
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 20, out);
                        out.flush();
                        out.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (/*!*/(hue0&&hue1&&hue3)) {
                    Core.inRange(hsv14, new Scalar(54, 100, 100), new Scalar(67, 255, 255),/*new Scalar(113, 150, 100), new Scalar(135, 255, 255),*/ hsv11);
                    Imgproc.dilate(hsv11, hsv11, element);
                    Imgproc.erode(hsv11, hsv11, element);
                    Imgproc.threshold(hsv11, hsv11, 200, 255, Imgproc.THRESH_BINARY_INV);
                    bitmap=BitmapFactory.decodeFile(Environment.getExternalStorageDirectory().getPath().toString()+"//temp//hue11.jpg");
                    if (file11.exists()) file11.delete();
                    try {
                        FileOutputStream out = new FileOutputStream(file11);
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 20, out);
                        out.flush();
                        out.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (/*!*/(hue0&&hue2&&hue3))
                {
                Core.inRange(hsv14, new Scalar(157, 100, 100), new Scalar(170, 255, 255), hsv12);
                Imgproc.dilate(hsv12, hsv12, element);
                Imgproc.erode(hsv12, hsv12, element);
                Imgproc.threshold(hsv12, hsv12, 200, 255, Imgproc.THRESH_BINARY_INV);
                    bitmap=BitmapFactory.decodeFile(Environment.getExternalStorageDirectory().getPath().toString()+"//temp/hue12.jpg");
                    if (file12.exists()) file12.delete();
                    try {
                        FileOutputStream out = new FileOutputStream(file12);
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 20, out);
                        out.flush();
                        out.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (/*!*/(hue1&&hue2&&hue3))
                {
                    Core.inRange(hsv14, new Scalar(172, 100, 100), new Scalar(176, 255, 255), hsv13);
                    Imgproc.dilate(hsv13, hsv13, element);
                    Imgproc.erode(hsv13, hsv13, element);
                    Imgproc.threshold(hsv13, hsv13, 200, 255, Imgproc.THRESH_BINARY_INV);
                    bitmap=BitmapFactory.decodeFile(Environment.getExternalStorageDirectory().getPath().toString()+"//temp/hue13.jpg");
                    if (file13.exists()) file13.delete();
                    try {
                        FileOutputStream out = new FileOutputStream(file13);
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 20, out);
                        out.flush();
                        out.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (/*!*/(hue0&&hue1))
                {
                    Core.inRange(hsv14, new Scalar(15, 150, 100), new Scalar(20, 255, 255),/*new Scalar(42, 150, 100), new Scalar(47, 255, 255),*/ hsv6);
                    Imgproc.dilate(hsv6, hsv6, element);
                    Imgproc.erode(hsv6, hsv6, element);
                    Imgproc.threshold(hsv6, hsv6, 200, 255, Imgproc.THRESH_BINARY_INV);
                    bitmap=BitmapFactory.decodeFile(Environment.getExternalStorageDirectory().getPath().toString()+"//temp/hue6.jpg");
                    if (file6.exists()) file6.delete();
                    try {
                        FileOutputStream out = new FileOutputStream(file6);
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 20, out);
                        out.flush();
                        out.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (/*!*/(hue0&&hue2))
                {
                    Core.inRange(hsv14, new Scalar(0, 100, 100), new Scalar(5, 255, 255),/*new Scalar(30, 150, 100), new Scalar(36, 255, 255),*/ hsv4);
                    Imgproc.dilate(hsv4, hsv4, element);
                    Imgproc.erode(hsv4, hsv4, element);
                    Imgproc.threshold(hsv4, hsv4, 200, 255, Imgproc.THRESH_BINARY_INV);
                    bitmap=BitmapFactory.decodeFile(Environment.getExternalStorageDirectory().getPath().toString()+"//temp/hue4.jpg");
                    if (file4.exists()) file4.delete();
                    try {
                        FileOutputStream out = new FileOutputStream(file4);
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 20, out);
                        out.flush();
                        out.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (/*!*/(hue0&&hue3))
                {
                    Core.inRange(hsv14, new Scalar(9, 100, 100), new Scalar(14, 255, 255),/*new Scalar(36, 150, 100), new Scalar(41, 255, 255),*/ hsv5);
                    Imgproc.dilate(hsv5, hsv5, element);
                    Imgproc.erode(hsv5, hsv5, element);
                    Imgproc.threshold(hsv5, hsv5, 200, 255, Imgproc.THRESH_BINARY_INV);
                    bitmap=BitmapFactory.decodeFile(Environment.getExternalStorageDirectory().getPath().toString()+"//temp/hue5.jpg");
                    if (file5.exists()) file5.delete();
                    try {
                        FileOutputStream out = new FileOutputStream(file5);
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 20, out);
                        out.flush();
                        out.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (/*!*/(hue1&&hue2))
                {

                    Core.inRange(hsv14, new Scalar(30, 150, 100), new Scalar(36, 255, 255),/*new Scalar(67, 150, 100), new Scalar(85, 255, 255),*/ hsv8);
                    Imgproc.dilate(hsv8, hsv8, element);
                    Imgproc.erode(hsv8, hsv8, element);
                    Imgproc.threshold(hsv8, hsv8, 200, 255, Imgproc.THRESH_BINARY_INV);
                    bitmap=BitmapFactory.decodeFile(Environment.getExternalStorageDirectory().getPath().toString()+"//temp/hue8.jpg");
                    if (file8.exists()) file8.delete();
                    try {
                        FileOutputStream out = new FileOutputStream(file8);
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 20, out);
                        out.flush();
                        out.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (/*!*/(hue1&&hue3))
                {
                    Core.inRange(hsv14, new Scalar(36, 150, 100), new Scalar(44, 255, 255),/*new Scalar(100, 150, 100), new Scalar(106, 255, 255),*/ hsv9);
                    Imgproc.dilate(hsv9, hsv9, element);
                    Imgproc.erode(hsv9, hsv9, element);
                    Imgproc.threshold(hsv9, hsv9, 200, 255, Imgproc.THRESH_BINARY_INV);
                    bitmap=BitmapFactory.decodeFile(Environment.getExternalStorageDirectory().getPath().toString()+"//temp/hue9.jpg");
                    if (file9.exists()) file9.delete();
                    try {
                        FileOutputStream out = new FileOutputStream(file9);
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 20, out);
                        out.flush();
                        out.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (/*!*/(hue2&&hue3))
                {
                    Core.inRange(hsv14, new Scalar(21, 150, 100), new Scalar(28, 255, 255),/*new Scalar(50, 150, 100), new Scalar(64, 255, 255),*/ hsv7);
                    Imgproc.dilate(hsv7, hsv7, element);
                    Imgproc.erode(hsv7, hsv7, element);
                    Imgproc.threshold(hsv7, hsv7, 200, 255, Imgproc.THRESH_BINARY_INV);
                    bitmap=BitmapFactory.decodeFile(Environment.getExternalStorageDirectory().getPath().toString()+"//temp/hue7.jpg");
                    if (file7.exists()) file7.delete();
                    try {
                        FileOutputStream out = new FileOutputStream(file7);
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 20, out);
                        out.flush();
                        out.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                Core.inRange(hsv14, new Scalar(0, 0, 5), new Scalar(255, 255, 80), hsv14);
                Imgproc.dilate(hsv14, hsv14, element);
                Imgproc.erode(hsv14, hsv14, element);
                Imgproc.threshold(hsv14, hsv14, 200, 255, Imgproc.THRESH_BINARY_INV);



                if (!(hue0)) {
                    Core.bitwise_and(hsv, hsv14, hsv);
                    Core.bitwise_and(hsv, hsv10, hsv);
                    Core.bitwise_and(hsv, hsv11, hsv);
                    Core.bitwise_and(hsv, hsv12, hsv);
                    Core.bitwise_and(hsv, hsv6, hsv);
                    Core.bitwise_and(hsv, hsv4, hsv);
                    Core.bitwise_and(hsv, hsv5, hsv);
                }
                if (!(hue1)) {
                    Core.bitwise_and(hsv1, hsv14, hsv1);
                    Core.bitwise_and(hsv1, hsv10, hsv1);
                    Core.bitwise_and(hsv1, hsv11, hsv1);
                    Core.bitwise_and(hsv1, hsv13, hsv1);
                    Core.bitwise_and(hsv1, hsv6, hsv1);
                    Core.bitwise_and(hsv1, hsv8, hsv1);
                    Core.bitwise_and(hsv1, hsv9, hsv1);
                }
                if (!(hue2)) {
                    Core.bitwise_and(hsv2, hsv14, hsv2);
                    Core.bitwise_and(hsv2, hsv10, hsv2);
                    Core.bitwise_and(hsv2, hsv12, hsv2);
                    Core.bitwise_and(hsv2, hsv13, hsv2);
                    Core.bitwise_and(hsv2, hsv4, hsv2);
                    Core.bitwise_and(hsv2, hsv8, hsv2);
                    Core.bitwise_and(hsv2, hsv7, hsv2);
                }
                if (!(hue3)) {
                    Core.bitwise_and(hsv3, hsv14, hsv3);
                    Core.bitwise_and(hsv3, hsv11, hsv3);
                    Core.bitwise_and(hsv3, hsv12, hsv3);
                    Core.bitwise_and(hsv3, hsv13, hsv3);
                    Core.bitwise_and(hsv3, hsv5, hsv3);
                    Core.bitwise_and(hsv3, hsv9, hsv3);
                    Core.bitwise_and(hsv3, hsv7, hsv3);
                }

                int width = bitmap.getWidth(), height = bitmap.getHeight();
                int[] pixels = new int[width * height];
                RGBLuminanceSource sourcetry;
                BinaryBitmap bBitmap ;
                MultiFormatReader reader;
                if (/*!*/(hue0)) {
                    bitmap=BitmapFactory.decodeFile(Environment.getExternalStorageDirectory().getPath().toString()+"//temp/hue0.jpg");
                    if (file.exists()) file.delete();
                    try {
                        FileOutputStream out = new FileOutputStream(file);
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 20, out);
                        out.flush();
                        out.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    bitmap.getPixels(pixels, 0, width, 0, 0, width, height);
                    sourcetry = new RGBLuminanceSource(width, height, pixels);
                    bBitmap = new BinaryBitmap(new HybridBinarizer(sourcetry));
                    reader = new MultiFormatReader();

                    try {
                        rawResult = reader.decode(bBitmap);
                    } catch (NotFoundException e) {
                        Log.e(TAG, "decode exception", e);
                    }
                }
                if (/*!*/(hue1)) {
                    bitmap=BitmapFactory.decodeFile(Environment.getExternalStorageDirectory().getPath().toString()+"//temp/hue1.jpg");
                    if (file1.exists()) file1.delete();
                    try {
                        FileOutputStream out = new FileOutputStream(file1);
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 20, out);
                        out.flush();
                        out.close();


                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    bitmap.getPixels(pixels, 0, width, 0, 0, width, height);
                    sourcetry = new RGBLuminanceSource(width, height, pixels);
                    bBitmap = new BinaryBitmap(new HybridBinarizer(sourcetry));
                    reader = new MultiFormatReader();
                    try {
                        rawResult2 = reader.decode(bBitmap);
                        if (rawResult2!=null)
                            Log.e("rawresult2 not null","");
                    } catch (NotFoundException e) {
                        Log.e(TAG, "decode exception", e);
                    }
                }
                if (/*!*/(hue2)) {
                    bitmap=BitmapFactory.decodeFile(Environment.getExternalStorageDirectory().getPath().toString()+"//temp/hue2.jpg");
                    if (file2.exists()) file2.delete();
                    try {
                        FileOutputStream out = new FileOutputStream(file2);
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 20, out);
                        out.flush();
                        out.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    bitmap.getPixels(pixels, 0, width, 0, 0, width, height);
                    sourcetry = new RGBLuminanceSource(width, height, pixels);
                    bBitmap = new BinaryBitmap(new HybridBinarizer(sourcetry));
                    reader = new MultiFormatReader();

                    try {
                        rawResult3 = reader.decode(bBitmap);
                        if (rawResult3!=null)
                            Log.e("rawresult3 not null","");
                    } catch (NotFoundException e) {
                        Log.e(TAG, "decode exception", e);
                    }
                }

                if (/*!*/(hue3)) {
                    bitmap=BitmapFactory.decodeFile(Environment.getExternalStorageDirectory().getPath().toString()+"//temp/hue3.jpg");
                    if (file3.exists()) file3.delete();
                    try {
                        FileOutputStream out = new FileOutputStream(file3);
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 20, out);
                        out.flush();
                        out.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    bitmap.getPixels(pixels, 0, width, 0, 0, width, height);
                    sourcetry = new RGBLuminanceSource(width, height, pixels);
                    bBitmap = new BinaryBitmap(new HybridBinarizer(sourcetry));
                    reader = new MultiFormatReader();
                    bitmap.recycle();
                    try {
                        rawResult4 = reader.decode(bBitmap);
                        if (rawResult4!=null)
                            Log.e("rawresult4 not null","");
                    } catch (NotFoundException e) {
                        Log.e(TAG, "decode exception", e);
                    }
                }

            }

            //rawResult = decoder.decode(source);
        }

        if (/*!*/(hue0&&hue1 &&hue2&&hue3)||(rawResult!=null||rawResult2!=null||rawResult3!=null||rawResult4!=null)) {
            Log.e("it isnt null","it isnt null");
            // Don't log the barcode contents for security.
            long end = System.currentTimeMillis();
            Log.d(TAG, "Found barcode in " + (end - start) + " ms");
            if (resultHandler != null) {
                if(!hue0&&rawResult!=null)
                {BarcodeResult barcodeResult1 = new BarcodeResult(rawResult, sourceData);
                    Log.e("hue0",barcodeResult1.toString());
                    OutputStream outputStream;
                    File filehue0=new File(Environment.getExternalStorageDirectory().getPath().toString()+"//temp","hue0.txt");
                    if (filehue0.exists ()) filehue0.delete ();
                    try {
                        outputStream = new BufferedOutputStream(new FileOutputStream(filehue0));
                        outputStream.write(barcodeResult1.toString().getBytes());
                        outputStream.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                hue0=true;}
                if(!hue1&&rawResult2!=null){BarcodeResult barcodeResult2 = new BarcodeResult(rawResult2, sourceData);
                Log.e("hue 1",barcodeResult2.toString());
                    OutputStream outputStream;
                    File filehue1=new File(Environment.getExternalStorageDirectory().getPath().toString()+"//temp","hue1.txt");
                    if (filehue1.exists ()) filehue1.delete ();
                    try {
                        outputStream = new BufferedOutputStream(new FileOutputStream(filehue1));
                        outputStream.write(barcodeResult2.toString().getBytes());
                        outputStream.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    hue1=true;}
                if(!hue2&&rawResult3!=null){BarcodeResult barcodeResult3 = new BarcodeResult(rawResult3, sourceData);
                    Log.e("hue 2",barcodeResult3.toString());
                    OutputStream outputStream;
                    File filehue2=new File(Environment.getExternalStorageDirectory().getPath().toString()+"//temp","hue2.txt");
                    if (filehue2.exists ()) filehue2.delete ();
                    try {
                        outputStream = new BufferedOutputStream(new FileOutputStream(filehue2));
                        outputStream.write(barcodeResult3.toString().getBytes());
                        outputStream.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    hue2=true;}
                if(!hue3&&rawResult4!=null){BarcodeResult barcodeResult4 = new BarcodeResult(rawResult4, sourceData);
                    Log.e("hue 3",barcodeResult4.toString());
                    OutputStream outputStream;
                    File filehue3=new File(Environment.getExternalStorageDirectory().getPath().toString()+"//temp","hue3.txt");
                    if (filehue3.exists ()) filehue3.delete ();
                    try {
                        outputStream = new BufferedOutputStream(new FileOutputStream(filehue3));
                        outputStream.write(barcodeResult4.toString().getBytes());
                        outputStream.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    hue3=true;}
                Message message;
                if(hue0&&hue1&&hue2&&hue3) {
                    BarcodeResult barcodeResult2 = new BarcodeResult(rawResult2, sourceData);
                    String huestr0intr="";
                    String huestr0="";
                    File fileh1=new File(Environment.getExternalStorageDirectory().getPath().toString()+"//temp","hue0.txt");
                    /*try {
                        InputStream inputStream = new BufferedInputStream(new FileInputStream(fileh1));

                        if ( inputStream != null ) {
                            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                            String receiveString = "";
                            StringBuilder stringBuilder = new StringBuilder();

                            while ( (receiveString = bufferedReader.readLine()) != null ) {
                                stringBuilder.append(receiveString);
                            }
                            Log.e("before","expand");
                            inputStream.close();
                            huestr0intr = stringBuilder.toString();
                            stringBuilder.delete(0,stringBuilder.length());
                            huestr0=stringBuilder.toString();

                        }
                    }
                    catch (FileNotFoundException e) {
                        Log.e("login activity", "File not found: " + e.toString());
                    } catch (IOException e) {
                        Log.e("login activity", "Can not read file: " + e.toString());
                    }*/
                    String huestr1intr="";
                    String huestr1="";
                    File fileh2=new File(Environment.getExternalStorageDirectory().getPath().toString()+"//temp","hue1.txt");

                    /*try {
                        InputStream inputStream = new BufferedInputStream(new FileInputStream(fileh2));

                        if ( inputStream != null ) {
                            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                            String receiveString = "";
                            StringBuilder stringBuilder = new StringBuilder();

                            while ( (receiveString = bufferedReader.readLine()) != null ) {
                                stringBuilder.append(receiveString);
                            }

                            inputStream.close();
                        }
                    }
                    catch (FileNotFoundException e) {
                        Log.e("login activity", "File not found: " + e.toString());
                    } catch (IOException e) {
                        Log.e("login activity", "Can not read file: " + e.toString());
                    }*/

                    String huestr2="";
                    String huestr2intr="";
                    File fileh3=new File(Environment.getExternalStorageDirectory().getPath().toString()+"//temp","hue2.txt");
                    /*try {
                        InputStream inputStream = new BufferedInputStream(new FileInputStream(fileh3));

                        if ( inputStream != null ) {
                            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                            String receiveString = "";
                            StringBuilder stringBuilder = new StringBuilder();

                            while ( (receiveString = bufferedReader.readLine()) != null ) {
                                stringBuilder.append(receiveString);
                            }

                            inputStream.close();
                            huestr2=stringBuilder.toString();
                            Log.e("after","expand");
                        }
                    }
                    catch (FileNotFoundException e) {
                        Log.e("login activity", "File not found: " + e.toString());
                    } catch (IOException e) {
                        Log.e("login activity", "Can not read file: " + e.toString());
                    }

                    */
                    String huestr3="";
                    String huestr3intr="";
                    File fileh4=new File(Environment.getExternalStorageDirectory().getPath().toString()+"//temp","hue3.txt");
                    /*try {
                        InputStream inputStream = new BufferedInputStream(new FileInputStream(fileh4));

                        if ( inputStream != null ) {
                            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                            String receiveString = "";
                            StringBuilder stringBuilder = new StringBuilder();

                            while ( (receiveString = bufferedReader.readLine()) != null ) {
                                stringBuilder.append(receiveString);
                            }

                            inputStream.close();
                            huestr3=stringBuilder.toString();

                        }
                    }
                    catch (FileNotFoundException e) {
                        Log.e("login activity", "File not found: " + e.toString());
                    } catch (IOException e) {
                        Log.e("login activity", "Can not read file: " + e.toString());
                    }*/
                    int size=(int)(fileh1.length()+fileh2.length()+fileh3.length()+fileh4.length());
                    OutputStream outputStream;
                    File filefinal=new File(Environment.getExternalStorageDirectory().getPath().toString()+"/saved_images","final.txt");
                    if (filefinal.exists ()) filefinal.delete ();
                    try {
                        int c,j=0;
                        int data=0;
                        char dat;
                        String finstr="";
                        StringBuilder stringBuilder = new StringBuilder();
                        byte arr1[]= new byte[(int)fileh1.length()];
                        FileInputStream in=new FileInputStream(fileh1);
                        in.read(arr1);
                        in.close();
                        byte arr2[]= new byte[(int)fileh2.length()];
                        in=new FileInputStream(fileh2);
                        in.read(arr2);
                        in.close();
                        byte arr3[]= new byte[(int)fileh3.length()];
                        in=new FileInputStream(fileh3);
                        in.read(arr3);
                        in.close();
                        byte arr4[]= new byte[(int)fileh4.length()];
                        in=new FileInputStream(fileh4);
                        in.read(arr4);
                        in.close();
                        byte[] arr = new byte[arr1.length+arr2.length+arr3.length+arr4.length];
                        System.arraycopy(arr1, 0, arr, 0, arr1.length);
                        System.arraycopy(arr2, 0, arr, arr1.length, arr2.length);
                        System.arraycopy(arr3, 0, arr, arr2.length, arr3.length);
                        System.arraycopy(arr4, 0, arr, arr3.length, arr4.length);
                        for (int i=0;i<arr.length;i++)
                        {
                            Log.e(Integer.toString(i),Byte.toString(arr[i]));

                            if (arr[i]==0x00&&!ascflag)
                            {ascflag=true;
                                continue;
                            }
                            if (arr[i]==0x01&&ascflag)
                            {ascflag=false;
                                stringBuilder.append(" ");
                                continue;
                            }
							if (arr[i]==0x02&&ascflag)
                            {
                                stringBuilder.append(", ");
                                continue;
                            }
                            if (ascflag)
                            {
                                dat=(char)arr[i];
                                stringBuilder.append(dat);
                            }
                            else {
                                if(arr[i]>= 0){
                                    data =arr[i];
                                    if(strarray[arr[i]]!=null)
                                    stringBuilder.append(strarray[arr[i]]);   //single byte is written
                                    stringBuilder.append(" ");
                                }

                                else {
                                    data=arr[i]&127;
                                    data=data<<8;
                                    data=data|32768;
                                    data+=arr[++i]&127;
                                   if(!(arr[i] >=0)){
                                       data+=128;

                                    }
                                    if (strarray[data]!=null)
                                    {stringBuilder.append(strarray[data]);
                                    stringBuilder.append(" ");}
                                    //three bytes
                                   /* else{
                                        data+=256;
                                        data=data<<8;
                                        data+=arr[++i]&255;
                                        System.out.println(data);
                                        stringBuilder.append(strarray[data]);
                                        stringBuilder.append(" ");
                                    }*/

                                }
                            }
                        }
                        finstr=stringBuilder.toString();
                        outputStream = new BufferedOutputStream(new FileOutputStream(filefinal));
                        outputStream.write(finstr.getBytes());
                        outputStream.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                     message= Message.obtain(resultHandler, R.id.zxing_decode_succeeded,barcodeResult2);
                    Bundle bundle = new Bundle();
                    message.setData(bundle);
                    message.sendToTarget();
                }
               // message = Message.obtain(resultHandler, R.id.zxing_decode_succeeded, barcodeResult2);

            }
        } else {
            if (resultHandler != null) {
                Message message = Message.obtain(resultHandler, R.id.zxing_decode_failed);
                message.sendToTarget();
            }
        }
        if (resultHandler != null) {
            List<ResultPoint> resultPoints = decoder.getPossibleResultPoints();
            Message message = Message.obtain(resultHandler, R.id.zxing_possible_result_points, resultPoints);
            message.sendToTarget();
        }
        requestNextPreview();
    }

}

