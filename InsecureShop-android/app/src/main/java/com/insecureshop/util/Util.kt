package com.insecureshop.util

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.insecureshop.ProductDetail
import com.insecureshop.R
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

object Util {

    private fun getUserCreds(): HashMap<String,String> {
        val userCreds = HashMap<String, String>()
        userCreds["kiwaadmin"] = "Sup3rSecurePassword!123"
        return userCreds
    }

    fun verifyUserNamePassword(username: String, password: String): Boolean {
        if (getUserCreds().containsKey(username)) {
            val passwordValue = getUserCreds()[username]
            return passwordValue.equals(password)
        } else {
            return false
        }
    }

    private fun getProductList(): ArrayList<ProductDetail> {
        val productList = ArrayList<ProductDetail>()
        productList.add(ProductDetail(1, "Kiwa Beaver", R.drawable.beaver ,"6",5, "https://www.insecureshopapp.com"))
        productList.add(ProductDetail(2, "Cap", R.drawable.cap, "5.10", 2, "https://www.insecureshopapp.com"))
        productList.add(ProductDetail(3, "Bert Bever Behendigheid", R.drawable.bertbever, "4", 4, "https://www.insecureshopapp.com"))
        productList.add(ProductDetail(4, "Americano", R.drawable.americano, "30", 4, "https://www.insecureshopapp.com"))
        productList.add(ProductDetail(5, "Blue bag", R.drawable.blauwetas, "40", 2, "https://www.insecureshopapp.com"))
        productList.add(ProductDetail(6, "cycling shirt", R.drawable.cyclingshirt, "10", 2, "https://www.insecureshopapp.com"))
        productList.add(ProductDetail(7, "Kiwa FSS Artwork Special edition", R.drawable.kiwa_artwork_se, "20", 999999, "https://www.insecureshopapp.com"))
        productList.add(ProductDetail(8, "Kiwa FSS Artwork", R.drawable.kiwa_artwork,"2", 2, "https://www.insecureshopapp.com"))
        productList.add(ProductDetail(9, "USB Stick", R.drawable.usb,"2", 2, "https://www.insecureshopapp.com"))
        return productList
    }

    fun saveProductList(context: Context, productList: List<ProductDetail> = getProductList()) {
        val productJson = Gson().toJson(productList)
        Prefs.getInstance(context).productList = productJson
    }

    fun getProductsPrefs(context: Context): List<ProductDetail> {
        val products =  Prefs.getInstance(context).productList
        return Gson().fromJson(products, object : TypeToken<List<ProductDetail>>() {}.type)
    }

    fun updateProductItem(context: Context, updateProductDetail: ProductDetail) {
        val productList = getProductsPrefs(context)
        for (productDetail in productList) {
            if (productDetail.id == updateProductDetail.id) {
                productDetail.qty = updateProductDetail.qty
            }
        }
        saveProductList(context, productList)
    }

    fun getCartProduct(context: Context): ArrayList<ProductDetail> {
        val cartList = arrayListOf<ProductDetail>()
        val productList = getProductsPrefs(context)
        for (productDetail in productList) {
            if (productDetail.qty > 0) {
                cartList.add(productDetail)
            }
        }
        return cartList
    }
}