/* 
Copyright (c) 2022 Kotlin Data Classes Generated from JSON powered by http://www.json2kotlin.com

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

For support, please feel free to contact me at https://www.linkedin.com/in/syedabsar */


//Constantes que se utilizan para pasar atributos como parametros entre actividades.
const val ITEM_TITLE = "ItemTitle"
const val ITEM_PRICE = "ItemPrice"
const val ITEM_CURRENCY = "ItemCurrency"
const val ITEM_QUANTITY = "ItemQuantity"
const val ITEM_THUMBNAIL = "ItemThumbnail"

//Se comentan y eliminan atributos que no se van a utilizar en esta app de prueba.
data class Item (

	val id : String,
	val site_id : String,
	val title : String,
	//val seller : Seller,
	val price : Float,
	//val prices : Prices,
	val sale_price : String,
	val currency_id : String,
	val available_quantity : Int,
	val sold_quantity : Int,
	val buying_mode : String,
	val listing_type_id : String,
	/*val stop_time : String,
	val condition : String,
	val permalink : String,*/
	val thumbnail : String
	/*val thumbnail_id : String,
	val accepts_mercadopago : Boolean,
	val installments : Installments,
	val address : Address,
	val shipping : Shipping,
	val seller_address : Seller_address,
	val seller_contact : Seller_contact,
	val location : Location,
	val attributes : List<Attributes>,
	val original_price : String,
	val category_id : String,
	val official_store_id : String,
	val domain_id : String,
	val catalog_product_id : String,
	val tags : List<String>,
	val order_backend : Int,
	val use_thumbnail_id : Boolean,
	val offer_score : String,
	val offer_share : String,
	val match_score : String,
	val winner_item_id : String,
	val melicoin : String,
	val discounts : String*/
)