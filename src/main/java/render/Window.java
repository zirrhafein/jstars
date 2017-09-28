package render;

import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.glfw.GLFWWindowSizeCallback;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class Window {

    private final long window;

    public interface Loopable {
        void doLoop();
    }

    public Window(int weight, int height, String caption) {
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 2);

        glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GL_TRUE);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);

        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);

        window = glfwCreateWindow(weight, height, caption, 0, 0);
        if (window == 0) {
            throw new RuntimeException("Can't create window");
        }

        GLFWVidMode vidMode = glfwGetVideoMode(glfwGetPrimaryMonitor());

        glfwSetWindowPos(window,
            (vidMode.width() - weight) /2,
            (vidMode.height() - height) /2);

        glfwMakeContextCurrent(window);
        glfwSwapInterval(1);
        glfwShowWindow(window);

        GL.createCapabilities();

        glfwSetWindowSizeCallback(window, new GLFWWindowSizeCallback() {
            @Override
            public void invoke(long window, int width, int height) {
                glViewport(0, 0, weight, height);
            }
        });
    }

    public void loop(Loopable loopable) {
        while (!glfwWindowShouldClose(window)) {
            loopable.doLoop();
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
            }
            glfwSwapBuffers(window);
            glfwPollEvents();
        }
    }
}
