package packages.androidclientapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import packages.androidclientapp.R;

public class RegisterActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "packages.androidclientapp.activities.RegisterActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }


    public void Submit(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        EditText user = (EditText) findViewById(R.id.register_username);
        if(user.getText().length()==0) {
            //user.setError(Html.fromHtml("<font color='red'>UserName field is blank!</font>"));
            Toast.makeText(RegisterActivity.this, "UserName Field is blank", Toast.LENGTH_LONG).show();
            return;
        }

        EditText pass = (EditText) findViewById(R.id.register_password);
        if(pass.getText().length()==0) {
            //pass.setError(Html.fromHtml("<font color='red'>Password field is blank!</font>"));
            Toast.makeText(RegisterActivity.this,"Password Field is blank",Toast.LENGTH_LONG).show();
            return;
        }


        String message="";
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }



}
