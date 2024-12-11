package com.example.fesenko

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.Placeholder
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.fesenko.ui.theme.FesenkoTheme

@Composable
fun Place(){
    FesenkoTheme(){
        Surface(
            modifier = Modifier.fillMaxSize(),
        ) {
            val imageId = arrayOf(
                R.drawable.img,
                R.drawable.img_1,
                R.drawable.img_2,
                R.drawable.img_3,
                R.drawable.img_4,
                R.drawable.img_5,
            )

            val names = arrayOf(
                "Уютный дом у леса",
                "Участок у озера",
                "Загородный дом с баней",
                " Уютный дом для дачи",
                "Современный дом ",
                "Дачный участок в экологически чистом районе"
            )

            val description = arrayOf(
                "Продается уютный загородный дом в живописном месте, у самого леса! Площадь 120 м², 3 спальни, просторная кухня и гостиная с камином. Участок 15 соток, ухоженный сад и место для барбекю. Идеально для семейных выходных и летнего отдыха. До города всего 30 минут на машине! Не упустите шанс перестать мечтать и начать жить на природе!",
                "участок 10 соток в живописном месте на берегу озера. Отличное место для строительства собственного дома мечты! Тишина, свежий воздух и великолепные виды. Электричество и вода на границе участка. Поблизости развитая инфраструктура: магазины, школы, автобусные остановки. Отличная инвестиция для будущего. Звони, чтобы узнать больше!",
                "стильный загородный дом с баней в 20 км от города. Дом площадью 150 м², 4 спальни, кухня-студия и два санузла. Участок 12 соток с красивым ландшафтным дизайном и зоной для отдыха. Идеальное место для дачного отдыха с семьей и друзьями. У вас будет возможность насладиться спокойствием природы и хорошей компанией!",
                "Сдается в аренду уютный дом для дачного отдыха! 2 спальни, кухня, столовая и терраса для барбекю. Участок 8 соток, полностью огорожен. Находится в 15 минутах от города, рядом с лесом и речкой. Идеально подходит для семейных выходных или романтических побегов. Доступен в течение всего года. Звоните, чтобы узнать больше!",
                "современный дом с потрясающим видом на холмы. Общая площадь 180 м², 3 спальни, гостиная с панорамными окнами. Участок 20 соток с ландшафтным дизайном, бассейном и зоной для отдыха. Удаленность от города всего 40 минут. Идеальное решение для тех, кто хочет жить в гармонии с природой, не отказываясь от городских удобств. Звоните прямо сейчас!",
                "Продается дачный участок 6 соток в экологически чистом районе. Участок с ровным рельефом, подходит для строительства дома или дачи. Вокруг густые леса и чистые пруды. Доступ к электричеству и водоснабжению. Отличное место для тех, кто ищет спокойствия и уединения вдали от городской суеты. Позвоните, чтобы назначить просмотр! ",
                )

            val rating = arrayOf(
                "оценка пользователей: 4.0/5",
                "оценка пользователей: 3.0/5",
                "оценка пользователей: 4.4/5",
                "оценка пользователей: 4.5/5",
                "оценка пользователей: 2.3/5",
                "оценка пользователей: 4.0/5"
            )

            val navController = rememberNavController()
            NavHost (navController = navController, startDestination = "MainScreen") {
                composable(route = "MainScreen") {
                    PlaceScreen(imageId, names, description, rating, navController)
                }
                composable(route = "DetailScreen/{index}",
                    arguments = listOf(
                        navArgument(name = "index") {
                            type = NavType.IntType
                        }
                    )
                ) { index ->
                    DetailScreen(
                        photos = imageId,
                        names = names,
                        description = description,
                        rating = rating,
                        itemIndex = index.arguments?.getInt("index")
                    )
                }
            }
        }
    }

}