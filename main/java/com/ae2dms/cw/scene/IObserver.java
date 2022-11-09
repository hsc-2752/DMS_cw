package com.ae2dms.cw.scene;

import com.ae2dms.cw.model.AllGameObject;
import javafx.scene.canvas.GraphicsContext;

/**
 * Interface for observer
 */
public interface IObserver {

    AllGameObject object = null;
    public abstract boolean update(GraphicsContext gc);

}
