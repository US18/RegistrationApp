package just.android.uplabdhisingh.parselogin.model;

public class Users
{

    String userId,username,password,email;

    public Users(String userId, String username, String password)
    {
        this.userId = userId;
        this.username = username;
        this.password = password;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }
    public String getUsername()
    {
        return username;
    }
    public String getPassword()
    {
        return password;
    }
    public String getEmail()
    {
        return email;
    }

    @Override
    public String toString()
    {
        return this.getUsername();
    }
}
