package fr.emilienleroy.outerspacemanager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class SignUpActivity extends AppCompatActivity {

    private EditText login;
    private EditText password;
    private Button connexion;
    private Button create;
    SharedPreferences save;
    private String Token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        save = getSharedPreferences("PREFS", MODE_PRIVATE);
        Token = loadtoken();
        Log.e("Token",Token);

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
        User user = new User(login.getText().toString(),password.getText().toString());
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://outer-space-manager.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService service = retrofit.create(ApiService.class);
        Call<ApiToken> repo = service.login(user);
        repo.enqueue(new Callback<ApiToken>() {
            @Override
            public void onResponse(Call<ApiToken> call, Response<ApiToken> response) {
                if (response.isSuccessful()) {
                    Log.e("token",response.body().getToken());
                    savetoken(response.body().getToken());
                    Token = response.body().getToken();
                    Log.e("OK", "onResponse: ok");
                } else {
                    Log.e("erreur",response.toString());
                }
            }

            @Override
            public void onFailure(Call<ApiToken> call, Throwable t) {
            }
        });
    }

    private String loadtoken() {
        String token = save.getString("token", null);
        return token;
    }

    private void savetoken(String token) {
        SharedPreferences.Editor editor = save.edit();
        editor.putString("token", token);
        editor.commit();
    }
}
