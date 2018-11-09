package noledge2105.getservice;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;

import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.TextView;
import android.widget.Spinner;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.security.cert.PolicyNode;
import java.util.List;
import java.util.ArrayList;




public class ManageServiceType extends AppCompatActivity {

    EditText editTextService;
    EditText editTextHourlyRate;
    Button buttonAddService;
    ListView listViewServices;
    DatabaseReference databaseServices;

    List<ServiceType> services;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_manage_service_type);


        editTextService = (EditText) findViewById(R.id.serviceType);
        editTextHourlyRate= (EditText) findViewById(R.id.hourlyRate);
        listViewServices = (ListView) findViewById(R.id.listViewServices);
        buttonAddService = (Button) findViewById(R.id.addButton);

        services = new ArrayList<>();


        buttonAddService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addService();
            }
        });

        listViewServices.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                ServiceType serv = services.get(i);
                showUpdateDeleteDialog(serv.getId(), serv.getService());
                return true;
            }
        });
        databaseServices=FirebaseDatabase.getInstance().getReference("services");
    }


    @Override
    protected void onStart() {

        super.onStart();
        //attaching value event listener
        databaseServices.addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(DataSnapshot dataSnapshot){
                //Clearing the previous artis list
                services.clear();
                //iterating through all the nodes
                for(DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    //getting product
                    ServiceType serv = postSnapshot.getValue(ServiceType.class);
                    //adding product to the list
                    services.add(serv);
                }

                //creating adapter
                ServiceTypeList servicesAdapter = new ServiceTypeList(ManageServiceType.this, services);
                //attaching adapter to the listview
                listViewServices.setAdapter(servicesAdapter);
            }
            @Override
            public void onCancelled(DatabaseError databaseError){

            }
        });



    }


    private void showUpdateDeleteDialog(final String Id, String Name) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.update_dialog, null);
        dialogBuilder.setView(dialogView);

        final EditText editName = (EditText) dialogView.findViewById(R.id.editName);
        final EditText editRate  = (EditText) dialogView.findViewById(R.id.editRate);
        final Button buttonUpdate = (Button) dialogView.findViewById(R.id.buttonUpdateService);
        final Button buttonDelete = (Button) dialogView.findViewById(R.id.buttonDeleteService);

        dialogBuilder.setTitle(Name);
        final AlertDialog b = dialogBuilder.create();
        b.show();

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editName.getText().toString().trim();
                double rate = Double.parseDouble(String.valueOf(editRate.getText().toString()));
                if (!TextUtils.isEmpty(name)) {
                    updateService(Id, name, rate);
                    b.dismiss();
                }
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteService(Id);
                b.dismiss();
            }
        });
    }

    private void updateService(String id, String name, double rate) {
        //getting the specified product reference
        DatabaseReference dRef = FirebaseDatabase.getInstance().getReference("services").child(id);
        //updating product
        ServiceType service = new ServiceType(id, name, rate);
        dRef.setValue(service);

        Toast.makeText(getApplicationContext(), "Service type Updated", Toast.LENGTH_LONG).show();
    }

    private boolean deleteService(String id) {
        //getting the specified product reference
        DatabaseReference dRef = FirebaseDatabase.getInstance().getReference("services").child(id);
        //removing product
        dRef.removeValue();
        Toast.makeText(getApplicationContext(), "Service type Deleted", Toast.LENGTH_LONG).show();
        return true;
    }

    private void addService() {
        //getting the values to save
        String name = editTextService.getText().toString().trim();
        double rate = Double.parseDouble(String.valueOf(editTextHourlyRate.getText().toString()));

        //checking if the value is provided
        if (!TextUtils.isEmpty(name)){
            //getting a unique id using push().getKey() method
            // it will create a unique id and we will use it as the Primary Key for our Product
            String id = databaseServices.push().getKey();

            //creating a Product Object
            ServiceType serv = new ServiceType(id, name, rate);

            //Saving the Product
            databaseServices.child(id).setValue(serv);

            //setting edittext to blank again
            editTextService.setText("");
            editTextHourlyRate.setText("");

            //displaying a success toast
            Toast.makeText(this, "Service type added", Toast.LENGTH_LONG).show();

        }else{
            // if the value is not given displaying a toast
            Toast.makeText(this, "Please enter a service name", Toast.LENGTH_LONG).show();
        }


    }
}