package com.example.test

import android.content.res.Configuration
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.widget.Spinner
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.test.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    var myMediaPlayer : MediaPlayer? = null
    private lateinit var binding: ActivityMainBinding
    //var myMediaPlayer : MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //added
        //setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_music
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        if(myMediaPlayer == null || myMediaPlayer?.isPlaying == false){
            updateSong()
        }
    }
    fun updateSong(){
        myMediaPlayer?.isLooping = false
        myMediaPlayer?.stop()
        val sharedPreferences = getSharedPreferences("sharedPref", MODE_PRIVATE)

        val songChoice = sharedPreferences.getString("songChoice", "N64 Theme")

        when (songChoice){
            "N64 Theme" -> myMediaPlayer = MediaPlayer.create(this, R.raw.animalcrossingtheme)
            "New Leaf Theme" -> myMediaPlayer = MediaPlayer.create(this, R.raw.newleafmain)
            "K.K. Slider's Dream" -> myMediaPlayer = MediaPlayer.create(this, R.raw.sildersdream)
            "Bubblegum K.K." -> myMediaPlayer = MediaPlayer.create(this, R.raw.bubblegumkk)
            "K.K. Disco" -> myMediaPlayer = MediaPlayer.create(this, R.raw.kkdisco)
            "Go K.K. Rider!" -> myMediaPlayer = MediaPlayer.create(this, R.raw.gokkrider)
        }
        myMediaPlayer?.start()
        myMediaPlayer?.isLooping = true
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
    }



/*    //A button for Starting sound.
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
