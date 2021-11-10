package ipca.plantas.a234234

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class AddPlantActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_plant)

        val editTextName = findViewById<EditText>(R.id.editTextPlantName)
        val editTextLatinName = findViewById<EditText>(R.id.editTextLatinName)
        val editTextDescription = findViewById<EditText>(R.id.editTextDescription)
        val buttonDone = findViewById<Button>(R.id.buttonDone)

        buttonDone.setOnClickListener {
            val intent = intent
            intent.putExtra("plant_name", editTextName.text.toString())
            intent.putExtra("plant_latin_name", editTextLatinName.text.toString())
            intent.putExtra("plant_description", editTextDescription.text.toString())
            setResult(RESULT_OK, intent)
            finish()
        }

    }
}