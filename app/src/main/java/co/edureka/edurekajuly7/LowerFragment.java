package co.edureka.edurekajuly7;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class LowerFragment extends Fragment {


    ListView listView;
    ArrayAdapter<String> adapter;

    public LowerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lower, container, false);

        listView = view.findViewById(R.id.listView);

        adapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1);
        adapter.add("BBC");
        adapter.add("CNN");
        adapter.add("NDTV");
        adapter.add("ZEE");
        //..

        listView.setAdapter(adapter);

        return view;
    }



}
