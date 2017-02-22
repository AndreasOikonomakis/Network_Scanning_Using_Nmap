package packages.androidclientapp.customAdapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import packages.androidclientapp.R;


public class Adapter extends ArrayAdapter<Contents> {

    Context context;
    int layoutResourceId;
    Contents data[] = null;


    public Adapter(Context context, int layoutResourceId, Contents[] data){

        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ContentHolder holder;

        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new ContentHolder();

            holder.title = (TextView)row.findViewById(R.id.txtTitle);

            row.setTag(holder);
        }
        else
        {
            holder = (ContentHolder)row.getTag();
        }

        Contents con = data[position];
        holder.title.setText(con.title);

        return row;
    }




    static class ContentHolder{
        TextView title;

    }



}
