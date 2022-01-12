package ipca.plantas.a234234

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView

class PlantDetailActivity : AppCompatActivity() {

    lateinit var name : String
    lateinit var description : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plant_detail)

        name = intent.getStringExtra("plant_name")?:""
        val nameLatin   : String = intent.getStringExtra("plant_latin_name")?:""
        description = intent.getStringExtra("plant_description")?:""
        val anInt = intent.getIntExtra("an_int",0)

        val textViewName           = findViewById<TextView>(R.id.textViewName)
        val textViewPlantLatinName = findViewById<TextView>(R.id.textViewLatinName)
        val textViewDescription    = findViewById<TextView>(R.id.textViewDescription)

        textViewName.text = name
        textViewPlantLatinName.text = nameLatin
        textViewDescription.text = description

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.plant_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_share -> {
                val intent = Intent(Intent.ACTION_SEND)
                intent.type = "text/plain"
                intent.putExtra(Intent.EXTRA_SUBJECT,name)
                intent.putExtra(Intent.EXTRA_TEXT,description)
                startActivity(Intent.createChooser(intent, "Plants"))
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

}