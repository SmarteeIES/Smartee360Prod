package za.smartee.threesixty.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.amplifyframework.datastore.generated.model.Assets;
import com.amplifyframework.datastore.generated.model.AuditLog;
import com.amplifyframework.datastore.generated.model.Locations;
import com.amplifyframework.datastore.generated.model.ScannedAssetsAuditLog;
import com.amplifyframework.rx.RxAmplify;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;
import za.smartee.threesixty.R;

public class AssetAllocationsActivity extends AppCompatActivity {

    ArrayList<Map<String, String>> locationDetailInfo;
    ArrayList<Map<String, String>> assetDetailInfo;
    ArrayList<String> assets = new ArrayList<>();
    ArrayList<String> selectedList = new ArrayList<String>();
    List<Button> contactButtonList = new ArrayList<>();
    Integer rowCounter;
    Integer columnCounter;
    Integer buttonPressedId;
    Button addRemoveButton;
    Button saveButton;
    String selectedStore;
    Integer selectedButtonId;
    Spinner assetsDD;
    Boolean saveFlag;
    ListView allocationList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_asset_allocations);

        //counters to track rows and columns for store selection buttons
        rowCounter = 0;
        columnCounter = 0;
        saveFlag = false;

        //Initialize Buttons
        addRemoveButton = (Button) findViewById(R.id.addAsset);
        saveButton = (Button) findViewById(R.id.saveButton);

        //Initialize drop down list which shows the assets
        Spinner assetsDD = (Spinner) findViewById(R.id.assetSpinner);

        //Query Locations and create the buttons and drop down lists
        queryDbCreateViews();

        //Make selected store Not Selected to do error checking
        selectedStore = "Not Selected";

        addRemoveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modifyAssetSelection(assetsDD);
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveAllocation(selectedStore, selectedList);
            }
        });


    }

    void createButtonList(ArrayList<Map<String, String>> locations){
        //List<Button> contactButtonList = new ArrayList<>();
        List<String> contactIdList = new ArrayList<>();
        //gets constraint layout
        ConstraintLayout layout = findViewById(R.id.mainConstraint);
        //creates constraint set
        ConstraintSet set = new ConstraintSet();

        //loops through array
        for(int i = 0; i < locations.size(); i++){
            //creates string to display on button
            String buttonText = locations.get(i).get("Address");
            //creates new button and sets ID based of size of list
            Button btn = new Button(this);

            //sets layout params width as 0 so we can set to match constraint
            btn.setLayoutParams(new ConstraintLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT));

            btn.setId(View.generateViewId());
           // btn.setId(Integer.parseInt(locations.get(i).get("id")));
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
            set.constrainDefaultWidth(btn.getId(), ConstraintSet.MATCH_CONSTRAINT);

            //sets button width to match constraint
            set.constrainDefaultHeight(btn.getId(), ConstraintSet.MATCH_CONSTRAINT);


            if (columnCounter < 3) {
                columnCounter++;
            } else {
                rowCounter++;
                columnCounter = 1;
            }
            if (columnCounter == 1){
                Log.i("S360Startpt","0");
                //connects button to left vert constraint
                set.connect(btn.getId(), ConstraintSet.LEFT, R.id.guideline19, ConstraintSet.RIGHT, 8);
                //connects button to right ver constraint
                set.connect(btn.getId(), ConstraintSet.RIGHT, R.id.guideline20, ConstraintSet.RIGHT, 8);

                //if first button attaches to horz constraint else attaches to last button
                if(rowCounter == 0){
                    set.connect(btn.getId(), ConstraintSet.TOP, R.id.guideline18, ConstraintSet.BOTTOM, 8);
                }else{
                    set.connect(btn.getId(), ConstraintSet.TOP, contactButtonList.get(contactButtonList.size() - 1).getId(), ConstraintSet.BOTTOM, 100);
                }
            }
            if (columnCounter == 2){
                //connects button to left vert constraint
                set.connect(btn.getId(), ConstraintSet.LEFT, R.id.guideline21, ConstraintSet.RIGHT, 8);
                //connects button to right ver constraint
                set.connect(btn.getId(), ConstraintSet.RIGHT, R.id.guideline22, ConstraintSet.RIGHT, 8);

                //if first button attaches to horz constraint else attaches to last button
                if(rowCounter == 0){
                    set.connect(btn.getId(), ConstraintSet.TOP, R.id.guideline18, ConstraintSet.BOTTOM, 8);
                }else{
                    set.connect(btn.getId(), ConstraintSet.TOP, contactButtonList.get(contactButtonList.size() - 2).getId(), ConstraintSet.BOTTOM, 100);
                }
            }
            if (columnCounter == 3){
                //connects button to left vert constraint
                set.connect(btn.getId(), ConstraintSet.LEFT, R.id.guideline23, ConstraintSet.RIGHT, 8);
                //connects button to right ver constraint
                set.connect(btn.getId(), ConstraintSet.RIGHT, R.id.guideline24, ConstraintSet.RIGHT, 8);

                //if first button attaches to horz constraint else attaches to last button
                if(rowCounter == 0){
                    set.connect(btn.getId(), ConstraintSet.TOP, R.id.guideline18, ConstraintSet.BOTTOM, 8);
                }else{
                    set.connect(btn.getId(), ConstraintSet.TOP, contactButtonList.get(contactButtonList.size() - 3).getId(), ConstraintSet.BOTTOM, 100);
                }
            }
            btn.setBackgroundColor(Color.BLUE);

            //adds button to list
            contactButtonList.add(btn);
            //adds id to list
            contactIdList.add(locations.get(i).get("id"));

            //apply set to layout
            set.applyTo(layout);
        }
        for(Button btn : contactButtonList){
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TextView assetAllocationHeader = (TextView) findViewById(R.id.storeHeader);
                    assetAllocationHeader.setText("Allocation: " + btn.getText());
                    selectedStore = (String) btn.getText();
                    selectedButtonId = btn.getId();

                    if (buttonPressedId != null){
                        for (Button btnCheck: contactButtonList){
                            if (btnCheck.getId() == buttonPressedId){
                                btnCheck.setBackgroundColor(Color.BLUE);
                            }
                        }
                    }
                    buttonPressedId = btn.getId();
                    btn.setBackgroundColor(Color.GREEN);
                }
            });
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    void queryDbCreateViews(){
        locationDetailInfo = new ArrayList<Map<String, String>>();
        RxAmplify.DataStore.query(Locations.class)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> {
                    Log.i("S360", String.valueOf(locationDetailInfo));
                    queryAssets();
                    locationDetailInfo.sort((o1, o2) -> o1.get("Address").compareTo(o2.get("Address")));
                    createButtonList(locationDetailInfo);
                })
                .subscribe(
                        locResponse -> {
                            Map<String, String> locationDetailInfo1 = new HashMap<String, String>();
                            locationDetailInfo1.put("Address", locResponse.getAddress());
                            locationDetailInfo1.put("LocationID", locResponse.getId());
                            locationDetailInfo1.put("Longitude", locResponse.getLongitude().toString());
                            locationDetailInfo1.put("Latitude", locResponse.getLatitude().toString());
                            locationDetailInfo1.put("baseLocationType", locResponse.getBaseLocationType());
                            locationDetailInfo.add(locationDetailInfo1);
                        });
    }

    void queryAssets(){
        assetDetailInfo = new ArrayList<Map<String, String>>();
        RxAmplify.DataStore.query(Assets.class)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(()-> {

                    //Build the dropdown list for the assets which are available to be selected
                    //Initialize spinner
                    Spinner assetsDD = (Spinner) findViewById(R.id.assetSpinner);
                    assetsDD.setAdapter(new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item,assets));
                    assetsDD.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                })
                .subscribe(
                        assetResponse -> {
                            assets.add((assetResponse.getAssetName()));
                        });
    }

    void modifyAssetSelection(Spinner assetsDD){
        ArrayAdapter<String> assetSelectedListAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, selectedList);
        assetSelectedListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        final ListView allocationList = (ListView) findViewById(R.id.assetAllocateList);
        if (!saveFlag){
            String selectedAsset = assetsDD.getSelectedItem().toString();
            Boolean assetDuplicateFlag = false;
            for (String assetName: selectedList){
                if (assetName.equals(selectedAsset)){
                    assetDuplicateFlag = true;
                    Toast.makeText(this,"Asset Already Added",Toast.LENGTH_LONG).show();
                }
            }
            if (!assetDuplicateFlag){
                selectedList.add(selectedAsset);
            }


            assetSelectedListAdapter.notifyDataSetChanged();
            allocationList.setAdapter(assetSelectedListAdapter);


            allocationList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    final String item = (String) parent.getItemAtPosition(position);
                    view.animate().setDuration(2000).alpha(0)
                            .withEndAction(new Runnable() {
                                @Override
                                public void run() {selectedList.remove(item);
                                    assetSelectedListAdapter.notifyDataSetChanged();
                                    view.setAlpha(1);
                                }
                            });
                    return false;
                }
            });
        } else {
            //Clear the listview and the data for next use
            selectedList.clear();
            allocationList.setAdapter(null);
            saveFlag=false;
        }
    }


    void saveAllocation(String storeName, ArrayList<String> selectedList){
        if (storeName.equals("Not Selected")){
            Toast.makeText(this,"Save Not Completed, Please select a store",Toast.LENGTH_LONG).show();
        } else {
            for (int t = 0; t < selectedList.size(); t++){
            AuditLog assetData = AuditLog.builder()
                    .baseActionType("Asset Allocation")
                    .device(selectedList.get(t))
                    .storeName(storeName)
                    .scanTime(String.valueOf(Calendar.getInstance().getTime()))
                    .build();

            RxAmplify.DataStore.save(assetData)
                    .subscribe(
                            () ->
                            {
                                Log.i("S360","saved");
                            },
                            failure -> Log.e("S360 Failure","save Failed")
                    );
            }

            for (Button btnCheck: contactButtonList){
                if (btnCheck.getId() == selectedButtonId){
                    btnCheck.setBackgroundColor(Color.BLUE);
                }
            }
            Toast.makeText(this,"Saved Successfully",Toast.LENGTH_LONG).show();
            TextView assetAllocationHeader = (TextView) findViewById(R.id.storeHeader);
            assetAllocationHeader.setText("Select Store to Allocate");
            selectedStore = "Not Selected";
            saveFlag = true;
            // Call teh modify Assets selection to remove data from teh list view with teh Saveflag = true
            modifyAssetSelection(assetsDD);
        }

    }

}