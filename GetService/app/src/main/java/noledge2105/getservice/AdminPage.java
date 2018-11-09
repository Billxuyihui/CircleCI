package noledge2105.getservice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminPage extends AppCompatActivity {

    private static Button user_button;
    private static Button service_button;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);

        serviceButtonListener();




    }
    public void serviceButtonListener(){
        service_button = (Button) findViewById(R.id.serviceButton);
        service_button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(AdminPage.this, ManageServiceType.class);
                        startActivity(intent);
                    }
                }
        );

    }
}
