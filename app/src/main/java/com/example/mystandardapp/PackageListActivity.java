package com.example.mystandardapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;

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
            packageList.add("Adat: " + data.getString(1) + "\nSMS: " + data.getString(2) + "\nTelefonszám: " + data.getString(3));
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, packageList);
        packageListView.setAdapter(adapter);

        packageListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String packageData = ((TextView) view).getText().toString();
                Cursor cursor = packageDbHelper.getData();
                cursor.moveToPosition(position);
                int packageId = cursor.getInt(cursor.getColumnIndexOrThrow("_id"));
                if(packageId != -1) {
                    Intent intent = new Intent(PackageListActivity.this, PackageModifyActivity.class);
                    intent.putExtra("packageData", packageData);
                    intent.putExtra("packageId", packageId);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(PackageListActivity.this, "Nem találjuk a csomagot", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Cursor data = packageDbHelper.getData();
        ArrayList<String> packageList = new ArrayList<>();
        while (data.moveToNext()) {
            packageList.add("Adat: " + data.getString(1) + "\nSMS: " + data.getString(2) + "\nTelefonszám: " + data.getString(3));
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, packageList);
        packageListView.setAdapter(adapter);
    }

    public void goBack(View view) {
        Intent intent = new Intent(this, PackageOrderActivity.class);
        startActivity(intent);
    }
}
