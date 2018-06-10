package xudeyang.bawie.com.oc.view.recommend.hot.hotadapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import xudeyang.bawie.com.oc.R;
import xudeyang.bawie.com.oc.view.recommend.hot.hotbean.RecHotBean;

/**
 * Created by 怪胎 on 2018.6.9.
 */

public class HotMyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<RecHotBean.DataBean> list;

    public HotMyAdapter(Context context, List<RecHotBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rem_hot_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyViewHolder my = (MyViewHolder) holder;

    }

    @Override
    public int getItemCount() {
        if (list!=null){
            return list.size();
        }
            return 0;
    }
    class MyViewHolder extends RecyclerView.ViewHolder{
        private ImageView touxian;
        private ImageView shipin;
        private TextView name;
        private TextView time;
        private TextView title;
        private TextView pl1;
        private TextView pl01;
        private TextView pl2;
        private TextView pl02;


        public MyViewHolder(View itemView) {
            super(itemView);

            touxian = itemView.findViewById(R.id.img_01);
            shipin = itemView.findViewById(R.id.img_02);
            name = itemView.findViewById(R.id.hot_item_name);
            time = itemView.findViewById(R.id.hot_item_time);
            title = itemView.findViewById(R.id.hot_item_title);
            pl1 = itemView.findViewById(R.id.hot_item_pl1);
            pl01 = itemView.findViewById(R.id.hot_item_pl01);
            pl2 = itemView.findViewById(R.id.hot_item_pl2);
            pl02 = itemView.findViewById(R.id.hot_item_pl02);

        }
    }
}
