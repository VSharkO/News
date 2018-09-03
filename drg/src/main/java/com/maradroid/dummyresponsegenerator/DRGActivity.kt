package com.maradroid.dummyresponsegenerator

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import com.google.gson.*
import com.maradroid.dummyresponsegenerator.init_responses.InitFragment
import com.maradroid.dummyresponsegenerator.response.ResponseFragment
import com.maradroid.dummyresponsegenerator.utils.SCREEN
import com.maradroid.dummyresponsegenerator.utils.SCREEN_INIT

class DRGActivity : AppCompatActivity(), MainDelegate {

    companion object {
        fun startInitScreen(context: Context) {
            val intent = Intent(context, DRGActivity::class.java)
            intent.putExtra(SCREEN, SCREEN_INIT)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_drg)
        //ivDone.visibility = View.GONE
        //loadFragment(ResponseFragment.newInstance(), false)
        //loadFragment(ChooseFragment.newInstance(), false)

        if (intent.extras.getInt(SCREEN) == SCREEN_INIT) {
            loadFragment(InitFragment.newInstance(), false)
        }

        /*ivDone.setOnClickListener {
            val loadedFragment = supportFragmentManager.findFragmentById(R.id.frameLayout)
            if (loadedFragment is ResponseFragment) {
                loadedFragment.saveResponse()
            }
        }*/
    }

    private fun loadFragment(fragment: Fragment, addToBackStack: Boolean) {
        val transaction = supportFragmentManager
                .beginTransaction()
                .replace(R.id.frameLayout, fragment)
        if (addToBackStack)
            transaction.addToBackStack(null)
        transaction.commit()

        /*if (fragment is ResponseFragment)
            ivDone.visibility = View.VISIBLE*/
    }

    override fun showInitScreen() {
        loadFragment(InitFragment.newInstance(), true)
    }

    override fun showResponse(fileName: String) {
        loadFragment(ResponseFragment.newInstance(fileName), true)
    }
}

interface MainDelegate {
    fun showInitScreen()
    fun showResponse(fileName: String)
}

data class MapData(val entry: MutableMap.MutableEntry<String, JsonElement>)
