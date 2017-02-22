package packages.androidclientapp.activities;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import packages.androidclientapp.R;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "packages.androidclientapp.activities.MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Resources res = getResources();

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void Login(View view) {
        Intent intent = new Intent(this, LoggedinActivity.class);
        EditText user = (EditText) findViewById(R.id.username);
        if(user.getText().length()==0) {
            //user.setError(Html.fromHtml("<font color='red'>UserName field is blank!</font>"));
            Toast.makeText(MainActivity.this,"UserName Field is blank",Toast.LENGTH_LONG).show();
            return;
        }

        EditText pass = (EditText) findViewById(R.id.password);
        if(pass.getText().length()==0) {
            //pass.setError(Html.fromHtml("<font color='red'>Password field is blank!</font>"));
            Toast.makeText(MainActivity.this,"Password Field is blank",Toast.LENGTH_LONG).show();
            return;
        }



        String message = "Hello ";
        message = message + user.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }


    public void Register(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        String message = "Insert Username and Password";
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }



}
