package ittallaght.youplanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.microsoft.windowsazure.mobileservices.*;

import java.net.MalformedURLException;

public class LoginActivity extends AppCompatActivity {

    private MobileServiceClient mClient;

    //private MobileServiceTable<User> mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        try {
            mClient = new MobileServiceClient(
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
    }

    public class MobileServiceClient {
        public <E> MobileServiceTable<E> getTable(Class<E> clazz);
        public <E> MobileServiceTable<E> getTable(String name, Class<E> clazz);
    }


}
