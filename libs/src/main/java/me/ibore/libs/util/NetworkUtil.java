package me.ibore.libs.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;

/**
 * description:
 * author: Ibore Xie
 * date: 2018-01-19 00:05
 * website: ibore.me
 */

public class NetworkUtil {


    private NetworkUtil() { }

    public static final int NONE  = 0x00;
    public static final int WIFI  = 0x01;
    public static final int CMWAP = 0x02;
    public static final int CMNET = 0x03;

    /**
     * 网络是否可用
     */
    public static boolean isNetworkAvailable(@NonNull Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(
                Context.CONNECTIVITY_SERVICE);
        if (null == manager) {
            return false;
        }
        NetworkInfo[] info = manager.getAllNetworkInfo();
        if (info != null) {
            for (NetworkInfo anInfo : info) {
                if (anInfo.getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 判断是否有网络连接
     */
    public static boolean isNetworkConnected(@NonNull Context context) {
        ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(
                Context.CONNECTIVITY_SERVICE);
        NetworkInfo mNetworkInfo = null;
        if (mConnectivityManager != null) {
            mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
        }
        return mNetworkInfo != null && mNetworkInfo.isAvailable();
    }

    /**
     * 判断WIFI网络是否可用
     */
    public static boolean isWifiConnected(@NonNull Context context) {
        ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(
                Context.CONNECTIVITY_SERVICE);
        NetworkInfo mWiFiNetworkInfo = null;
        if (mConnectivityManager != null) {
            mWiFiNetworkInfo = mConnectivityManager.getNetworkInfo(
                    ConnectivityManager.TYPE_WIFI);
        }
        return mWiFiNetworkInfo != null && mWiFiNetworkInfo.isAvailable();
    }

    /**
     * 判断MOBILE网络是否可用
     */
    public static boolean isMobileConnected(@NonNull Context context) {
        ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(
                Context.CONNECTIVITY_SERVICE);
        NetworkInfo mMobileNetworkInfo = null;
        if (mConnectivityManager != null) {
            mMobileNetworkInfo = mConnectivityManager.getNetworkInfo(
                    ConnectivityManager.TYPE_MOBILE);
        }
        return mMobileNetworkInfo != null && mMobileNetworkInfo.isAvailable();
    }

    /**
     * 获取当前的网络连接类型 0：没有网络 1：WIFI网络2：wap 网络3：net网络
     */
    public static int getAPNType(@NonNull Context context) {
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(
                Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;
        if (connMgr != null) {
            networkInfo = connMgr.getActiveNetworkInfo();
        }
        if (networkInfo == null) {
            return NONE;
        }
        int nType = networkInfo.getType();

        if (nType == ConnectivityManager.TYPE_MOBILE) {
            if ("cmnet".equals(networkInfo.getExtraInfo().toLowerCase())) {
                return CMNET;
            } else {
                return CMWAP;
            }
        } else if (nType == ConnectivityManager.TYPE_WIFI) {
            return WIFI;
        }
        return NONE;
    }
}