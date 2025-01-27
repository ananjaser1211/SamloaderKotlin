package tk.zwander.commonCompose.view.components

import androidx.compose.foundation.layout.heightIn
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import tk.zwander.common.util.PatreonSupportersParser
import tk.zwander.common.util.SupporterInfo
import tk.zwander.common.util.invoke
import tk.zwander.samloaderkotlin.resources.MR

@Composable
internal fun PatreonSupportersDialog(
    showing: Boolean,
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit
) {
    val supporters = remember { mutableStateListOf<SupporterInfo>() }

    LaunchedEffect(key1 = null) {
        supporters.clear()

        try {
            supporters.addAll(PatreonSupportersParser.getInstance().parseSupporters())
        } catch (_: Exception) {}
    }

    AlertDialogDef(
        showing = showing,
        modifier = modifier,
        onDismissRequest = onDismissRequest,
        buttons = {
            TextButton(
                onClick = onDismissRequest
            ) {
                Text(text = MR.strings.ok())
            }
        },
        title = {
            Text(text = MR.strings.patreonSupporters())
        },
        text = {
            PatreonSupportersList(
                supporters = supporters,
                modifier = Modifier.heightIn(min = 200.dp)
            )
        }
    )
}
