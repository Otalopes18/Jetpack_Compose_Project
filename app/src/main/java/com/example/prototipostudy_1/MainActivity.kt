@file:OptIn(ExperimentalMaterial3WindowSizeClassApi::class)

package com.example.prototipostudy_1

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.border
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.runtime.key
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.graphics.luminance
import com.example.prototipostudy_1.ui.theme.ExtraColors
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

//COMPOSIÇÃO DAS CHAMADAS DA COMPOSABLES
@PreviewScreenSizes
@Composable
fun PrototipoStudy_1App() {
    var currentDestination by rememberSaveable {
        mutableStateOf(AppDestinations.HOME)
    }
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
            ViewResponsible(modifier = Modifier.padding(innerPadding))
        }
    }
}

enum class AppDestinations(
    val label: String,
    val icon: Int
) {
    HOME("Home", R.drawable.ic_home),
    FAVORITES("Favorites", R.drawable.ic_favorite),
    PROFILE("Profile", R.drawable.ic_account_box),
}

//Gravador
@Composable
fun TextRecorder(
    corDoTexto: Color,
    modifier: Modifier = Modifier
) {
    Text(
        text = "Gravador",
        textAlign = TextAlign.Center,
        color = corDoTexto,
        fontSize = 12.sp,
        modifier = modifier
            .padding(10.dp)
            .border(2.dp, Color.Black, RoundedCornerShape(6.dp))
            .padding(horizontal = 30.dp, vertical = 2.dp)
    )
}

@Composable
fun NomeDaCor(
    texto: String,
    corDoTexto: Color,
    modifier: Modifier = Modifier
) {
    Text(
        text = texto,
        textAlign = TextAlign.Center,
        color = corDoTexto,
        fontSize = 14.sp,
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 6.dp)
    )
}

//REGRAS QUADRADOS
private const val totalQuadrados = 9
private fun posicaoParaEstado(posicaoVisivel: Int, quadradosTela: Int): Int {
    if (quadradosTela == totalQuadrados) {
        return posicaoVisivel
    }
    if (posicaoVisivel == 0) return 0
    if (posicaoVisivel == 1) return 1
    if (posicaoVisivel == 2) return 2
    if (posicaoVisivel == 3) return 3
    if (posicaoVisivel == 4) return 4
    if (posicaoVisivel == 5) return 5
    if (posicaoVisivel == 6) return 6
    if (posicaoVisivel == 7) return 7
    return 8
}
@Composable
fun ViewResponsible(modifier: Modifier = Modifier) {

    val todasAsCores = ExtraColors
    val configuration = LocalConfiguration.current
    val isPortrait =
        configuration.orientation == Configuration.ORIENTATION_PORTRAIT
    val quadradosTela = if (isPortrait) {
        9
    } else {
        8
    }
    val colunas = if (isPortrait) {
        3
    } else {
        4
    }
    var estadosDeIndice by rememberSaveable(key = "estados") {
        mutableStateOf(List(totalQuadrados) { 0 })
    }

    LaunchedEffect(isPortrait) {
        val estadosNaTela = (0 until quadradosTela)
            .map { posicaoParaEstado(it, quadradosTela) }
        Log.d(
            "debug_color",
            "TELA CRIADA | ${if (isPortrait) "RETRATO" else "PAISAGEM"} | " +
                    "visiveis=$quadradosTela | " +
                    "exibindo=${estadosNaTela.joinToString(",")} | " +
                    "oculto=${
                        (0 until totalQuadrados)
                            .filterNot { it in estadosNaTela }
                            .joinToString(",")
                            .ifEmpty { "nenhum" }
                    } | " +
                    "estados=${estadosDeIndice.joinToString(",")}"
        )
    }
    //ESTILIZAÇÃO DO QUADRADO PAI
    LazyVerticalGrid(
        columns = GridCells.Fixed(colunas),
        modifier = modifier
            .fillMaxSize()
            .padding(5.dp),
        horizontalArrangement = Arrangement.spacedBy(2.dp),
        verticalArrangement = Arrangement.spacedBy(2.dp),
        contentPadding = PaddingValues(5.dp)
    ) {
        //Mudança de cor
        items(
            count = quadradosTela,
            key = { posicao ->
                posicaoParaEstado(posicao, quadradosTela)
            }
        ) { posicao ->
            val indiceEstado = posicaoParaEstado(posicao, quadradosTela)
            val opcao = todasAsCores[estadosDeIndice[indiceEstado]]
            val corDoTexto = if (opcao.cor.luminance() > 0.5f) Color.Black else Color.White
            Box(
                modifier = Modifier
                    .aspectRatio(1f)
                    .padding(
                        horizontal = 2.dp,
                        vertical = 2.dp
                    )
                    .background(
                        opcao.cor,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .border(
                        2.dp, Color.Black,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .clickable {
                        val anterior = estadosDeIndice[indiceEstado]
                        val proximo = (anterior + 1) % todasAsCores.size
                        val novaLista = estadosDeIndice.toMutableList()
                        novaLista[indiceEstado] = proximo
                        estadosDeIndice = novaLista

                        Log.d(
                            "DEBUG",
                            "CLIQUE pos=$posicao estado=$indiceEstado | " +
                                    "${todasAsCores[anterior].nome}($anterior) -> " +
                                    "${todasAsCores[proximo].nome}($proximo) | " +
                                    "estados=${novaLista.joinToString(",")}"
                        )
                    },
                contentAlignment = Alignment.TopCenter
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    NomeDaCor(texto = opcao.nome, corDoTexto = corDoTexto)
                    TextRecorder(corDoTexto = corDoTexto)
                }
            }
        }
    }
}

@Preview(name = "Retrato", showBackground = true, widthDp = 360, heightDp = 640)
@Preview(name = "Paisagem", showBackground = true, widthDp = 720, heightDp = 360)
@Preview(showBackground = true)
@Composable
fun ViewResponsiblePreview() {
    PrototipoStudy_1Theme() {
        ViewResponsible()
    }
}