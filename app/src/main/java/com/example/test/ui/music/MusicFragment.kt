package com.example.test.ui.music

import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.test.R


class MusicFragment : Fragment() {

    var myMediaPlayer : MediaPlayer? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_music, container, false)

        view.findViewById<Button>(R.id.play).setOnClickListener {

            if (myMediaPlayer == null){
                myMediaPlayer = MediaPlayer.create(view.context, R.raw.animalcrossingtheme)
            }
            // Starts or resumes playback.
            myMediaPlayer?.start()
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