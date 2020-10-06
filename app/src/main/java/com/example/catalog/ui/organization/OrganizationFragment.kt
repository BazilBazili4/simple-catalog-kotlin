package com.example.catalog.ui.organization

import RoundedTransformation
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.example.catalog.R
import com.example.catalog.models.Organization
import com.example.catalog.services.DatabaseService
import com.squareup.picasso.Picasso
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.PlacemarkMapObject
import com.yandex.mapkit.mapview.MapView
import com.yandex.runtime.image.ImageProvider


class OrganizationFragment : Fragment() {

    companion object {
        fun newInstance() = OrganizationFragment()
    }

    private lateinit var viewModel: OrganizationViewModel

    private lateinit var mapView: MapView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_organization, container, false)

        val dbService: DatabaseService = DatabaseService()

        val organization: Organization? = dbService.getOrganizationById(arguments?.getLong("organizationId"))

        val imageView = root.findViewById<ImageView>(R.id.organizationLogo)
        val transformationTop: RoundedTransformation = RoundedTransformation(50, 0)
        Picasso.with(requireContext())
            .load(organization?.logoImg)
            .transform(transformationTop)
            .placeholder(R.drawable.ic_baseline_photo_camera_24) //optional
            .error(R.drawable.ic_setting_empty)
            .into(imageView)

        val title: TextView = root.findViewById(R.id.titleText)
        title.text = organization?.title


        val checkBox = root.findViewById<CheckBox>(R.id.favoritesButton)
        organization?.let {
            checkBox.isChecked = it.isFavorite
            checkBox.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    dbService.setOrganizationFavorite(organization.id)
                    Toast.makeText(context, "Организация добавлена в избранное", Toast.LENGTH_LONG).show()
                } else {
                    dbService.removeOrganizationFavorite(organization.id)
                    Toast.makeText(context, "Организация удалена из избранного", Toast.LENGTH_LONG).show()
                }
            }
            val point: Point = Point(organization.latitude, organization.longitude)
            mapView = root.findViewById(R.id.mapview)
            mapView.map.move(
                CameraPosition(point, 13.6f, 0.0f, 0.0f),
                Animation(Animation.Type.SMOOTH, 0f),
                null
            )
            val resourceBackedImage = ImageProvider.fromBitmap(
                getBitmapFromVectorDrawable(R.drawable.ic_location, requireContext())
            )
            val placemark: PlacemarkMapObject = mapView.map.mapObjects.addPlacemark(
                point, resourceBackedImage
            )
            mapView.map.isScrollGesturesEnabled = false
            mapView.map.isTappableAreaRenderingEnabled = false

            val toProgrammButton: Button = root.findViewById(R.id.to_programm_button)

            toProgrammButton.setOnClickListener(
                Navigation.createNavigateOnClickListener(
                    R.id.navigation_web,
                    getLinkBundle(organization.programmUrl)
                )
            )
        }

        val titleDesc: TextView = root.findViewById(R.id.shortTitleView)
        titleDesc.text = organization?.shortDescription

        val phone: TextView = root.findViewById(R.id.phoneTextView)
        phone.text = organization?.phone

        val email: TextView = root.findViewById(R.id.emailTextView)
        email.text = organization?.email

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(OrganizationViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onStop() {
        // Вызов onStop нужно передавать инстансам MapView и MapKit.
        mapView.onStop()
        MapKitFactory.getInstance().onStop()
        super.onStop()
    }

    override fun onStart() {
        // Вызов onStart нужно передавать инстансам MapView и MapKit.
        super.onStart()
        MapKitFactory.getInstance().onStart()
        mapView.onStart()
    }

    fun getBitmapFromVectorDrawable(drawableId: Int, context: Context): Bitmap? {
        var drawable = ContextCompat.getDrawable(context, drawableId) ?: return null

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            drawable = DrawableCompat.wrap(drawable).mutate()
        }

        val bitmap = Bitmap.createBitmap(
            drawable.intrinsicWidth,
            drawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888) ?: return null
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)

        return bitmap
    }

    fun getLinkBundle(link: String): Bundle {
        return bundleOf(
            "link" to link
        )
    }

}