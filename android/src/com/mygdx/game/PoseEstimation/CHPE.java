package com.mygdx.game.PoseEstimation;


import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;


import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;


import com.mygdx.game.PoseEstimation.nn.PoseModel;
import com.mygdx.game.persistance.AppDatabase;
import com.mygdx.game.persistance.Coordinate.NNCoordinateDAO;
import com.mygdx.game.persistance.Relations.NNFrameCoordinateDAO;


public class CHPE {
    private Context context;
    private AppDatabase db;
    private PoseModel model;
    private String points;
    //private Net net;


    public CHPE(Context context, AppDatabase db, PoseModel model) {
        this.context = context;
        this.db = db;
        this.model = model;

    }




    public void ProcessFrame() {

    }

    public void StoreFrame() {


        // Creating the point amount of Frame Frame
        for (int i = 0; i < this.model.points; i++) {

            NNCoordinateDAO nnCoordinateDAO = this.db.nnCoordinateDAO();
            NNFrameCoordinateDAO nnFrameCoordinateDAO = this.db.nnFrameCoordinateDAO();
        }
        // Creating

    }

    public void execute() {

    }


}