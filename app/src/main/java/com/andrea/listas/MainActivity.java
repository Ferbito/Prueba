package com.andrea.listas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private final String TAG = getClass().getSimpleName();

    private ListView lv;
    private ArrayList<String> mNames;
    private boolean mListSimple = false;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = findViewById(R.id.lista);

        mNames = new ArrayList<String>();
        mNames.add("Android");
        mNames.add("iPhone");
        mNames.add("PUB");
        mNames.add("Nokia");
        mNames.add("Samsung");
        mNames.add("PUB");
        mNames.add("Toshiba");
        mNames.add("Motorola");
        mNames.add("PUB");
        mNames.add("Huawei");
        mNames.add("Xiaomi");
        mNames.add("PUB");
        mNames.add("Oppo");
        mNames.add("OnePlus");
        mNames.add("PUB");
        mNames.add("ESPECIAL");

        if (mListSimple) {
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mNames);
            lv.setAdapter(adapter);
        } else {
            myAdapter = new MyAdapter(this, R.layout.list_icon, mNames);
            lv.setAdapter(myAdapter);
        }

        if(mListSimple == false){
            lv.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
                @Override
                public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
                    contextMenu.add(0,1,1,"Borrado");
                    contextMenu.add(0,2,0,"Añadir");
                }
            });
        }

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "Has pulsado: " + mNames.get(position), Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()){
            case 1:
                mNames.remove(info.position);
                Toast.makeText(MainActivity.this,"Borrado", Toast.LENGTH_SHORT).show();
                myAdapter.notifyDataSetChanged();
                break;
            case 2:
                mNames.add(info.position + 1,"Juan XXIII");
                myAdapter.notifyDataSetChanged();
                break;
        }
        return true;
    }

    public class MyAdapter extends BaseAdapter{
        Integer i = 0;
        private Context context;
        private int layout;
        private ArrayList<String> names;

        public MyAdapter(Context context, int layout, ArrayList<String> names) {
            this.context = context;
            this.layout = layout;
            this.names = names;
        }

        @Override
        public int getCount() {
            return this.names.size();
        }

        @Override
        public Object getItem(int position) {
            return this.names.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {
            View v = view;


            LayoutInflater layoutInflater = LayoutInflater.from(this.context);

            if(names.get(position).contains("PUB")){
                v= layoutInflater.inflate(R.layout.banner_rojo,null);
            }else if(names.get(position).contains("ESPECIAL")){
                v=layoutInflater.inflate(R.layout.especial,null);
            }
            else{

                v = layoutInflater.inflate(R.layout.list_icon, null);
                TextView text = (TextView) v.findViewById(R.id.texto);
                text.setText(names.get(position));
                i++;
            }

            return v;
        }
    }



    public class NuevoAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            return null;
        }
    }
}
