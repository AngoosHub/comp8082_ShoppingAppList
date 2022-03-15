package bcit.comp8082.myapplication.models;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

import bcit.comp8082.myapplication.ListActivity;
import bcit.comp8082.myapplication.R;

public class RecyclerItemListAdapter extends RecyclerView.Adapter<RecyclerItemListAdapter.ViewHolder> {
    Context context;
    ArrayList<ItemsList> items;
    DBHelper db;
    Activity activity;
    int db_list_id;

    public RecyclerItemListAdapter(Context context, Activity activity, ArrayList<ItemsList> items, int db_list_id) {
        this.context = context;
        this.items = items;
        this.db = new DBHelper(context);
        this.activity = activity;
        this.db_list_id = db_list_id;
    }

    @NonNull
    @Override
    public RecyclerItemListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_list, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerItemListAdapter.ViewHolder holder, int position) {
        Item item = items.get(position).getItem();
        holder.name.setText(item.getItem_name());
        holder.price.setText(String.valueOf(item.getItem_price()));
        holder.quantity.setText(String.valueOf(items.get(position).getItems_list_item_qty()));
        holder.image.setImageBitmap(BitmapFactory.decodeByteArray(item.getItem_img(), 0, item.getItem_img().length));

        holder.llRow.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("ITEM_ID", items.get(holder.getAdapterPosition()).getItem().getItem_id());
                activity.setResult(Activity.RESULT_OK, intent);
                activity.finish();
            }
        });

        holder.llRow.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(context).setTitle("Delete Item")
                        .setMessage("Are you sure to remove " + items.get(holder.getAdapterPosition()).getItem().getItem_name() + " from the list?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                db.deleteItemsList(items.get(holder.getAdapterPosition()).getItems_list_id());
                                items.remove(holder.getAdapterPosition());
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
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, price, quantity;
        ImageView image;
        LinearLayout llRow;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tvItemListName);
            price = itemView.findViewById(R.id.tvItemListPrice);
            quantity = itemView.findViewById(R.id.tvItemListQty);
            llRow = itemView.findViewById(R.id.item_list_container);
            image = itemView.findViewById(R.id.imageListView);
        }
    }
}

