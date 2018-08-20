package uib.tfg.project.model.Data;

import android.location.LocationListener;
import android.webkit.WebView;

import java.util.ArrayList;
import java.util.List;

public class PictureBox {

    private ArrayList<PictureObject> pictureList;
    private final float X_Coordinate;
    private final float Y_Coordinate;

    public float getX_Coordinate() {
        return X_Coordinate;
    }

    public float getY_Coordinate() {
        return Y_Coordinate;
    }

    public PictureBox(float x, float y){
        X_Coordinate = x;
        Y_Coordinate = y;
        pictureList = new ArrayList<>();
    }

    public void addPicture(PictureObject po){
        pictureList.add(po);
    }

    public boolean isEmpty(){
        return pictureList.isEmpty();
    }

    public PictureObject getPicture(float user_id, float picture_id){
        for (PictureObject picture:pictureList) {
            if(picture.isThisPicture(user_id,picture_id)){
                return picture;
            }
        }
        return null;
    }

    public boolean deletePicture(float user_id, float picture_id){
        for (int i = 0; i<pictureList.size(); i++) {
            PictureObject p = pictureList.get(i);
            if(p.isThisPicture(user_id,picture_id)){
                pictureList.remove(i);
                return true;
            }
        }
        return false;
    }
}