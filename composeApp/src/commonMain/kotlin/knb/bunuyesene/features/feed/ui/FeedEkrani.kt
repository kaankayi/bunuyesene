package knb.bunuyesene.features.feed.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import org.koin.compose.viewmodel.koinViewModel
import knb.bunuyesene.features.common.domain.entities.TarifItem
import knb.bunuyesene.features.common.ui.components.ErrorContent
import knb.bunuyesene.features.common.ui.components.Loader


@Composable
fun FeedRoute(
    navigateToDetay: (Long) -> Unit,
    navigateToArama: () -> Unit,
    feedViewModel: FeedViewModel = koinViewModel()
) {

    val feedUiState = feedViewModel.feedUiState.collectAsState()

    FeedEkrani(
        navigateToDetay = navigateToDetay,
        feedUiState = feedUiState.value,
        navigateToArama = navigateToArama
    )

}


@Composable
fun FeedEkrani(
    navigateToDetay: (Long) -> Unit,
    feedUiState: FeedUiState,
    navigateToArama: () -> Unit,
) {

    val tarifler = feedUiState.tarifList
    Scaffold(
        topBar = {
            TopBar(navigateToArama)
        }
    ) { innerPadding ->
        when {
            feedUiState.tarifListIsLoading -> {
                Loader()
            }

            feedUiState.tarifListError != null -> {
                ErrorContent()
            }

            tarifler != null -> {
                FeedContent(innerPadding = innerPadding, tarifler = tarifler, navigateToDetay = navigateToDetay)
            }
        }

    }

}


@Composable
private fun TopBar(navigateToArama: () -> Unit) {
    Column(
        modifier = Modifier
            .windowInsetsPadding(WindowInsets.statusBars) //we need to consume status bars padding here, as we are not doing it on the NavHost becoz from there it will apply to all tab screen
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {

        Text(
            text = "Hi Alan!",
            color = MaterialTheme.colorScheme.primaryContainer,
            style = MaterialTheme.typography.titleMedium
        )

        Text(
            text = "Got a tasty dish in mind?",
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
        )

        AramaBari(
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp).height(45.dp)
                .background(MaterialTheme.colorScheme.onPrimary, shape = RoundedCornerShape(12.dp))
                .border(
                    width = 1.dp,
                    shape = RoundedCornerShape(12.dp),
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.3f)
                )
                .padding(horizontal = 16.dp)
                .clickable {
                    navigateToArama()
                },
        )

    }

}


@Composable
private fun AramaBari(
    modifier: Modifier = Modifier
) {

    Box(modifier = modifier, contentAlignment = Alignment.CenterStart) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Search any tarifler",
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onBackground.copy(
                    alpha = 0.7f
                )
            )
        }

    }


}

@Composable
private fun FeedContent(
    navigateToDetay: (Long) -> Unit,
    innerPadding: PaddingValues,
    tarifler: List<TarifItem>
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.padding(
            top = innerPadding.calculateTopPadding()
        )
    ) {
        item(
            span = { GridItemSpan(maxLineSpan) }
        ) {
            TopTariflerList(title = "Top Recommendations", tarifler = tarifler.reversed(), navigateToDetay = navigateToDetay)
        }

        haftaninTarifleri(
            title = "Recipes Of the Week", tarifler = tarifler,  navigateToDetay = navigateToDetay
        )

    }
}

@Composable
private fun TopTariflerList(
    navigateToDetay: (Long) -> Unit,
    title: String,
    tarifler: List<TarifItem>
) {
    Column {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium.copy(
                fontSize = 18.sp
            ),
            modifier = Modifier.padding(top = 16.dp, start = 16.dp)
        )

        LazyRow(
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(tarifler, key = { it.id }) { recipe ->
                val imageModifier =
                    Modifier.width(120.dp).height(140.dp).clip(RoundedCornerShape(16.dp))
                TarifCard(
                    recipe,
                    modifier = Modifier.width(110.dp),
                    imageModifier = imageModifier.clickable {
                        navigateToDetay(recipe.id)
                    }
                )
            }

        }

    }

}


private fun LazyGridScope.haftaninTarifleri(
    navigateToDetay: (Long) -> Unit,
    title: String,
    tarifler: List<TarifItem>
) {

    item(
        span = { GridItemSpan(maxLineSpan) }
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium.copy(
                fontSize = 18.sp
            ),
            modifier = Modifier.padding(top = 16.dp, start = 16.dp)
        )
    }

    itemsIndexed(tarifler, key = { _, it -> it.id }) { index, tarif ->

        val cardPaddingStart = if (index % 2 == 0) 16.dp else 0.dp
        val cardPaddingEnd = if (index % 2 == 0) 0.dp else 16.dp

        val imageModifier =
            Modifier.fillMaxWidth().height(130.dp).clip(RoundedCornerShape(16.dp))
        TarifCard(
            tarif,
            modifier = Modifier.padding(start = cardPaddingStart, end = cardPaddingEnd),
            imageModifier = imageModifier.clickable {
                navigateToDetay(tarif.id)
            }
        )
    }

}

@Composable
private fun TarifCard(
    recipe: TarifItem,
    modifier: Modifier,
    imageModifier: Modifier
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {

        AsyncImage(
            model = recipe.imageUrl,
            onError = {
                println("AsyncImage_onError=${it.result.throwable}")
            },
            modifier = imageModifier,
            contentScale = ContentScale.Crop,
            contentDescription = null
        )

        Text(
            textAlign = TextAlign.Start,
            text = recipe.title,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.fillMaxWidth().padding(top = 4.dp),
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.Medium
            )
        )

        Row(
            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
        ) {

            Row(
                modifier = Modifier.padding(end = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Schedule,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp)
                )
                Text(
                    text = recipe.duration,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(start = 4.dp)
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp),
                    tint = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f)
                )
                Text(
                    text = " ${recipe.rating}",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(start = 4.dp),
                )
            }
        }


    }

}