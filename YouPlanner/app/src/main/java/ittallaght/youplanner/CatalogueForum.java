package ittallaght.youplanner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class CatalogueForum extends AppCompatActivity {

    Button bAdd;
    EditText etFoodName, etCalories, etIngrediant;
    CheckBox vegetarian;
    String type = "";
    DatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalogue_forum);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        myDB = new DatabaseHelper(this);
        bAdd = (Button) findViewById(R.id.bAddDiet);
        vegetarian = (CheckBox) findViewById(R.id.cbType);
        etFoodName = (EditText) findViewById(R.id.etFoodName);
        etCalories = (EditText) findViewById(R.id.etCalories);
        etIngrediant = (EditText) findViewById(R.id.etIngrediants);

        AddData();
    }


    public void AddData(){
        bAdd.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {

                        if(etFoodName.getText().toString().equals("") || etIngrediant.getText().toString().equals("") || etCalories.getText().toString().equals("")){
                            Toast.makeText(CatalogueForum.this, "All Fields Required", Toast.LENGTH_LONG).show();
                        }

                        else{
                            boolean pass = myDB.uniqueFood(etFoodName.getText().toString());

                            if(pass == true){

                                if(vegetarian.isChecked()){
                                    type = "Veg";
                                }

                                else{
                                    type = "Meat";
                                }

                                boolean isInserted = myDB.insertFood(
                                        etFoodName.getText().toString(),
                                        etCalories.getText().toString(),
                                        type,
                                        etIngrediant.getText().toString()
                                );

                                if (isInserted == true) {
                                    Intent registerIntent = new Intent(CatalogueForum.this, CatalogueActivity.class);
                                    CatalogueForum.this.startActivity(registerIntent);
                                    Toast.makeText(CatalogueForum.this, "Item Inserted", Toast.LENGTH_LONG).show();

                                } else {
                                    Toast.makeText(CatalogueForum.this, "Data Failed to Insert", Toast.LENGTH_LONG).show();
                                }
                            }
                            else {
                                Toast.makeText(CatalogueForum.this, "Name Matches Item in Catalogue", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                }
        );
    }

}
