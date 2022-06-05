package za.smartee.threesixty.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import za.smartee.threesixty.R;

public class AssetAllocationsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asset_allocations);
        String[][] testArray = {{"a","123"},{"bill","245"},{"test","246"}};
        updateButtonList(testArray);
    }

    void updateButtonList(final String[][] contactsArray){
        List<Button> contactButtonList = new ArrayList<>();
        List<String> contactIdList = new ArrayList<>();
        //gets constraint layout
        ConstraintLayout layout = findViewById(R.id.mainConstraint);
        //creates constraint set
        ConstraintSet set = new ConstraintSet();

        //loops through array
        for(int i = 0; i < contactsArray.length; i++){

            //creates string to display on button
            String buttonText = contactsArray[i][0]+": "+contactsArray[i][1];
            //creates new button and sets ID based of size of list
            Button btn = new Button(this);

            //sets layout params width as 0 so we can set to match constraint
            btn.setLayoutParams(new ConstraintLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT));

            //btn.setId(View.generateViewId());
            btn.setId(Integer.parseInt(contactsArray[i][1]));
            //sets button text from array
            btn.setText(buttonText);

            //sets color of button based of pos in list (red for even blue for odd)
//            if((contactButtonList.size()%2) == 0) {
//                btn.setBackgroundResource(R.drawable.red_button);
//            }else{
//                btn.setBackgroundResource(R.drawable.blue_button);
//            }

            //adds the button to the layout
            layout.addView(btn,contactButtonList.size());

            //sets the constraint set to match the current layout.... i think?, this needs to be done after adding the view
            set.clone(layout);

            //sets button width to match constraint
            set.constrainDefaultWidth(btn.getId(), ConstraintSet.MATCH_CONSTRAINT_WRAP);

            //connects button to left vert constraint
            set.connect(btn.getId(), ConstraintSet.LEFT, R.id.guideline19, ConstraintSet.RIGHT, 8);
            //connects button to right ver constraint
            set.connect(btn.getId(), ConstraintSet.RIGHT, R.id.guideline20, ConstraintSet.RIGHT, 8);

            //if first button attaches to horz constraint else attaches to last button
            if(contactButtonList.isEmpty()){
                set.connect(btn.getId(), ConstraintSet.TOP, R.id.guideline18, ConstraintSet.BOTTOM, 8);
            }else{
                set.connect(btn.getId(), ConstraintSet.TOP, contactButtonList.get(contactButtonList.size() - 1).getId(), ConstraintSet.BOTTOM);
            }
            //adds button to list
            contactButtonList.add(btn);
            //adds id to list
            contactIdList.add(contactsArray[i][1]);

            //apply set to layout
            set.applyTo(layout);
        }
        for(Button btn : contactButtonList){
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("S360", String.valueOf(btn.getText()));
                    Log.i("S360", String.valueOf(btn.getId()));
                    btn.setBackgroundColor(0x228B22);
                    // red 0xffff0000
                }
            });
        }

    }
}