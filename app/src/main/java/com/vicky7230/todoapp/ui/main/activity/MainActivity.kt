package com.vicky7230.todoapp.ui.main.activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.ktx.messaging
import com.vicky7230.todoapp.ui.contentprovider.ContentProviderActivity
import com.vicky7230.todoapp.ui.main.screen.MainScreen
import com.vicky7230.todoapp.ui.main.state.MainSideEffect
import com.vicky7230.todoapp.ui.main.viewmodel.MainViewModel
import com.vicky7230.todoapp.ui.search.activity.SearchActivity
import com.vicky7230.todoapp.ui.theme.TodoAppTheme
import dagger.android.AndroidInjection
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var mainViewModel: MainViewModel

    private val requestPermissionsLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            val readContactsGranted = permissions[Manifest.permission.READ_CONTACTS] == true
            val postNotificationsGranted = permissions[Manifest.permission.POST_NOTIFICATIONS] == true

            if (readContactsGranted && postNotificationsGranted) {
                Timber.d("Permissions granted")
                Toast.makeText(this@MainActivity, "Permissions granted", Toast.LENGTH_SHORT)
                    .show()
            } else {
                Timber.d("Permissions denied")
                Toast.makeText(this@MainActivity, "Permissions denied", Toast.LENGTH_SHORT)
                    .show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        printFcmToken()

        //request permissions
        requestPermissionsIfNeeded()

        //enableEdgeToEdge()
        mainViewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]

        setContent {
            val state = mainViewModel.mainUiState.collectAsStateWithLifecycle()
            TodoAppTheme {
                MainScreen(
                    modifier = Modifier.fillMaxSize(),
                    state = state.value,
                    onSearchClick = {
                        startActivity(Intent(this@MainActivity, SearchActivity::class.java))
                    },
                    onDataClick = {
                        startActivity(
                            Intent(
                                this@MainActivity,
                                ContentProviderActivity::class.java
                            )
                        )
                    }
                )
            }
        }

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                mainViewModel.sideEffect.collectLatest { event ->
                    when (event) {
                        is MainSideEffect.ShowError ->
                            Toast.makeText(this@MainActivity, event.message, Toast.LENGTH_SHORT)
                                .show()

                        is MainSideEffect.ShowMessage ->
                            Toast.makeText(this@MainActivity, event.message, Toast.LENGTH_SHORT)
                                .show()
                    }
                }
            }
        }

    }

    private fun requestPermissionsIfNeeded() {
        val permissionsToRequest = mutableListOf<String>()

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
            != PackageManager.PERMISSION_GRANTED
        ) {
            permissionsToRequest.add(Manifest.permission.READ_CONTACTS)
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU && // Android 13+
            ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS)
            != PackageManager.PERMISSION_GRANTED
        ) {
            permissionsToRequest.add(Manifest.permission.POST_NOTIFICATIONS)
        }

        if (permissionsToRequest.isNotEmpty()) {
            requestPermissionsLauncher.launch(permissionsToRequest.toTypedArray())
        } else {
            Toast.makeText(this@MainActivity, "Permissions granted", Toast.LENGTH_SHORT)
                .show()
        }
    }


    private fun printFcmToken() {
        Firebase.messaging.token.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val token = task.result
                Timber.d("FCM Token: $token")
            } else {
                Timber.e("Fetching FCM registration token failed", task.exception)
            }
        }
    }
}