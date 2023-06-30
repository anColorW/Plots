package me.color.plots.functions;
import java.lang.Math;
public class mainFunctions {

    public static int calculateDistance(Vector3 first, Vector3 second){

        double x = Math.pow((second.x - first.x), 2);
        double y = Math.pow((second.y - first.y), 2);
        double z = Math.pow((second.z - first.z), 2);

        return (int) Math.sqrt(x + y + z);
    }

    public static class Vector3{
        public int x,y,z;
    }
}
