package ca.iotechnology.iotrack;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;


import com.loopj.android.http.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ca.iotechnology.iotrack.domain.Merchant;

public class MainActivity extends AppCompatActivity {
    static final String[] FRUITS = new String[] { "Apple", "Avocado", "Banana",
            "Blueberry", "Coconut", "Durian", "Guava", "Kiwifruit",
            "Jackfruit", "Mango", "Olive", "Pear", "Sugar-apple" };

    ProgressDialog progressDlg;
    //ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

/*
        listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(new ArrayAdapter<>(this, R.layout.activity_list_merchants, FRUITS));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                int itemPosition     = position;

                // ListView Clicked item value
                String  itemValue    = (String) listView.getItemAtPosition(position);

                // Show Alert
                Toast.makeText(getApplicationContext(),
                        "Position :"+itemPosition+"  ListItem : " +itemValue , Toast.LENGTH_LONG)
                        .show();

            }

        });
*/
        progressDlg = new ProgressDialog(this);
        progressDlg.setMessage("Please wait while calling the rest service ...");
        progressDlg.setCancelable(false);




        /*
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void callRestService(View view){
        //Toast.makeText(getApplicationContext(), "Trying to add some elements..." , Toast.LENGTH_LONG).show();
       // LinearLayout ll = new LinearLayout(this);
       // ll.setOrientation(LinearLayout.HORIZONTAL);
        final IoTrackController  aController = (IoTrackController) getApplicationContext();
        final Context ctx = this;
        progressDlg.show();
        String url = "https://demo7675902.mockable.io/";
        AsyncHttpClient client = new AsyncHttpClient();

        client.get(url,new RequestParams(), new AsyncHttpResponseHandler(){
            public void onSuccess(int statusCode, String response){
                try{
                    aController.removeAll();
                    JSONArray merchantsArray = new JSONArray(response);
                    for (int i=0; i<merchantsArray.length(); i++){
                        JSONObject jsonMerchant = merchantsArray.getJSONObject(i);
                        String name = jsonMerchant.getString("name");
                        String description = jsonMerchant.getString("description");
                        String logoUrl = jsonMerchant.getString("logoUrl");
                        long coupons = jsonMerchant.getLong("coupons");
                        String icon = jsonMerchant.getString("icon");

                        Merchant aMerchant = new Merchant(name,description ,logoUrl, coupons, icon);
                        aController.addMerchants(aMerchant);
                    }
                    progressDlg.hide();
                    Intent intent = new Intent(ctx, ListMerchantsActivity.class);
                    startActivity(intent);
                }
                catch (JSONException e){
                    e.printStackTrace();
                }

            }
        });

    }
}
