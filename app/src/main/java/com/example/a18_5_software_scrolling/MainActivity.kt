package com.example.a18_5_software_scrolling

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.a18_5_software_scrolling.ui.theme.Purple80
import com.example.a18_5_software_scrolling.ui.theme.PurpleGrey40
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Start()
        }
    }
}

@OptIn(ExperimentalLayoutApi::class, ExperimentalFoundationApi::class)
@Composable
fun Start() {

    val persons = listOf<Person>(
        Person(fName = "Дмитрий", sName = "Гухман", position = "Junior"),
        Person(fName = "Анна", sName = "Котлиновна", position = "Middle"),
        Person(fName = "Василий", sName = "Явович", position = "Senior"),

        Person(fName = "Евгения", sName = "Ассемблеровна", position = "CEO"),
        Person(fName = "Надежда", sName = "Фортрановна", position = "CTO"),
        Person(fName = "Илья", sName = "Питонович", position = "Project manager"),

        Person(fName = "Борис", sName = "Растович", position = "Junior"),
        Person(fName = "Инга", sName = "Рубиевна", position = "Junior"),
        Person(fName = "Владимир", sName = "Яваскриптович", position = "Junior"),

        Person(fName = "Артём", sName = "Обжектсивович", position = "Middle"),
        Person(fName = "Валентина", sName = "Тайпскриптовна", position = "Middle"),
        Person(fName = "Мария", sName = "Скалаевна", position = "Middle"),

        Person(fName = "Вариний", sName = "Алголович", position = "Senior"),
        Person(fName = "Марина", sName = "Бейсиковна", position = "Senior"),
        Person(fName = "Александр", sName = "Коболович", position = "Senior"),

        Person(fName = "Полина", sName = "Лисповна", position = "CEO"),
        Person(fName = "Инна", sName = "Паскалевна", position = "CEO"),
        Person(fName = "Алексей", sName = "Делфиевич", position = "CEO"),

        Person(fName = "Анатолий", sName = "Хаскелович", position = "Project manager"),
        Person(fName = "Екатерина", sName = "Перловна", position = "Project manager"),
        Person(fName = "Владлен", sName = "Свифтович", position = "Project manager"),

        Person(fName = "Николай", sName = "Вижуалбейсикович", position = "Project manager"),
        Person(fName = "София", sName = "Сиплюсплюсовна", position = "Project manager"),
        Person(fName = "Дарья", sName = "Одинэсовна", position = "Project manager"),
    )

    val groups = persons.groupBy { it.position }
        .mapValues { entry -> entry.value.sortedBy { it.fName } }

    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()


    Scaffold(content = { padding ->
        Column(
            modifier = Modifier.padding(padding)
        ) {
            LazyColumn(Modifier.background(color = Purple80), listState) {

                item {
                    Text(text = "В конец списка ↓↓\n______________________",
                        fontSize = 22.sp,
                        modifier = Modifier
                            .padding(12.dp)
                            .clickable {
                                coroutineScope.launch {
                                    listState.animateScrollToItem(persons.lastIndex)
                                }
                            })
                }


                groups.forEach { (position, name) ->
                    stickyHeader {
                        Text(
                            modifier = Modifier
                                .padding(0.dp, 12.dp, 0.dp, 0.dp)
                                .background(PurpleGrey40),
                            text = position,
                            fontSize = 48.sp
                        )
                    }

                    items(name) { person ->
                        Text(
                            text = "${person.fName} ${person.sName}",
                            fontSize = 32.sp
                        )
                    }
                }

                item {
                    Text(text = "______________________\nВ начало списка ↑↑",
                        fontSize = 22.sp,
                        modifier = Modifier
                            .padding(12.dp)
                            .clickable {
                                coroutineScope.launch {
                                    listState.animateScrollToItem(0)
                                }
                            })
                }
            }
        }
    })
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Preview() {
    Start()
}