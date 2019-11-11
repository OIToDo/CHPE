package com.mygdx.game.Analysis;

/**
 * Utility two component integer vector class. 
 */
class Vec2 {
    /**
     * value on the x-axis.
     */
    public int x;

    /**
     * value on the y-axis.
     */
    public int y;

    /**
     * int constructor.
     * @param x New x value.
     * @param y new y value.
     */
    public Vec2(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the absolute (positive) value of the vector.
     * @return New vector containing the result.
     */
    public Vec2 abs() {
        return new Vec2(
            (this.x > 0 ? x : x*-1),
            (this.y > 0 ? y : y*-1)
        );
    }

    /**
     * Checks if the vector is 0,0
     * @return Whether the vector is 0,0 or not
     */
    public boolean isNull() {
        return x == 0 && y == 0;
    }

    /**
     * Subtracts another vector from this vector.
     * @param rhs Other vector you want to subtract.
     * @return New vector based on the subtraction.
     */
    public Vec2 sub(Vec2 rhs) {
        return new Vec2(this.x - rhs.x, this.y - rhs.y);
    }

    /**
     * Adds another vector to this vector.
     * @param rhs Other vector you want to add.
     * @return New vector based on the addition.
     */
    public Vec2 add(Vec2 rhs) {
        return new Vec2(this.x + rhs.x, this.y + rhs.y);
    }

    /**
     * Integer constructor.
     * TODO: figure out if this can be simplified using generics, as far as I
     * found it's either not possible or not worth over casting beforehand.
     * @param x New x value.
     * @param y new y value.
     */
    public Vec2(Integer x, Integer y) {
        this.x = x.intValue();
        this.y = y.intValue();
    }

    /**
     * Long constructor.
     * TODO: figure out if this can be simplified using generics, as far as I
     * found it's either not possible or not worth over casting beforehand.
     * @param x New x value.
     * @param y new y value.
     */
    public Vec2(Long x, Long y) {
        this.x = x.intValue();
        this.y = y.intValue();
    }

    /**
     * Creates a String easy for printing to console.
     * @return String version of the vector.
     */
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}