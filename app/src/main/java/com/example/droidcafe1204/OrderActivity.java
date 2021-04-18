package com.example.droidcafe1204;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.droidcafe1204.databinding.ActivityOrderBinding;

public class OrderActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private ActivityOrderBinding binding;
    private String spinnerLabel;
    public Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOrderBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);



        Intent intent = getIntent();
        String message= "Order: " + intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        binding.orderTextview.setText(message);
        spinner = (Spinner) findViewById(R.id.label_spinner);

        if (spinner != null){ spinner.setOnItemSelectedListener(this); }

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.labels_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        if (spinner != null){ spinner.setAdapter(adapter);}

    }

    public void onRadioButtonClicked(View view) {
        Boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()){
            case R.id.sameday:
                if (checked)
                    displayToast(getString(R.string.same_day_messenger_service));
                break;
            case R.id.nextday:
                if (checked)
                    displayToast(getString(R.string.next_day_ground_delivery));
                break;
            case R.id.pickup:
                if (checked)
                    displayToast(getString(R.string.pick_up));
                break;
        }
    }



    public void displayToast(String message){
        Toast.makeText(getApplicationContext(), message,
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        spinnerLabel = parent.getItemAtPosition(position).toString();
        displayToast(spinnerLabel);

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}