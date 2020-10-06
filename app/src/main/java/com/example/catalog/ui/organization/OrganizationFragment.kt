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
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
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
        mapView = root.findViewById(R.id.mapview)
        mapView.map.move(
            CameraPosition(Point(50.597810, 36.598038), 13.6f, 0.0f, 0.0f),
            Animation(Animation.Type.SMOOTH, 0f),
            null
        )

        val resourceBackedImage = ImageProvider.fromBitmap(
            getBitmapFromVectorDrawable(R.drawable.ic_location, requireContext())
        )
        val placemark: PlacemarkMapObject = mapView.map.mapObjects.addPlacemark(
            Point(50.597810, 36.598038), resourceBackedImage
        )
        mapView.map.isScrollGesturesEnabled = false
        mapView.map.isTappableAreaRenderingEnabled = false


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

}