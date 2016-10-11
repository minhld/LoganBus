package com.minhld.supports;

import android.content.Context;
import android.os.AsyncTask;

import org.osmdroid.tileprovider.modules.DatabaseFileArchive;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ScaleBarOverlay;
import org.osmdroid.views.overlay.gestures.RotationGestureOverlay;

import java.io.File;

/**
 * Created by minhld on 10/10/2016.
 */

public class MapLoader {
    private static final int MAP_DEFAULT_ZOOM = 15;
    private double MAP_DEFAULT_LATITUDE = 38.535350;
    private double MAP_DEFAULT_LONGITUDE = -121.753807;

    public static void loadLocalMap(final Context context, final MapView osmViewer) {
        new AsyncTask(){
            @Override
            protected Object doInBackground(Object[] params) {
                String osmPath = Utils.getDownloadPath() + "/map.osm";
                DatabaseFileArchive localTileSource = DatabaseFileArchive.getDatabaseFileArchive(new File(osmPath));

                publishProgress();
                return null;
            }

            @Override
            protected void onProgressUpdate(Object[] values) {
                // configure map view
                configMapView(context, osmViewer);
            }
        }.execute();
    }

    private static void configMapView(Context context, MapView osmViewer) {
        RotationGestureOverlay mRotationGestureOverlay = new RotationGestureOverlay(context, osmViewer);
        mRotationGestureOverlay.setEnabled(true);

        osmViewer.setBuiltInZoomControls(true);
        osmViewer.setMultiTouchControls(true);

        osmViewer.getOverlays().add(mRotationGestureOverlay);

        osmViewer.setTileSource(TileSourceFactory.MAPNIK);
    }
}
