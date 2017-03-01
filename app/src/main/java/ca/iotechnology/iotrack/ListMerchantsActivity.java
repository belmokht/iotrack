package ca.iotechnology.iotrack;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

import ca.iotechnology.iotrack.domain.Merchant;


public class ListMerchantsActivity extends ListActivity {
    //static final String[] FRUITS = new String[] { "Apple", "Avocado", "Banana","Blueberry", "Coconut", "Durian", "Guava", "Kiwifruit", "Jackfruit", "Mango", "Olive", "Pear", "Sugar-apple" };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_merchants);
        final IoTrackController  aController = (IoTrackController) getApplicationContext();
        ArrayList<Merchant> merchantsArray = aController.getMerchants();
        ArrayList merchantsNames = new ArrayList();
        Iterator iter = merchantsArray.iterator();

        while (iter.hasNext()){
            Merchant aMerchant = (Merchant)iter.next();
            merchantsNames.add(aMerchant.getName());
        }
        //setListAdapter(new ArrayAdapter<>(this, R.layout.array_adapter, merchantsNames));
        setListAdapter(new MerchantsArrayAdapter(this, merchantsArray));
        //lookupMerchants();

        ListView listView = getListView();
        listView.setTextFilterEnabled(true);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                RelativeLayout layout = (RelativeLayout)view;
                Toast.makeText(getApplicationContext(),
                        ((TextView) layout.findViewById(R.id.item_title)).getText(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void lookupMerchants(){
        String url = "https://demo7675902.mockable.io/hhactivesession/v1/1234567";
        AsyncHttpClient client = new AsyncHttpClient();

        client.get(url,new RequestParams(), new AsyncHttpResponseHandler(){
            public void onSuccess(int statusCode, String response){
                try{
                    JSONObject json = new JSONObject(response);
                    String usage = json.getString("DataUsage");
                    String[] merchants = new String[] {usage};


                    Toast.makeText(getApplicationContext(), "Data USage : "+usage , Toast.LENGTH_LONG).show();
                }
                catch (JSONException e){
                    e.printStackTrace();
                }

            }
        });
    }

}
