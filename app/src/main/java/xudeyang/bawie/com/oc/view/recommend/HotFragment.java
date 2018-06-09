package xudeyang.bawie.com.oc.view.recommend;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.youth.banner.Banner;

import java.util.Arrays;
import java.util.List;

import xudeyang.bawie.com.oc.R;

/**
 * Created by Carson_Ho on 16/7/22.
 */
public class HotFragment extends Fragment {
    private View view;

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
      //  bannerClass();
        Banner banner = view.findViewById(R.id.banner);
        banner.setImageLoader(new Img());
        banner.setImages(list);
        banner.start();
        return view;
    }

}
