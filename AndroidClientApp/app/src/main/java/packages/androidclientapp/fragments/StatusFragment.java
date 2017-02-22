package packages.androidclientapp.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import packages.androidclientapp.activities.LoggedinActivity;
import packages.androidclientapp.R;




public class StatusFragment extends Fragment {

    private TableLayout tableLayout;
    private int row_insert_position=0;
    public StatusFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((LoggedinActivity) getActivity()).setActionBarTitle(R.string.statusfragment);

        View view = inflater.inflate(R.layout.fragment_status, container, false);

        tableLayout=(TableLayout) view.findViewById(R.id.table_client_status);

        //TODO for testing purposes
        Button addclientbutton = (Button) view.findViewById(R.id.addclientbutton);
        addclientbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddClient(v,"123456789");
            }
        });

        //TODO for testing purposes
        Button setonline = (Button) view.findViewById(R.id.setClientOnlinebutton);
        setonline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AcceptClient(1);
            }
        });
        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
    public void AcceptClient( int position) {
//        View row =tableLayout.getChildAt(position);
//        if (row instanceof TableRow) {
//            ;//todo
//            }
//        }
//        return;
    }
    public void DeclineClient(View view ,int position) {

    }
    /*
    * AddClient Method each time called adds a client to the table
    * @param View the view in which client will be added
    * must be a view from StatusFragment
    * @param String the id of the client that needs to be added
    * */
    public void AddClient(View v,String client_id ){
        //todo ? needed final??
        final TableRow tableRow = new TableRow(v.getContext());
        //a tag for each row so we know which button was pressed
        //index row_insert_position begins with 0
        tableRow.setTag(row_insert_position++);

        TableLayout.LayoutParams tableRowParams=
                new TableLayout.LayoutParams
                        (TableLayout.LayoutParams.WRAP_CONTENT,TableLayout.LayoutParams.WRAP_CONTENT);
        int leftMargin=5;
        int topMargin=2;
        int rightMargin=5;
        int bottomMargin=2;
        tableRowParams.setMargins(leftMargin, topMargin, rightMargin, bottomMargin);

        TextView t1 = new TextView(v.getContext());
        //default status is offline until administrator accepts
        ImageView t2= new ImageView(v.getContext());
        t2.setImageResource(R.mipmap.ic_offline);

        Button t3 = new Button(v.getContext());
        t3.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //todo get id malaka
                TextView textView = (TextView) tableRow.getChildAt(0);
                String clientid = textView.getText().toString();
                System.out.println("MOLIS PATHSES TO KOUMPI Accept"+tableRow.getTag() +" "+clientid );
            }
        });
        Button t4 = new Button(v.getContext());
        t4.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //todo get id malaka
                TextView textView = (TextView) tableRow.getChildAt(0);
                String clientid = textView.getText().toString();
                System.out.println("MOLIS PATHSES TO KOUMPI Decline"+tableRow.getTag()+" " +clientid);
            }
        });
        // methods applied to buttons and textviews
        // for better table visualization
        t1.setText(client_id+row_insert_position);
        t3.setText(R.string.table_clients_accept_button);
        t4.setText(R.string.table_clients_decline_button);

        t1.setGravity(Gravity.CENTER_HORIZONTAL);
        t3.setGravity(Gravity.CENTER_HORIZONTAL);
        t4.setGravity(Gravity.CENTER_HORIZONTAL);

        t1.setTextSize(18);
        t3.setTextSize(15);
        t4.setTextSize(15);

        tableRow.setLayoutParams(tableRowParams);
        tableRow.addView(t1);
        tableRow.addView(t2);
        tableRow.addView(t3);
        tableRow.addView(t4);
        tableLayout.addView(tableRow);

        return;
    }
}
