package com.example.practicaclase.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.navegacion.Navigation.AppScreen
import com.example.practicaclase.R

@Composable
fun Datos(modifier: Modifier = Modifier, navController: NavController) {
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(0.dp)
        ) {
            Header(modifier = modifier)
            Body(modifier = Modifier, navController)
            Filler(modifier = Modifier)

        }

    }

}

@Preview(showBackground = true)
@Composable
fun Header(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(color = Color.Blue),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = stringResource(R.string.logo),
            fontWeight = FontWeight.Bold,
            fontSize = 25.sp,
            color = Color.White,
            modifier = Modifier.padding(start = 16.dp),
        )
    }
}

@Composable
fun Body(modifier: Modifier = Modifier, navController: NavController) {
    //Variable que decide si el boton esta activado o no
    var enviarOk by rememberSaveable { mutableStateOf(false) }
    var nombreText by rememberSaveable { mutableStateOf("") }
    var apellidosText by rememberSaveable { mutableStateOf("") }
    var edadText by rememberSaveable { mutableStateOf("") }
    var dniText by rememberSaveable { mutableStateOf("") }
    var alert by rememberSaveable { mutableStateOf("") }


    Column(
        modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        //Nombre
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
        ) {
            TextField(
                value = nombreText,
                onValueChange = { nombreText = it },
                label = { Text("Nombre") },
                maxLines = 1,
                modifier = Modifier.padding(20.dp),
            )
        }

        //Apellidos
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
        ) {
            TextField(
                value = apellidosText,
                onValueChange = { apellidosText = it },
                label = { Text("Apellidos") },
                maxLines = 1,
                modifier = Modifier.padding(20.dp)
            )
        }

        //Edad
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
        ) {
            TextField(
                value = edadText,
                onValueChange = { edadText = it },
                label = { Text("Edad") },
                maxLines = 1,
                modifier = Modifier.padding(20.dp)
            )
        }

        //DNI
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
        ) {
            TextField(
                value = dniText,
                onValueChange = { dniText = it },
                label = { Text("DNI") },
                maxLines = 1,
                modifier = Modifier.padding(20.dp)
            )
        }

        //Alerta si algo esta mal, si no hay problemas no aparece
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
        ) {
            Text(
                text = alert,
                color = Color.Red
            )
        }

        //Comprueba que ninguno de lps campos este vacio
        if (nombreText.isEmpty() || apellidosText.isEmpty() || edadText.isEmpty() || dniText.isEmpty()) {
            alert = "Rellene todos los campos"
            enviarOk = false
            //Compreuba que edadText sea un entero
        } else if (edadText.toIntOrNull() == null) {
            alert = "La edad debe de ser un entero"
            enviarOk = false
            //Se asegura que el usuario es mayor de edad
        } else if (edadText.toInt() < 18) {
            alert = "El usuario debe ser mayor de edad"
            enviarOk = false
            //Comprueba la longitud del DNI
        } else if (dniText.length != 8) {
            alert = "El DNI no es la longitud correcta"
            enviarOk = false
            //Comprueba que los 7 primeros sean numeros
        } else if (dniText.substring(0,7).toIntOrNull() == null) {
            alert = "El DNI no sigue el formato correcto"
            enviarOk = false
        } else {
            enviarOk = true
            alert = ""
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            ButtonAccept(
                modifier = Modifier,
                navController,
                nombreText,
                apellidosText,
                edadText,
                dniText,
                enviarOk
            )
        }


    }

}

@Preview(showBackground = true)
@Composable
fun BodyPreview() {
    val mockNavController = rememberNavController()
    Body(modifier = Modifier, mockNavController)
}

@Preview(showBackground = true)
@Composable
fun ButtonAcceptPreview() {
    val mockNavController = rememberNavController()
    ButtonAccept(
        modifier = Modifier,
        mockNavController,
        "Pedrito",
        "Tolentino Gutierrez",
        "47",
        "1234567A",
        true
    )
}

//Boton para enviar los datos, solo esta disponible si enviarOk es true
@Composable
fun ButtonAccept(
    modifier: Modifier = Modifier,
    navController: NavController,
    nombre: String,
    apellidos: String,
    edad: String,
    dni: String,
    enviarOk: Boolean
) {
    Button(
        onClick = {
            navController.navigate(
                route = AppScreen.SecondScreen.route +
                        "/$nombre/$apellidos/${edad.toInt()}/$dni"
            )
        },
        modifier.size(width = 120.dp, height = 40.dp),
        enabled = enviarOk,
        colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
    ) {
        Text(
            text = "Enviar",
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp,
            color = Color.White
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Filler(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    )
}