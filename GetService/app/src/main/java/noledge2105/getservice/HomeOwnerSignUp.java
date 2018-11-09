package noledge2105.getservice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;//package for deitText
import android.widget.Button;//package for button
import android.view.View;
import android.content.Intent;
import android.widget.Toast;

public class HomeOwnerSignUp extends AppCompatActivity {

    private static Button b_finish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_owner_sign_up);
        FinishButtonListener();
    }

    public void FinishButtonListener(){
        b_finish = (Button)findViewById(R.id.btnCreate1);
        b_finish.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean error = false;

                        EditText username= (EditText)findViewById(R.id.usernameText1);
                        EditText password = (EditText)findViewById(R.id.passwordText1);
                        EditText firstName = (EditText)findViewById(R.id.firstnameText1);
                        EditText lastName = (EditText)findViewById(R.id.lastnameText1);
                        EditText dateOfBirth = (EditText)findViewById(R.id.dateOfBirthText1);
                        EditText phoneNumber = (EditText)findViewById(R.id.phoneNumberText1);

                        //Make sure that no field are empty
                        if(TextUtils.isEmpty(username.getText().toString()) ||
                                TextUtils.isEmpty(password.getText().toString()) ||
                                TextUtils.isEmpty(firstName.getText().toString()) ||
                                TextUtils.isEmpty(lastName.getText().toString()) ||
                                TextUtils.isEmpty(dateOfBirth.getText().toString()) ||
                                TextUtils.isEmpty(phoneNumber.getText().toString())
                                ) error = true;
                        //Error = true if there was a error so we don't come here
                        if(!error) {
                            Intent returnIntent = new Intent(HomeOwnerSignUp.this, MainActivity.class);
                            Bundle extras = new Bundle();

                            extras.putString("username", username.getText().toString());
                            extras.putString("password", password.getText().toString());
                            extras.putString("firstname", firstName.getText().toString());
                            extras.putString("lastname", lastName.getText().toString());
                            extras.putString("dateOfBirth", dateOfBirth.getText().toString());
                            extras.putString("phoneNumber", phoneNumber.getText().toString());
                            extras.putInt("permLevel", 2);

                            returnIntent.putExtras(extras);

                            setResult(RESULT_OK, returnIntent);

                            finish();
                        }
                        else
                            Toast.makeText(getApplicationContext(),
                                "Please make sure that no field are empty.",
                                Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
}
