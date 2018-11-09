package noledge2105.getservice;

import android.accounts.Account;
import android.app.Service;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;//package for deitText
import android.widget.Button;//package for button
import android.view.View;
import android.content.Intent;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MainActivity extends AppCompatActivity {

    //All the final variable
    private static final String DATABASE_NAME = "serviceDB";

    //Table USER ACCOUNT
    private static final String TABLE_USERACCOUNT = "userAccount";
    private static final String COL_USERID = "userID";
    private static final String COL_USERNAME = "username";
    private static final String COL_PASSWORD = "password";
    private static final String COL_FIRSTNAME = "firstname";
    private static final String COL_LASTNAME = "lastname";
    private static final String COL_BIRTH = "dateOfBirth";
    private static final String COL_PHONE = "phoneNumber";
    private static final String COL_PERMLVL = "permLevel";

    //Table SERVICE PROVIDER
    private static final String TABLE_SERVICEPROVIDER = "serviceProvider";
    private static final String COL_SPID = "serviceProviderID";
    private static final String COL_SERVICETYPE = "serviceType";
    private static final String COL_HOURLYRATE = "hourlyRate";

    private static Button button_login;
    private static Button button_signup;
    private static Button button_signup1;

    //Because do not have time to do Firebase AUTH, we get all user
    List<UserAccount> userAccounts = new ArrayList<UserAccount>();
    List<ServiceProvider> serviceProviders= new ArrayList<ServiceProvider>();

    DatabaseReference databaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        databaseUser = FirebaseDatabase.getInstance().getReference(DATABASE_NAME);

        setContentView(R.layout.activity_main);
        callListener();
    }

    @Override
    protected void onStart(){
        super.onStart();

        //Listener for the table USER
        databaseUser.child(TABLE_USERACCOUNT).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                userAccounts.clear();
                try {
                    for (DataSnapshot postSnapShot : dataSnapshot.getChildren()) {
                        UserAccount userAccount = postSnapShot.getValue(UserAccount.class);
                        userAccounts.add(userAccount);
                    }
                }catch(Exception ea) {
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //Listener for the table SERVICE PROVIDER
        databaseUser.child(TABLE_SERVICEPROVIDER).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               // serviceProviders.clear();
               /*  for(DataSnapshot postSnapShot : dataSnapshot.getChildren()){
                   String userID = postSnapShot.child(COL_USERID).getValue(String.class);
                    for(UserAccount ua : userAccounts)
                    {
                        String tmpID = ua.getUserID().substring(0,userID.length()-1);
                        if(tmpID.equals(userID))//Load the good account to service provider
                        {
                            //Get the values
                            double hourlyRate = postSnapShot.child(COL_HOURLYRATE).getValue(Double.class);
                            String serviceType = postSnapShot.child(COL_SERVICETYPE).getValue(String.class);
                            String serviceTypeID = postSnapShot.child(COL_SPID).getValue(String.class);

                            //Create a service provider
                            ServiceProvider spTMP = new ServiceProvider(ua.getUserName(),
                                    ua.getUserID(),
                                    ua.getPassword(),
                                    ua.getFirstName(),
                                    ua.getLastName(),
                                    ua.getDateOfBirth(),
                                    ua.getPhoneNumber(),
                                    ua.getPermLevel(),
                                    serviceTypeID,
                                    serviceType,
                                    hourlyRate);
                            //Add the service provider
                            serviceProviders.add(new ServiceProvider());
                        }
                    }

                }*/
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    /**
     * Do different thing if the user sign up as provider or home owner
     * @param requestCode
     * @param resultCode
     * @param data2
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data2) {
        if (resultCode == RESULT_CANCELED) return;
        {
            Bundle data = data2.getExtras();//Receive the value of the bundle
            int permLevel = data.getInt(COL_PERMLVL,-1);

            Toast.makeText(getApplicationContext(), "Perm level :" + permLevel, Toast.LENGTH_LONG).show();
            //If there is a problem with anything
            if(permLevel == -1){
            }
            //If it is a Service Provider that try to sign up
            else if(permLevel == 1){
                ServiceProvider serviceProviderTMP;
                //Create the new service provider from the data.extra
                try {
                        serviceProviderTMP = new ServiceProvider(data.getString(COL_USERNAME),
                                "",
                                data.getString(COL_PASSWORD),
                                data.getString(COL_FIRSTNAME),
                                data.getString(COL_LASTNAME),
                                data.getString(COL_BIRTH),
                                data.getString(COL_PHONE),
                                1,
                                "",
                                data.getString(COL_SERVICETYPE),
                                Double.valueOf(data.getDouble(COL_HOURLYRATE, -1)));

                        //Insert the service provider in the data base
                        ServiceProvider serviceProvider = addServiceProvider(serviceProviderTMP);
                        String myID = serviceProvider.getServiceProviderID();

                        if(myID == "")//Mean there is an error, error code can be found in AccountDBHandler.addServiceProvider();
                            Toast.makeText(getApplicationContext(), "Something went wrong.\n Please try again!\n"
                                    + myID, Toast.LENGTH_LONG).show();
                        else {

                            EditText userMessage = (EditText) findViewById(R.id.usernameTextMain);
                            userMessage.setText(serviceProvider.getUserName());

                            EditText passwordMessage = (EditText) findViewById(R.id.passwordTextMain);
                            passwordMessage.setText(serviceProvider.getPassword());
                        }
                }
                catch(Exception ex){
                    Toast.makeText(getApplicationContext(),
                            "Something went wrong.\n Please try again!\n",
                            Toast.LENGTH_LONG).show();
                }


            }//When its from the Home owner sign up
            else if(permLevel == 2){
                //Create a user account with the data coming from the owner signup
                UserAccount userAccountTMP = new UserAccount(  data.getString(COL_USERNAME),
                        "",
                        data.getString(COL_PASSWORD),
                        data.getString(COL_FIRSTNAME),
                        data.getString(COL_LASTNAME),
                        data.getString(COL_BIRTH),
                        data.getString(COL_PHONE),
                        2);

                //Insert the home owner in the database
                UserAccount userAccount = addUserAccount(userAccountTMP);
                String myID = userAccount.getUserID(); //myDBHandler.addAccount(userAccount);

                if(myID == "")//Mean there is an error, error code can be found in AccountDBHandler.addServiceProvider();
                    Toast.makeText(getApplicationContext(), "Something went wrong.\n Please try again!\n"
                            + myID + "\n" +
                            userAccount.getUserName()  + "\n" +
                            userAccount.getPassword()  + "\n" +
                            userAccount.getFirstName()  + "\n" +
                            userAccount.getLastName()  + "\n" +
                            userAccount.getDateOfBirth()  + "\n" +
                            userAccount.getPhoneNumber()
                            , Toast.LENGTH_LONG).show();
                else {

                    EditText userMessage = (EditText) findViewById(R.id.usernameTextMain);
                    userMessage.setText(userAccount.getUserName());

                    EditText passwordMessage = (EditText) findViewById(R.id.passwordTextMain);
                    passwordMessage.setText(userAccount.getPassword());

                    Toast.makeText(getApplicationContext(), "Congrats, your ID is : "
                            + userAccount.getUserID()+ "\n" +
                            userAccount.getUserName()  + "\n" +
                            userAccount.getPassword()  + "\n" +
                            userAccount.getFirstName()  + "\n" +
                            userAccount.getLastName()  + "\n" +
                            userAccount.getDateOfBirth()  + "\n" +
                            userAccount.getPhoneNumber()
                            , Toast.LENGTH_LONG).show();
                }
            }
        }
    }
    public void LoginButtonListener(){
        button_login = (Button)findViewById(R.id.btnSignIn);
        button_login.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                }
        );
    }
    /**
     * =================================== LISTENERS ======================================
     */

    public void callListener(){
        createOwnerListener();
        createProvierListener();
        signInListener();
    }
    //Listener for the create owner button
    public void createOwnerListener(){
        button_signup = (Button)findViewById(R.id.btnCreateOwner);
        button_signup.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this,HomeOwnerSignUp.class);
                        startActivityForResult(intent,0);
                    }
                }
        );

    }

    //Listener for Create provider button
    public void createProvierListener(){
        button_signup1 = (Button)findViewById(R.id.btnCreateProvider);
        button_signup1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this,ServiceProviderSignUp.class);
                        startActivityForResult(intent,0);
                    }
                }
        );
    }

    public void signInListener(){
        Button btnSignIn = (Button)findViewById(R.id.btnSignIn);
        btnSignIn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v){
                        tryConnection();
                    }
        }
        );
    }

    /**
     * =================================== Data base handler ======================================
     */
    /**
    /**
     * Add a service provider by calling addUserAcount; to generate an ID and return that ID to put in
     * the table ServiceProvider
     * @param serviceProvider
     * @return Service Provider
     */
    private ServiceProvider addServiceProvider(ServiceProvider serviceProvider)
    {
        UserAccount tmpAcc = new UserAccount(   serviceProvider.getUserName(),
                serviceProvider.getUserID(),
                serviceProvider.getPassword(),
                serviceProvider.getFirstName(),
                serviceProvider.getLastName(),
                serviceProvider.getDateOfBirth(),
                serviceProvider.getPhoneNumber(),
                serviceProvider.getPermLevel()
        );
        //Create a userAccount to get the ID related to it
        UserAccount tmp2 = addUserAccount(tmpAcc);
        serviceProvider.setUserID(tmp2.getUserID());

        //Insert service provider if there is an ID(meaning there was a user created)
        if(tmp2.getUserID() != "")
        {
            //Put the service type
            serviceProvider.setServiceProviderID(databaseUser.child(TABLE_SERVICEPROVIDER).push().getKey());
            databaseUser.child(TABLE_SERVICEPROVIDER).child(serviceProvider.getServiceProviderID()).
                    child(COL_SERVICETYPE).setValue(serviceProvider.getServiceType());

            //Put the hourly rate
            databaseUser.child(TABLE_SERVICEPROVIDER).child(serviceProvider.getServiceProviderID()).
                    child(COL_HOURLYRATE).setValue(serviceProvider.getHourlyRate());

            //Put the user ID
            databaseUser.child(TABLE_SERVICEPROVIDER).child(serviceProvider.getServiceProviderID()).
                    child(COL_USERID).setValue(serviceProvider.getUserID());

        }
        else
            Toast.makeText(this,"Something went wrong, please try again!",Toast.LENGTH_LONG).show();
        return(new ServiceProvider(serviceProvider));
    }

    /**
     * Method to add a user
     * @param userAccount
     * @return userAccount
     */
    private UserAccount addUserAccount(UserAccount userAccount) {
        userAccount.setUserID(databaseUser.child(TABLE_USERACCOUNT).push().getKey());
        databaseUser.child(TABLE_USERACCOUNT).child(userAccount.getUserID()).setValue(userAccount);

        if(userAccount.getUserID() =="")
            Toast.makeText(this, "Something went wrong, please try again!", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this, "Account created!", Toast.LENGTH_LONG).show();
        return(new UserAccount(userAccount));
    }

    /**
     * Method to connect a user, has to be changed for something with AUTH instead of having a List
     * @return UserAccount
     */
    private void tryConnection(){
        String username = ((EditText)findViewById(R.id.usernameTextMain)).getText().toString();
        String password = ((EditText)findViewById(R.id.passwordTextMain)).getText().toString();

        //Toast.makeText(this,"Searching for "+ username + " " + password,Toast.LENGTH_LONG).show();

        if(username.equals("admin") && password.equals("admin")) {
            Intent intent = new Intent(MainActivity.this,AdminPage.class);
            startActivity(intent);
        }

        else {
            UserAccount theUser = new UserAccount();
            theUser.setUserID("");//Make sure the ID not initialised

            ListIterator<UserAccount> iter = null;
            iter = userAccounts.listIterator();

            //Loop until there is an ID or ssomeone in the database, still need to make sure we cant insert twice the same username and password combination
            for (UserAccount ua : userAccounts) {
                if (ua.getPassword().equals(password) && ua.getUserName().equals(username) && theUser.getUserID().equals("")) {
                    theUser = new UserAccount(ua);
                }
            }

            //Cant find the user
            if (theUser.getUserID() == "")
                Toast.makeText(this, "Wrong username or password.\nPlease try again!", Toast.LENGTH_LONG).show();
            else {//Have found the user
                int permLevel = theUser.getPermLevel();
                if (permLevel == 1)
                    Toast.makeText(this, "Welcome Service Provider.\nUserID " + theUser.getUserID() + " : ", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(this, "Welcome Home Owner.\nUserID " + theUser.getUserID(), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this, WelcomePage.class);
                intent.putExtra("UserAccount", theUser);
                startActivity(intent);
            }

        }
    }
}

