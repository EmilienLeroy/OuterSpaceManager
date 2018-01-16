package fr.emilienleroy.outerspacemanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class SignUpActivity extends AppCompatActivity {

    private EditText login;
    private EditText password;
    private Button connexion;
    private Button create;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        login = (EditText) findViewById(R.id.login);
        password = (EditText) findViewById(R.id.password);
        connexion = (Button) findViewById(R.id.button3);
        create = (Button) findViewById(R.id.create);

        connexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Connect(v);
            }
        });

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                create(v);
            }
        });
    }

    private void create(View v) {
        Intent myIntent = new Intent(getApplicationContext(),CreateAccountActivity.class);
        startActivity(myIntent);
    }

    private void Connect(View v) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://outer-space-manager.herokuapp.com/")
                .build();

        OuterSpaceService service = retrofit.create(OuterSpaceService.class);
    }

    public interface OuterSpaceService {
       // @GET("/api/v1/auth/login")
       //Call<List<login>> listRepos(@Path("user") String user);
    }
}
