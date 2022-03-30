package com.example.backgroundprocess

import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs


class SecondaryFragment : Fragment() {


    lateinit var appCount  :TextView
    lateinit var maxCount  :TextView
    lateinit var reset     :Button

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
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_secondary, container, false)
        app_count = args.appleCount
        max_count = args.maxAppleCount

        app_count1 = args.appleCount
        max_count1 = args.maxAppleCount


        return view
    }




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val plus     = view.findViewById<Button>(R.id.plus)
        val minus    = view.findViewById<Button>(R.id.minus)

        appCount     = view.findViewById(R.id.second_app_count)
        maxCount     = view.findViewById(R.id.second_max_app_count)
        reset        = view.findViewById(R.id.reset)

        reset.visibility = View.GONE


        val bg_works = BackgroundWork()
        bg_works.execute()

        plus?.setOnClickListener {
            val bg_process1 = BackgroundWork()
            cheek = 1
            bg_process1.execute()
        }

        minus.setOnClickListener{
            val bg_process2 = BackgroundWork()
            cheek = 2
            bg_process2.execute()
        }

        reset.setOnClickListener{
            val bg_process3 = BackgroundWork()
            cheek = 3
            bg_process3.execute()
            reset.visibility = View.GONE
        }


    }//


        inner class BackgroundWork : AsyncTask<Unit?, Unit?, Unit?>() {
            override fun onPreExecute() {
                super.onPreExecute()
                appCount.setText(app_count.toString())
                maxCount.setText(max_count.toString())
            }

            var aaa = false
            override fun doInBackground(vararg voids: Unit?): Unit? {
                try {
                    if (app_count < max_count) {
                        if (cheek == 1)
                            app_count += 1
                    }else
                        aaa = true

                    if (app_count > 0) {
                        if (cheek == 2)
                            app_count -= 1
                    }else
                        aaa = true

                    if (cheek == 3)
                        app_count = app_count1

                    Thread.sleep(500)

                } catch (e: InterruptedException) {

                }
                return null
            }

            override fun onPostExecute(result: Unit?) {
                super.onPostExecute(result)
                appCount.setText(app_count.toString())
                if (aaa) {
                    aaa = false
                    reset.visibility = View.VISIBLE
                }else
                    reset.visibility = View.GONE
            }
        }


}