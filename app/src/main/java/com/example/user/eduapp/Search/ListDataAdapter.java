package com.example.user.eduapp.Search;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.eduapp.R;
import com.example.user.eduapp.Search.DataProvider;
import com.example.user.eduapp.Search.ViewResultsActivity;

import java.util.ArrayList;
import java.util.List;

public class ListDataAdapter extends ArrayAdapter {
    List list = new ArrayList();
    Context context;
    public ListDataAdapter(Context context, int resource, final String username){
        super(context,resource);
    }

    static class LayoutHandler{
        TextView COURSENAME,L1R5,L1R4,RATING,LOCATION;
        static String username;
        Button viewResults;

    }

    public String getUsername(){
        return LayoutHandler.username;
    }

    @Override
    public void add(Object object){
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount(){
        return  list.size();
    }

    @Override
    public Object getItem(int position){
        return  list.get(position);
    }

    @Override
    public View getView(final  int position, View convertView, ViewGroup parent){

        View row = convertView;
        final LayoutHandler layoutHandler;
        if (row == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.row_layout,parent, false);
            layoutHandler = new LayoutHandler();
            layoutHandler.COURSENAME = (TextView) row.findViewById(R.id.text_coursename);
            layoutHandler.L1R4 = (TextView) row.findViewById(R.id.text_courseL1R4);
            layoutHandler.L1R5 = (TextView) row.findViewById(R.id.text_courseL1R5);
            layoutHandler.LOCATION = (TextView) row.findViewById(R.id.text_courseLocation);
            layoutHandler.RATING = (TextView) row.findViewById(R.id.text_courseRating);
            layoutHandler.viewResults = (Button)row.findViewById(R.id.button4);
            layoutHandler.viewResults.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick (View v){
                    Intent intent = new Intent(getContext(), ViewResultsActivity.class);
                    intent.putExtra("COURSENAME",layoutHandler.COURSENAME.getText().toString());
                    intent.putExtra("INSTITUTENAME",layoutHandler.LOCATION.getText().toString());
                    getContext().startActivity(intent);
                }
            });
            row.setTag(layoutHandler);
        }
        else {

            layoutHandler = (LayoutHandler)row.getTag();

        }
        DataProvider dataProvider = (DataProvider)this.getItem(position);
        layoutHandler.COURSENAME.setText(dataProvider.getCourseName());
        layoutHandler.L1R4.setText(dataProvider.getL1R4());
        layoutHandler.L1R5.setText(dataProvider.getL1R5());
        layoutHandler.LOCATION.setText(dataProvider.getLocation());
        layoutHandler.RATING.setText(dataProvider.getRating());
//        layoutHandler.viewResults.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick (View v){
//                    Toast t = Toast.makeText(getContext(), "Button clicked for list item" + position, Toast.LENGTH_LONG);
//                    t.show();
////                    Intent intent = new Intent(v.getContext(), ViewResultsActivity.class);
////                    context.startActivity(intent);
//                }
//            });

        return row;
    }

}
