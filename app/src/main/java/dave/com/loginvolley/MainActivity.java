package dave.com.loginvolley;
import android.app.ProgressDialog;
import android.content.Intent;
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

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    ProgressDialog progressDialog;
    Button register , login ;
    EditText name, email , password ;
    String RegisterURL = "http://10.0.2.2/androidphp/register.php" ;
    Boolean CheckEditText ;
    String Response;
      String NameHolder, EmailHolder, PasswordHolder ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        register = (Button)findViewById(R.id.button);
        login = (Button)findViewById(R.id.button2);
        name = (EditText)findViewById(R.id.name);
        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetCheckEditTextIsEmptyOrNot();
                if(CheckEditText){
                  registerUser(NameHolder,EmailHolder,PasswordHolder);
                }
                else {
                    Toast.makeText(MainActivity.this, "Please fill all form fields.", Toast.LENGTH_LONG).show();
                }
            }
        });
       login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Login_Activity.class);
                startActivity(intent);

            }
        });
    }

    public void GetCheckEditTextIsEmptyOrNot(){
        NameHolder = name.getText().toString();
        EmailHolder = email.getText().toString();
        PasswordHolder = password.getText().toString();
        if(TextUtils.isEmpty(NameHolder) || TextUtils.isEmpty(EmailHolder) || TextUtils.isEmpty(PasswordHolder))
        {
            CheckEditText = false;
        }
        else {

            CheckEditText = true ;
        }
    }

    public void registerUser(final String name,final String email, final String password){
        progressDialog = ProgressDialog.show(MainActivity.this,"Loading Data",null,true,true);
        RequestQueue rq=Volley.newRequestQueue(this);
      /*  StringRequest st=new StringRequest(Request.Method.POST, RegisterURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(MainActivity.this, ""+response, Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this, ""+error, Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("name", name);
                params.put("email", email);
                params.put("password", password);

                return params;
            }
        };
        rq.add(st);*/

     StringRequest st=new StringRequest(Request.Method.POST, RegisterURL, new Response.Listener<String>() {
         @Override
         public void onResponse(String response) {

         }
     }, new Response.ErrorListener() {
         @Override
         public void onErrorResponse(VolleyError error) {

         }
     }){
         @Override
         protected Map<String, String> getParams() throws AuthFailureError {
             return super.getParams();
         }
     };

    }

}
