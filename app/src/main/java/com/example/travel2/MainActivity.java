package com.example.travel2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity {

   private FirebaseDatabase mFirebaseDatabase;
   private DatabaseReference mDatabaseReference;
   EditText txtTitle;
    EditText txtPrice;
    EditText txtDescription;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference =  mFirebaseDatabase.getReference().child("traveldeals");

        txtTitle = (EditText)findViewById(R.id.txtTitle);
        txtPrice = (EditText)findViewById(R.id.txtPrice);
        txtDescription = (EditText)findViewById(R.id.txtDescription);

    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item) {
        switch(item.getItemId()){
            case R.id.save_menu:
                saveDeal();
                Toast.makeText(this, "Deal saved",Toast.LENGTH_LONG).show();
                clean();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.save_menu, menu);
        return true;
    }
    private void saveDeal(){
        String title = txtTitle.getText().toString();
        String price = txtPrice.getText().toString();
        String description = txtDescription.getText().toString();
        TravelDeal deal = new TravelDeal(title, description, price,  "");
        mDatabaseReference.push().setValue(deal);

    }
    private void clean(){
        txtTitle.setText("");
        txtDescription.setText("");
        txtPrice.setText("");
        txtTitle.requestFocus();

    }
}
