package xudeyang.bawie.com.oc.view.recommend.hot.hotfragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.youth.banner.Banner;

import java.util.Arrays;
import java.util.List;

import xudeyang.bawie.com.oc.R;
import xudeyang.bawie.com.oc.view.recommend.Img;

/**
 * Created by Carson_Ho on 16/7/22.
 */
public class HotFragment extends Fragment {
    private View view;
    private RecyclerView rlv;
    private List<String> mListImage, mListTitle;
    List<String> list = Arrays.asList(
            "https://www.zhaoapi.cn/images/quarter/ad1.png",
            "https://www.zhaoapi.cn/images/quarter/ad2.png",
            "https://www.zhaoapi.cn/images/quarter/ad3.png",
            "https://www.zhaoapi.cn/images/quarter/ad4.png"

    );
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.hot_fragment, container, false);
        //轮播
        bannerClass();
        //网络请求
        intnight();

        into();

        return view;
    }

    private void into() {
        rlv = view.findViewById(R.id.hot_rlv);
        rlv.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void intnight() {
//        Flowable<RecHotBean> rechots = RetrofitUtil.getInstance().rechot("F8EB129296C90580807D0C6D9FD9B7F7","1");
//        rechots.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<RecHotBean>() {
//                    @Override
//                    public void accept(RecHotBean recHotBean) throws Exception {
//                        List<RecHotBean.DataBean> list = recHotBean.getData();
//                        HotMyAdapter hotMyAdapter = new HotMyAdapter(getContext(), list);
//                        rlv.setAdapter(hotMyAdapter);
//                        String msg = recHotBean.getMsg();
//                        Log.i("Hot",msg+"==========");
//                    }
//                });
    }

    private void bannerClass() {
        Banner banner = view.findViewById(R.id.banner);
        banner.setImageLoader(new Img());
        banner.setImages(list);
        banner.start();
    }

}
