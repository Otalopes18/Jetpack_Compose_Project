@file:OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
package com.example.prototipostudy_1
import android.content.res.Configuration
import android.os.Bundle
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.adaptive.currentWindowSize
import androidx.compose.foundation.layout.Column
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextAlign
import com.example.prototipostudy_1.ui.theme.ExtraColors
import com.example.prototipostudy_1.ui.theme.PrototipoStudy_1Theme
import com.example.prototipostudy_1.ui.theme.Cores
import com.example.prototipostudy_1.ui.theme.ExtraColors
import androidx.compose.runtime.toMutableStateList

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PrototipoStudy_1Theme {
                PrototipoStudy_1App()
            }
        }
    }
}
//COMPOSIÇÃO PARA AS CHAMADAS DA COMPOSABLES
@PreviewScreenSizes
@Composable
fun PrototipoStudy_1App() {
    var currentDestination by rememberSaveable { mutableStateOf(AppDestinations.HOME) }
    NavigationSuiteScaffold(
        navigationSuiteItems = {
            AppDestinations.entries.forEach {
                item(
                    icon = {
                        Icon(
                            painterResource(it.icon),
                            contentDescription = it.label
                        )
                    },
                    label = { Text(it.label) },
                    selected = it == currentDestination,
                    onClick = { currentDestination = it }
                )
            }
        }
    ) {

        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            ViewResponsible(modifier = Modifier.padding(innerPadding),)
        }
    }
}
enum class AppDestinations(
    val label: String,
    val icon: Int,
) {
    HOME("Home", R.drawable.ic_home),
    FAVORITES("Favorites", R.drawable.ic_favorite),
    PROFILE("Profile", R.drawable.ic_account_box),
}
//Gravador
@Composable
fun TextRecorder(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .padding(32.dp)
            .border(width = 1.dp, color = Color.Black)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
            .height(20.dp)
            .width(20.dp)
        )
        {
            Text(
                "Gravador",

                textAlign = TextAlign.Right,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(end = 7.dp),
                color = Color.White,
                fontSize = 18.sp

            )
        }
    }
}
//REGRAS PARA A EXIBIÇÃO DOS QUADRADOS DA TELA
@Composable
fun ViewResponsible(modifier: Modifier = Modifier) {
    val allitems = remember { ExtraColors }
    val configuration = LocalConfiguration.current
    val isPortrait = configuration.orientation == Configuration.ORIENTATION_PORTRAIT
    val colunas = if (isPortrait) 3 else 4

    val Exibition = if (isPortrait) {
        allitems
    } else {
        allitems.take(8)
    }
    var corGlobal =Color.Red
    val estadosDeIndice = remember {
        List(Exibition.size) { -1 }.toMutableStateList()
    }
    //ESTILIZAÇÃO DO QUADRADO PAI
    LazyVerticalGrid(
        columns = GridCells.Fixed(colunas),
        modifier = modifier.fillMaxSize().padding(5.dp),
        horizontalArrangement = Arrangement.spacedBy(2.dp),
        verticalArrangement = Arrangement.spacedBy(2.dp),
        contentPadding = PaddingValues(5.dp),
    )
    {//regra para mudança de cor
            itemsIndexed(Exibition) { index,item->

                val indiceCorAtual = estadosDeIndice[index]
                val corExibida = if (indiceCorAtual  == -1) {
                    corGlobal
                } else {
                    allitems[indiceCorAtual].cor
                }
                val nomeExibido = if (indiceCorAtual == -1){
                    "Red"
                } else{
                    allitems[indiceCorAtual].nome
                }
            Box(
                modifier = Modifier.clickable{
                    estadosDeIndice[index] = (indiceCorAtual + 1) % allitems.size
                }
                    .aspectRatio(1f)
                    .padding(horizontal = 2.dp, vertical = 2.dp)
                    .background(color=corExibida, shape = RoundedCornerShape(10.dp))
                    .border(
                        2.dp, Color.Black,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .fillMaxWidth(0.5f)
                    .fillMaxHeight(0.5f)
            )
            {
                TextRecorder()
                Text(

                    textAlign = TextAlign.Center,
                    modifier = modifier
                        .fillMaxSize()
                        .offset(y = (-20).dp),
                    text = nomeExibido,
                    color = Color.White,


                    )
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun ViewResponsiblePreview(){
    PrototipoStudy_1Theme() {
        TextRecorder()
        ViewResponsible()
    }
}
//Aprofundar com o uso de grid
//Verificar o local.configuration