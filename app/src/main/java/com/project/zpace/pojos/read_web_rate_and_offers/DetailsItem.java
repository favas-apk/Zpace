package com.project.zpace.pojos.read_web_rate_and_offers;

import com.google.gson.annotations.SerializedName;

public class DetailsItem{

	@SerializedName("web_stockid")
	private String webStockid;

	@SerializedName("web_offer_price")
	private String webOfferPrice;

	@SerializedName("web_rate")
	private String webRate;

	@SerializedName("web_buy_qty")
	private String webBuyQty;

	@SerializedName("web_free_qty")
	private String webFreeQty;

	@SerializedName("web_offer_end")
	private String webOfferEnd;

	@SerializedName("web_free_perc")
	private String webFreePerc;

	public void setWebStockid(String webStockid){
		this.webStockid = webStockid;
	}

	public String getWebStockid(){
		return webStockid;
	}

	public void setWebOfferPrice(String webOfferPrice){
		this.webOfferPrice = webOfferPrice;
	}

	public String getWebOfferPrice(){
		return webOfferPrice;
	}

	public void setWebRate(String webRate){
		this.webRate = webRate;
	}

	public String getWebRate(){
		return webRate;
	}

	public void setWebBuyQty(String webBuyQty){
		this.webBuyQty = webBuyQty;
	}

	public String getWebBuyQty(){
		return webBuyQty;
	}

	public void setWebFreeQty(String webFreeQty){
		this.webFreeQty = webFreeQty;
	}

	public String getWebFreeQty(){
		return webFreeQty;
	}

	public void setWebOfferEnd(String webOfferEnd){
		this.webOfferEnd = webOfferEnd;
	}

	public String getWebOfferEnd(){
		return webOfferEnd;
	}

	public void setWebFreePerc(String webFreePerc){
		this.webFreePerc = webFreePerc;
	}

	public String getWebFreePerc(){
		return webFreePerc;
	}
}