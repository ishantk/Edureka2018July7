package co.edureka.edurekajuly7.view;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.edureka.edurekajuly7.R;

import co.edureka.edurekajuly7.Util;
import co.edureka.edurekajuly7.model.Customer;

public class AddCustomerActivity extends AppCompatActivity implements View.OnClickListener{


    @BindView(R.id.editTextName)
    EditText eTxtName;

    @BindView(R.id.editTextPhone)
    EditText eTxtPhone;

    @BindView(R.id.editTextEmail)
    EditText eTxtEmail;

    @BindView(R.id.buttonAdd)
    Button btnAdd;

    Customer customer;

    ContentResolver resolver;

    ArrayList<Customer> customers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);

        ButterKnife.bind(this);

        btnAdd.setOnClickListener(this);

        customer = new Customer();

        resolver = getContentResolver();

        customers = new ArrayList<>();
    }

    void addCustomerToDB(){

        // Write ContentResolver Code to add the data to DB

        ContentValues values = new ContentValues();
        values.put(Util.COL_NAME,customer.name);
        values.put(Util.COL_PHONE,customer.phone);
        values.put(Util.COL_EMAIL,customer.email);

        Uri uri = resolver.insert(Util.CUSTOMER_URI,values);
        Toast.makeText(this,customer.name+" added !! ID: "+uri.getLastPathSegment(),Toast.LENGTH_LONG).show();

        clearFields();
    }

    void clearFields(){
        eTxtName.setText("");
        eTxtPhone.setText("");
        eTxtEmail.setText("");
    }

    void queryFromDB(){

        String[] projection = {Util.COL_ID, Util.COL_NAME, Util.COL_PHONE, Util.COL_EMAIL};
        Cursor cursor = resolver.query(Util.CUSTOMER_URI,projection,null,null,null);

        if( cursor!=null && cursor.getCount()>0) {

            while (cursor.moveToNext()) {

                Customer cRef = new Customer();
                cRef.id = cursor.getInt(cursor.getColumnIndex(Util.COL_ID));
                cRef.name = cursor.getString(cursor.getColumnIndex(Util.COL_NAME));
                cRef.phone = cursor.getString(cursor.getColumnIndex(Util.COL_PHONE));
                cRef.email = cursor.getString(cursor.getColumnIndex(Util.COL_EMAIL));

                customers.add(cRef);

                Log.i("Customer",cRef.toString());
            }


            // ListView, CustomAdapter -> Display the data in ArrayList
        }

    }

    void deleteFromDB(){

        String where = Util.COL_ID +" = 2";
        int i = resolver.delete(Util.CUSTOMER_URI,where,null);
        if(i>0){
            Toast.makeText(this,"Record Deleted !!",Toast.LENGTH_LONG).show();
            Log.i("Customer","Record Deleted at ID 2");
            queryFromDB();
        }

    }

    void updateInDB(){

        String where = Util.COL_ID +" = 1";

        ContentValues values = new ContentValues();
        values.put(Util.COL_NAME,"John Watson");
        values.put(Util.COL_PHONE,"6789678922");
        values.put(Util.COL_EMAIL,"john.w@edureka.co");

        int i = resolver.update(Util.CUSTOMER_URI,values,where,null);
        if(i>0){
            Toast.makeText(this,"Record Updated !!",Toast.LENGTH_LONG).show();
            Log.i("Customer","Record Updated at ID 2");
            queryFromDB();
        }

    }

    @Override
    public void onClick(View v) {

        /*customer.name = eTxtName.getText().toString().trim();
        customer.phone = eTxtPhone.getText().toString().trim();
        customer.email = eTxtEmail.getText().toString().trim();

        addCustomerToDB();*/

        //queryFromDB();

        //deleteFromDB();

        updateInDB();

    }
}
