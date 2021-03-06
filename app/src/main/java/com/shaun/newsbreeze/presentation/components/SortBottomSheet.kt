package com.shaun.newsbreeze.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shaun.newsbreeze.R
import com.shaun.newsbreeze.ui.theme.LightGreen
import com.shaun.newsbreeze.utils.AppConstants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@Composable
fun LibraryBottomSheet(
    bottomSheetScaffoldState: ModalBottomSheetState,
    coroutineScope: CoroutineScope,
    currentScreen: String,
    onSortItemClicked: (String) -> Unit
) {


    Column(
        Modifier
            .fillMaxWidth()
            .background(Color.White)

    ) {

        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()) {

            Image(
                painter = painterResource(id = R.drawable.bar), contentDescription = "",
                Modifier
                    .width(470.dp)
                    .height(40.dp)


            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Sort By",
            color = Color.Black,
            style = MaterialTheme.typography.h5,
            modifier = Modifier.padding(start = 30.dp, bottom = 50.dp),
            fontWeight = FontWeight.Bold
        )
        AppConstants.SORT_ITEMS.forEach {
            SortItemRow(modifier = Modifier.clickable {
                coroutineScope.launch {
                    bottomSheetScaffoldState.hide()
                }
                onSortItemClicked(it)
            }, title = it, isTickVisible = currentScreen == it)
        }

        Text(
            text = "Cancel",
            color = LightGreen,
            style = MaterialTheme.typography.body1,
            modifier = Modifier
                .clickable {
                    coroutineScope.launch {
                        bottomSheetScaffoldState.hide()
                    }
                }
                .align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(50.dp))
    }

}


@Composable
fun SortItemRow(
    modifier: Modifier = Modifier,
    title: String,
    isTickVisible: Boolean = true
) {

    Row(
        modifier
            .fillMaxWidth()
            .padding(start = 30.dp, end = 20.dp, bottom = 10.dp, top = 10.dp)
    ) {
        Text(text = title, color = Color.Black)
        if (isTickVisible)
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_tick), contentDescription = "",
                    modifier = Modifier
                        .size(20.dp)
                        .align(Alignment.End)
                        .padding(top = 4.dp),
                    colorFilter = ColorFilter.tint(LightGreen)
                )
            }
    }
}