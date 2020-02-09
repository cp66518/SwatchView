# SwatchView
An android library that creates a swatch for the product detail page to select the color. it has some feature mentioned below:

- Easy to use, just you have to add the SwatchView to your activity layout and load the dataset from ViewModel/activity or any other class.
- SwatchView background color option to match your theme color.
- Adjust the position swatch label (Color: Blue) to Left|Center|Right, fonts, text color, and size.
- Color can be displayed in 2 shape oval or rectangle.
- You can change the selector stroke width and color.
## Download
Gradle:
### Step 1: Add it to your root build.gradle at the end of repositories:
allprojects {
repositories {
...
maven { url 'https://jitpack.io' }
}
}
### Step 2: Add dependency to app level build.gradle file.

dependencies {
implementation 'com.github.cp66518:SwatchView:V0.1.0'
}

## Screenshot
![swatchview](https://user-images.githubusercontent.com/31465586/74104629-88b7db80-4b7c-11ea-9c84-676ab8812be0.png)

## Usage
In layout: 

```XML
<com.csp.library.view.SwatchView
        android:id="@+id/swatchView"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        />
      
  <com.csp.library.view.SwatchView
        android:id="@+id/swatchView"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:color_stroke_width="2"
        app:label_alignment="left"
        app:label_color_text="my color:"
        app:label_color_text_color="#000000"
        app:label_color_text_size="16sp"
        app:label_selected_color_text_color="#000000"
        app:label_selected_color_text_size="16sp"
        app:no_color_placeholder="Please select color"
        app:selected_stroke_color="#000000"
        app:selected_stroke_width="2"
        app:shape="oval"
        app:swatch_background_color="#F3F0F0" />
```      

In activity/fragment/viewmodel/other class

```kotlin
var swatchData = ArrayList<SwatchModel>()
swatchData.add(SwatchModel("color 1", "#f44336"))
        
// pass arraylist to load the color data
swatchView.loadSwatchData(swatchData)

// To change the font of color label
var typeface = Typeface.createFromAsset(assets, "fonts/roboto_slab.ttf")
swatchView.setColorLabelFont(typeface)

// To change the font for selected color value   
var selectedColorTypeface = Typeface.createFromAsset(assets, "fonts/roboto_bold.ttf")
swatchView.setSelectedColorLabelFont(selectedColorTypeface)
      
// To listen color selection
swatchView.setOnSwatchColorSelectedListener(object :
OnSwatchColorSelectedListener {
  override fun onSwatchColorSelected(selectedColor: SwatchModel) {
    // todo do extra
  }
})
```
## Custom attribute information:
          
|Attributes name				  |Purpose |
|--------------------------------|--------|
|shape                   		 |	change the shape of color block items; currently only 2 shape available oval and rectangle.|
|selected_stroke_color   		 |	change the border color or selection.|
|selected_stroke_width   		 |	modify the border stroke width.|
|color_stroke_width	     		 | set the stroke width of color.|
|swatch_background_color 		 |	Change the background color of entier swatch layout.|
|label_alignment 		 		 | The label i.e (color label :Blue) can be align in left/center/right.|
|label_color_text_size   		 |	Adjust label(color label:Blue) text size.|
|label_color_text        		 | 	Adjust label(color label:Blue) text.|
|label_color_text_color          | 	Adjust label(color label:Blue) text color.|
|label_selected_color_text_size  | 	Adjust label(color label:Blue) text size.|
|label_selected_color_text_color | 	Adjust label(color label: Blue) text color.|
|no_color_placeholder 			 |	Set placeholder when no color selected color label.|
