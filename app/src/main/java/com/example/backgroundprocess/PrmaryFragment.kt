package com.example.backgroundprocess

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController


class PrmaryFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_prmary, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tv_apple_count = view.findViewById<EditText>(R.id.app_count)
        val tv_apple_max_count = view.findViewById<EditText>(R.id.max_app_count)
        val button = view.findViewById<Button>(R.id.send)


        button.setOnClickListener {
            if (tv_apple_count.text.length != 0 && tv_apple_max_count.text.length != 0)
                findNavController()
                    .navigate(
                        PrmaryFragmentDirections
                            .actionPrmaryFragmentToSecondaryFragment(
                                tv_apple_count.text.toString().toInt(),
                                tv_apple_max_count.text.toString().toInt(),
                            )
                    )
        }
    }
}