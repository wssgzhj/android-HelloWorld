package helloworld.zhj.me.helloworld.views.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import helloworld.zhj.me.helloworld.R;

/**
 * Created by zhj-plusplus on 3/13/16.
 */
public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.MyViewHolder> {

    private Context mContext;
    private int[] mOrders;
    private OrderItemClickListener mItemClickListener;

    public OrderAdapter(Context context, int[] orders) {
        mContext = context;
        mOrders = orders;
    }

    public void setOnItemClickListener(OrderItemClickListener itemClickListener)
    {
        this.mItemClickListener = itemClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                mContext).inflate(R.layout.item_order, parent,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.tvOrder.setText(String.format("order%d", mOrders[position]));
        if(mItemClickListener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItemClickListener.onItemClick(holder.itemView, position);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mItemClickListener.onItemLongClick(holder.itemView, pos);
                    return true;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mOrders.length;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvOrder;

        public MyViewHolder(View view) {
            super(view);
            tvOrder = (TextView) view.findViewById(R.id.tv_order);
        }
    }

    public interface OrderItemClickListener
    {
        void onItemClick(View view, int position);
        void onItemLongClick(View view , int position);
    }
}