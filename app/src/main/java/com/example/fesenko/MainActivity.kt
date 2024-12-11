package com.example.fesenko

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.fesenko.ui.theme.FesenkoTheme
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val auth = Firebase.auth
        setContent {
            FesenkoTheme {
                Myapp(auth)
            }
        }
    }
}

@Composable
fun Myapp(auth:FirebaseAuth) {
    var isRegistered by remember { mutableStateOf(false) }
    if (isRegistered) {
        Router(auth)
    } else {
      AuthRegScreen (onAuth = {isRegistered=true})
    }
}

@Composable
fun Router(auth: FirebaseAuth) {
    Column(modifier = Modifier.fillMaxSize()) {
        var route by remember { mutableStateOf("home") }
        Box(modifier = Modifier.weight(1f))
        { when (route) {
                "place" -> {Place()}
                "Profile" -> {Profile()}
        }
        }
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp), horizontalArrangement = Arrangement.SpaceAround) {

            Image(
                painter = painterResource(id = R.drawable.home),
                modifier = Modifier.clickable { route = "place" },
                contentDescription = "place"
            )
            Image(
                painter = painterResource(id = R.drawable.user),
                modifier = Modifier.clickable { route = "Profile" },
                contentDescription = "Profile"
            )
        }
    }
}