package ittallaght.youplanner;

import android.content.Intent;
import android.icu.text.StringSearch;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.microsoft.windowsazure.mobileservices.*;
import com.microsoft.windowsazure.mobileservices.http.OkHttpClientFactory;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;
import com.squareup.okhttp.OkHttpClient;

import java.net.MalformedURLException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class LoginActivity extends AppCompatActivity {

    private MobileServiceClient mClient;
    private MobileServiceTable<User> mUserTable;


    private MobileServiceTable<LoginActivity> mToDoTable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        try {
            MobileServiceClient mClient = new MobileServiceClient(
                    "https://youplanner.azurewebsites.net",
                    this
            );
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final EditText etEmail = (EditText) findViewById(R.id.etEmail);
        final Button bLogin = (Button) findViewById(R.id.bLogin);
        final TextView registerLink = (TextView) findViewById(R.id.tvRegisterHere);
        //Link register button to open register view on click

        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(registerIntent);
            }
        });

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = etPassword.getText().toString();

                try {
                    final MobileServiceList<LoginActivity> user = mToDoTable.where().field("email").eq(email).execute().get();
                    final String emailconfirm = user.get(7).toString();
                    if(email == emailconfirm);
                    Intent registerIntent = new Intent(LoginActivity.this, DietPlanner.class);
                    LoginActivity.this.startActivity(registerIntent);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }





                Intent registerIntent = new Intent(LoginActivity.this, DietPlanner.class);
                LoginActivity.this.startActivity(registerIntent);
            }
        });




        try {
            // Create the Mobile Service Client instance, using the provided

            // Mobile Service URL and key
            mClient = new MobileServiceClient(
                    "https://youplanner.azurewebsites.net",
                    this);

            // Extend timeout from default of 10s to 20s
            mClient.setAndroidHttpClientFactory(new OkHttpClientFactory() {
                @Override
                public OkHttpClient createOkHttpClient() {
                    OkHttpClient client = new OkHttpClient();
                    client.setReadTimeout(20, TimeUnit.SECONDS);
                    client.setWriteTimeout(20, TimeUnit.SECONDS);
                    return client;
                }
            });

            mUserTable = mClient.getTable(User.class);

        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }





    }






}
