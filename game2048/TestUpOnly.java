package game2048;

import org.junit.Test;

import static org.junit.Assert.*;

/** Tests the tilt() method in the up (Side.NORTH) direction only.
 *
 * @author Omar Khan
 */
public class TestUpOnly extends TestUtils {

    /** Move tiles up (no merging). */
    @Test
    public void testUpNoMerge() {
        int[][] before = new int[][] {
                {0, 0, 4, 0},
                {0, 0, 0, 2},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };
        int[][] after = new int[][] {
                {0, 0, 4, 2},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };

        model = new Model(before, 0, 0, false);
        String prevBoard = model.toString();
        boolean changed = model.tilt(Side.NORTH);
        checkChanged(Side.NORTH, true, changed);
        checkModel(after, 0, 0, prevBoard, Side.NORTH);
    }

    /** A basic merge. */
    @Test
    public void testUpBasicMerge() {
        int[][] before = new int[][] {
                {0, 0, 0, 0},
                {0, 0, 2, 0},
                {0, 0, 2, 0},
                {0, 0, 0, 0},
        };
        int[][] after = new int[][] {
                {0, 0, 4, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };

        updateModel(before, 0, 0, false);
        String prevBoard = model.toString();
        boolean changed = model.tilt(Side.NORTH);
        checkChanged(Side.NORTH, true, changed);
        checkModel(after, 4, 0, prevBoard, Side.NORTH);
    }

    /** A triple merge. Only the leading 2 tiles should merge. */
    @Test
    public void testUpTripleMerge() {
        int[][] before = new int[][] {
                {0, 0, 2, 0},
                {0, 0, 0, 0},
                {0, 0, 2, 0},
                {0, 0, 2, 0},
        };
        int[][] after = new int[][] {
                {0, 0, 4, 0},
                {0, 0, 2, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };

        updateModel(before, 0, 0, false);
        String prevBoard = model.toString();
        boolean changed = model.tilt(Side.NORTH);
        checkChanged(Side.NORTH, true, changed);
        checkModel(after, 4, 0, prevBoard, Side.NORTH);
    }

    /** A tricky merge.
     *
     * The tricky part here is that the 4 tile on the bottom row shouldn't
     * merge with the newly created 4 tile on the top row. If you're failing
     * this test, try seeing how you can ensure that the bottom 4 tile doesn't
     * merge with the newly created 4 tile on top.
     */
    @Test
    public void testUpTrickyMerge() {
        int[][] before = new int[][] {
                {0, 0, 2, 0},
                {0, 0, 2, 0},
                {0, 0, 0, 0},
                {0, 0, 4, 0},
        };
        int[][] after = new int[][] {
                {0, 0, 4, 0},
                {0, 0, 4, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };

        updateModel(before, 0, 0, false);
        String prevBoard = model.toString();
        boolean changed = model.tilt(Side.NORTH);
        checkChanged(Side.NORTH, true, changed);
        checkModel(after, 4, 0, prevBoard, Side.NORTH);
    }

    @Test
    public void testUpBuggy() {
        int[][] before = new int[][] {
                {8, 16, 4, 2},
                {4, 32, 8, 2},
                {8, 2, 32, 2},
                {8, 0, 4, 0},
        };
        int[][] after = new int[][] {
                {8, 16, 4, 4},
                {4, 32, 8, 2},
                {16, 2, 32, 0},
                {0, 0, 4, 0},
        };

        updateModel(before, 0, 0, false);
        String prevBoard = model.toString();
        boolean changed = model.tilt(Side.NORTH);
        checkChanged(Side.NORTH, true, changed);
        checkModel(after, 20, 0, prevBoard, Side.NORTH);
    }
}
