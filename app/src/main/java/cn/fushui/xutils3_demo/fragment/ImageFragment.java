package cn.fushui.xutils3_demo.fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import org.xutils.common.Callback;
import org.xutils.image.ImageOptions;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;

import cn.fushui.xutils3_demo.R;

/**
 * Created by jianfeng on 2016/6/8.
 */
@ContentView(R.layout.image_fragment)
public class ImageFragment extends Fragment {

    @ViewInject(R.id.id_iv_01)
    ImageView iv1;
    @ViewInject(R.id.id_iv_02)
    ImageView iv2;
    @ViewInject(R.id.id_iv_03)
    ImageView iv3;
    @ViewInject(R.id.id_iv_04)
    ImageView iv4;
    @ViewInject(R.id.id_iv_05)
    ImageView iv5;
    String urls[] = { "http://img4.duitang.com/uploads/item/201209/03/20120903121445_nx5wk.jpeg",
            "http://i7.qhimg.com/t01755f436ab31acc3e.jpg",
            "http://www.dianziyan668.com/images/mnsg4lteovuxiylom4xgg33n/uploads/item/201203/11/20120311204006_GraP2.jpeg",
            "http://img4.imgtn.bdimg.com/it/u=2141450888,3610011306&fm=11&gp=0.jpg",
            "http://img3.duitang.com/uploads/item/201509/20/20150920214014_dYSRj.jpeg",
            "http://p2.gexing.com/G1/M00/85/40/rBACFFPoibzDeIc6AAC_zw6Zw1Y995_600x.jpg"};
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return x.view().inject(this,inflater,container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setPic();
    }

    private void setPic() {
        //设置圆形
        ImageOptions options1 = new ImageOptions.Builder()
                .setCircular(true)
                .build();

        //设置方形
        ImageOptions options2 = new ImageOptions.Builder()
                .setSquare(true)
                .build();

        //设置大小
        ImageOptions options3 = new ImageOptions.Builder()
                .setCrop(true)
                .setSize(200, 200)
                .build();
        //四种方法
        x.image().bind(iv1,urls[0], options1);
        x.image().bind(iv2,urls[1],options2);
        x.image().bind(iv3, urls[2], new Callback.CommonCallback<Drawable>() {
            @Override
            public void onSuccess(Drawable result) {

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
        });

        x.image().bind(iv4, urls[3], options3, new Callback.CommonCallback<Drawable>() {
            @Override
            public void onSuccess(Drawable result) {

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
        });

        //加载图片,不用传入ImageView
        Callback.Cancelable cancelable = x.image().loadDrawable(urls[2], options1, new Callback.CommonCallback<Drawable>() {
            @Override
            public void onSuccess(Drawable result) {
                iv5.setImageDrawable(result);
            }

            @Override
            public void onFinished() {

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }
        });

        //使用bind和loadDrawable后会在本地留下图片
        //在loadFile的onCache就可以拿到该图片
        x.image().loadFile(urls[0], options1, new Callback.CacheCallback<File>() {
            @Override
            public boolean onCache(File result) {
                //图片另存为 等操作
                Log.i("fushui01","file<<"+result.getPath()+result.getName());
                return true;
            }

            @Override
            public void onSuccess(File result) {
                Log.i("fushui01","file");
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
        });

    }
}
