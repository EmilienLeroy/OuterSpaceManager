package fr.emilienleroy.outerspacemanager.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import fr.emilienleroy.outerspacemanager.ApiService;
import fr.emilienleroy.outerspacemanager.R;
import fr.emilienleroy.outerspacemanager.activity.CreateAccountActivity;
import fr.emilienleroy.outerspacemanager.activity.MainActivity;
import fr.emilienleroy.outerspacemanager.models.ApiToken;
import fr.emilienleroy.outerspacemanager.models.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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
                .baseUrl("https://outer-space-manager-staging.herokuapp.com")
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
                    mainActivity(Token);
                } else {
                    Log.e("erreur",response.toString());
                    toast();
                }
            }

            @Override
            public void onFailure(Call<ApiToken> call, Throwable t) {
            }
        });
    }

    private void toast() {
        Context context = getApplicationContext();
        CharSequence text = "User ou Pass faux";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    private void mainActivity(String token) {
        Intent intent = new Intent(getBaseContext(), MainActivity.class);
        intent.putExtra("TOKEN", token);
        startActivity(intent);
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
