package com.andr.com.filetestdemo;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class FileList extends Activity {
    ListView fileContentList;
    List<String> fileDataList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_list);
        fileDataList = new ArrayList<String>();
        fileContentList = (ListView)findViewById(R.id.listView);
        readFromFile("testfile.txt");
        if(fileDataList.size()>0) {
            ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(FileList.this, android.R.layout.simple_list_item_1, fileDataList);
            fileContentList.setAdapter(listAdapter);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_file_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void readFromFile(String fileName){
        try {
            File myFile = new File("/sdcard/"+fileName);
            FileInputStream fIn = new FileInputStream(myFile);
            BufferedReader myReader = new BufferedReader(new InputStreamReader(fIn));
            String aDataRow = "";
            String aBuffer = "";
            while ((aDataRow = myReader.readLine()) != null) {
                fileDataList.add(aDataRow);
            }
            myReader.close();
            fIn.close();
       //     txtData.setText(aBuffer);

        }
        catch (IOException e ){
            e.printStackTrace();
        }
    }
}
