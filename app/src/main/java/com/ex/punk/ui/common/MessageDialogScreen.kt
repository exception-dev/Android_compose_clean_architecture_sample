package com.ex.punk.ui.common

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource

@Composable
fun ErrorMessage(message : String){
    var isShowDialog by remember { mutableStateOf(true) }

    if(isShowDialog){
        AlertDialog(
            onDismissRequest = {
                isShowDialog = false
            },
            text = {
                Text(message)
            },
            confirmButton = {
                TextButton(

                    onClick = {
                        isShowDialog = false
                    }) {
                    Text(stringResource(id = android.R.string.ok))
                }
            }
        )
    }
}