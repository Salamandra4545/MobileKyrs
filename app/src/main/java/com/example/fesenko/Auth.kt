package com.example.fesenko

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

@Composable
fun AuthRegScreen(onAuth: () -> Unit) {
    val auth = Firebase.auth

    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    var isValid by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    )
    //ввод почты и пароля
    {
        TextField(
            value = email.value, onValueChange = { email.value = it
                                                    isValid = it.isNotEmpty()}
        )
        Spacer(modifier = Modifier.height(10.dp))
        TextField(
            value = password.value, onValueChange = { password.value = it
                                                        isValid = it.isNotEmpty()},
        )
        if (!isValid) {
            Text(
                text = "логин или пароль не может быть пустым",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodyLarge
            )
        }
        Button(
            onClick = {
                if (email.value.isEmpty()||password.value.isEmpty()) {
                    isValid = false
                } else {
                    SignIn(auth, email.value, password.value)
                    onAuth()
                }
            },
            colors = ButtonColors(Color.Cyan, Color.Black, Color.Black, Color.Black),
        ) {
            Text(text = "Sign In")
        }
        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = {
                if (email.value.isEmpty()||password.value.isEmpty()) {
                    isValid = false
                } else {
                    SignUp(auth, email.value, password.value)
                    onAuth()
                }
            },
            colors = ButtonColors(Color.Cyan, Color.Black, Color.Black, Color.Black)
        )
        {
            Text(text = "Sign Up")
        }
    }
}
//вход в аккаунт
fun SignIn(auth: FirebaseAuth, email: String, password: String) {
    auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
        if (it.isSuccessful) {
            Log.d("myLog", "Sign in Successful")
        } else {
            Log.d("myLog", "Sign in failure")
        }
    }
}

//регистация аккаунта
fun SignUp(auth: FirebaseAuth, email: String, password: String) {
    auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
        if (it.isSuccessful) {
            Log.d("myLog", "Sign up Successful")
        } else {
            Log.d("myLog", "Sign up failure")
        }
    }
}