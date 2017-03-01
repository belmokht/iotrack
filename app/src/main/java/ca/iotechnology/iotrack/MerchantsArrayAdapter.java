package ca.iotechnology.iotrack;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import ca.iotechnology.iotrack.domain.Merchant;
import ca.iotechnology.iotrack.utils.*;
/**
 * Created by Puneet on 16/02/2017.
 */

public class MerchantsArrayAdapter extends ArrayAdapter {

    private final Context context;
    private final ArrayList<Merchant> modelsArrayList;
    private ImageDownloader imageDownloader = new ImageDownloader();

    public MerchantsArrayAdapter(Context context, ArrayList<Merchant> modelsArrayList){
        super(context, R.layout.list_item, modelsArrayList);

        this.context = context;
        this.modelsArrayList = modelsArrayList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // 1. Create inflater
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // 2. Get rowView from inflater

        View rowView = null;
        rowView = inflater.inflate(R.layout.list_item, parent, false);

        // 3. Get icon,title & counter views from the rowView
        ImageView imgView = (ImageView) rowView.findViewById(R.id.item_icon);
        TextView titleView = (TextView) rowView.findViewById(R.id.item_title);
        TextView counterView = (TextView) rowView.findViewById(R.id.item_counter);

        // 4. Set the text for textView
        imageDownloader.download("http://iotechnology.ca/img/icons/subway.png", imgView);
        //imgView.setImageURI(Uri.parse("http://iotechnology.ca/img/icons/subway.png"));
        titleView.setText(modelsArrayList.get(position).getName());
        counterView.setText(""+modelsArrayList.get(position).getNumberOfCoupons());
        // 5. retrn rowView
        return rowView;
    }

}
