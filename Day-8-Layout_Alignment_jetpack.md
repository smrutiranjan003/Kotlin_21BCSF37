# Jetpack Compose Layouts and UI Elements

## Overview

Layouts define the structure of your UI by arranging and positioning composables (views) within a hierarchy.

### Key Concepts:

1. **Parent View (Container):**
    - A layout composable that can contain one or more child views.
    - Examples: `Column`, `Row`, `Box`, `ConstraintLayout`, etc.

2. **Child View:**
    - Individual components or composables nested within a parent layout.
    - Examples: `Text`, `Image`, `Button`, etc.

3. **Arrangement:**
    - Determines how child views inside a parent view are positioned relative to each other.
    - Can be horizontal (`Row`), vertical (`Column`), or overlapped (`Box`).

## Common Layout Types:

### 1. **Row:**
- **Description:** Horizontal arrangement of child composables.
- **Use Case:** Suitable for arranging items side by side, like buttons in a toolbar or navigation items.

```kotlin
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun RowExample() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "Item 1")
        Text(text = "Item 2")
        Text(text = "Item 3")
    }
}
```

### 2. **Column:**
- **Description:** Vertical arrangement of child composables.
- **Use Case:** Ideal for stacking items vertically, such as a list of items or a form layout.

```kotlin
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ColumnExample() {
    Column(
        modifier = Modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Item 1")
        Text(text = "Item 2")
        Text(text = "Item 3")
    }
}
```

### 3. **Box:**
- **Description:** Allows for stacking child composables on top of each other.
- **Use Case:** Useful for overlaying UI elements, creating layered effects, or implementing complex custom layouts.

```kotlin
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun BoxExample() {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Bottom Layer",
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(Color.Red)
        )
        Text(
            text = "Middle Layer",
            modifier = Modifier
                .padding(16.dp)
                .background(Color.Green)
        )
        Text(
            text = "Top Layer",
            modifier = Modifier
                .padding(32.dp)
                .background(Color.Blue)
        )
    }
}
```

## Compose Modifiers:

Modifiers are functions that modify the behavior or appearance of composables. They are applied to composables using the `modifier` parameter. 

### Common Modifiers:

```kotlin
@Composable
fun PaddedText() {
    Text(
        text = "Hello, World!",
        modifier = Modifier.padding(16.dp)
    )
}

@Composable
fun TextWithBackground() {
    Text(
        text = "Jetpack Compose",
        modifier = Modifier.background(color = Color.Blue)
    )
}

@Composable
fun ClickableText(onClick: () -> Unit) {
    Text(
        text = "Click Me!",
        modifier = Modifier.clickable { onClick() }
    )
}

@Composable
fun FullWidthText() {
    Text(
        text = "Full Width Text",
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun FullHeightText() {
    Text(
        text = "Full Height Text",
        modifier = Modifier.fillMaxHeight()
    )
}
```

### Custom Modifiers:

Custom modifiers encapsulate a set of modifier operations for reusability and readability.

```kotlin
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

// Define a custom modifier function
fun Modifier.customModifier(): Modifier {
    return this.then(
        Modifier.padding(8.dp).background(Color.Gray)
    )
}

// Usage example of the custom modifier
@Composable
fun CustomModifierExample() {
    // Apply the custom modifier to a composable
    Box(
        modifier = Modifier.customModifier()
    ) {
        // Your composable content here
    }
}
```

## Buttons:

Jetpack Compose provides various button composables that can be customized with text, icons, colors, and click listeners.

### Types of Buttons:

- **Button:** The basic button composable with text.
- **TextButton:** A button with only text and no background.
- **OutlinedButton:** A button with an outlined border.
- **IconButton:** A button with an icon and optional text.
- **FloatingActionButton:** A round button typically used for primary actions.
- **DropdownMenuButton:** A button that opens a dropdown menu when clicked.
- **Checkbox:** A button-like element used for toggling a binary state.
- **RadioButton:** A button-like element used for selecting one option from a group.
- **Switch:** A button-like element used for toggling between two states.
- **ToggleableIconButton:** A button with an icon that toggles between two states.
- **Slider:** A button-like element used for selecting a value from a range by sliding.
- **RangeSlider:** A button-like element used for selecting a range of values by sliding.
- **DatePicker:** A button-like element used for selecting a date.
- **TimePicker:** A button-like element used for selecting a time.
- **CalendarDatePicker:** A button-like element used for selecting a date from a calendar view.

## Dialogs:

Dialogs in Jetpack Compose are implemented using `AlertDialog` or custom composables. They are used to display important messages, notifications, or to prompt users for input.

## Interruptive UI:

Interruptive UI refers to elements that demand immediate attention from the user, often by overlaying the current content or pausing ongoing tasks. Examples include dialogs, toasts, and snackbars. These elements are crucial for communicating critical information to users, such as errors, warnings, or important messages.
