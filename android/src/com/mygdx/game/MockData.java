package com.mygdx.game;

import com.mygdx.game.PoseEstimation.nn.MPI;
import com.mygdx.game.persistance.AppDatabase;
import com.mygdx.game.persistance.Coordinate.NNCoordinate;
import com.mygdx.game.persistance.Coordinate.NNCoordinateDAO;
import com.mygdx.game.persistance.Frame.NNFrame;
import com.mygdx.game.persistance.Frame.NNFrameDAO;
import com.mygdx.game.persistance.Relations.NNFrameCoordinate;
import com.mygdx.game.persistance.Relations.NNFrameCoordinateDAO;
import com.mygdx.game.persistance.Relations.NNVideoFrame;
import com.mygdx.game.persistance.Relations.NNVideoFrameDAO;
import com.mygdx.game.persistance.Video.NNVideo;
import com.mygdx.game.persistance.Video.NNVideoDAO;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


public class MockData {

    private AppDatabase appDatabase;
    private JSONArray entries;

    public static double[] StringArrToDoubleArr(String[] s) {
        double[] result = new double[s.length];
        for (int i = 0; i < s.length; i++) {
            result[i] = Double.parseDouble(s[i]);
        }
        return result;
    }

    // TODO: Insert wrapper for inserting frames would be useful

    private long insertFrame(int frameCount) {
        NNFrame nnFrame = new NNFrame();
        nnFrame.frame_count = frameCount;
        NNFrameDAO nnFrameDAO = this.appDatabase.nnFrameDAO();
        return nnFrameDAO.insert(nnFrame);
    }

    private long insertSession(int frameCount){
        NNVideo nnSession = new NNVideo();
        nnSession.frame_count = frameCount;
        nnSession.frames_per_second = 24;
        NNVideoDAO nnVideoDAO = this.appDatabase.nnVideoDAO();
        return nnVideoDAO.insert(nnSession);
    }
    private long insertCoordinate(double x, double y){

        NNCoordinateDAO nnCoordinateDAO = appDatabase.nnCoordinateDAO();
        NNCoordinate coordinate = new NNCoordinate();
        coordinate.x = x;
        coordinate.y = y;
        return nnCoordinateDAO.insert(coordinate);
    }
    private void insertFrameCoordinate(long fid, long cid){
        NNFrameCoordinate nnFrameCoordinate = new NNFrameCoordinate();
        NNFrameCoordinateDAO nnFrameCoordinateDAO = this.appDatabase.nnFrameCoordinateDAO();
        nnFrameCoordinate.coordinate_id = cid;
        nnFrameCoordinate.frame_id = fid;
        nnFrameCoordinateDAO.insert(nnFrameCoordinate);
    }

    private void insertSessionFrame(long fid, long sid){
        NNVideoFrame nnSessionFrame = new NNVideoFrame();
        NNVideoFrameDAO nnSessionFrameDAO = this.appDatabase.nnSessionFrameDAO();
        nnSessionFrame.video_id = sid;
        nnSessionFrame.frame_id = fid;
        nnSessionFrameDAO.insert(nnSessionFrame);
    }


    public MockData(AppDatabase appDatabase) {
        this.appDatabase = appDatabase;
        try {
            this.entries = new JSONArray();
        } catch (Exception ex) {
            DebugLog.log(ex.getMessage());
        }

        executeInserts();

    }

    public void executeInserts() {
        DebugLog.log(String.valueOf(entries .size()));
        long sessionId = insertSession(this.entries.size());
        long insertId = 0;
        MPI poseModel = new MPI();
        for (int i = 0; i < this.entries.size(); i++) {
            try {
                JSONObject jsonObject =  (JSONObject) this.entries.get(i);
                long frameId = insertFrame(i);
                insertSessionFrame(frameId, sessionId);
                for (String spart : poseModel.body_parts) {

                    Object part = jsonObject.get(spart);
                    String values = part.toString();
                    values = values.substring(1, values.length() - 1);

                    String[] nums = values.split(",");
                    double [] coordinates = StringArrToDoubleArr(nums);

                    long recordInsertID = insertCoordinate(coordinates[0], coordinates[1]);
                    insertFrameCoordinate(frameId, recordInsertID);
                }
            } catch (Exception ex) {
                DebugLog.log(ex.getMessage());
            }
        }


        /*
        insertCoordinates();
        insertSession();
        insertRelations();
         */
    }

    public MockData(AppDatabase appDatabase, JSONArray content) {
        this.appDatabase = appDatabase;
        try {
            this.entries = content;
        } catch (Exception ex) {
            DebugLog.log(ex.getMessage());
        }
    }

}
