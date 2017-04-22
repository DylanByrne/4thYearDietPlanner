package ittallaght.youplanner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CatalogueActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private ArrayList<Food> list;

    Button bAddItem;
    DatabaseHelper myDb;
    ImageView foodImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalogue);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        myDb = new DatabaseHelper(this);

        foodImage = (ImageView) findViewById(R.id.ivFood);

        list = myDb.foodSpin();
        Spinner spinner = (Spinner) findViewById(R.id.dietSpin);

        List<String> foodNames = new ArrayList<String>();
        for (int i = 0; i < list.size(); i++)
        {
            foodNames.add(list.get(i).getfoodName());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, foodNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(this);

        bAddItem = (Button) findViewById(R.id.bAddDiet);
        addItem();
    }


    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id)
    {
        String name = parent.getItemAtPosition(pos).toString();

        //Assigns local variables for all text views

        TextView item = (TextView) findViewById(R.id.tvDietName);
        TextView calories = (TextView) findViewById(R.id.tvCalories);
        TextView ingrediants = (TextView) findViewById(R.id.tvIngredients);

        // Used to display info
        for (Food f: list)
        {

            if (f.getfoodName().equalsIgnoreCase(name))
            {

                item.setText(f.getfoodName());
                calories.setText(f.getCalories().toString());
                ingrediants.setText(f.getIngredients());

                if(f.getfoodName().toString().equals("Spicy meatballs with chilli black beans")){
                    foodImage.setImageResource(R.drawable.meatballs);
                }

                else if(f.getfoodName().toString().equals("Tandoori chicken")) {
                    foodImage.setImageResource(R.drawable.tandori);
                }

                else if(f.getfoodName().toString().equals("Coconut & squash dhansak")) {
                    foodImage.setImageResource(R.drawable.coconutsquash);
                }
                else if(f.getfoodName().toString().equals("Miso-roasted aubergine steaks with sweet potato")) {
                    foodImage.setImageResource(R.drawable.miso);
                }
                else {
                    foodImage.setImageResource(0);
                }


            }
        }
    }

    public void addItem(){
        bAddItem.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        Intent registerIntent = new Intent(CatalogueActivity.this, CatalogueForum.class);
                        CatalogueActivity.this.startActivity(registerIntent);
                    }
                }
        );
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
