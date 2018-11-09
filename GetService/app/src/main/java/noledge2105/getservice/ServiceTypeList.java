package noledge2105.getservice;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ServiceTypeList extends ArrayAdapter<ServiceType> {
    private Activity context;
    List<ServiceType> services;

    public ServiceTypeList(Activity context, List<ServiceType> services) {
        super(context, R.layout.activity_services_list, services);
        this.context = context;
        this.services = services;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.activity_services_list, null, true);

        TextView textViewName = (TextView) listViewItem.findViewById(R.id.serviceType);
        TextView textViewPrice = (TextView) listViewItem.findViewById(R.id.hourlyRate);

        ServiceType service = services.get(position);
        textViewName.setText(service.getService());
        textViewPrice.setText(String.valueOf(service.getHourlyRate()));
        return listViewItem;
    }

}
