package co.edureka.edurekajuly7;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class StudentsAdapter extends ArrayAdapter<Student>{

    Context context;
    int resource;
    ArrayList<Student> objects;

    public StudentsAdapter(Context context, int resource, ArrayList<Student> objects) {
        super(context, resource, objects);

        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }



    // getView will be executed from 0 to n-1, n is size of ArrayList
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = null;

        // 1. To create a view pointing to list_item
        view = LayoutInflater.from(context).inflate(resource,parent,false);

        ImageView imageView = view.findViewById(R.id.imageView);
        TextView txtName = view.findViewById(R.id.textViewName);
        TextView txtPhone = view.findViewById(R.id.textViewPhone);

        //2. obtain the object from ArrayList
        Student student = objects.get(position);

        //3. set the data on views
        imageView.setBackgroundResource(student.image);
        txtName.setText(student.name);
        txtPhone.setText(student.phone);

        return view;

    }
}
