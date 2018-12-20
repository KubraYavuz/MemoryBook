package com.example.user.memorybook;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class PostClass extends ArrayAdapter
{
    private final ArrayList<String> userEmail;
    private final ArrayList<String> userComment;
    private final ArrayList<String> userImage;
    private final Activity context;

    public PostClass(ArrayList<String> userEmail, ArrayList<String> userComment, ArrayList<String> userImage, Activity context) {
        super(context,R.layout.custom_view,userEmail);
        this.userEmail = userEmail;
        this.userComment = userComment;
        this.userImage = userImage;
        this.context = context;
    }


    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {
        return super.getView(position, convertView, parent);
    }
}
