- FUNDAMENTOS DOS LAYOUTS DO COMPOSE PARA AUXILIAR NO DESENVOLVIMENTO:
Para criar um layout e modificar uma compose deve-se seguir a seguinte estrutura de código:

Layout {
image(..)
Layout(){
Text(...)
Text(...)
}
}

_________________________________________________________________________________________________________________________
- DIFERENÇA DE LAYOUT COM: Arrangement

Arrangement são as formas como os filhos do layouts se comportam no eixo principal tanto para row quanto para column.
Estas posições são definidas conforme o uso de row ou de column em ambos os casos:

@Composable
fun ArtistCardArrangement(artist: Artist) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {
        Image(bitmap = artist.image, contentDescription = "Artist image")
        Column { /*...*/ }
    }
}
________________________________________________________________________________________________________________________
- MODIFICADORES EM LAYOUTS
Os modificadores decoram ou aumentam as funções para personalizar o seu layout.
Não se esquecer que a ordem dos modificadores são muito importantes, cada uma delas realiza uma mudança, elas afetam 
o resultado final, um exemplo esta abaixo como exemplo de visualizar a situação:

Modifier
            .padding(padding)
            .clickable(onClick = onClick)
            .fillMaxWidth()
---------------------------------------------------------------------------------------------------------------------
Abaixo está outro exemplo de como podemos utilizar a composição dos layouts pai em um layout filho
caso queira deixar uma estilização diferente.

@Composable
fun ArtistCard(/*...*/) {
    Row(
        modifier = Modifier.size(width = 400.dp, height = 100.dp)
    ) {
        Image(
            /*...*/
            modifier = Modifier.fillMaxHeight()
        )
        Column { /*...*/ }
    }
}
__________________________________________________________________________________________________________________________
DEMAIS CONTEÚDOS QUE PODEM APARECER AO MENCIONAR EM MODIFICADORES/LAYOUT:
