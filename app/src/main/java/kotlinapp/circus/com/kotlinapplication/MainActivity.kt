package kotlinapp.circus.com.kotlinapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private var mCurrentIndex: Int = 0
    private val mCountryList = CountryList()
    private val mAnswer = mutableListOf<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        updateQuestion()
        setButton()
    }

    private fun showQuestion() {
        question_text.setText("${mCurrentIndex + 1}" + ". " + resources.getString(R.string.question_title))
        question_country_text.setText("[" + resources.getString(mCountryList.mQuestions[mCurrentIndex].country) + "]")
    }

    private fun updateQuestion() {
        showQuestion()
        setQuestionNumber()
        setAnswerButtonText()
    }

    private fun setButton() {
        prev_button.setOnClickListener {
            mCurrentIndex = mCurrentIndex - 1
            if (mCurrentIndex < 0) {
                mCurrentIndex = mCountryList.mQuestions.size - 1
            }
            updateQuestion()
        }

        next_button.setOnClickListener {
            mCurrentIndex = (mCurrentIndex + 1) % mCountryList.mQuestions.size
            updateQuestion()
        }

        answer_one.setOnClickListener {
            if (resources.getString(mCountryList.mQuestions[mCurrentIndex].capital) == resources.getString(mCountryList.mQuestions[mAnswer.get(0)].capital)) {
                Toast.makeText(applicationContext, R.string.answer_true, Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(applicationContext, R.string.answer_false, Toast.LENGTH_SHORT).show()
            }
        }

        answer_two.setOnClickListener {
            if (resources.getString(mCountryList.mQuestions[mCurrentIndex].capital) == resources.getString(mCountryList.mQuestions[mAnswer.get(1)].capital)) {
                Toast.makeText(applicationContext, R.string.answer_true, Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(applicationContext, R.string.answer_false, Toast.LENGTH_SHORT).show()
            }
        }

        answer_three.setOnClickListener {
            if (resources.getString(mCountryList.mQuestions[mCurrentIndex].capital) == resources.getString(mCountryList.mQuestions[mAnswer.get(2)].capital)) {
                Toast.makeText(applicationContext, R.string.answer_true, Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(applicationContext, R.string.answer_false, Toast.LENGTH_SHORT).show()
            }
        }

        answer_four.setOnClickListener {
            if (resources.getString(mCountryList.mQuestions[mCurrentIndex].capital) == resources.getString(mCountryList.mQuestions[mAnswer.get(3)].capital)) {
                Toast.makeText(applicationContext, R.string.answer_true, Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(applicationContext, R.string.answer_false, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setQuestionNumber() {
        var isDuplicated = false
        mAnswer.clear()
        mAnswer.add(0, -1)
        mAnswer.add(1, -1)
        mAnswer.add(2, -1)
        mAnswer.add(3, -1)

        var count = 0
        val random = Random()

        var temp: Int
        while (true) {
            temp = random.nextInt(mCountryList.mQuestions.size - 1)
            if (temp == mCurrentIndex) {
                continue
            }

            for (i in 0..3) {
                if (temp == mAnswer.get(i)) {
                    isDuplicated = true
                }
            }
            if (isDuplicated) {
                isDuplicated = false
                continue
            } else {
                mAnswer.set(count, temp)
                count++
            }
            if (count > 3) {
                break
            }
        }
        mAnswer.set(0, mCurrentIndex)
        Collections.shuffle(mAnswer)
    }

    private fun setAnswerButtonText() {
        answer_one.setText(resources.getString(mCountryList.mQuestions[mAnswer.get(0)].capital))
        answer_two.setText(resources.getString(mCountryList.mQuestions[mAnswer.get(1)].capital))
        answer_three.setText(resources.getString(mCountryList.mQuestions[mAnswer.get(2)].capital))
        answer_four.setText(resources.getString(mCountryList.mQuestions[mAnswer.get(3)].capital))
    }
}
