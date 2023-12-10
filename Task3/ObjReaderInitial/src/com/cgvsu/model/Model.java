package com.cgvsu.model;
import com.cgvsu.math.Vector2f;
import com.cgvsu.math.Vector3f;

import java.util.*;

public class Model {

    public ArrayList<Vector3f> vertices = new ArrayList<Vector3f>();
    public ArrayList<Vector2f> textureVertices = new ArrayList<Vector2f>();
    public ArrayList<Vector3f> normals = new ArrayList<Vector3f>();
    public ArrayList<Polygon> polygons = new ArrayList<Polygon>();

    private Vector3f calculatePolygonNormal(Polygon polygon) {
        Vector3f v1 = vertices.get(polygon.getVertexIndices().get(0));
        Vector3f v2 = vertices.get(polygon.getVertexIndices().get(1));
        Vector3f v3 = vertices.get(polygon.getVertexIndices().get(2));
        Vector3f edge1 = v2.subtract(v1);
        Vector3f edge2 = v3.subtract(v1);
        return edge1.cross(edge2).normalize();
    }

    private List<Polygon> getPolygonsWithVertex(Vector3f vertex) {
        List<Polygon> polygonsWithVertex = new ArrayList<>();
        for (Polygon polygon : polygons) {
            for (Integer i : polygon.getVertexIndices()) {
                if (vertex.equals(vertices.get(i))) {
                    polygonsWithVertex.add(polygon);
                    break;
                }
            }
        }
        return polygonsWithVertex;
    }

    public Vector3f calculateVertexNormal(Vector3f vertex) {
        List<Polygon> polygonsWithVertex = getPolygonsWithVertex(vertex);
        Vector3f total = new Vector3f(0, 0, 0);
        for (Polygon polygon : polygonsWithVertex) {
            total.add(calculatePolygonNormal(polygon));
        }
        return total.divide(polygonsWithVertex.size()).normalize();
    }
}
