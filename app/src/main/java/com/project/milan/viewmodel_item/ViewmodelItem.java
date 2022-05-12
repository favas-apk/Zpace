package com.project.milan.viewmodel_item;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.project.milan.pojos.read_item_by_stkid_cust_app.Response;
import com.project.milan.repository_item.RepositoryItem;

public class ViewmodelItem  extends ViewModel {

   private RepositoryItem repo;
   private    MutableLiveData<Response> liveData ;


    public ViewmodelItem() {
        repo = new RepositoryItem();
    }






    public MutableLiveData<Response>  getitem_bystockid(String stockid)
    {


            liveData=repo.getitem_bystockid(stockid);

           return liveData;

    }




}
