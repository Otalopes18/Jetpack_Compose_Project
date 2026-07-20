package com.example.prototipostudy_1

import android.content.res.Configuration
import android.os.Bundle
import android.widget.GridLayout
import androidx.compose.foundation.layout.Column
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.border
import androidx.compose.foundation.content.contentReceiver
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.contextmenu.modifier.filterTextContextMenuComponents
import androidx.compose.material3.Button
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
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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

@Composable
fun BottonsV(name:String ="volume",modifier: Modifier){
   Column(modifier = Modifier
       .padding(20.dp)
       .border(2.dp,Color.Green)

   ){
      Text(text = name )
   }}
//REGRA PARA A EXIBIÇÃO DOS QUADRADOS DENTRO DA TELA
@Composable
fun ViewResponsible(modifier: Modifier = Modifier) {
    val configuration = LocalConfiguration.current
    val isPortrait = configuration.orientation == Configuration.ORIENTATION_PORTRAIT
    val All = remember { List(9) { index -> "$index" } }
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
                    .padding(horizontal =2.dp, vertical = 2.dp)
                    .aspectRatio(1f)
                    .background(Color(0xFF696969),shape =RoundedCornerShape(2.dp))
                    .border(2.dp,Color(0xFF050404),
                        shape = RoundedCornerShape(15.dp)
                    )
                    .fillMaxWidth(0.5f)
                    .fillMaxHeight(0.5f)


            )
         //Gravadores

         { BottonsV(name ="",modifier = Modifier.fillMaxWidth())
                 Column(modifier = Modifier
                     .border(width = 10.dp,color = Color(0xFF828282))
                     .padding(20.dp),
                 ){
                     Row(modifier = Modifier.fillMaxWidth()

                     )
                     {//icônes
                     }
                         Text("Gravador $item",color = Color.White, fontSize = 18.sp

                         )
                     }
             }
            }
        }
    }


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PrototipoStudy_1Theme {
        ViewResponsible()
    }
}
//Aprofundar com o uso de grid
//Verificar o local.configuration