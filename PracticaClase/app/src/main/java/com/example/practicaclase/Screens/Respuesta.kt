package com.example.practicaclase.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.navegacion.Navigation.AppScreen

@Preview(showBackground = true)
@Composable
fun ChatScreenPreview() {
    val mockNavControler = rememberNavController()
    RespuestaScreen(modifier = Modifier, mockNavControler, "Pedrito", "Gutierrez Gad", 34,  "1234567A")
}

@Composable
fun RespuestaScreen(modifier: Modifier = Modifier, navController: NavController, nombre: String?, apellidos: String?, edad: Int?, dni: String?) {
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        Column(modifier = modifier) {
            RespuestaHeader(modifier = Modifier, navController, nombre!!, apellidos!!)
            RespuestaBody(modifier = Modifier, edad!!, dni!!)
            
        }
    }
}



@Composable
fun RespuestaHeader(modifier: Modifier = Modifier, navController: NavController, nombre: String, apellidos: String?) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(color = Color.Blue),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = "Volver",
            tint = Color.White,
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .clickable(
                    true,
                    onClick = { navController.navigate(route = AppScreen.FirstScreen.route) })
        )

        Text(
            text = "Hola $nombre $apellidos",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = Color.White,
            modifier = Modifier.padding(start = 16.dp),
        )
    }
}

@Composable
fun RespuestaBody(modifier: Modifier = Modifier, edad: Int?, dni: String?) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier.padding(30.dp)
    ) {
        Column() {
            Text(text = "Edad: $edad")
            Text(text = "DNI: $dni")
        }

    }

}


