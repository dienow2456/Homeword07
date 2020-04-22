package com.example.homeword07;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import io.bloco.faker.Faker;

public class MainActivity extends AppCompatActivity {

    List<EmailItemModel> items;
    List<EmailItemModel> items2;
    List<EmailItemModel> item3;
    Button btnSearch;
    EditText edtSearch;
    boolean isCheck = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        items = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            Faker faker= new Faker();
            items.add(new EmailItemModel(faker.name.name(), faker.lorem.sentence(), faker.lorem.paragraph(), "12:00 PM"));
        }
        final RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        final EmailItemAdapter adapter = new EmailItemAdapter(items);
        recyclerView.setAdapter(adapter);



        btnSearch = findViewById(R.id.search);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                items2=new ArrayList<>();
                for(int i=0;i<10;i++){
                    if(items.get(i).isFavorite){
                        items2.add(items.get(i));
                    }
                }
                EmailItemAdapter favoriteAdapter = new EmailItemAdapter(items2);
                if(!isCheck) {
                    recyclerView.setAdapter(favoriteAdapter);
                }
                else recyclerView.setAdapter(adapter);
                isCheck =!isCheck;
            }
        });

        edtSearch = findViewById(R.id.edit_search);

        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String charString = s.toString();
                if (count > 1) {
                    item3 = new ArrayList<>();
                    for (int i = 0; i < items.size(); i++) {

                        if (items.get(i).getName().toLowerCase().indexOf(charString) != -1 ||  items.get(i).getSubject().toLowerCase().indexOf(charString) != -1 || items.get(i).getContent().toLowerCase().indexOf(charString) != -1)
                            item3.add(items.get(i));
                    }
                    EmailItemAdapter searchAdapter = new EmailItemAdapter(item3);
                    recyclerView.setAdapter(searchAdapter);
                }
                else {
                    recyclerView.setAdapter(adapter);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

}

