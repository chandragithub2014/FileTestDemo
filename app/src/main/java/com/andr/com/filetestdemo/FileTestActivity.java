package com.andr.com.filetestdemo;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

//http://www.tutorialspoint.com/android/android_internal_storage.htm
public class FileTestActivity extends Activity implements View.OnClickListener{
    LinearLayout stringLayout;
    Button add,write,readFileData;
    EditText initialTextField;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_test);
        stringLayout = (LinearLayout)findViewById(R.id.linear);
        add = (Button)findViewById(R.id.add);
        write = (Button)findViewById(R.id.write);
        readFileData = (Button)findViewById(R.id.readfile);
        initialTextField = (EditText)findViewById(R.id.first);
        add.setOnClickListener(this);
        write.setOnClickListener(this);
        readFileData.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_file_test, menu);
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

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.add:
                EditText editTextView = new EditText(this);
                LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,
                        LayoutParams.WRAP_CONTENT, 1);

                editTextView.setLayoutParams(params);
                editTextView.setHint("Enter Text");
                stringLayout.addView(editTextView);
                break;
            case R.id.write:
                int childCount = stringLayout.getChildCount();
                List<String> childContentList = new ArrayList<String>();
                String childContent = "";
                for(int i=0;i<childCount;i++){
                    EditText child =(EditText) stringLayout.getChildAt(i);
                    childContent=childContent+"\n"+child.getText().toString();
                    childContentList.add(child.getText().toString());
                }
                Toast.makeText(FileTestActivity.this,"ChildContent::::"+childContent,Toast.LENGTH_LONG).show();
               if(childContentList.size()>0) {
                   writeToFile("testfile.txt",childContentList);
               }

                break;
            case R.id.readfile:
                Intent fileList = new Intent(FileTestActivity.this,FileList.class);
                startActivity(fileList);
                break;
        }
    }


    private void writeToFile(String fileName,List<String> childData) {
        try {
            File myFile = new File("/sdcard/" + fileName);
            myFile.createNewFile();
            FileOutputStream fOut = new FileOutputStream(myFile);
            OutputStreamWriter myOutWriter =
                    new OutputStreamWriter(fOut);
          for(int i=0;i<childData.size();i++) {
              myOutWriter.write(childData.get(i)+"\n");

          }
            myOutWriter.flush();
            myOutWriter.close();
            fOut.close();
            Toast.makeText(getBaseContext(),
                    "Done writing SD"+fileName,
                    Toast.LENGTH_SHORT).show();
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }
}
