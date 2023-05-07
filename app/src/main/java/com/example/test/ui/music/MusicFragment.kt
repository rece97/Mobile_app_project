package com.example.test.ui.music

import android.annotation.SuppressLint
import android.content.Context
import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.test.MainActivity
import com.example.test.R


class MusicFragment : Fragment()
{

    var myMediaPlayer : MediaPlayer? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_music, container, false)

        val myList = listOf("N64 Theme", "New Leaf Theme", "K.K. Slider's Dream")
        val myAdapter = ArrayAdapter<String>(view.context, android.R.layout.simple_spinner_dropdown_item, myList)
        view.findViewById<Spinner>(R.id.spinner).adapter = myAdapter
        view.findViewById<Spinner>(R.id.spinner).onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val item = parent?.getItemAtPosition(position)
                val appContext = requireContext().applicationContext
                var preferences = appContext.getSharedPreferences("sharedPref", Context.MODE_PRIVATE)
                var songChoice = preferences.getString("songChoice", "N64 Theme")
                if(!item.toString().equals(songChoice)) {
                    preferences.edit().putString("songChoice", item.toString()).apply()

                    (activity as MainActivity).updateSong()
                }

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }

        val appContext = requireContext().applicationContext
        var preferences = appContext.getSharedPreferences("sharedPref", Context.MODE_PRIVATE)
        var songChoice = preferences.getString("songChoice", "N64 Theme")
        when (songChoice){
            "N64 Theme" -> view.findViewById<Spinner>(R.id.spinner).setSelection(0)
            "New Leaf Theme" -> view.findViewById<Spinner>(R.id.spinner).setSelection(1)
            "K.K. Slider's Dream" -> view.findViewById<Spinner>(R.id.spinner).setSelection(2)
        }
        return view
    }

    /*     //A button for Starting sound.
     //If it is the first time, it creates the MediaPlayer object with the song provided
     fun playSound(view: View) {
         if (myMediaPlayer == null){
             myMediaPlayer = MediaPlayer.create(this, R.raw.animalcrossingtheme)
         }
         // Starts or resumes playback.
         myMediaPlayer?.start()
     }

     //A button for Pausing sound.
     fun pauseSound(view: View) {
         myMediaPlayer?.pause()
     }

     //A button for Stopping sound after it has been started or paused.
     fun stopSound(view: View) {
         //myMediaPlayer?.stop()
         stopSong()
     }

     //Helper function to handle stopping the sound
     private fun stopSong(){
         if(myMediaPlayer != null){
             //myMediaPlayer?.stop()
             // Release is probably better option to release the system resources used
             myMediaPlayer?.release()
             myMediaPlayer = null
         }
     }

     //Stop the song if onStop() is called
     override fun onStop() {
         super.onStop()
         stopSong()
     }*/
}