package io.github.athorfeo.architecture.utils

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import io.github.athorfeo.architecture.R

@BindingAdapter("asMoney")
fun bindAsMoney(textView: TextView, value: Double) {
    with(textView){
        text = context.getString(R.string.text_money, value.toString())
    }
}

@BindingAdapter("loadImage")
fun bindLoadImage(imageView: ImageView, imageUrl: String) {
    Glide
        .with(imageView.context)
        .load(imageUrl)
        .into(imageView);
}