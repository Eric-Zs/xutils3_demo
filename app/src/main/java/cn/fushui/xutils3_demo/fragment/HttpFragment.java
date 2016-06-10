package cn.fushui.xutils3_demo.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.xutils.common.Callback;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.x;

import java.io.File;

import cn.fushui.xutils3_demo.R;

/**
 * Created by jianfeng on 2016/6/8.
 */
@ContentView(R.layout.http_fragment)
public class HttpFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return x.view().inject(this, inflater, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Event(value = R.id.id_bt_get)
    private void get(View v) {

        final ProgressDialog dialog = new ProgressDialog(getActivity());

        dialog.setMessage("请稍候...");

        dialog.show();
        String url = "http://www.jikexueyuan.com/";
        RequestParams entity = new RequestParams(url);

        //在url中添加参数
        entity.addQueryStringParameter("username", "haha");
        entity.addQueryStringParameter("password", "123");

        Log.i("entity", entity.toString());
        x.http().get(entity, new Callback.CommonCallback<String>() {
            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            //不管请求是否成功,都会被调用,相单与finally
            @Override
            public void onFinished() {
                dialog.cancel();
            }

            @Override
            public void onSuccess(String result) {
                Log.i("tag", result);
            }
        });
    }

    @Event(value = R.id.id_bt_post)
    private void post(View v) {

        final ProgressDialog dialog = new ProgressDialog(getActivity());

        dialog.setMessage("请稍候...");

        dialog.show();
        String url = "http://www.jikexueyuan.com/";
        RequestParams entity = new RequestParams(url);

        //添加到请求的body中
        entity.addBodyParameter("username", "haha");
        //根据请求方式,决定添加到url后还是body中
        entity.addParameter("password", "123");

        entity.addHeader("head", "jike");

        Log.i("entity", entity.toString());
        x.http().post(entity, new Callback.CommonCallback<String>() {
            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            //不管请求是否成功,都会被调用,相单与finally
            @Override
            public void onFinished() {
                dialog.cancel();
            }

            @Override
            public void onSuccess(String result) {
                Log.i("tag", result);
            }
        });
    }

    @Event(value = R.id.id_bt_other)
    private void other(View v) {


        String url = "http://www.jikexueyuan.com/";
        RequestParams entity = new RequestParams(url);

        //添加到请求的body中
        entity.addParameter("username", "haha");


        Log.i("entity", entity.toString());
        x.http().request(HttpMethod.PUT, entity, new Callback.CommonCallback<String>() {
            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            //不管请求是否成功,都会被调用,相单与finally
            @Override
            public void onFinished() {

            }

            @Override
            public void onSuccess(String result) {
                Log.i("tag", result);
            }
        });
    }

    @Event(R.id.id_bt_upload)
    private void upload(View v) {


        String url = "http://www.jikexueyuan.com/";
        String path = Environment.getExternalStorageDirectory() + "/jikeapp/" + "com.jikexueyuan.geekacademy_4.2.0-4f71632_421.apk";


        RequestParams entity = new RequestParams(url);
        entity.setMultipart(true);
        //添加到请求的body中
        entity.addParameter("file", new File(path));


        Log.i("entity", entity.toString());
        x.http().post(entity, new Callback.CommonCallback<String>() {
            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            //不管请求是否成功,都会被调用,相单与finally
            @Override
            public void onFinished() {

            }

            @Override
            public void onSuccess(String result) {
                Log.i("tag", result);
            }
        });
    }


    @Event(R.id.id_bt_download)
    private void download(View v) {


        String url;
        String path = "";
         url = "http://124.193.230.157/dd.myapp.com/16891/28F3DE528CE9DAE149E2D39A26BB94CA.apk?mkey=5735c90b910ce983&f=8e5d&c=0&fsname=com.jikexueyuan.geekacademy_4.2.0-4f71632_421.apk&p=.apk";
     //   url = "http://a.hiphotos.bdimg.com/wisegame/pic/item/abfcc3cec3fdfc03923bb57ed33f8794a5c226b0.jpg";
        RequestParams entity = new RequestParams(url);

        //设置保存路径
        entity.setSaveFilePath(Environment.getExternalStorageDirectory() + "/jikeapp/");
        //自动获取文件名
        entity.setAutoRename(true);


        x.http().post(entity, new Callback.ProgressCallback<File>() {
            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onSuccess(File result) {
                //安装应用
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.fromFile(result), "application/vnd.android.package-archive");
                getActivity().startActivity(intent);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            //不管请求是否成功,都会被调用,相单与finally
            @Override
            public void onFinished() {

            }

            @Override
            public void onWaiting() {

            }

            @Override
            public void onStarted() {

            }

            @Override
            public void onLoading(long total, long current, boolean isDownloading) {
                Log.i("loadapp", "current<<" + current + " total<<" + total);
            }
        });
    }

    @Event(R.id.id_bt_cache)
    private void cache(View v) {


        String url = "http://www.jikexueyuan.com/";
        RequestParams params = new RequestParams(url);
        params.setCacheMaxAge(1000 * 60);
        Callback.Cancelable cancelable = x.http().get(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.i("tag", "onSuccess<<" + result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }

            @Override
            public boolean onCache(String result) {
                //false 不相信本地缓存 重新发送请求
                //true 相信本地缓存   在特定时间内不再发送请求
                Log.i("tag", "cache<<" + result);
                return true;
            }
        });
    }
}
