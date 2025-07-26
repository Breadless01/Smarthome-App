package com.example.smarthome_app.ui.tiles.shoppingTile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.unit.dp
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.draw.shadow
import androidx.compose.runtime.getValue
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun ShoppingTile(viewModel: ShoppingViewModel = viewModel()) {
    var amountInput by remember { mutableStateOf("") }
    var unitExpanded by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }
    var showDoneDialog by remember { mutableStateOf(false) }
    var inputText by remember { mutableStateOf("") }

    val availableUnits = listOf("pcs", "l", "g", "kg", "ml")
    var selectedUnit by remember { mutableStateOf(availableUnits[0]) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .shadow(elevation = 8.dp, shape = MaterialTheme.shapes.medium)
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Einkaufsliste",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Button(
                    onClick = { showDialog = true },
                    modifier = Modifier.height(36.dp)
                ) {
                    Text("Hinzufügen")
                }
                Button(
                    onClick = { showDoneDialog = true },
                    modifier = Modifier.height(36.dp)
                ) {
                    Text("Fertig")
                }
            }
            Spacer(modifier = Modifier.height(12.dp))

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                items(viewModel.productList) { product ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(fraction = 0.8f)
                                .padding(vertical = 4.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(MaterialTheme.colorScheme.surfaceVariant)
                                .padding(horizontal = 12.dp, vertical = 8.dp)
                        ) {
                            Text(
                                text = product.name,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }
                        Box(
                            modifier = Modifier
                                .padding(vertical = 4.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(MaterialTheme.colorScheme.surfaceVariant)
                                .padding(horizontal = 12.dp, vertical = 8.dp)
                        ) {
                            Text(
                                text = product.quantity,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }
                    }
                }
            }

            if (showDialog) {
                AlertDialog(
                    onDismissRequest = { showDialog = false },
                    title = { Text("Einkaufslist") },
                    text = {
                        Row {
                            TextField(
                                value = inputText,
                                onValueChange = { inputText = it },
                                placeholder = { Text("Produktname") },
                                singleLine = true
                            )

                            Spacer(modifier = Modifier.width(8.dp))

                            TextField(
                                value = amountInput,
                                onValueChange = { amountInput = it },
                                placeholder = { Text("Menge") },
                                modifier = Modifier.weight(1f),
                                singleLine = true,
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                            )

                            Spacer(modifier = Modifier.width(8.dp))

                            Box {
                                OutlinedButton(onClick = { unitExpanded = true }) {
                                    Text(selectedUnit)
                                }

                                DropdownMenu(
                                    expanded = unitExpanded,
                                    onDismissRequest = { unitExpanded = false }
                                ) {
                                    availableUnits.forEach { unit ->
                                        DropdownMenuItem(
                                            onClick = {
                                                selectedUnit = unit
                                                unitExpanded = false
                                            },
                                            text = { Text(unit) }
                                        )
                                    }
                                }
                            }
                        }
                    },
                    confirmButton = {
                        Button(onClick = {
                            val fullQuantity = "$amountInput $selectedUnit"
                            viewModel.addProduct(inputText, fullQuantity)
                            showDialog = false
                            inputText = ""
                        }) {
                            Text("Zur Liste hinzufügen")
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = { showDialog = false }) {
                            Text("Abbrechen")
                        }
                    }
                )
            }

            if (showDoneDialog) {
                AlertDialog(
                    onDismissRequest = { showDoneDialog = false },
                    title = { Text("Fertig") },
                    text = { Text("Einkaufsliste an Handy senden?.") },
                    confirmButton = {
                        Button(onClick = {
                            viewModel.sendToPhone()
                            showDoneDialog = false
                        }) {
                            Text("OK")
                        }
                    }
                )
            }
        }
    }
}
