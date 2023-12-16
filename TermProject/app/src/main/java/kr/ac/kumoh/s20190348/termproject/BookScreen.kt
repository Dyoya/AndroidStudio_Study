package kr.ac.kumoh.s20190348.termproject

import android.content.Intent
import android.graphics.Paint.Align
import android.net.Uri
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavController
import androidx.navigation.NavType
import coil.compose.AsyncImage
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

enum class BookScreen {
    List,
    Detail
}

@Composable
fun BookApp(bookList: List<Book>) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = BookScreen.List.name,
    ) {
        composable(route = BookScreen.List.name) {
            BookList(bookList) {
                navController.navigate(it)
            }
        }
        composable(
            route = BookScreen.Detail.name + "/{index}",
            arguments = listOf(navArgument("index") {
                type = NavType.IntType
            })
        ) {
            val index = it.arguments?.getInt("index") ?: -1
            if (index >= 0)
                BookDetail(bookList[index])
        }
    }
}

@Composable
fun BookList(list: List<Book>, onNavigateToDetail: (String) -> Unit)
{
    Column (
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text("2022년 베스트셀러!", fontSize = 30.sp, fontWeight = FontWeight.Bold)
        LazyColumn (
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(horizontal = 8.dp)
        ){

            items(list.size) {
                BookItem(it, list[it], onNavigateToDetail)
            }
        }
    }
}

@Composable
fun BookItem(index: Int, book: Book, onNavigateToDetail: (String) -> Unit)
{
    Card(
        modifier = Modifier.clickable {
            onNavigateToDetail(BookScreen.Detail.name + "/$index")
        },
        elevation = CardDefaults.cardElevation(8.dp)
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
                .background(Color(255, 255, 0, 60))
                .padding(8.dp)
        ) {
            AsyncImage(
                model = "https://port-0-s23smartappproject-gj8u2llomircso.sel5.cloudtype.app/images/${book.id}.jpg",
                contentDescription = "책 커버 이미지",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(150.dp)
                    .width(100.dp)
                    .clip(RoundedCornerShape(percent = 10)),
            )
            Spacer(modifier = Modifier.width(10.dp))
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                TextTitle(book.title)
                TextGenre(book.genre)
                TextAuthor(book.author)
            }
        }
    }
}

@Composable
fun TextTitle(title: String) {
    Text(title, fontSize = 24.sp, lineHeight = 35.sp, fontWeight = FontWeight.Bold)
}

@Composable
fun TextGenre(genre: String) {
    Text("장르 : ${genre}", fontSize = 20.sp)
}

@Composable
fun TextAuthor(author: String) {
    Text("저자 : ${author}", fontSize = 20.sp)
}

@Composable
fun BookDetail(book: Book) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(book.title, fontSize = 40.sp, textAlign = TextAlign.Center, lineHeight = 45.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(16.dp))

        RatingBar(book.rating)

        Spacer(modifier = Modifier.height(16.dp))

        AsyncImage(
            model = "https://port-0-s23smartappproject-gj8u2llomircso.sel5.cloudtype.app/images/${book.id}.jpg",
            contentDescription = "책 커버 이미지",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(600.dp)
                .width(400.dp)
                .clip(RoundedCornerShape(percent = 10)),
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text("장르 : ${book.genre}", fontSize = 30.sp)
        Text("작가 : ${book.author}", fontSize = 30.sp)
        Text("출판일 : ${book.publication_date}", fontSize = 24.sp)

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val intent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://search.kyobobook.co.kr/search?keyword=${book.title}")
                )
                startActivity(context, intent, null)
            },
            modifier = Modifier
                .width(600.dp)
                .padding(30.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            )
            {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = null
                )
                Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
                Text("교보문고 검색")
            }
        }
    }
}

@Composable
fun RatingBar(stars: Int)
{
    Row {
        repeat(stars) {
            Icon(
                imageVector = Icons.Filled.Favorite,
                contentDescription = "stars",
                modifier = Modifier.size(32.dp),
                tint = Color.Red
            )
        }
        repeat(10-stars) {
            Icon(
                imageVector = Icons.Outlined.FavoriteBorder,
                contentDescription = "stars",
                modifier = Modifier.size(32.dp),
                tint = Color.Red
            )
        }
    }
}