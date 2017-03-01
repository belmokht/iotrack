package ca.iotechnology.iotrack;

import android.app.Application;

import java.util.ArrayList;

import ca.iotechnology.iotrack.domain.Merchant;


/**
 * Created by Puneet on 16/02/2017.
 */

public class IoTrackController extends Application {

    ArrayList<Merchant> merchants = new ArrayList<Merchant>();

    public ArrayList<Merchant> getMerchants() {
        return merchants;
    }

    public void setMerchants(ArrayList<Merchant> merchants) {
        this.merchants = merchants;
    }

    public void addMerchants(Merchant merchant) {
        this.merchants.add(merchant);
    }

    public void removeAll(){
        merchants = new ArrayList<Merchant>();
    }
}
