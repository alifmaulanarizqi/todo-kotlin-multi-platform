package org.alifmaulanarizqi.todokmp.presentation.screen.task

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.alifmaulanarizqi.todokmp.util.Alpha
import org.alifmaulanarizqi.todokmp.util.Resource
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskScreen(
    taskId: String?,
    navigateToBack: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Task") },
                navigationIcon = {
                    IconButton(
                        onClick = navigateToBack
                    ) {
                        Icon(
                            painter = painterResource(Resource.Icon.BACK_ARROW),
                            contentDescription = "Back arrow icon"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .windowInsetsPadding(WindowInsets.ime)
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(all = 16.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                TaskInputSection(
                    title = "Task Title",
                    value = "",
                    onValueChange = {},
                    placeHolder = "Enter task title...",
                    isRequired = true
                )

                TaskInputSection(
                    title = "Task Description",
                    value = "",
                    onValueChange = {},
                    placeHolder = "Enter task description...",
                    isRequired = false,
                    maxLine = 6,
                    minLine = 3
                )
            }
            Box(
                modifier = Modifier.padding(16.dp)
            ) {
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    onClick = {}
                ) {
                    Text(
                        text = if(taskId != null) "Update Task" else "Create Task",
                    )
                }
            }
        }
    }
}

@Composable
fun TaskInputSection(
    modifier: Modifier = Modifier,
    title: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeHolder: String,
    isRequired: Boolean = false,
    minLine: Int = 1,
    maxLine: Int = 1,
) {
    Column(
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium
            )
            if(isRequired) {
                Text(
                    text = " *",
                    color = MaterialTheme.colorScheme.error,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(6.dp))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = value,
            onValueChange = onValueChange,
            placeholder = {
                Text(
                    text = placeHolder,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = Alpha.HALF)
                )
            },
            shape = RoundedCornerShape(size = 12.dp),
            minLines = minLine,
            maxLines = maxLine
        )
    }
}