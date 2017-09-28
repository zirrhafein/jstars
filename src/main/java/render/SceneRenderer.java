package render;

import static org.lwjgl.opengl.GL11.*;

public class SceneRenderer {

    public SceneRenderer() {
        glClearColor(0.1f, 0.2f, 0.2f, 0.0f);
    }

    public void render() {
        glClear(GL_COLOR_BUFFER_BIT);
    }

}
