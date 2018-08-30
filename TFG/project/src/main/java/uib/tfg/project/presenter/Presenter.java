package uib.tfg.project.presenter;

import android.content.Context;
import android.graphics.Bitmap;
import android.location.Location;

/**
 * Created by Micky on 15/03/2018.
 */

public interface Presenter {
    void initiateLocationService();
    void stopLocationService();
    void initiateSensorsService();
    void stopSensorsService();
    Location getUserLocation();
    float [] getUserRotation();
    float [] getUserAcceleration();
    void initiatePictureLoader();
    void stopPictureLoader();
    void storeDataBase();
    void setContext(Context c);
    boolean isLocationServiceEnabled();
    void setUserCurrentBitmap(Bitmap bitmap);
    double getUserHeight();
    void setUserHeight(double height);
}
