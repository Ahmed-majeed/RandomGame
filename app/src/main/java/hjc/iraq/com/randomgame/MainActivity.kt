package hjc.iraq.com.randomgame


import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    var sensorManager: SensorManager? = null
    var proximitySensor: Sensor? = null
    var proximitySensorListener: SensorEventListener? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        clearBT.setOnClickListener {
            clear()
        }



        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        proximitySensor = sensorManager!!.getDefaultSensor(Sensor.TYPE_PROXIMITY)



        proximitySensorListener = object : SensorEventListener {
            override fun onSensorChanged(sensorEvent: SensorEvent) {
                if (sensorEvent.values[0] < proximitySensor!!.maximumRange) {
                    //window.decorView.setBackgroundColor(Color.RED)
                    numberOne.setBackgroundColor(Color.BLUE)
                    numberTow.setBackgroundColor(Color.GREEN)
                    //numberTow.text=(0..100).random().toString()

                    luckyRandom()



                } else {
                    // window.decorView.setBackgroundColor(Color.GREEN)
                    numberOne.setBackgroundColor(Color.GREEN)
                    numberTow.setBackgroundColor(Color.BLUE)



                }
            }

            override fun onAccuracyChanged(sensor: Sensor, i: Int) {}
        }

    }

    private fun clear() {
        numberOne.text = "0"
        numberTow.text = "0"
    }

    private fun luckyRandom() {

        val numberVariableOne = (0..100).random().toString()
        numberOne.text = numberVariableOne
        val numberRandom = numberVariableOne.toInt()
        if (numberRandom == 15 || numberRandom == 30) {

            val messageWinner=AlertDialog.Builder(this)
            messageWinner.setTitle("Winner")
            messageWinner.setIcon(R.drawable.winner)
            messageWinner.setMessage("Player One Is Winner")
            messageWinner.setNegativeButton("ok", { dialogInterface: DialogInterface, i: Int -> })
            messageWinner.show()

        }

        val numberVariableTow = (0..100).random().toString()
        numberTow.text = numberVariableTow
        val numberRandom1 = numberVariableTow.toInt()
        if (numberRandom1 == 15 || numberRandom1 == 30) {

            val messageWinner=AlertDialog.Builder(this)
            messageWinner.setTitle("Winner")
            messageWinner.setIcon(R.drawable.winner)
            messageWinner.setMessage("Player Tow Is Winner")
            messageWinner.setNegativeButton("ok", { dialogInterface: DialogInterface, i: Int -> })
            messageWinner.show()
        }
    }

    override fun onResume() {
        super.onResume()
        sensorManager!!.registerListener(
            proximitySensorListener, proximitySensor,
            2 * 1000 * 1000
        )
    }

    fun IntRange.random() =
        Random().nextInt((endInclusive + 1) - start) +  start




}


