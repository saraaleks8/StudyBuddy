package si.uni_lj.fe.tnuv.studybuddy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    ArrayList<Sessions> list = new ArrayList<>();

    public MyAdapter(Context context) {
        this.context = context;
    }

    public MyAdapter(HistoryFragment context) {

    }

    public void refreshItems(ArrayList<Sessions> list){
        this.list = list;
        notifyDataSetChanged();
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
        holder.focusTime.setText(""+ session.getFocusTime());
        holder.intervals.setText(""+ session.getIntervals());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView label, focusTime, intervals;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            label = itemView.findViewById(R.id.type);
            focusTime = itemView.findViewById(R.id.focusTime);
            intervals = itemView.findViewById(R.id.intervals);
        }
    }
}
