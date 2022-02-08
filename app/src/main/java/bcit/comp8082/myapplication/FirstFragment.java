package bcit.comp8082.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import java.util.ArrayList;

import bcit.comp8082.myapplication.databinding.FragmentFirstBinding;
import bcit.comp8082.myapplication.models.DBHelper;
import bcit.comp8082.myapplication.models.List;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    private DBHelper db;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });

//        test_database();

    }

//    public void test_database() {
//        db = new DBHelper(getActivity());
//
//        getActivity().deleteDatabase(DBHelper.DATABASE_NAME);
//
//        List sample_list = new List(1, "Test List", "Awesome List", 1);
//        db.insertList(sample_list);
//
//        Item sample_item = new Item(1, "Test Item", "Description", 29.99, "image");
//        db.insertItem(sample_item);
//
//        ItemsList sample_items_list = new ItemsList(1, 2, 3, 4, 29.99);
//        db.insertItemsList(sample_items_list);
//
//
//        ArrayList<List> lists = db.getAllList();
//
//        StringBuilder lists_arr = new StringBuilder();
//        for (List list : lists) {
//            lists_arr.append(list.getList_id()).append(" ").append(list.getList_name()).append(" ")
//                    .append(list.getList_desc()).append("\n");
//        }
//
//        TextView lists_tv = getView().findViewById(R.id.list_array_get_test);
//        lists_tv.setText(lists_arr);
//
//
//        ArrayList<Item> items = db.getAllItem();
//        StringBuilder items_arr = new StringBuilder();
//        for (Item item : items) {
//            items_arr.append(item.getItem_name()).append(" ").append(item.getItem_desc()).append(" ")
//                    .append(item.getItem_price()).append("\n");
//        }
//
//        TextView items_tv = getView().findViewById(R.id.item_array_get_test);
//        items_tv.setText(items_arr);
//
//
//        int list_id = lists.get(0).getList_id();
//        ArrayList<ItemsList> Itemslists = db.getItemsList(list_id);
//        StringBuilder itemslist_arr = new StringBuilder();
//        for (ItemsList itemslist : Itemslists) {
//            itemslist_arr.append(itemslist.getItems_list_id()).append(" ").append(itemslist.getItems_list_list_id()).append(" ")
//                    .append(itemslist.getItems_list_backup_price()).append("\n");
//        }
//
//        TextView itemslists_tv = getView().findViewById(R.id.itemslist_array_get_test);
//        itemslists_tv.setText(itemslist_arr);
//    }

    @Override
    public void onDestroyView() {
        db.close();
        super.onDestroyView();
        binding = null;
    }

}