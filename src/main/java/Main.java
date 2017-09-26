import org.lwjgl.glfw.GLFWErrorCallback;
import render.SceneRenderer;
import render.Window;

import static org.lwjgl.glfw.GLFW.glfwInit;

public class Main {

    public static void main(String... arg) {

        GLFWErrorCallback.createPrint(System.out).set();

        if (!glfwInit()) {
            throw new RuntimeException("Init failed");
        }


        Window window = new Window(600, 300, "jStars v0.1");
        SceneRenderer renderer = new SceneRenderer();
        window.loop(renderer::render);

    }
}
