package com.mygdx.game;

import com.mygdx.game.PoseEstimation.NN.MPI;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


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

    private long insertFrame(int frameCount) {
        NNFrame nnFrame = new NNFrame();
        nnFrame.frame_count = frameCount;
        NNFrameDAO nnFrameDAO = this.appDatabase.nnFrameDAO();
        return nnFrameDAO.insert(nnFrame);
    }

    private long insertSession(int frameCount) {
        NNVideo nnSession = new NNVideo();
        nnSession.frame_count = frameCount;
        nnSession.frames_per_second = 24;
        NNVideoDAO nnVideoDAO = this.appDatabase.nnVideoDAO();
        return nnVideoDAO.insert(nnSession);
    }

    private long insertRawCoordinate(int x, int y) {
        NNCoordinateDAO nnCoordinateDAO = appDatabase.nnCoordinateDAO();
        NNCoordinate coordinate = new NNCoordinate();
        coordinate.raw_x = x;
        coordinate.raw_y = y;
        return nnCoordinateDAO.insert(coordinate);
    }

    private long insertCoordinate(double x, double y) {

        NNCoordinateDAO nnCoordinateDAO = appDatabase.nnCoordinateDAO();
        NNCoordinate coordinate = new NNCoordinate();
        coordinate.x = x;
        coordinate.y = y;
        return nnCoordinateDAO.insert(coordinate);
    }

    private void insertFrameCoordinate(long fid, long cid) {
        NNFrameCoordinate nnFrameCoordinate = new NNFrameCoordinate();
        NNFrameCoordinateDAO nnFrameCoordinateDAO = this.appDatabase.nnFrameCoordinateDAO();
        nnFrameCoordinate.coordinate_id = cid;
        nnFrameCoordinate.frame_id = fid;
        nnFrameCoordinateDAO.insert(nnFrameCoordinate);
    }

    private void insertSessionFrame(long fid, long sid) {
        NNVideoFrame nnSessionFrame = new NNVideoFrame();
        NNVideoFrameDAO nnSessionFrameDAO = this.appDatabase.nnSessionFrameDAO();
        nnSessionFrame.video_id = sid;
        nnSessionFrame.frame_id = fid;
        nnSessionFrameDAO.insert(nnSessionFrame);
    }


    public MockData(AppDatabase appDatabase) {
        this.appDatabase = appDatabase;
        String content = "[{\"head\": [667,78],\"neck\": [667,187],\"l_shoulder\": [528,234],\"l_elbow\": [445,234],\"l_wrist\": [528,125],\"r_shoulder\": [779,250],\"r_elbow\": [806,422],\"r_wrist\": [779,516],\"l_hip\": [584,500],\"l_knee\": [556,657],\"l_foot\": [0,0],\"r_hip\": [695,500],\"r_knee\": [723,673],\"r_foot\": [0,0],\"waist\": [640,375]},{\"head\": [667,78],\"neck\": [667,187],\"l_shoulder\": [528,234],\"l_elbow\": [445,234],\"l_wrist\": [528,125],\"r_shoulder\": [779,250],\"r_elbow\": [806,422],\"r_wrist\": [779,516],\"l_hip\": [584,500],\"l_knee\": [556,657],\"l_foot\": [0,0],\"r_hip\": [695,500],\"r_knee\": [723,673],\"r_foot\": [0,0],\"waist\": [640,375]},{\"head\": [667,78],\"neck\": [667,187],\"l_shoulder\": [528,234],\"l_elbow\": [445,234],\"l_wrist\": [528,125],\"r_shoulder\": [779,250],\"r_elbow\": [806,422],\"r_wrist\": [779,516],\"l_hip\": [584,500],\"l_knee\": [556,657],\"l_foot\": [0,0],\"r_hip\": [695,500],\"r_knee\": [723,673],\"r_foot\": [0,0],\"waist\": [640,375]},{\"head\": [667,78],\"neck\": [667,187],\"l_shoulder\": [528,234],\"l_elbow\": [445,234],\"l_wrist\": [528,125],\"r_shoulder\": [779,250],\"r_elbow\": [806,422],\"r_wrist\": [779,516],\"l_hip\": [584,500],\"l_knee\": [556,657],\"l_foot\": [0,0],\"r_hip\": [695,500],\"r_knee\": [723,673],\"r_foot\": [0,0],\"waist\": [640,375]},{\"head\": [667,78],\"neck\": [667,187],\"l_shoulder\": [528,234],\"l_elbow\": [445,234],\"l_wrist\": [528,125],\"r_shoulder\": [779,250],\"r_elbow\": [806,422],\"r_wrist\": [779,516],\"l_hip\": [584,500],\"l_knee\": [556,657],\"l_foot\": [0,0],\"r_hip\": [695,500],\"r_knee\": [695,688],\"r_foot\": [0,0],\"waist\": [640,375]},{\"head\": [667,78],\"neck\": [667,187],\"l_shoulder\": [528,234],\"l_elbow\": [445,234],\"l_wrist\": [528,125],\"r_shoulder\": [779,250],\"r_elbow\": [806,422],\"r_wrist\": [779,516],\"l_hip\": [584,500],\"l_knee\": [556,657],\"l_foot\": [0,0],\"r_hip\": [695,500],\"r_knee\": [695,688],\"r_foot\": [0,0],\"waist\": [640,375]},{\"head\": [667,78],\"neck\": [667,187],\"l_shoulder\": [528,234],\"l_elbow\": [445,234],\"l_wrist\": [528,125],\"r_shoulder\": [779,250],\"r_elbow\": [806,422],\"r_wrist\": [779,516],\"l_hip\": [584,500],\"l_knee\": [556,657],\"l_foot\": [0,0],\"r_hip\": [695,500],\"r_knee\": [695,688],\"r_foot\": [0,0],\"waist\": [640,375]},{\"head\": [667,78],\"neck\": [667,187],\"l_shoulder\": [528,234],\"l_elbow\": [445,234],\"l_wrist\": [528,125],\"r_shoulder\": [779,250],\"r_elbow\": [806,422],\"r_wrist\": [779,516],\"l_hip\": [584,500],\"l_knee\": [556,657],\"l_foot\": [0,0],\"r_hip\": [695,500],\"r_knee\": [695,688],\"r_foot\": [0,0],\"waist\": [640,375]},{\"head\": [667,78],\"neck\": [667,187],\"l_shoulder\": [528,234],\"l_elbow\": [445,234],\"l_wrist\": [528,125],\"r_shoulder\": [779,250],\"r_elbow\": [806,422],\"r_wrist\": [779,516],\"l_hip\": [584,500],\"l_knee\": [556,657],\"l_foot\": [0,0],\"r_hip\": [695,500],\"r_knee\": [695,688],\"r_foot\": [0,0],\"waist\": [640,375]},{\"head\": [667,78],\"neck\": [667,187],\"l_shoulder\": [528,234],\"l_elbow\": [445,234],\"l_wrist\": [528,125],\"r_shoulder\": [779,250],\"r_elbow\": [806,422],\"r_wrist\": [779,516],\"l_hip\": [584,500],\"l_knee\": [556,657],\"l_foot\": [0,0],\"r_hip\": [695,500],\"r_knee\": [695,688],\"r_foot\": [0,0],\"waist\": [640,375]},{\"head\": [667,78],\"neck\": [667,187],\"l_shoulder\": [528,234],\"l_elbow\": [445,234],\"l_wrist\": [528,125],\"r_shoulder\": [779,250],\"r_elbow\": [806,422],\"r_wrist\": [779,516],\"l_hip\": [584,500],\"l_knee\": [556,657],\"l_foot\": [0,0],\"r_hip\": [695,500],\"r_knee\": [695,688],\"r_foot\": [0,0],\"waist\": [640,375]},{\"head\": [667,78],\"neck\": [667,187],\"l_shoulder\": [528,234],\"l_elbow\": [445,234],\"l_wrist\": [528,125],\"r_shoulder\": [779,250],\"r_elbow\": [806,422],\"r_wrist\": [779,516],\"l_hip\": [584,500],\"l_knee\": [556,657],\"l_foot\": [0,0],\"r_hip\": [695,500],\"r_knee\": [695,688],\"r_foot\": [0,0],\"waist\": [640,375]},{\"head\": [667,78],\"neck\": [667,187],\"l_shoulder\": [528,234],\"l_elbow\": [445,234],\"l_wrist\": [528,125],\"r_shoulder\": [779,250],\"r_elbow\": [806,422],\"r_wrist\": [779,516],\"l_hip\": [584,500],\"l_knee\": [584,657],\"l_foot\": [0,0],\"r_hip\": [695,500],\"r_knee\": [695,688],\"r_foot\": [0,0],\"waist\": [640,375]},{\"head\": [667,78],\"neck\": [667,187],\"l_shoulder\": [528,234],\"l_elbow\": [445,234],\"l_wrist\": [528,125],\"r_shoulder\": [779,250],\"r_elbow\": [806,422],\"r_wrist\": [779,516],\"l_hip\": [584,500],\"l_knee\": [584,657],\"l_foot\": [0,0],\"r_hip\": [695,500],\"r_knee\": [695,688],\"r_foot\": [0,0],\"waist\": [640,375]},{\"head\": [667,78],\"neck\": [667,187],\"l_shoulder\": [528,234],\"l_elbow\": [445,234],\"l_wrist\": [528,125],\"r_shoulder\": [779,250],\"r_elbow\": [806,422],\"r_wrist\": [779,516],\"l_hip\": [584,500],\"l_knee\": [584,657],\"l_foot\": [0,0],\"r_hip\": [695,500],\"r_knee\": [695,688],\"r_foot\": [0,0],\"waist\": [640,375]},{\"head\": [667,78],\"neck\": [667,187],\"l_shoulder\": [528,234],\"l_elbow\": [445,234],\"l_wrist\": [528,125],\"r_shoulder\": [779,250],\"r_elbow\": [806,422],\"r_wrist\": [779,516],\"l_hip\": [584,500],\"l_knee\": [584,657],\"l_foot\": [0,0],\"r_hip\": [695,500],\"r_knee\": [695,688],\"r_foot\": [0,0],\"waist\": [640,375]},{\"head\": [667,78],\"neck\": [667,187],\"l_shoulder\": [528,234],\"l_elbow\": [445,234],\"l_wrist\": [528,125],\"r_shoulder\": [779,250],\"r_elbow\": [806,422],\"r_wrist\": [779,516],\"l_hip\": [584,500],\"l_knee\": [584,657],\"l_foot\": [0,0],\"r_hip\": [695,500],\"r_knee\": [695,688],\"r_foot\": [0,0],\"waist\": [640,375]},{\"head\": [667,78],\"neck\": [667,187],\"l_shoulder\": [528,234],\"l_elbow\": [445,234],\"l_wrist\": [528,125],\"r_shoulder\": [779,250],\"r_elbow\": [806,422],\"r_wrist\": [779,516],\"l_hip\": [584,500],\"l_knee\": [584,657],\"l_foot\": [0,0],\"r_hip\": [695,500],\"r_knee\": [695,688],\"r_foot\": [0,0],\"waist\": [640,375]},{\"head\": [667,78],\"neck\": [667,187],\"l_shoulder\": [528,234],\"l_elbow\": [445,234],\"l_wrist\": [528,125],\"r_shoulder\": [779,250],\"r_elbow\": [806,422],\"r_wrist\": [779,516],\"l_hip\": [584,500],\"l_knee\": [584,657],\"l_foot\": [0,0],\"r_hip\": [695,500],\"r_knee\": [695,688],\"r_foot\": [0,0],\"waist\": [640,375]},{\"head\": [667,78],\"neck\": [667,187],\"l_shoulder\": [528,234],\"l_elbow\": [445,234],\"l_wrist\": [528,125],\"r_shoulder\": [779,250],\"r_elbow\": [806,422],\"r_wrist\": [779,516],\"l_hip\": [584,500],\"l_knee\": [584,657],\"l_foot\": [0,0],\"r_hip\": [695,500],\"r_knee\": [695,688],\"r_foot\": [0,0],\"waist\": [640,375]},{\"head\": [667,78],\"neck\": [667,187],\"l_shoulder\": [528,234],\"l_elbow\": [445,234],\"l_wrist\": [528,125],\"r_shoulder\": [779,250],\"r_elbow\": [806,422],\"r_wrist\": [779,516],\"l_hip\": [584,500],\"l_knee\": [584,657],\"l_foot\": [0,0],\"r_hip\": [695,500],\"r_knee\": [695,688],\"r_foot\": [0,0],\"waist\": [640,375]},{\"head\": [667,78],\"neck\": [667,187],\"l_shoulder\": [528,234],\"l_elbow\": [445,234],\"l_wrist\": [528,125],\"r_shoulder\": [779,250],\"r_elbow\": [806,422],\"r_wrist\": [779,516],\"l_hip\": [584,500],\"l_knee\": [584,657],\"l_foot\": [0,0],\"r_hip\": [695,500],\"r_knee\": [695,688],\"r_foot\": [0,0],\"waist\": [640,375]},{\"head\": [667,78],\"neck\": [667,187],\"l_shoulder\": [528,234],\"l_elbow\": [445,234],\"l_wrist\": [528,125],\"r_shoulder\": [779,250],\"r_elbow\": [806,422],\"r_wrist\": [779,516],\"l_hip\": [584,500],\"l_knee\": [584,657],\"l_foot\": [0,0],\"r_hip\": [695,500],\"r_knee\": [695,688],\"r_foot\": [0,0],\"waist\": [640,375]},{\"head\": [667,78],\"neck\": [667,187],\"l_shoulder\": [528,234],\"l_elbow\": [445,234],\"l_wrist\": [528,125],\"r_shoulder\": [779,250],\"r_elbow\": [806,422],\"r_wrist\": [779,516],\"l_hip\": [584,500],\"l_knee\": [556,657],\"l_foot\": [0,0],\"r_hip\": [695,500],\"r_knee\": [695,688],\"r_foot\": [0,0],\"waist\": [640,375]},{\"head\": [667,78],\"neck\": [667,187],\"l_shoulder\": [528,234],\"l_elbow\": [445,234],\"l_wrist\": [528,125],\"r_shoulder\": [779,250],\"r_elbow\": [806,422],\"r_wrist\": [779,516],\"l_hip\": [584,500],\"l_knee\": [584,657],\"l_foot\": [0,0],\"r_hip\": [695,500],\"r_knee\": [695,688],\"r_foot\": [0,0],\"waist\": [640,375]},{\"head\": [667,78],\"neck\": [667,187],\"l_shoulder\": [528,234],\"l_elbow\": [445,234],\"l_wrist\": [528,125],\"r_shoulder\": [779,250],\"r_elbow\": [806,422],\"r_wrist\": [779,516],\"l_hip\": [584,500],\"l_knee\": [584,657],\"l_foot\": [0,0],\"r_hip\": [695,500],\"r_knee\": [695,688],\"r_foot\": [0,0],\"waist\": [640,375]},{\"head\": [667,78],\"neck\": [667,187],\"l_shoulder\": [528,234],\"l_elbow\": [445,234],\"l_wrist\": [528,125],\"r_shoulder\": [779,250],\"r_elbow\": [806,422],\"r_wrist\": [779,516],\"l_hip\": [584,500],\"l_knee\": [584,657],\"l_foot\": [0,0],\"r_hip\": [695,500],\"r_knee\": [695,688],\"r_foot\": [0,0],\"waist\": [640,375]},{\"head\": [667,78],\"neck\": [667,187],\"l_shoulder\": [528,234],\"l_elbow\": [445,234],\"l_wrist\": [528,125],\"r_shoulder\": [779,250],\"r_elbow\": [806,422],\"r_wrist\": [779,516],\"l_hip\": [584,500],\"l_knee\": [584,657],\"l_foot\": [0,0],\"r_hip\": [695,500],\"r_knee\": [695,688],\"r_foot\": [0,0],\"waist\": [640,375]},{\"head\": [667,78],\"neck\": [667,187],\"l_shoulder\": [528,234],\"l_elbow\": [445,234],\"l_wrist\": [528,125],\"r_shoulder\": [779,250],\"r_elbow\": [806,422],\"r_wrist\": [779,516],\"l_hip\": [584,500],\"l_knee\": [584,657],\"l_foot\": [0,0],\"r_hip\": [695,500],\"r_knee\": [695,688],\"r_foot\": [0,0],\"waist\": [640,375]},{\"head\": [667,78],\"neck\": [667,187],\"l_shoulder\": [528,234],\"l_elbow\": [445,234],\"l_wrist\": [528,125],\"r_shoulder\": [779,250],\"r_elbow\": [806,422],\"r_wrist\": [779,516],\"l_hip\": [584,500],\"l_knee\": [584,657],\"l_foot\": [0,0],\"r_hip\": [695,500],\"r_knee\": [695,688],\"r_foot\": [0,0],\"waist\": [640,375]},{\"head\": [667,78],\"neck\": [667,187],\"l_shoulder\": [528,234],\"l_elbow\": [445,234],\"l_wrist\": [528,125],\"r_shoulder\": [779,250],\"r_elbow\": [806,422],\"r_wrist\": [779,516],\"l_hip\": [584,500],\"l_knee\": [584,657],\"l_foot\": [0,0],\"r_hip\": [695,500],\"r_knee\": [695,688],\"r_foot\": [0,0],\"waist\": [640,375]},{\"head\": [667,78],\"neck\": [667,187],\"l_shoulder\": [528,234],\"l_elbow\": [445,234],\"l_wrist\": [528,125],\"r_shoulder\": [779,250],\"r_elbow\": [806,422],\"r_wrist\": [779,516],\"l_hip\": [584,500],\"l_knee\": [584,657],\"l_foot\": [0,0],\"r_hip\": [695,500],\"r_knee\": [695,688],\"r_foot\": [0,0],\"waist\": [640,375]},{\"head\": [667,78],\"neck\": [667,187],\"l_shoulder\": [528,234],\"l_elbow\": [445,234],\"l_wrist\": [528,125],\"r_shoulder\": [779,250],\"r_elbow\": [806,422],\"r_wrist\": [779,516],\"l_hip\": [584,500],\"l_knee\": [584,657],\"l_foot\": [0,0],\"r_hip\": [695,500],\"r_knee\": [695,688],\"r_foot\": [0,0],\"waist\": [640,375]},{\"head\": [667,78],\"neck\": [667,187],\"l_shoulder\": [528,234],\"l_elbow\": [445,234],\"l_wrist\": [528,125],\"r_shoulder\": [779,250],\"r_elbow\": [806,422],\"r_wrist\": [779,516],\"l_hip\": [584,500],\"l_knee\": [584,657],\"l_foot\": [0,0],\"r_hip\": [695,500],\"r_knee\": [695,688],\"r_foot\": [0,0],\"waist\": [640,375]},{\"head\": [667,78],\"neck\": [667,187],\"l_shoulder\": [528,234],\"l_elbow\": [445,234],\"l_wrist\": [528,125],\"r_shoulder\": [779,250],\"r_elbow\": [806,422],\"r_wrist\": [779,516],\"l_hip\": [584,500],\"l_knee\": [584,657],\"l_foot\": [0,0],\"r_hip\": [695,500],\"r_knee\": [695,688],\"r_foot\": [0,0],\"waist\": [640,375]},{\"head\": [667,78],\"neck\": [667,187],\"l_shoulder\": [528,234],\"l_elbow\": [445,234],\"l_wrist\": [528,125],\"r_shoulder\": [779,250],\"r_elbow\": [806,422],\"r_wrist\": [779,516],\"l_hip\": [584,500],\"l_knee\": [556,657],\"l_foot\": [0,0],\"r_hip\": [695,500],\"r_knee\": [695,688],\"r_foot\": [0,0],\"waist\": [640,375]},{\"head\": [667,78],\"neck\": [667,187],\"l_shoulder\": [528,234],\"l_elbow\": [445,234],\"l_wrist\": [528,125],\"r_shoulder\": [779,250],\"r_elbow\": [806,422],\"r_wrist\": [779,516],\"l_hip\": [584,500],\"l_knee\": [584,657],\"l_foot\": [0,0],\"r_hip\": [695,500],\"r_knee\": [695,688],\"r_foot\": [0,0],\"waist\": [640,375]},{\"head\": [667,78],\"neck\": [667,187],\"l_shoulder\": [528,234],\"l_elbow\": [445,234],\"l_wrist\": [528,125],\"r_shoulder\": [779,250],\"r_elbow\": [806,422],\"r_wrist\": [779,516],\"l_hip\": [584,500],\"l_knee\": [584,657],\"l_foot\": [0,0],\"r_hip\": [695,500],\"r_knee\": [695,688],\"r_foot\": [0,0],\"waist\": [640,375]},{\"head\": [667,78],\"neck\": [667,187],\"l_shoulder\": [528,234],\"l_elbow\": [445,234],\"l_wrist\": [528,125],\"r_shoulder\": [779,250],\"r_elbow\": [806,422],\"r_wrist\": [779,516],\"l_hip\": [584,500],\"l_knee\": [584,657],\"l_foot\": [0,0],\"r_hip\": [695,500],\"r_knee\": [695,688],\"r_foot\": [0,0],\"waist\": [640,375]},{\"head\": [667,78],\"neck\": [667,187],\"l_shoulder\": [528,234],\"l_elbow\": [445,234],\"l_wrist\": [528,125],\"r_shoulder\": [779,250],\"r_elbow\": [806,422],\"r_wrist\": [779,516],\"l_hip\": [584,500],\"l_knee\": [584,657],\"l_foot\": [0,0],\"r_hip\": [695,500],\"r_knee\": [695,688],\"r_foot\": [0,0],\"waist\": [640,375]},{\"head\": [667,78],\"neck\": [667,187],\"l_shoulder\": [528,234],\"l_elbow\": [445,234],\"l_wrist\": [528,125],\"r_shoulder\": [779,250],\"r_elbow\": [806,422],\"r_wrist\": [779,516],\"l_hip\": [584,500],\"l_knee\": [584,657],\"l_foot\": [0,0],\"r_hip\": [695,500],\"r_knee\": [695,688],\"r_foot\": [0,0],\"waist\": [640,375]},{\"head\": [667,78],\"neck\": [667,187],\"l_shoulder\": [528,234],\"l_elbow\": [445,234],\"l_wrist\": [528,125],\"r_shoulder\": [779,250],\"r_elbow\": [806,422],\"r_wrist\": [779,516],\"l_hip\": [584,500],\"l_knee\": [584,657],\"l_foot\": [0,0],\"r_hip\": [695,500],\"r_knee\": [695,688],\"r_foot\": [0,0],\"waist\": [640,375]},{\"head\": [667,78],\"neck\": [667,187],\"l_shoulder\": [528,234],\"l_elbow\": [445,234],\"l_wrist\": [528,125],\"r_shoulder\": [779,250],\"r_elbow\": [806,422],\"r_wrist\": [779,516],\"l_hip\": [584,500],\"l_knee\": [584,657],\"l_foot\": [0,0],\"r_hip\": [695,500],\"r_knee\": [695,688],\"r_foot\": [0,0],\"waist\": [640,375]},{\"head\": [667,78],\"neck\": [667,187],\"l_shoulder\": [528,234],\"l_elbow\": [445,234],\"l_wrist\": [528,125],\"r_shoulder\": [779,250],\"r_elbow\": [806,422],\"r_wrist\": [779,516],\"l_hip\": [584,500],\"l_knee\": [584,657],\"l_foot\": [0,0],\"r_hip\": [695,500],\"r_knee\": [695,688],\"r_foot\": [0,0],\"waist\": [640,375]},{\"head\": [667,78],\"neck\": [667,187],\"l_shoulder\": [528,234],\"l_elbow\": [445,234],\"l_wrist\": [528,125],\"r_shoulder\": [779,250],\"r_elbow\": [806,422],\"r_wrist\": [779,516],\"l_hip\": [584,500],\"l_knee\": [584,657],\"l_foot\": [0,0],\"r_hip\": [695,500],\"r_knee\": [695,688],\"r_foot\": [0,0],\"waist\": [640,375]},{\"head\": [667,78],\"neck\": [667,187],\"l_shoulder\": [528,234],\"l_elbow\": [445,234],\"l_wrist\": [528,125],\"r_shoulder\": [779,250],\"r_elbow\": [806,422],\"r_wrist\": [779,516],\"l_hip\": [584,500],\"l_knee\": [584,657],\"l_foot\": [0,0],\"r_hip\": [695,500],\"r_knee\": [695,688],\"r_foot\": [0,0],\"waist\": [640,375]},{\"head\": [667,78],\"neck\": [667,187],\"l_shoulder\": [528,234],\"l_elbow\": [445,234],\"l_wrist\": [528,125],\"r_shoulder\": [779,250],\"r_elbow\": [806,422],\"r_wrist\": [779,516],\"l_hip\": [584,500],\"l_knee\": [584,657],\"l_foot\": [0,0],\"r_hip\": [695,500],\"r_knee\": [695,688],\"r_foot\": [0,0],\"waist\": [640,375]},{\"head\": [667,78],\"neck\": [667,187],\"l_shoulder\": [528,234],\"l_elbow\": [445,234],\"l_wrist\": [528,125],\"r_shoulder\": [779,250],\"r_elbow\": [806,422],\"r_wrist\": [779,516],\"l_hip\": [584,500],\"l_knee\": [584,657],\"l_foot\": [0,0],\"r_hip\": [695,500],\"r_knee\": [695,688],\"r_foot\": [0,0],\"waist\": [640,375]},{\"head\": [667,78],\"neck\": [667,187],\"l_shoulder\": [528,234],\"l_elbow\": [445,234],\"l_wrist\": [528,125],\"r_shoulder\": [779,250],\"r_elbow\": [806,422],\"r_wrist\": [779,516],\"l_hip\": [584,500],\"l_knee\": [584,657],\"l_foot\": [0,0],\"r_hip\": [695,500],\"r_knee\": [723,673],\"r_foot\": [0,0],\"waist\": [640,375]},{\"head\": [667,78],\"neck\": [667,187],\"l_shoulder\": [528,234],\"l_elbow\": [445,234],\"l_wrist\": [528,125],\"r_shoulder\": [779,250],\"r_elbow\": [806,422],\"r_wrist\": [779,516],\"l_hip\": [584,500],\"l_knee\": [584,657],\"l_foot\": [0,0],\"r_hip\": [695,500],\"r_knee\": [723,673],\"r_foot\": [0,0],\"waist\": [640,375]}]";
        try {
            this.entries = new JSONArray(content);
        } catch (JSONException ex) {
            DebugLog.log(ex.getMessage());
        }
        executeInserts();
    }

    public void executeInserts() {
        DebugLog.log(String.valueOf(entries.length()));
        long sessionId = insertSession(this.entries.length());
        long insertId = 0;
        MPI poseModel = new MPI();
        for (int i = 0; i < this.entries.length(); i++) {
            try {
                JSONObject jsonObject = (JSONObject) this.entries.get(i);
                long frameId = insertFrame(i);
                insertSessionFrame(frameId, sessionId);
                for (String spart : poseModel.body_parts) {

                    Object part = jsonObject.get(spart);
                    String values = part.toString();
                    values = values.substring(1, values.length() - 1);

                    String[] nums = values.split(",");
                    double[] coordinates = StringArrToDoubleArr(nums);

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
