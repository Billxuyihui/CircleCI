package noledge2105.getservice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class ServiceProviderSignUp extends AppCompatActivity {

    private static Button btnCreate;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_provider_sign_up);
        FinishButtonListener();
    }

    public void FinishButtonListener(){
        btnCreate = (Button)findViewById(R.id.btnCreate);
        btnCreate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Getting values, need some check up
                        boolean error = false;

                        EditText username= (EditText)findViewById(R.id.usernameText);
                        EditText password = (EditText)findViewById(R.id.passwordText);
                        EditText firstName = (EditText)findViewById(R.id.firstNameText);
                        EditText lastName = (EditText)findViewById(R.id.lastNameText);
                        EditText dateOfBirth = (EditText)findViewById(R.id.dateOfBirthText);
                        EditText phoneNumber = (EditText)findViewById(R.id.phoneText);
                        EditText serviceType = (EditText)findViewById(R.id.serviceTypeText);
                        EditText hourlyRate = (EditText)findViewById(R.id.hourlyRateText);

                        //Make sure that no field are empty
                        if(TextUtils.isEmpty(username.getText().toString()) ||
                                TextUtils.isEmpty(password.getText().toString()) ||
                                TextUtils.isEmpty(firstName.getText().toString()) ||
                                TextUtils.isEmpty(lastName.getText().toString()) ||
                                TextUtils.isEmpty(dateOfBirth.getText().toString()) ||
                                TextUtils.isEmpty(phoneNumber.getText().toString()) ||
                                TextUtils.isEmpty(serviceType.getText().toString()) ||
                                TextUtils.isEmpty(hourlyRate.getText().toString())
                                ) error = true;
                        //Look if hourly hour can be changed in double
                        try{
                            double hourly = Double.parseDouble(hourlyRate.getText().toString());
                        }
                        catch(Exception e)
                        {
                            error = true;
                        }
                        if(! username.getText().toString().equals("admin")) {
                            //Error = true if there was a error so we dont come here
                            if (!error) {
                                Intent returnIntent = new Intent(ServiceProviderSignUp.this, MainActivity.class);
                                Bundle extras = new Bundle();
                                try {
                                    extras.putString("username", username.getText().toString());
                                    extras.putString("password", password.getText().toString());
                                    extras.putString("firstname", firstName.getText().toString());
                                    extras.putString("lastname", lastName.getText().toString());
                                    extras.putString("dateOfBirth", dateOfBirth.getText().toString());
                                    extras.putString("phoneNumber", phoneNumber.getText().toString());
                                    extras.putString("serviceType", serviceType.getText().toString());
                                    extras.putDouble("hourlyRate", Double.parseDouble(hourlyRate.getText().toString()));
                                    extras.putInt("permLevel", 1);

                                    returnIntent.putExtras(extras);

                                    setResult(RESULT_OK, returnIntent);

                                    finish();
                                } catch (Exception ex) {

                                }
                            }
                            Toast.makeText(getApplicationContext(),
                                    "Please make sure that no field are empty and hourly rate is a number",
                                    Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(getApplicationContext(),
                                    "This user name is not availiable.",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
    }
}
