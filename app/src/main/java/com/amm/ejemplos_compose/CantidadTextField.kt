package com.amm.ejemplos_compose

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CantidadTextField(
    nominacion:Int,
    cantidad:String,
    onTextChange:(String)->Unit = {},
){
    Column(modifier = Modifier.padding(5.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "$$nominacion",fontSize = 18.sp,color = Color.Black,)
        BasicTextField(
            value = cantidad,
            onValueChange = { onTextChange(it) },
            textStyle = TextStyle(color = Color.Black,
                fontSize = 17.sp,
                textAlign = TextAlign.Center),
            modifier = Modifier
                .background(Color.White, RoundedCornerShape(8.dp))
                .border(
                width = 1.dp,
                color = Color.DarkGray,
                shape = RoundedCornerShape(8.dp)
            )
                .padding(10.dp),

        )
    }
}

@Preview(showBackground = true)
@Composable
fun CantidadPreview(

){
    CantidadTextField(nominacion = 10, cantidad = ""){}
}