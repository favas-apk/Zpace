package com.project.zpace.viewmodel_item;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.project.zpace.pojos.read_item_by_stkid_cust_app.Response;
import com.project.zpace.repository_item.RepositoryItem;

public class ViewmodelItem  extends ViewModel {

   private RepositoryItem repo;
   private    MutableLiveData<com.project.zpace.pojos.read_item_by_stkid_cust_app.Response> liveData ;


    public ViewmodelItem() {
        repo = new RepositoryItem();
    }






    public MutableLiveData<Response>  getitem_bystockid(String stockid)
    {


            liveData=repo.getitem_bystockid(stockid);

           return liveData;

    }




}
