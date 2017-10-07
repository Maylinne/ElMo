package com.example.android.elmo;



/**
 * Created by Anasztázia on 2017.10.06..
 */

public class AttributeSpender {

    // Properties

    private int mRemainingPoints;
    private int mAttack;
    private int mDefense;
    private int mHitPoints;

    // region Constructors

    private AttributeSpender () {}

    public AttributeSpender (int remainingPoints) {
        this.mRemainingPoints = remainingPoints;
    }

    // endregion

    // region Getter, NO setters

    public int getmRemainingPoints() {
        return mRemainingPoints;
    }

    public void setmRemainingPoints(int mRemainingPoints) {
        this.mRemainingPoints = mRemainingPoints;
    }

    public int getmAttack() {
        return mAttack;
    }

    public int getmDefense() {
        return mDefense;
    }

    public int getmHitPoints() {
        return mHitPoints;
    }

    // endregion

    // region Methods

    public int IncreaseAttribute (int attr, int incr) {
        int result = 0;
        if (mRemainingPoints >= incr) {
            switch (attr) {
                case Constants.ATTACK:
                    if (mAttack + incr >= 0) {
                        mAttack += incr;
                        mRemainingPoints -= incr;

                    } break;
                case Constants.DEFENSE:
                    if (mDefense + incr >= 0) {
                        mDefense += incr;
                        mRemainingPoints -= incr;

                    } break;
                case Constants.HITPOINTS:
                    if (mHitPoints + incr >= 0) {
                        mHitPoints += incr;
                        mRemainingPoints -= incr;

                    } break;
                default:// ToDo Exception or notification
                    result = -1;
                    break;
            }

        } else {
            result = -1;
        }
        return result;
    }

    // endregion

}
