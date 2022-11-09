package com.ae2dms.cw.model.behavior;

import com.ae2dms.cw.model.CollidePossibleObject;

/**
 * An interface defines boundary behavior
 */
public interface BoundaryBehavior {

    void collideWith(CollidePossibleObject cpo);

}
