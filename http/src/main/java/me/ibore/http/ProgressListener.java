package me.ibore.http;


/**
 * description: OKHttp进度回调类
 * author: Ibore Xie
 * date: 2018-01-19 00:04
 * website: ibore.me
 */

public interface ProgressListener {

    void onProgress(ProgressInfo progressInfo);

}