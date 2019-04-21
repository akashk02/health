package arkaa.health.user.arkaahealthcare.Fragment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import arkaa.health.user.arkaahealthcare.R;

import java.util.ArrayList;

public class listViewAdapter extends ArrayAdapter<healthInfo> {



    public listViewAdapter(Context context, ArrayList<healthInfo> info) {
        super(context, 0, info);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_view_beta, parent, false);
        }

        healthInfo obj = getItem(position);

        TextView appointmentTextView = (TextView) listItemView.findViewById(R.id.appointment);
        appointmentTextView.setText(obj.getAppointment());

        TextView locationTextView = (TextView) listItemView.findViewById(R.id.location);
        locationTextView.setText(obj.getLocation());


        return listItemView;

    }


}

