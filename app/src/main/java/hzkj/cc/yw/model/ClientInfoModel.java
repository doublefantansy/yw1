package hzkj.cc.yw.model;

import hzkj.cc.yw.contract.ClientInfoContract;

public class ClientInfoModel extends BaseModel<ClientInfoContract.Presenter> implements
    ClientInfoContract.Model {

  @Override
  public void getOne(int id) {
//    RetrofitFactory.getInstance().getRetrofit().create(ClientService.class).searchByCustomerId()
  }
}
