package xudeyang.bawie.com.oc.view.recommend.hot.hotadapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import xudeyang.bawie.com.oc.R;
import xudeyang.bawie.com.oc.utils.GlideCircleTransform;
import xudeyang.bawie.com.oc.view.recommend.hot.hotbean.RecHotBean;

/**
 * Created by 怪胎 on 2018.6.9.
 */

public class HotMyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<RecHotBean.DataBean> list;
    private boolean bo = true;

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
        final MyViewHolder my = (MyViewHolder) holder;
        Glide.with(context)
                .load(list.get(position).getCover())
                .placeholder(R.drawable.ic_launcher_background)
                .transform(new GlideCircleTransform(context))
                .into(my.touxian);
        my.time.setText(list.get(position).getCreateTime());
        if (list.get(position).getWorkDesc()==null||list.get(position).getWorkDesc()==""){
            my.name.setText("天蝎喝牛奶");
        }else {
            my.name.setText(list.get(position).getWorkDesc());
        }
        my.title.setText(list.get(position).getUser().getNickname());
        Glide.with(context)
                .load(list.get(position).getUser().getIcon())
                .into(my.shipin);
        my.pl1.setText(list.get(position).getComments().get(0).getNickname()+":");
        my.pl01.setText(list.get(position).getComments().get(0).getContent());
        my.pl2.setText(list.get(position).getComments().get(1).getNickname()+":");
        my.pl02.setText(list.get(position).getComments().get(1).getContent());
        my.a1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                my.a1.setSelected(!my.a1.isSelected());
            }
        });
        my.a2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                my.a2.setSelected(!my.a2.isSelected());
            }
        });

        //显示隐藏侧面
        my.shipin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bo){
                    my.lin.setVisibility(View.GONE);
                    bo = false;
                }else {
                    my.lin.setVisibility(View.VISIBLE);
                    bo = true;
                }
            }
        });



     /*   my.lin

        mLinearlayout.setVisibility(View.GONE);
        mRlv.setVisibility(View.VISIBLE);*/
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

        private ImageView a1;
        private ImageView a2;
        private ImageView a3;
        private ImageView a4;
        private LinearLayout lin;

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
            a1 = itemView.findViewById(R.id.hot_item_a1);
            a2 = itemView.findViewById(R.id.hot_item_a2);
            a3 = itemView.findViewById(R.id.hot_item_a3);
            a4 = itemView.findViewById(R.id.hot_item_a4);
            lin = itemView.findViewById(R.id.linearlayout_cemian);

        }
    }
}
