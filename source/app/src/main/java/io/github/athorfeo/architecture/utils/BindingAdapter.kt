package io.github.athorfeo.architecture.utils

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import io.github.athorfeo.architecture.R
import io.github.athorfeo.architecture.model.Picture

@BindingAdapter("asMoney")
fun bindAsMoney(textView: TextView, value: Double?) {
    value?.let{
        with(textView){
            text = context.getString(R.string.text_money, it.toString())
        }
    }

}

@BindingAdapter("loadImage")
fun bindLoadImage(imageView: ImageView, imageUrl: String?) {
    imageUrl?.let{
        Glide
            .with(imageView.context)
            .load(it)
            .into(imageView)
    }
}

@BindingAdapter("loadImages")
fun bindLoadImages(imageView: ImageView, pictures: List<Picture>?) {
    pictures?.let{
        Glide
            .with(imageView.context)
            .load(it[0].secureUrl)
            .into(imageView)
    }
}