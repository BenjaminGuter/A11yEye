package com.example.a11ybrowser.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.OutlinedButton

@Composable
fun FeatureCard(
    title: String,
    currentValue: Int,
    maxValue: Int,
    onValueChange: (Int) -> Unit
) {
    Card(
        modifier = Modifier.padding(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(title)

            Spacer(modifier = Modifier.height(8.dp))  // Abstand

                Row {
                    for (i in 0..maxValue) {
                        if (i == currentValue) {
                            Button(onClick = { onValueChange(i) }) {
                                Text("$i")
                            }
                        } else {
                            OutlinedButton(onClick = { onValueChange(i) }) {
                                Text("$i")
                            }
                        }
                    }
                }
            }
    }
}