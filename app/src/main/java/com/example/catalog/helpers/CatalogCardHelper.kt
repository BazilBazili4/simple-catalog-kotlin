package com.example.catalog.helpers

import android.content.Context
import android.content.res.Resources
import android.util.TypedValue
import android.widget.*
import com.example.catalog.R


class CatalogCardHelper {

    fun createCardLayout(cardId: Int, context: Context): LinearLayout {
        val r: Resources = context.resources
        val layout = LinearLayout(context)
        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        params.setMargins(
            convertDpToPixels(8, context),
            convertDpToPixels(8, context),
            convertDpToPixels(8, context),
            convertDpToPixels(8, context)
        )
        layout.layoutParams = params
        layout.setBackgroundResource(R.drawable.rounded_button_white)
        layout.orientation = LinearLayout.VERTICAL

        return layout
    }

    fun createRelativeLayout(context: Context): RelativeLayout {
        val layout = RelativeLayout(context)
        val params = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.MATCH_PARENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT
        )
        layout.layoutParams = params
        return layout
    }

    fun createImageView(context: Context): ImageView {
        val imageView = ImageView(context)
        val params = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.MATCH_PARENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT
        )
        imageView.layoutParams = params
        imageView.setBackgroundResource(R.drawable.ic_baseline_photo_camera_24)
        return  imageView
    }

    fun createFavoritesCheckbox(context: Context, isChecked: Boolean): CheckBox {
        val checkBox = CheckBox(context)
        val params = RelativeLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT)
        params.setMargins(
            convertDpToPixels(10, context),
            convertDpToPixels(10, context),
            convertDpToPixels(10, context),
            convertDpToPixels(10, context)
        )
        if (isChecked) {
            checkBox.setBackgroundResource(R.drawable.ic_heart_filled)
        } else {
            checkBox.setBackgroundResource(R.drawable.ic_heart_empty)
        }
        checkBox.layoutParams = params
        return checkBox
    }

    fun createTitle(context: Context, text: String): TextView {
        val textView = TextView(context)
        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        params.setMargins(
            convertDpToPixels(10, context),
            convertDpToPixels(5, context),
            convertDpToPixels(10, context),
            convertDpToPixels(0, context)
        )
        textView.layoutParams = params
        textView.setTextColor(context.resources.getColor(R.color.generalText))
        textView.textSize = 28F
        textView.text = text
        return  textView
    }

    fun createDescription(context: Context, text: String): TextView {
        val textView = TextView(context)
        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        params.setMargins(
            convertDpToPixels(10, context),
            convertDpToPixels(5, context),
            convertDpToPixels(10, context),
            convertDpToPixels(0, context)
        )
        textView.layoutParams = params
        textView.setTextColor(context.resources.getColor(R.color.secondaryText))
        textView.textSize = 20F
        textView.text = text
        return  textView
    }

    fun createCatalogCard(context: Context): LinearLayout {
        val imageLayout = createRelativeLayout(context)
        val imageView = createImageView(context)
        val favoritesCheckbox = createFavoritesCheckbox(context, true)
        imageLayout.addView(imageView)
        imageLayout.addView(favoritesCheckbox)
        val title = createTitle(context, "СОШ №27 Школа")
        val description = createDescription(context, "Мини описание, возможно, даже в две строки")
        val cardLayout = createCardLayout(1, context)
        cardLayout.addView(imageLayout)
        cardLayout.addView(title)
        cardLayout.addView(description)

        return cardLayout
    }

    private fun convertDpToPixels(dpValue: Int, context: Context): Int {
        val r: Resources = context.resources
        val px = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dpValue.toFloat(),
            r.displayMetrics
        ).toInt()

        return px
    }
    
}