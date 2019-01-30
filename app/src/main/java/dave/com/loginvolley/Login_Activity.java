package dave.com.loginvolley;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;

public class Login_Activity extends AppCompatActivity {
    Button login ;
    ProgressDialog progressDialog;
    EditText email, password ;
    String EmailHolder, PasswordHolder ;
    boolean CheckEditText ;
    String ServerLoginURL = "http://10.0.2.2/androidphp/login.php";
    public static final String UserEmail = "";
      @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);
        login = (Button)findViewById(R.id.button);
        email = (EditText) findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetCheckEditTextIsEmptyOrNot();
                if(CheckEditText){
                  loginuser(EmailHolder,PasswordHolder);
                }
                else {

                    Toast.makeText(Login_Activity.this, "Please fill all form fields.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    public void GetCheckEditTextIsEmptyOrNot(){
        EmailHolder = email.getText().toString();
        PasswordHolder = password.getText().toString();
        if(TextUtils.isEmpty(EmailHolder) || TextUtils.isEmpty(PasswordHolder))
        {
            CheckEditText = false;
        }
        else {
            CheckEditText = true ;
        }
    }

    public void loginuser(final String emailh, final String passwordh){
        progressDialog = ProgressDialog.show(Login_Activity.this,"Loading Data",null,true,true);
        RequestQueue rq=Volley.newRequestQueue(this);
        StringRequest st=new StringRequest(Request.Method.POST, ServerLoginURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
            if(response.equalsIgnoreCase("Data Matched")){
                finish();
                Intent intent = new Intent(Login_Activity.this, profile.class);
                intent.putExtra(UserEmail,emailh);
                startActivity(intent);
            }
                Toast.makeText(Login_Activity.this, ""+response, Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(Login_Activity.this, ""+error, Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email", emailh);
                params.put("password", passwordh);

                return params;
            }
        };
        rq.add(st);
    }
}
