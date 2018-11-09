package noledge2105.getservice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class WelcomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);

        Intent i = getIntent();
        UserAccount userAccount = (UserAccount)i.getSerializableExtra("UserAccount");

        TextView welcome = (TextView)findViewById(R.id.welcome_tv);
        TextView firstName = (TextView)findViewById(R.id.firstName_tv2);
        TextView lastName = (TextView)findViewById(R.id.lastName_tv2);
        TextView dateOfBirth = (TextView)findViewById(R.id.dateOfBirth_tv2);
        TextView phone = (TextView)findViewById(R.id.phoneNumb_tv2);
        TextView username = (TextView)findViewById(R.id.userName_tv2);



        firstName.setText(userAccount.getFirstName());
        lastName.setText(userAccount.getLastName());
        dateOfBirth.setText(userAccount.getDateOfBirth());
        phone.setText(userAccount.getPhoneNumber());
        username.setText(userAccount.getUserName());

        welcome.setText("Welcome " + userAccount.getFirstName()+ ". You are logged as " +
                ((userAccount.getPermLevel()==0)?"administrator.":
                (userAccount.getPermLevel()==1)?"service provider":"home owner"));

        //Trying to do a sign out button

        /*
        Button button_signout = (Button)findViewById(R.id.button_signout);

        button_signout.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(WelcomePage.this,MainActivity.class);
                        startActivityForResult(intent,0);
                    }
                }
        );
        */

    }
}
