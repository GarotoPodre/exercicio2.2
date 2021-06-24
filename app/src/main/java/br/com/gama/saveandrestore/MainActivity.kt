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
                discountCodeConfirmation.text=getString(R.string.discount_code_confirmation,fullName)
                //gerando o código de desconto
                discountCode.text= UUID.randomUUID().toString().take(8).uppercase()

            }
        }

        Log.d(TAG, "onCreate")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.d(TAG, "onRestoreInstanceState")

        //(2) na restauração, recupero os valores das constantes previamentes savas em (1)
        //Pega o código de desconto ou uma string vazia se ela não foi definida
        discountCode.text= savedInstanceState.getString(DISCOUNT_CODE,"")
        //Pega a mensagem de confirmação ou uma string vazia se esta não foi definida
        discountCodeConfirmation.text=savedInstanceState.getString(DISCOUNT_CONFIRMATION_MESSAGE,"")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(TAG, "onSaveInstanceState")
        //Quando salvar o estado da instância, coloco esses valores nas constantes (1)
        outState.putString(DISCOUNT_CODE, discountCode.text.toString())
        outState.putString(DISCOUNT_CONFIRMATION_MESSAGE, discountCodeConfirmation.text.toString())
    }

    companion object {
        private const val TAG = "MainActivity"
        private const val DISCOUNT_CONFIRMATION_MESSAGE="DISCOUNT_CONFIRMATION_MESSAGE"
        private const val DISCOUNT_CODE="DISCOUNT_CODE"

    }
}