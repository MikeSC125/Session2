package com.example.cms2;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PostActivity extends AppCompatActivity {

    //declaring variables to be used in this class
    private ImageView upload;
    public Uri imageUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setting layout resource file for this activity
        setContentView(R.layout.activity_post);

        //declaring bundle obtaining the extras passed to it from the previous activity
        //these extras is the ID passed from the previous activity's fragment to ensure the
        //new report is added to the correct fragment category
        Bundle extras = getIntent().getExtras();
        //locating the FragID by the key passed via the bundle
        final int fragID = extras.getInt("FragID");

        //locating cancel button from layout resource file
        Button cancel = findViewById(R.id.cancelButton);
        //setting onclick listener to the above button finshing the current activity returning to the previous one
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //locating the title editText from layout resource file
        final EditText postTitle = findViewById(R.id.postTitleEditText);
        //locating the description editText from layout resource file
        final EditText postDescription = findViewById(R.id.descriptionEditText);
        //locating image view to test for image uploaded in the validation section in the if
        final ImageView imageView = findViewById(R.id.imageView);

        //locating the submit button from layout resource file
        Button submit = findViewById(R.id.submitButton);
        //setting an onclick listener to the above button
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //check for valid data
                //check for blank fields
                //check if image was uploaded
                if(postTitle.getText().toString().equals("") || postDescription.getText().toString().equals("") || imageUri==null) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Please ensure all fields are filled",Toast.LENGTH_SHORT);
                    toast.show();
                }
                else{
                    //if all is valid ...

                    //create a new instance of Item
                    Item item = new Item();
                    //set title of new instance to the text in the title editText
                    item.title = postTitle.getText().toString();
                    //set description of the new instance to the text in the description editText
                    item.description = postDescription.getText().toString();
                    //setting the image for the new instance to the image uploaded's URI converted to text
                    //this prevents us from saving an image to the database overloading the database
                    item.image = imageUri.toString();
                    //setting the category to the fragID passed from the previous fragment on the previous activity
                    item.category = fragID;

                    //using the save method, save the new instance of Item
                    item.save();

                    //close activity returning to the previous one
                    finish();
                }
            }
        });

        //locating upload imageView
        upload = findViewById(R.id.imageView);

        //setting onclick listener for the imageView
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //instantiate intent called gallery
                Intent gallery = new Intent();
                //set type of intent to image/* allowing us qto access images
                gallery.setType("image/*");
                //setting the intent action to ACTION_OPEN_DOCUMENT allowing us to read from the database leaving it open
                //for future use
                gallery.setAction(Intent.ACTION_OPEN_DOCUMENT);
                //start activity obtaining a result
                startActivityForResult(gallery, 1);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //if the request code is 1 and the result is ok and the data obtained is not null
        if(requestCode==1 && resultCode==RESULT_OK && data.getData()!= null){
            //set imageUri variable declared above with the result of the gallery intent
            imageUri = data.getData();
            //sett the image to the imageView called upload by URI
            upload.setImageURI(imageUri);
        }
    }

}

