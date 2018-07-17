package just.android.uplabdhisingh.parselogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

import just.android.uplabdhisingh.parselogin.model.Users;

public class ViewUsersActivity extends AppCompatActivity
{
    ListView viewUsersListView;
    ArrayAdapter<Users> usernamesListAdapter;

    List<Users> usersList;

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_users);

        Parse.initialize(this);

        viewUsersListView = (ListView) findViewById(R.id.lv_users);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        usersList = new ArrayList<Users>();
        usernamesListAdapter = new ArrayAdapter<Users>(this,R.layout.users,R.id.tv_username,usersList);
        viewUsersListView.setAdapter(usernamesListAdapter);

        showUsers();
    }

    public void showUsers()
    {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Users");
        progressBar.setVisibility(View.VISIBLE);
        query.findInBackground(new FindCallback<ParseObject>()
        {
            @Override
            public void done(List<ParseObject> objects, ParseException e)
            {
                if (e==null)
                {
                    progressBar.setVisibility(View.INVISIBLE);
                    usersList.clear();
                    for (ParseObject users : objects)
                    {
                        Users users1 = new Users(users.getObjectId(),
                                users.getString("username"),
                                users.getString("password"));
                        usersList.add(users1);
                    }
                    usernamesListAdapter.notifyDataSetChanged();
                } else {
                    Log.d("ERROR MESSAGE ","ERROR : "+e.getMessage());
                }
            }
        });
    }
}
