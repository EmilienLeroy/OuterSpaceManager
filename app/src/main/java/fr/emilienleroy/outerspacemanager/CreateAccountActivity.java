package fr.emilienleroy.outerspacemanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CreateAccountActivity extends AppCompatActivity {

    private EditText name;
    private EditText password;
    private EditText mail;
    private Button register;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        password = (EditText) findViewById(R.id.Pass);
        name = (EditText) findViewById(R.id.Nom);
        mail = (EditText) findViewById(R.id.mail);
        register = (Button) findViewById(R.id.button2);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Connect(v);
            }
        });
    }

    private void Connect(View v) {
        User user = new User(name.getText().toString(),password.getText().toString(),mail.getText().toString());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://outer-space-manager.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService Create_User = retrofit.create(ApiService.class);
        Call<ApiToken> repo = Create_User.createUser(user);
        repo.enqueue(new Callback<ApiToken>() {
            @Override
            public void onResponse(Call<ApiToken> call, Response<ApiToken> response) {
                Log.v("sdfs",response.toString());
                if (response.isSuccessful()) {
                    System.out.print(response.body().getToken());
                    Log.e("sfg", "onResponse: ok");
                } else {
                    Log.e("sfg", "onResponse: erruer");
                }
            }

            @Override
            public void onFailure(Call<ApiToken> call, Throwable t) {
                System.out.print(t);
            }
        });
    }
}
