package com.amm.ejemplos_compose

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.amm.ejemplos_compose.ui.theme.Ejemplos_ComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Ejemplos_ComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize(),

                    ) { innerPadding ->
                    ContarDinero()
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun Ejemplo1(){
    var count by remember { mutableStateOf(0)}
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Button(onClick = { count++ }) {
            Text(text = "Tocame")
        }
        Text(text = "Se ha tocado $count veces", fontSize = 18.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun Ejemplo2(){
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
        var fvalue by remember { mutableStateOf("") }
        var cvalue by remember { mutableStateOf("") }
        EntradaGrados(fvalue,"F",{
            fvalue = it
            try {
                val celcius = ((it.toFloat()-32)*5/9)
                cvalue = String.format("%.1f",celcius)
            }
            catch (_:Exception){
                cvalue = ""
            }
        })
        Spacer(modifier = Modifier.height(10.dp))
        EntradaGrados(cvalue,"C",{})
    }
}

@Composable
fun EntradaGrados(
    value:String,
    unidad:String,
    onTextChange:(String)->Unit = {}
){
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center
    ){
        BasicTextField(
            value = value,
            onValueChange = onTextChange,
            textStyle = MaterialTheme.typography.labelMedium.copy(
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Monospace
            ),
            modifier = Modifier.width(120.dp).height(40.dp)
                .padding(5.dp)
                .border(
                    1.dp,MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(8.dp)
                ).align(alignment = CenterVertically),

        )
        Text(text = unidad, fontSize = 18.sp)
    }
}

@SuppressLint("MutableCollectionMutableState")
@OptIn(ExperimentalLayoutApi::class)
@Preview(showBackground = true)
@Composable
fun ContarDinero(modifier: Modifier = Modifier){
    val nominaciones = listOf(500, 200, 100,50,20,10,5,2,1)
    val cantidades = remember{
        mutableStateListOf<Int>().apply {
            addAll(List(nominaciones.size){0})
        }
    }
    var total by remember {
        mutableStateOf(0)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.back_color)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ){
        Text(
            text = stringResource(R.string.head),
            fontSize = 20.sp,
            color = Color.Black,
            style = MaterialTheme.typography.
            titleLarge.copy(fontWeight = FontWeight.Bold)
        )
        FlowRow {
            for (i in nominaciones.indices){
                CantidadTextField(
                    nominacion = nominaciones[i],
                    cantidad = if (cantidades[i] > 0)
                                    cantidades[i].toString()
                                else ""
                        ,
                onTextChange = {
                    try {
                        cantidades[i] = it.toInt()
                    }
                    catch (_:Exception){
                        cantidades[i] = 0
                    }
                    total = cantidades.indices.sumOf {
                        it1-> cantidades[it1]*nominaciones[it1]
                    }
                })
            }
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                "${stringResource(R.string.total)} $$total",
                fontSize = 20.sp,
                color = Color.Black,
                style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold)
            )
            Button(
                shape = RoundedCornerShape(12.dp),
                onClick = {
                    cantidades.clear()
                    cantidades.addAll(List(nominaciones.size){0})
                    total = 0
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(R.color.button_color)
                )
            ) {
                Image(painterResource(R.drawable.baseline_cleaning_services_24), contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = stringResource(R.string.clean), color = Color.White)
            }
        }

    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}


@Composable
fun GreetingPreview() {
    Ejemplos_ComposeTheme {
        Greeting("Android")
    }
}