package com.example.backgroundprocess

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import kotlinx.coroutines.*
import kotlin.properties.Delegates


class SecondaryFragment : Fragment() {

    lateinit var appleCount: TextView
    lateinit var maxCount: TextView
    lateinit var reset: Button
    var aaa by Delegates.notNull<Boolean>()

    val args: SecondaryFragmentArgs by navArgs()

    var app_count = 0
    var max_count = 0

    var cheek = 0

    var app_count1 = 0
    var max_count1 = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_secondary, container, false)
        app_count = args.appleCount
        max_count = args.maxAppleCount

        app_count1 = args.appleCount
        max_count1 = args.maxAppleCount

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val plus = view.findViewById<Button>(R.id.plus)
        val minus = view.findViewById<Button>(R.id.minus)

        appleCount = view.findViewById(R.id.second_app_count)
        maxCount = view.findViewById(R.id.second_max_app_count)
        reset = view.findViewById(R.id.reset)

        reset.visibility = View.GONE

        appleCount.setText(app_count.toString())
        maxCount.setText(max_count.toString())
        plus?.setOnClickListener {
            cheek = 1
            GlobalScope.launch(Dispatchers.Default) {
                apple()
            }
        }

        minus.setOnClickListener {
            cheek = 2
            GlobalScope.launch(Dispatchers.Default) {
                apple()
            }
        }

        reset.setOnClickListener {
            cheek = 3
            GlobalScope.launch(Dispatchers.Default) {
                apple()
            }
            reset.visibility = View.GONE
        }
    }

    suspend fun apple() {
        aaa = false
        delay(100)
        if (app_count < max_count) {
            if (cheek == 1)
                app_count += 1
        } else aaa = true

        if (app_count > 0) {
            if (cheek == 2) app_count -= 1
        } else aaa = true

        if (cheek == 3) app_count = app_count1
        withContext(Dispatchers.Main) {
            appleCount.setText(app_count.toString())
            if (aaa) {
                aaa = false
                reset.visibility = View.VISIBLE
            } else reset.visibility = View.GONE
        }
    }
}