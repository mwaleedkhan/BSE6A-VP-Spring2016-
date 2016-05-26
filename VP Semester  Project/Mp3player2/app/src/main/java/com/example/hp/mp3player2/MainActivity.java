package com.example.hp.mp3player2;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lv;
    String[] items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv=(ListView)findViewById(android.R.id.list);
        final ArrayList<File> songs=findsongs(Environment.getExternalStorageDirectory());
        items=new String[songs.size()];
        for(int i=0;i<songs.size();i++)
        {
            toast(songs.get(i).getName().toString());
            items[i]=songs.get(i).getName().toString().replace(".mp3","").replace(".wav","");
        }
        ArrayAdapter<String> adp=new ArrayAdapter<String>(getApplicationContext(),R.layout.song_layout,R.id.textView,items);
        lv.setAdapter(adp);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(getApplicationContext(),Player.class).putExtra("pos",position).putExtra("song",songs));
            }
        });
    }
    public ArrayList<File> findsongs(File root)
    {
        ArrayList<File> al=new ArrayList<File>();
        File[] files=root.listFiles();
        for(File singleFile:files)
        {
            if(singleFile.isDirectory() && !singleFile.isHidden())
            {
                al.addAll(findsongs(singleFile));
            }
            else
            {
                if(singleFile.getName().endsWith(".mp3"))
                    al.add(singleFile);

            }
        }
        return al;
    }
    public void toast(String text)
    {
        Toast.makeText(this,text,Toast.LENGTH_SHORT);
    }
}
