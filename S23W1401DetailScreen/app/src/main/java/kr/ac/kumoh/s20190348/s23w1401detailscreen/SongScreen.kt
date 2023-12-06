package kr.ac.kumoh.s20190348.s23w1401detailscreen
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

enum class SongScreen {
    List,
    Detail
}

@Composable
fun SongApp()
{
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = SongScreen.List.name
    ) {
        composable(SongScreen.List.name) {
            SongList(navController)
        }
        composable(
                route = SongScreen.Detail.name + "/{songId},{songGenre}",
                arguments = listOf(
                    navArgument("songId") { type = NavType.StringType },
                    navArgument("songGenre") { type = NavType.StringType }
                )
            ) {
            SongDetail(
                it.arguments?.getString("songId"),
                it.arguments?.getString("songGenre")
            )
        }
    }
}

@Composable
fun SongList(navController: NavController)
{
    LazyColumn()
    {
        items(50)
        {
            Button(
                modifier = Modifier
                    .fillMaxWidth(),
                onClick = {
                    navController.navigate("${SongScreen.Detail.name}/$it,발라드")
                }) {
                Text("노래 $it")
            }
        }
    }
}

@Composable
fun SongDetail(songId: String?, songGenre: String?)
{
    Column(

    ){
        Text("노래${songId} 소개", fontSize = 30.sp)
        Text("장르 : $songGenre")
    }
}