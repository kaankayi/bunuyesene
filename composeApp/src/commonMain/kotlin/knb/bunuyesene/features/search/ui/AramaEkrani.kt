    package knb.bunuyesene.features.search.ui
    
    import androidx.compose.foundation.background
    import androidx.compose.foundation.clickable
    import androidx.compose.foundation.layout.Arrangement
    import androidx.compose.foundation.layout.Column
    import androidx.compose.foundation.layout.Row
    import androidx.compose.foundation.layout.Spacer
    import androidx.compose.foundation.layout.fillMaxSize
    import androidx.compose.foundation.layout.fillMaxWidth
    import androidx.compose.foundation.layout.height
    import androidx.compose.foundation.layout.padding
    import androidx.compose.foundation.layout.size
    import androidx.compose.foundation.lazy.LazyColumn
    import androidx.compose.foundation.lazy.items
    import androidx.compose.foundation.shape.RoundedCornerShape
    import androidx.compose.material.icons.Icons
    import androidx.compose.material.icons.automirrored.filled.ArrowBack
    import androidx.compose.material.icons.filled.Schedule
    import androidx.compose.material.icons.filled.Search
    import androidx.compose.material.icons.filled.Star
    import androidx.compose.material3.ExperimentalMaterial3Api
    import androidx.compose.material3.Icon
    import androidx.compose.material3.IconButton
    import androidx.compose.material3.MaterialTheme
    import androidx.compose.material3.OutlinedTextField
    import androidx.compose.material3.OutlinedTextFieldDefaults
    import androidx.compose.material3.Scaffold
    import androidx.compose.material3.Text
    import androidx.compose.material3.TopAppBar
    import androidx.compose.material3.TopAppBarDefaults
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
    import coil3.compose.AsyncImage
    import org.koin.compose.viewmodel.koinViewModel
    import knb.bunuyesene.features.common.domain.entities.TarifItem

    @Composable
    fun AramaRoute(
        navigateToDetay: (Long) -> Unit,
        onBackPress: () -> Unit,
        aramaViewModel: AramaViewModel = koinViewModel()
    ) {
        val ekranState = aramaViewModel.aramaEkraniUiState.collectAsState()
        val aramaText = aramaViewModel.aramaText.collectAsState()

        AramaEkrani(
            aramaText = aramaText.value,
            onSearchTextChanged = {
                aramaViewModel.onSearchQueryChanged(it)
            },
            aramaEkraniState = ekranState.value,
            onTarifItemClicked = {
                navigateToDetay(it.id)
            },
            onBackPress = onBackPress
        )
    }


    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun AramaEkrani(
        aramaText: String,
        onSearchTextChanged: (String) -> Unit,
        aramaEkraniState: AramaEkraniState,
        onTarifItemClicked: (TarifItem) -> Unit,
        onBackPress: () -> Unit
    ) {

        Scaffold(topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                ),
                title = {
                    Text(
                        text = "Tarifleri Ara"
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = onBackPress
                    ) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
                    }
                }
            )
        }) { innerPadding ->

            Column(
                modifier = Modifier.fillMaxSize().padding(innerPadding),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                AramaEkraniContent(
                    aramaText, onSearchTextChanged, aramaEkraniState, onTarifItemClicked
                )
            }
        }

    }

    @Composable
    fun AramaEkraniContent(
        searchText: String,
        onSearchTextChanged: (String) -> Unit,
        aramaEkraniState: AramaEkraniState,
        onTarifItemClicked: (TarifItem) -> Unit,
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp).padding(bottom = 16.dp)
        ) {

            OutlinedTextField(
                shape = MaterialTheme.shapes.medium,
                value = searchText,
                onValueChange = {
                    onSearchTextChanged(it)
                },
                placeholder = {
                    Text(text = "Malzemeleri Ara...")
                },
                modifier = Modifier.background(
                    MaterialTheme.colorScheme.onPrimary
                ).fillMaxWidth(),
                singleLine = true,
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Search, contentDescription = null)
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = MaterialTheme.colorScheme.primaryContainer,
                    focusedLabelColor = MaterialTheme.colorScheme.primaryContainer,
                    cursorColor = MaterialTheme.colorScheme.primaryContainer,
                    focusedContainerColor = MaterialTheme.colorScheme.onPrimary,
                    unfocusedContainerColor = MaterialTheme.colorScheme.onPrimary,
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            when {
                aramaEkraniState.error != null -> {
                    Text(
                        text = "No Recipe Items found",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                aramaEkraniState.success && aramaEkraniState.results.isNotEmpty() -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                    ) {
                        items(aramaEkraniState.results, key = {
                            it.id
                        }) { item ->
                            AraTarifItem(item = item, onTarifItemClicked = onTarifItemClicked)
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun AraTarifItem(
        item: TarifItem,
        onTarifItemClicked: (TarifItem) -> Unit
    ) {

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth().clickable {
                onTarifItemClicked(item)
            }.padding(16.dp)
        ) {

            AsyncImage(
                model = item.imageUrl,
                onError = {
                    println("AsyncImage_onError=${it.result.throwable}")
                },
                modifier = Modifier.size(80.dp).clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop,
                contentDescription = null
            )

            Column {
                Text(
                    textAlign = TextAlign.Start,
                    text = item.title,
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
                            text = item.duration,
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
                            text = " ${item.rating}",
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.padding(start = 4.dp),
                        )
                    }
                }
            }
        }

    }