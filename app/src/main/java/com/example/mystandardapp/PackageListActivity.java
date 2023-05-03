package com.example.mystandardapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class PackageListActivity extends AppCompatActivity {
    private PackageDatabaseHelper packageDbHelper;
    private ListView packageListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_package_list);

        packageListView = findViewById(R.id.package_list_view);
        packageDbHelper = new PackageDatabaseHelper(this);

        Cursor data =  packageDbHelper.getData();
        ArrayList<String> packageList = new ArrayList<>();
        while (data.moveToNext()) {
            packageList.add("Data: " + data.getString(1) + "\nSMS: " + data.getString(2));
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, packageList);
        packageListView.setAdapter(adapter);

        packageListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String packageData = ((TextView) view).getText().toString();
                Package currentPackage = packageDbHelper.getPackage(id+1);
                if(currentPackage != null) {
                    Intent intent = new Intent(PackageListActivity.this, PackageModifyActivity.class);
                    intent.putExtra("packageData", packageData);
                    intent.putExtra("packageId", currentPackage.getId());
                    startActivity(intent);
                }
                else {
                    Toast.makeText(PackageListActivity.this, "Package not found", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void goBack(View view) {
        Intent intent = new Intent(this, PackageOrderActivity.class);
        startActivity(intent);
    }
}
