package com.shaun.newsbreeze.presentation.screens

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shaun.newsbreeze.models.Article
import com.shaun.newsbreeze.presentation.components.AuthorRow
import com.shaun.newsbreeze.presentation.components.NewsViewToolbar
import com.shaun.newsbreeze.presentation.components.NewsViewTopSection

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun NewsViewScreen(article: Article?, onBackPressed: () -> Unit) {

    Column(Modifier.fillMaxSize()) {


        Box() {
            NewsViewTopSection(
                imageUrl = article?.urlToImage.toString(),
                onSaveClick = { /*TODO*/ }) {
                onBackPressed()
            }

            LazyColumn( ) {

                stickyHeader {
                    Column {
                        Spacer(modifier = Modifier.height(20.dp))
                        NewsViewToolbar(onSaveClick = { }) {
                            onBackPressed()

                        }
                    }
                }

                item {
                    Column {
                        Spacer(modifier = Modifier
                            .height(280.dp)
                           )
                        Card(
                            shape = RoundedCornerShape(topEnd = 40.dp, topStart = 40.dp),
                            backgroundColor = Color.White,
                            modifier = Modifier.fillMaxSize()
                        ) {

                            AuthorRow(
                                author = article?.author ?: "null",
                                source = article?.source?.name ?: "null",
                                imageUrl = article?.urlToImage ?: ""
                            ) {
                            }

                        }
                    }
                }



                item {
                    Column(
                        Modifier
                            .fillMaxSize()
                            .background(Color.White)
                            .padding(20.dp)
                    ) {

                        Text(
                            text = article?.content.toString(),
                            fontSize = 35.sp,
                            fontFamily = FontFamily.Serif
                        )

                    }
                }
            }

        }
    }
}