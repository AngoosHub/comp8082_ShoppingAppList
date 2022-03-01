package bcit.comp8082.myapplication.models;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import bcit.comp8082.myapplication.ListActivity;
import bcit.comp8082.myapplication.R;
import bcit.comp8082.myapplication.SavedItemList;

public class RecyclerListAdapter extends RecyclerView.Adapter<RecyclerListAdapter.ViewHolder> {
    Context context;
    ArrayList<List> lists;
    DBHelper db;
    String username;
    String password;

    public RecyclerListAdapter(Context context, ArrayList<List> lists, String username, String password) {
        this.context = context;
        this.lists = lists;
        this.db = new DBHelper(context);
        this.username = username;
        this.password = password;
    }

    @NonNull
    @Override
    public RecyclerListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.list, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerListAdapter.ViewHolder holder, int position) {
        List list = lists.get(position);
        holder.name.setText(list.getList_name());
        holder.desc.setText(String.valueOf(list.getList_desc()));
        holder.date.setText(String.valueOf(list.getList_datetime_as_string()));

        holder.llRow.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ListActivity.class);
                intent.putExtra("LIST_ID", list.getList_id());
                intent.putExtra("LIST_NAME", list.getList_name());
                intent.putExtra("username", username);
                intent.putExtra("password", password);
                v.getContext().startActivity(intent);
            }
        });
        holder.llRow.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(context).setTitle("Delete List")
                        .setMessage("Are you sure to delete " + lists.get(holder.getAdapterPosition()).getList_name() + " from the list?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                db.deleteList(lists.get(holder.getAdapterPosition()).getList_id());
                                lists.remove(holder.getAdapterPosition());
                                notifyItemRemoved(holder.getAdapterPosition());
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                builder.show();
                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, desc, date;
        LinearLayout llRow;
        public ViewHolder(@NonNull View listView) {
            super(listView);
            name = listView.findViewById(R.id.tvListName);
            desc = listView.findViewById(R.id.tvListDesc);
            date = listView.findViewById(R.id.tvListDate);
            llRow = listView.findViewById(R.id.list_container);
        }
    }
}

