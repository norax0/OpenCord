package com.xinto.opencord.ui.screens.home.panels.guild

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import com.xinto.opencord.ui.components.guild.list.GuildsListHeaderItem
import com.xinto.opencord.ui.components.guild.list.GuildsListItemImage
import com.xinto.opencord.ui.components.guild.list.GuildsListTextItem
import com.xinto.opencord.ui.components.guild.list.RegularGuildItem
import com.xinto.opencord.ui.viewmodel.GuildsViewModel.GuildItem

@Composable
fun GuildsListLoaded(
    items: SnapshotStateList<GuildItem>,
    onGuildSelect: (Long) -> Unit,
    modifier: Modifier = Modifier,
) {
    // Get the context using LocalContext.current
    val context = LocalContext.current

    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(vertical = 8.dp),
    ) {
        items(items, key = { it.key }) { item ->
            when (item) {
                is GuildItem.Header -> {
                    RegularGuildItem(
                        modifier = Modifier.fillMaxWidth(),
                        selected = false,
                        showIndicator = false,
                        onClick = {
                            // Use the context here to show the toast
                            Toast.makeText(
                                context,
                                "debug",
                                Toast.LENGTH_LONG
                            ).show()
                        },
                    ) {
                        GuildsListHeaderItem()
                    }
                }

                is GuildItem.Divider -> {
                    Divider(
                        modifier = Modifier
                            .fillMaxWidth(0.55f)
                            .padding(bottom = 4.dp, top = 2.dp)
                            .clip(MaterialTheme.shapes.medium),
                        thickness = 2.dp,
                        color = MaterialTheme.colorScheme.outline,
                    )
                }

                is GuildItem.Guild -> {
                    val guild = item.data

                    RegularGuildItem(
                        modifier = Modifier.fillMaxWidth(),
                        selected = item.selected,
                        showIndicator = true,
                        onClick = { onGuildSelect(guild.id) },
                    ) {
                        if (guild.iconUrl != null) {
                            GuildsListItemImage(
                                url = guild.iconUrl,
                            )
                        } else {
                            GuildsListTextItem(
                                iconText = guild.iconText,
                            )
                        }
                    }
                }
            }
        }
    }
}
