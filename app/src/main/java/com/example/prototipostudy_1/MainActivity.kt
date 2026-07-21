@file:OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
package com.example.prototipostudy_1
import android.content.res.Configuration
import android.os.Bundle
import androidx.compose.material3.adaptive.currentWindowSize
import androidx.compose.foundation.layout.Column
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.border
import androidx.compose.foundation.content.contentReceiver
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Icon
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
import com.example.prototipostudy_1.ui.theme.PrototipoStudy_1Theme
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
        //VISUALIZAÇÃO DAS FUNÇÕES
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
//gravador
@Composable
fun TextRecorder(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .padding(4.dp)
            .border(width = 2.dp, color = Color(0xFF828282))
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        )
        {//icônes
            Text(
                "Gravador",
                color = Color.White,
                fontSize = 18.sp
            )
        }

    }
}
//REGRA PARA A EXIBIÇÃO DOS QUADRADOS DENTRO DA TELA
@Composable
fun ViewResponsible(modifier: Modifier = Modifier) {
    val configuration = LocalConfiguration.current
    val isPortrait = configuration.orientation == Configuration.ORIENTATION_PORTRAIT
    val All = remember { List(9){ index ->} }
    val colunas = if (isPortrait) 3 else 4

    val Exibition = if (isPortrait) {
        All
    } else {
        All.take(8)
    }
    //ESTILIZAÇÃO DO QUADRADO PAI
    LazyVerticalGrid(
        columns = GridCells.Fixed(colunas),
        modifier = modifier.fillMaxSize().padding(5.dp),
        horizontalArrangement = Arrangement.spacedBy(2.dp),
        verticalArrangement = Arrangement.spacedBy(2.dp),
        contentPadding = PaddingValues(5.dp),
    )
    {
        items(Exibition) { item ->
            Box(
                modifier = Modifier
                    .padding(horizontal = 2.dp, vertical = 2.dp)
                    .aspectRatio(1f)
                    .background(Color(0xFF696969), shape = RoundedCornerShape(15.dp))
                    .border(
                        2.dp, Color(0xFF050404),
                        shape = RoundedCornerShape(15.dp)
                    )
                    .fillMaxWidth(0.5f)
                    .fillMaxHeight(0.5f)
            )
            {
                TextRecorder()
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