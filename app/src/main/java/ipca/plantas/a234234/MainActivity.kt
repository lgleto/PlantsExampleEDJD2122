package ipca.plantas.a234234

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.*

class MainActivity : AppCompatActivity() {
    // model
    var plants = arrayListOf(
        Plant("name1", "latinName1", "description1", R.mipmap.pic1),
        Plant("name2", "latinName2", "description2", R.mipmap.pic2),
        Plant("name3", "latinName3", "description3", R.mipmap.pic3)
    )

    var listviewPlants : ListView? = null
    var adapter : PlantsAdapter?  = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listviewPlants = findViewById(R.id.listviewPlants)
        adapter = PlantsAdapter()
        listviewPlants?.adapter = adapter

        val buttonAdd = findViewById<Button>(R.id.buttonAdd)
        buttonAdd.setOnClickListener {
            val intent = Intent(this@MainActivity, AddPlantActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE_ADD_PLANT)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_ADD_PLANT && resultCode == RESULT_OK) {
            data?.let{
                val name = it.getStringExtra("plant_name")?:""
                val latinName = it.getStringExtra("plant_latin_name")?:""
                val description = it.getStringExtra("plant_description")?:""

                val plant = Plant(name,
                    latinName,
                    description, R.mipmap.pic1)
                plants.add(plant)
                adapter?.notifyDataSetChanged()
            }
        }
    }

    inner class PlantsAdapter : BaseAdapter() {

        override fun getCount(): Int {
            return plants.size
        }

        override fun getItem(position: Int): Any {
            return plants[position]
        }

        override fun getItemId(position: Int): Long {
            return plants[position].id
        }

        override fun getView(position: Int, view: View?, viewGroup: ViewGroup?): View {
            var rootView = layoutInflater.inflate(R.layout.row_plant,viewGroup,false)
            val textViewPlant = rootView.findViewById<TextView>(R.id.textViewPlantName)
            val textViewPlantLatinName = rootView.findViewById<TextView>(R.id.textViewPlantLatinName)
            val imageViewPhoto = rootView.findViewById<ImageView>(R.id.imageViewPhoto)

            textViewPlant.text = plants[position].name
            textViewPlantLatinName.text = plants[position].latinName

            plants[position].imgRes?.let { imageViewPhoto.setImageResource(it) }

            rootView.isClickable = true
            rootView.setOnClickListener {
                val intent = Intent(this@MainActivity, PlantDetailActivity::class.java)
                intent.putExtra("plant_name", plants[position].name)
                intent.putExtra("plant_latin_name", plants[position].latinName)
                intent.putExtra("plant_description", plants[position].description)
                intent.putExtra("an_int", 34)
                startActivity(intent)
            }


            return rootView
        }

    }

    companion object {
        const val REQUEST_CODE_ADD_PLANT = 1001
    }
}