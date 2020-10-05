package com.example.catalog.services

import RoundedTransformation
import android.accounts.AccountManager.get
import android.app.ActionBar
import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.util.TypedValue
import android.view.Gravity
import android.widget.*
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import com.example.catalog.R
import com.example.catalog.models.Organization
import com.squareup.picasso.Picasso


class UiHelper {

    fun createCardLayout(cardId: Long, context: Context): LinearLayout {
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
        layout.setBackgroundResource(R.drawable.catalog_card)
        layout.orientation = LinearLayout.VERTICAL

        return layout
    }

    fun createRelativeLayout(context: Context): RelativeLayout {
        val layout = RelativeLayout(context)
        val params = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.MATCH_PARENT,
            RelativeLayout.LayoutParams.MATCH_PARENT
        )
        layout.layoutParams = params

        return layout
    }

    fun createImageView(context: Context): ImageView {
        val imageView = ImageView(context)
        val params = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.MATCH_PARENT,
            RelativeLayout.LayoutParams.MATCH_PARENT
        )
        imageView.layoutParams = params
        imageView.clipToOutline = true
        return  imageView
    }

    fun createFavoritesCheckbox(context: Context, isChecked: Boolean, databaseService: DatabaseService, organizationId: Long): CheckBox {
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
        checkBox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                databaseService.setOrganizationFavorite(organizationId)
                Toast.makeText(context, "Организация добавлена в избранное",Toast.LENGTH_LONG).show()
            } else {
                databaseService.removeOrganizationFavorite(organizationId)
                Toast.makeText(context, "Организация удалена из избранного",Toast.LENGTH_LONG).show()
            }
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
            convertDpToPixels(8, context)
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

    fun createCatalogCard(context: Context, organization: Organization, databaseService: DatabaseService): LinearLayout {
        val imageLayout = createRelativeLayout(context)
        val imageView = createImageView(context)
        val transformation: RoundedTransformation = RoundedTransformation(50, 0)
        Picasso.with(context)
            .load(organization.logoImg)
            .transform(transformation)
            .fit()
            .placeholder(R.drawable.ic_baseline_photo_camera_24) //optional
            .error(R.drawable.ic_setting_empty)
            .into(imageView)

        val favoritesCheckbox = createFavoritesCheckbox(context, organization.isFavorite, databaseService, organization.id)
        imageLayout.addView(imageView)
        imageLayout.addView(favoritesCheckbox)
        val title = createTitle(context, organization.shortTitle)
        val description = createDescription(context, organization.shortDescription)
        val cardLayout = createCardLayout(organization.id, context)
        cardLayout.addView(imageLayout)
        cardLayout.addView(title)
        cardLayout.addView(description)
        cardLayout.addView(
            createContactsBlock(context, organization.phone, organization.address)
        )

        return cardLayout
    }

    fun createIconLayout(context: Context): LinearLayout {
        val r: Resources = context.resources
        val layout = LinearLayout(context)
        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        params.setMargins(
            convertDpToPixels(8, context),
            convertDpToPixels(30, context),
            convertDpToPixels(8, context),
            convertDpToPixels(8, context)
        )

        params.gravity = Gravity.CENTER or Gravity.CENTER_VERTICAL

        layout.layoutParams = params
//        layout.setBackgroundResource(R.drawable.ic_outline_sentiment_satisfied_24)
        val imageView = ImageView(context)
        val imageParams = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.WRAP_CONTENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT
        )
        imageView.setImageDrawable(
            context.resources.getDrawable(R.drawable.ic_outline_sentiment_satisfied_24),
        )
        imageView.layoutParams = imageParams
        layout.addView(imageView)
        return layout
    }

    fun createSecondaryButton(context: Context, title: String): Button {
        val button: Button = Button(context)
        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        params.setMargins(
            convertDpToPixels(8, context),
            convertDpToPixels(5, context),
            convertDpToPixels(8, context),
            convertDpToPixels(0, context)
        )
        button.setPadding(
            convertDpToPixels(10, context),
            convertDpToPixels(5, context),
            convertDpToPixels(10, context),
            convertDpToPixels(5, context)
        )
        button.layoutParams = params
        button.gravity = Gravity.LEFT or Gravity.CENTER_VERTICAL
        button.setBackgroundResource(R.drawable.rounded_button_white)
        button.setTextColor(context.resources.getColor(R.color.generalText))
        button.setCompoundDrawablesRelativeWithIntrinsicBounds(
            context.resources.getDrawable(R.drawable.ic_location),
            null,
            context.resources.getDrawable(R.drawable.ic_arrow),
            null
        )
        val bundle = bundleOf(
            "name" to "",
            "phone" to "",
            "direction" to "",
            "city" to title,
            "address" to ""
        )
        button.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.search_result, bundle)
        )
        button.text = title
        return  button

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