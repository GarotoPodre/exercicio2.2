package br.com.gama.saveandrestore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text
import java.util.*

class MainActivity : AppCompatActivity() {
    private val discountButton: Button
    get()=findViewById(R.id.discount_button)

    private val firstName: EditText
    get()=findViewById(R.id.first_name)

    private val lastName: EditText
    get()=findViewById(R.id.last_name)

    private val email: EditText
    get()=findViewById(R.id.email)

    private val discountCodeConfirmation: TextView
    get()=findViewById(R.id.discount_code_confirmation)

    private val discountCode:TextView
    get()=findViewById(R.id.discount_code)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Aqui nós capturamos o evento de click do botão
        discountButton.setOnClickListener {
            val firstName=firstName.text.toString().trim()
            val lastName=lastName.text.toString().trim()
            val email=email.text.toString().trim()

            if(firstName.isEmpty() || lastName.isEmpty() || email.isEmpty()){
                Toast.makeText(this, getString(R.string.add_text_validation), Toast.LENGTH_LONG).show()
            }else{
                val fullName=firstName.plus(" ").plus(lastName)
                //gerando o código de desconto
                discountCode.text= UUID.randomUUID().toString().take(8).uppercase()

            }
        }

        Log.d(TAG, "onCreate")
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}