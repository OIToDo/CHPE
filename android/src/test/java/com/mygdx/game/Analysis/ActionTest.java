package com.mygdx.game.Analysis;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * This class describes the
 */
public class ActionTest {
    @Test
    public void NameTest() {
        Action action = new Action("action");
        String action_name = action.getName();
        assertEquals(action.getName(), action_name);
    }

    @Test
    public void OccurrenceTest() {
        Action action = new Action("action");
        action.setOccurrence(true);
        assertEquals(action.occurred(), true);
    }
}
