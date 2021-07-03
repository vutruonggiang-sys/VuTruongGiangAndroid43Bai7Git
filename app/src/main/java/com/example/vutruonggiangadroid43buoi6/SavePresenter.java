package com.example.vutruonggiangadroid43buoi6;

public class SavePresenter {
    ISave iSave;

    public SavePresenter(ISave iSave) {
        this.iSave = iSave;
    }
    public void onSave(int d){
        if(d==1){
            iSave.onSaveSuccessful("Bạn đã lưu thành công");
        }else{
            iSave.onNotSave("Thông tin không được lưu");
        }
    }
}
