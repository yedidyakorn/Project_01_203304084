package unittests;

import elements.AmbientLight;
import elements.Camera;
import elements.SpotLight;
import geometries.Geometries;
import geometries.Intersectable;
import geometries.Polygon;
import org.junit.Test;
import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;
import renderer.ImageWriter;
import renderer.Render;
import scene.Scene;

public class Chess {

    @Test
    public void chess() {
        Scene scene = new Scene("chess sphere");
        scene.setCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.setDistance(1000);
        scene.setBackground(new Color(java.awt.Color.BLACK));
        scene.setAmbientLight(new AmbientLight(Color.BLACK, 0));

        scene.addGeometries(chessBored(new Point3D(-100, 0, 50), 10));

        scene.addLights(new SpotLight(new Color(20, 30, 12), new Point3D(-100, 100, -500), new Vector(-1, 1, 2), 1,
                0.0004, 0.0000006));

//        scene.getCamera().setDepthOfFiled(10, 1, 15);
        ImageWriter imageWriter = new ImageWriter("chess", 200, 200, 300, 300);
        Render render = new Render(imageWriter, scene);
        render.setMultithreading(3);
        render.setDebugPrint();
        render.renderImage();
        render.writeToImage();
    }

    private Intersectable chessBored(Point3D point, double size) {
        Geometries list = new Geometries();
        double x = point.getX().get();
        double y = point.getY().get();
        double z = point.getZ().get();
        Point3D a;
        Point3D b;
        Point3D c;
        Point3D d;
        int k = 20;
        java.awt.Color color;
        Vector u = new Vector(0, 0, 1);
        Vector v = new Vector(1, 0, 0);
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < k; j++) {
                if ((j + i) % 2 == 0)
                    color = java.awt.Color.gray;
                else
                    color = java.awt.Color.darkGray;
                a = new Point3D(x + size * j, y + i * 5, z + size * i);
                b = new Point3D(x + size + size * j, y + i * 5, z + size * i + 100);
                c = new Point3D(x + size + size * j, y + i * 5, z + size + size * i + 100);
                d = new Point3D(x + size * j, y + i * 5, z + size + size * i);
                list.add(new Polygon(new Color(color), new Material(0.4, 0.3, 100, 0, 1), a, b, c, d));
                System.out.println(a + " " + b + " " + c + " " + d);
            }
        }
        return list;
    }
}
