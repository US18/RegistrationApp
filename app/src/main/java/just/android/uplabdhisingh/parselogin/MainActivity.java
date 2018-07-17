package just.android.uplabdhisingh.parselogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{

    EditText fullnameEditText, usernameEditText, passwordEditText, emailIdEditText;
    Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Parse.initialize(this);
        ParseInstallation.getCurrentInstallation().saveInBackground();

        fullnameEditText = (EditText) findViewById(R.id.et_fullname);
        usernameEditText = (EditText) findViewById(R.id.et_username);
        passwordEditText = (EditText) findViewById(R.id.et_password);
        emailIdEditText = (EditText) findViewById(R.id.et_email);

        registerButton = (Button) findViewById(R.id.btn_register);

        registerButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                register();
            }
        });

    }

    private void register()
    {
        ParseUser user = new ParseUser();
        user.setEmail(emailIdEditText.getText().toString());
        user.setUsername(usernameEditText.getText().toString());
        user.setPassword(passwordEditText.getText().toString());

        ParseObject parseObject = new ParseObject("Users");
        parseObject.put("username",usernameEditText.getText().toString());
        parseObject.put("password",passwordEditText.getText().toString());
        parseObject.saveInBackground();

        user.signUpInBackground(new SignUpCallback()
        {
            @Override
            public void done(ParseException e)
            {
                if (e == null)
                {
                    Toast.makeText(MainActivity.this,
                            "Congratulations " + usernameEditText.getText().toString() + "! \n You are registered!",
                            Toast.LENGTH_SHORT).show();
                } else
                {
                    Toast.makeText(MainActivity.this, "Registration Unsuccessfull!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.view_users,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        if(id == R.id.item_view)
        {
            Intent intent = new Intent(MainActivity.this, ViewUsersActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
