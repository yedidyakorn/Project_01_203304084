package unittests;

import elements.AmbientLight;
import elements.Camera;
import geometries.Sphere;
import org.junit.Test;
import primitives.Color;
import primitives.Point3D;
import primitives.Vector;
import renderer.ImageWriter;
import renderer.Render;
import scene.Scene;

public class superSamplingTests {
    @Test
//    public void superSampling() {
//        Scene scene = new Scene("Test scene");
//        scene.setCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
//        scene.setDistance(1000);
//        scene.setBackground(new Color(java.awt.Color.BLACK));
//        scene.setAmbientLight(new AmbientLight(Color.BLACK, 0));
//        int x = 0, y = 10, z = 0;
//        for (int i = 0; i < 7; i++) {
//            scene.addGeometries(
//                    new Sphere(new Color(java.awt.Color.MAGENTA), new Material(0.4, 0.3, 100, 0.3, 0),
//                            new Point3D(x, y - 2 * i, z + i * 5), 2),
//                    new Sphere(new Color(java.awt.Color.BLUE), new Material(0.4, 0.3, 100, 0.3, 0),
//                            new Point3D(x + 3, y - 2 * i, z + i * 5), 2),
//                    new Sphere(new Color(java.awt.Color.GREEN), new Material(0.4, 0.3, 100, 0.3, 0),
//                            new Point3D(x - 3, y - 2 * i, z + i * 5), 2));
//        }
//
//        scene.addLights(new SpotLight(new Color(1000, 600, 0), new Point3D(-100, 100, -500), new Vector(-1, 1, 2), 1,
//                0.0004, 0.0000006));
//
//        scene.getCamera().setDepthOfFiled(10, 1, 100);
//        ImageWriter imageWriter = new ImageWriter("SSAdepth10_1", 30, 30, 500, 500);
//        Render render = new Render(imageWriter, scene);
//        render.setMultithreading(3);
//        render.setDebugPrint();
//        render.renderImage();
//        render.writeToImage();
//
////        scene.getCamera().setDepthOfFiled(10, 0.5, 15);
////        ImageWriter imageWriter1 = new ImageWriter("SSAdepth10_0.5", 30, 30, 500, 500);
////        render = new Render(imageWriter1, scene);
////        render.setMultithreading(3);
////        render.setDebugPrint();
////        render.renderImage();
////        render.writeToImage();
//    }

    public void superSampling1() {
        Scene scene = new Scene("Test scene");
        scene.setCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.setDistance(1000);
        scene.setBackground(new Color(java.awt.Color.BLACK));
        scene.setAmbientLight(new AmbientLight(Color.BLACK, 0));

        scene.addGeometries(new Sphere(new Color(java.awt.Color.magenta), new Point3D(0, 0, 10), 1));

        scene.getCamera().setDepthOfFiled(10, 1, 100);
        ImageWriter imageWriter = new ImageWriter("SSAblank", 30, 30, 500, 500);
        Render render = new Render(imageWriter, scene);
        render.setMultithreading(3);
        render.setDebugPrint();
        render.renderImage();
        render.writeToImage();

    }
}
