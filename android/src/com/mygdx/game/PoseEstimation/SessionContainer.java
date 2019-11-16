package com.mygdx.game.PoseEstimation;

import com.mygdx.game.persistance.AppDatabase;
import com.mygdx.game.persistance.Coordinate.NNCoordinate;

import java.util.List;

public class SessionContainer {

    private class FrameContainer {
        private CoordinateContainer coordinateContainer;
        public FrameContainer(CoordinateContainer coordinateContainer) {
            this.coordinateContainer = coordinateContainer;
        }
    }

    private class CoordinateContainer {
        private List<NNCoordinate> nnCoordinateList;
        public CoordinateContainer(List<NNCoordinate> nnCoordinateList) {
            this.nnCoordinateList = nnCoordinateList;
        }
        public void insertContainer(AppDatabase appDatabase) {
            for (NNCoordinate nnCoordinate : nnCoordinateList) {
                appDatabase.nnCoordinateDAO().insert(nnCoordinate);
            }
        }
    }
}
