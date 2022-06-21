package si.uni_lj.fe.tnuv.studybuddy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    ArrayList<Sessions> list;

    public MyAdapter(Context context, ArrayList<Sessions> list) {
        this.context = context;
        this.list = list;
    }

    public MyAdapter(HistoryFragment context) {

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.sessions_item, parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Sessions session = list.get(position);
        holder.label.setText(session.getType());
        holder.label.setText((Integer) session.getFocusTime());
        holder.label.setText((Integer) session.getIntervals());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView label, focusTime, intervals;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            label = itemView.findViewById(R.id.tvLabel);
            focusTime = itemView.findViewById(R.id.tvFocusTime);
            intervals = itemView.findViewById(R.id.tvIntervals);
        }
    }
}
