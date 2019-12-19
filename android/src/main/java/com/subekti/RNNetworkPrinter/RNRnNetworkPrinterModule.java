
package com.subekti.RNNetworkPrinter;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import java.net.*;
import java.io.*;
import javax.annotation.Nullable;
import android.util.Base64;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.facebook.react.bridge.*;
import com.subekti.RNNetworkPrinter.sdk.Command;
import com.subekti.RNNetworkPrinter.sdk.PrintPicture;
import com.subekti.RNNetworkPrinter.sdk.PrinterCommand;

public class RNRnNetworkPrinterModule extends ReactContextBaseJavaModule {
  public static final int WIDTH_58 = 384;
  public static final int WIDTH_80 = 576;
  private int deviceWidth = WIDTH_58;
  private final ReactApplicationContext reactContext;

  public RNRnNetworkPrinterModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
  }

  @Override
  public String getName() {
    return "RNRnNetworkPrinter";
  }

  @ReactMethod
  public void PrintText(String ip, String text, Callback cb) {
    try {
      Socket sock = new Socket(ip, 9100);
      DataOutputStream out = new DataOutputStream(sock.getOutputStream());

      String encoding = "GBK";
      int codepage = 0;
      int widthTimes = 0;
      int heigthTimes = 0;
      int fonttype = 0;
      String toPrint = text;

      byte[] bytes = PrinterCommand.POS_Print_Text(toPrint, encoding, codepage, widthTimes, heigthTimes, fonttype);
      out.write(PrinterCommand.POS_S_Align(1));
      out.write(bytes);
      out.write(PrinterCommand.POS_Set_PrtAndFeedPaper(30));
      out.write(Command.ESC_Init);
      sock.close();
      cb.invoke(null, "Success");
    } catch (Exception e) {
      cb.invoke(e.toString(), null);
    }
  }

  @ReactMethod
  public void PrintPic(String ip, String base64Image, @Nullable ReadableMap options, Callback cb) {
    try {
      Socket sock = new Socket(ip, 9100);
      DataOutputStream out = new DataOutputStream(sock.getOutputStream());
      int width = 0;
      int leftPadding = 0;
      if (options != null) {
        width = options.hasKey("width") ? options.getInt("width") : 0;
        leftPadding = options.hasKey("left") ? options.getInt("left") : 0;
      }

      // cannot larger then devicesWith;
      if (width > deviceWidth || width == 0) {
        width = deviceWidth;
      }

      byte[] bytes = Base64.decode(base64Image, Base64.DEFAULT);
      Bitmap mBitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
      int nMode = 0;
      if (mBitmap != null) {
        /**
         * Parameters: mBitmap 要打印的图片 nWidth 打印宽度（58和80） nMode 打印模式 Returns: byte[]
         */
        byte[] data = PrintPicture.POS_PrintBMP(mBitmap, width, nMode, leftPadding);

        out.write(Command.ESC_Init);
        out.write(Command.LF);
        out.write(PrinterCommand.POS_S_Align(1));
        out.write(data);
        out.write(PrinterCommand.POS_Set_PrtAndFeedPaper(30));
        out.write(Command.ESC_Init);
        cb.invoke(null, "Success");
      }
    } catch (Exception e) {
      cb.invoke(e.toString(), null);
    }

  }
  
  @ReactMethod
  public void CutPaper(String ip, Callback cb) {
    try {
      Socket sock = new Socket(ip, 9100);
      DataOutputStream out = new DataOutputStream(sock.getOutputStream());
        out.write(PrinterCommand.POS_Set_Cut(1));
        cb.invoke(null, "Success");
 
    } catch (Exception e) {
      cb.invoke(e.toString(), null);
    }
  }

}
