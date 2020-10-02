package com.example.catalog.services

import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.Drawable
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
        params.width = convertDpToPixels(40, context)
        params.height = convertDpToPixels(40, context)
        params.setMargins(
            convertDpToPixels(0, context),
            convertDpToPixels(10, context),
            convertDpToPixels(10, context),
            convertDpToPixels(0, context)
        )
        checkBox.isChecked = isChecked
        val checkboxDrawable: Drawable = context.resources.getDrawable(R.drawable.favorites_checkbox_selector)
        checkboxDrawable.mutate()
        checkBox.buttonDrawable = null
        checkBox.setBackgroundDrawable(checkboxDrawable)

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

    fun createContactsLayout(context: Context): TableLayout {
        val tableLayout = TableLayout(context)
        val params = TableLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )
        params.setMargins(
            convertDpToPixels(0, context),
            convertDpToPixels(5, context),
            convertDpToPixels(0, context),
            convertDpToPixels(0, context)
        )
        tableLayout.layoutParams = params
        return  tableLayout
    }

    fun createContactsRow(context: Context): TableRow {
        val  tableRow = TableRow(context)
        val params = TableRow.LayoutParams(
            TableRow.LayoutParams.MATCH_PARENT,
            TableRow.LayoutParams.MATCH_PARENT
        )
        params.setMargins(
            convertDpToPixels(10, context),
            convertDpToPixels(5, context),
            convertDpToPixels(10, context),
            convertDpToPixels(0, context)
        )
        tableRow.layoutParams = params
        return tableRow
    }

    fun createPhoneView(context: Context, phone: String): TextView {
        val textView = TextView(context)
        val params = TableRow.LayoutParams(
            TableRow.LayoutParams.MATCH_PARENT,
            TableRow.LayoutParams.WRAP_CONTENT
        )
        params.setMargins(
            convertDpToPixels(10, context),
            convertDpToPixels(5, context),
            convertDpToPixels(10, context),
            convertDpToPixels(0, context)
        )
        textView.layoutParams = params
        textView.setTextColor(context.resources.getColor(R.color.generalText))
        textView.textSize = 16F
        textView.setCompoundDrawablesRelativeWithIntrinsicBounds(
            context.resources.getDrawable(R.drawable.ic_phone),
            null,
            null,
            null
        )
        textView.text = phone
        return  textView
    }

    fun createAddressView(context: Context, address: String): TextView {
        val textView = TextView(context)
        val params = TableRow.LayoutParams(
            TableRow.LayoutParams.MATCH_PARENT,
            TableRow.LayoutParams.WRAP_CONTENT
        )
        params.setMargins(
            convertDpToPixels(10, context),
            convertDpToPixels(5, context),
            convertDpToPixels(10, context),
            convertDpToPixels(0, context)
        )
        textView.layoutParams = params
        textView.setTextColor(context.resources.getColor(R.color.generalText))
        textView.textSize = 16F
        textView.setCompoundDrawablesRelativeWithIntrinsicBounds(
            context.resources.getDrawable(R.drawable.ic_location),
            null,
            null,
            null
        )
        textView.text = address
        return  textView
    }

    fun createContactsBlock(context: Context, phone: String, address: String): TableLayout {
        val contactsLayout = createContactsLayout(context)
        val contactsRaw = createContactsRow(context)
        val phoneView = createPhoneView(context, phone)
        val addressView = createAddressView(context, address)
        contactsRaw.addView(phoneView)
        contactsRaw.addView(addressView)
        contactsLayout.addView(contactsRaw)
        contactsLayout.setStretchAllColumns(true)
        return contactsLayout
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
        cardLayout.addView(
            createContactsBlock(context, "+79209999999", "ул. Ленина 26")
        )

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