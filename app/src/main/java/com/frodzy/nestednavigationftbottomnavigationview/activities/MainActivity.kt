package com.frodzy.nestednavigationftbottomnavigationview.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.frodzy.nestednavigationftbottomnavigationview.R
import com.frodzy.nestednavigationftbottomnavigationview.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var homeNavController: NavController
    private lateinit var profileNavController: NavController
    private lateinit var settingNavController: NavController
    private var activeFragment: Fragment? = null
    private var activeTab: String = "home"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bottomNavigationView = findViewById(R.id.bottomNavigationView)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.bottomNavigationView.setOnApplyWindowInsetsListener { view, insets ->
            view.updatePadding(bottom = 0)
            insets
        }

        setUpNavControllers()
        setUpBottomNavigationViewEventListeners()
    }

    private fun setUpNavControllers(){
        val homeHostFragment = supportFragmentManager.findFragmentById(R.id.navHostHome) as NavHostFragment
        val profileHostFragment = supportFragmentManager.findFragmentById(R.id.navHostProfile) as NavHostFragment
        val settingHostFragment = supportFragmentManager.findFragmentById(R.id.navHostSetting) as NavHostFragment

        homeNavController = homeHostFragment.navController
        profileNavController = profileHostFragment.navController
        settingNavController = settingHostFragment.navController

        supportFragmentManager.beginTransaction()
            .hide(profileHostFragment)
            .hide(settingHostFragment)
            .show(homeHostFragment)
            .commit()

        activeFragment = homeHostFragment
    }

    private fun setUpBottomNavigationViewEventListeners(){
        bottomNavigationView.setOnItemSelectedListener { item ->
            when(item.itemId){
                R.id.homeFragment -> {
                    println("SWITCH TO HOME")
                    activeTab = "home"
                    switchToNavFragment("home")
                }
                R.id.profileFragment -> {
                    println("SWITCH TO PROFILE")
                    activeTab = "profile"
                    switchToNavFragment("profile")
                }
                R.id.settingFragment -> {
                    println("SWITCH TO SETTING")
                    activeTab = "setting"
                    switchToNavFragment("setting")
                }
            }
            true
        }
    }

    private fun switchToNavFragment(tag: String){
        val homeHostFragment = supportFragmentManager.findFragmentById(R.id.navHostHome) as NavHostFragment
        val profileHostFragment = supportFragmentManager.findFragmentById(R.id.navHostProfile) as NavHostFragment
        val settingHostFragment = supportFragmentManager.findFragmentById(R.id.navHostSetting) as NavHostFragment

        val targetFragment = when(tag){
            "home" -> homeHostFragment
            "profile" -> profileHostFragment
            "setting" -> settingHostFragment
            else -> return
        }

        if(activeFragment != targetFragment){
            supportFragmentManager.beginTransaction()
                .hide(activeFragment!!)
                .show(targetFragment)
                .commit()

            activeFragment = targetFragment
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val currentNavHostFragment = supportFragmentManager.findFragmentById(getCurrentNavHostId())
        val navController = (currentNavHostFragment as NavHostFragment).navController
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    private fun getCurrentNavHostId(): Int {
        return when(activeTab){
            "home" -> R.id.navHostHome
            "profile" -> R.id.navHostProfile
            "setting" -> R.id.navHostSetting
            else -> R.id.navHostHome
        }
    }

    @Deprecated("This method has been deprecated in favor of using the\n      {@link OnBackPressedDispatcher} via {@link #getOnBackPressedDispatcher()}.\n      The OnBackPressedDispatcher controls how back button events are dispatched\n      to one or more {@link OnBackPressedCallback} objects.")
    override fun onBackPressed() {
        val navController = findNavController(getCurrentNavHostId())

        if(!navController.navigateUp()){
            super.onBackPressed()
        }
    }

}