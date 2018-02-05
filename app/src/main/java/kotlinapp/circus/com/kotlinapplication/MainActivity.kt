package kotlinapp.circus.com.kotlinapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var mCurrentIndex: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showQuestion()
    }

    private fun showQuestion() {
        var countryList = CountryList()
        question_text.setText("${mCurrentIndex + 1}" + ". " + resources.getString(R.string.question_title))
        question_country_text.setText("[" + resources.getString(countryList.mQuestions[mCurrentIndex].country) + "]")
    }
}
