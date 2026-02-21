package org.alifmaulanarizqi.todokmp.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import org.alifmaulanarizqi.todokmp.domain.ToDoTask
import org.alifmaulanarizqi.todokmp.util.Alpha

@Composable
fun TaskCard(
    modifier: Modifier = Modifier,
    task: ToDoTask,
    onCLick: (taskId: String) -> Unit,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(6.dp))
            .clickable {
                onCLick(task.id)
            },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer
        ),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.Top
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    modifier = Modifier
                        .alpha(if(task.isCompleted) Alpha.HALF else Alpha.FULL),
                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                    text = task.title,
                    style = TextStyle(
                        fontWeight = FontWeight.Medium,
                        textDecoration = if(task.isCompleted) TextDecoration.LineThrough else TextDecoration.None
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                if(task.description.isNotBlank())
                    Text(
                        modifier = Modifier
                            .alpha(if(task.isCompleted) Alpha.HALF else Alpha.FULL),
                        text = task.description,
                        fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                    )
            }
            PriorityChip(
                priority = task.priority,
                isCompleted = task.isCompleted
            )
        }
    }
}