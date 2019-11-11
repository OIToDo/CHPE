package com.mygdx.game;

import android.util.Log;

import org.json.*;

import com.mygdx.game.PoseEstimation.nn.MPI;
import com.mygdx.game.PoseEstimation.nn.PoseModel;
import com.mygdx.game.persistance.AppDatabase;
import com.mygdx.game.persistance.Coordinate.NNCoordinate;
import com.mygdx.game.persistance.Coordinate.NNCoordinateDAO;
import com.mygdx.game.persistance.Frame.NNFrameDAO;
import com.mygdx.game.persistance.Frame.NNFrame;
import com.mygdx.game.persistance.Relations.NNFrameCoordinate;
import com.mygdx.game.persistance.Relations.NNFrameCoordinateDAO;
import com.mygdx.game.persistance.Relations.NNSessionFrame;
import com.mygdx.game.persistance.Relations.NNSessionFrameDAO;
import com.mygdx.game.persistance.Session.NNSession;
import com.mygdx.game.persistance.Session.NNSessionDAO;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;


public class MockData {

    private AppDatabase appDatabase;
    private JSONArray entries;

    public static int[] StringArrToIntArr(String[] s) {
        int[] result = new int[s.length];
        for (int i = 0; i < s.length; i++) {
            result[i] = Integer.parseInt(s[i]);
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
        NNSession nnSession = new NNSession();
        nnSession.frame_count = frameCount;
        nnSession.frames_per_second = 24;
        NNSessionDAO nnSessionDAO = this.appDatabase.nnSessionDAO();
        return nnSessionDAO.insert(nnSession);
    }
    private long insertCoordinate(int x, int y){

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
        NNSessionFrame nnSessionFrame = new NNSessionFrame();
        NNSessionFrameDAO nnSessionFrameDAO = this.appDatabase.nnSessionFrameDAO();
        nnSessionFrame.session_id = sid;
        nnSessionFrame.frame_id = fid;
        nnSessionFrameDAO.insert(nnSessionFrame);
    }


    public MockData(AppDatabase appDatabase) {
        this.appDatabase = appDatabase;

        String content = "[\n" +
                "    {\n" +
                "        \"head\": [\n" +
                "            667,\n" +
                "            78\n" +
                "        ],\n" +
                "        \"neck\": [\n" +
                "            667,\n" +
                "            187\n" +
                "        ],\n" +
                "        \"l_shoulder\": [\n" +
                "            528,\n" +
                "            234\n" +
                "        ],\n" +
                "        \"l_elbow\": [\n" +
                "            445,\n" +
                "            234\n" +
                "        ],\n" +
                "        \"l_wrist\": [\n" +
                "            528,\n" +
                "            125\n" +
                "        ],\n" +
                "        \"r_shoulder\": [\n" +
                "            779,\n" +
                "            250\n" +
                "        ],\n" +
                "        \"r_elbow\": [\n" +
                "            806,\n" +
                "            422\n" +
                "        ],\n" +
                "        \"r_wrist\": [\n" +
                "            779,\n" +
                "            516\n" +
                "        ],\n" +
                "        \"l_hip\": [\n" +
                "            584,\n" +
                "            500\n" +
                "        ],\n" +
                "        \"l_knee\": [\n" +
                "            556,\n" +
                "            657\n" +
                "        ],\n" +
                "        \"l_foot\": [\n" +
                "            0,\n" +
                "            0\n" +
                "        ],\n" +
                "        \"r_hip\": [\n" +
                "            695,\n" +
                "            500\n" +
                "        ],\n" +
                "        \"r_knee\": [\n" +
                "            723,\n" +
                "            673\n" +
                "        ],\n" +
                "        \"r_foot\": [\n" +
                "            0,\n" +
                "            0\n" +
                "        ],\n" +
                "        \"waist\": [\n" +
                "            640,\n" +
                "            375\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"head\": [\n" +
                "            667,\n" +
                "            78\n" +
                "        ],\n" +
                "        \"neck\": [\n" +
                "            667,\n" +
                "            187\n" +
                "        ],\n" +
                "        \"l_shoulder\": [\n" +
                "            528,\n" +
                "            234\n" +
                "        ],\n" +
                "        \"l_elbow\": [\n" +
                "            445,\n" +
                "            234\n" +
                "        ],\n" +
                "        \"l_wrist\": [\n" +
                "            528,\n" +
                "            125\n" +
                "        ],\n" +
                "        \"r_shoulder\": [\n" +
                "            779,\n" +
                "            250\n" +
                "        ],\n" +
                "        \"r_elbow\": [\n" +
                "            806,\n" +
                "            422\n" +
                "        ],\n" +
                "        \"r_wrist\": [\n" +
                "            779,\n" +
                "            516\n" +
                "        ],\n" +
                "        \"l_hip\": [\n" +
                "            584,\n" +
                "            500\n" +
                "        ],\n" +
                "        \"l_knee\": [\n" +
                "            556,\n" +
                "            657\n" +
                "        ],\n" +
                "        \"l_foot\": [\n" +
                "            0,\n" +
                "            0\n" +
                "        ],\n" +
                "        \"r_hip\": [\n" +
                "            695,\n" +
                "            500\n" +
                "        ],\n" +
                "        \"r_knee\": [\n" +
                "            723,\n" +
                "            673\n" +
                "        ],\n" +
                "        \"r_foot\": [\n" +
                "            0,\n" +
                "            0\n" +
                "        ],\n" +
                "        \"waist\": [\n" +
                "            640,\n" +
                "            375\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"head\": [\n" +
                "            667,\n" +
                "            78\n" +
                "        ],\n" +
                "        \"neck\": [\n" +
                "            667,\n" +
                "            187\n" +
                "        ],\n" +
                "        \"l_shoulder\": [\n" +
                "            528,\n" +
                "            234\n" +
                "        ],\n" +
                "        \"l_elbow\": [\n" +
                "            445,\n" +
                "            234\n" +
                "        ],\n" +
                "        \"l_wrist\": [\n" +
                "            528,\n" +
                "            125\n" +
                "        ],\n" +
                "        \"r_shoulder\": [\n" +
                "            779,\n" +
                "            250\n" +
                "        ],\n" +
                "        \"r_elbow\": [\n" +
                "            806,\n" +
                "            422\n" +
                "        ],\n" +
                "        \"r_wrist\": [\n" +
                "            779,\n" +
                "            516\n" +
                "        ],\n" +
                "        \"l_hip\": [\n" +
                "            584,\n" +
                "            500\n" +
                "        ],\n" +
                "        \"l_knee\": [\n" +
                "            556,\n" +
                "            657\n" +
                "        ],\n" +
                "        \"l_foot\": [\n" +
                "            0,\n" +
                "            0\n" +
                "        ],\n" +
                "        \"r_hip\": [\n" +
                "            695,\n" +
                "            500\n" +
                "        ],\n" +
                "        \"r_knee\": [\n" +
                "            723,\n" +
                "            673\n" +
                "        ],\n" +
                "        \"r_foot\": [\n" +
                "            0,\n" +
                "            0\n" +
                "        ],\n" +
                "        \"waist\": [\n" +
                "            640,\n" +
                "            375\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"head\": [\n" +
                "            667,\n" +
                "            78\n" +
                "        ],\n" +
                "        \"neck\": [\n" +
                "            667,\n" +
                "            187\n" +
                "        ],\n" +
                "        \"l_shoulder\": [\n" +
                "            528,\n" +
                "            234\n" +
                "        ],\n" +
                "        \"l_elbow\": [\n" +
                "            445,\n" +
                "            234\n" +
                "        ],\n" +
                "        \"l_wrist\": [\n" +
                "            528,\n" +
                "            125\n" +
                "        ],\n" +
                "        \"r_shoulder\": [\n" +
                "            779,\n" +
                "            250\n" +
                "        ],\n" +
                "        \"r_elbow\": [\n" +
                "            806,\n" +
                "            422\n" +
                "        ],\n" +
                "        \"r_wrist\": [\n" +
                "            779,\n" +
                "            516\n" +
                "        ],\n" +
                "        \"l_hip\": [\n" +
                "            584,\n" +
                "            500\n" +
                "        ],\n" +
                "        \"l_knee\": [\n" +
                "            556,\n" +
                "            657\n" +
                "        ],\n" +
                "        \"l_foot\": [\n" +
                "            0,\n" +
                "            0\n" +
                "        ],\n" +
                "        \"r_hip\": [\n" +
                "            695,\n" +
                "            500\n" +
                "        ],\n" +
                "        \"r_knee\": [\n" +
                "            723,\n" +
                "            673\n" +
                "        ],\n" +
                "        \"r_foot\": [\n" +
                "            0,\n" +
                "            0\n" +
                "        ],\n" +
                "        \"waist\": [\n" +
                "            640,\n" +
                "            375\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"head\": [\n" +
                "            667,\n" +
                "            78\n" +
                "        ],\n" +
                "        \"neck\": [\n" +
                "            667,\n" +
                "            187\n" +
                "        ],\n" +
                "        \"l_shoulder\": [\n" +
                "            528,\n" +
                "            234\n" +
                "        ],\n" +
                "        \"l_elbow\": [\n" +
                "            445,\n" +
                "            234\n" +
                "        ],\n" +
                "        \"l_wrist\": [\n" +
                "            528,\n" +
                "            125\n" +
                "        ],\n" +
                "        \"r_shoulder\": [\n" +
                "            779,\n" +
                "            250\n" +
                "        ],\n" +
                "        \"r_elbow\": [\n" +
                "            806,\n" +
                "            422\n" +
                "        ],\n" +
                "        \"r_wrist\": [\n" +
                "            779,\n" +
                "            516\n" +
                "        ],\n" +
                "        \"l_hip\": [\n" +
                "            584,\n" +
                "            500\n" +
                "        ],\n" +
                "        \"l_knee\": [\n" +
                "            556,\n" +
                "            657\n" +
                "        ],\n" +
                "        \"l_foot\": [\n" +
                "            0,\n" +
                "            0\n" +
                "        ],\n" +
                "        \"r_hip\": [\n" +
                "            695,\n" +
                "            500\n" +
                "        ],\n" +
                "        \"r_knee\": [\n" +
                "            695,\n" +
                "            688\n" +
                "        ],\n" +
                "        \"r_foot\": [\n" +
                "            0,\n" +
                "            0\n" +
                "        ],\n" +
                "        \"waist\": [\n" +
                "            640,\n" +
                "            375\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"head\": [\n" +
                "            667,\n" +
                "            78\n" +
                "        ],\n" +
                "        \"neck\": [\n" +
                "            667,\n" +
                "            187\n" +
                "        ],\n" +
                "        \"l_shoulder\": [\n" +
                "            528,\n" +
                "            234\n" +
                "        ],\n" +
                "        \"l_elbow\": [\n" +
                "            445,\n" +
                "            234\n" +
                "        ],\n" +
                "        \"l_wrist\": [\n" +
                "            528,\n" +
                "            125\n" +
                "        ],\n" +
                "        \"r_shoulder\": [\n" +
                "            779,\n" +
                "            250\n" +
                "        ],\n" +
                "        \"r_elbow\": [\n" +
                "            806,\n" +
                "            422\n" +
                "        ],\n" +
                "        \"r_wrist\": [\n" +
                "            779,\n" +
                "            516\n" +
                "        ],\n" +
                "        \"l_hip\": [\n" +
                "            584,\n" +
                "            500\n" +
                "        ],\n" +
                "        \"l_knee\": [\n" +
                "            556,\n" +
                "            657\n" +
                "        ],\n" +
                "        \"l_foot\": [\n" +
                "            0,\n" +
                "            0\n" +
                "        ],\n" +
                "        \"r_hip\": [\n" +
                "            695,\n" +
                "            500\n" +
                "        ],\n" +
                "        \"r_knee\": [\n" +
                "            695,\n" +
                "            688\n" +
                "        ],\n" +
                "        \"r_foot\": [\n" +
                "            0,\n" +
                "            0\n" +
                "        ],\n" +
                "        \"waist\": [\n" +
                "            640,\n" +
                "            375\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"head\": [\n" +
                "            667,\n" +
                "            78\n" +
                "        ],\n" +
                "        \"neck\": [\n" +
                "            667,\n" +
                "            187\n" +
                "        ],\n" +
                "        \"l_shoulder\": [\n" +
                "            528,\n" +
                "            234\n" +
                "        ],\n" +
                "        \"l_elbow\": [\n" +
                "            445,\n" +
                "            234\n" +
                "        ],\n" +
                "        \"l_wrist\": [\n" +
                "            528,\n" +
                "            125\n" +
                "        ],\n" +
                "        \"r_shoulder\": [\n" +
                "            779,\n" +
                "            250\n" +
                "        ],\n" +
                "        \"r_elbow\": [\n" +
                "            806,\n" +
                "            422\n" +
                "        ],\n" +
                "        \"r_wrist\": [\n" +
                "            779,\n" +
                "            516\n" +
                "        ],\n" +
                "        \"l_hip\": [\n" +
                "            584,\n" +
                "            500\n" +
                "        ],\n" +
                "        \"l_knee\": [\n" +
                "            556,\n" +
                "            657\n" +
                "        ],\n" +
                "        \"l_foot\": [\n" +
                "            0,\n" +
                "            0\n" +
                "        ],\n" +
                "        \"r_hip\": [\n" +
                "            695,\n" +
                "            500\n" +
                "        ],\n" +
                "        \"r_knee\": [\n" +
                "            695,\n" +
                "            688\n" +
                "        ],\n" +
                "        \"r_foot\": [\n" +
                "            0,\n" +
                "            0\n" +
                "        ],\n" +
                "        \"waist\": [\n" +
                "            640,\n" +
                "            375\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"head\": [\n" +
                "            667,\n" +
                "            78\n" +
                "        ],\n" +
                "        \"neck\": [\n" +
                "            667,\n" +
                "            187\n" +
                "        ],\n" +
                "        \"l_shoulder\": [\n" +
                "            528,\n" +
                "            234\n" +
                "        ],\n" +
                "        \"l_elbow\": [\n" +
                "            445,\n" +
                "            234\n" +
                "        ],\n" +
                "        \"l_wrist\": [\n" +
                "            528,\n" +
                "            125\n" +
                "        ],\n" +
                "        \"r_shoulder\": [\n" +
                "            779,\n" +
                "            250\n" +
                "        ],\n" +
                "        \"r_elbow\": [\n" +
                "            806,\n" +
                "            422\n" +
                "        ],\n" +
                "        \"r_wrist\": [\n" +
                "            779,\n" +
                "            516\n" +
                "        ],\n" +
                "        \"l_hip\": [\n" +
                "            584,\n" +
                "            500\n" +
                "        ],\n" +
                "        \"l_knee\": [\n" +
                "            556,\n" +
                "            657\n" +
                "        ],\n" +
                "        \"l_foot\": [\n" +
                "            0,\n" +
                "            0\n" +
                "        ],\n" +
                "        \"r_hip\": [\n" +
                "            695,\n" +
                "            500\n" +
                "        ],\n" +
                "        \"r_knee\": [\n" +
                "            695,\n" +
                "            688\n" +
                "        ],\n" +
                "        \"r_foot\": [\n" +
                "            0,\n" +
                "            0\n" +
                "        ],\n" +
                "        \"waist\": [\n" +
                "            640,\n" +
                "            375\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"head\": [\n" +
                "            667,\n" +
                "            78\n" +
                "        ],\n" +
                "        \"neck\": [\n" +
                "            667,\n" +
                "            187\n" +
                "        ],\n" +
                "        \"l_shoulder\": [\n" +
                "            528,\n" +
                "            234\n" +
                "        ],\n" +
                "        \"l_elbow\": [\n" +
                "            445,\n" +
                "            234\n" +
                "        ],\n" +
                "        \"l_wrist\": [\n" +
                "            528,\n" +
                "            125\n" +
                "        ],\n" +
                "        \"r_shoulder\": [\n" +
                "            779,\n" +
                "            250\n" +
                "        ],\n" +
                "        \"r_elbow\": [\n" +
                "            806,\n" +
                "            422\n" +
                "        ],\n" +
                "        \"r_wrist\": [\n" +
                "            779,\n" +
                "            516\n" +
                "        ],\n" +
                "        \"l_hip\": [\n" +
                "            584,\n" +
                "            500\n" +
                "        ],\n" +
                "        \"l_knee\": [\n" +
                "            556,\n" +
                "            657\n" +
                "        ],\n" +
                "        \"l_foot\": [\n" +
                "            0,\n" +
                "            0\n" +
                "        ],\n" +
                "        \"r_hip\": [\n" +
                "            695,\n" +
                "            500\n" +
                "        ],\n" +
                "        \"r_knee\": [\n" +
                "            695,\n" +
                "            688\n" +
                "        ],\n" +
                "        \"r_foot\": [\n" +
                "            0,\n" +
                "            0\n" +
                "        ],\n" +
                "        \"waist\": [\n" +
                "            640,\n" +
                "            375\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"head\": [\n" +
                "            667,\n" +
                "            78\n" +
                "        ],\n" +
                "        \"neck\": [\n" +
                "            667,\n" +
                "            187\n" +
                "        ],\n" +
                "        \"l_shoulder\": [\n" +
                "            528,\n" +
                "            234\n" +
                "        ],\n" +
                "        \"l_elbow\": [\n" +
                "            445,\n" +
                "            234\n" +
                "        ],\n" +
                "        \"l_wrist\": [\n" +
                "            528,\n" +
                "            125\n" +
                "        ],\n" +
                "        \"r_shoulder\": [\n" +
                "            779,\n" +
                "            250\n" +
                "        ],\n" +
                "        \"r_elbow\": [\n" +
                "            806,\n" +
                "            422\n" +
                "        ],\n" +
                "        \"r_wrist\": [\n" +
                "            779,\n" +
                "            516\n" +
                "        ],\n" +
                "        \"l_hip\": [\n" +
                "            584,\n" +
                "            500\n" +
                "        ],\n" +
                "        \"l_knee\": [\n" +
                "            556,\n" +
                "            657\n" +
                "        ],\n" +
                "        \"l_foot\": [\n" +
                "            0,\n" +
                "            0\n" +
                "        ],\n" +
                "        \"r_hip\": [\n" +
                "            695,\n" +
                "            500\n" +
                "        ],\n" +
                "        \"r_knee\": [\n" +
                "            695,\n" +
                "            688\n" +
                "        ],\n" +
                "        \"r_foot\": [\n" +
                "            0,\n" +
                "            0\n" +
                "        ],\n" +
                "        \"waist\": [\n" +
                "            640,\n" +
                "            375\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"head\": [\n" +
                "            667,\n" +
                "            78\n" +
                "        ],\n" +
                "        \"neck\": [\n" +
                "            667,\n" +
                "            187\n" +
                "        ],\n" +
                "        \"l_shoulder\": [\n" +
                "            528,\n" +
                "            234\n" +
                "        ],\n" +
                "        \"l_elbow\": [\n" +
                "            445,\n" +
                "            234\n" +
                "        ],\n" +
                "        \"l_wrist\": [\n" +
                "            528,\n" +
                "            125\n" +
                "        ],\n" +
                "        \"r_shoulder\": [\n" +
                "            779,\n" +
                "            250\n" +
                "        ],\n" +
                "        \"r_elbow\": [\n" +
                "            806,\n" +
                "            422\n" +
                "        ],\n" +
                "        \"r_wrist\": [\n" +
                "            779,\n" +
                "            516\n" +
                "        ],\n" +
                "        \"l_hip\": [\n" +
                "            584,\n" +
                "            500\n" +
                "        ],\n" +
                "        \"l_knee\": [\n" +
                "            556,\n" +
                "            657\n" +
                "        ],\n" +
                "        \"l_foot\": [\n" +
                "            0,\n" +
                "            0\n" +
                "        ],\n" +
                "        \"r_hip\": [\n" +
                "            695,\n" +
                "            500\n" +
                "        ],\n" +
                "        \"r_knee\": [\n" +
                "            695,\n" +
                "            688\n" +
                "        ],\n" +
                "        \"r_foot\": [\n" +
                "            0,\n" +
                "            0\n" +
                "        ],\n" +
                "        \"waist\": [\n" +
                "            640,\n" +
                "            375\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"head\": [\n" +
                "            667,\n" +
                "            78\n" +
                "        ],\n" +
                "        \"neck\": [\n" +
                "            667,\n" +
                "            187\n" +
                "        ],\n" +
                "        \"l_shoulder\": [\n" +
                "            528,\n" +
                "            234\n" +
                "        ],\n" +
                "        \"l_elbow\": [\n" +
                "            445,\n" +
                "            234\n" +
                "        ],\n" +
                "        \"l_wrist\": [\n" +
                "            528,\n" +
                "            125\n" +
                "        ],\n" +
                "        \"r_shoulder\": [\n" +
                "            779,\n" +
                "            250\n" +
                "        ],\n" +
                "        \"r_elbow\": [\n" +
                "            806,\n" +
                "            422\n" +
                "        ],\n" +
                "        \"r_wrist\": [\n" +
                "            779,\n" +
                "            516\n" +
                "        ],\n" +
                "        \"l_hip\": [\n" +
                "            584,\n" +
                "            500\n" +
                "        ],\n" +
                "        \"l_knee\": [\n" +
                "            556,\n" +
                "            657\n" +
                "        ],\n" +
                "        \"l_foot\": [\n" +
                "            0,\n" +
                "            0\n" +
                "        ],\n" +
                "        \"r_hip\": [\n" +
                "            695,\n" +
                "            500\n" +
                "        ],\n" +
                "        \"r_knee\": [\n" +
                "            695,\n" +
                "            688\n" +
                "        ],\n" +
                "        \"r_foot\": [\n" +
                "            0,\n" +
                "            0\n" +
                "        ],\n" +
                "        \"waist\": [\n" +
                "            640,\n" +
                "            375\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"head\": [\n" +
                "            667,\n" +
                "            78\n" +
                "        ],\n" +
                "        \"neck\": [\n" +
                "            667,\n" +
                "            187\n" +
                "        ],\n" +
                "        \"l_shoulder\": [\n" +
                "            528,\n" +
                "            234\n" +
                "        ],\n" +
                "        \"l_elbow\": [\n" +
                "            445,\n" +
                "            234\n" +
                "        ],\n" +
                "        \"l_wrist\": [\n" +
                "            528,\n" +
                "            125\n" +
                "        ],\n" +
                "        \"r_shoulder\": [\n" +
                "            779,\n" +
                "            250\n" +
                "        ],\n" +
                "        \"r_elbow\": [\n" +
                "            806,\n" +
                "            422\n" +
                "        ],\n" +
                "        \"r_wrist\": [\n" +
                "            779,\n" +
                "            516\n" +
                "        ],\n" +
                "        \"l_hip\": [\n" +
                "            584,\n" +
                "            500\n" +
                "        ],\n" +
                "        \"l_knee\": [\n" +
                "            584,\n" +
                "            657\n" +
                "        ],\n" +
                "        \"l_foot\": [\n" +
                "            0,\n" +
                "            0\n" +
                "        ],\n" +
                "        \"r_hip\": [\n" +
                "            695,\n" +
                "            500\n" +
                "        ],\n" +
                "        \"r_knee\": [\n" +
                "            695,\n" +
                "            688\n" +
                "        ],\n" +
                "        \"r_foot\": [\n" +
                "            0,\n" +
                "            0\n" +
                "        ],\n" +
                "        \"waist\": [\n" +
                "            640,\n" +
                "            375\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"head\": [\n" +
                "            667,\n" +
                "            78\n" +
                "        ],\n" +
                "        \"neck\": [\n" +
                "            667,\n" +
                "            187\n" +
                "        ],\n" +
                "        \"l_shoulder\": [\n" +
                "            528,\n" +
                "            234\n" +
                "        ],\n" +
                "        \"l_elbow\": [\n" +
                "            445,\n" +
                "            234\n" +
                "        ],\n" +
                "        \"l_wrist\": [\n" +
                "            528,\n" +
                "            125\n" +
                "        ],\n" +
                "        \"r_shoulder\": [\n" +
                "            779,\n" +
                "            250\n" +
                "        ],\n" +
                "        \"r_elbow\": [\n" +
                "            806,\n" +
                "            422\n" +
                "        ],\n" +
                "        \"r_wrist\": [\n" +
                "            779,\n" +
                "            516\n" +
                "        ],\n" +
                "        \"l_hip\": [\n" +
                "            584,\n" +
                "            500\n" +
                "        ],\n" +
                "        \"l_knee\": [\n" +
                "            584,\n" +
                "            657\n" +
                "        ],\n" +
                "        \"l_foot\": [\n" +
                "            0,\n" +
                "            0\n" +
                "        ],\n" +
                "        \"r_hip\": [\n" +
                "            695,\n" +
                "            500\n" +
                "        ],\n" +
                "        \"r_knee\": [\n" +
                "            695,\n" +
                "            688\n" +
                "        ],\n" +
                "        \"r_foot\": [\n" +
                "            0,\n" +
                "            0\n" +
                "        ],\n" +
                "        \"waist\": [\n" +
                "            640,\n" +
                "            375\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"head\": [\n" +
                "            667,\n" +
                "            78\n" +
                "        ],\n" +
                "        \"neck\": [\n" +
                "            667,\n" +
                "            187\n" +
                "        ],\n" +
                "        \"l_shoulder\": [\n" +
                "            528,\n" +
                "            234\n" +
                "        ],\n" +
                "        \"l_elbow\": [\n" +
                "            445,\n" +
                "            234\n" +
                "        ],\n" +
                "        \"l_wrist\": [\n" +
                "            528,\n" +
                "            125\n" +
                "        ],\n" +
                "        \"r_shoulder\": [\n" +
                "            779,\n" +
                "            250\n" +
                "        ],\n" +
                "        \"r_elbow\": [\n" +
                "            806,\n" +
                "            422\n" +
                "        ],\n" +
                "        \"r_wrist\": [\n" +
                "            779,\n" +
                "            516\n" +
                "        ],\n" +
                "        \"l_hip\": [\n" +
                "            584,\n" +
                "            500\n" +
                "        ],\n" +
                "        \"l_knee\": [\n" +
                "            584,\n" +
                "            657\n" +
                "        ],\n" +
                "        \"l_foot\": [\n" +
                "            0,\n" +
                "            0\n" +
                "        ],\n" +
                "        \"r_hip\": [\n" +
                "            695,\n" +
                "            500\n" +
                "        ],\n" +
                "        \"r_knee\": [\n" +
                "            695,\n" +
                "            688\n" +
                "        ],\n" +
                "        \"r_foot\": [\n" +
                "            0,\n" +
                "            0\n" +
                "        ],\n" +
                "        \"waist\": [\n" +
                "            640,\n" +
                "            375\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"head\": [\n" +
                "            667,\n" +
                "            78\n" +
                "        ],\n" +
                "        \"neck\": [\n" +
                "            667,\n" +
                "            187\n" +
                "        ],\n" +
                "        \"l_shoulder\": [\n" +
                "            528,\n" +
                "            234\n" +
                "        ],\n" +
                "        \"l_elbow\": [\n" +
                "            445,\n" +
                "            234\n" +
                "        ],\n" +
                "        \"l_wrist\": [\n" +
                "            528,\n" +
                "            125\n" +
                "        ],\n" +
                "        \"r_shoulder\": [\n" +
                "            779,\n" +
                "            250\n" +
                "        ],\n" +
                "        \"r_elbow\": [\n" +
                "            806,\n" +
                "            422\n" +
                "        ],\n" +
                "        \"r_wrist\": [\n" +
                "            779,\n" +
                "            516\n" +
                "        ],\n" +
                "        \"l_hip\": [\n" +
                "            584,\n" +
                "            500\n" +
                "        ],\n" +
                "        \"l_knee\": [\n" +
                "            584,\n" +
                "            657\n" +
                "        ],\n" +
                "        \"l_foot\": [\n" +
                "            0,\n" +
                "            0\n" +
                "        ],\n" +
                "        \"r_hip\": [\n" +
                "            695,\n" +
                "            500\n" +
                "        ],\n" +
                "        \"r_knee\": [\n" +
                "            695,\n" +
                "            688\n" +
                "        ],\n" +
                "        \"r_foot\": [\n" +
                "            0,\n" +
                "            0\n" +
                "        ],\n" +
                "        \"waist\": [\n" +
                "            640,\n" +
                "            375\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"head\": [\n" +
                "            667,\n" +
                "            78\n" +
                "        ],\n" +
                "        \"neck\": [\n" +
                "            667,\n" +
                "            187\n" +
                "        ],\n" +
                "        \"l_shoulder\": [\n" +
                "            528,\n" +
                "            234\n" +
                "        ],\n" +
                "        \"l_elbow\": [\n" +
                "            445,\n" +
                "            234\n" +
                "        ],\n" +
                "        \"l_wrist\": [\n" +
                "            528,\n" +
                "            125\n" +
                "        ],\n" +
                "        \"r_shoulder\": [\n" +
                "            779,\n" +
                "            250\n" +
                "        ],\n" +
                "        \"r_elbow\": [\n" +
                "            806,\n" +
                "            422\n" +
                "        ],\n" +
                "        \"r_wrist\": [\n" +
                "            779,\n" +
                "            516\n" +
                "        ],\n" +
                "        \"l_hip\": [\n" +
                "            584,\n" +
                "            500\n" +
                "        ],\n" +
                "        \"l_knee\": [\n" +
                "            584,\n" +
                "            657\n" +
                "        ],\n" +
                "        \"l_foot\": [\n" +
                "            0,\n" +
                "            0\n" +
                "        ],\n" +
                "        \"r_hip\": [\n" +
                "            695,\n" +
                "            500\n" +
                "        ],\n" +
                "        \"r_knee\": [\n" +
                "            695,\n" +
                "            688\n" +
                "        ],\n" +
                "        \"r_foot\": [\n" +
                "            0,\n" +
                "            0\n" +
                "        ],\n" +
                "        \"waist\": [\n" +
                "            640,\n" +
                "            375\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"head\": [\n" +
                "            667,\n" +
                "            78\n" +
                "        ],\n" +
                "        \"neck\": [\n" +
                "            667,\n" +
                "            187\n" +
                "        ],\n" +
                "        \"l_shoulder\": [\n" +
                "            528,\n" +
                "            234\n" +
                "        ],\n" +
                "        \"l_elbow\": [\n" +
                "            445,\n" +
                "            234\n" +
                "        ],\n" +
                "        \"l_wrist\": [\n" +
                "            528,\n" +
                "            125\n" +
                "        ],\n" +
                "        \"r_shoulder\": [\n" +
                "            779,\n" +
                "            250\n" +
                "        ],\n" +
                "        \"r_elbow\": [\n" +
                "            806,\n" +
                "            422\n" +
                "        ],\n" +
                "        \"r_wrist\": [\n" +
                "            779,\n" +
                "            516\n" +
                "        ],\n" +
                "        \"l_hip\": [\n" +
                "            584,\n" +
                "            500\n" +
                "        ],\n" +
                "        \"l_knee\": [\n" +
                "            584,\n" +
                "            657\n" +
                "        ],\n" +
                "        \"l_foot\": [\n" +
                "            0,\n" +
                "            0\n" +
                "        ],\n" +
                "        \"r_hip\": [\n" +
                "            695,\n" +
                "            500\n" +
                "        ],\n" +
                "        \"r_knee\": [\n" +
                "            695,\n" +
                "            688\n" +
                "        ],\n" +
                "        \"r_foot\": [\n" +
                "            0,\n" +
                "            0\n" +
                "        ],\n" +
                "        \"waist\": [\n" +
                "            640,\n" +
                "            375\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"head\": [\n" +
                "            667,\n" +
                "            78\n" +
                "        ],\n" +
                "        \"neck\": [\n" +
                "            667,\n" +
                "            187\n" +
                "        ],\n" +
                "        \"l_shoulder\": [\n" +
                "            528,\n" +
                "            234\n" +
                "        ],\n" +
                "        \"l_elbow\": [\n" +
                "            445,\n" +
                "            234\n" +
                "        ],\n" +
                "        \"l_wrist\": [\n" +
                "            528,\n" +
                "            125\n" +
                "        ],\n" +
                "        \"r_shoulder\": [\n" +
                "            779,\n" +
                "            250\n" +
                "        ],\n" +
                "        \"r_elbow\": [\n" +
                "            806,\n" +
                "            422\n" +
                "        ],\n" +
                "        \"r_wrist\": [\n" +
                "            779,\n" +
                "            516\n" +
                "        ],\n" +
                "        \"l_hip\": [\n" +
                "            584,\n" +
                "            500\n" +
                "        ],\n" +
                "        \"l_knee\": [\n" +
                "            584,\n" +
                "            657\n" +
                "        ],\n" +
                "        \"l_foot\": [\n" +
                "            0,\n" +
                "            0\n" +
                "        ],\n" +
                "        \"r_hip\": [\n" +
                "            695,\n" +
                "            500\n" +
                "        ],\n" +
                "        \"r_knee\": [\n" +
                "            695,\n" +
                "            688\n" +
                "        ],\n" +
                "        \"r_foot\": [\n" +
                "            0,\n" +
                "            0\n" +
                "        ],\n" +
                "        \"waist\": [\n" +
                "            640,\n" +
                "            375\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"head\": [\n" +
                "            667,\n" +
                "            78\n" +
                "        ],\n" +
                "        \"neck\": [\n" +
                "            667,\n" +
                "            187\n" +
                "        ],\n" +
                "        \"l_shoulder\": [\n" +
                "            528,\n" +
                "            234\n" +
                "        ],\n" +
                "        \"l_elbow\": [\n" +
                "            445,\n" +
                "            234\n" +
                "        ],\n" +
                "        \"l_wrist\": [\n" +
                "            528,\n" +
                "            125\n" +
                "        ],\n" +
                "        \"r_shoulder\": [\n" +
                "            779,\n" +
                "            250\n" +
                "        ],\n" +
                "        \"r_elbow\": [\n" +
                "            806,\n" +
                "            422\n" +
                "        ],\n" +
                "        \"r_wrist\": [\n" +
                "            779,\n" +
                "            516\n" +
                "        ],\n" +
                "        \"l_hip\": [\n" +
                "            584,\n" +
                "            500\n" +
                "        ],\n" +
                "        \"l_knee\": [\n" +
                "            584,\n" +
                "            657\n" +
                "        ],\n" +
                "        \"l_foot\": [\n" +
                "            0,\n" +
                "            0\n" +
                "        ],\n" +
                "        \"r_hip\": [\n" +
                "            695,\n" +
                "            500\n" +
                "        ],\n" +
                "        \"r_knee\": [\n" +
                "            695,\n" +
                "            688\n" +
                "        ],\n" +
                "        \"r_foot\": [\n" +
                "            0,\n" +
                "            0\n" +
                "        ],\n" +
                "        \"waist\": [\n" +
                "            640,\n" +
                "            375\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"head\": [\n" +
                "            667,\n" +
                "            78\n" +
                "        ],\n" +
                "        \"neck\": [\n" +
                "            667,\n" +
                "            187\n" +
                "        ],\n" +
                "        \"l_shoulder\": [\n" +
                "            528,\n" +
                "            234\n" +
                "        ],\n" +
                "        \"l_elbow\": [\n" +
                "            445,\n" +
                "            234\n" +
                "        ],\n" +
                "        \"l_wrist\": [\n" +
                "            528,\n" +
                "            125\n" +
                "        ],\n" +
                "        \"r_shoulder\": [\n" +
                "            779,\n" +
                "            250\n" +
                "        ],\n" +
                "        \"r_elbow\": [\n" +
                "            806,\n" +
                "            422\n" +
                "        ],\n" +
                "        \"r_wrist\": [\n" +
                "            779,\n" +
                "            516\n" +
                "        ],\n" +
                "        \"l_hip\": [\n" +
                "            584,\n" +
                "            500\n" +
                "        ],\n" +
                "        \"l_knee\": [\n" +
                "            584,\n" +
                "            657\n" +
                "        ],\n" +
                "        \"l_foot\": [\n" +
                "            0,\n" +
                "            0\n" +
                "        ],\n" +
                "        \"r_hip\": [\n" +
                "            695,\n" +
                "            500\n" +
                "        ],\n" +
                "        \"r_knee\": [\n" +
                "            695,\n" +
                "            688\n" +
                "        ],\n" +
                "        \"r_foot\": [\n" +
                "            0,\n" +
                "            0\n" +
                "        ],\n" +
                "        \"waist\": [\n" +
                "            640,\n" +
                "            375\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"head\": [\n" +
                "            667,\n" +
                "            78\n" +
                "        ],\n" +
                "        \"neck\": [\n" +
                "            667,\n" +
                "            187\n" +
                "        ],\n" +
                "        \"l_shoulder\": [\n" +
                "            528,\n" +
                "            234\n" +
                "        ],\n" +
                "        \"l_elbow\": [\n" +
                "            445,\n" +
                "            234\n" +
                "        ],\n" +
                "        \"l_wrist\": [\n" +
                "            528,\n" +
                "            125\n" +
                "        ],\n" +
                "        \"r_shoulder\": [\n" +
                "            779,\n" +
                "            250\n" +
                "        ],\n" +
                "        \"r_elbow\": [\n" +
                "            806,\n" +
                "            422\n" +
                "        ],\n" +
                "        \"r_wrist\": [\n" +
                "            779,\n" +
                "            516\n" +
                "        ],\n" +
                "        \"l_hip\": [\n" +
                "            584,\n" +
                "            500\n" +
                "        ],\n" +
                "        \"l_knee\": [\n" +
                "            584,\n" +
                "            657\n" +
                "        ],\n" +
                "        \"l_foot\": [\n" +
                "            0,\n" +
                "            0\n" +
                "        ],\n" +
                "        \"r_hip\": [\n" +
                "            695,\n" +
                "            500\n" +
                "        ],\n" +
                "        \"r_knee\": [\n" +
                "            695,\n" +
                "            688\n" +
                "        ],\n" +
                "        \"r_foot\": [\n" +
                "            0,\n" +
                "            0\n" +
                "        ],\n" +
                "        \"waist\": [\n" +
                "            640,\n" +
                "            375\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"head\": [\n" +
                "            667,\n" +
                "            78\n" +
                "        ],\n" +
                "        \"neck\": [\n" +
                "            667,\n" +
                "            187\n" +
                "        ],\n" +
                "        \"l_shoulder\": [\n" +
                "            528,\n" +
                "            234\n" +
                "        ],\n" +
                "        \"l_elbow\": [\n" +
                "            445,\n" +
                "            234\n" +
                "        ],\n" +
                "        \"l_wrist\": [\n" +
                "            528,\n" +
                "            125\n" +
                "        ],\n" +
                "        \"r_shoulder\": [\n" +
                "            779,\n" +
                "            250\n" +
                "        ],\n" +
                "        \"r_elbow\": [\n" +
                "            806,\n" +
                "            422\n" +
                "        ],\n" +
                "        \"r_wrist\": [\n" +
                "            779,\n" +
                "            516\n" +
                "        ],\n" +
                "        \"l_hip\": [\n" +
                "            584,\n" +
                "            500\n" +
                "        ],\n" +
                "        \"l_knee\": [\n" +
                "            584,\n" +
                "            657\n" +
                "        ],\n" +
                "        \"l_foot\": [\n" +
                "            0,\n" +
                "            0\n" +
                "        ],\n" +
                "        \"r_hip\": [\n" +
                "            695,\n" +
                "            500\n" +
                "        ],\n" +
                "        \"r_knee\": [\n" +
                "            695,\n" +
                "            688\n" +
                "        ],\n" +
                "        \"r_foot\": [\n" +
                "            0,\n" +
                "            0\n" +
                "        ],\n" +
                "        \"waist\": [\n" +
                "            640,\n" +
                "            375\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"head\": [\n" +
                "            667,\n" +
                "            78\n" +
                "        ],\n" +
                "        \"neck\": [\n" +
                "            667,\n" +
                "            187\n" +
                "        ],\n" +
                "        \"l_shoulder\": [\n" +
                "            528,\n" +
                "            234\n" +
                "        ],\n" +
                "        \"l_elbow\": [\n" +
                "            445,\n" +
                "            234\n" +
                "        ],\n" +
                "        \"l_wrist\": [\n" +
                "            528,\n" +
                "            125\n" +
                "        ],\n" +
                "        \"r_shoulder\": [\n" +
                "            779,\n" +
                "            250\n" +
                "        ],\n" +
                "        \"r_elbow\": [\n" +
                "            806,\n" +
                "            422\n" +
                "        ],\n" +
                "        \"r_wrist\": [\n" +
                "            779,\n" +
                "            516\n" +
                "        ],\n" +
                "        \"l_hip\": [\n" +
                "            584,\n" +
                "            500\n" +
                "        ],\n" +
                "        \"l_knee\": [\n" +
                "            556,\n" +
                "            657\n" +
                "        ],\n" +
                "        \"l_foot\": [\n" +
                "            0,\n" +
                "            0\n" +
                "        ],\n" +
                "        \"r_hip\": [\n" +
                "            695,\n" +
                "            500\n" +
                "        ],\n" +
                "        \"r_knee\": [\n" +
                "            695,\n" +
                "            688\n" +
                "        ],\n" +
                "        \"r_foot\": [\n" +
                "            0,\n" +
                "            0\n" +
                "        ],\n" +
                "        \"waist\": [\n" +
                "            640,\n" +
                "            375\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"head\": [\n" +
                "            667,\n" +
                "            78\n" +
                "        ],\n" +
                "        \"neck\": [\n" +
                "            667,\n" +
                "            187\n" +
                "        ],\n" +
                "        \"l_shoulder\": [\n" +
                "            528,\n" +
                "            234\n" +
                "        ],\n" +
                "        \"l_elbow\": [\n" +
                "            445,\n" +
                "            234\n" +
                "        ],\n" +
                "        \"l_wrist\": [\n" +
                "            528,\n" +
                "            125\n" +
                "        ],\n" +
                "        \"r_shoulder\": [\n" +
                "            779,\n" +
                "            250\n" +
                "        ],\n" +
                "        \"r_elbow\": [\n" +
                "            806,\n" +
                "            422\n" +
                "        ],\n" +
                "        \"r_wrist\": [\n" +
                "            779,\n" +
                "            516\n" +
                "        ],\n" +
                "        \"l_hip\": [\n" +
                "            584,\n" +
                "            500\n" +
                "        ],\n" +
                "        \"l_knee\": [\n" +
                "            584,\n" +
                "            657\n" +
                "        ],\n" +
                "        \"l_foot\": [\n" +
                "            0,\n" +
                "            0\n" +
                "        ],\n" +
                "        \"r_hip\": [\n" +
                "            695,\n" +
                "            500\n" +
                "        ],\n" +
                "        \"r_knee\": [\n" +
                "            695,\n" +
                "            688\n" +
                "        ],\n" +
                "        \"r_foot\": [\n" +
                "            0,\n" +
                "            0\n" +
                "        ],\n" +
                "        \"waist\": [\n" +
                "            640,\n" +
                "            375\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"head\": [\n" +
                "            667,\n" +
                "            78\n" +
                "        ],\n" +
                "        \"neck\": [\n" +
                "            667,\n" +
                "            187\n" +
                "        ],\n" +
                "        \"l_shoulder\": [\n" +
                "            528,\n" +
                "            234\n" +
                "        ],\n" +
                "        \"l_elbow\": [\n" +
                "            445,\n" +
                "            234\n" +
                "        ],\n" +
                "        \"l_wrist\": [\n" +
                "            528,\n" +
                "            125\n" +
                "        ],\n" +
                "        \"r_shoulder\": [\n" +
                "            779,\n" +
                "            250\n" +
                "        ],\n" +
                "        \"r_elbow\": [\n" +
                "            806,\n" +
                "            422\n" +
                "        ],\n" +
                "        \"r_wrist\": [\n" +
                "            779,\n" +
                "            516\n" +
                "        ],\n" +
                "        \"l_hip\": [\n" +
                "            584,\n" +
                "            500\n" +
                "        ],\n" +
                "        \"l_knee\": [\n" +
                "            584,\n" +
                "            657\n" +
                "        ],\n" +
                "        \"l_foot\": [\n" +
                "            0,\n" +
                "            0\n" +
                "        ],\n" +
                "        \"r_hip\": [\n" +
                "            695,\n" +
                "            500\n" +
                "        ],\n" +
                "        \"r_knee\": [\n" +
                "            695,\n" +
                "            688\n" +
                "        ],\n" +
                "        \"r_foot\": [\n" +
                "            0,\n" +
                "            0\n" +
                "        ],\n" +
                "        \"waist\": [\n" +
                "            640,\n" +
                "            375\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"head\": [\n" +
                "            667,\n" +
                "            78\n" +
                "        ],\n" +
                "        \"neck\": [\n" +
                "            667,\n" +
                "            187\n" +
                "        ],\n" +
                "        \"l_shoulder\": [\n" +
                "            528,\n" +
                "            234\n" +
                "        ],\n" +
                "        \"l_elbow\": [\n" +
                "            445,\n" +
                "            234\n" +
                "        ],\n" +
                "        \"l_wrist\": [\n" +
                "            528,\n" +
                "            125\n" +
                "        ],\n" +
                "        \"r_shoulder\": [\n" +
                "            779,\n" +
                "            250\n" +
                "        ],\n" +
                "        \"r_elbow\": [\n" +
                "            806,\n" +
                "            422\n" +
                "        ],\n" +
                "        \"r_wrist\": [\n" +
                "            779,\n" +
                "            516\n" +
                "        ],\n" +
                "        \"l_hip\": [\n" +
                "            584,\n" +
                "            500\n" +
                "        ],\n" +
                "        \"l_knee\": [\n" +
                "            584,\n" +
                "            657\n" +
                "        ],\n" +
                "        \"l_foot\": [\n" +
                "            0,\n" +
                "            0\n" +
                "        ],\n" +
                "        \"r_hip\": [\n" +
                "            695,\n" +
                "            500\n" +
                "        ],\n" +
                "        \"r_knee\": [\n" +
                "            695,\n" +
                "            688\n" +
                "        ],\n" +
                "        \"r_foot\": [\n" +
                "            0,\n" +
                "            0\n" +
                "        ],\n" +
                "        \"waist\": [\n" +
                "            640,\n" +
                "            375\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"head\": [\n" +
                "            667,\n" +
                "            78\n" +
                "        ],\n" +
                "        \"neck\": [\n" +
                "            667,\n" +
                "            187\n" +
                "        ],\n" +
                "        \"l_shoulder\": [\n" +
                "            528,\n" +
                "            234\n" +
                "        ],\n" +
                "        \"l_elbow\": [\n" +
                "            445,\n" +
                "            234\n" +
                "        ],\n" +
                "        \"l_wrist\": [\n" +
                "            528,\n" +
                "            125\n" +
                "        ],\n" +
                "        \"r_shoulder\": [\n" +
                "            779,\n" +
                "            250\n" +
                "        ],\n" +
                "        \"r_elbow\": [\n" +
                "            806,\n" +
                "            422\n" +
                "        ],\n" +
                "        \"r_wrist\": [\n" +
                "            779,\n" +
                "            516\n" +
                "        ],\n" +
                "        \"l_hip\": [\n" +
                "            584,\n" +
                "            500\n" +
                "        ],\n" +
                "        \"l_knee\": [\n" +
                "            584,\n" +
                "            657\n" +
                "        ],\n" +
                "        \"l_foot\": [\n" +
                "            0,\n" +
                "            0\n" +
                "        ],\n" +
                "        \"r_hip\": [\n" +
                "            695,\n" +
                "            500\n" +
                "        ],\n" +
                "        \"r_knee\": [\n" +
                "            695,\n" +
                "            688\n" +
                "        ],\n" +
                "        \"r_foot\": [\n" +
                "            0,\n" +
                "            0\n" +
                "        ],\n" +
                "        \"waist\": [\n" +
                "            640,\n" +
                "            375\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"head\": [\n" +
                "            667,\n" +
                "            78\n" +
                "        ],\n" +
                "        \"neck\": [\n" +
                "            667,\n" +
                "            187\n" +
                "        ],\n" +
                "        \"l_shoulder\": [\n" +
                "            528,\n" +
                "            234\n" +
                "        ],\n" +
                "        \"l_elbow\": [\n" +
                "            445,\n" +
                "            234\n" +
                "        ],\n" +
                "        \"l_wrist\": [\n" +
                "            528,\n" +
                "            125\n" +
                "        ],\n" +
                "        \"r_shoulder\": [\n" +
                "            779,\n" +
                "            250\n" +
                "        ],\n" +
                "        \"r_elbow\": [\n" +
                "            806,\n" +
                "            422\n" +
                "        ],\n" +
                "        \"r_wrist\": [\n" +
                "            779,\n" +
                "            516\n" +
                "        ],\n" +
                "        \"l_hip\": [\n" +
                "            584,\n" +
                "            500\n" +
                "        ],\n" +
                "        \"l_knee\": [\n" +
                "            584,\n" +
                "            657\n" +
                "        ],\n" +
                "        \"l_foot\": [\n" +
                "            0,\n" +
                "            0\n" +
                "        ],\n" +
                "        \"r_hip\": [\n" +
                "            695,\n" +
                "            500\n" +
                "        ],\n" +
                "        \"r_knee\": [\n" +
                "            695,\n" +
                "            688\n" +
                "        ],\n" +
                "        \"r_foot\": [\n" +
                "            0,\n" +
                "            0\n" +
                "        ],\n" +
                "        \"waist\": [\n" +
                "            640,\n" +
                "            375\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"head\": [\n" +
                "            667,\n" +
                "            78\n" +
                "        ],\n" +
                "        \"neck\": [\n" +
                "            667,\n" +
                "            187\n" +
                "        ],\n" +
                "        \"l_shoulder\": [\n" +
                "            528,\n" +
                "            234\n" +
                "        ],\n" +
                "        \"l_elbow\": [\n" +
                "            445,\n" +
                "            234\n" +
                "        ],\n" +
                "        \"l_wrist\": [\n" +
                "            528,\n" +
                "            125\n" +
                "        ],\n" +
                "        \"r_shoulder\": [\n" +
                "            779,\n" +
                "            250\n" +
                "        ],\n" +
                "        \"r_elbow\": [\n" +
                "            806,\n" +
                "            422\n" +
                "        ],\n" +
                "        \"r_wrist\": [\n" +
                "            779,\n" +
                "            516\n" +
                "        ],\n" +
                "        \"l_hip\": [\n" +
                "            584,\n" +
                "            500\n" +
                "        ],\n" +
                "        \"l_knee\": [\n" +
                "            584,\n" +
                "            657\n" +
                "        ],\n" +
                "        \"l_foot\": [\n" +
                "            0,\n" +
                "            0\n" +
                "        ],\n" +
                "        \"r_hip\": [\n" +
                "            695,\n" +
                "            500\n" +
                "        ],\n" +
                "        \"r_knee\": [\n" +
                "            695,\n" +
                "            688\n" +
                "        ],\n" +
                "        \"r_foot\": [\n" +
                "            0,\n" +
                "            0\n" +
                "        ],\n" +
                "        \"waist\": [\n" +
                "            640,\n" +
                "            375\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"head\": [\n" +
                "            667,\n" +
                "            78\n" +
                "        ],\n" +
                "        \"neck\": [\n" +
                "            667,\n" +
                "            187\n" +
                "        ],\n" +
                "        \"l_shoulder\": [\n" +
                "            528,\n" +
                "            234\n" +
                "        ],\n" +
                "        \"l_elbow\": [\n" +
                "            445,\n" +
                "            234\n" +
                "        ],\n" +
                "        \"l_wrist\": [\n" +
                "            528,\n" +
                "            125\n" +
                "        ],\n" +
                "        \"r_shoulder\": [\n" +
                "            779,\n" +
                "            250\n" +
                "        ],\n" +
                "        \"r_elbow\": [\n" +
                "            806,\n" +
                "            422\n" +
                "        ],\n" +
                "        \"r_wrist\": [\n" +
                "            779,\n" +
                "            516\n" +
                "        ],\n" +
                "        \"l_hip\": [\n" +
                "            584,\n" +
                "            500\n" +
                "        ],\n" +
                "        \"l_knee\": [\n" +
                "            584,\n" +
                "            657\n" +
                "        ],\n" +
                "        \"l_foot\": [\n" +
                "            0,\n" +
                "            0\n" +
                "        ],\n" +
                "        \"r_hip\": [\n" +
                "            695,\n" +
                "            500\n" +
                "        ],\n" +
                "        \"r_knee\": [\n" +
                "            695,\n" +
                "            688\n" +
                "        ],\n" +
                "        \"r_foot\": [\n" +
                "            0,\n" +
                "            0\n" +
                "        ],\n" +
                "        \"waist\": [\n" +
                "            640,\n" +
                "            375\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"head\": [\n" +
                "            667,\n" +
                "            78\n" +
                "        ],\n" +
                "        \"neck\": [\n" +
                "            667,\n" +
                "            187\n" +
                "        ],\n" +
                "        \"l_shoulder\": [\n" +
                "            528,\n" +
                "            234\n" +
                "        ],\n" +
                "        \"l_elbow\": [\n" +
                "            445,\n" +
                "            234\n" +
                "        ],\n" +
                "        \"l_wrist\": [\n" +
                "            528,\n" +
                "            125\n" +
                "        ],\n" +
                "        \"r_shoulder\": [\n" +
                "            779,\n" +
                "            250\n" +
                "        ],\n" +
                "        \"r_elbow\": [\n" +
                "            806,\n" +
                "            422\n" +
                "        ],\n" +
                "        \"r_wrist\": [\n" +
                "            779,\n" +
                "            516\n" +
                "        ],\n" +
                "        \"l_hip\": [\n" +
                "            584,\n" +
                "            500\n" +
                "        ],\n" +
                "        \"l_knee\": [\n" +
                "            584,\n" +
                "            657\n" +
                "        ],\n" +
                "        \"l_foot\": [\n" +
                "            0,\n" +
                "            0\n" +
                "        ],\n" +
                "        \"r_hip\": [\n" +
                "            695,\n" +
                "            500\n" +
                "        ],\n" +
                "        \"r_knee\": [\n" +
                "            695,\n" +
                "            688\n" +
                "        ],\n" +
                "        \"r_foot\": [\n" +
                "            0,\n" +
                "            0\n" +
                "        ],\n" +
                "        \"waist\": [\n" +
                "            640,\n" +
                "            375\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"head\": [\n" +
                "            667,\n" +
                "            78\n" +
                "        ],\n" +
                "        \"neck\": [\n" +
                "            667,\n" +
                "            187\n" +
                "        ],\n" +
                "        \"l_shoulder\": [\n" +
                "            528,\n" +
                "            234\n" +
                "        ],\n" +
                "        \"l_elbow\": [\n" +
                "            445,\n" +
                "            234\n" +
                "        ],\n" +
                "        \"l_wrist\": [\n" +
                "            528,\n" +
                "            125\n" +
                "        ],\n" +
                "        \"r_shoulder\": [\n" +
                "            779,\n" +
                "            250\n" +
                "        ],\n" +
                "        \"r_elbow\": [\n" +
                "            806,\n" +
                "            422\n" +
                "        ],\n" +
                "        \"r_wrist\": [\n" +
                "            779,\n" +
                "            516\n" +
                "        ],\n" +
                "        \"l_hip\": [\n" +
                "            584,\n" +
                "            500\n" +
                "        ],\n" +
                "        \"l_knee\": [\n" +
                "            584,\n" +
                "            657\n" +
                "        ],\n" +
                "        \"l_foot\": [\n" +
                "            0,\n" +
                "            0\n" +
                "        ],\n" +
                "        \"r_hip\": [\n" +
                "            695,\n" +
                "            500\n" +
                "        ],\n" +
                "        \"r_knee\": [\n" +
                "            695,\n" +
                "            688\n" +
                "        ],\n" +
                "        \"r_foot\": [\n" +
                "            0,\n" +
                "            0\n" +
                "        ],\n" +
                "        \"waist\": [\n" +
                "            640,\n" +
                "            375\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"head\": [\n" +
                "            667,\n" +
                "            78\n" +
                "        ],\n" +
                "        \"neck\": [\n" +
                "            667,\n" +
                "            187\n" +
                "        ],\n" +
                "        \"l_shoulder\": [\n" +
                "            528,\n" +
                "            234\n" +
                "        ],\n" +
                "        \"l_elbow\": [\n" +
                "            445,\n" +
                "            234\n" +
                "        ],\n" +
                "        \"l_wrist\": [\n" +
                "            528,\n" +
                "            125\n" +
                "        ],\n" +
                "        \"r_shoulder\": [\n" +
                "            779,\n" +
                "            250\n" +
                "        ],\n" +
                "        \"r_elbow\": [\n" +
                "            806,\n" +
                "            422\n" +
                "        ],\n" +
                "        \"r_wrist\": [\n" +
                "            779,\n" +
                "            516\n" +
                "        ],\n" +
                "        \"l_hip\": [\n" +
                "            584,\n" +
                "            500\n" +
                "        ],\n" +
                "        \"l_knee\": [\n" +
                "            584,\n" +
                "            657\n" +
                "        ],\n" +
                "        \"l_foot\": [\n" +
                "            0,\n" +
                "            0\n" +
                "        ],\n" +
                "        \"r_hip\": [\n" +
                "            695,\n" +
                "            500\n" +
                "        ],\n" +
                "        \"r_knee\": [\n" +
                "            695,\n" +
                "            688\n" +
                "        ],\n" +
                "        \"r_foot\": [\n" +
                "            0,\n" +
                "            0\n" +
                "        ],\n" +
                "        \"waist\": [\n" +
                "            640,\n" +
                "            375\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"head\": [\n" +
                "            667,\n" +
                "            78\n" +
                "        ],\n" +
                "        \"neck\": [\n" +
                "            667,\n" +
                "            187\n" +
                "        ],\n" +
                "        \"l_shoulder\": [\n" +
                "            528,\n" +
                "            234\n" +
                "        ],\n" +
                "        \"l_elbow\": [\n" +
                "            445,\n" +
                "            234\n" +
                "        ],\n" +
                "        \"l_wrist\": [\n" +
                "            528,\n" +
                "            125\n" +
                "        ],\n" +
                "        \"r_shoulder\": [\n" +
                "            779,\n" +
                "            250\n" +
                "        ],\n" +
                "        \"r_elbow\": [\n" +
                "            806,\n" +
                "            422\n" +
                "        ],\n" +
                "        \"r_wrist\": [\n" +
                "            779,\n" +
                "            516\n" +
                "        ],\n" +
                "        \"l_hip\": [\n" +
                "            584,\n" +
                "            500\n" +
                "        ],\n" +
                "        \"l_knee\": [\n" +
                "            584,\n" +
                "            657\n" +
                "        ],\n" +
                "        \"l_foot\": [\n" +
                "            0,\n" +
                "            0\n" +
                "        ],\n" +
                "        \"r_hip\": [\n" +
                "            695,\n" +
                "            500\n" +
                "        ],\n" +
                "        \"r_knee\": [\n" +
                "            695,\n" +
                "            688\n" +
                "        ],\n" +
                "        \"r_foot\": [\n" +
                "            0,\n" +
                "            0\n" +
                "        ],\n" +
                "        \"waist\": [\n" +
                "            640,\n" +
                "            375\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"head\": [\n" +
                "            667,\n" +
                "            78\n" +
                "        ],\n" +
                "        \"neck\": [\n" +
                "            667,\n" +
                "            187\n" +
                "        ],\n" +
                "        \"l_shoulder\": [\n" +
                "            528,\n" +
                "            234\n" +
                "        ],\n" +
                "        \"l_elbow\": [\n" +
                "            445,\n" +
                "            234\n" +
                "        ],\n" +
                "        \"l_wrist\": [\n" +
                "            528,\n" +
                "            125\n" +
                "        ],\n" +
                "        \"r_shoulder\": [\n" +
                "            779,\n" +
                "            250\n" +
                "        ],\n" +
                "        \"r_elbow\": [\n" +
                "            806,\n" +
                "            422\n" +
                "        ],\n" +
                "        \"r_wrist\": [\n" +
                "            779,\n" +
                "            516\n" +
                "        ],\n" +
                "        \"l_hip\": [\n" +
                "            584,\n" +
                "            500\n" +
                "        ],\n" +
                "        \"l_knee\": [\n" +
                "            556,\n" +
                "            657\n" +
                "        ],\n" +
                "        \"l_foot\": [\n" +
                "            0,\n" +
                "            0\n" +
                "        ],\n" +
                "        \"r_hip\": [\n" +
                "            695,\n" +
                "            500\n" +
                "        ],\n" +
                "        \"r_knee\": [\n" +
                "            695,\n" +
                "            688\n" +
                "        ],\n" +
                "        \"r_foot\": [\n" +
                "            0,\n" +
                "            0\n" +
                "        ],\n" +
                "        \"waist\": [\n" +
                "            640,\n" +
                "            375\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"head\": [\n" +
                "            667,\n" +
                "            78\n" +
                "        ],\n" +
                "        \"neck\": [\n" +
                "            667,\n" +
                "            187\n" +
                "        ],\n" +
                "        \"l_shoulder\": [\n" +
                "            528,\n" +
                "            234\n" +
                "        ],\n" +
                "        \"l_elbow\": [\n" +
                "            445,\n" +
                "            234\n" +
                "        ],\n" +
                "        \"l_wrist\": [\n" +
                "            528,\n" +
                "            125\n" +
                "        ],\n" +
                "        \"r_shoulder\": [\n" +
                "            779,\n" +
                "            250\n" +
                "        ],\n" +
                "        \"r_elbow\": [\n" +
                "            806,\n" +
                "            422\n" +
                "        ],\n" +
                "        \"r_wrist\": [\n" +
                "            779,\n" +
                "            516\n" +
                "        ],\n" +
                "        \"l_hip\": [\n" +
                "            584,\n" +
                "            500\n" +
                "        ],\n" +
                "        \"l_knee\": [\n" +
                "            584,\n" +
                "            657\n" +
                "        ],\n" +
                "        \"l_foot\": [\n" +
                "            0,\n" +
                "            0\n" +
                "        ],\n" +
                "        \"r_hip\": [\n" +
                "            695,\n" +
                "            500\n" +
                "        ],\n" +
                "        \"r_knee\": [\n" +
                "            695,\n" +
                "            688\n" +
                "        ],\n" +
                "        \"r_foot\": [\n" +
                "            0,\n" +
                "            0\n" +
                "        ],\n" +
                "        \"waist\": [\n" +
                "            640,\n" +
                "            375\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"head\": [\n" +
                "            667,\n" +
                "            78\n" +
                "        ],\n" +
                "        \"neck\": [\n" +
                "            667,\n" +
                "            187\n" +
                "        ],\n" +
                "        \"l_shoulder\": [\n" +
                "            528,\n" +
                "            234\n" +
                "        ],\n" +
                "        \"l_elbow\": [\n" +
                "            445,\n" +
                "            234\n" +
                "        ],\n" +
                "        \"l_wrist\": [\n" +
                "            528,\n" +
                "            125\n" +
                "        ],\n" +
                "        \"r_shoulder\": [\n" +
                "            779,\n" +
                "            250\n" +
                "        ],\n" +
                "        \"r_elbow\": [\n" +
                "            806,\n" +
                "            422\n" +
                "        ],\n" +
                "        \"r_wrist\": [\n" +
                "            779,\n" +
                "            516\n" +
                "        ],\n" +
                "        \"l_hip\": [\n" +
                "            584,\n" +
                "            500\n" +
                "        ],\n" +
                "        \"l_knee\": [\n" +
                "            584,\n" +
                "            657\n" +
                "        ],\n" +
                "        \"l_foot\": [\n" +
                "            0,\n" +
                "            0\n" +
                "        ],\n" +
                "        \"r_hip\": [\n" +
                "            695,\n" +
                "            500\n" +
                "        ],\n" +
                "        \"r_knee\": [\n" +
                "            695,\n" +
                "            688\n" +
                "        ],\n" +
                "        \"r_foot\": [\n" +
                "            0,\n" +
                "            0\n" +
                "        ],\n" +
                "        \"waist\": [\n" +
                "            640,\n" +
                "            375\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"head\": [\n" +
                "            667,\n" +
                "            78\n" +
                "        ],\n" +
                "        \"neck\": [\n" +
                "            667,\n" +
                "            187\n" +
                "        ],\n" +
                "        \"l_shoulder\": [\n" +
                "            528,\n" +
                "            234\n" +
                "        ],\n" +
                "        \"l_elbow\": [\n" +
                "            445,\n" +
                "            234\n" +
                "        ],\n" +
                "        \"l_wrist\": [\n" +
                "            528,\n" +
                "            125\n" +
                "        ],\n" +
                "        \"r_shoulder\": [\n" +
                "            779,\n" +
                "            250\n" +
                "        ],\n" +
                "        \"r_elbow\": [\n" +
                "            806,\n" +
                "            422\n" +
                "        ],\n" +
                "        \"r_wrist\": [\n" +
                "            779,\n" +
                "            516\n" +
                "        ],\n" +
                "        \"l_hip\": [\n" +
                "            584,\n" +
                "            500\n" +
                "        ],\n" +
                "        \"l_knee\": [\n" +
                "            584,\n" +
                "            657\n" +
                "        ],\n" +
                "        \"l_foot\": [\n" +
                "            0,\n" +
                "            0\n" +
                "        ],\n" +
                "        \"r_hip\": [\n" +
                "            695,\n" +
                "            500\n" +
                "        ],\n" +
                "        \"r_knee\": [\n" +
                "            695,\n" +
                "            688\n" +
                "        ],\n" +
                "        \"r_foot\": [\n" +
                "            0,\n" +
                "            0\n" +
                "        ],\n" +
                "        \"waist\": [\n" +
                "            640,\n" +
                "            375\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"head\": [\n" +
                "            667,\n" +
                "            78\n" +
                "        ],\n" +
                "        \"neck\": [\n" +
                "            667,\n" +
                "            187\n" +
                "        ],\n" +
                "        \"l_shoulder\": [\n" +
                "            528,\n" +
                "            234\n" +
                "        ],\n" +
                "        \"l_elbow\": [\n" +
                "            445,\n" +
                "            234\n" +
                "        ],\n" +
                "        \"l_wrist\": [\n" +
                "            528,\n" +
                "            125\n" +
                "        ],\n" +
                "        \"r_shoulder\": [\n" +
                "            779,\n" +
                "            250\n" +
                "        ],\n" +
                "        \"r_elbow\": [\n" +
                "            806,\n" +
                "            422\n" +
                "        ],\n" +
                "        \"r_wrist\": [\n" +
                "            779,\n" +
                "            516\n" +
                "        ],\n" +
                "        \"l_hip\": [\n" +
                "            584,\n" +
                "            500\n" +
                "        ],\n" +
                "        \"l_knee\": [\n" +
                "            584,\n" +
                "            657\n" +
                "        ],\n" +
                "        \"l_foot\": [\n" +
                "            0,\n" +
                "            0\n" +
                "        ],\n" +
                "        \"r_hip\": [\n" +
                "            695,\n" +
                "            500\n" +
                "        ],\n" +
                "        \"r_knee\": [\n" +
                "            695,\n" +
                "            688\n" +
                "        ],\n" +
                "        \"r_foot\": [\n" +
                "            0,\n" +
                "            0\n" +
                "        ],\n" +
                "        \"waist\": [\n" +
                "            640,\n" +
                "            375\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"head\": [\n" +
                "            667,\n" +
                "            78\n" +
                "        ],\n" +
                "        \"neck\": [\n" +
                "            667,\n" +
                "            187\n" +
                "        ],\n" +
                "        \"l_shoulder\": [\n" +
                "            528,\n" +
                "            234\n" +
                "        ],\n" +
                "        \"l_elbow\": [\n" +
                "            445,\n" +
                "            234\n" +
                "        ],\n" +
                "        \"l_wrist\": [\n" +
                "            528,\n" +
                "            125\n" +
                "        ],\n" +
                "        \"r_shoulder\": [\n" +
                "            779,\n" +
                "            250\n" +
                "        ],\n" +
                "        \"r_elbow\": [\n" +
                "            806,\n" +
                "            422\n" +
                "        ],\n" +
                "        \"r_wrist\": [\n" +
                "            779,\n" +
                "            516\n" +
                "        ],\n" +
                "        \"l_hip\": [\n" +
                "            584,\n" +
                "            500\n" +
                "        ],\n" +
                "        \"l_knee\": [\n" +
                "            584,\n" +
                "            657\n" +
                "        ],\n" +
                "        \"l_foot\": [\n" +
                "            0,\n" +
                "            0\n" +
                "        ],\n" +
                "        \"r_hip\": [\n" +
                "            695,\n" +
                "            500\n" +
                "        ],\n" +
                "        \"r_knee\": [\n" +
                "            695,\n" +
                "            688\n" +
                "        ],\n" +
                "        \"r_foot\": [\n" +
                "            0,\n" +
                "            0\n" +
                "        ],\n" +
                "        \"waist\": [\n" +
                "            640,\n" +
                "            375\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"head\": [\n" +
                "            667,\n" +
                "            78\n" +
                "        ],\n" +
                "        \"neck\": [\n" +
                "            667,\n" +
                "            187\n" +
                "        ],\n" +
                "        \"l_shoulder\": [\n" +
                "            528,\n" +
                "            234\n" +
                "        ],\n" +
                "        \"l_elbow\": [\n" +
                "            445,\n" +
                "            234\n" +
                "        ],\n" +
                "        \"l_wrist\": [\n" +
                "            528,\n" +
                "            125\n" +
                "        ],\n" +
                "        \"r_shoulder\": [\n" +
                "            779,\n" +
                "            250\n" +
                "        ],\n" +
                "        \"r_elbow\": [\n" +
                "            806,\n" +
                "            422\n" +
                "        ],\n" +
                "        \"r_wrist\": [\n" +
                "            779,\n" +
                "            516\n" +
                "        ],\n" +
                "        \"l_hip\": [\n" +
                "            584,\n" +
                "            500\n" +
                "        ],\n" +
                "        \"l_knee\": [\n" +
                "            584,\n" +
                "            657\n" +
                "        ],\n" +
                "        \"l_foot\": [\n" +
                "            0,\n" +
                "            0\n" +
                "        ],\n" +
                "        \"r_hip\": [\n" +
                "            695,\n" +
                "            500\n" +
                "        ],\n" +
                "        \"r_knee\": [\n" +
                "            695,\n" +
                "            688\n" +
                "        ],\n" +
                "        \"r_foot\": [\n" +
                "            0,\n" +
                "            0\n" +
                "        ],\n" +
                "        \"waist\": [\n" +
                "            640,\n" +
                "            375\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"head\": [\n" +
                "            667,\n" +
                "            78\n" +
                "        ],\n" +
                "        \"neck\": [\n" +
                "            667,\n" +
                "            187\n" +
                "        ],\n" +
                "        \"l_shoulder\": [\n" +
                "            528,\n" +
                "            234\n" +
                "        ],\n" +
                "        \"l_elbow\": [\n" +
                "            445,\n" +
                "            234\n" +
                "        ],\n" +
                "        \"l_wrist\": [\n" +
                "            528,\n" +
                "            125\n" +
                "        ],\n" +
                "        \"r_shoulder\": [\n" +
                "            779,\n" +
                "            250\n" +
                "        ],\n" +
                "        \"r_elbow\": [\n" +
                "            806,\n" +
                "            422\n" +
                "        ],\n" +
                "        \"r_wrist\": [\n" +
                "            779,\n" +
                "            516\n" +
                "        ],\n" +
                "        \"l_hip\": [\n" +
                "            584,\n" +
                "            500\n" +
                "        ],\n" +
                "        \"l_knee\": [\n" +
                "            584,\n" +
                "            657\n" +
                "        ],\n" +
                "        \"l_foot\": [\n" +
                "            0,\n" +
                "            0\n" +
                "        ],\n" +
                "        \"r_hip\": [\n" +
                "            695,\n" +
                "            500\n" +
                "        ],\n" +
                "        \"r_knee\": [\n" +
                "            695,\n" +
                "            688\n" +
                "        ],\n" +
                "        \"r_foot\": [\n" +
                "            0,\n" +
                "            0\n" +
                "        ],\n" +
                "        \"waist\": [\n" +
                "            640,\n" +
                "            375\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"head\": [\n" +
                "            667,\n" +
                "            78\n" +
                "        ],\n" +
                "        \"neck\": [\n" +
                "            667,\n" +
                "            187\n" +
                "        ],\n" +
                "        \"l_shoulder\": [\n" +
                "            528,\n" +
                "            234\n" +
                "        ],\n" +
                "        \"l_elbow\": [\n" +
                "            445,\n" +
                "            234\n" +
                "        ],\n" +
                "        \"l_wrist\": [\n" +
                "            528,\n" +
                "            125\n" +
                "        ],\n" +
                "        \"r_shoulder\": [\n" +
                "            779,\n" +
                "            250\n" +
                "        ],\n" +
                "        \"r_elbow\": [\n" +
                "            806,\n" +
                "            422\n" +
                "        ],\n" +
                "        \"r_wrist\": [\n" +
                "            779,\n" +
                "            516\n" +
                "        ],\n" +
                "        \"l_hip\": [\n" +
                "            584,\n" +
                "            500\n" +
                "        ],\n" +
                "        \"l_knee\": [\n" +
                "            584,\n" +
                "            657\n" +
                "        ],\n" +
                "        \"l_foot\": [\n" +
                "            0,\n" +
                "            0\n" +
                "        ],\n" +
                "        \"r_hip\": [\n" +
                "            695,\n" +
                "            500\n" +
                "        ],\n" +
                "        \"r_knee\": [\n" +
                "            695,\n" +
                "            688\n" +
                "        ],\n" +
                "        \"r_foot\": [\n" +
                "            0,\n" +
                "            0\n" +
                "        ],\n" +
                "        \"waist\": [\n" +
                "            640,\n" +
                "            375\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"head\": [\n" +
                "            667,\n" +
                "            78\n" +
                "        ],\n" +
                "        \"neck\": [\n" +
                "            667,\n" +
                "            187\n" +
                "        ],\n" +
                "        \"l_shoulder\": [\n" +
                "            528,\n" +
                "            234\n" +
                "        ],\n" +
                "        \"l_elbow\": [\n" +
                "            445,\n" +
                "            234\n" +
                "        ],\n" +
                "        \"l_wrist\": [\n" +
                "            528,\n" +
                "            125\n" +
                "        ],\n" +
                "        \"r_shoulder\": [\n" +
                "            779,\n" +
                "            250\n" +
                "        ],\n" +
                "        \"r_elbow\": [\n" +
                "            806,\n" +
                "            422\n" +
                "        ],\n" +
                "        \"r_wrist\": [\n" +
                "            779,\n" +
                "            516\n" +
                "        ],\n" +
                "        \"l_hip\": [\n" +
                "            584,\n" +
                "            500\n" +
                "        ],\n" +
                "        \"l_knee\": [\n" +
                "            584,\n" +
                "            657\n" +
                "        ],\n" +
                "        \"l_foot\": [\n" +
                "            0,\n" +
                "            0\n" +
                "        ],\n" +
                "        \"r_hip\": [\n" +
                "            695,\n" +
                "            500\n" +
                "        ],\n" +
                "        \"r_knee\": [\n" +
                "            695,\n" +
                "            688\n" +
                "        ],\n" +
                "        \"r_foot\": [\n" +
                "            0,\n" +
                "            0\n" +
                "        ],\n" +
                "        \"waist\": [\n" +
                "            640,\n" +
                "            375\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"head\": [\n" +
                "            667,\n" +
                "            78\n" +
                "        ],\n" +
                "        \"neck\": [\n" +
                "            667,\n" +
                "            187\n" +
                "        ],\n" +
                "        \"l_shoulder\": [\n" +
                "            528,\n" +
                "            234\n" +
                "        ],\n" +
                "        \"l_elbow\": [\n" +
                "            445,\n" +
                "            234\n" +
                "        ],\n" +
                "        \"l_wrist\": [\n" +
                "            528,\n" +
                "            125\n" +
                "        ],\n" +
                "        \"r_shoulder\": [\n" +
                "            779,\n" +
                "            250\n" +
                "        ],\n" +
                "        \"r_elbow\": [\n" +
                "            806,\n" +
                "            422\n" +
                "        ],\n" +
                "        \"r_wrist\": [\n" +
                "            779,\n" +
                "            516\n" +
                "        ],\n" +
                "        \"l_hip\": [\n" +
                "            584,\n" +
                "            500\n" +
                "        ],\n" +
                "        \"l_knee\": [\n" +
                "            584,\n" +
                "            657\n" +
                "        ],\n" +
                "        \"l_foot\": [\n" +
                "            0,\n" +
                "            0\n" +
                "        ],\n" +
                "        \"r_hip\": [\n" +
                "            695,\n" +
                "            500\n" +
                "        ],\n" +
                "        \"r_knee\": [\n" +
                "            695,\n" +
                "            688\n" +
                "        ],\n" +
                "        \"r_foot\": [\n" +
                "            0,\n" +
                "            0\n" +
                "        ],\n" +
                "        \"waist\": [\n" +
                "            640,\n" +
                "            375\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"head\": [\n" +
                "            667,\n" +
                "            78\n" +
                "        ],\n" +
                "        \"neck\": [\n" +
                "            667,\n" +
                "            187\n" +
                "        ],\n" +
                "        \"l_shoulder\": [\n" +
                "            528,\n" +
                "            234\n" +
                "        ],\n" +
                "        \"l_elbow\": [\n" +
                "            445,\n" +
                "            234\n" +
                "        ],\n" +
                "        \"l_wrist\": [\n" +
                "            528,\n" +
                "            125\n" +
                "        ],\n" +
                "        \"r_shoulder\": [\n" +
                "            779,\n" +
                "            250\n" +
                "        ],\n" +
                "        \"r_elbow\": [\n" +
                "            806,\n" +
                "            422\n" +
                "        ],\n" +
                "        \"r_wrist\": [\n" +
                "            779,\n" +
                "            516\n" +
                "        ],\n" +
                "        \"l_hip\": [\n" +
                "            584,\n" +
                "            500\n" +
                "        ],\n" +
                "        \"l_knee\": [\n" +
                "            584,\n" +
                "            657\n" +
                "        ],\n" +
                "        \"l_foot\": [\n" +
                "            0,\n" +
                "            0\n" +
                "        ],\n" +
                "        \"r_hip\": [\n" +
                "            695,\n" +
                "            500\n" +
                "        ],\n" +
                "        \"r_knee\": [\n" +
                "            695,\n" +
                "            688\n" +
                "        ],\n" +
                "        \"r_foot\": [\n" +
                "            0,\n" +
                "            0\n" +
                "        ],\n" +
                "        \"waist\": [\n" +
                "            640,\n" +
                "            375\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"head\": [\n" +
                "            667,\n" +
                "            78\n" +
                "        ],\n" +
                "        \"neck\": [\n" +
                "            667,\n" +
                "            187\n" +
                "        ],\n" +
                "        \"l_shoulder\": [\n" +
                "            528,\n" +
                "            234\n" +
                "        ],\n" +
                "        \"l_elbow\": [\n" +
                "            445,\n" +
                "            234\n" +
                "        ],\n" +
                "        \"l_wrist\": [\n" +
                "            528,\n" +
                "            125\n" +
                "        ],\n" +
                "        \"r_shoulder\": [\n" +
                "            779,\n" +
                "            250\n" +
                "        ],\n" +
                "        \"r_elbow\": [\n" +
                "            806,\n" +
                "            422\n" +
                "        ],\n" +
                "        \"r_wrist\": [\n" +
                "            779,\n" +
                "            516\n" +
                "        ],\n" +
                "        \"l_hip\": [\n" +
                "            584,\n" +
                "            500\n" +
                "        ],\n" +
                "        \"l_knee\": [\n" +
                "            584,\n" +
                "            657\n" +
                "        ],\n" +
                "        \"l_foot\": [\n" +
                "            0,\n" +
                "            0\n" +
                "        ],\n" +
                "        \"r_hip\": [\n" +
                "            695,\n" +
                "            500\n" +
                "        ],\n" +
                "        \"r_knee\": [\n" +
                "            695,\n" +
                "            688\n" +
                "        ],\n" +
                "        \"r_foot\": [\n" +
                "            0,\n" +
                "            0\n" +
                "        ],\n" +
                "        \"waist\": [\n" +
                "            640,\n" +
                "            375\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"head\": [\n" +
                "            667,\n" +
                "            78\n" +
                "        ],\n" +
                "        \"neck\": [\n" +
                "            667,\n" +
                "            187\n" +
                "        ],\n" +
                "        \"l_shoulder\": [\n" +
                "            528,\n" +
                "            234\n" +
                "        ],\n" +
                "        \"l_elbow\": [\n" +
                "            445,\n" +
                "            234\n" +
                "        ],\n" +
                "        \"l_wrist\": [\n" +
                "            528,\n" +
                "            125\n" +
                "        ],\n" +
                "        \"r_shoulder\": [\n" +
                "            779,\n" +
                "            250\n" +
                "        ],\n" +
                "        \"r_elbow\": [\n" +
                "            806,\n" +
                "            422\n" +
                "        ],\n" +
                "        \"r_wrist\": [\n" +
                "            779,\n" +
                "            516\n" +
                "        ],\n" +
                "        \"l_hip\": [\n" +
                "            584,\n" +
                "            500\n" +
                "        ],\n" +
                "        \"l_knee\": [\n" +
                "            584,\n" +
                "            657\n" +
                "        ],\n" +
                "        \"l_foot\": [\n" +
                "            0,\n" +
                "            0\n" +
                "        ],\n" +
                "        \"r_hip\": [\n" +
                "            695,\n" +
                "            500\n" +
                "        ],\n" +
                "        \"r_knee\": [\n" +
                "            723,\n" +
                "            673\n" +
                "        ],\n" +
                "        \"r_foot\": [\n" +
                "            0,\n" +
                "            0\n" +
                "        ],\n" +
                "        \"waist\": [\n" +
                "            640,\n" +
                "            375\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"head\": [\n" +
                "            667,\n" +
                "            78\n" +
                "        ],\n" +
                "        \"neck\": [\n" +
                "            667,\n" +
                "            187\n" +
                "        ],\n" +
                "        \"l_shoulder\": [\n" +
                "            528,\n" +
                "            234\n" +
                "        ],\n" +
                "        \"l_elbow\": [\n" +
                "            445,\n" +
                "            234\n" +
                "        ],\n" +
                "        \"l_wrist\": [\n" +
                "            528,\n" +
                "            125\n" +
                "        ],\n" +
                "        \"r_shoulder\": [\n" +
                "            779,\n" +
                "            250\n" +
                "        ],\n" +
                "        \"r_elbow\": [\n" +
                "            806,\n" +
                "            422\n" +
                "        ],\n" +
                "        \"r_wrist\": [\n" +
                "            779,\n" +
                "            516\n" +
                "        ],\n" +
                "        \"l_hip\": [\n" +
                "            584,\n" +
                "            500\n" +
                "        ],\n" +
                "        \"l_knee\": [\n" +
                "            584,\n" +
                "            657\n" +
                "        ],\n" +
                "        \"l_foot\": [\n" +
                "            0,\n" +
                "            0\n" +
                "        ],\n" +
                "        \"r_hip\": [\n" +
                "            695,\n" +
                "            500\n" +
                "        ],\n" +
                "        \"r_knee\": [\n" +
                "            723,\n" +
                "            673\n" +
                "        ],\n" +
                "        \"r_foot\": [\n" +
                "            0,\n" +
                "            0\n" +
                "        ],\n" +
                "        \"waist\": [\n" +
                "            640,\n" +
                "            375\n" +
                "        ]\n" +
                "    }\n" +
                "]";
        try {
            this.entries = new JSONArray(content);
        } catch (JSONException ex) {
            DebugLog.log(ex.getMessage());
        }

        executeInserts();

    }

    public void executeInserts() {
        long sessionId = insertSession(this.entries.length());
        long insertId = 0;
        MPI poseModel = new MPI();
        for (int i = 0; i < this.entries.length(); i++) {
            try {
                JSONObject jsonObject = this.entries.getJSONObject(i);
                long frameId = insertFrame(i);
                insertSessionFrame(frameId, sessionId);
                for (String spart : poseModel.body_parts) {

                    Object part = jsonObject.get(spart);
                    String values = part.toString();
                    values = values.substring(1, values.length() - 1);

                    String[] nums = values.split(",");
                    int [] coordinates = StringArrToIntArr(nums);

                    long recordInsertID = insertCoordinate(coordinates[0], coordinates[1]);
                    insertFrameCoordinate(frameId, recordInsertID);
                }
            } catch (JSONException ex) {
                DebugLog.log(ex.getMessage());
            }
        }


        /*
        insertCoordinates();
        insertSession();
        insertRelations();
         */
    }

    public MockData(AppDatabase appDatabase, String content) {
        this.appDatabase = appDatabase;
        try {
            this.entries = new JSONArray(content);
        } catch (JSONException ex) {
            DebugLog.log(ex.getMessage());
        }
    }

}
